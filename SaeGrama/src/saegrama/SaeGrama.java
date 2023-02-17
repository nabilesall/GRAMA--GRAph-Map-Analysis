/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saegrama;

//<editor-fold defaultstate="collapsed" desc=" IMPORT">
import grama.classes.models.Graphe;
import grama.classes.fenetre.AfficherGraphe;
import java.io.File;
//</editor-fold>

/**
 *
 * @author Aro et Idrissa
 */
public class SaeGrama {

    //<editor-fold defaultstate="collapsed" desc=" MAIN">
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initLookAndFeel(LookAndFeel.WINDOWS);
        
        /*Si on veut pas utiliser l'interface pour choisir un fichier
        on remplace le new graphe par l'instance qu'on vient de créer
        */
        //Graphe graphe = new Graphe("SAE GRAMA.csv");
        new AfficherGraphe(new Graphe()).setVisible(true);

    }
    
    /**
     * Initialise le Look And Feel (le type d'affichage de la fenêtre)
     *
     * @param lookAndFeel Correspond au Look And Feel utilisé
     */
    private static void initLookAndFeel(LookAndFeel lookAndFeel) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.toString().equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaeGrama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
    
}