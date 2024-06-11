package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Classe pour dessiner le jeu du labyrinthe
 */
public class LabyDessin implements DessinJeu {
    /**
     * Constantes pour les couleurs
     */
    public static final Color vert = Color.GREEN;
    public static final Color orange = Color.ORANGE;
    public static final Color jaune = Color.YELLOW;
    public static final Color rouge = Color.RED;

    public static final Color bleuNoir = Color.DARKBLUE;
    public static final Color bleu = Color.BLUE;
    public static final Color bleuCiel = Color.SKYBLUE;
    public static final Color noir = Color.BLACK;


    /**
     * Methode pour dessiner le jeu
     * @param jeu jeu a afficher
     * @param canvas canvas dans lequel dessiner l'etat du jeu
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu game = (LabyJeu) jeu;
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // Scenne de fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Calcul des tailles des cellules
        int x = game.WIDTH / game.getLabyrinthe().getMurs().length;
        int y = game.HEIGHT / game.getLabyrinthe().getMurs()[0].length;

        // Dessin du labyrinthe
        dessinerLabyrinthe(gc, game.getLabyrinthe().getMurs(), x, y);
        //Dessin cases pièges
        dessinerCasesPieges(gc, game.getLabyrinthe().casesPieges, x, y);
        // Dessin du personnage
        dessinerPersonnage(gc, game.getLabyrinthe().getPj(), x, y);

        // Dessin des monstres
        dessinerMonstres(gc, game.getLabyrinthe().getListMonstre(), x, y);
        //Dessin amulette
        dessinerAmulette(gc,game.getLabyrinthe().getAmulette(),game.getLabyrinthe().getPj(),x,y);

    }

    /**
     * Methode pour dessiner le labyrinthe
     * @param gc objet GraphicsContext
     * @param murs tableau de booleens representant les murs
     * @param x position x
     * @param y position y
     */
    private void dessinerLabyrinthe(GraphicsContext gc, boolean[][] murs, int x, int y) {
        for (int line = 0; line < murs.length; line++) {
            for (int col = 0; col < murs[line].length; col++) {
                if (murs[line][col]) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(line * x, col * y, x, y);
                }
            }
        }
    }

    /**
     * Methode pour dessiner le personnage
     * @param gc objet GraphicsContext
     * @param perso objet de la classe Perso
     * @param x position x
     * @param y position y
     */
    private void dessinerPersonnage(GraphicsContext gc, Perso perso, int x, int y) {

        gc.setFill(getPersonnageColor(perso.getPv()));
        dessinerEntite(gc, perso.getX(), perso.getY(), x, y, perso.getPv(),new Image("file:img/Hero.png"));
    }

    /**
     * Methode pour dessiner les monstres
     * @param gc objet GraphicsContext
     * @param monstres liste des monstres
     * @param x position x
     * @param y position y
     */
    private void dessinerMonstres(GraphicsContext gc, List<Monstre> monstres, int x, int y) {
        for (Monstre monstre : monstres) {
            if(!monstre.estMort()) {
                gc.setFill(getMonstreColor(monstre.getPv()));
                dessinerEntite(gc, monstre.getX(), monstre.getY(), x, y, monstre.getPv(),new Image("file:img/skeletonlord_up_2.png"));
            }
        }
    }

    /**qq
     * Methode pour dessiner les cases pièges
     * @param gc objet GraphicsContext
     * @param casesPieges liste des cases pièges
     * @param x position x
     * @param y position y
     */
    private void dessinerCasesPieges(GraphicsContext gc, List<CasePieges> casesPieges, int x, int y) {
        for (CasePieges c : casesPieges) {
            if(c.getEtrePasserDessus()){
                gc.setFill(Color.BROWN);
                gc.fillRect(c.getX() * x, c.getY() * y, x, y);
            }
        }
    }

    /**
     * Methode pour dessiner l'amulette
     * @param gc objet GraphicsContext
     * @param a objet de la classe Amulette
     * @param pj objet de la classe Perso
     * @param x position x
     * @param y position y
     */
    public void dessinerAmulette(GraphicsContext gc, Amulette a,Perso pj , int x , int y ){
        if(!pj.getPossedeAmulette()){
            gc.setFill(Color.YELLOW);
            gc.fillOval(a.getX()*x,a.getY()*y,x,y);
        }
    }

    /**
     * Methode pour dessiner une entite
     * @param gc objet GraphicsContext
     * @param x position x
     * @param y position y
     * @param cellWidth largeur de la cellule
     * @param cellHeight    hauteur de la cellule
     * @param pv    points de vie
     */
    private void dessinerEntite(GraphicsContext gc, int x, int y, int cellWidth, int cellHeight, int pv,Image i ) {
        gc.setFill(getPersonnageColor(pv));
        gc.fillRect(x*cellWidth,y*cellHeight-10,getPersonnage(pv)*cellHeight/20,10);
        gc.drawImage(i,x*cellWidth,y*cellHeight,cellWidth,cellHeight);
    }

    /**
     * Methode pour obtenir la couleur de l'entite, en fonction de ses points de vie
     * @param pv points de vie
     * @return
     */
    private Color getPersonnageColor(int pv) {
        if (pv >= Perso.PV_ENTIER) {
            return vert;
        } else if (pv > Perso.PV_3_QUART) {
            return jaune;
        } else if (pv > Perso.PV_DEMI) {
            return orange;
        } else if (pv > Perso.PV_1_QUART) {
            return rouge;
        } else {
            return noir;
        }
    }
    private int getPersonnage(int pv){
        Color c = getPersonnageColor(pv);
        if (c.equals(vert)) {
            return 35;
        } else if (c.equals(jaune)) {
            return 25;
        } else if (c.equals(orange)) {
            return 15;
        } else if (c.equals(rouge)) {
            return 5;
        }
        return 0;
    }

    /**
     * Methode pour obtenir la couleur du monstre, en fonction de ses points de vie
     * @param pv points de vie
     * @return
     */
    private Color getMonstreColor(int pv) {
        if (pv >= Monstre.PV_MONSTRE_ENTIER) {
            return bleuCiel;
        } else if (pv >= Monstre.PV_MONSTRE_DEMI) {
            return bleu;
        } else{
            return bleuNoir;
        }
    }
}
