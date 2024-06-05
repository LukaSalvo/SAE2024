package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

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
        int x = game.WIDTH / game.getLabyrinthe().murs.length;
        int y = game.HEIGHT / game.getLabyrinthe().murs[0].length;

        // Dessin du labyrinthe
        dessinerLabyrinthe(gc, game.getLabyrinthe().murs, x, y);

        // Dessin du personnage
        dessinerPersonnage(gc, game.getLabyrinthe().pj, x, y);

        // Dessin des monstres
        dessinerMonstres(gc, game.getLabyrinthe().listMonstre, x, y);

        // Dessin des cases pièges
       dessinerCasesPieges(gc, game.getLabyrinthe().casesPieges, x, y);
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
        dessinerEntite(gc, perso.getX(), perso.getY(), x, y, perso.getPv());
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
                dessinerEntite(gc, monstre.getX(), monstre.getY(), x, y, monstre.getPv());
            }
        }
    }

    /**
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
            gc.fillRect(c.getPosX() * x, c.getPosY() * y, x, y);
            }
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
    private void dessinerEntite(GraphicsContext gc, int x, int y, int cellWidth, int cellHeight, int pv) {
        gc.fillOval(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(20));
        gc.fillText(String.valueOf(pv), x * cellWidth + cellWidth / 4, y * cellHeight + cellHeight / 2);
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

    /**
     * Methode pour obtenir la couleur du monstre, en fonction de ses points de vie
     * @param pv points de vie
     * @return
     */
    private Color getMonstreColor(int pv) {
        if (pv >= Monstre.PV_MONSTRE_ENTIER) {
            return bleuCiel;
        } else if (pv >= Monstre.Pv_MONSTRE_DEMI) {
            return bleu;
        } else{
            return bleuNoir;
        }
    }
}



