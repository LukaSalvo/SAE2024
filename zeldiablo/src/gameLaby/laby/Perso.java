package gameLaby.laby;

/**
 * Classe perso qui herite de la classe Personnage, representant le personnage du jeu
 */
public class Perso extends Personnage {

    /**
     * Constructeur de la classe Perso
     *
     * @param dx position x
     * @param dy position y
     */
    private boolean possedeAmulette;

    public Perso(int dx, int dy) {
        super(dx, dy, PV_ENTIER);
        this.setTypeDeplacement(new DeplacementClavier());
        this.possedeAmulette = false;
    }

    @Override
    public boolean peutBouger(Labyrinthe l, int dx, int dy) {
        return l.deplacementPossible(dx, dy);
    }

    public boolean getPossedeAmulette() {
        return possedeAmulette;
    }
    public void setPossedeAmulette(boolean possede) {
        this.possedeAmulette = possede;
    }


}
