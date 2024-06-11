package plusCourChemin;

public interface Algorithme {
    /**
     * Résout le problème des plus courts chemins à partir d'un nœud de départ donné.
     * @param g      le graphe représenté par une liste d'adjacence
     * @param depart le nœud de départ
     * @return une instance de Valeur contenant les distances minimales et les parents des nœuds
     */
    Valeur resoudre(Graphe g, String depart);
}
