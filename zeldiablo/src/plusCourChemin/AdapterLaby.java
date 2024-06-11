package plusCourChemin;

import gameLaby.laby.Labyrinthe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterLaby implements Graphe{

    private Labyrinthe laby;


    public AdapterLaby(Labyrinthe laby) {
        this.laby = laby;
    }

    @Override
    public List<String> listeNoeuds() {
        List<String> noeuds = new ArrayList<>();
        for (int line =0;line<laby.getLength();line++){
            for (int col =0;col<laby.getLengthY();col++){
                if (!laby.getMur(line,col)){
                    noeuds.add(line+","+col);
                }
            }
        }
        return noeuds;
    }

    @Override
    public List<Arc> suivants(String n) {
        List<Arc> arcs = new ArrayList<>();
        String[] coord = n.split(",");
        int[] coordInt = {Integer.parseInt(coord[0]),Integer.parseInt(coord[1])};
        int x = coordInt[0];
        int y = coordInt[1];

        if(laby.estDansLimiteLaby(x-1,y) && !laby.getMur(x-1,y)){
            arcs.add(new Arc((x-1)+","+y,1));
        }
        if(laby.estDansLimiteLaby(x+1,y) && !laby.getMur(x+1,y)){
            arcs.add(new Arc((x+1)+","+y,1));
        }
        if(laby.estDansLimiteLaby(x,y-1) && !laby.getMur(x,y-1)){
            arcs.add(new Arc(x+","+(y-1),1));
        }
        if(laby.estDansLimiteLaby(x,y+1) && !laby.getMur(x,y+1)){
            arcs.add(new Arc(x+","+(y+1),1));
        }

        return arcs;
    }


}
