package plusCourChemin;

import java.util.ArrayList;

/**
 * Classe représentant une liste d'arcs et la méthode pour y ajouter un nouvel arc.
 */
public class Arcs {
    private ArrayList<Arc> arcs;

    /**
     * constructeur par défaut
     */
    public Arcs() {
        arcs = new ArrayList<>();
    }

    /**
     * Méthode qui permet d'ajouter un arc
     * @param a Arc à ajouter
     */
    public void ajouterArc(Arc a) {
        arcs.add(a);
    }

    /**
     * renvoie les arcs de la liste
     * @return ArrayList<Arc> liste des arcs
     */
    public ArrayList<Arc> getArcs() {
        return arcs;
    }
}
