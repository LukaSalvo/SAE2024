package GameLabyTest.laby;

import static org.junit.jupiter.api.Assertions.*;

import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Monstre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class LabyrintheTest {
    private Labyrinthe labyrinthe;

    @BeforeEach
    public void setUp() throws IOException {
        labyrinthe = new Labyrinthe("labySimple/laby1.txt");
    }

    @Test
    public void testMonstrePositionInitiale() {
        assertNotNull(labyrinthe.listMonstre.get(0));
        int monstreX = labyrinthe.listMonstre.get(0).getX();
        int monstreY = labyrinthe.listMonstre.get(0).getY();
        assertTrue(monstreX >= 0 && monstreX < labyrinthe.getLength());
        assertTrue(monstreY >= 0 && monstreY < labyrinthe.getLengthY());
        assertFalse(labyrinthe.getMur(monstreX, monstreY));
        assertFalse(labyrinthe.pj.etrePresent(monstreX, monstreY));
    }

    @Test
    public void testPersoNePeutPasSeDeplacerSurMonstre() {
        int monstreX = labyrinthe.listMonstre.get(0).getX();
        int monstreY = labyrinthe.listMonstre.get(0).getY();

        // Pos du perso differente de celle du monstre
        int persoX = labyrinthe.pj.getX();
        int persoY = labyrinthe.pj.getY();

        // Calculer les mouvements pour aller sur la case du monstre
        String actionVersMonstre = (monstreX > persoX) ? Labyrinthe.DROITE : (monstreX < persoX) ? Labyrinthe.GAUCHE :
                (monstreY > persoY) ? Labyrinthe.BAS : Labyrinthe.HAUT;

        labyrinthe.deplacerPerso(actionVersMonstre);

        assertNotEquals(monstreX, labyrinthe.pj.getX());
        assertNotEquals(monstreY, labyrinthe.pj.getY());
    }

    @Test
    public void testMonstreDeplacement() {
        int monstreX = labyrinthe.listMonstre.get(0).getX();
        int monstreY = labyrinthe.listMonstre.get(0).getY();

        labyrinthe.deplacerMonstre(labyrinthe.listMonstre.get(0));

        int nouveauMonstreX = labyrinthe.listMonstre.get(0).getX();
        int nouveauMonstreY = labyrinthe.listMonstre.get(0).getY();

        assertTrue(Math.abs(nouveauMonstreX - monstreX) <= 1 && Math.abs(nouveauMonstreY - monstreY) <= 1);
        assertFalse(labyrinthe.getMur(nouveauMonstreX, nouveauMonstreY));
        assertFalse(labyrinthe.pj.etrePresent(nouveauMonstreX, nouveauMonstreY));
    }

    @Test
    public void testPersoDeplacementValide() {
        int persoX = labyrinthe.pj.getX();
        int persoY = labyrinthe.pj.getY();

        labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        assertEquals(persoX + 1, labyrinthe.pj.getX());
        assertEquals(persoY, labyrinthe.pj.getY());

        labyrinthe.deplacerPerso(Labyrinthe.HAUT);
        assertEquals(persoX + 1, labyrinthe.pj.getX());
        assertEquals(persoY - 1, labyrinthe.pj.getY());
    }

    @Test
    public void testPersoNePeutPasTraverserMur() {
        int persoX = labyrinthe.pj.getX();
        int persoY = labyrinthe.pj.getY();
        // Déplacez le personnage vers un mur
        labyrinthe.deplacerPerso(Labyrinthe.GAUCHE);
        // Le personnage ne doit pas traverser le mur
        assertEquals(persoX, labyrinthe.pj.getX());
        assertEquals(persoY, labyrinthe.pj.getY());
    }


    /**
     * Test deplacement aleatoire des monstres
     */

    // Test 1: a chaque evolution (temps) le monstre se deplace, que le joueur se deplace ou non
    // Test 4: A chaque evolution, tous les monstres tentent de se deplacer
    // Test 5 : L'interface graphique se met a jour en fonction des deplacements des monstres
    @Test
    public void testDeplacementAleatoireMonstre() {
        int monstreX = labyrinthe.listMonstre.get(0).getX();
        int monstreY = labyrinthe.listMonstre.get(0).getY();

        labyrinthe.deplacerMonstre(labyrinthe.listMonstre.get(0));

        int nouveauMonstreX = labyrinthe.listMonstre.get(0).getX();
        int nouveauMonstreY = labyrinthe.listMonstre.get(0).getY();

        assertTrue(Math.abs(nouveauMonstreX - monstreX) <= 1 && Math.abs(nouveauMonstreY - monstreY) <= 1);
        assertFalse(labyrinthe.getMur(nouveauMonstreX, nouveauMonstreY));
        assertFalse(labyrinthe.pj.etrePresent(nouveauMonstreX, nouveauMonstreY));
    }

    /**
     * Test de collision entre monstre et joueur
     */

    //Test 3 Deux monstres ou un monstre et un joueur ne peuvent pas se trouver sur la meme case
    @Test
    public void testMonstreJoueurCollision() {
        int monstreX = labyrinthe.listMonstre.get(0).getX();
        int monstreY = labyrinthe.listMonstre.get(0).getY();
        int persoX = labyrinthe.pj.getX();
        int persoY = labyrinthe.pj.getY();

        // Calculer les mouvements pour aller sur la case du monstre
        String actionVersMonstre = (monstreX > persoX) ? Labyrinthe.DROITE : (monstreX < persoX) ? Labyrinthe.GAUCHE :
                (monstreY > persoY) ? Labyrinthe.BAS : Labyrinthe.HAUT;

        labyrinthe.deplacerPerso(actionVersMonstre);

        assertNotEquals(monstreX, labyrinthe.pj.getX());
        assertNotEquals(monstreY, labyrinthe.pj.getY());

    }

    /**
     * Test de collision entre monstre et obstacle
     */

    //Test 2 : • Les monstres sont bloqués par les obstacles. S'ils ont choisi de se diriger vers un
    //obstacle (mur, joueur, ...), leur mouvement est alors annulé (sauf s'il s'agit de
    //fantômes).
    @Test
    public void testMonstreBloqueParObstacle() {
        int monstreX = labyrinthe.listMonstre.get(0).getX();
        int monstreY = labyrinthe.listMonstre.get(0).getY();

        // Calculer les mouvements pour aller sur la case du monstre
        String actionVersMonstre = (monstreX > 0) ? Labyrinthe.GAUCHE : Labyrinthe.HAUT;

        labyrinthe.deplacerPerso(actionVersMonstre);

        assertEquals(monstreX, labyrinthe.listMonstre.get(0).getX());
        assertEquals(monstreY, labyrinthe.listMonstre.get(0).getY());
    }

    @Test
    public void testNombreDeMonstreApresMort() {
        int compteur = labyrinthe.listMonstre.size();
        Monstre monstre = labyrinthe.listMonstre.get(0);

        // Faire perdre tous ses PV au monstre
        monstre.perdrePv(monstre.getPv());

        labyrinthe.deplacerMonstre(monstre);

        assertEquals(compteur -1, labyrinthe.listMonstre.size());

    }
}
