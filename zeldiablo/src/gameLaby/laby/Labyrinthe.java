package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
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

    /**
     * les murs du labyrinthe
     */
    private boolean[][] murs;

    /**
     * Liste de case pieges
     */
    public List<CasePieges> casesPieges;


    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        try (BufferedReader bfRead = new BufferedReader(new FileReader(nom))) {
            int nbLignes = Integer.parseInt(bfRead.readLine());
            int nbColonnes = Integer.parseInt(bfRead.readLine());

            this.murs = new boolean[nbColonnes][nbLignes];
            this.pj = null;
            this.casesPieges = new ArrayList<>();

            String ligne;
            int numeroLigne = 0;

            while ((ligne = bfRead.readLine()) != null) {
                for (int colonne = 0; colonne < ligne.length(); colonne++) {
                    char c = ligne.charAt(colonne);
                    switch (c) {
                        case MUR:
                            this.murs[colonne][numeroLigne] = true;
                            break;
                        case VIDE:
                            this.murs[colonne][numeroLigne] = false;
                            break;
                        case C:
                            this.murs[colonne][numeroLigne] = false;
                            this.casesPieges.add(new CasePieges(colonne, numeroLigne));
                            break;
                        case MONSTRE:
                            this.murs[colonne][numeroLigne] = false;
                            this.listMonstre.add(new Monstre(colonne, numeroLigne));
                            break;
                        case PJ:
                            this.murs[colonne][numeroLigne] = false;
                            this.pj = new Perso(colonne, numeroLigne);
                            break;
                        default:
                            throw new Error("caractere inconnu " + c);
                    }
                }
                numeroLigne++;
            }

            this.creerMonstres(NBMONSTRE);
        }
    }

    /**
     * Déplace un personnage selon son type de déplacement et gère les répercussions.
     *
     * @param p Le personnage à déplacer.
     */
    public void deplacerPersonnage(Personnage p) {
        TypeDeplacement t = p.getType();
        t.deplacer(p, this);
        repercution(p);
    }

    /**
     * Gère les répercussions du déplacement d'un personnage, telles que les pièges et les attaques.
     *
     * @param p Le personnage pour lequel les répercussions doivent être gérées.
     */
    public void repercution(Personnage p) {
        estSurCasePiege(p.getCoordonnees(), p);
        if (p instanceof Monstre) {
            if (pj.estAutour(p)) {
                p.attaquer(pj, new AttaqueAlentour());
            }
        }
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
     * Vérifie si le déplacement est possible (pas de mur et pas d'entité déjà présente)
     *
     * @param x coordonnée x de la case cible
     * @param y coordonnée y de la case cible
     * @return true si le déplacement est possible, sinon false
     */
    public boolean deplacementPossible(int x, int y) {
        return estValide(x, y) && !murs[x][y] && !personnagePresent(x, y);
    }

    /**
     * Vérifie si les coordonnées sont dans les limites du labyrinthe
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si les coordonnées sont valides, sinon false
     */
    private boolean estValide(int x, int y) {
        return x >= 0 && x < murs.length && y >= 0 && y < murs[0].length;
    }

    /**
     * Vérifie si une entité (personnage ou monstre) est présente à la case donnée
     *
     * @param x coordonnée x de la case
     * @param y coordonnée y de la case
     * @return true si une entité est présente, sinon false
     */
    public boolean personnagePresent(int x, int y) {
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
            while (!generationPositionValide(posX, posY)) {
                posX = rand.nextInt(murs.length);
                posY = rand.nextInt(murs[0].length);
            }
            this.listMonstre.add(new Monstre(posX, posY));
        }
    }

    /**
     * Vérifie si une position de génération pour un monstre est valide.
     *
     * @param posX La coordonnée X de la position à vérifier.
     * @param posY La coordonnée Y de la position à vérifier.
     * @return true si la position est valide pour la génération d'un monstre, sinon false.
     */
    private boolean generationPositionValide(int posX, int posY) {
        // Vérifie si la position est un mur ou si un personnage est présent
        if (murs[posX][posY] || personnagePresent(posX, posY)) {
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


    public void recupererAmulette(){
        if(pj.etreSurMemeCase(1,1)){
            pj.setPossedeAmulette(true);
            System.out.println(true);
        } else {
            System.out.println(false);
        }
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

}
