/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grama.classes.fenetre;

//<editor-fold defaultstate="collapsed" desc=" IMPORT">
import grama.classes.models.Graphe;
import grama.classes.models.Liens;
import grama.classes.models.Noeuds;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//</editor-fold>

/**
 *
 * @author Aro et Idrissa
 */
public class PanelAffichage extends JPanel {

    //<editor-fold defaultstate="collapsed" desc=" ATTRIBUTS">
    /**
     * objet graphe
     */
    //private Graphe graphe;
    /**
     * correspond à la liste de position des points
     */
    private LinkedList<Point> listeDePosition;

    /**
     * correspond à la liste de position des points
     */
    private Graphe graphe;

    /**
     * correspond à la taille du cercle
     */
    private int tailleDuCercle = 25;

    /**
     * détérmine si la position est déjà générée
     */
    private boolean estGeneree;

    /**
     * button group
     */
    private ButtonGroup bg;

    /**
     * correspond au nom du noeud cliqué
     */
    private static String noeudClicked = "";

    /**
     * si oui ou non on efface tous les noms des noeuds
     */
    private static boolean effacerNomNoeuds = false;

    /**
     * si oui ou nom on efface tous les noms des liens
     */
    private static boolean effacerNomLiens = false;

    /**
     * si oui ou nom on efface tous les noms des villes
     */
    private static boolean effacerNomVille = false;

    /**
     * si oui ou nom on efface tous les noms des restaurants
     */
    private static boolean effacerNomRestaurant = false;

    /**
     * si oui ou nom on efface tous les noms des centres de loisirs
     */
    private static boolean effacerNomLoisir = false;

    /**
     * si oui ou nom on efface tous les noms des autoroutes
     */
    private static boolean effacerNomAutoroutes = false;

    /**
     * si oui ou nom on efface tous les noms des nationales
     */
    private static boolean effacerNomNationales = false;

    /**
     * si oui ou nom on efface tous les noms des départementales
     */
    private static boolean effacerNomDepartementales = false;

    /**
     * si oui ou nom on colorie le noeud sélectionné
     */
    private static boolean colorierNoeud = false;

    /**
     * correspond au noeud sélectionné
     */
    private Noeuds noeudSelectionne;

    /**
     * si oui ou nom on colorie les deux noeuds sélectionnés
     */
    private static boolean colorierLesDeuxNoeuds = false;

    /**
     * correspond le premier noeud choisi dans {@link AfficherGraphe}
     */
    private static Noeuds noeud1Choisi;

    /**
     * correspond le deuxième noeud choisi dans {@link AfficherGraphe}
     */
    private static Noeuds noeud2Choisi;

