package gameLaby.laby;

import javafx.application.Platform;
import moteurJeu.MoteurJeu;

import java.io.IOException;
import java.sql.SQLOutput;

/**
 * classe principale pour lancer le jeu
 */

public class MainLaby {
    /**
     * methode principale, cree le jeu et lance le moteur de jeu
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int width = LabyJeu.WIDTH;
        int height = LabyJeu.HEIGHT;
        int pFPS = 60;

        LabyJeu jeu = new LabyJeu();
        LabyDessin dessin = new LabyDessin();

        MoteurJeu.setTaille(width, height);
        MoteurJeu.setFPS(pFPS);

        MoteurJeu.launch(jeu, dessin);

    }

}
