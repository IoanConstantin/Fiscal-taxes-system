package tema;

import java.io.IOException;

public interface IMagazin 
{
    public double getTotalFaraTaxe();
    public double getTotalCuTaxe();
    public double getTotalCuTaxeScutite() throws IOException;
    public double getTotalTaraFaraTaxe(String taraOrigine);
    public double getTotalTaraCuTaxe(String taraOrigine);
    public double getTotalCategorieCuTaxe(String taraOrigine);
    public double getTotalTaraCuTaxeScutite(String taraOrigine) throws IOException;
    abstract public double calculScutiriTaxe() throws IOException;
}
