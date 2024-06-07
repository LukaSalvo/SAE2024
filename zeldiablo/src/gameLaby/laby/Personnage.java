package gameLaby.laby;

/**
 * Classe abstraite pour les personnages
 */
public abstract class Personnage extends Coordonnees {
    /**
     * pv
     */
    protected int pv;
    private TypeDeplacement typeDeplacement;
    String action;
    /**
     * Constantes: PV_ENTIER, PV_3_QUART, PV_DEMI, PV_1_QUART, PV_MORT
     */
    public static final int PV_ENTIER = 10;
    public static final int PV_3_QUART = PV_ENTIER * 3 / 4;
    public static final int PV_DEMI = PV_ENTIER / 2;
    public static final int PV_1_QUART = PV_ENTIER / 4;
    public static final int PV_MORT = 0;

    /**
     * Constructeur de la classe Personnage
     *
     * @param dx position x
     * @param dy position y
     * @param pv points de vie
     */
    public Personnage(int dx, int dy, int pv) {
        super(dx, dy);
        this.pv = pv;
        ;
    }

    /**
     * Methode pour se deplacer
     *
     * @param dx position x
     * @param dy position y
     * @return retourne true si le deplacement est possible
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }


    /**
     * Guetteur des points de vie
     *
     * @return
     */
    public int getPv() {
        return this.pv;
    }

    /**
     * Methode pour perdre des points de vie
     *
     * @param degats degats subis
     */
    public void perdrePv(int degats) {
        if(!estMort()) {
            this.pv -= degats;
            if(this.pv < PV_MORT){
                this.pv = PV_MORT;
            }
        }
    }


    /**
     * Methode pour attaquer un personnage
     *
     * @param p       personnage attaqué
     * @param typeAttaque type d'attaque
     */
    public void attaquer(Personnage p, TypeAttaque typeAttaque) {
        if (!estMort()){
            typeAttaque.attaquer(this, p);
        }
    }


    /**
     * Vérifie si un autre personnage est autour du personnage principal.
     *
     * @param p Le personnage à vérifier.
     * @return true si le personnage p est adjacent, false sinon.
     */
    public boolean estAutour(Personnage p) {
        return (this.x == p.getX() + 1 && this.y == p.getY()) ||
                (this.x == p.getX() - 1 && this.y == p.getY()) ||
                (this.y == p.getY() + 1 && this.x == p.getX()) ||
                (this.y == p.getY() - 1 && this.x == p.getX());
    }


    /**
     * Renvoie le type de déplacement du personnage.
     *
     * @return Le type de déplacement du personnage.
     */
    public TypeDeplacement getType() {
        return this.typeDeplacement;
    }

    /**
     * Définit le type de déplacement du personnage.
     *
     * @param type Le type de déplacement à définir.
     */
    public void setTypeDeplacement(TypeDeplacement type) {
        this.typeDeplacement = type;
    }

    /**
     * Vérifie si le personnage peut bouger vers une position donnée dans le labyrinthe.
     *
     * @param l  Le labyrinthe dans lequel se déplace le personnage.
     * @param dx Coordonnée x de la position vers laquelle le personnage veut bouger.
     * @param dy Coordonnée y de la position vers laquelle le personnage veut bouger.
     * @return true si le personnage peut bouger vers cette position, sinon false.
     */
    public abstract boolean peutBouger(Labyrinthe l, int dx, int dy);

    /**
     * Définit l'action en cours du personnage.
     *
     * @param action L'action en cours à définir.
     */
    public void action(String action) {
        this.action = action;
    }

    /**
     * Renvoie l'action en cours du personnage.
     *
     * @return L'action en cours du personnage.
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Vérifie si le monstre est mort.
     *
     * @return true si le monstre est mort, false sinon.
     */
    public boolean estMort() {
        return this.pv <= PV_MORT;
    }

}
