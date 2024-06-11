package gameLaby.laby;

import javafx.application.Platform;
import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Classe qui représente le jeu du labyrinthe.
 */
public class LabyJeu implements Jeu {
    /**
     * Largeur de la fenêtre du jeu.
     */
    public static final int WIDTH = 1000;

    /**
     * Hauteur de la fenêtre du jeu.
     */
    public static final int HEIGHT = 700;

    /**
     * Labyrinthe du jeu.
     */
    private Labyrinthe labyrinthe;

    /**
     * Service de planification des tâches pour les mises à jour périodiques.
     */
    private ScheduledExecutorService timerMaj;

    /**
     * Personnage joueur.
     */
    private Perso pj;

    /**
     * Constructeur de la classe LabyJeu.
     *
     * @throws IOException si le fichier du labyrinthe ne peut être lu.
     */
    public LabyJeu() throws IOException {
        this.labyrinthe = new Labyrinthe();
        this.timerMaj = Executors.newScheduledThreadPool(1);
        this.pj = labyrinthe.getPj();
        deplacerMonstre();
    }

    /**
     * Méthode pour déplacer les monstres du labyrinthe périodiquement.
     */
    private void deplacerMonstre() {
        timerMaj.scheduleAtFixedRate(() -> {
            for (Monstre m : labyrinthe.getListMonstre()) {
                m.deplacerPersonnage(labyrinthe);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * Méthode pour mettre à jour le jeu.
     *
     * @param secondes temps écoulé depuis la dernière mise à jour.
     * @param clavier  objet contenant l'état du clavier.
     */
    @Override
    public void update(double secondes, Clavier clavier) {
        if (!etreFini()) {
            deplacementsPerso(clavier);
            attaquePerso(clavier);
            labyrinthe.majEtatMonstre();
        } else {
            fermerJeu();
        }
    }

    /**
     * Gère les déplacements du personnage joueur en fonction des touches pressées.
     *
     * @param clavier objet contenant l'état du clavier.
     */
    private void deplacementsPerso(Clavier clavier) {
        if (clavier.haut) {
            deplacerPerso(Labyrinthe.HAUT, clavier);
        } else if (clavier.bas) {
            deplacerPerso(Labyrinthe.BAS, clavier);
        } else if (clavier.gauche) {
            deplacerPerso(Labyrinthe.GAUCHE, clavier);
        } else if (clavier.droite) {
            deplacerPerso(Labyrinthe.DROITE, clavier);
        }
    }

    /**
     * Déplace le personnage joueur dans la direction spécifiée.
     *
     * @param direction direction du déplacement.
     * @param clavier   objet contenant l'état du clavier.
     */
    private void deplacerPerso(String direction, Clavier clavier) {
        pj.action(direction);
        pj.deplacerPersonnage(labyrinthe);
        resetKey(clavier, direction);
    }

    /**
     * Réinitialise l'état de la touche directionnelle spécifiée.
     *
     * @param clavier   objet contenant l'état du clavier.
     * @param direction direction de la touche à réinitialiser.
     */
    private void resetKey(Clavier clavier, String direction) {
        switch (direction) {
            case Labyrinthe.HAUT:
                clavier.haut = false;
                break;
            case Labyrinthe.BAS:
                clavier.bas = false;
                break;
            case Labyrinthe.GAUCHE:
                clavier.gauche = false;
                break;
            case Labyrinthe.DROITE:
                clavier.droite = false;
                break;
        }
    }

    /**
     * Gère l'attaque du personnage joueur lorsqu'il appuie sur la barre d'espace.
     *
     * @param clavier objet contenant l'état du clavier.
     */
    private void attaquePerso(Clavier clavier) {
        if (clavier.space) {
            for (Monstre m : labyrinthe.getListMonstre()) {
                pj.attaquer(m, new AttaqueAlentour());
            }
            clavier.space = false;
        }
    }

    /**
     * Termine le jeu et ferme l'application.
     */
    private void fermerJeu() {
        System.out.println("Fermeture du jeu");
        timerMaj.shutdownNow();
        Platform.exit();
    }

    /**
     * Méthode pour initialiser le jeu.
     */
    @Override
    public void init() {
        // Initialisation si nécessaire
    }

    /**
     * Vérifie si le jeu est terminé.
     *
     * @return true si le jeu est fini, false sinon.
     */
    @Override
    public boolean etreFini() {
        if (pj.getPossedeAmulette() && pj.etreSurMemeCase(labyrinthe.getDepart().getX(), labyrinthe.getDepart().getY())) {
            System.out.println("Victoire");
            return true;
        } else if (pj.estMort()) {
            System.out.println("Défaite");
            return true;
        }
        return false;
    }

    /**
     * Retourne le labyrinthe.
     *
     * @return le labyrinthe.
     */
    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }
}
