/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.classes.models;

//<editor-fold defaultstate="collapsed" desc=" IMPORT">
import grama.classes.fenetre.AfficherGraphe;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
//</editor-fold>

/**
 *
 * @author Aro et Idrissa
 */
public final class Graphe {

    //<editor-fold defaultstate="collapsed" desc=" ATTRIBUTS">
    /**
     * Liste des noeuds présents dans le graphe
     */
    private LinkedList<Noeuds> listeDeNoeuds;

    /**
     * correspond aux informations relatives à un noeud précis. En indiquant le
     * nombre de ville relié ou restaurant ou centre de loisir
     */
    private static String statistique;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" CONSTRUCTEURS">
    public Graphe(){
        this.listeDeNoeuds = new LinkedList<>();
    }
    /**
     * Crée un objet à graphe à afficher dans {@link AfficherGraphe}
     *
     * @param listeNoeuds correspond à la liste de noeud
     */
    public Graphe(LinkedList<Noeuds> listeNoeuds) {
        this.listeDeNoeuds = listeNoeuds;
    }

    /**
     * Insére les données dans la {@link #listeDeNoeuds}
     *
     * @param nomFichier correspond le nom du fichier qu'on souhaite ouvrir
     */
    public Graphe(String nomFichier) {
        File nomGraphe = new File(nomFichier);
        try {
            listeDeNoeuds = insertionDonnees(nomGraphe);
        } catch (FileNotFoundException ex) {
            System.out.println("Le fichier n'existe pas !");
        } catch (IOException ec) {
            System.out.println("Erreur entrée-sortie !");
        }
        
    }
    
