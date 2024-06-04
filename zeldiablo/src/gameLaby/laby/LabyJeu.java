package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Labyrinthe labyrinthe;

    public LabyJeu() throws IOException {
        this.labyrinthe = new Labyrinthe("labySimple/laby2.txt");
    }

    @Override
    public void update(double secondes, Clavier clavier) {

        // Deplacement du personnage selon les touches utilisé
        if(clavier.haut){
            labyrinthe.deplacerPerso(Labyrinthe.HAUT);
        }
        else if(clavier.bas){
            labyrinthe.deplacerPerso(Labyrinthe.BAS);
        }
        else if(clavier.gauche){
            labyrinthe.deplacerPerso(Labyrinthe.GAUCHE);
        }
        else if(clavier.droite){
            labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        }


    }

    @Override
    public void init() {

    }

    @Override
    public boolean etreFini() {
        return false;
    }


    public Labyrinthe getLabyrinthe(){
        return this.labyrinthe;
    }

}
