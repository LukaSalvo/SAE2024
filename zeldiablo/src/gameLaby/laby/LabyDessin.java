package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;
import javafx.scene.paint.Color;


import java.io.IOException;

public class LabyDessin implements DessinJeu {
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu game = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // Scenne de fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        // Dessin Laby
        boolean[][] mur = game.getLabyrinthe().murs;
        int x = game.WIDTH/mur.length;
        int y = game.HEIGHT/ mur[0].length;

        for(int line = 0;line<mur.length;line++){
            for(int col =0;col<mur[line].length;col++){
                if(mur[line][col]){
                    gc.setFill(Color.BLACK);
                    gc.fillRect(line*x,col*y,x,y);
                }

            }
        }



        // Dessin Personnage
        DessinerPersonnage(gc, game, x, y);
        // Dessin Mosntre
        DessinerMonstre(game, gc, x, y);

    }

    private static void DessinerPersonnage(GraphicsContext gc, LabyJeu game, int x, int y) {
        gc.setFill(Color.RED);
        Perso perso = game.getLabyrinthe().pj;
        gc.fillOval(perso.getX()* x, perso.getY()* y, x, y);
        System.out.println(game.getLabyrinthe().listMonstre.size());
    }

    private static void DessinerMonstre(LabyJeu game, GraphicsContext gc, int x, int y) {
        for(Monstre m: game.getLabyrinthe().listMonstre){
            gc.setFill(Color.PURPLE);
            gc.fillOval(m.getX()* x, m.getY()* y, x, y);
        }
    }
}
