package gameLaby.laby;

/**
 * class qui sera extends par toutes les classes auront des coordonnées
 */
public class Cordonnees {
    /**
     * Attributs qui sont les coordonnées X et Y
     */
    int posX;
    int posY;

    /**
     * Constructeur qui construit un objet avec les cordonnes en parametre
     * @param x
     * @param y
     */
    public Cordonnees(int x , int y ){
        this.posX=x;
        this.posY=y;
    }

    /**
     * Getter de la position X
     * @return
     */
    public int getPosX(){
        return this.posX;
    }

    /**
     * Getter de la position Y
     * @return
     */
    public int getPosY(){
        return this.posY;
    }

    /**
     * Setter de la position X
     * @param x
     */
    public void setPosX(int x ){
        this.posX=x;
    }

    /**
     * Setter de la position Y
     * @param y
     */

    public void setPosY(int y ){
        this.posY = y;
    }

    /**
     * Methode qui dit si on est sur la meme case que les parametres;
     * @param x
     * @param y
     * @return
     */
    public boolean etreSurMemeCase(int x , int y ){
        return this.posX == x && this.posY == y ;
    }

}
