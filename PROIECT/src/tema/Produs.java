package tema;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Produs 
{
    private String denumire;
    private String categorie;
    private String taraOrigine;
    private double pret;
    
    public Produs(String denumire, String categorie, String taraOrigine, double pret)
    {
    	this.denumire=denumire;
    	this.categorie=categorie;
    	this.taraOrigine=taraOrigine;
    	this.pret=pret;
    }
    
    public Produs()
    {
    	this("miere","dulciuri","EN",50);
    }
    
    public String toString()
    {
    	return "Denumire: "+this.denumire+", Categorie: "+this.categorie+", Tara de origine: "+this.taraOrigine+", Pret:"+this.pret;
    }
    
    public void setDenumire(String denum)
    {
    	this.denumire=denum;
    }
    
    public String getDenumire()
    {
    	return denumire;
    }
    
    public void setCategorie(String categor)
    {
    	this.categorie=categor;
    }
    
    public String getCategorie()
    {
    	return categorie;
    }
    
    public void setTaraOrigine(String taraOrig)
    {
    	this.taraOrigine=taraOrig;
    }
    
    public String getTaraOrigine()
    {
    	return taraOrigine;
    }
    
    public void setPret(double pr)
    {
    	this.pret=pr;
    }
    
    public double getPret()
    {
    	return pret;
    }
    
    public ArrayList<Produs> metoda(String in) throws IOException
    {
        ArrayList <Produs> produse=new ArrayList <Produs>();
        ArrayList <String> tari=new ArrayList <String>();
        
        String den,cat,aux;
        RandomAccessFile file=new RandomAccessFile(in,"r");
        String line;
        
        line=file.readLine();
        StringTokenizer st=new StringTokenizer(line," ");
        st.nextToken();
        st.nextToken();
        while(st.hasMoreTokens())
        {
            aux=st.nextToken();
            tari.add(aux);
        }
        
        while((line=file.readLine())!=null)
        {
            st=new StringTokenizer(line," ");
            den=st.nextToken();
            cat=st.nextToken();
            
            int ok=0;
            while(st.hasMoreTokens())
            {
                produse.add(new Produs(den,cat,tari.get(ok),Double.parseDouble(st.nextToken())));
                ok++;
            }
        }
        
        return produse;
    }
    
    /*public static void main(String args[]) throws IOException
    {
        int i=0;
        Produs produs1=new Produs();
        System.out.println(produs1.metoda("C:\\Users\\mihai\\Desktop\\POO\\produse.txt"));
        
        ArrayList <Produs> produsele=new ArrayList <Produs>();
        produsele=produs1.metoda("C:\\Users\\mihai\\Desktop\\POO\\produse.txt");
        for(i=0;i<produsele.size();i++)
        {
            System.out.println(produsele.get(i));
        }
    }*/
}