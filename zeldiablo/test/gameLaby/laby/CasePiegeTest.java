package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de test pour tester le comportement des cases pièges.
 */
public class CasePiegeTest {
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
        pvInitiaux = perso.getPv();
    }

    /**
     * Teste si le personnage perd des points de vie lorsqu'il est sur une case piège.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Test
    public void testPersoSurCasePiege() throws IOException {
        CasePieges casePiege = listCasePiege.get(0);

        perso.setX(casePiege.getX());
        perso.setY(casePiege.getY());

        labyrinthe.estSurCasePiege(new int[]{perso.getX(), perso.getY()}, perso);

        assertTrue(perso.getPv() < pvInitiaux);
    }

    /**
     * Teste si un monstre perd des points de vie lorsqu'il est sur une case piège.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Test
    public void testMonstreSurCasePiege() throws IOException {
        int pvInitiaux = monstre.getPv();

        CasePieges casePiege = listCasePiege.get(0);

        monstre.setX(casePiege.getX());
        monstre.setY(casePiege.getY());

        labyrinthe.estSurCasePiege(new int[]{monstre.getX(), monstre.getY()}, monstre);

        assertTrue(monstre.getPv() < pvInitiaux);
    }
}
