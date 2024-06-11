package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestAmulette {
    
    private Amulette amulette;
    private Perso perso;
    private Monstre monstre;
    
    private Labyrinthe labyrinthe;

    @BeforeEach
    public void setup() throws IOException {
        labyrinthe = new Labyrinthe("LabySimple/laby1.txt");
        
        amulette = labyrinthe.getAmulette();
        perso = labyrinthe.getPj();
        monstre = labyrinthe.getListMonstre().get(0);
    }

    @Test
    public void testAmulettePositionInitiale() {
        assertEquals(8, amulette.getX());
        assertEquals(1, amulette.getY());
    }

    @Test
    public void testMonstreRecupereAmulette() {
        monstre.setPosition(8, 1);
        labyrinthe.getPj().recupererAmulette(amulette);
        assertNotNull(labyrinthe.getAmulette());
    }

    @Test
    public void testPersoRecupereAmulette() {
        perso.setPosition(8, 1);
        perso.recupererAmulette(amulette);
        perso.appliquerRepercussion(labyrinthe);
        assertTrue(perso.getPossedeAmulette());
        assertNull(labyrinthe.getAmulette());
    }

    @Test
    public void testPersoNeRecuperePasAmuletteHorsPosition() {
        perso.setPosition(7, 1);
        perso.recupererAmulette(amulette);
        perso.appliquerRepercussion(labyrinthe);
        assertFalse(perso.getPossedeAmulette());
        assertNotNull(labyrinthe.getAmulette());
    }

    @Test
    public void testAmuletteAbsenteApresRecuperation() {
        perso.setPosition(8, 1);
        labyrinthe.getPj().recupererAmulette(amulette);
        perso.appliquerRepercussion(labyrinthe);
        assertTrue(perso.getPossedeAmulette());
        assertNull(labyrinthe.getAmulette());
    }

    @Test
    public void testPersoNePeutPasRecupererAmuletteDeuxFois() {
        perso.setPosition(8, 1);
        labyrinthe.getPj().recupererAmulette(amulette);
        perso.appliquerRepercussion(labyrinthe);
        assertTrue(perso.getPossedeAmulette());
        assertNull(labyrinthe.getAmulette());

        // Essayer de récupérer l'amulette une deuxième fois
        labyrinthe.getPj().recupererAmulette(amulette);
        assertTrue(perso.getPossedeAmulette());
    }

    @Test
    public void testMonstreNePeutPasInteragirAvecAmulette() {
        monstre.setPosition(8, 1);
        assertNotEquals(monstre.getX(), perso.getX());
        assertNotEquals(monstre.getY(), perso.getY());

        // Le monstre essaie d'interagir avec l'amulette (par la position)
        labyrinthe.getPj().recupererAmulette(amulette);
        assertNotNull(labyrinthe.getAmulette());
    }
}
