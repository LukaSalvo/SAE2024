package gameLaby.laby;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import moteurJeu.Clavier;
import moteurJeu.Jeu;

import javax.management.monitor.MonitorSettingException;
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
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    /**
     * Attributs: labyrinthe, timer
     */
    private Labyrinthe labyrinthe;
    private Timer timer;
    private Perso pj;

    /**
     * Constructeur de la classe LabyJeu
     *
     * @throws IOException
     */
    public LabyJeu() throws IOException {
        this.labyrinthe = new Labyrinthe("labySimple/laby1.txt");
        this.timer = new Timer();
        this.pj = labyrinthe.getPj();
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
                    for (Monstre m : labyrinthe.getListMonstre())
                        labyrinthe.deplacerPersonnage(m);
                        if (pj.estMort()) {
                            Platform.runLater(()->{
                            System.out.println("FIn du jeu , le personnage principal est mort ...");
                            Platform.exit();
                        });
                            timer.cancel();

                        }

                    }
                }, 5000, 5000);
        });
        monsterThread.start();
    }

    /**
     * Methode pour mettre a jour le jeu
     *
     * @param secondes temps ecoule depuis la derniere mise a jour
     * @param clavier  objet contenant l'état du clavier'
     */
    @Override
    public void update(double secondes, Clavier clavier) {
        // Deplacement du personnage selon les touches utilisées


        if( !pj.estMort()){
            if (clavier.haut) {
                pj.action(Labyrinthe.HAUT);
                labyrinthe.deplacerPersonnage(pj);
                clavier.haut = false;
            } else if (clavier.bas) {
                pj.action(Labyrinthe.BAS);
                labyrinthe.deplacerPersonnage(pj);
                clavier.bas = false;
            } else if (clavier.gauche) {
                pj.action(Labyrinthe.GAUCHE);
                labyrinthe.deplacerPersonnage(pj);
                clavier.gauche = false;
            } else if (clavier.droite) {
                pj.action(Labyrinthe.DROITE);
                labyrinthe.deplacerPersonnage(pj);
                clavier.droite = false;
            } else if (clavier.space) {
                clavier.space = false;
                for (Monstre m : labyrinthe.getListMonstre()) {
                    pj.attaquer(m, new AttaqueAlentour());
                }

            }



            labyrinthe.majEtatMonstre();
        }


    }

    /**
     * Methode pour initialiser le jeu
     */
    @Override
    public void init() {


    }

    /**
     * Methode pour savoir si le jeu est fini
     *
     * @return false
     */
    @Override
    public boolean etreFini() {
        return false;
    }

    /**
     * Methode pour recuperer le labyrinthe
     *
     * @return
     */
    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }


}
