package gameLaby.laby;

import moteurJeu.MoteurJeu;

import java.io.IOException;

/**
 * classe principale pour lancer le jeu
 */

public class MainLaby {
    /**
     * methode principale, cree le jeu et lance le moteur de jeu
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int width = 800;
        int height = 600;
        int pFPS = 60;

        LabyJeu jeu = new LabyJeu();
        LabyDessin dessin = new LabyDessin();

        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

        MoteurJeu.launch(jeu,dessin);
    }

}
