/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grama.classes.models;

//<editor-fold defaultstate="collapsed" desc=" IMPORTS">
import java.awt.Color;
import java.util.Objects;
//</editor-fold>

/**
 * @author Aro et Idrissa
 */
public class Liens {

    //<editor-fold defaultstate="collapsed" desc=" ATTRIBUTS">
    /**
     * Correspond au type du premier noeud lié par le lien
     */
    private String typeNoeudLie1;
    
    /**
     * correspond au premier noeud lié par le lien
     */
    private String nomNoeudLie1;
    
    /**
    * Correspond au type du lien
    */
    private String typeLien;
    
    /**
     * correspond à la couleur du lien
     */
    private Color couleur;
    
    /**
     * correspond au poids du lien c'est à dire la distance en km
     */
    private int poidsLien;
    
    /**
     * Correspond au type du deuxième noeud lié par le lien
     */
    private String typeNoeudLie2;
    
    /**
     * correspond au deuxième noeud lié par le lien
     */
    private String nomNoeudLie2;
    
    private Graphe graphe;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" CONSTRCUTEURS">
    
    /**
     * crée un objet lien
     * @param typeNoeudLie1 le type du premier noeud
     * @param nomNoeudLie1 le nom du premier noeud
     * @param typeLien le type du lien
     * @param poidsLien la distance entre les deux noeuds
     * @param typeNoeudLie2 le type du deuxième noeud
     * @param nomNoeudLie2  le nom du deuxième noeud
     */
    public Liens(String typeNoeudLie1, String nomNoeudLie1, String typeLien, int poidsLien, String typeNoeudLie2, String nomNoeudLie2) {
        this.typeNoeudLie1 = typeNoeudLie1;
        this.nomNoeudLie1 = nomNoeudLie1;
        this.typeLien = typeLien;
        this.poidsLien = poidsLien;
        this.typeNoeudLie2 = typeNoeudLie2;
        this.nomNoeudLie2 = nomNoeudLie2;
        if (typeLien.equalsIgnoreCase("A")) {
            couleur = Color.blue;
        } else {
            if (typeLien.equalsIgnoreCase("D")) {
                couleur = Color.MAGENTA;
            } else {
                if (typeLien.equalsIgnoreCase("N")) {
                    couleur = Color.LIGHT_GRAY;
                }
            }
        }
    }

