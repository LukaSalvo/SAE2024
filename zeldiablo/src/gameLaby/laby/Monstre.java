package gameLaby.laby;

/**
 * Classe représentant un monstre du jeu, héritant de la classe Personnage.
 */
public class Monstre extends Personnage {

    /**
     * Constantes représentant les points de vie du monstre.
     */
    public static final int PV_MONSTRE_ENTIER = 10;
    public static final int PV_MONSTRE_DEMI = PV_MONSTRE_ENTIER / 2;
    public static final int PV_MONSTRE_MORT = 0;

    /**
     * Constructeur de la classe Monstre.
     *
     * @param dx Coordonnée x de la position initiale du monstre.
     * @param dy Coordonnée y de la position initiale du monstre.
     */
    public Monstre(int dx, int dy) {
        super(dx, dy, PV_MONSTRE_ENTIER);
        this.setTypeDeplacement(new DeplacementIntelligent());
    }



    /**
     * Vérifie si le monstre peut bouger vers une position donnée.
     *
     * @param l  Le labyrinthe dans lequel se déplace le monstre.
     * @param dx Coordonnée x de la position vers laquelle le monstre souhaite se déplacer.
     * @param dy Coordonnée y de la position vers laquelle le monstre souhaite se déplacer.
     * @return true si le monstre peut bouger vers la position donnée, false sinon.
     */
    @Override
    public boolean peutBouger(Labyrinthe l, int dx, int dy) {
        return l.estDansLimiteLaby(dx, dy) && !l.getMur(dx,dy) && !l.personnagesPresent(dx, dy) && !estMort();
    }


    /**
     * Applique les répercussions du déplacement du monstre dans le labyrinthe.
     *
     * @param labyrinthe Le labyrinthe dans lequel se déplace le monstre.
     */
    @Override
    public void appliquerRepercussion(Labyrinthe labyrinthe) {
        labyrinthe.estSurCasePiege(this.getCoordonnees(),this);
        if (labyrinthe.getPj().estAutour(this)) {
            attaquer(labyrinthe.getPj(), new AttaqueAlentour());
        }
    }
}