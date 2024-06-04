package gameLaby.laby;

public class CasePieges extends Cordonnées {

    public static final int degats = 5;


    /**
     * Constructeur qui construit un objet avec les cordonnes en parametre
     *
     * @param x
     * @param y
     */
    public CasePieges(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean etreSurMemeCase(int x, int y) {
        return super.etreSurMemeCase(x, y);
    }
    public static int getDegats(){
        return degats;
    }
}
