package tema;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ProdusComandat 
{
    private Produs produs;
    private double taxa;
    private int cantitate;
    
    public ProdusComandat(Produs produs, double taxa, int cantitate)
    {
    	this.produs=produs;
    	this.taxa=taxa;
    	this.cantitate=cantitate;
    }
    
    public ProdusComandat()
    {
    	this(new Produs("kiwi","fructe","TR",30),5,50);
    }
    
    public String toString()
    {
    	return this.produs+", Taxa: "+this.taxa+", Cantitate: "+this.cantitate+"\n";
    }
    
    public void setProdus(Produs prod)
    {
    	this.produs=prod;
    }
    
    public Produs getProdus()
    {
    	return produs;
    }
    
    public void setTaxa(double ta)
    {
    	this.taxa=ta;
    }
    
    public double getTaxa()
    {
    	return taxa;
    }
    
    public void setCantitate(int cantit)
    {
    	this.cantitate=cantit;
    }
    
    public int getCantitate()
    {
    	return cantitate;
    }
    
    public Map<String,HashMap> metoda1(String in) throws IOException
    {   
        HashMap <String,Double> dictionarius=new HashMap<String,Double>();
        
        Map <String,HashMap> taxe=new HashMap<String,HashMap>();
            
        RandomAccessFile file=new RandomAccessFile(in,"r");
        RandomAccessFile file1=new RandomAccessFile(in,"r");
        String line,line1,line2,aux=null,cat;
        ArrayList <String> tari=new ArrayList <String>();
        
        line=file.readLine();
        StringTokenizer st=new StringTokenizer(line," ");
        st.nextToken();
        while(st.hasMoreTokens())
        {
            aux=st.nextToken();
            tari.add(aux);
        }
        /*for(int i=0;i<tari.size();i++)
        {
            System.out.println(tari.get(i));
        }*/
        
        for(int j=0;j<tari.size();j++)
        {
            file1=new RandomAccessFile(in,"r");
            line1=file1.readLine();
            //System.out.println(line1);
            
            dictionarius=new HashMap<String,Double>();
            
            while((line2=file1.readLine())!=null)
            {
                //System.out.println(line2);
                
                StringTokenizer st2=new StringTokenizer(line2," ");
                cat=st2.nextToken();
                //System.out.println(cat);
                
                for(int z=0;z<=j;z++)
                {
                    aux=st2.nextToken();
                }
                //System.out.println(cat+" "+aux);
                
                dictionarius.put(cat,Double.parseDouble(aux));
                
            }
            //System.out.println("*******");
            
            //System.out.println(dictionarius);
            
            taxe.put(tari.get(j),dictionarius);
        }
        
        return taxe;
    }
    
    public ArrayList<ProdusComandat> metoda(String in) throws IOException
    {
        ArrayList <ProdusComandat> produsecomandate=new ArrayList <ProdusComandat>();
        
        int ok,contor;
        double pretul=0,taxa1=0;
        String aux,auxiliar,aux1=null,aux2=null,aux3=null,categoria=null;
        RandomAccessFile file=new RandomAccessFile(in,"r");
        String line,line1;
        
        ProdusComandat produscomandat1=new ProdusComandat();
        Map <String,HashMap> taxe=new HashMap<String,HashMap>();
        
        taxe=produscomandat1.metoda1(".\\src\\tema\\taxe.txt");
        
        /*dictionar=taxe.get("SP");
        
        double taxa2=dictionar.get("Carte");
        
        System.out.println(taxa2);*/
        
        while((line=file.readLine())!=null)
        {
            StringTokenizer st=new StringTokenizer(line," :");
            ok=0;
            while(st.hasMoreTokens())
            {  
                aux=st.nextToken();
                ok++;
                if(ok==1&&aux.equals("DenumireProdus"))
                {
                    //line1=file.readLine();
                    while((line1=file.readLine())!=null&&(!(line1.isEmpty())))
                    {
                        StringTokenizer st1=new StringTokenizer(line1," :");
                        contor=0;
                        while(st1.hasMoreTokens())
                        {
                            auxiliar=st1.nextToken();
                            contor++;
                            if(contor==1)
                            {
                                aux1=auxiliar;
                            }
                            if(contor==2)
                            {
                                aux2=auxiliar;
                            }
                            if(contor==3)
                            {
                                aux3=auxiliar;
                            }
                        }
                        
                        Produs produs1=new Produs();
                        ArrayList <Produs> produsele=new ArrayList <Produs>();
                        
                        produsele=produs1.metoda(".\\src\\tema\\produse.txt");
                        
                        for(int i=0;i<produsele.size();i++)
                        {
                            if(produsele.get(i).getDenumire().equals(aux1)&&produsele.get(i).getTaraOrigine().equals(aux2))
                            {
                                categoria=produsele.get(i).getCategorie();
                                pretul=produsele.get(i).getPret();
                            }
                        }
                        
                        HashMap <String,Double> dictionar=new HashMap<String,Double>();
                        dictionar=taxe.get(aux2);
                        taxa1=dictionar.get(categoria);
                        
                        produsecomandate.add(new ProdusComandat(new Produs(aux1,categoria,aux2,pretul),taxa1,Integer.parseInt(aux3)));
                    }    
                }  
            }  
        }
        return produsecomandate;
    }
    
    /*public static void main(String args[]) throws IOException
    {
        ProdusComandat produscomandat1=new ProdusComandat();
        System.out.println(produscomandat1.metoda("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt"));
    */    
        /*HashMap <String,Double> dictionar=new HashMap<String,Double>();
        
        Map <String,HashMap> taxe=new HashMap<String,HashMap>();
        
        taxe=produscomandat1.metoda1("C:\\Users\\mihai\\Desktop\\POO\\taxe.txt");
        
        System.out.println(taxe);*/
        
        //System.out.println(produscomandat1.metoda1("C:\\Users\\mihai\\Desktop\\POO\\taxe.txt"));
        
        /*Produs produs1=new Produs();
        ArrayList <ProdusComandat> produselecomandate=new ArrayList <ProdusComandat>();
        
        produselecomandate=produscomandat1.metoda("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt");
        
        for(int j=0;j<produselecomandate.size();j++)
        {
            System.out.println(produselecomandate.get(j));
        }
        System.out.println(produselecomandate.size());
    }*/
}