package tema;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

abstract class Magazin implements IMagazin
{
    String nume;//obtinut din fisier(MegaImage1)
    Vector <Factura> facturi=new Vector<Factura>();
    
    public Magazin(String nume)
    {
        this.nume=nume;
        facturi=new Vector<Factura>();
    }
    
    public Magazin()
    {
        this("orice",new Vector<Factura>());
    }
    
    public Magazin(String nume,Vector <Factura> facturi)
    {
        this.nume=nume;
        this.facturi=facturi;
    }
    
    public String getNume()
    {
        return nume;
    }
    
    public Vector<Factura> getFacturi()
    {
        return facturi;
    }
    
    @Override
    public double getTotalFaraTaxe()
    {
        double total=0.0;
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi=this.facturi;
        
        for(int i=0;i<facturi.size();i++)
        {
            total=total+facturi.get(i).getTotalFaraTaxe();
        }
        
        return Math.round(total*10000.0)/10000.0;
    }
    
    @Override
    public double getTotalCuTaxe()
    {
        double total=0.0;
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi=this.facturi;
        
        for(int i=0;i<facturi.size();i++)
        {
            total=total+facturi.get(i).getTotalCuTaxe();
        }
        
        return Math.round(total*10000.0)/10000.0;
    }
    
    @Override
    public double getTotalCuTaxeScutite() throws IOException
    {
        double total=0.0;
        
        if(this.getClass().getSimpleName().equals("MiniMarket"))
        {
            total=total+this.getTotalCuTaxe()-this.calculScutiriTaxe()*this.getTotalCuTaxe();
        }
        
        if(this.getClass().getSimpleName().equals("MediumMarket"))
        {
            total=total+this.getTotalCuTaxe()-this.calculScutiriTaxe()*this.getTotalCuTaxe();
        }
        
        if(this.getClass().getSimpleName().equals("HyperMarket"))
        {
            total=total+this.getTotalCuTaxe()-this.calculScutiriTaxe()*this.getTotalCuTaxe();
        }
        
        return Math.round(total*10000.0)/10000.0;
    }
    
    @Override
    public double getTotalTaraFaraTaxe(String taraOrigine)
    {
        double total=0.0;
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi=this.facturi;
        
        for(int i=0;i<facturi.size();i++)
        {
            total=total+facturi.get(i).getTotalTaraFaraTaxe(taraOrigine);
        }
        
        return Math.round(total*10000.0)/10000.0;
    }
    
    @Override
    public double getTotalTaraCuTaxe(String taraOrigine)
    {
        double total=0.0;
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi=this.facturi;
        
        for(int i=0;i<facturi.size();i++)
        {
            total=total+facturi.get(i).getTotalTaraCuTaxe(taraOrigine);
        }
        
        return Math.round(total*10000.0)/10000.0; 
    }
    
    @Override
    public double getTotalTaraCuTaxeScutite(String taraOrigine) throws IOException
    {
        double total=0.0;
        
        if(this.getClass().getSimpleName().equals("MiniMarket"))
        {
            total=total+this.getTotalTaraCuTaxe(taraOrigine)-this.calculScutiriTaxe()*this.getTotalTaraCuTaxe(taraOrigine);
        }
        
        if(this.getClass().getSimpleName().equals("MediumMarket"))
        {
            total=total+this.getTotalTaraCuTaxe(taraOrigine)-this.calculScutiriTaxe()*this.getTotalTaraCuTaxe(taraOrigine);
        }
        
        if(this.getClass().getSimpleName().equals("HyperMarket"))
        {
            total=total+this.getTotalTaraCuTaxe(taraOrigine)-this.calculScutiriTaxe()*this.getTotalTaraCuTaxe(taraOrigine);
        }
        
        return Math.round(total*10000.0)/10000.0;
    }
    
    @Override
    public double getTotalCategorieCuTaxe(String categorie)
    {
        double total=0.0;
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi=this.facturi;
        
        for(int i=0;i<facturi.size();i++)
        {
            total=total+facturi.get(i).getTotalCategorieCuTaxe(categorie);
        }
        
        return Math.round(total*10000.0)/10000.0; 
    }
    
    abstract public double calculScutiriTaxe() throws IOException;
    
    public String toString()
    {
        return "\nNume Magazin: "+this.nume+"\n"+"Facturi:\n"+this.facturi;
    }
    
