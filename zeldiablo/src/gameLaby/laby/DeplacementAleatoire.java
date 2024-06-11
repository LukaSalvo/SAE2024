package gameLaby.laby;

import java.util.Random;

import static gameLaby.laby.Labyrinthe.*;

public class DeplacementAleatoire implements TypeDeplacement {
    /**
     * Methode pour deplacer le personnage
     *
     * @param p personnage a deplacer
     */
    public void deplacer(Personnage p, Labyrinthe l) {
        String[] actions = {HAUT, BAS, GAUCHE, DROITE};
        Random rand = new Random();
        int[] suivante = p.getSuivant(actions[rand.nextInt(actions.length)]);
        if (p.peutBouger(l, suivante[0], suivante[1])) {
            p.setPosition(suivante);
        }

    }
}
