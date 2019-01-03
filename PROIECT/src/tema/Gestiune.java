package tema;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.*;

public class Gestiune 
{
    private static final Gestiune gestiune=new Gestiune();
    
    private Gestiune()
    {
        
    }
    
    public static Gestiune getInstance()
    {
        return gestiune;
    }
    
    ArrayList <Produs> produse=new ArrayList<Produs>();
    Vector <Magazin> magazine=new Vector<Magazin>();
    
    Map <String,HashMap> taxe=new HashMap<String,HashMap>();
    
    public Map<String,HashMap> metoda(String in) throws IOException
    {
        ProdusComandat produscomandat1=new ProdusComandat();
        Map <String,HashMap> taxelele=new HashMap <String,HashMap>();
        
        taxelele=produscomandat1.metoda1(".\\src\\tema\\taxe.txt");
        return taxelele;
    }
    
    public void setProduse() throws IOException
    {
        Produs produs1=new Produs();
        this.produse=produs1.metoda(".\\src\\tema\\produse.txt");
    }
    
    public ArrayList <Produs> getProduse()
    {
        return produse;
    }
    
    public void setMagazine() throws IOException
    {
        Magazin magazin=new MiniMarket();
        this.magazine=magazin.metodaMagazine(".\\src\\tema\\facturi.txt");
    }
    
    public Vector <Magazin> getMagazine()
    {
        return magazine;
    }
    
    public void setTaxe() throws IOException
    {
        Gestiune gestiune=new Gestiune();
        this.taxe=gestiune.metoda(".\\src\\tema\\facturi.txt");
    }
    
    public Map <String,HashMap> getTaxele()
    {
        return taxe;
    }
    
    public String toString()
    {
        return this.produse+" "+this.magazine+" "+this.taxe;
    }
    
    static class comparare implements Comparator<Magazin>
    {
	public int compare(Magazin magazin1,Magazin magazin2)
	{
            return (int)magazin1.getTotalFaraTaxe()-(int)magazin2.getTotalFaraTaxe();
	}
    }
    
    static class comparare2 implements Comparator<Factura>
    {
	public int compare(Factura factura1,Factura factura2)
	{
            return (int)factura1.getTotalCuTaxe()-(int)factura2.getTotalCuTaxe();
	}
    }
    
    protected static void afis() throws IOException
    {
        Gestiune gestiune=new Gestiune();
        System.out.println(gestiune.metoda(".\\src\\tema\\facturi.txt"));     
    }
    