    /**
     * Insére les données dans la {@link #listeDeNoeuds}
     *
     * @param fichier correspond au fichier qu'on souhaite ouvrir
     */
    public Graphe(File fichier) {
        try {
            listeDeNoeuds = insertionDonnees(fichier);
        } catch (FileNotFoundException ex) {
            System.out.println("Le fichier n'existe pas !");
        } catch (IOException ec) {
            System.out.println("Erreur entrée-sortie !");
        }
        
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" GETTERS">
    /**
     * @return le {@link #listeDeNoeuds}
     */
    public LinkedList<Noeuds> getListeDeNoeuds() {
        return listeDeNoeuds;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" SETTERS">
    /**
     * Modifie le {@link #listeDeNoeuds}
     *
     * @param listeDeNoeuds la nouvelle valeur de {@link #listeDeNoeuds}
     */
    public void setListeDeNoeuds(LinkedList<Noeuds> listeDeNoeuds) {
        this.listeDeNoeuds = listeDeNoeuds;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" METHODES">
    /**
     * Insertion des noeuds dans le graphe
     *
     * @param fichier Fichier duquelsera pris les noeurds
     * @return Liste de noeuds
     * @throws FileNotFoundException Si le fichier n'est pas accessible
     * @throws IOException Toute autre exception
     */
    public LinkedList<Noeuds> insertionDonnees(File fichier) throws FileNotFoundException, IOException {
        InputStream inputStream = new FileInputStream(fichier);
        LinkedList<Noeuds> listeDesNoeuds = new LinkedList<>();
        int cptColonne = 0, cptLigne = 0;
        String champ;
        int unsignedByte;
        try {
            while ((unsignedByte = inputStream.read()) > -1) {
                champ = "";
                while ((char) (unsignedByte) != ';') {
                    champ += (char) unsignedByte;
                    unsignedByte = inputStream.read();
                }
                String[] champSepare = champ.split(":");
                String[] infoNoeudInit = champSepare[0].split(",");
                String[] infoLien = champSepare[1].split(",");
                String[] infoNoeudLie = champSepare[3].split(",");
                listeDesNoeuds.add(new Noeuds(infoNoeudInit[0], infoNoeudInit[1]));
                try {
                    listeDesNoeuds.get(cptLigne).ajoutLien(new Liens(infoNoeudInit[0], infoNoeudInit[1], infoLien[0], infoLien[1], infoNoeudLie[0], infoNoeudLie[1]));
                } catch (NumberFormatException ex) {
                    System.out.println("Erreur dans l'insertion des données de la ligne " + cptLigne + " à la colonne " + cptColonne);
                    listeDeNoeuds = new LinkedList<>();
                }
                unsignedByte = inputStream.read();
                while ((char) (unsignedByte) != '\n') {
                    while ((char) unsignedByte == ';') {
                        unsignedByte = inputStream.read();
                    }
                    if ((char) unsignedByte == '\r') {
                        unsignedByte = inputStream.read();
                        continue;
                    }
                    champ = "";
                    while ((char) (unsignedByte) != ';') {
                        if ((char) (unsignedByte) == '\r') {
                            break;
                        }
                        champ += (char) unsignedByte;
                        unsignedByte = inputStream.read();
                    }
                    champSepare = champ.split(":");
                    infoLien = champSepare[0].split(",");
                    infoNoeudLie = champSepare[2].split(",");
                    try {
                        listeDesNoeuds.get(cptLigne).ajoutLien(new Liens(infoNoeudInit[0], infoNoeudInit[1], infoLien[0], infoLien[1], infoNoeudLie[0], infoNoeudLie[1]));
                    } catch (NumberFormatException ex) {
                        System.out.println("Erreur dans l'insertion des données de la ligne " + cptLigne + " à la colonne " + cptColonne);
                    }
                    unsignedByte = inputStream.read();
                }
                cptColonne = 0;
                cptLigne++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problème dans l'ouverture du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.out.println("erreur");
        }
        return listeDesNoeuds;
    }// fin public static LinkedList<Noeuds> insertionDonnees(File fichier)// fin public static LinkedList<Noeuds> insertionDonnees(File fichier)

    /**
     * determine la ville la plus ouverte entre deux villes
     *
     * @param ville1 correspond à la première ville
     * @param ville2 correspond à la deuxième ville
     * @return la ville la plus ouverte sinon "Egal"
     */
    public String plusOuverte(String ville1, String ville2) {
        LinkedList<Noeuds> noeuds = listeDeNoeuds;
        /**
         * je place mes deux villes dans une liste de noeud pour avoir un
         * traitement plus efficace
         */
        LinkedList<Noeuds> deuxNoeuds = new LinkedList<>();

        /**
         * je cree pour chaque noeud dans ma liste de noeud de deuxNoeuds une
         * liste de noeuds de ville à deux distance
         */
        LinkedList<Noeuds> noeuds1disVille1 = new LinkedList<>();
        LinkedList<Noeuds> noeuds1disVille2 = new LinkedList<>();

        /**
         * je cherche ma première ville que je place dans ma liste de deuxNoeuds
         */
        for (int i = 0; i < noeuds.size(); i++) {
            if (ville1.toUpperCase().equals(noeuds.get(i).getNomNoeud().toUpperCase())) {
                deuxNoeuds.add(noeuds.get(i));
            }
        }

        /**
         * je cherche ma deuxième ville que je place dans ma liste de deuxNoeuds
         */
        for (int i = 0; i < noeuds.size(); i++) {
            if (ville2.toUpperCase().equals(noeuds.get(i).getNomNoeud().toUpperCase())) {
                deuxNoeuds.add(noeuds.get(i));
            }
        }
        /**
         * pour bien les distinguer je place ma premiere ville à l'indice 0 donc
         * get(0) c'est ma premiere ville. je cree deux liste de liens pour
         * chacun de mes des noeuds
         */
        LinkedList<Liens> lienVille1 = deuxNoeuds.get(0).getListeDeLiens();
        LinkedList<Liens> lienVille2 = deuxNoeuds.get(1).getListeDeLiens();

        /**
         * je déclare deux entiers qui vont stocker le nombre de ville à deux
         * distances des deux villes passées en paramètre
         */
        int nombre2DisVille1, nombre2DisVille2;

        /**
         * pour une ecriture plus lisible je d�clare une variable string pour me
         * permettre de remplir mes deux liste de noeuds � une distance
         */
        String nomNoeudTemporaire;

        /**
         * Je vérifie maintenant dans mes liens où j'ai des villes à l'autre
         * bout du noeud. si c'est le cas je l'ajoute dans liste de noeud
         * noeuds1disVille1 ou noeuds1disVille2
         */
        for (int i = 0; i < lienVille1.size(); i++) {
            nomNoeudTemporaire = lienVille1.get(i).getNomNoeudLie2();
            noeuds1disVille1.add(noeuds.get(getIndiceNoeud(nomNoeudTemporaire)));
        }
        for (int i = 0; i < lienVille2.size(); i++) {
            nomNoeudTemporaire = lienVille2.get(i).getNomNoeudLie2();
            noeuds1disVille2.add(noeuds.get(getIndiceNoeud(nomNoeudTemporaire)));
        }

        /**
         * et je compte ainsi pour chaque noeud à une distance de chacune de mes
         * ville le nombre de ville auxquelles c'est relié j'utilise la fonction
         * counterVilleDeuxDistance()
         */
        nombre2DisVille1 = counterVilleDeuxDistance(noeuds1disVille1, ville1);
        nombre2DisVille2 = counterVilleDeuxDistance(noeuds1disVille2, ville2);
        return (nombre2DisVille1 > nombre2DisVille2) ? ville1 : (nombre2DisVille1 < nombre2DisVille2) ? ville2 : "Egaux";
    }

    /**
     * determine la ville la plus gastronomique entre deux villes
     *
     * @param ville1 la première ville
     * @param ville2 la deuxième ville
     * @return la ville la plus gastronomique
     */
    public String plusGastronomique(String ville1, String ville2) {
        LinkedList<Noeuds> noeuds = listeDeNoeuds;

        LinkedList<Noeuds> deuxNoeuds = new LinkedList<>();

        LinkedList<Noeuds> noeuds1disVille1 = new LinkedList<>();
        LinkedList<Noeuds> noeuds1disVille2 = new LinkedList<>();

        for (int i = 0; i < noeuds.size(); i++) {
            if (ville1.toUpperCase().equals(noeuds.get(i).getNomNoeud().toUpperCase())) {
                deuxNoeuds.add(noeuds.get(i));
            }
        }

        for (int i = 0; i < noeuds.size(); i++) {
            if (ville2.toUpperCase().equals(noeuds.get(i).getNomNoeud().toUpperCase())) {
                deuxNoeuds.add(noeuds.get(i));
            }
        }

        LinkedList<Liens> lienVille1 = deuxNoeuds.get(0).getListeDeLiens();
        LinkedList<Liens> lienVille2 = deuxNoeuds.get(1).getListeDeLiens();

        int nombre2DisResataurant1, nombre2DisResataurant2;

        /**
         * pour une ecriture plus lisible je d�clare une variable string pour me
         * permettre de remplir mes deux liste de noeuds � une distance
         */
        String nomNoeudTemporaire;

        for (int i = 0; i < lienVille1.size(); i++) {
            //if ("V".equals(lienVille1.get(i).getTypeNoeudLie2())) {
            nomNoeudTemporaire = lienVille1.get(i).getNomNoeudLie2();
            noeuds1disVille1.add(noeuds.get(getIndiceNoeud(nomNoeudTemporaire)));
            //}
        }
        for (int i = 0; i < lienVille2.size(); i++) {
            //if ("V".equals(lienVille2.get(i).getTypeNoeudLie2())) {
            nomNoeudTemporaire = lienVille2.get(i).getNomNoeudLie2();
            noeuds1disVille2.add(noeuds.get(getIndiceNoeud(nomNoeudTemporaire)));
            //}
        }

        /**
         * et je compte ainsi pour chaque noeud � une distance de chacune de mes
         * ville le nombre de restaurant auxquels c'est reli� j'utilise la
         * fonction counterRestaurantDeuxDistance()
         */
        nombre2DisResataurant1 = counterRestaurantDeuxDistance(noeuds1disVille1);
        nombre2DisResataurant2 = counterRestaurantDeuxDistance(noeuds1disVille2);
        return (nombre2DisResataurant1 > nombre2DisResataurant2) ? ville1 : (nombre2DisResataurant1 < nombre2DisResataurant2) ? ville2 : "Egaux";
    }

    /**
     * determine la ville la plus culturelle entre deux villes
     *
     * @param ville1 la première ville
     * @param ville2 la deuxième ville
     * @return la ville la plus culturelle
     */
    public String plusCulturelle(String ville1, String ville2) {
        LinkedList<Noeuds> noeuds = listeDeNoeuds;

        LinkedList<Noeuds> deuxNoeuds = new LinkedList<>();

        LinkedList<Noeuds> noeuds1disVille1 = new LinkedList<>();
        LinkedList<Noeuds> noeuds1disVille2 = new LinkedList<>();

        for (int i = 0; i < noeuds.size(); i++) {
            if (ville1.toUpperCase().equals(noeuds.get(i).getNomNoeud().toUpperCase())) {
                deuxNoeuds.add(noeuds.get(i));
            }
        }

        for (int i = 0; i < noeuds.size(); i++) {
            if (ville2.toUpperCase().equals(noeuds.get(i).getNomNoeud().toUpperCase())) {
                deuxNoeuds.add(noeuds.get(i));
            }
        }

        LinkedList<Liens> lienVille1 = deuxNoeuds.get(0).getListeDeLiens();
        LinkedList<Liens> lienVille2 = deuxNoeuds.get(1).getListeDeLiens();

        int nombre2DisLoisir1, nombre2DisLoisir2;

        /**
         * pour une ecriture plus lisible je d�clare une variable string pour me
         * permettre de remplir mes deux liste de noeuds � une distance
         */
        String nomNoeudTemporaire;

        for (int i = 0; i < lienVille1.size(); i++) {
            //if ("V".equals(lienVille1.get(i).getTypeNoeudLie2())) {
            nomNoeudTemporaire = lienVille1.get(i).getNomNoeudLie2();
            noeuds1disVille1.add(noeuds.get(getIndiceNoeud(nomNoeudTemporaire)));
            //}
        }
        for (int i = 0; i < lienVille2.size(); i++) {
            //if ("V".equals(lienVille2.get(i).getTypeNoeudLie2())) {
            nomNoeudTemporaire = lienVille2.get(i).getNomNoeudLie2();
            noeuds1disVille2.add(noeuds.get(getIndiceNoeud(nomNoeudTemporaire)));
            //}
        }

        /**
         * et je compte ainsi pour chaque noeud � une distance de chacune de mes
         * ville le nombre de restaurant auxquels c'est reli� j'utilise la
         * fonction counterLoisirsDeuxDistance()
         */
        nombre2DisLoisir1 = counterLoisirsDeuxDistance(noeuds1disVille1);
        nombre2DisLoisir2 = counterLoisirsDeuxDistance(noeuds1disVille2);
        return (nombre2DisLoisir1 > nombre2DisLoisir2) ? ville1 : (nombre2DisLoisir1 < nombre2DisLoisir2) ? ville2 : "Egaux";
    }

    /**
     * compte le nombre de ville deux distance
     *
     * @param noeuds correspond à la liste de noeud où il faut effectuer
     * l'opération
     * @param villeInterdite correspondant à la ville qu'on ne doit pas compter
     * deux fois
     * @return le nombre de ville à deux distance
     */
    public int counterVilleDeuxDistance(LinkedList<Noeuds> noeuds, String villeInterdite) {
        int nombreVille2dis = 0;
        for (int i = 0; i < noeuds.size(); i++) {
            for (int j = 0; j < noeuds.get(i).getListeDeLiens().size(); j++) {
                if ("V".equals(noeuds.get(i).getListeDeLiens().get(j).getTypeNoeudLie2())
                        && (!(villeInterdite.equalsIgnoreCase(noeuds.get(i).getListeDeLiens().get(j).getNomNoeudLie2())))) {
                    nombreVille2dis++;
                }
            }
        }
        return nombreVille2dis;
    }

    /**
     * Compte le nombre de restaurants à deux distances
     *
     * @param noeuds correspond à la liste de noeuds où il faut effectuer
     * l'opération
     * @return le nombre de restaurant à deux distance
     */
    public int counterRestaurantDeuxDistance(LinkedList<Noeuds> noeuds) {
        int nombreRestaurant2dis = 0;
        for (int i = 0; i < noeuds.size(); i++) {
            for (int j = 0; j < noeuds.get(i).getListeDeLiens().size(); j++) {
                if ("R".equals(noeuds.get(i).getListeDeLiens().get(j).getTypeNoeudLie2())) {
                    nombreRestaurant2dis++;
                }
            }
        }
        return nombreRestaurant2dis;
    }

    /**
     * Compte le nombre de centre de loisirs à deux distances
     *
     * @param noeuds correspond à la liste de noeuds où il faut effectuer
     * l'opération
     * @return le nombre de centre de loisirs à deux distance
     */
    public int counterLoisirsDeuxDistance(LinkedList<Noeuds> noeuds) {
        int nombreLoisir2dis = 0;
        for (int i = 0; i < noeuds.size(); i++) {
            for (int j = 0; j < noeuds.get(i).getListeDeLiens().size(); j++) {
                if ("L".equals(noeuds.get(i).getListeDeLiens().get(j).getTypeNoeudLie2())) {
                    nombreLoisir2dis++;
                }
            }
        }
        return nombreLoisir2dis;
    }

    /**
     * pour avoir l'indice d'une ville ou un noeud à partir de son nom
     *
     * @param node c'est le nom du noeud qu'on souhaite avoir son indice
     * @return retourne la position du noeud dans la liste de noeuds principale
     */
    public int getIndiceNoeud(String node) {
        LinkedList<Noeuds> noeuds = listeDeNoeuds;
        int positionNoeud = -5;
        boolean res = false;
        int i = 0;
        while (i < noeuds.size() && res == false) {
            if (node.equalsIgnoreCase(noeuds.get(i).getNomNoeud())) {
                res = true;
                positionNoeud = i;
            }
            i++;
        }
        return positionNoeud;
    }

    /**
     * Affiche les noeuds demandés
     *
     * @param noeuds Liste de tous les noeuds du graphe
     * @param typeNoeud correspond au type de noeud parmis les 3 qui existe ou
     * bien les afficher tout
     * @return la liste de noeud aprés concaténation
     */
    public String afficheNoeuds(LinkedList<Noeuds> noeuds, String typeNoeud) {
        String listeNoeudsAafficher = "";
        for (int i = 0; i < noeuds.size(); i++) {
            if (typeNoeud.equalsIgnoreCase(noeuds.get(i).getTypeNoeud())) {
                listeNoeudsAafficher = listeNoeudsAafficher + noeuds.get(i).getNomNoeud() + "\n";
            } else if ("tout".equalsIgnoreCase(typeNoeud)) {
                listeNoeudsAafficher = listeNoeudsAafficher + noeuds.get(i).getNomNoeud() + "\n";
            }
        }
        return listeNoeudsAafficher;
    }

    /**
     * Affiche les noeuds demandés
     *
     * @param noeuds Liste de tous les noeuds du graphe
     * @param typeLien correspond au type de lien parmis les 3 qui existe ou
     * bien les afficher tout
     * @return la liste de liens aprés concaténation
     */
    public String afficheLiens(LinkedList<Noeuds> noeuds, String typeLien) {
        String listeLiens = "";
        LinkedList<Liens> lienTraites = new LinkedList<>();
        for (int i = 0; i < noeuds.size(); i++) {
            LinkedList<Liens> lien = noeuds.get(i).getListeDeLiens();
            for (int j = 0; j < lien.size(); j++) {
                if (typeLien.equalsIgnoreCase(lien.get(j).getTypeLien()) && !lienTraites.contains(lien.get(j))) {
                    listeLiens = listeLiens + lien.get(j) + "\n";
                    lienTraites.add(lien.get(j));
                }
            }
        }
        return listeLiens;
    }

    /**
     * Compte le nombre de noeud présente dans le graphe par type
     *
     * @param noeuds Liste de tous les noeuds du graphe
     * @param typeNoeud correspond au type de noeud parmis les 3 qui existe ou
     * bien les afficher tout
     * @return Le nombre de noeud par typeNoeud présente dans le graphe
     */
    public int counterNoeudByType(LinkedList<Noeuds> noeuds, String typeNoeud) {
        int nombreDeCeTypeDeNoeud = 0;
        for (int i = 0; i < noeuds.size(); i++) {
            if (typeNoeud.equalsIgnoreCase(noeuds.get(i).getTypeNoeud())) {
                nombreDeCeTypeDeNoeud++;
            }

        }
        return nombreDeCeTypeDeNoeud;
    }

    /**
     * Compte le nombre de lien présente dans le graphe par type
     *
     * @param noeuds Liste de tous les noeuds du graphe
     * @param typeLien correspond au type de lien parmis les 3 qui existe ou
     * bien les afficher tout
     * @return Le nombre de lien par typeLien présente dans le graphe
     */
    public int counterLinkByType(LinkedList<Noeuds> noeuds, String typeLien) {
        int nombreDeCeTypeDeLien = 0;
        LinkedList<Liens> lienTraites = new LinkedList<>();
        for (int i = 0; i < noeuds.size(); i++) {
            LinkedList<Liens> lien = noeuds.get(i).getListeDeLiens();
            for (int j = 0; j < lien.size(); j++) {
                if (typeLien.equalsIgnoreCase(lien.get(j).getTypeLien()) && !lienTraites.contains(lien.get(j))) {
                    nombreDeCeTypeDeLien++;
                    lienTraites.add(lien.get(j));
                }
            }
        }
        return nombreDeCeTypeDeLien;
    }

    /**
     * Retourne une {@link LinkedList} de tout les noeuds à 1 distance de la
     * ville mis en argument
     *
     * @param nomVille Ville dont on veut connaître les noeuds à 1 distance
     * @return Tout les noeuds à 1 distance de la ville mis en argument
     */
    public ArrayList<String> connaitre1Distance(String nomVille) {
        ArrayList<String> listeNoeuds1Distance = new ArrayList();
        Noeuds elliste = new Noeuds();
        int compteur = 0;
        while (!nomVille.equalsIgnoreCase(elliste.getNomNoeud())) {
            elliste = getListeDeNoeuds().get(compteur);
            compteur++;
        }
        for (Liens lien : elliste.getListeDeLiens()) {
            listeNoeuds1Distance.add(lien.toStringInfoNoeudLie2());
        }
        return listeNoeuds1Distance;
    }

    /**
     * Retourne si oui ou non les deux noeuds mis en arguments sont à 2
     * distances l'une de l'autre
     *
     * @param noeud1 Noeud à comparer 1
     * @param noeud2 Noeud à comparer 2
     * @return Retourne true si les deux noeuds mis en arguments sont à 2
     * distances l'une de l'autre, non sinon
     */
    public boolean connaitre2Distance(Noeuds noeud1, Noeuds noeud2) {
        for (Liens lienNoeud1 : noeud1.getListeDeLiens()) {
            for (Liens lienNoeud2 : noeud2.getListeDeLiens()) {
                if (lienNoeud1.toStringInfoNoeudLie2().equals(lienNoeud2.toStringInfoNoeudLie2())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Affiche les noeuds à deux distance par type. Et par la même occasion on
     * fait la statistique du noeud
     *
     * @param liste correspond à {@link #listeDeNoeuds}
     * @param type correspond au type de noeud qu'on veut afficher
     */
    public static void afficher1Dist(ArrayList liste, String type) {
        String chai = "";
        String debutChaine;
        int nbVille = 0, nbResto = 0, nbLoisirs = 0;
        for (int i = 0; i < liste.size(); i++) {
            debutChaine = (String) liste.get(i);
            if ("v".equalsIgnoreCase(debutChaine.substring(0, 1))) {
                nbVille++;
            }
            if ("r".equalsIgnoreCase(debutChaine.substring(0, 1))) {
                nbResto++;
            }
            if ("l".equalsIgnoreCase(debutChaine.substring(0, 1))) {
                nbLoisirs++;
            }
            if (type.equalsIgnoreCase(debutChaine.substring(0, 1))) {
                chai = chai + liste.get(i) + "\n";
            } else if ("tout".equalsIgnoreCase(type)) {
                chai = chai + liste.get(i) + "\n";
            }
        }
        AfficherGraphe.setTextArea2(chai);
        statistique = "Nous avons pour ce Noeuds:\n-Villes: " + nbVille + "\n-Restaurants: " + nbResto
                + "\n-Loisirs: " + nbLoisirs;

    }

    /**
     * Getter pour statistique
     *
     * @return {@link #statistique}
     */
    public static String getStatistique() {
        return statistique;
    }

    //</editor-fold>
}
