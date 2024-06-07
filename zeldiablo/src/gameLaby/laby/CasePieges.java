package gameLaby.laby;

/**
 * Classe qui gère les cases pièges, ces cases infligent des dégâts au joueur.
 */
public class CasePieges extends Coordonnees {
    /**
     * Attribut qui représente les dégâts infligés par les pièges.
     */
    public static final int degats = 5;
    private boolean etrePasserDessus;

    /**
     * Constructeur qui construit un objet avec les coordonnées en paramètre.
     *
     * @param x coordonnée x de la case piège
     * @param y coordonnée y de la case piège
     */
    public CasePieges(int x, int y) {
        super(x, y);
        this.etrePasserDessus = false;
    }

    /**
     * Getter des dégâts infligés par les pièges.
     *
     * @return les dégâts infligés par les pièges
     */
    public static int getDegats() {
        return degats;
    }

    /**
     * Getter pour vérifier si la case piège a été traversée.
     *
     * @return true si la case piège a été traversée, sinon false
     */
    public boolean getEtrePasserDessus() {
        return this.etrePasserDessus;
    }

    /**
     * Setter pour indiquer si la case piège a été traversée ou non.
     *
     * @param n true si la case piège a été traversée, sinon false
     */
    public void setEtrePasserDessus(boolean n) {
        this.etrePasserDessus = n;
    }
}
