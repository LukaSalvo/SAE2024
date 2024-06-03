package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char MONSTRE = 'M';
    public static final char VIDE = '.';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * attribut du personnage
     */
    public Perso pj;
    public ArrayList<Monstre> listMonstre = new ArrayList<>();

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case MONSTRE:
                        // ajoute monstre
                        this.murs[colonne][numeroLigne] = false;
                        this.listMonstre.add(new Monstre(colonne, numeroLigne));
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Perso(colonne, numeroLigne);
                        break;
                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }
            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }
        // Creation des monstre
        this.creerMonstre(3);
        // ferme fichier
        bfRead.close();
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        // case courante
        int[] courante = {this.pj.x, this.pj.y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]] && !this.monstresPresent(suivante[0],suivante[1])) {
            // on met a jour personnage
            this.pj.x = suivante[0];
            this.pj.y = suivante[1];
            for(int i =0; i<this.listMonstre.size();i++){
                this.deplacerMonstre(action,this.listMonstre.get(i));
            }

        }
    }

    /**
     * deplace le Monstre en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerMonstre(String action,Monstre monstre) {
        int[] courante = {monstre.getX(), monstre.getY()};
        String[] actions = {HAUT,BAS,GAUCHE,DROITE};
        Random rand = new Random();
        int ind = rand.nextInt(actions.length);
        int[] suivante = getSuivant(courante[0], courante[1], actions[ind]);
        int[] suivantePerso = getSuivant(pj.x,pj.y,action);

        // si c'est pas un mur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]]  && !this.pj.etrePresent(suivante[0],suivante[1]) && this.murs[suivantePerso[0]][suivantePerso[1]] && !this.monstresPresent(suivante[0],suivante[1])) {
            // on met a jour Mostre
            monstre.x = suivante[0];
            monstre.y = suivante[1];
        }
    }

    public void creerMonstre(int nb){
        Random rand = new Random();
        int poxX;
        int poxY;
        for(int i=0;i<nb;i++){
            poxX = rand.nextInt(this.murs.length);
            poxY = rand.nextInt(this.murs[0].length);
            while (murs[poxX][poxY] && this.pj.etrePresent(poxX,poxY) && monstresPresent(poxX,poxY)){
                poxX = rand.nextInt(this.murs.length);
                poxY = rand.nextInt(this.murs[0].length);
            }
            this.listMonstre.add(new Monstre(poxX,poxY));
        }
    }

    public boolean monstresPresent(int dx,int dy){
        boolean res = true;
        int ind =0;
        int size = this.listMonstre.size();
        while (ind<size && this.listMonstre.get(ind).etrePresent(dx,dy)){
            ind++;
        }
        return ind==size;
    }



    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     *
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }
}
