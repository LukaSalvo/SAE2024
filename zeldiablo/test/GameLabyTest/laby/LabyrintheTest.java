package GameLabyTest.laby;

import static org.junit.jupiter.api.Assertions.*;

import gameLaby.laby.CasePieges;
import gameLaby.laby.LabyJeu;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Monstre;
import moteurJeu.Jeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Classe de test pour la classe Labyrinthe
 */
public class LabyrintheTest {
    /**
     * Labyrinthe à tester
     */
    private Labyrinthe labyrinthe;

    /**
     * Initialisation du labyrinthe
     * @throws IOException
     */
    @BeforeEach
    public void setUp() throws IOException {
        labyrinthe = new Labyrinthe("labySimple/laby1.txt");
    }

    /**
     * Test de la position initiale du Monstre
     */
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

    /**
     * Test que le personnage ne peut pas se déplacer sur un monstre
     */
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

    /**
     * Test deplacement du monstre
     */
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
    public void testMonstreDeplacement2() {
        Monstre monstre = labyrinthe.listMonstre.get(0);
        int monstreX = monstre.getX();
        int monstreY = monstre.getY();

        labyrinthe.deplacerMonstre(monstre);

        int nouveauMonstreX = monstre.getX();
        int nouveauMonstreY = monstre.getY();

        assertFalse(monstreX == nouveauMonstreX && monstreY == nouveauMonstreY);

        assertFalse(labyrinthe.getMur(nouveauMonstreX, nouveauMonstreY));

        assertFalse(labyrinthe.personnagePresent(nouveauMonstreX, nouveauMonstreY) && !monstre.etrePresent(nouveauMonstreX, nouveauMonstreY));
    }

    /**
     * Test si le personnage peut se deplacer
     */
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

    /**
     * Test si le personnage ne peut pas se deplacer sur un mur
     */
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

    /**
     * Test de la mort d'un monstre (disparition de la liste)
     * @throws IOException
     */

    @Test
    public void testNombreDeMonstreApresMort() throws IOException {

        int compteur = labyrinthe.listMonstre.size();
        Monstre monstre = labyrinthe.listMonstre.get(0);

        // Faire perdre tous ses PV au monstre
        monstre.perdrePv(monstre.getPv());


        labyrinthe.deplacerMonstre(monstre);


        assertEquals(compteur -1, labyrinthe.listMonstre.size());
    }
    @Test
    public void testMonstreSurCasePiege() {

        assertTrue(!labyrinthe.listMonstre.isEmpty());
        assertTrue(!labyrinthe.casesPieges.isEmpty());

        Monstre monstre = labyrinthe.listMonstre.get(0);
        CasePieges casePiege = labyrinthe.casesPieges.get(0);

        int pvInitiaux = monstre.getPv();

        monstre.setX(casePiege.getPosX());
        monstre.setY(casePiege.getPosY());

        labyrinthe.estSurCasePiege(new int[]{monstre.getX(), monstre.getY()}, monstre);

        assertTrue(monstre.getPv() < pvInitiaux);
    }


    @Test
    public void testPersonnageSurCasePiege() {
        assertNotNull(labyrinthe.pj);
        assertTrue(!labyrinthe.casesPieges.isEmpty());

        CasePieges casePiege = labyrinthe.casesPieges.get(0);

        int pvInitiaux = labyrinthe.pj.getPv();

        labyrinthe.pj.setX(casePiege.getPosX());
        labyrinthe.pj.setY(casePiege.getPosY());

        labyrinthe.estSurCasePiege(new int[]{labyrinthe.pj.getX(), labyrinthe.pj.getY()}, labyrinthe.pj);

        assertTrue(labyrinthe.pj.getPv() < pvInitiaux);
    }
}
