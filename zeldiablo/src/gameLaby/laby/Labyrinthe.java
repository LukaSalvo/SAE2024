package gameLaby.laby;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
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
    public static final char C = 'c';

    public static final char A = 'a';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";


    public static final int NBMONSTRE = 3;


    /**
     * attribut des personnages
     */
    private Perso pj;
    private ArrayList<Monstre> listMonstre = new ArrayList<>();
    private Coordonnees depart;

    /**
     * les murs du labyrinthe
     */
    private boolean[][] murs;

    /**
     * Liste de case pieges
     */
    public List<CasePieges> casesPieges;

    private Amulette amu;


    /**
     * Constructeur de la classe Labyrinthe
     *
     * @param murs        les murs du labyrinthe
     * @param pj          le personnage
     * @param casesPieges les cases pièges
     * @param listMonstre la liste des monstres
     * @param amu         l'amulette
     * @param depart      les coordonnées de départ
     */
    public Labyrinthe(boolean[][] murs, Perso pj, List<CasePieges> casesPieges, List<Monstre> listMonstre, Amulette amu, Coordonnees depart) {
        this.murs = murs;
        this.pj = pj;
        this.casesPieges = casesPieges;
        this.listMonstre = new ArrayList<>(listMonstre);
        this.amu = amu;
        this.depart = depart;
    }

    public Labyrinthe(){
        Labyrinthe l = LabyrintheLoader.chargerLabyrintheAleatoire();
        this.murs = l.murs;
        this.pj = l.pj;
        this.casesPieges = l.casesPieges;
        this.listMonstre = l.listMonstre;
        this.amu = l.amu;
        this.depart = l.depart;
        creerMonstres(NBMONSTRE);
    }

    /**
     * Constructeur qui charge le labyrinthe avec un fichier
     *
     * @param nom nom du fichier de labyrinthe
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        Labyrinthe loadedLabyrinthe = LabyrintheLoader.chargerLabyrinthe(nom);
        this.murs = loadedLabyrinthe.murs;
        this.pj = loadedLabyrinthe.pj;
        this.casesPieges = loadedLabyrinthe.casesPieges;
        this.listMonstre = loadedLabyrinthe.listMonstre;
        this.amu = loadedLabyrinthe.amu;
        this.depart = loadedLabyrinthe.depart;
    }


    /**
     * Vérifie si le personnage est sur une case piège et applique les effets le cas échéant.
     *
     * @param suivante Les coordonnées de la case suivante où le personnage se déplace.
     * @param p        Le personnage en déplacement.
     */
    public void estSurCasePiege(int[] suivante, Personnage p) {
        for (CasePieges c : casesPieges) {
            if (c.etreSurMemeCase(suivante[0], suivante[1])) {
                c.setEtrePasserDessus(true);
                p.perdrePv(CasePieges.getDegats());
            }
        }
    }


    /**
     * Met à jour l'état des monstres (supprime les monstres morts)
     */
    public void majEtatMonstre() {
        Iterator<Monstre> iterator = listMonstre.iterator();
        while (iterator.hasNext()) {
            Monstre m = iterator.next();
            if (m.estMort()) {
                iterator.remove(); // Supprimer l'élément actuel en utilisant l'itérateur
            }
        }
    }


    /**
     * Vérifie si les coordonnées sont dans les limites du labyrinthe
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si les coordonnées sont valides, sinon false
     */
    public boolean estDansLimiteLaby(int x, int y) {
        return x >= 0 && x < murs.length && y >= 0 && y < murs[0].length;
    }

    /**
     * Vérifie si une entité (personnage ou monstre) est présente à la case donnée
     *
     * @param x coordonnée x de la case
     * @param y coordonnée y de la case
     * @return true si une entité est présente, sinon false
     */
    public boolean personnagesPresent(int x, int y) {
        if (pj.etrePresent(x, y)) return true;
        for (Monstre m : listMonstre) {
            if (m.etrePresent(x, y)) return true;
        }
        return false;
    }


    /**
     * Crée un nombre donné de monstres à des positions valides
     *
     * @param nb le nombre de monstres à créer
     */
    public void creerMonstres(int nb) {
        Random rand = new Random();
        for (int i = 0; i < nb; i++) {
            int posX = rand.nextInt(murs.length);
            int posY = rand.nextInt(murs[0].length);
            while (!VerfiPositonValide(posX, posY)) {
                posX = rand.nextInt(murs.length);
                posY = rand.nextInt(murs[0].length);
            }
            this.listMonstre.add(new Monstre(posX, posY));
        }
    }

    public void creerCasePiege(int nb) {
        Random rand = new Random();
        for (int i = 0; i < nb; i++) {
            int posX = rand.nextInt(murs.length);
            int posY = rand.nextInt(murs[0].length);
            while (!VerfiPositonValide(posX, posY)) {
                posX = rand.nextInt(murs.length);
                posY = rand.nextInt(murs[0].length);
            }
            this.casesPieges.add(new CasePieges(posX, posY));
        }
    }

    /**
     * Vérifie si une position de génération pour un monstre est valide.
     *
     * @param posX La coordonnée X de la position à vérifier.
     * @param posY La coordonnée Y de la position à vérifier.
     * @return true si la position est valide pour la génération d'un monstre, sinon false.
     */
    private boolean VerfiPositonValide(int posX, int posY) {
        // Vérifie si la position est un mur ou si un personnage est présent
        if (murs[posX][posY] || personnagesPresent(posX, posY)) {
            return false;
        }
        if (amu.etreSurMemeCase(posX, posY)) {
            return false;
        }
        // Vérifie si la position est sur une case piège
        if (casesPieges != null) {
            for (CasePieges piege : casesPieges) {
                if (piege.etreSurMemeCase(posX, posY)) {
                    return false;
                }
            }
        }
        return true;
    }


    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return taille selon Y
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return taille selon X
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (x,y)
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si mur, sinon false
     */
    public boolean getMur(int x, int y) {
        return this.murs[x][y];
    }

    /**
     * Renvoie la matrice représentant l'état des murs dans le labyrinthe.
     *
     * @return La matrice des murs.
     */
    public boolean[][] getMurs() {
        return this.murs;
    }

    /**
     * Renvoie le Perso pj du labyrinthe.
     *
     * @return pj.
     */
    public Perso getPj() {
        return this.pj;
    }

    /**
     * Renvoie la liste des monstres présents dans le labyrinthe.
     *
     * @return La liste des monstres.
     */
    public List<Monstre> getListMonstre() {
        return this.listMonstre;
    }

    /**
     * Renvoie la liste des cases pièges dans le labyrinthe.
     *
     * @return La liste des cases pièges.
     */
    public List<CasePieges> getCasesPieges() {
        return this.casesPieges;
    }

    /**
     * Renvoie l'amulette du labyrinthe.
     *
     * @return
     */
    public Amulette getAmulette() {
        return this.amu;
    }
    public void setAmulette(Amulette amu) {
        this.amu = amu;
    }

    /**
     * Renvoie les coordonnées de départ du labyrinthe.
     */
    public Coordonnees getDepart() {
        return this.depart;
    }

}
