package gameLaby.laby;

/**
 * Classe qui gere les cases pieges, ces cases infligent des degats au joueur
 */
public class CasePieges extends Cordonnees {
    /**
     * Attribut qui represente les degats infliges par les pieges
     */
    public static final int degats = 5;
    private boolean etrePasserDessus;

    /**
     * Constructeur qui construit un objet avec les cordonnes en parametre
     *
     * @param x
     * @param y
     */
    public CasePieges(int x, int y) {
        super(x, y);this.etrePasserDessus=false;
    }

    /**
     * Methode qui dit si on est sur la meme case que les parametres;
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean etreSurMemeCase(int x, int y) {
        return super.etreSurMemeCase(x, y);
    }

    /**
     * Getter des degats
     * @return
     */
    public static int getDegats(){
        return degats;
    }

    public boolean getEtrePasserDessus(){
        return this.etrePasserDessus;
    }

    public void setEtrePasserDessus(boolean n ){
        this.etrePasserDessus = n;
    }
}
