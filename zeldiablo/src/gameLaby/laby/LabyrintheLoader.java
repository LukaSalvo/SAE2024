package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LabyrintheLoader {

    public static final int NB_LIGNES = 20;
    public static final int NB_COLONNES = 20;

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @throws IOException probleme a la lecture / ouverture
     */
    public static Labyrinthe chargerLabyrinthe(String nom) throws IOException {
        try (BufferedReader bfRead = new BufferedReader(new FileReader(nom))) {
            int nbLignes = Integer.parseInt(bfRead.readLine());
            int nbColonnes = Integer.parseInt(bfRead.readLine());

            boolean[][] murs = new boolean[nbColonnes][nbLignes];
            Perso pj = null;
            List<CasePieges> casesPieges = new ArrayList<>();
            List<Monstre> listMonstre = new ArrayList<>();
            Amulette amu = null;
            Coordonnees depart = null;

            String ligne;
            int numeroLigne = 0;

            while ((ligne = bfRead.readLine()) != null) {
                for (int colonne = 0; colonne < ligne.length(); colonne++) {
                    char c = ligne.charAt(colonne);
                    switch (c) {
                        case Labyrinthe.MUR:
                            murs[colonne][numeroLigne] = true;
                            break;
                        case Labyrinthe.VIDE:
                            murs[colonne][numeroLigne] = false;
                            break;
                        case Labyrinthe.C:
                            murs[colonne][numeroLigne] = false;
                            casesPieges.add(new CasePieges(colonne, numeroLigne));
                            break;
                        case Labyrinthe.MONSTRE:
                            murs[colonne][numeroLigne] = false;
                            listMonstre.add(new Monstre(colonne, numeroLigne));
                            break;
                        case Labyrinthe.PJ:
                            murs[colonne][numeroLigne] = false;
                            pj = new Perso(colonne, numeroLigne);
                            depart = new Coordonnees(colonne, numeroLigne);
                            break;
                        case Labyrinthe.A:
                            murs[colonne][numeroLigne] = false;
                            amu = new Amulette(colonne, numeroLigne);
                            break;
                        default:
                            throw new Error("caractere inconnu " + c);
                    }
                }
                numeroLigne++;
            }

            Labyrinthe labyrinthe = new Labyrinthe(murs, pj, casesPieges, listMonstre, amu, depart);
            labyrinthe.creerMonstres(Labyrinthe.NBMONSTRE);
            return labyrinthe;
        }
    }


    public static Labyrinthe chargerLabyrintheAleatoire() {
        int nbLignes = NB_LIGNES;
        int nbColonnes = NB_COLONNES;
        boolean[][] murs = new boolean[nbLignes][nbColonnes];
        Random rand = new Random();

        // Initialiser les bords comme des murs
        for (int i = 0; i < nbLignes; i++) {
            murs[i][0] = true;
            murs[i][nbColonnes - 1] = true;
        }
        for (int j = 0; j < nbColonnes; j++) {
            murs[0][j] = true;
            murs[nbLignes - 1][j] = true;
        }

        // Choisir un point de départ non sur les bords
        int x = rand.nextInt(nbLignes - 2) + 1;
        int y = rand.nextInt(nbColonnes - 2) + 1;
        murs[x][y] = true;

        int xAmu = x;
        int yAmu = y;

        ArrayList<int[]> chemin = new ArrayList<>();

        ArrayList<int[]> sauvegarde = new ArrayList<>();
        chemin.add(new int[]{x, y});

        while (!chemin.isEmpty()) {
            int[] posActuelle = chemin.get(chemin.size() - 1);
            x = posActuelle[0];
            y = posActuelle[1];

            ArrayList<Integer> voisins = new ArrayList<>();
            if (x - 2 > 0 && !murs[x - 2][y]) {
                voisins.add(0);
            }
            if (x + 2 < nbLignes - 1 && !murs[x + 2][y]) {
                voisins.add(1);
            }
            if (y - 2 > 0 && !murs[x][y - 2]) {
                voisins.add(2);
            }
            if (y + 2 < nbColonnes - 1 && !murs[x][y + 2]) {
                voisins.add(3);
            }

            if (!voisins.isEmpty()) {
                int choix = rand.nextInt(voisins.size());
                switch (voisins.get(choix)) {
                    case 0:
                        murs[x - 1][y] = true;
                        murs[x - 2][y] = true;
                        chemin.add(new int[]{x - 2, y});
                        break;
                    case 1:
                        murs[x + 1][y] = true;
                        murs[x + 2][y] = true;
                        chemin.add(new int[]{x + 2, y});
                        break;
                    case 2:
                        murs[x][y - 1] = true;
                        murs[x][y - 2] = true;
                        chemin.add(new int[]{x, y - 2});
                        break;
                    case 3:
                        murs[x][y + 1] = true;
                        murs[x][y + 2] = true;
                        chemin.add(new int[]{x, y + 2});
                        break;
                }
            } else {
                sauvegarde.add(chemin.get(chemin.size() - 1));
                chemin.remove(chemin.size() - 1);
            }
        }

        // Inverser les murs pour obtenir les chemins corrects
        for (int i = 1; i < nbLignes - 1; i++) {
            for (int j = 1; j < nbColonnes - 1; j++) {
                murs[i][j] = !murs[i][j];
            }
        }

        Labyrinthe labyrinthe = new Labyrinthe(murs, new Perso(x, y), new ArrayList<>(), new ArrayList<>(), new Amulette(x + 1, y), new Coordonnees(x, y));
        labyrinthe.creerMonstres(rand.nextInt(5));
        xAmu = sauvegarde.get(1)[0];
        yAmu = sauvegarde.get(1)[1];
        labyrinthe.setAmulette(new Amulette(xAmu, yAmu));

        labyrinthe.creerCasePiege(rand.nextInt(5));

        return labyrinthe;
    }
}
