package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabyrintheLoader {
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
}
