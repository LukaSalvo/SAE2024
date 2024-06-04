package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Monstre{

    /**
     * position du personnage
     */
    int x, y;
    int pv;

    private static final int POINTDEVIEMONSTREENTIER = 75;
    public static final int POINTDEVIEMONSTREDEMI = 50;
    public static final int POINTDEVIEMONSTREQUART = 25;
    public static final int POINTDEVIEMONSTREDEAD = 100;


    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Monstre(int dx, int dy) {
        this.x=dx;
        this.y=dy;
        this.pv = POINTDEVIEMONSTREENTIER;
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
}
