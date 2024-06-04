package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso {

    /**
     * position du personnage
     */
    int x, y;
    int pv;

    public static final int POINTDEVIEPERSOENTIER = 10;
    public static final int POINTDEVIEPERSO3QUART = 7;
    public static final int POINTDEVIEPERSODEMI = 5;
    public static final int POINTDEVIEPERSO1QUART = 2;
    public static final int POINTDEVIEPERSODEAD = 0;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy ) {
        this.x=dx;
        this.y=dy;
        this.pv = POINTDEVIEPERSOENTIER;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    // ############################################
    // GETTER
    // ############################################

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    public int getPv(){
        return this.pv;
    }

    public void perdrePv(int degats){
        this.pv -= degats;
    }
}
