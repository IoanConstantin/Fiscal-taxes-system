----------------------------------------------------------------------------- POO - TEMA -----------------------------------------------------------------------------
======================================================================================================================================================================

 -> Nume: Constantin Ioan
    
 -> Grupa: 321CC

 -> Gradul de dificultate: 8,5/10

 -> Timpul alocat: 30-40 de ore

 -> Modul de implementare
======================================================================================================================================================================

 -> Clasa Produs
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 - In afara scheletului propus si a constructorilor am adaugat o metoda care returneaza un ArrayList <Produs> care contine toate obiectele de tip Produs obtinute 
   in urma parsarii fisierului produse.txt


 -> Clasa ProdusComandat
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 - In afara scheletului propus si a constructorilor am adaugat o metoda care returneaza un ArrayList <ProdusComandat> care contine toate obiectele de tip 

   ProdusComandat. Aceasta apeleaza o alta metoda care face legatura dintre fisierele produse.txt, facturi.txt si taxe.txt asa cum este specficat in enunt. 

   Aceasta metoda returneaza un Map<String,HashMap> care reprezinta dictionarul de perechi (Tara, (Categorie, Taxa)), asadar aici taxa este preluata din fisierul 

   taxe.txt conform categoriei acelui produs gasita in fisierul produse.txt.


 -> Interfata IMagazin
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 - Identica cu cea din enunt, in plus am adaugat metoda public double getTotalCategorieCuTaxe(String) pentru a fi apelata din metoda calculScutiriTaxe() din 
   
   clasa MediumMarket.


 -> Clasa abstracta Magazin implementeaza IMagazin
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 - Am implementat toate metodele non-abstracte ale interfetei IMagazin si o metoda care, folosind Factory Pattern, returneaza un obiect de tip Magazin, folosind 

   constructori din MiniMarket, MediumMarket sau HyperMarket in functie de tipul fiecarui magazin. O alta metoda adaugata este cea care returneaza un Vector <Magazin>

   care contine toate obiectele de tip Magazin obtinute in urma parsarii fisierului facturi.txt.


 -> Clasa MiniMarket/MediumMarket/HyperMarket extinde Magazin
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 - Am implementat metoda calculScutiriTaxe() pentru fiecare caz conform criteriilor specificate in enunt si am adaugat o metoda care returneaza un 

   Vector<MiniMarket>/Vector<MediumMarket>/Vector<HyperMarket> care contine toate obiectele de tip MiniMarket/MediumMarket/HyperMarket.


 -> Clasa Factura
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 - Am implementat in plus fata de schelet o metoda care returneaza un Vector <Factura> care contine toate obiectele de tip Factura obtinute in urma parsarii 

   fisierului facturi.txt


 -> Clasa Gestiune
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 - Am implementat clasa folosind Singleton Pattern pentru a crea un singur obiect o data. Am adaugat o metoda care returneaza un Map<String,HashMap> 

   care reprezinta dictionarul de perechi (Tara, (Categorie, Taxa)) care apeleaza metoda din ProdusComandat in care se facea legatura dintre fisiere si returna acelasi

   tip de dictionar de perechi. Am facut getteri si setteri pentru fiecare data a clasei. Am realizat 2 clase care implementeaza interfata Comparator 

   pentru a ordona magazinele in functie de getTotalFaraTaxe si facturile in functie de getTotalCuTaxe(). Aceasta clasa contine metoda main, care este utilizata 

   pentru crearea si scrierea in fisierul out.txt conform cerintelor primului task.