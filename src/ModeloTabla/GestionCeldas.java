/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTabla;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;

//
// Esta clase permite gestionar la tabla y los eventos realizados sobre ella
// cada celda seria un objeto personalizable
// @author CHENAO
// 
//
public class GestionCeldas extends DefaultTableCellRenderer {

    private String tipo = "text";

    //se definen por defecto los tipos de datos a usar
    private Font regular = new Font("Rammetto", Font.BOLD, 14);

    //etiqueta que almacenará el icono a mostrar
    private JLabel label = new JLabel();
    //iconos disponibles para ser mostrados en la etiqueta dependiendo de la columna que lo contenga
    private ImageIcon iconoEliminar = new ImageIcon("src/Imagenes/eliminar.png");
    public ImageIcon iconoUniforme;
    //private ImageIcon iconoBuscar = new ImageIcon(getClass().getResource("/recursos/iconos/ico_buscar.png"));

    public GestionCeldas() {

    }

//
//constructor explicito con el tipo de dato que tendrá la celda
// @param tipo
//
    public GestionCeldas(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

//
//Este metodo controla toda la tabla, podemos obtener el valor que contiene
// definir que celda está seleccionada, la fila y columna al tener el foco en ella.
// 
// cada evento sobre la tabla invocará a este metodo
//
        //definimos colores por defecto
        Color colorFondo = null;
        Color colorFondoPorDefecto = new Color(170, 170, 170);
        Color colorFondoSeleccion = new Color(124, 124, 124);

        /*
         * Si la celda del evento es la seleccionada se asigna el fondo por defecto para la selección
         */
        if (selected) {
            this.setBackground(colorFondoPorDefecto);
        } else {
            //Para las que no están seleccionadas se pinta el fondo de las celdas de blanco
            this.setBackground(Color.white);
        }

        /*
         * Se definen los tipos de datos que contendrán las celdas basado en la instancia que
         * se hace en la ventana de la tabla al momento de construirla
         */
        if (tipo.equals("texto")) {
            //si es tipo texto define el color de fondo del texto y de la celda así como la alineación
            if (focused) {
                colorFondo = colorFondoSeleccion;
            } else {
                colorFondo = colorFondoPorDefecto;
            }
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setText((String) value);
            //this.setForeground( (selected)? new Color(87, 98, 90) :new Color(0,0,0) );   
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );
            this.setBackground((selected) ? colorFondo : new Color (238, 238, 238)); //aqui iba "white"
            this.setFont(regular);
            //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            //this.setFont(bold);
            return this;
        }

        //si el tipo es icono entonces valida cual icono asignar a la etiqueta.
        if (tipo.equals("icono")) {
           
                label.setBackground(Color.white);
                label.setIcon(iconoEliminar);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                
            /*
            if( String.valueOf(value).equals("PERFIL") )
            {
             label.setIcon(iconoBuscar);
            }
            else if( String.valueOf(value).equals("EVENTO") )
            {
             label.setIcon(iconoGuardar);
            }*/
//            return boton;
            return label;
        }

        //definie si el tipo de dato el numerico para personalizarlo
        if (tipo.equals("numerico")) {
            if (focused) {
                colorFondo = colorFondoSeleccion;
            } else {
                colorFondo = colorFondoPorDefecto;
            }
            // System.out.println(value);
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setText((String) value);
            this.setForeground((selected) ? new Color(255, 255, 255) : new Color(32, 117, 32));
            this.setBackground((selected) ? colorFondo : new Color(238, 238, 238));
            // this.setBackground( (selected)? colorFondo :Color.MAGENTA);
            this.setFont(regular);
            return this;
        }
        
        if(value instanceof JLabel){
            JLabel lbl = (JLabel)value;
            
            return lbl;
        }

        return this;
    }
}