    /**
     * crée un objet lien
     * @param typeNoeudLie1 le type du premier noeud
     * @param nomNoeudLie1 le nom du premier noeud
     * @param typeLien le type du lien
     * @param poidsLien la distance entre les deux noeuds
     * @param typeNoeudLie2 le type du deuxième noeud
     * @param nomNoeudLie2 le nom du deuxieme noeud
     * @throws NumberFormatException 
     */
    public Liens(String typeNoeudLie1, String nomNoeudLie1, String typeLien, String poidsLien, String typeNoeudLie2, String nomNoeudLie2) throws NumberFormatException {
        this.typeNoeudLie1 = typeNoeudLie1;
        this.nomNoeudLie1 = nomNoeudLie1;
        this.typeLien = typeLien;
        this.poidsLien = Integer.parseInt(poidsLien);
        this.typeNoeudLie2 = typeNoeudLie2;
        this.nomNoeudLie2 = nomNoeudLie2;
        if (typeLien.equalsIgnoreCase("A")) {
            couleur = Color.blue;
        } else {
            if (typeLien.equalsIgnoreCase("D")) {
                couleur = Color.MAGENTA;
            } else {
                if (typeLien.equalsIgnoreCase("N")) {
                    couleur = Color.LIGHT_GRAY;
                }
            }
        }
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" GETTERS">
    /**
     * @return le type du premier noeud
     */
    public String getTypeNoeudLie1() {
        return typeNoeudLie1;
    }

    /**
     * @return le nom du premier noeud
     */
    public String getNomNoeudLie1() {
        return nomNoeudLie1;
    }

    /**
     * @return le type du lien
     */
    public String getTypeLien() {
        return typeLien;
    }

    /**
     * @return la distance du lien
     */
    public int getPoidsLien() {
        return poidsLien;
    }

    /**
     * @return le type du deuxième noeud
     */
    public String getTypeNoeudLie2() {
        return typeNoeudLie2;
    }

    /**
     * @return le nom du deuxième noeud
     */
    public String getNomNoeudLie2() {
        return nomNoeudLie2;
    }

    /**
     * @return la couleur du lien
     */
    public Color getCouleur() {
        return couleur;
    }
    
    public Noeuds getNoeudDes(){
        return graphe.getListeDeNoeuds().get(graphe.getIndiceNoeud(nomNoeudLie2));
    }

    //</editor-fold>

    ///<editor-fold defaultstate="collapsed" desc=" SETTERS">
    /**
     * modifie le type du premier noeud
     * @param typeNoeudLie1 la nouvelle valeur
     */
    public void setTypeNoeudLie1(String typeNoeudLie1) {
        this.typeNoeudLie1 = typeNoeudLie1;
    }

    /**
     * modifie le nom du premier noeud
     * @param nomNoeudLie1 la nouvelle valeur
     */
    public void setNomNoeudLie1(String nomNoeudLie1) {
        this.nomNoeudLie1 = nomNoeudLie1;
    }

    /**
     * modifie le type du lien
     * @param typeLien la nouvelle valeur
     */
    public void setTypeLien(String typeLien) {
        this.typeLien = typeLien;
    }

    /**
     * modifie le type du deuxième noeud
     * @param typeNoeudLie2 la nouvelle valeur
     */
    public void setTypeNoeudLie2(String typeNoeudLie2) {
        this.typeNoeudLie2 = typeNoeudLie2;
    }

    /**
     * modifie le nom du deuxième noeud
     * @param nomNoeudLie2 la nouvelle valeur
     */
    public void setNomNoeudLie2(String nomNoeudLie2) {
        this.nomNoeudLie2 = nomNoeudLie2;
    }

    /**
     * modifie la distance du lien
     * @param poidsLien la nouvelle valeur
     */
    public void setPoidsLien(int poidsLien) {
        this.poidsLien = poidsLien;
    }

    /**
     * modifie la couleur du lien
     * @param couleur la nouvelle valeur
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /**
     *Convertis la distance en int
     * @param poidsLien
     * @throws NumberFormatException
     */
    public void setPoidsLien(String poidsLien) throws NumberFormatException {
        this.poidsLien = Integer.parseInt(poidsLien);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" hashCode + equals + toString">
    @Override
    public int hashCode() {
        int hash = 4;
        hash = 53 * hash + Objects.hashCode(this.typeNoeudLie1);
        hash = 53 * hash + Objects.hashCode(this.nomNoeudLie1);
        hash = 53 * hash + Objects.hashCode(this.typeLien);
        hash = 53 * hash + this.poidsLien;
        hash = 53 * hash + Objects.hashCode(this.typeNoeudLie2);
        hash = 53 * hash + Objects.hashCode(this.nomNoeudLie2);
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
        final Liens other = (Liens) obj;
        return ((this.nomNoeudLie1.equals(other.nomNoeudLie1) && this.typeNoeudLie1.equals(other.typeNoeudLie1) && this.nomNoeudLie2.equals(other.nomNoeudLie2) && this.typeNoeudLie2.equals(other.typeNoeudLie2)) || (this.nomNoeudLie2.equals(other.nomNoeudLie1) && this.typeNoeudLie2.equals(other.typeNoeudLie1) && this.nomNoeudLie1.equals(other.nomNoeudLie2) && this.typeNoeudLie1.equals(other.typeNoeudLie2))) && this.typeLien.equals(other.typeLien) && this.poidsLien == other.poidsLien;
    }

    //ToString
    @Override
    public String toString() {
        return typeNoeudLie1 + "," + nomNoeudLie1 + "::" + typeLien
                + "," + poidsLien + "::" + typeNoeudLie2 + "," + nomNoeudLie2;
    }

    public String toStringInfoNoeudLie1() {
        return typeNoeudLie1 + "," + nomNoeudLie1;
    }

    public String toStringInfoLien() {
        return typeLien + "," + poidsLien;
    }

    public String toStringInfoNoeudLie2() {
        return typeNoeudLie2 + "," + nomNoeudLie2;
    }

    public String toStringInfoNoeudLie() {
        return typeNoeudLie1 + "," + nomNoeudLie1 + "-" + typeNoeudLie2 + "," + nomNoeudLie2;
    }//</editor-fold>

}