    public static void main(String args[]) throws IOException
    {
        Gestiune gestiune=Gestiune.getInstance();
        //gestiune.afis();
        
        gestiune.setProduse();
        //System.out.println(gestiune.getProduse());
        
        gestiune.setMagazine();
        //System.out.println(gestiune.getMagazine());
        
        gestiune.setTaxe();
        //System.out.println(gestiune.getTaxele());
        
        PrintWriter writer=new PrintWriter(".\\src\\tema\\out.txt");
        
        Vector <Magazin> magazine=new Vector <Magazin>();
        Map <String,HashMap> taxe=new HashMap <String,HashMap>();
        
        magazine=gestiune.getMagazine();
        taxe=gestiune.getTaxele();
        
        Vector <Magazin> minimarketuri=new Vector <Magazin>();
        Vector <Factura> facturimini=new Vector <Factura>();
        
        for(int i=0;i<magazine.size();i++)
        {
            if(magazine.get(i).getClass().getSimpleName().equals("MiniMarket"))
            {
                //System.out.println(magazine.get(i).getClass().getSimpleName());
                minimarketuri.add(magazine.get(i));
            }
        }
        
        Collections.sort(minimarketuri,new comparare());
        
        writer.println(minimarketuri.get(0).getClass().getSimpleName());
        
        for(int x=0;x<minimarketuri.size();x++)
        {
            //System.out.println(minimarketuri.get(x));
            //System.out.println(minimarketuri.get(x).getClass().getSimpleName());
            if(x==0) writer.println(minimarketuri.get(x).getNume()+"\r\n");
            if(x>0) writer.println("\r\n"+minimarketuri.get(x).getNume()+"\r\n");
            
            writer.print("Total"+" ");
            
            double farataxetotal=minimarketuri.get(x).getTotalFaraTaxe();
            double cutaxetotal=minimarketuri.get(x).getTotalCuTaxe();
            double cutaxescutitetotal=minimarketuri.get(x).getTotalCuTaxeScutite();
            
            int farataxetotal2=-1;
            int cutaxetotal2=-1;
            int cutaxescutitetotal2=-1;
                
            if(farataxetotal==Math.floor(farataxetotal)) farataxetotal2=(int) farataxetotal;
            if(cutaxetotal==Math.floor(cutaxetotal)) cutaxetotal2=(int) cutaxetotal;
            if(cutaxescutitetotal==Math.floor(cutaxescutitetotal)) cutaxescutitetotal2=(int) cutaxescutitetotal;
                
            if(farataxetotal2!=-1)
                writer.print(farataxetotal2+" ");
            else writer.print(farataxetotal+" ");
                
            if(cutaxetotal2!=-1)
                writer.print(cutaxetotal2+" ");
            else writer.print(cutaxetotal+" ");
                            
            if(cutaxescutitetotal2!=-1)
                writer.println(cutaxescutitetotal2+"\r\n");
            else writer.println(cutaxescutitetotal+"\r\n");
            
            //writer.println("Total"+" "+minimarketuri.get(x).getTotalFaraTaxe()+" "+minimarketuri.get(x).getTotalCuTaxe()+" "+minimarketuri.get(x).getTotalCuTaxeScutite()+"\r\n");
            
            writer.println("Tara");
            
            //System.out.println(taxe);
            Set set=taxe.entrySet();
            Iterator i=set.iterator();
            
            while(i.hasNext())
            {   
                Map.Entry entry=(Map.Entry)i.next();
                
                double farataxe=minimarketuri.get(x).getTotalTaraFaraTaxe((String) entry.getKey());
                double cutaxe=minimarketuri.get(x).getTotalTaraCuTaxe((String) entry.getKey());
                double cutaxescutite=minimarketuri.get(x).getTotalTaraCuTaxeScutite((String) entry.getKey());
              
              if(farataxe!=0.0)
              {
                int farataxe1=-1;
                int cutaxe1=-1;
                int cutaxescutite1=-1;
                
                if(farataxe==Math.floor(farataxe)) farataxe1=(int) farataxe;
                if(cutaxe==Math.floor(cutaxe)) cutaxe1=(int) cutaxe;
                if(cutaxescutite==Math.floor(cutaxescutite)) cutaxescutite1=(int) cutaxescutite;
                
                writer.print(entry.getKey()+" ");
                
                if(farataxe1!=-1)
                    writer.print(farataxe1+" ");
                else writer.print(farataxe+" ");
                
                if(cutaxe1!=-1)
                    writer.print(cutaxe1+" ");
                else writer.print(cutaxe+" ");
                            
                if(cutaxescutite1!=-1)
                    writer.println(cutaxescutite1);
                else writer.println(cutaxescutite);
              }
              else 
              {
                    writer.print(entry.getKey()+" ");
                    writer.println("0");
              }      
            }
            
            facturimini=minimarketuri.get(x).getFacturi();
            Collections.sort(facturimini,new comparare2());
            for(int a=0;a<facturimini.size();a++)
            {
                writer.println("\r\n"+facturimini.get(a).getDenumire()+"\r\n");
                
                writer.print("Total"+" ");
                
                double farataxetotalfactura=facturimini.get(a).getTotalFaraTaxe();
                double cutaxetotalfactura=facturimini.get(a).getTotalCuTaxe();
            
                int farataxetotalfactura2=-1;
                int cutaxetotalfactura2=-1;
                
                if(farataxetotalfactura==Math.floor(farataxetotalfactura)) farataxetotalfactura2=(int) farataxetotalfactura;
                if(cutaxetotalfactura==Math.floor(cutaxetotalfactura)) cutaxetotalfactura2=(int) cutaxetotalfactura;
                
                if(farataxetotalfactura2!=-1)
                    writer.print(farataxetotalfactura2+" ");
                else writer.print(farataxetotalfactura+" ");
                
                if(cutaxetotalfactura2!=-1)
                    writer.println(cutaxetotalfactura2+"\r\n");
                else writer.println(cutaxetotalfactura+"\r\n");
                
                //writer.println("Total"+" "+facturimini.get(a).getTotalFaraTaxe()+" "+facturimini.get(a).getTotalCuTaxe()+"\r\n");
            
                writer.println("Tara");
                
                Set set2=taxe.entrySet();
                Iterator i2=set2.iterator();
            
                while(i2.hasNext())
                {   
                    Map.Entry entry2=(Map.Entry)i2.next();
                
                    double farataxetara=facturimini.get(a).getTotalTaraFaraTaxe((String) entry2.getKey());
                    double cutaxetara=facturimini.get(a).getTotalTaraCuTaxe((String) entry2.getKey());
              
              if(farataxetara!=0.0)
              {
                int farataxetara1=-1;
                int cutaxetara1=-1;
                
                if(farataxetara==Math.floor(farataxetara)) farataxetara1=(int) farataxetara;
                if(cutaxetara==Math.floor(cutaxetara)) cutaxetara1=(int) cutaxetara;
                
                writer.print(entry2.getKey()+" ");
                
                if(farataxetara1!=-1)
                    writer.print(farataxetara1+" ");
                else writer.print(farataxetara+" ");
                
                if(cutaxetara1!=-1)
                    writer.println(cutaxetara1+" ");
                else writer.println(cutaxetara+" ");
              }
              else 
              {
                  writer.print(entry2.getKey()+" ");
                  writer.println("0");
              }
            }
            }
        }
        
        Vector <Magazin> mediummarketuri=new Vector <Magazin>();
        Vector <Factura> facturimedium=new Vector <Factura>();
        
        for(int j=0;j<magazine.size();j++)
        {
            if(magazine.get(j).getClass().getSimpleName().equals("MediumMarket"))
            {
                //System.out.println(magazine.get(j).getClass().getSimpleName());
                mediummarketuri.add(magazine.get(j));
            }
            
        }
        
        Collections.sort(mediummarketuri,new comparare());
        
        writer.println("\r\n"+mediummarketuri.get(0).getClass().getSimpleName());
        
        for(int y=0;y<mediummarketuri.size();y++)
        {
            //System.out.println(mediummarketuri.get(y));
            //System.out.println(mediummarketuri.get(y).getClass().getSimpleName());
            if(y==0) writer.println(mediummarketuri.get(y).getNume()+"\r\n");
            if(y>0) writer.println("\r\n"+mediummarketuri.get(y).getNume()+"\r\n");
            
            writer.print("Total"+" ");
            
            double farataxetotalmedium=mediummarketuri.get(y).getTotalFaraTaxe();
            double cutaxetotalmedium=mediummarketuri.get(y).getTotalCuTaxe();
            double cutaxescutitetotalmedium=mediummarketuri.get(y).getTotalCuTaxeScutite();
            
            int farataxetotalmedium2=-1;
            int cutaxetotalmedium2=-1;
            int cutaxescutitetotalmedium2=-1;
                
            if(farataxetotalmedium==Math.floor(farataxetotalmedium)) farataxetotalmedium2=(int) farataxetotalmedium;
            if(cutaxetotalmedium==Math.floor(cutaxetotalmedium)) cutaxetotalmedium2=(int) cutaxetotalmedium;
            if(cutaxescutitetotalmedium==Math.floor(cutaxescutitetotalmedium)) cutaxescutitetotalmedium2=(int) cutaxescutitetotalmedium;
                
            if(farataxetotalmedium2!=-1)
                writer.print(farataxetotalmedium2+" ");
            else writer.print(farataxetotalmedium+" ");
                
            if(cutaxetotalmedium2!=-1)
                writer.print(cutaxetotalmedium2+" ");
            else writer.print(cutaxetotalmedium+" ");
                            
            if(cutaxescutitetotalmedium2!=-1)
                writer.println(cutaxescutitetotalmedium2+"\r\n");
            else writer.println(cutaxescutitetotalmedium+"\r\n");
            
            //writer.println("Total"+" "+mediummarketuri.get(y).getTotalFaraTaxe()+" "+mediummarketuri.get(y).getTotalCuTaxe()+" "+mediummarketuri.get(y).getTotalCuTaxeScutite()+"\r\n");
            
            writer.println("Tara");
            
            Set set=taxe.entrySet();
            Iterator i=set.iterator();
            
            while(i.hasNext())
            {   
                Map.Entry entry=(Map.Entry)i.next();
                
                double farataxemedium=mediummarketuri.get(y).getTotalTaraFaraTaxe((String) entry.getKey());
                double cutaxemedium=mediummarketuri.get(y).getTotalTaraCuTaxe((String) entry.getKey());
                double cutaxescutitemedium=mediummarketuri.get(y).getTotalTaraCuTaxeScutite((String) entry.getKey());
              
              if(farataxemedium!=0.0)
              {
                int farataxemedium1=-1;
                int cutaxemedium1=-1;
                int cutaxescutitemedium1=-1;
                
                if(farataxemedium==Math.floor(farataxemedium)) farataxemedium1=(int) farataxemedium;
                if(cutaxemedium==Math.floor(cutaxemedium)) cutaxemedium1=(int) cutaxemedium;
                if(cutaxescutitemedium==Math.floor(cutaxescutitemedium)) cutaxescutitemedium1=(int) cutaxescutitemedium;
                
                writer.print(entry.getKey()+" ");
                
                if(farataxemedium1!=-1)
                    writer.print(farataxemedium1+" ");
                else writer.print(farataxemedium+" ");
                
                if(cutaxemedium1!=-1)
                    writer.print(cutaxemedium1+" ");
                else writer.print(cutaxemedium+" ");
                            
                if(cutaxescutitemedium1!=-1)
                    writer.println(cutaxescutitemedium1);
                else writer.println(cutaxescutitemedium);
              }
              else
              {
                    writer.print(entry.getKey()+" ");
                    writer.println("0");
              }
            }
            
            facturimedium=mediummarketuri.get(y).getFacturi();
            Collections.sort(facturimedium,new comparare2());
            for(int b=0;b<facturimedium.size();b++)
            {
                writer.println("\r\n"+facturimedium.get(b).getDenumire()+"\r\n");
                
                writer.print("Total"+" ");
                
                double farataxetotalfacturamedium=facturimedium.get(b).getTotalFaraTaxe();
                double cutaxetotalfacturamedium=facturimedium.get(b).getTotalCuTaxe();
            
                int farataxetotalfacturamedium2=-1;
                int cutaxetotalfacturamedium2=-1;
                
                if(farataxetotalfacturamedium==Math.floor(farataxetotalfacturamedium)) farataxetotalfacturamedium2=(int) farataxetotalfacturamedium;
                if(cutaxetotalfacturamedium==Math.floor(cutaxetotalfacturamedium)) cutaxetotalfacturamedium2=(int) cutaxetotalfacturamedium;
                
                if(farataxetotalfacturamedium2!=-1)
                    writer.print(farataxetotalfacturamedium2+" ");
                else writer.print(farataxetotalfacturamedium+" ");
                
                if(cutaxetotalfacturamedium2!=-1)
                    writer.println(cutaxetotalfacturamedium2+"\r\n");
                else writer.println(cutaxetotalfacturamedium+"\r\n");
                
                //writer.println("Total"+" "+facturimedium.get(b).getTotalFaraTaxe()+" "+facturimedium.get(b).getTotalCuTaxe()+"\r\n");
            
                writer.println("Tara");
                
                Set set2=taxe.entrySet();
                Iterator i2=set2.iterator();
            
                while(i2.hasNext())
                {   
                    Map.Entry entry2=(Map.Entry)i2.next();
                
                    double farataxetaramedium=facturimedium.get(b).getTotalTaraFaraTaxe((String) entry2.getKey());
                    double cutaxetaramedium=facturimedium.get(b).getTotalTaraCuTaxe((String) entry2.getKey());
              
                    if(farataxetaramedium!=0.0)
                    {
                        int farataxetaramedium1=-1;
                        int cutaxetaramedium1=-1;
                
                        if(farataxetaramedium==Math.floor(farataxetaramedium)) farataxetaramedium1=(int) farataxetaramedium;
                        if(cutaxetaramedium==Math.floor(cutaxetaramedium)) cutaxetaramedium1=(int) cutaxetaramedium;
                
                        writer.print(entry2.getKey()+" ");
                
                        if(farataxetaramedium1!=-1)
                            writer.print(farataxetaramedium1+" ");
                        else writer.print(farataxetaramedium+" ");
                
                        if(cutaxetaramedium1!=-1)
                            writer.println(cutaxetaramedium1+" ");
                        else writer.println(cutaxetaramedium+" ");
                    }
                    else 
                    {
                        writer.print(entry2.getKey()+" ");
                        writer.println("0");
                    }
                }
            }
        }
        
        Vector <Magazin> hypermarketuri=new Vector <Magazin>();
        Vector <Factura> facturihyper=new Vector <Factura>();
        
        for(int z=0;z<magazine.size();z++)
        {
            if(magazine.get(z).getClass().getSimpleName().equals("HyperMarket"))
            {
                //System.out.println(magazine.get(z).getClass().getSimpleName());
                hypermarketuri.add(magazine.get(z));
            }
        }
        
        Collections.sort(hypermarketuri,new comparare());
        
        writer.println("\r\n"+hypermarketuri.get(0).getClass().getSimpleName());
        
        for(int w=0;w<hypermarketuri.size();w++)
        {
            //System.out.println(hypermarketuri.get(w));
            //System.out.println(hypermarketuri.get(w).getClass().getSimpleName());
            if(w==0) writer.println(hypermarketuri.get(w).getNume()+"\r\n");
            if(w>0) writer.println("\r\n"+hypermarketuri.get(w).getNume()+"\r\n");
            
            writer.print("Total"+" ");
            
            double farataxetotalhyper=hypermarketuri.get(w).getTotalFaraTaxe();
            double cutaxetotalhyper=hypermarketuri.get(w).getTotalCuTaxe();
            double cutaxescutitetotalhyper=hypermarketuri.get(w).getTotalCuTaxeScutite();
            
            int farataxetotalhyper2=-1;
            int cutaxetotalhyper2=-1;
            int cutaxescutitetotalhyper2=-1;
                
            if(farataxetotalhyper==Math.floor(farataxetotalhyper)) farataxetotalhyper2=(int) farataxetotalhyper;
            if(cutaxetotalhyper==Math.floor(cutaxetotalhyper)) cutaxetotalhyper2=(int) cutaxetotalhyper;
            if(cutaxescutitetotalhyper==Math.floor(cutaxescutitetotalhyper)) cutaxescutitetotalhyper2=(int) cutaxescutitetotalhyper;
                
            if(farataxetotalhyper2!=-1)
                writer.print(farataxetotalhyper2+" ");
            else writer.print(farataxetotalhyper+" ");
                
            if(cutaxetotalhyper2!=-1)
                writer.print(cutaxetotalhyper2+" ");
            else writer.print(cutaxetotalhyper+" ");
                            
            if(cutaxescutitetotalhyper2!=-1)
                writer.println(cutaxescutitetotalhyper2+"\r\n");
            else writer.println(cutaxescutitetotalhyper+"\r\n");
            
            //writer.println("Total"+" "+hypermarketuri.get(w).getTotalFaraTaxe()+" "+hypermarketuri.get(w).getTotalCuTaxe()+" "+hypermarketuri.get(w).getTotalCuTaxeScutite()+"\r\n");
            
            writer.println("Tara");
            
            Set set=taxe.entrySet();
            Iterator i=set.iterator();
            
            while(i.hasNext())
            {   
                Map.Entry entry=(Map.Entry)i.next();
                
                double farataxehyper=hypermarketuri.get(w).getTotalTaraFaraTaxe((String) entry.getKey());
                double cutaxehyper=hypermarketuri.get(w).getTotalTaraCuTaxe((String) entry.getKey());
                double cutaxescutitehyper=hypermarketuri.get(w).getTotalTaraCuTaxeScutite((String) entry.getKey());
                
              if(farataxehyper!=0.0)
              {
                int farataxehyper1=-1;
                int cutaxehyper1=-1;
                int cutaxescutitehyper1=-1;
                
                if(farataxehyper==Math.floor(farataxehyper)) farataxehyper1=(int) farataxehyper;
                if(cutaxehyper==Math.floor(cutaxehyper)) cutaxehyper1=(int) cutaxehyper;
                if(cutaxescutitehyper==Math.floor(cutaxescutitehyper)) cutaxescutitehyper1=(int) cutaxescutitehyper;
                
                writer.print(entry.getKey()+" ");
                
                if(farataxehyper1!=-1)
                    writer.print(farataxehyper1+" ");
                else writer.print(farataxehyper+" ");
                
                if(cutaxehyper1!=-1)
                    writer.print(cutaxehyper1+" ");
                else writer.print(cutaxehyper+" ");
                            
                if(cutaxescutitehyper1!=-1)
                    writer.println(cutaxescutitehyper1);
                else writer.println(cutaxescutitehyper);
              }
              else 
              {
                  writer.print(entry.getKey()+" ");
                  writer.println("0");
              }
             }
            
            facturihyper=hypermarketuri.get(w).getFacturi();
            Collections.sort(facturihyper,new comparare2());
            for(int c=0;c<facturihyper.size();c++)
            {
                writer.println("\r\n"+facturihyper.get(c).getDenumire()+"\r\n");
                
                writer.print("Total"+" ");
                
                double farataxetotalfacturahyper=facturihyper.get(c).getTotalFaraTaxe();
                double cutaxetotalfacturahyper=facturihyper.get(c).getTotalCuTaxe();
            
                int farataxetotalfacturahyper2=-1;
                int cutaxetotalfacturahyper2=-1;
                
                if(farataxetotalfacturahyper==Math.floor(farataxetotalfacturahyper)) farataxetotalfacturahyper2=(int) farataxetotalfacturahyper;
                if(cutaxetotalfacturahyper==Math.floor(cutaxetotalfacturahyper)) cutaxetotalfacturahyper2=(int) cutaxetotalfacturahyper;
                
                if(farataxetotalfacturahyper2!=-1)
                    writer.print(farataxetotalfacturahyper2+" ");
                else writer.print(farataxetotalfacturahyper+" ");
                
                if(cutaxetotalfacturahyper2!=-1)
                    writer.println(cutaxetotalfacturahyper2+"\r\n");
                else writer.println(cutaxetotalfacturahyper+"\r\n");
                
                //writer.println("Total"+" "+facturihyper.get(c).getTotalFaraTaxe()+" "+facturihyper.get(c).getTotalCuTaxe()+"\r\n");
            
                writer.println("Tara");
                
                Set set2=taxe.entrySet();
                Iterator i2=set2.iterator();
            
                while(i2.hasNext())
                {   
                    Map.Entry entry2=(Map.Entry)i2.next();
                
                    double farataxetarahyper=facturihyper.get(c).getTotalTaraFaraTaxe((String) entry2.getKey());
                    double cutaxetarahyper=facturihyper.get(c).getTotalTaraCuTaxe((String) entry2.getKey());
                    
                  if(c!=(facturihyper.size()-1)||i2.hasNext())
                  {  
                    if(farataxetarahyper!=0.0)
                    {
                        int farataxetarahyper1=-1;
                        int cutaxetarahyper1=-1;
                
                        if(farataxetarahyper==Math.floor(farataxetarahyper)) farataxetarahyper1=(int) farataxetarahyper;
                        if(cutaxetarahyper==Math.floor(cutaxetarahyper)) cutaxetarahyper1=(int) cutaxetarahyper;
                
                        writer.print(entry2.getKey()+" ");
                
                        if(farataxetarahyper1!=-1)
                            writer.print(farataxetarahyper1+" ");
                        else writer.print(farataxetarahyper+" ");
                
                        if(cutaxetarahyper1!=-1)
                            writer.println(cutaxetarahyper1+" ");
                        else writer.println(cutaxetarahyper+" ");
                    }
                    else
                    {
                        writer.print(entry2.getKey()+" ");
                        writer.println("0");
                    }
                  }
                  else
                  {
                    if(farataxetarahyper!=0.0)
                    {
                        int farataxetarahyper1=-1;
                        int cutaxetarahyper1=-1;
                
                        if(farataxetarahyper==Math.floor(farataxetarahyper)) farataxetarahyper1=(int) farataxetarahyper;
                        if(cutaxetarahyper==Math.floor(cutaxetarahyper)) cutaxetarahyper1=(int) cutaxetarahyper;
                
                        writer.print(entry2.getKey()+" ");
                
                        if(farataxetarahyper1!=-1)
                            writer.print(farataxetarahyper1+" ");
                        else writer.print(farataxetarahyper+" ");
                
                        if(cutaxetarahyper1!=-1)
                            writer.print(cutaxetarahyper1+" ");
                        else writer.print(cutaxetarahyper+" ");
                    }
                    else 
                    {
                        writer.print(entry2.getKey()+" ");
                        writer.print("0");
                    }
                  }
                }
            }
        }
        writer.close();
    }
}