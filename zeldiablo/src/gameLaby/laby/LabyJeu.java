package gameLaby.laby;
import moteurJeu.Clavier;
import moteurJeu.Jeu;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe qui represente le jeu du labyrinthe

 */
public class LabyJeu implements Jeu {
    /**
     * Constantes pour la taille de la fenetre
     */
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    /**
     * Attributs: labyrinthe, timer
     */
    private Labyrinthe labyrinthe;
    private Timer timer;

    /**
     * Constructeur de la classe LabyJeu
     * @throws IOException
     */
    public LabyJeu() throws IOException {
        this.labyrinthe = new Labyrinthe("labySimple/laby1.txt");
        this.timer = new Timer();
        deplacerMonstre();
    }

    /**
     * Methode pour deplacer le monstre
     */
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

    /**
     * Methode pour mettre a jour le jeu
     * @param secondes temps ecoule depuis la derniere mise a jour
     * @param clavier objet contenant l'état du clavier'
     */
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

    /**
     * Methode pour initialiser le jeu
     */
    @Override
    public void init() {

    }

    /**
     * Methode pour savoir si le jeu est fini
     * @return false
     */
    @Override
    public boolean etreFini() {
        return false;
    }

    /**
     * Methode pour recuperer le labyrinthe
     * @return
     */
    public Labyrinthe getLabyrinthe(){
        return this.labyrinthe;
    }
}
