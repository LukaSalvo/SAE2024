package plusCourChemin;

/**
 * Classe représentant un arc allant jusqu'au sommet dest avec un coût de cout dans un graphe.
 */
public class Arc {
    private String dest;
    private double cout;

    /**
     * constructeur avec destination et cout
     * @param dest String destination de l'arc
     * @param cout double coût de l'arc
     */
    public Arc(String dest, double cout){
        this.dest = dest;
        this.cout = cout;
    }

    /**
     * getter du coût
     * @return double le coût de l'arc
     */
    public double getCout() {
        return cout;
    }

    /**
     * getter de la destination
     * @return String la destination de l'arc
     */
    public String getDest() {
        return dest;
    }
}
