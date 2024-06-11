package gameLaby.laby;

/**
 * Implémentation de l'interface TypeDeplacement pour le déplacement au clavier.
 */
public class DeplacementClavier implements TypeDeplacement {

    /**
     * Déplace le personnage selon une action du clavier.
     *
     * @param p    le personnage à déplacer
     * @param laby le labyrinthe dans lequel se déplace le personnage
     */
    @Override
    public void deplacer(Personnage p, Labyrinthe laby) {
        int[] suivante = p.getSuivant(p.getAction());

        // Vérifie si le déplacement est possible
        if (p.peutBouger(laby, suivante[0], suivante[1])) {
            p.setPosition(suivante);
        }
    }
}