    /**
     * si oui ou non on souhaite afficher la boite de dialogue si on clique sur
     * un noeud
     */
    private static boolean afficherBoiteDeDialogue = false;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" CONSTRUCTEURS">
    /**
     * crée l'objet panelAffichage
     */
    public PanelAffichage() {
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.white);
    }

    public PanelAffichage(Graphe graphe) {
        this.setVisible(true);
        this.setLayout(null);
        this.setBackground(Color.white);
        this.estGeneree = false;
        this.graphe = graphe;
        this.bg = new ButtonGroup();
        this.listeDePosition = new LinkedList();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Point point1 = new Point(evt.getX(), evt.getY());
                if (positionCheck(point1)) {
                    noeudSelectionne = graphe.getListeDeNoeuds().get((getIndex(point1)));
                    noeudClicked = graphe.getListeDeNoeuds().get((getIndex(point1))).getNomNoeud();
                    Graphe.afficher1Dist(graphe.connaitre1Distance(noeudClicked), "tout");
                    AfficherGraphe.activateButtonEcran1(true);
                    AfficherGraphe.setSelectedParDef(true);
                    afficherInformation(noeudSelectionne);
                    repaint();
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                Point point = new Point(evt.getX(), evt.getY());
                if (positionCheck(point)) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }

            @Override
            public void mouseDragged(MouseEvent evt) {
                Point point = new Point(evt.getX(), evt.getY());
                int index = getIndex(point);
                if (positionCheck(point)) {
                    setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    listeDePosition.get(index).setLocation(evt.getX() - tailleDuCercle / 2, evt.getY() - tailleDuCercle / 2);
                    repaint();
                } else {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }

        });
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" SETTERS + GETTERS">
    /**
     * @return le nom du noeud cliqué
     */
    public static String getNoeudclicked() {
        if (noeudClicked != null) {
            return noeudClicked;
        }
        return "";
    }

    /**
     * Modifie la valeur de {@link #effacerNomNoeuds}
     *
     * @param effacerNomNoeuds la nouvelle valeur
     */
    public static void setEffacerNomNoeuds(boolean effacerNomNoeuds) {
        PanelAffichage.effacerNomNoeuds = effacerNomNoeuds;
    }

    /**
     * Modifie la valeur de {@link #effacerNomLiens}
     * @param effacerNomLiens la nouvelle valeur
     */
    public static void setEffacerNomLiens(boolean effacerNomLiens) {
        PanelAffichage.effacerNomLiens = effacerNomLiens;
    }

    /**
     * Modifie la valeur de {@link #effacerNomVille}
     * @param effacerNomVille la nouvelle valeur
     */
    public static void setEffacerNomVille(boolean effacerNomVille) {
        PanelAffichage.effacerNomVille = effacerNomVille;
    }

    /**
     * Modifie la valeur de {@link #effacerNomRestaurant}
     *
     * @param effacerNomRestaurant la nouvelle valeur
     */
    public static void setEffacerNomRestaurant(boolean effacerNomRestaurant) {
        PanelAffichage.effacerNomRestaurant = effacerNomRestaurant;
    }

    /**
     * Modifie la valeur de {@link #effacerNomLoisir}
     *
     * @param effacerNomLoisir la nouvelle valeur
     */
    public static void setEffacerNomLoisir(boolean effacerNomLoisir) {
        PanelAffichage.effacerNomLoisir = effacerNomLoisir;
    }

    /**
     * Modifie la valeur de {@link #effacerNomAutoroutes}
     *
     * @param effacerNomAutoroutes la nouvelle valeur
     */
    public static void setEffacerNomAutoroutes(boolean effacerNomAutoroutes) {
        PanelAffichage.effacerNomAutoroutes = effacerNomAutoroutes;
    }

    /**
     * Modifie la valeur de {@link #effacerNomNationales}
     *
     * @param effacerNomNationales la nouvelle valeur
     */
    public static void setEffacerNomNationales(boolean effacerNomNationales) {
        PanelAffichage.effacerNomNationales = effacerNomNationales;
    }

    /**
     * Modifie la valeur de {@link #effacerNomDepartementales}
     *
     * @param effacerNomDepartementales la nouvelle valeur
     */
    public static void setEffacerNomDepartementales(boolean effacerNomDepartementales) {
        PanelAffichage.effacerNomDepartementales = effacerNomDepartementales;
    }
    
    /**
     * @return la taille du cercle
     */
    public int getTailleDuCercle() {
        return tailleDuCercle;
    }

    /**
     * modifie la taille du cercle
     *
     * @param tailleDuCercle la nouvelle valeur
     */
    public void setTailleDuCercle(int tailleDuCercle) {
        this.tailleDuCercle = tailleDuCercle;
    }

    /**
     * @return la valeur de {@link #estGeneree}
     */
    public boolean isEstGeneree() {
        return estGeneree;
    }

    /**
     * modifie la valeur de {@link #estGeneree}
     *
     * @param estGeneree la nouvelle valeur
     */
    public void setEstGeneree(boolean estGeneree) {
        this.estGeneree = estGeneree;
    }

    public ButtonGroup getBg() {
        return bg;
    }

    public void setBg(ButtonGroup bg) {
        this.bg = bg;
    }

    /**
     * modifie la valeur de {@link #colorierNoeud}
     *
     * @param colorierNoeud la nouvelle valeur
     */
    public static void setColorierNoeud(boolean colorierNoeud) {
        PanelAffichage.colorierNoeud = colorierNoeud;
    }

    /**
     * modifie le premier noeud choisi
     *
     * @param noeud1Choisi la nouvelle valeur
     */
    public static void setNoeud1Choisi(Noeuds noeud1Choisi) {
        PanelAffichage.noeud1Choisi = noeud1Choisi;
    }

    /**
     * modifie le deuxième noeud choisi
     *
     * @param noeud2Choisi la nouvelle valeur
     */
    public static void setNoeud2Choisi(Noeuds noeud2Choisi) {
        PanelAffichage.noeud2Choisi = noeud2Choisi;
    }

    /**
     * modifie la valeur de {@link #colorierLesDeuxNoeuds}
     *
     * @param colorierLesDeuxNoeuds la nouvelle valeur
     */
    public static void setColorierLesDeuxNoeuds(boolean colorierLesDeuxNoeuds) {
        PanelAffichage.colorierLesDeuxNoeuds = colorierLesDeuxNoeuds;
    }

    /**
     * modifie la valeur de {@link #afficherBoiteDeDialogue}
     *
     * @param afficherBoiteDeDialogue
     */
    public static void setAfficherBoiteDeDialogue(boolean afficherBoiteDeDialogue) {
        PanelAffichage.afficherBoiteDeDialogue = afficherBoiteDeDialogue;
    }

    /**
     * renvoie la liste de position
     * @return la liste de position
     */
    public LinkedList<Point> getListeDePosition() {
        return listeDePosition;
    }

    /**
     * modifie la liste de position
     * @param listeDePosition 
     */
    public void setListeDePosition(LinkedList<Point> listeDePosition) {
        this.listeDePosition = listeDePosition;
    }
    
    /**
     * renvoie l'objet graphe
     * @return l'objet graphe
     */
    public Graphe getGraphe() {
        return graphe;
    }

    /**
     * modifie le graphe
     * @param graphe 
     */
    public void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }

    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" METHODES PRIVATES + PAINTCOMPONENTS">
    /**
     * Génére des positions aléatoire et rempli la liste de position
     */
    public void genererPosition() {
        int min = 2 * tailleDuCercle;
        int max1 = this.getWidth() - (2 * tailleDuCercle);
        int max2 = this.getHeight() - (2 * tailleDuCercle);
        this.listeDePosition = new LinkedList();
        int posX = 0, posY = 0;
        for (int i = 0; i < graphe.getListeDeNoeuds().size(); i++) {
            do {
                posX = new Random().nextInt(Math.abs(-2 * min + max1)) + min;
                posY = new Random().nextInt(Math.abs(-2 * min + max2)) + min;
            } while (positionCheck(new Point(posX, posY)));
            listeDePosition.add(new Point(posX, posY));
        }
        estGeneree = true;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
                java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (!estGeneree) {
            this.genererPosition();
        }
        Noeuds noeud1;
        Noeuds noeud2;
        Point pos1;
        Point pos2;
        g2.setStroke(new BasicStroke(2));
        for (int indice1 = 0; indice1 < graphe.getListeDeNoeuds().size(); indice1++) {
            pos1 = listeDePosition.get(indice1);
            noeud1 = graphe.getListeDeNoeuds().get(indice1);
            for (Liens lien : noeud1.getListeDeLiens()) {
                for (int indice2 = 0; indice2 < graphe.getListeDeNoeuds().size(); indice2++) {
                    noeud2 = graphe.getListeDeNoeuds().get(indice2);
                    pos2 = listeDePosition.get(indice2);
                    if (noeud2.equals(new Noeuds(lien.getTypeNoeudLie2(), lien.getNomNoeudLie2()))) {
                        g2.setColor(lien.getCouleur());
                        float x1 = (float) pos1.getX() + (tailleDuCercle / 2);
                        float x2 = (float) pos2.getX() + (tailleDuCercle / 2);
                        float y1 = (float) pos1.getY() + (tailleDuCercle / 2);
                        float y2 = (float) pos2.getY() + (tailleDuCercle / 2);
                        g2.draw(new Line2D.Float(x1, y1, x2, y2));
                        if (effacerNomLiens == true) {
                            g2.setColor(Color.black);
                            g2.fillRect((int) (x1 + x2) / 2 - 3, (int) (y1 + y2) / 2 - 10, 35, 15);
                            g2.setColor(Color.white);
                            g2.drawString(lien.getTypeLien() + "," + String.valueOf(lien.getPoidsLien()), (x1 + x2) / 2, (y1 + y2) / 2);
                        }
                        if (effacerNomAutoroutes == true && lien.getTypeLien().equalsIgnoreCase("a")) {
                            g2.setColor(Color.black);
                            g2.fillRect((int) (x1 + x2) / 2 - 3, (int) (y1 + y2) / 2 - 10, 35, 15);
                            g2.setColor(Color.white);
                            g2.drawString(lien.getTypeLien() + "," + String.valueOf(lien.getPoidsLien()), (x1 + x2) / 2, (y1 + y2) / 2);
                        }
                        if (effacerNomNationales == true && lien.getTypeLien().equalsIgnoreCase("n")) {
                            g2.setColor(Color.black);
                            g2.fillRect((int) (x1 + x2) / 2 - 3, (int) (y1 + y2) / 2 - 10, 35, 15);
                            g2.setColor(Color.white);
                            g2.drawString(lien.getTypeLien() + "," + String.valueOf(lien.getPoidsLien()), (x1 + x2) / 2, (y1 + y2) / 2);
                        }
                        if (effacerNomDepartementales == true && lien.getTypeLien().equalsIgnoreCase("d")) {
                            g2.setColor(Color.black);
                            g2.fillRect((int) (x1 + x2) / 2 - 3, (int) (y1 + y2) / 2 - 10, 35, 15);
                            g2.setColor(Color.white);
                            g2.drawString(lien.getTypeLien() + "," + String.valueOf(lien.getPoidsLien()), (x1 + x2) / 2, (y1 + y2) / 2);
                        }

                    }
                }
            }
        }
        for (int cpt1 = 0; cpt1 < graphe.getListeDeNoeuds().size(); cpt1++) {
            noeud1 = graphe.getListeDeNoeuds().get(cpt1);
            pos1 = listeDePosition.get(cpt1);
            g.setColor(noeud1.getCouleur());
            g.fillOval((int) pos1.getX(), (int) pos1.getY(), tailleDuCercle, tailleDuCercle);
            if (effacerNomNoeuds == true) {
                g.setColor(Color.black);
                g.drawString(noeud1.getTypeNoeud() + "," + noeud1.getNomNoeud(), (int) pos1.getX() - 10, (int) pos1.getY() + tailleDuCercle + 15);
            }
            if (effacerNomVille == true && noeud1.getTypeNoeud().equalsIgnoreCase("v")) {
                g.setColor(Color.black);
                g.drawString(noeud1.getTypeNoeud() + "," + noeud1.getNomNoeud(), (int) pos1.getX() - 10, (int) pos1.getY() + tailleDuCercle + 15);
            }
            if (effacerNomRestaurant == true && noeud1.getTypeNoeud().equalsIgnoreCase("r")) {
                g.setColor(Color.black);
                g.drawString(noeud1.getTypeNoeud() + "," + noeud1.getNomNoeud(), (int) pos1.getX() - 10, (int) pos1.getY() + tailleDuCercle + 15);
            }
            if (effacerNomLoisir == true && noeud1.getTypeNoeud().equalsIgnoreCase("l")) {
                g.setColor(Color.black);
                g.drawString(noeud1.getTypeNoeud() + "," + noeud1.getNomNoeud(), (int) pos1.getX() - 10, (int) pos1.getY() + tailleDuCercle + 15);
            }
            if (noeud1 == noeudSelectionne && colorierNoeud == true) {
                g.setColor(Color.red);
                g.fillOval((int) pos1.getX() - 5, (int) pos1.getY() - 5, tailleDuCercle + 10, tailleDuCercle + 10);
            }
            if (noeud1 == noeud1Choisi && colorierLesDeuxNoeuds == true) {
                g.setColor(Color.BLACK);
                g.fillOval((int) pos1.getX() - 5, (int) pos1.getY() - 5, tailleDuCercle + 10, tailleDuCercle + 10);
            }
            if (noeud1 == noeud2Choisi && colorierLesDeuxNoeuds == true) {
                g.setColor(Color.BLACK);
                g.fillOval((int) pos1.getX() - 5, (int) pos1.getY() - 5, tailleDuCercle + 10, tailleDuCercle + 10);

            }
        }
    }

    /**
     * verifie si un point passé en paramètre appartient à notre liste de
     * position
     *
     * @param p le point à chercher
     * @return si oui ou non
     */
    private boolean positionCheck(Point p) {
        for (Point point : listeDePosition) {
            if ((point.x <= p.x && p.x <= point.x + tailleDuCercle) && (point.y <= p.y && p.y <= point.y + tailleDuCercle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * donne l'indice dans la liste de position d'un point
     *
     * @param p l'indice du point à chercher
     * @return l'indice ou -1 sinon
     */
    private int getIndex(Point p) {
        int index = -1;
        for (Point point : listeDePosition) {
            index++;
            if ((point.x <= p.x && p.x <= point.x + tailleDuCercle) && (point.y <= p.y && p.y <= point.y + tailleDuCercle)) {
                return index;
            }
        }
        index = -1;
        return index;
    }

    /**
     * renseignment relatif à notre boite de dialogue
     *
     * @param noeud les information sur le noeud en question
     */
    private void afficherInformation(Noeuds noeud) {
        if (afficherBoiteDeDialogue) {
            String listLien = "";
            String type = (noeud.getTypeNoeud().equalsIgnoreCase("v") ? "La ville" : noeud.getTypeNoeud().equalsIgnoreCase("r")
                    ? "Le restaurant" : "Le centre de Loisir");
            int i;
            for (i = 0; i < noeud.getListeDeLiens().size() - 1; i++) {
                String typeLien = (noeud.getListeDeLiens().get(i).getTypeLien().equalsIgnoreCase("a") ? "une Autoroute"
                        : noeud.getListeDeLiens().get(i).getTypeLien().equalsIgnoreCase("n") ? "une Nationale" : "une Departementale");
                listLien = listLien + noeud.getListeDeLiens().get(i).getNomNoeudLie2() + " par " + typeLien + " avec une distance de "
                        + String.valueOf(noeud.getListeDeLiens().get(i).getPoidsLien()) + " Km " + ",\n-";
            }

            String typeLien = (noeud.getListeDeLiens().get(i).getTypeLien().equalsIgnoreCase("a") ? "une Autoroute"
                    : noeud.getListeDeLiens().get(i).getTypeLien().equalsIgnoreCase("n") ? "une Nationale" : "une Departementale");
            listLien = listLien + noeud.getListeDeLiens().get(i).getNomNoeudLie2() + " par " + typeLien + " avec une distance de "
                    + String.valueOf(noeud.getListeDeLiens().get(i).getPoidsLien()) + " Km " + ".\n";

            String message = type + " s'appelle " + noeud.getNomNoeud() + ". Ce noeud est lié au(x) noeud(s):\n-" + listLien;

            listLien = null;
            String[] monBouton = {"Fermer"};
            int conf = JOptionPane.showOptionDialog(this, message, "SAE GRAMA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, monBouton, monBouton[0]);
        }

    }//</editor-fold>
}
