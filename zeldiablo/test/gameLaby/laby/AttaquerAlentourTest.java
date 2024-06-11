package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de test pour tester les attaques dans le jeu.
 */
public class AttaquerAlentourTest {

    Labyrinthe labyrinthe;
    Personnage perso;
    List<Monstre> listMonstre;
    List<CasePieges> listCasePiege;

    int pvInitiaux;
    Monstre monstre;

    @BeforeEach
    void setUp() throws IOException {
        labyrinthe = new Labyrinthe("LabySimple/laby1.txt");
        perso = labyrinthe.getPj();
        listMonstre = labyrinthe.getListMonstre();
        monstre = listMonstre.get(0);
        listCasePiege = labyrinthe.getCasesPieges();
        perso.setTypeDeplacement(new DeplacementClavier());
        pvInitiaux = monstre.getPv();
    }

    /**
     * Teste la perte de points de vie du personnage lorsque attaqué par un monstre adjacent.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Test
    public void testAttaqueDeMonstreACote() throws IOException {
        // Place Personnage et Monstre à côté
        perso.setX(5);
        perso.setY(5);
        monstre.setX(4);
        monstre.setY(5);

        int pvBase = perso.getPv();
        monstre.deplacerPersonnage(labyrinthe);

        // Vérifie que le joueur a perdu 1 point de vie
        assertEquals(pvBase - 1, perso.getPv());

        // Vérifie que le monstre n'a pas bougé
        assertEquals(4, monstre.getX());
        assertEquals(5, monstre.getY());
    }

    /**
     * Teste l'attaque d'un monstre qui n'est pas à proximité du personnage.
     */
    @Test
    public void testAttaquerMonstrePasAlentour() {
        int pvMonstre = monstre.getPv();
        perso.attaquer(monstre, new AttaqueAlentour());

        assertEquals(pvMonstre, monstre.getPv());
    }

    /**
     * Teste l'attaque d'un monstre qui est à une case de distance du personnage.
     */
    @Test
    public void testAttaquerMonstre1Alentour() {
        monstre.setX(perso.getX() + 1);
        monstre.setY(perso.getY());

        int pvMonstre = monstre.getPv();
        perso.attaquer(monstre, new AttaqueAlentour());

        assertEquals(pvMonstre - 1, monstre.getPv());
    }

    /**
     * Teste l'attaque de deux monstres qui sont chacun à une case de distance du personnage.
     */
    @Test
    public void testAttaquerMonstre2Alentour() {
        labyrinthe.creerMonstres(1);
        Monstre monstre1 = listMonstre.get(1);

        monstre.setX(perso.getX() + 1);
        monstre.setY(perso.getY());

        monstre1.setX(perso.getX());
        monstre1.setY(perso.getY() + 1);

        int pvMonstre = monstre.getPv();
        int pvMonstre2 = monstre1.getPv();

        perso.attaquer(monstre, new AttaqueAlentour());
        perso.attaquer(monstre1, new AttaqueAlentour());

        assertEquals(pvMonstre - 1, monstre.getPv());
        assertEquals(pvMonstre2 - 1, monstre1.getPv());
    }
}