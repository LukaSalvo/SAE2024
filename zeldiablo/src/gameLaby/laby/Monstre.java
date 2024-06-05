package gameLaby.laby;

public class Monstre extends Personnage {
    public static final int PV_MONSTRE_ENTIER = 2;
    public static final int Pv_MONSTRE_DEMI = 1;
    public static final int PV_MONSTRE_MORT = 0;

    public Monstre(int dx, int dy) {
        super(dx, dy, PV_MONSTRE_ENTIER);
    }


    public boolean estMort(){
        return this.pv <= PV_MONSTRE_MORT;
    }
}
