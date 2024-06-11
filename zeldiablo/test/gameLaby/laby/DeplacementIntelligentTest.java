package gameLaby.laby;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeplacementIntelligentTest {



    private Labyrinthe labyrinthe;
    private Personnage personnage;
    private List<Monstre> listMonstre;



    @Test
    public void testDeplacementIntelligentVersHero() throws IOException {

        labyrinthe = new Labyrinthe("LabySimple/laby1.txt");
        Perso perso = new Perso(1,1);
        Monstre monstre = new Monstre(4,4);
        DeplacementIntelligent dI = new DeplacementIntelligent();


        assertEquals(4, monstre.getX());
        assertEquals(4, monstre.getY());

        dI.deplacer(monstre, labyrinthe);
        int expectedX = monstre.getX();
        int expectedY = monstre.getY();
        assertTrue((expectedX == 3 && expectedY == 4) || (expectedX == 4 && expectedY == 3));
    }

    
    @Test
    public void testDeplacementIntelligentPasDeDeplacementPourHeroMort() throws IOException {



        labyrinthe = new Labyrinthe("LabySimple/laby1.txt");
        Perso perso = new Perso(1,1);
        Monstre monstre = new Monstre(4,4);
        DeplacementIntelligent dI = new DeplacementIntelligent();
        
        perso.perdrePv(Perso.PV_ENTIER);


        assertEquals(4, monstre.getX());
        assertEquals(4, monstre.getY());
        
        dI.deplacer(monstre, labyrinthe);

        assertEquals(4, monstre.getX());
        assertEquals(4, monstre.getY());
    }
}

