package gameLaby.laby;

/**
 * Classe perso qui herite de la classe Personnage, representant le personnage du jeu
 */
public class Perso extends Personnage {
    /**
     * Constructeur de la classe Perso
     * @param dx position x
     * @param dy position y
     */
    public Perso(int dx, int dy) {
        super(dx, dy, PV_ENTIER);
    }
}
