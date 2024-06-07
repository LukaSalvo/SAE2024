package gameLaby.laby;

public interface TypeAttaque {

    /**
     * Methode pour attaquer un personnage
     *
     * @param attaquant personnage attaquant
     * @param victime   personnage victime
     */
    public void attaquer(Personnage attaquant, Personnage victime);
}
