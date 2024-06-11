package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour tester le comportement des monstres.
 */
public class MonstreTest {
    Labyrinthe labyrinthe;
    Personnage perso;
    List<Monstre> listMonstre;
    List<CasePieges> listCasePiege;

    int pvInitiaux;
    int initialX;
    int initialY;
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
        initialX = monstre.getX();
        initialY = monstre.getY();
    }

    /**
     * Teste la position initiale du Monstre.
     */
    @Test
    public void testMonstrePositionInitiale() {
        int monstreX = monstre.getX();
        int monstreY = monstre.getY();
        assertTrue(monstreX >= 0 && monstreX < labyrinthe.getLength());
        assertTrue(monstreY >= 0 && monstreY < labyrinthe.getLengthY());
        assertFalse(labyrinthe.getMur(monstreX, monstreY));
        assertFalse(perso.etrePresent(monstreX, monstreY));
    }

    /**
     * Teste la création de monstres.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Test
    void creerMonstres() throws IOException {
        for(Monstre m : listMonstre){
            int x = m.getX();
            int y = m.getY();
            assertEquals(labyrinthe.getMurs()[x][y],false);
            boolean res = false;
            if(perso.getX()==x && perso.getY()==y){
                res = true;
            }
            assertEquals(false,res);
            res = false;
            for(CasePieges c : listCasePiege){
                if(c.etreSurMemeCase(x,y)){
                    res =true;
                }
            }
            assertEquals(false,res);
        }
    }

    /**
     * Teste si le monstre peut se déplacer dans différentes directions.
     */
    @Test
    public void testMonstreDeplacementValide() {
        monstre.setTypeDeplacement(new DeplacementClavier());
        monstre.action(Labyrinthe.DROITE);
        monstre.deplacerPersonnage(labyrinthe);
        assertEquals(initialX + 1, monstre.getX());
        assertEquals(initialY, monstre.getY());

        monstre.action(Labyrinthe.HAUT);
        monstre.deplacerPersonnage(labyrinthe);
        assertEquals(initialX +1, monstre.getX());
        assertEquals(initialY - 1, monstre.getY());

        monstre.action(Labyrinthe.HAUT);
        monstre.deplacerPersonnage(labyrinthe);
        assertEquals(initialX+1 , monstre.getX());
        assertEquals(initialY-1, monstre.getY());
    }

    /**
     * Teste le déplacement aléatoire des monstres.
     */
    @Test
    public void testDeplacementAleatoireMonstre() {
        monstre.setTypeDeplacement(new DeplacementAleatoire());

        boolean deplaceAuMoinsUneFois = false;

        // Effectuer plusieurs déplacements
        for (int i = 0; i < 100; i++) {
            monstre.deplacerPersonnage(labyrinthe);

            int nouveauMonstreX = monstre.getX();
            int nouveauMonstreY = monstre.getY();

            // Vérifier que le monstre s'est déplacé
            if (initialX != nouveauMonstreX || initialY != nouveauMonstreY) {
                deplaceAuMoinsUneFois = true;
            }

            // Vérifier que le monstre ne se trouve pas sur un mur
            assertFalse(labyrinthe.getMur(nouveauMonstreX, nouveauMonstreY));

            // Si le monstre s'est déplacé au moins une fois, sortir de la boucle
            if (deplaceAuMoinsUneFois) {
                break;
            }
        }

        // Vérifier que le monstre s'est déplacé au moins une fois
        assertTrue(deplaceAuMoinsUneFois);
    }

    /**
     * Teste la collision entre deux monstres.
     */
    @Test
    public void testCollisionMonstre() {
        // Créer deux monstres et attribuer le déplacement par clavier
        labyrinthe.creerMonstres(2);
        Monstre monstre1 = listMonstre.get(1);
        monstre1.setTypeDeplacement(new DeplacementClavier());
        Monstre monstre2 = listMonstre.get(2);
        monstre2.setTypeDeplacement(new DeplacementClavier());

        // Positionner monstre1 à droite du personnage
        monstre1.setX(perso.getX() + 1);
        monstre1.setY(perso.getY());

        // Positionner monstre2 à droite de monstre1
        monstre2.setX(monstre1.getX() + 1);
        monstre2.setY(monstre1.getY());

        // Stocker les positions initiales de monstre1 et monstre2
        int initialXMonstre1 = monstre1.getX();
        int initialYMonstre1 = monstre1.getY();
        int initialXMonstre2 = monstre2.getX();
        int initialYMonstre2 = monstre2.getY();

        // Tenter de déplacer monstre1 sur le personnage
        monstre1.action(Labyrinthe.GAUCHE);
        monstre1.deplacerPersonnage(labyrinthe);

        // Vérifier que monstre1 n'a pas bougé
        assertEquals(initialXMonstre1, monstre1.getX());
        assertEquals(initialYMonstre1, monstre1.getY());

        // Tenter de déplacer monstre2 sur monstre1
        monstre2.action(Labyrinthe.GAUCHE);
        monstre2.deplacerPersonnage(labyrinthe);

        // Vérifier que monstre2 n'a pas bougé
        assertEquals(initialXMonstre2, monstre2.getX());
        assertEquals(initialYMonstre2, monstre2.getY());
    }

    /**
     * Teste le nombre de monstres après la mort d'un monstre.
     *
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @Test
    public void testNombreDeMonstreApresMort() throws IOException {
        int compteur = listMonstre.size();
        Monstre monstre = listMonstre.get(0);

        // Faire perdre tous ses PV au monstre
        monstre.perdrePv(monstre.getPv());

        labyrinthe.majEtatMonstre();

        assertEquals(compteur -1, listMonstre.size());
    }
}
