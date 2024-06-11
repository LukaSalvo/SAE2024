package gameLaby.laby;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeplacementIntelligentTest {



    private Labyrinthe labyrinthe;


    @Test
    public void testDeplacementIntelligentVersHero() throws IOException {

        labyrinthe = new Labyrinthe("LabySimple/laby1.txt");
        Perso perso = new Perso(1,1);
        Monstre monstre = new Monstre(4,1);
        DeplacementIntelligent dI = new DeplacementIntelligent();


        assertEquals(4, monstre.getX());
        assertEquals(1, monstre.getY());

        dI.deplacer(monstre, labyrinthe);
        int X = monstre.getX();
        int Y = monstre.getY();
        assertTrue((X == 3 && Y == 1)|| (X == 4 && Y == 0) || (X == 4 && Y == 2));
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

