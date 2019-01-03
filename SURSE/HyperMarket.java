package tema;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class HyperMarket extends Magazin
{
    String Tip;
    
    public HyperMarket(String Tip,String nume,Vector <Factura> facturi)
    {
        super(nume,facturi);
        this.Tip=Tip;
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
    
    public HyperMarket()
    {
        this("orice","orice",new Vector <Factura>());
    }
    
    @Override
    public double calculScutiriTaxe()throws IOException
    {
        int ok=0;
        
        Vector <Factura> facturi=new Vector <Factura>();
        facturi=this.facturi;
        
        for(int i=0;i<facturi.size();i++)
        {
            if(facturi.get(i).getTotalCuTaxe()>this.getTotalCuTaxe()*10/100)
            {
                ok=1;
            }
        }
        
        if(ok==1)
        {
            return 0.01;
        }
        else return 0.0;
    }
    
    public String toString()
    {
         return "\nTip Magazin: "+this.Tip+super.toString();
    }
    
    public Vector<HyperMarket> metoda(String in) throws IOException
    {
        Vector<HyperMarket> hypermarketuri=new Vector<HyperMarket>();
        
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
                if(tip.equals("HyperMarket"))
                {
                    hypermarketuri.add(new HyperMarket(tip,nume,facturimagazin));
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
        
        if(tip.equals("HyperMarket"))
        {
            hypermarketuri.add(new HyperMarket(tip,nume,facturimagazin));
        }
        //System.out.println(facturimagazin);
        
        return hypermarketuri;
    }
    
    /*public static void main(String args[]) throws IOException
    {
        HyperMarket hypermarket=new HyperMarket("orice","orice",new Vector<Factura>());
        //Factura factura1=new Factura("oricare",new Vector <ProdusComandat>());
        //Vector <Factura> facturi=new Vector <Factura>();
        
        System.out.println(hypermarket.metoda("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt"));
        
        //facturi=factura1.metoda("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt");
        //minimarket=new MiniMarket("MiniMarket","orice",facturi);
        //System.out.println(minimarket);
    }*/
}
