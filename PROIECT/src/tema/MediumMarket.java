package tema;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class MediumMarket extends Magazin
{
    String Tip;
    
    public MediumMarket(String Tip,String nume,Vector <Factura> facturi)
    {
        super(nume,facturi);
        this.Tip=Tip;
    }
    
    public MediumMarket()
    {
        this("orice","orice",new Vector <Factura>());
    }
    
    public String getTip()
    {
        return Tip;
    }
    
    public String getNume()
    {
        return super.getNume();
    }
    
    public Vector <Factura> getFacturi()
    {
        return super.getFacturi();
    }
    
    @Override
    public double calculScutiriTaxe()throws IOException
    {
        int ok=0;
        
        ArrayList <String> categorii=new ArrayList <String>();
        RandomAccessFile file=new RandomAccessFile(".\\src\\tema\\produse.txt","r");
        String line,aux,categorie;
        int contor=0,adauga=1;
        
        line=file.readLine();
        while((line=file.readLine())!=null)
        {
            StringTokenizer st=new StringTokenizer(line," ");
            aux=st.nextToken();
            categorie=st.nextToken();
            for(int i=0;i<contor;i++)
            {
                if(categorie.equals(categorii.get(i))) adauga=0;
            }
            if(adauga==1)
            {
                contor++;
                categorii.add(categorie);
            }
        }
        
        for(int z=0;z<categorii.size();z++)
        {
            if(this.getTotalCategorieCuTaxe(categorii.get(z))>this.getTotalCuTaxe()*50/100)
            {
                ok=1;
            }
        }
        
        if(ok==1)
        {
            return 0.05;
        }
        else return 0.0;
    }
    
    public String toString()
    {
         return "\nTip Magazin: "+this.Tip+super.toString();
    }
    
    public Vector<MediumMarket> metoda(String in) throws IOException
    {
        Vector<MediumMarket> mediummarketuri=new Vector<MediumMarket>();
        
        Factura factura1=new Factura("oricare",new Vector <ProdusComandat>());
        Vector <Factura> facturi=new Vector <Factura>();
        Vector <Factura> facturimagazin=new Vector<Factura>();
        
        RandomAccessFile file=new RandomAccessFile(in,"r");
        String line,tip=null,nume=null,auxiliar;
        int contor,contorfacturi=0;
        
        facturi=factura1.metoda(".\\src\\tema\\facturi.txt");
        
        while((line=file.readLine())!=null)
        {
          if(line.contains(":"))
          {
            //System.out.println(facturimagazin);
            if(tip!=null&&nume!=null)
            {
                if(tip.equals("MediumMarket"))
                {
                    mediummarketuri.add(new MediumMarket(tip,nume,facturimagazin));
                }
            }
            
            facturimagazin=new Vector<Factura>();
              
            StringTokenizer st=new StringTokenizer(line,":");
            contor=0;
            while(st.hasMoreTokens())
            {
                auxiliar=st.nextToken();
                contor++;
                if(contor==2)
                {
                    tip=auxiliar;
                }
                if(contor==3)
                {
                    nume=auxiliar;
                }
            }
            
            /*if(tip.equals("MiniMarket"))
            {
                System.out.println(tip+" "+nume);
            }*/
            
            //System.out.println(tip+" "+nume);
          }
          
          if(line.contains(" ")||line.contains(":")||line.isEmpty())
          {
              
          }
          else
          {
             //System.out.println(facturi.get(contorfacturi));
             facturimagazin.add(facturi.get(contorfacturi));
             contorfacturi++;
          }
          
        }
        
        if(tip.equals("MediumMarket"))
        {
            mediummarketuri.add(new MediumMarket(tip,nume,facturimagazin));
        }
        //System.out.println(facturimagazin);
        
        return mediummarketuri;
    }
    
    /*public static void main(String args[]) throws IOException
    {
        MediumMarket mediummarket=new MediumMarket("orice","orice",new Vector<Factura>());
        //Factura factura1=new Factura("oricare",new Vector <ProdusComandat>());
        //Vector <Factura> facturi=new Vector <Factura>();
        
        System.out.println(mediummarket.metoda("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt"));
        
        //facturi=factura1.metoda("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt");
        //minimarket=new MiniMarket("MiniMarket","orice",facturi);
        //System.out.println(minimarket);
    }*/
}
