package gameLaby.laby;

import java.io.IOException;

/**
 * charge et affiche un labyrinthe
 */
public class Main {
    /**
     * methode principale, charge un labyrinthe et l'affiche
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // charge le labyrinthe
        Labyrinthe laby = new Labyrinthe("labySimple/laby1.txt");

        //affiche le labyrinthe charge
        for (int y = 0; y < laby.getLengthY(); y++) {
            // affiche la ligne
            for (int x = 0; x < laby.getLength(); x++) {
                if (laby.getMur(x, y))
                    System.out.print('X');
                else
                    System.out.print('.');
            }
            // saut de ligne
            System.out.println();
        }
    }
}
