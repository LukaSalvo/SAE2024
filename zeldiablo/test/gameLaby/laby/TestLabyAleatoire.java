package gameLaby.laby;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plusCourChemin.AdapterLaby;
import plusCourChemin.Algorithme;
import plusCourChemin.Dijkstra;
import plusCourChemin.Valeur;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class TestLabyAleatoire {
    @Test
    public void testChargerLaby(){
        Labyrinthe laby = new Labyrinthe();
        DeplacementIntelligent dep = new DeplacementIntelligent();
        Personnage pj = laby.getPj();
        Algorithme al = new Dijkstra();
        AdapterLaby l = new AdapterLaby(laby);
        Valeur v = al.resoudre(l,pj.getX()+" "+pj.getY());
        List<String> courtChemin = v.calculerChemin(laby.getAmulette().getX()+""+laby.getAmulette().getY());
        boolean res = false;
        if(courtChemin.size()>=1){
            res= true;
        }
        assertEquals(res,true);
    }
    @Test
    public void testCharger(){
        Labyrinthe laby = new Labyrinthe();
        boolean [][]murs = laby.getMurs();
        boolean res = false;
        for(int i = 0 ; i<murs.length;i++){
            if(murs[0][i]){
                res = true;
            }else{
                res = false;
                break;
            }
        }
        assertEquals(true,res);
        for(int i = 0 ; i<murs.length;i++){
            if(murs[i][0]){
                res = true;
            }else{
                res = false;
                break;
            }
        }
        assertEquals(true,res);
        for(int i = 0 ; i<murs.length;i++){
            if(murs[i][murs.length-1]){
                res = true;
            }else{
                res = false;
                break;
            }
        }
        assertEquals(true,res);
        for(int i = 0 ; i<murs.length;i++){
            if(murs[murs.length-1][i]){
                res = true;
            }else{
                res = false;
                break;
            }
        }
        assertEquals(true,res);
    }
}
