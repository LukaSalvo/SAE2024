package gameLaby.laby;

import plusCourChemin.AdapterLaby;
import plusCourChemin.Algorithme;
import plusCourChemin.Dijkstra;
import plusCourChemin.Valeur;

import java.util.List;

import static gameLaby.laby.Labyrinthe.*;

public class DeplacementIntelligent implements TypeDeplacement{

    @Override
    public void deplacer(Personnage p, Labyrinthe laby) {
        String[] actions = {HAUT, BAS, GAUCHE, DROITE};
        Algorithme Dijsktra = new Dijkstra();
        AdapterLaby l = new AdapterLaby(laby);
        Valeur v = Dijsktra.resoudre(l, p.getX()+","+p.getY());
        List<String> plusCourtChemin = v.calculerChemin(laby.getPj().getX()+","+laby.getPj().getY());
        if(plusCourtChemin.size() > 1){
            String[] coord = plusCourtChemin.get(1).split(",");
            int[] suivante = {Integer.parseInt(coord[0]),Integer.parseInt(coord[1])};
            if (p.peutBouger(laby, suivante[0], suivante[1])) {
                p.setPosition(suivante);
            }
        }
    }
}
