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



    /**
     * Constructeur de la classe Perso
     *
     * @param dx position x
     * @param dy position y
     */
    public Perso(int dx, int dy) {
        super(dx, dy, PV_ENTIER);
        this.setTypeDeplacement(new DeplacementClavier());
        this.possedeAmulette = false;
    }

    /**
     * Methode pour verifier si le personnage peut bouger vers une position donnee
     *
     * @param l  Le labyrinthe dans lequel se deplace le personnage
     * @param dx Coordonnee x de la position vers laquelle le personnage souhaite se deplacer
     * @param dy Coordonnee y de la position vers laquelle le personnage souhaite se deplacer
     * @return true si le personnage peut bouger vers la position donnee, false sinon
     */
    @Override
    public boolean peutBouger(Labyrinthe l, int dx, int dy) {
        return l.estDansLimiteLaby(dx, dy) && !l.getMur(dx,dy) && !l.personnagesPresent(dx, dy) && !estMort();

    }


    /**
     * Methode pour appliquer les repercussions du deplacement du personnage dans le labyrinthe
     *
     * @param labyrinthe Le labyrinthe dans lequel se deplace le personnage
     */
    @Override
    public void appliquerRepercussion(Labyrinthe labyrinthe) {
        labyrinthe.estSurCasePiege(this.getCoordonnees(),this);
        Amulette amu = labyrinthe.getAmulette();
        if(!this.possedeAmulette){
            boolean res = recupererAmulette(amu);
        } else {
            labyrinthe.setAmulette(null);
        }
    }

    /**
     * Methode pour recuperer l'amulette
     *
     * @param amu l'amulette a recuperer
     * @return l'amulette
     */
    public boolean recupererAmulette(Amulette amu){
        if(!possedeAmulette && etreSurMemeCase(amu.getX(),amu.getY())){
            possedeAmulette=true;
        }
        return possedeAmulette;
    }

    /**
     * Geutter de l'attribut possedeAmulette
     *
     * @return true ou false
     */
    public boolean getPossedeAmulette() {
        return possedeAmulette;
    }


    /**
     * Setter de l'attribut possedeAmulette
     *
     * @param possede true ou false
     */
    public void setPossedeAmulette(boolean possede) {
        this.possedeAmulette = possede;
    }



}
