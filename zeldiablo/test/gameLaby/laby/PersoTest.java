package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour tester le comportement du personnage.
 */
class PersoTest {

    Labyrinthe labyrinthe;
    Perso perso;
    List<Monstre> listMonstre;
    int initialX;
    int initialY;

    @BeforeEach
    void setUp() throws IOException {
        labyrinthe = new Labyrinthe("LabySimple/laby1.txt");
        perso = labyrinthe.getPj();
        listMonstre = labyrinthe.getListMonstre();
        perso.setTypeDeplacement(new DeplacementClavier());
        initialX = perso.getX();
        initialY = perso.getY();
    }

    /**
     * Teste le déplacement du personnage vers le haut.
     */
    @Test
    void deplacerVersHaut() {
        int initialX = perso.getX();
        int initialY = perso.getY();
        perso.deplacer(initialX, initialY - 1);

        assertEquals(initialX, perso.getX());
        assertEquals(initialY - 1, perso.getY());
    }

    /**
     * Teste le déplacement du personnage vers le bas.
     */
    @Test
    void deplacerVersBas() {
        int initialX = perso.getX();
        int initialY = perso.getY();
        perso.deplacer(initialX, initialY + 1);

        assertEquals(initialX, perso.getX());
        assertEquals(initialY + 1, perso.getY());
    }

    /**
     * Teste le déplacement du personnage vers la gauche.
     */
    @Test
    void deplacerVersGauche() {
        int initialX = perso.getX();
        int initialY = perso.getY();
        perso.deplacer(initialX - 1, initialY);

        assertEquals(initialX - 1, perso.getX());
        assertEquals(initialY, perso.getY());
    }

    /**
     * Teste le déplacement du personnage vers la droite.
     */
    @Test
    void deplacerVersDroite() {
        int initialX = perso.getX();
        int initialY = perso.getY();
        perso.deplacer(initialX + 1, initialY);

        assertEquals(initialX + 1, perso.getX());
        assertEquals(initialY, perso.getY());
    }

    /**
     * Teste si le personnage peut se déplacer dans différentes directions.
     */
    @Test
    public void testPersoDeplacementValide() {
        // Déplacement vers la droite
        perso.action(Labyrinthe.DROITE);
        labyrinthe.deplacerPersonnage(perso);
        assertEquals(initialX + 1, perso.getX());
        assertEquals(initialY, perso.getY());

        // Déplacement vers le haut
        perso.action(Labyrinthe.HAUT);
        labyrinthe.deplacerPersonnage(perso);
        assertEquals(initialX +1, perso.getX());
        assertEquals(initialY - 1, perso.getY());
    }

    /**
     * Teste que le personnage ne peut pas se déplacer sur un monstre.
     */
    @Test
    public void testPersoNePeutPasSeDeplacerSurMonstre() {

        Monstre monstre = listMonstre.get(0);

        // Positionner le monstre à droite du personnage
        monstre.setX(perso.getX()+1);
        monstre.setX(perso.getY());

        int monstreX =listMonstre.get(0).getX();
        int monstreY = listMonstre.get(0).getY();

        // Tenter de deplacer le personnage à droite
        perso.action(Labyrinthe.DROITE);
        labyrinthe.deplacerPersonnage(perso);

        assertNotEquals(monstreX, perso.getX());
        assertNotEquals(monstreY, perso.getY());
    }

    /**
     * Teste si le personnage ne peut pas se déplacer sur un mur.
     */
    @Test
    public void testPersoNePeutPasTraverserMur() {
        int initialX = perso.getX();
        int initialY = perso.getY();

        // Déplacez le personnage vers un mur
        perso.action(Labyrinthe.GAUCHE);
        labyrinthe.deplacerPersonnage(perso);

        // Le personnage ne doit pas traverser le mur
        assertEquals(initialX, perso.getX());
        assertEquals(initialY, perso.getY());
    }



    @Test
    public void testPersoNePeutPasAttquerLorsquilEstMort() {
        Monstre monstre = listMonstre.get(0);
        perso.perdrePv(Perso.PV_ENTIER);
        perso.attaquer(monstre, new AttaqueAlentour());

        assertEquals(Monstre.PV_MONSTRE_ENTIER, monstre.getPv());
    }


    @Test
    public void testMethodeEstMort(){
        perso.perdrePv(Perso.PV_ENTIER);
        assertTrue(perso.estMort());
    }


    @Test
    public void testPersoPeutPasBougerLorsquilEstMort() {

        perso.perdrePv(Perso.PV_ENTIER);
        assertTrue(perso.estMort());


        int X = perso.getX();
        int Y = perso.getY();


        perso.setTypeDeplacement(new DeplacementClavier());
        perso.action(Labyrinthe.HAUT);
        labyrinthe.deplacerPersonnage(perso);

        assertEquals(X, perso.getX());
        assertEquals(Y, perso.getY());
    }





}
