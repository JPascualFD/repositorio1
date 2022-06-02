/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Externos;

/**
 *
 * @author Pahcual
 */
import javax.swing.JPanel;

public class CambiarPanel {
    //
    private final JPanel container;
    private final JPanel content;


    /**
     * Constructor de clase
     * @param container
     * @param content
     */
    public CambiarPanel(JPanel container, JPanel content) {
        this.container = container;
        this.content = content;
        this.container.removeAll();
        this.container.revalidate();
        this.container.repaint();
        
        this.container.add(this.content);
        this.container.revalidate();
        this.container.repaint();
    }

}//--> fin clase
