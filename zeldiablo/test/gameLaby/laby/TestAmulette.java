package gameLaby.laby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestAmulette {
    @Test
    public void test() throws IOException {
        LabyJeu jeu = new LabyJeu();
        Amulette a = jeu.getLabyrinthe().getAmulette();
        assertEquals(a.getX(),8);
        assertEquals(a.getY(),1);
        Perso pj = jeu.getLabyrinthe().getPj();
        Monstre m = jeu.getLabyrinthe().getListMonstre().get(0);
        m.deplacer(8,1);
        jeu.getLabyrinthe().recupererAmulette();
        a = jeu.getLabyrinthe().getAmulette();
        assertNotNull(a);
        pj.deplacer(8,1);
        jeu.getLabyrinthe().recupererAmulette();
        assertEquals(pj.getPossedeAmulette(),true);

    }
}
