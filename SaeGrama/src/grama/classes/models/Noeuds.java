/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.classes.models;

//<editor-fold defaultstate="collapsed" desc=" IMPORT">
import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Objects;
//</editor-fold>

/**
 *
 * @author Aro et Idrissa 
 */
public class Noeuds {

    //<editor-fold defaultstate="collapsed" desc=" ATTRIBUTS">
    /**
     * représente l'abscisse du noeud
     */
    private int abs;
    
    /**
     * représente l'ordonnée du noeud
     */
    private int ord;
    
    /**
     * correspond au type de noeud
     */
    private String typeNoeud;
    
    /**
     * corrspond au nom du noeud
     */
    private String nomNoeud;
    
    /**
     * correspond à la couleur du noeud
     */
    private Color couleur;
    
    /**
     * correspond à la liste de lien du noeud
     */
    private LinkedList<Liens> listeDeLiens;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" CONSTRUCTEURS">
    /**
     * Constructeur sans paramètre
     */
    public Noeuds(){
    }

    /**
     * crée l'objet noeud
     * @param typeNoeud le type du noeud
     * @param nomNoeud le nom du noeud
     */
    public Noeuds(String typeNoeud, String nomNoeud) {
        this.typeNoeud = typeNoeud;
        this.nomNoeud = nomNoeud;
        this.listeDeLiens = new LinkedList();
        if(this.typeNoeud.equalsIgnoreCase("V")){
            this.couleur = Color.orange;
        } else {
            if (this.typeNoeud.equalsIgnoreCase("L")){
               this.couleur = Color.gray;
            } else {
                if (this.typeNoeud.equalsIgnoreCase("R")){
                    this.couleur = Color.green;
                }
            }
        }
    }

    /**
     * crée l'objet noeud
     * @param typeNoeud le type du noeud
     * @param nomNoeud le nom du noeud
     * @param listeLien la liste de ses liens
     */
    public Noeuds(String typeNoeud, String nomNoeud, LinkedList<Liens> listeLien) {
        this.typeNoeud = typeNoeud;
        this.nomNoeud = nomNoeud;
        this.listeDeLiens = listeLien; 
        if(this.typeNoeud.equalsIgnoreCase("V")){
            this.couleur = Color.orange;
        } else {
            if (this.typeNoeud.equalsIgnoreCase("L")){
               this.couleur = Color.gray;
            } else {
                if (this.typeNoeud.equalsIgnoreCase("R")){
                    this.couleur = Color.green;
                }
            }
        }
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" GETTERS">
    /**
     * @return le type de noeud
     */
    public String getTypeNoeud() {
        return typeNoeud;
    }

    /**
     * @return le nom du noeud
     */
    public String getNomNoeud() {
        return nomNoeud;
    }

    /**
     * @return la liste des liens du noeud
     */
    public LinkedList<Liens> getListeDeLiens() {
        return listeDeLiens;
    }

    /**
     * @return la tete de la liste de lien
     */
    public Liens getTeteLien() {
        return listeDeLiens.getFirst();
    }

    /**
     * @return la couleur du noeud
     */
    public Color getCouleur() {
        return couleur;
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" SETTERS">
    /**
     * modifie la position du noeud
     * @param p la nouvelle valeur
     */
    public void setPos(Point p){
        this.abs=p.x;
        this.ord=p.x;
    }
    
    /**
     * modifie le type de noeud
     * @param typeNoeud la nouvelle valeur
     */
    public void setTypeNoeud(String typeNoeud) {
        this.typeNoeud = typeNoeud;
    }

    /**
     * modifie le nom du noeud
     * @param nomNoeud la nouvelle valeur
     */
    public void setNomNoeud(String nomNoeud) {
        this.nomNoeud = nomNoeud;
    }

    /**
     * modifie la liste de lien du noeud
     * @param listeDeLiens la nouvelle valeur
     */
    public void setListeDeLiens(LinkedList<Liens> listeDeLiens) {
        this.listeDeLiens = listeDeLiens;
    }

    /**
     * ajoute un lien à la liste des liens
     * @param lienAjoute le lien à ajouter
     */
    public void ajoutLien(Liens lienAjoute) {
        this.listeDeLiens.add(lienAjoute);
    }

    /**
     * modifie la couleur du noeud
     * @param couleur la nouvelle valeur
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }//</editor-fold>   

    //<editor-fold defaultstate="collapsed" desc=" hashCode + equals + toString">
    @Override
    public String toString() {
        return typeNoeud + "," + nomNoeud + ":" + listeDeLiens;
    }

    public String toStringInfoNoeud() {
        return typeNoeud + "," + nomNoeud;
    }

    @Override
    public int hashCode() {
        int hash = 9;
        hash = 43 * hash + Objects.hashCode(this.typeNoeud);
        hash = 43 * hash + Objects.hashCode(this.nomNoeud);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Noeuds other = (Noeuds) obj;
        if (!Objects.equals(this.typeNoeud, other.typeNoeud)) {
            return false;
        }
        return Objects.equals(this.nomNoeud, other.nomNoeud);
    }//</editor-fold>

}
