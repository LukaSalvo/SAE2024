package gameLaby.laby;

import javafx.scene.canvas.Canvas;
import moteurJeu.MoteurJeu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonstreTest {

    @Test
    void creerMonstres() throws IOException {
        Labyrinthe laby = new Labyrinthe("labySimple/laby1.txt");
        for(Monstre m : laby.listMonstre){
            int x = m.getX();
            int y = m.getY();
            assertEquals(laby.murs[x][y],false);
            boolean res = false;
            if(laby.pj.getX()==x && laby.pj.getY()==y){
                res = true;
            }
            assertEquals(false,res);
            res = false;
            for(CasePieges c : laby.casesPieges){
                if(c.etreSurMemeCase(x,y)){
                    res =true;
                }
            }
            assertEquals(false,res);
        }
    }
}