    public Magazin creeazaMagazin(String Tip,String nume,Vector <Factura> facturi)
    {
        /*Vector <ProdusComandat> produsecomandate=new Vector <ProdusComandat>();
        produsecomandate.add(new ProdusComandat());
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi.add(new Factura("orice",produsecomandate));
        
        Magazin magazin=new MiniMarket("orice","orice",facturi);*/
        
        Magazin magazin=null;
        
        switch(Tip)
        {
            case "MiniMarket":
                magazin=new MiniMarket(Tip,nume,facturi);
                //System.out.println(Tip+" "+nume+" "+facturi);
                break;
                
            case "MediumMarket":
                magazin=new MediumMarket(Tip,nume,facturi);
                //System.out.println(Tip+" "+nume+" "+facturi);
                break;
                
            case "HyperMarket":
                magazin=new HyperMarket(Tip,nume,facturi);
                //System.out.println(Tip+" "+nume+" "+facturi);
                break;
                
            default:
                
                break;
        }
      
        return magazin;
    }
    
    public Vector<Magazin> metodaMagazine(String in) throws IOException
    {
        Vector <Factura> facturi=new Vector <Factura>();
        
        MiniMarket minimarket=new MiniMarket("orice","orice",facturi);
        MediumMarket mediummarket=new MediumMarket("orice","orice",facturi);
        HyperMarket hypermarket=new HyperMarket("orice","orice",facturi);
        
        Vector <MiniMarket> minimarketuri=new Vector <MiniMarket>();
        Vector <MediumMarket> mediummarketuri=new Vector <MediumMarket>();
        Vector <HyperMarket> hypermarketuri=new Vector <HyperMarket>();
        
        Vector <Magazin> magazine=new Vector <Magazin>();
        
        minimarketuri=minimarket.metoda(".\\src\\tema\\facturi.txt");
        mediummarketuri=mediummarket.metoda(".\\src\\tema\\facturi.txt");
        hypermarketuri=hypermarket.metoda(".\\src\\tema\\facturi.txt");
        
        //System.out.println(minimarketuri);
        //System.out.println(mediummarketuri);
        //System.out.println(hypermarketuri);
        
        //Magazin magazin=null;
        
        for(int i=0;i<minimarketuri.size();i++)
        {
            magazine.add(minimarketuri.get(i).creeazaMagazin(minimarketuri.get(i).getTip(),minimarketuri.get(i).getNume(),minimarketuri.get(i).getFacturi()));
        }
        
        for(int j=0;j<mediummarketuri.size();j++)
        {
            magazine.add(mediummarketuri.get(j).creeazaMagazin(mediummarketuri.get(j).getTip(),mediummarketuri.get(j).getNume(),mediummarketuri.get(j).getFacturi()));
        }
        
        for(int z=0;z<hypermarketuri.size();z++)
        {
            magazine.add(hypermarketuri.get(z).creeazaMagazin(hypermarketuri.get(z).getTip(),hypermarketuri.get(z).getNume(),hypermarketuri.get(z).getFacturi()));
        }
        
        return magazine;
    }
    
    /*public static void main(String args[]) throws IOException
    {   
        Vector <ProdusComandat> produsecomandate=new Vector <ProdusComandat>();
        produsecomandate.add(new ProdusComandat());
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi.add(new Factura("orice",produsecomandate));
        
        Magazin magazin=new MiniMarket("orice","orice",facturi);
        
        Vector <Magazin> magazine=new Vector <Magazin>();
        
        magazine=magazin.metodaMagazine("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt");
        //System.out.println(magazin.metodaMagazine("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt"));
        
        for(int z=0;z<magazine.size();z++)
        {
            System.out.println(magazine.get(z).getTotalFaraTaxe()+" "+magazine.get(z).getTotalCuTaxe()+" "+magazine.get(z).getTotalCuTaxeScutite());
        }
        
        System.out.println("*******");
        
        ArrayList <String> tari=new ArrayList <String>();
        RandomAccessFile file=new RandomAccessFile("C:\\Users\\mihai\\Desktop\\POO\\produse.txt","r");
        String line,taraOrig;
        
        line=file.readLine();
        StringTokenizer st=new StringTokenizer(line," ");
        st.nextToken();
        st.nextToken();
        while(st.hasMoreTokens())
        {
            taraOrig=st.nextToken();
            tari.add(taraOrig);
        }
        
        for(int i=0;i<magazine.size();i++)
        {
            for(int j=0;j<tari.size();j++)
            {
                System.out.println(tari.get(j)+" "+magazine.get(i).getTotalTaraFaraTaxe(tari.get(j))+" "+magazine.get(i).getTotalTaraCuTaxe(tari.get(j))+" "+magazine.get(i).getTotalTaraCuTaxeScutite(tari.get(j)));
            }
            System.out.println("*******");
        }
        
        //System.out.println(magazine);
    }*/
}