/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PopUps;

import Objetos.ObAn;
import Vista.Plantilla;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class VentanaDialogo extends JDialog {
    
    JPanel jpanel = null;
    public VentanaDialogo(Plantilla plantilla, boolean modal){
        super(plantilla, modal);
        setSize(825, 520);
        setLocationRelativeTo(null);
        setVisible(true);
        jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setVisible(true);
        add(jpanel);
        jpanel.add(ObAn.restablecer); 
    }
}
