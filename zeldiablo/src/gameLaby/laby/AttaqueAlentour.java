package gameLaby.laby;

public class AttaqueAlentour implements TypeAttaque {
    /**
     * Methode pour attaquer un personnage
     *
     * @param attaquant personnage attaquant
     * @param victime   personnage victime
     */
    @Override
    public void attaquer(Personnage attaquant, Personnage victime) {
        if (attaquant.estAutour(victime)) {
            victime.perdrePv(1);
        }

    }
}
