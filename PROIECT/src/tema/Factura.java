package tema;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class Factura 
{
    String denumire;//prima linie a facturii(citita din fisier)
    Vector <ProdusComandat> produsecomandate=new Vector<ProdusComandat>();
    
    public Factura(String denumire)
    {
        this.denumire=denumire;
        produsecomandate=new Vector<ProdusComandat>();
    }
    
    public Factura()
    {
        this("Orice",new Vector<ProdusComandat>());
    }
    
    public Factura(String denumire,Vector <ProdusComandat> produsecomandate)
    {
        this.denumire=denumire;
        this.produsecomandate=produsecomandate;
    }
    
    public String getDenumire()
    {
        return denumire;
    }
    
    public Vector<ProdusComandat> getProduseComandate()
    {
        return produsecomandate;
    }
    
    public double getTotalFaraTaxe()
    {
        double total=0.0;
        
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        produsecom=produsecomandate;
        
        for(int i=0;i<produsecom.size();i++)
        {
            total=total+produsecom.get(i).getProdus().getPret()*produsecom.get(i).getCantitate();
        }
        
        //if(total==Math.floor(total)) return Math.floor(total);
        return Math.round(total*10000.0)/10000.0;
    }
    
    public double getTotalCuTaxe()
    {
        double total=0.0;
        
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        produsecom=produsecomandate;
        
        for(int i=0;i<produsecom.size();i++)
        {
            total=total+produsecom.get(i).getProdus().getPret()*((produsecom.get(i).getTaxa()+100)/100)*produsecom.get(i).getCantitate();
        }
        
        //if(total==Math.floor(total)) return Math.floor(total);
        return Math.round(total*10000.0)/10000.0;
    }
    
    public double getTaxe()
    {
        double total=0.0;
        
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        produsecom=produsecomandate;
        
        for(int i=0;i<produsecom.size();i++)
        {
            total=total+produsecom.get(i).getProdus().getPret()*(produsecom.get(i).getTaxa()/100)*produsecom.get(i).getCantitate();
        }
        
        //if(total==Math.floor(total)) return Math.floor(total);
        return Math.round(total*10000.0)/10000.0;
        
        //return Math.round((this.getTotalCuTaxe()-this.getTotalFaraTaxe())*1000.0)/1000.0;
    }
    
    public double getTotalTaraFaraTaxe(String taraOrigine)
    {
        double total=0.0;
        
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        produsecom=produsecomandate;
        
        for(int i=0;i<produsecom.size();i++)
        {
            if(taraOrigine.equals(produsecom.get(i).getProdus().getTaraOrigine()))
            {
                total=total+produsecom.get(i).getProdus().getPret()*produsecom.get(i).getCantitate();
            }
        }
        
        //if(total==Math.floor(total)) return Math.floor(total);
        return Math.round(total*10000.0)/10000.0;      
    }
    
    public double getTotalTaraCuTaxe(String taraOrigine)
    {
        double total=0.0;
        
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        produsecom=produsecomandate;
        
        for(int i=0;i<produsecom.size();i++)
        {
            if(taraOrigine.equals(produsecom.get(i).getProdus().getTaraOrigine()))
            {
                total=total+produsecom.get(i).getProdus().getPret()*((produsecom.get(i).getTaxa()+100)/100)*produsecom.get(i).getCantitate();
            }
        }
        
        //if(total==Math.floor(total)) return Math.floor(total);
        return Math.round(total*10000.0)/10000.0;
    }
    
    public double getTaxeTara(String taraOrigine)
    {
        double total=0.0;
        
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        produsecom=produsecomandate;
        
        for(int i=0;i<produsecom.size();i++)
        {
            if(taraOrigine.equals(produsecom.get(i).getProdus().getTaraOrigine()))
            {
                total=total+produsecom.get(i).getProdus().getPret()*(produsecom.get(i).getTaxa()/100)*produsecom.get(i).getCantitate();
            }
        }
        
        //if(total==Math.floor(total)) return Math.floor(total);
        return Math.round(total*10000.0)/10000.0;
        
        //return Math.round((this.getTotalTaraCuTaxe()-this.getTotalTaraFaraTaxe())*1000.0)/1000.0;
    }
    
    public String toString()
    {
        return "\nDenumire Factura: "+this.denumire+"\n"+"Produse Comandate:\n"+this.produsecomandate;
    }
    
    public double getTotalCategorieCuTaxe(String categorie)
    {
        double total=0.0;
        
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        produsecom=produsecomandate;
        
        for(int i=0;i<produsecom.size();i++)
        {
            if(categorie.equals(produsecom.get(i).getProdus().getCategorie()))
            {
                total=total+produsecom.get(i).getProdus().getPret()*((produsecom.get(i).getTaxa()+100)/100)*produsecom.get(i).getCantitate();
            }
        }
        
        //if(total==Math.floor(total)) return Math.floor(total);
        return Math.round(total*10000.0)/10000.0;
    }
    
    public Vector<Factura> metoda(String in) throws IOException
    {
        Vector<Factura> facturi=new Vector<Factura>();
        Factura factura=new Factura();
        
        Vector <ProdusComandat> produsecomandate=new Vector <ProdusComandat>();
        
        ProdusComandat produscomandat1=new ProdusComandat();
        Map <String,HashMap> taxe=new HashMap<String,HashMap>();
        taxe=produscomandat1.metoda1(".\\src\\tema\\taxe.txt");
        
        RandomAccessFile file=new RandomAccessFile(in,"r");
        String line,line1,line2,auxline=null,auxiliar,aux1=null,aux2=null,aux3=null,categoria=null;
        int contor;
        double pretul=0,taxa1=0;
        
        while((line=file.readLine())!=null)
        {
            if(line.contains(" ")||line.contains(":")||line.isEmpty())
            {
                
            }
            else 
            {
                //System.out.println("********"+line+"********");
                auxline=line;//facturi.add(new Factura(line1));
                line1=file.readLine();
                
                while((line2=file.readLine())!=null&&(!(line2.isEmpty())))
                {
                    //System.out.println(line2);
                    
                    StringTokenizer st=new StringTokenizer(line2," :");
                    contor=0;
                    while(st.hasMoreTokens())
                    {
                        auxiliar=st.nextToken();
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
                    
                    //System.out.println(aux1+" "+categoria+" "+aux2+" "+pretul+" "+aux3);
                    
                    HashMap <String,Double> dictionar=new HashMap<String,Double>();
                    
                    dictionar=taxe.get(aux2);
                    taxa1=dictionar.get(categoria);
                    
                    //System.out.println(aux1+" "+categoria+" "+aux2+" "+pretul+" "+taxa1+" "+aux3);
                    
                    produsecomandate.add(new ProdusComandat(new Produs(aux1,categoria,aux2,pretul),taxa1,Integer.parseInt(aux3)));
                    //System.out.println(produsecomandate);
                }
                
                factura=new Factura(auxline,produsecomandate);
                facturi.add(factura);
                
                produsecomandate=new Vector<ProdusComandat>();
                
                //System.out.println("*******Rand Liber*******");
            }
        }
        
        /*for(int q=0;q<produsecomandate.size();q++)
        {
            System.out.println(produsecomandate.get(q));
        }
        System.out.println(produsecomandate.size());
        
        System.out.println(auxline);*/
        
        return facturi;
    }
    
    /*public static void main(String args[]) throws IOException
    {
        String denumire="Oricare";
        Vector <ProdusComandat> produsecom=new Vector <ProdusComandat>();
        
        Factura factura1=new Factura(denumire,produsecom);
        Vector <Factura> facturi=new Vector <Factura>();
        
        facturi=factura1.metoda("C:\\Users\\mihai\\Desktop\\POO\\facturi.txt");
        
        System.out.println(facturi);
    */    
        /*for(int w=0;w<facturi.size();w++)
        {
            System.out.println(facturi.get(w));
        }*/
        
        /*System.out.println(facturi.size());
        
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
        
        for(int i=0;i<facturi.size();i++)
        {
            //System.out.println(facturi.get(i).getTotalFaraTaxe()+" "+facturi.get(i).getTotalCuTaxe()+" "+facturi.get(i).getTaxe());
            
            System.out.println(facturi.get(i).getDenumire());
            
            for(int j=0;j<tari.size();j++)
            {
                System.out.println(tari.get(j)+" "+facturi.get(i).getTotalTaraFaraTaxe(tari.get(j))+" "+facturi.get(i).getTotalTaraCuTaxe(tari.get(j))+" "+facturi.get(i).getTaxeTara(tari.get(j)));
            }
            
        }
        
    }*/
}