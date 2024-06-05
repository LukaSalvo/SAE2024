package gameLaby.laby;
import moteurJeu.Clavier;
import moteurJeu.Jeu;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class LabyJeu implements Jeu {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Labyrinthe labyrinthe;
    private Timer timer;

    public LabyJeu() throws IOException {
        this.labyrinthe = new Labyrinthe("labySimple/laby1.txt");
        this.timer = new Timer();
        deplacerMonstre();
    }

    private void deplacerMonstre() {
        Thread monsterThread = new Thread(() -> {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    for(Monstre m: labyrinthe.listMonstre)
                        labyrinthe.deplacerMonstre(m);
                }
            }, 0, 5000);
        });
        monsterThread.start();
    }

    @Override
    public void update(double secondes, Clavier clavier) {
        // Deplacement du personnage selon les touches utilisées
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
        labyrinthe.majEtatMonstre();
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
