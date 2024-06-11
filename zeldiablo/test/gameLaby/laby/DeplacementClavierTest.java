package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test pour tester le déplacement du personnage avec la saisie clavier.
 */
class DeplacementClavierTest {

    Labyrinthe labyrinthe;
    Personnage personnage;
    List<Monstre> listMonstre;
    int initialX;
    int initialY;

    @BeforeEach
    void setUp() throws IOException {
        labyrinthe = new Labyrinthe("LabySimple/laby1.txt");
        personnage = labyrinthe.getPj();
        personnage.setTypeDeplacement(new DeplacementClavier());
        listMonstre = labyrinthe.getListMonstre();
        initialX = personnage.getX();
        initialY = personnage.getY();
    }

    /**
     * Teste si le personnage peut se déplacer dans toutes les directions.
     */
    @Test
    public void testPersoDeplacementValide() {

        // Le personnage devrait rester à sa position initiale lorsqu'il se déplace vers la gauche
        personnage.action(Labyrinthe.GAUCHE);
        personnage.deplacerPersonnage(labyrinthe);
        assertEquals(initialX, personnage.getX());
        assertEquals(initialY, personnage.getY());

        // Le personnage devrait se déplacer vers la droite
        personnage.action(Labyrinthe.DROITE);
        personnage.deplacerPersonnage(labyrinthe);
        assertEquals(initialX + 1, personnage.getX());
        assertEquals(initialY, personnage.getY());

        // Le personnage devrait se déplacer vers le haut
        personnage.action(Labyrinthe.HAUT);
        personnage.deplacerPersonnage(labyrinthe);
        assertEquals(initialX + 1, personnage.getX());
        assertEquals(initialY - 1, personnage.getY());

        // Le personnage devrait se déplacer vers le bas
        personnage.action(Labyrinthe.BAS);
        personnage.deplacerPersonnage(labyrinthe);
        assertEquals(initialX + 1, personnage.getX());
        assertEquals(initialY, personnage.getY());
    }
}
