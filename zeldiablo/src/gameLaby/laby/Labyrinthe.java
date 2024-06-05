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
     * attribut du personnage
     */




    public Perso pj;
    public ArrayList<Monstre> listMonstre = new ArrayList<>();

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * Liste de case pieges
     */
    public List<CasePieges> casesPieges;

    /**
     * retourne la case suivante selon une action
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                y--;
                break;
            case BAS:
                y++;
                break;
            case DROITE:
                x++;
                break;
            case GAUCHE:
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        return new int[]{x, y};
    }

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
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs et pieges
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        int[] suivante = getSuivant(this.pj.x, this.pj.y, action);
        if (deplacementPossible(suivante[0], suivante[1])) {
            estSurCasePiege(suivante,pj);
            this.pj.x = suivante[0];
            this.pj.y = suivante[1];
        }
    }

    public void estSurCasePiege(int[] suivante, Personnage p) {
        for (CasePieges c : casesPieges) {
            if (c.etreSurMemeCase(suivante[0], suivante[1])) {
                p.perdrePv(CasePieges.getDegats());
            }
        }
    }

    /**
     * deplace le Monstre de façon aléatoire.
     * gere la collision avec les murs et les pieges
     *
     * @param monstre le monstre à déplacer
     */
    public void deplacerMonstre(Monstre monstre) {
        String[] actions = {HAUT, BAS, GAUCHE, DROITE};
        Random rand = new Random();
        int[] suivante;
        boolean deplacementPossible = false;

        while (!deplacementPossible) {
            suivante = getSuivant(monstre.getX(), monstre.getY(), actions[rand.nextInt(actions.length)]);
            if (deplacementPossible(suivante[0], suivante[1])) {
                estSurCasePiege(suivante,monstre);
                monstre.x = suivante[0];
                monstre.y = suivante[1];
                deplacementPossible = true;
            }
            majEtatMonstre();
        }

    }

    /**
     * Met à jour l'état des monstres (supprime les monstres morts)
     */
    public void majEtatMonstre() {
        Iterator<Monstre> iterator = listMonstre.iterator();
        while(iterator.hasNext()){
            Monstre m = iterator.next();
            if(m.estMort()){
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
    private boolean deplacementPossible(int x, int y) {
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
            while (murs[posX][posY] || personnagePresent(posX, posY)) {
                posX = rand.nextInt(murs.length);
                posY =   rand.nextInt(murs[0].length);
            }
            this.listMonstre.add(new Monstre(posX, posY));
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
}
