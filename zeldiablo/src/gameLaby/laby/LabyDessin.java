package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

import java.util.List;

public class LabyDessin implements DessinJeu {

    public static final Color vert = Color.GREEN;
    public static final Color orange = Color.ORANGE;
    public static final Color jaune = Color.YELLOW;
    public static final Color rouge = Color.RED;

    public static final Color bleuNoir = Color.DARKBLUE;
    public static final Color bleu = Color.BLUE;
    public static final Color bleuCiel = Color.ALICEBLUE;
    public static final Color noir = Color.BLACK;

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

    private void dessinerPersonnage(GraphicsContext gc, Perso perso, int x, int y) {
        gc.setFill(getPersonnageColor(perso.getPv()));
        dessinerEntite(gc, perso.getX(), perso.getY(), x, y, perso.getPv());
    }

    private void dessinerMonstres(GraphicsContext gc, List<Monstre> monstres, int x, int y) {
        for (Monstre monstre : monstres) {
            gc.setFill(getMonstreColor(monstre.getPv()));
            dessinerEntite(gc, monstre.getX(), monstre.getY(), x, y, monstre.getPv());
        }
    }

    private void dessinerCasesPieges(GraphicsContext gc, List<CasePieges> casesPieges, int x, int y) {
        for (CasePieges c : casesPieges) {
            gc.setFill(Color.BROWN);
            gc.fillRect(c.getPosX() * x, c.getPosY() * y, x, y);
        }
    }

    private void dessinerEntite(GraphicsContext gc, int x, int y, int cellWidth, int cellHeight, int pv) {
        gc.fillOval(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(20));
        gc.fillText(String.valueOf(pv), x * cellWidth + cellWidth / 4, y * cellHeight + cellHeight / 2);
    }

    private Color getPersonnageColor(int pv) {
        if (pv >= Perso.POINTDEVIEPERSOENTIER) {
            return vert;
        } else if (pv > Perso.POINTDEVIEPERSO3QUART) {
            return jaune;
        } else if (pv > Perso.POINTDEVIEPERSODEMI) {
            return orange;
        } else if (pv > Perso.POINTDEVIEPERSO1QUART) {
            return rouge;
        } else {
            return noir;
        }
    }

    private Color getMonstreColor(int pv) {
        if (pv > 75) {
            return bleuCiel;
        } else if (pv > 50) {
            return bleu;
        } else if (pv > 25) {
            return bleuNoir;
        } else {
            return noir;
        }
    }
}



