package gameLaby.laby;

import static gameLaby.laby.Labyrinthe.*;

/**
 * Classe qui sera étendue par toutes les classes ayant des coordonnées.
 */
public class Coordonnees {
    /**
     * Attributs qui représentent les coordonnées X et Y.
     */
    int x;
    int y;

    /**
     * Constructeur qui crée un objet avec les coordonnées spécifiées.
     *
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Déplace l'objet aux coordonnées spécifiées.
     *
     * @param x nouvelle coordonnée x
     * @param y nouvelle coordonnée y
     */
    public void deplacer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Déplace l'objet aux coordonnées spécifiées.
     *
     * @param coordonnee tableau contenant les nouvelles coordonnées [x, y]
     */
    public void deplacer(int[] coordonnee) {
        this.x = coordonnee[0];
        this.y = coordonnee[1];
    }

    /**
     * Getter des coordonnées.
     *
     * @return un tableau contenant les coordonnées [x, y]
     */
    public int[] getCoordonnees() {
        return new int[]{this.x, this.y};
    }

    /**
     * Getter de la position X.
     *
     * @return la coordonnée x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter de la position Y.
     *
     * @return la coordonnée y
     */
    public int getY() {
        return this.y;
    }

    public int[] getPos(){
        return new int[]{this.x, this.y};
    }

    /**
     * Setter de la position X.
     *
     * @param x nouvelle coordonnée x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter de la position Y.
     *
     * @param y nouvelle coordonnée y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Méthode qui indique si l'objet est sur la même case que les coordonnées spécifiées.
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si l'objet est sur la même case, sinon false
     */
    public boolean etreSurMemeCase(int x, int y) {
        return this.x == x && this.y == y;
    }

    /**
     * Retourne les coordonnées de la case suivante selon une action donnée.
     *
     * @param action action effectuée
     * @return les coordonnées de la case suivante [x, y]
     */
    public int[] getSuivant(String action) {
        int x = this.x;
        int y = this.y;
        switch (action) {
            case HAUT:
                y--;
                break;
            case BAS:
                y++;
                break;
            case DROITE:
                x++;
                break;
            case GAUCHE:
                x--;
                break;
            default:
                throw new IllegalArgumentException("Action inconnue : " + action);
        }
        return new int[]{x, y};
    }

    /**
     * Retourne les coordonnées de la case suivante selon une action donnée.
     *
     * @param x      coordonnée x de départ
     * @param y      coordonnée y de départ
     * @param action action effectuée
     * @return les coordonnées de la case suivante [x, y]
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                y--;
                break;
            case BAS:
                y++;
                break;
            case DROITE:
                x++;
                break;
            case GAUCHE:
                x--;
                break;
            default:
                throw new IllegalArgumentException("Action inconnue : " + action);
        }
        return new int[]{x, y};
    }
}