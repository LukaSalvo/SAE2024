package plusCourChemin;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra implements Algorithme {
    /**
     * Résout le problème des plus courts chemins à partir d'un nœud de départ donné avec l'algorithme de Dijkstra.
     * @param g      le graphe représenté par une liste d'adjacence
     * @param depart le nœud de départ
     * @return une instance de Valeur contenant les distances minimales et les parents des nœuds
     */
    public Valeur resoudre(Graphe g, String depart) {
        List<String> q = new ArrayList<>(g.listeNoeuds());
        int nbNoeuds = q.size();
        Valeur v = new Valeur();

        for (int i = 0; i < nbNoeuds; i++) {
            v.setValeur(q.get(i), Double.MAX_VALUE);
        }
        v.setValeur(depart, 0);

        int iterations = 0;
        while (!q.isEmpty()) {
            int indMin = minimun(v, q);
            String min = q.get(indMin);
            q.remove(indMin);
            List<Arc> suivants = g.suivants(min);
            for (Arc arc : suivants) {
                double d = v.getValeur(min) + arc.getCout();
                if (d < v.getValeur(arc.getDest())) {
                    v.setValeur(arc.getDest(), d);
                    v.setParent(arc.getDest(), min);
                }
                iterations++;
            }
        }
        return v;
    }

    /**
     * renvoie le nœud minimum dans le graphe d'un sous-ensemble de nœuds
     * @param v Valeur graphe à lire
     * @param q List<String> sous-ensemble de nœud qui n'ont pas été ajoutées à Dijkstra et qui y sont adjacents
     * @return int la valeur minimum dans les sommets adjacents à Dijkstra
     */
    public int minimun(Valeur v, List<String> q) {
        double min = v.getValeur(q.get(0));
        int indMin = 0;
        double val;
        for (int i = 1; i < q.size(); i++) {
            val = v.getValeur(q.get(i));
            if (min > val) {
                min = val;
                indMin = i;
            }
        }
        return indMin;
    }
}


//Entrees :
//G un graphe orienté avec une pondération positive des arcs (coût ou poids)
//A un sommet (départ) de G

//Début
//Q <- {} // utilisation d'une liste de noeuds à traiter
//Pour chaque sommet v de G faire
//      v.valeur <- Infini
//      v.parent <- Indéfini
//      Q <- Q U {v} // ajouter le sommet v à la liste Q
//Fin Pour

//A.valeur <- 0
//Tant que Q est un ensemble non vide faire
//      u <- un sommet de Q telle que u.valeur est minimal
//      // enlever le sommet u de la liste Q
//      Q <- Q \ {u}
//      Pour chaque sommet v de Q tel que l’arc (u,v) existe faire
//              d <- u.valeur + poids(u,v)
//              Si d < v.valeur
//              // le chemin est plus interessant
//              Alors v.valeur <- d
//                  v.parent <- u
//              Fin Si
//      Fin Pour
//Fin Tant que
// Fin
