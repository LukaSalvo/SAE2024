package gameLaby.laby;

/**
 * Classe Monstre qui herite de la classe Personnage, representant le monstre du jeu

 */
public class Monstre extends Personnage {
    /**
     * Constantes: PV_MONSTRE_ENTIER, PV_MONSTRE_DEMI, PV_MONSTRE_MORT
     */
    public static final int PV_MONSTRE_ENTIER = 2;
    public static final int Pv_MONSTRE_DEMI = 1;
    public static final int PV_MONSTRE_MORT = 0;

    /**
     * Constructeur de la classe Monstre
     * @param dx parametres de position x
     * @param dy parametres de position y
     */
    public Monstre(int dx, int dy) {
        super(dx, dy, PV_MONSTRE_ENTIER);
    }

    /**
     * Methode pour voir si le monstre est mort
     * @return true si le monstre est mort, false sinon
     */
    public boolean estMort(){
        return this.pv <= PV_MONSTRE_MORT;
    }
}
