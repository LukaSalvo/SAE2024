package plusCourChemin;

import java.util.List;

/**
 * interface qui définit les méthodes nécessaires à l'implémentation d'un graphe
 */
public interface Graphe {
    public List<String> listeNoeuds();
    public List<Arc> suivants(String n);
}
