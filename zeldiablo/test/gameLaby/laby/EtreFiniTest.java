package gameLaby.laby;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EtreFiniTest {


    private LabyJeu jeu;

    /**
     * Initialisation du jeu avant chaque test.
     * @throws IOException
     */
    @BeforeEach
    public void setUp() throws IOException {
        jeu = new LabyJeu();
    }

    /**
     * Teste si le jeu est fini lorsque le personnage est mort.
     */
    @Test
    public void testPersonnageMort() {
        Perso pj = jeu.getLabyrinthe().getPj();
        pj.perdrePv(Perso.PV_ENTIER);
        assertTrue(jeu.etreFini());
    }

    /**
     * Teste si le jeu est fini lorsque le personnage est sur la case de sortie avec l'amulette.
     */
    @Test
    public void testPersonnageSurCaseDepartAvecAmulette() {
        Perso pj = jeu.getLabyrinthe().getPj();
        pj.setPossedeAmulette(true);
        pj.setPosition(jeu.getLabyrinthe().getDepart().getX(), jeu.getLabyrinthe().getDepart().getY());
        assertTrue(jeu.etreFini());
    }

    /**
     * Teste si le jeu est fini lorsque le personnage est sur la case de sortie sans amulette.
     */
    @Test
    public void testPersonnageSurCaseDepartSansAmulette() {
        Perso pj = jeu.getLabyrinthe().getPj();
        pj.setPosition(jeu.getLabyrinthe().getDepart().getX(), jeu.getLabyrinthe().getDepart().getY());
        assertFalse(jeu.etreFini());
    }

    /**
     * Teste si le jeu est fini lorsque le personnage a l'amulette mais n'est pas sur la case de sortie.
     */
    @Test
    public void testPersonnageAvecAmulettePasSurCaseDepart() {
        Perso pj = jeu.getLabyrinthe().getPj();
        pj.setPosition(1, 1);
        pj.setPossedeAmulette(true);
        assertFalse(jeu.etreFini());
    }

    /**
     * Teste si le jeu est fini lorsque le personnage est pas sur la case de sortie sans amulette.
     */
    @Test
    public void testPersonnageEnViePasSurCaseDepartSansAmulette() {
        assertFalse(jeu.etreFini());
    }
}


