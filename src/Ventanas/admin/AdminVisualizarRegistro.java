/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas.admin;

import Externos.CambiarPanel;
import ModeloTabla.EncabezadoTabla;
import ModeloTabla.GestionCeldas;
import ModeloTabla.ModeloTabla;
import ModeloTabla.Utilidades;
import Objetos.CamisaPlayera;
import Objetos.CompradorMayoreo;
import Objetos.ObAn;
import Vista.Plantilla;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author PC
 */
public class AdminVisualizarRegistro extends javax.swing.JPanel {

    /**
     * Creates new form AdminVisualizarRegistro
     */
    ArrayList<CompradorMayoreo> listaCompradorMayoreo;//lista que simula la información de la BD
    JScrollPane scrollPaneTabla;
    JTable tablaPersonas;

    ModeloTabla modelo;//modelo definido en la clase ModeloTabla
    //DefaultTableModel modelo2;

    private int filasTabla;
    private int columnasTabla;

    public AdminVisualizarRegistro() {
        initComponents();
        iniciarComponentes();
        /*modelo2 = new DefaultTableModel();
        modelo2.addColumn("ID");
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Telefono");
        modelo2.addColumn("Correo");
        modelo2.addColumn("Estado");
        modelo2.addColumn("Ciudad");
        modelo2.addColumn("Direccion");
        this.jTable1.setModel(modelo);*/

    }

    private void iniciarComponentes() {

        scrollPaneTabla = new JScrollPane();
        jPanel1.add(scrollPaneTabla);

        tablaPersonas = new JTable();
        tablaPersonas.setBackground(Color.WHITE);
        tablaPersonas.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        //tablaPersonas.addMouseListener(this);
        //tablaSeguimiento.addKeyListener(this);
        tablaPersonas.setOpaque(false);
        scrollPaneTabla.setViewportView(tablaPersonas);
    }

    public void construirTabla() {

        listaCompradorMayoreo = new SQL.Conexion().obtenerRegistroCompradoresMayoreo();
        ArrayList titulosList = new ArrayList<>();

        titulosList.add("ID");
        titulosList.add("Nombre");
        titulosList.add("Telefono");
        titulosList.add("Correo");
        titulosList.add("Estado");
        titulosList.add("Ciudad");
        titulosList.add("Direccion");
        //se asignan las columnas al arreglo para enviarse al momento de construir la tabla
        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = (String) titulosList.get(i);
        }
//obtenemos los datos de la lista y los guardamos en la matriz
//que luego se manda a construir la tabla

        Object[][] data = obtenerMatrizDatos(titulosList);
        construirTabla(titulos, data);

    }

    private Object[][] obtenerMatrizDatos(ArrayList titulosList) {

//se crea la matriz donde las filas son dinamicas pues corresponde
//a todos los usuarios, mientras que las columnas son estaticas
// correspondiendo a las columnas definidas por defecto
//
        String informacion[][] = new String[listaCompradorMayoreo.size()][titulosList.size()];

        for (int x = 0; x < informacion.length; x++) {

            informacion[x][Utilidades.id] = listaCompradorMayoreo.get(x).getId() + "";
            informacion[x][Utilidades.nombre] = listaCompradorMayoreo.get(x).getNombre() + "";
            informacion[x][Utilidades.telefono] = listaCompradorMayoreo.get(x).getTelefono() + "";
            informacion[x][Utilidades.correo] = listaCompradorMayoreo.get(x).getCorreo() + "";
            informacion[x][Utilidades.estado] = listaCompradorMayoreo.get(x).getEstado() + "";
            informacion[x][Utilidades.ciudad] = listaCompradorMayoreo.get(x).getCiudad() + "";
            informacion[x][Utilidades.direccion] = listaCompradorMayoreo.get(x).getDireccion() + "";

        }

        return informacion;
    }

    private void construirTabla(String[] titulos, Object[][] data) {
        // modelo = new ModeloTabla(data, titulos);
        //se asigna el modelo a la tabla
        //tratando de sobreescribir las columnas que seran editables
        modelo = new ModeloTabla(data, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //Si la columna es diferente de 
                if (column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        tablaPersonas.setModel(modelo);

        filasTabla = tablaPersonas.getRowCount();
        columnasTabla = tablaPersonas.getColumnCount();

        //se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
        tablaPersonas.getColumnModel().getColumn(Utilidades.id).setCellRenderer(new GestionCeldas("texto"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.nombre).setCellRenderer(new GestionCeldas("texto"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.telefono).setCellRenderer(new GestionCeldas("texto"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.correo).setCellRenderer(new GestionCeldas("texto"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.estado).setCellRenderer(new GestionCeldas("texto"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.ciudad).setCellRenderer(new GestionCeldas("texto"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.direccion).setCellRenderer(new GestionCeldas("texto"));

        tablaPersonas.getTableHeader().setReorderingAllowed(false);
        tablaPersonas.setRowHeight(40);//tamaño de las celdas
        tablaPersonas.setGridColor(new java.awt.Color(255, 255, 255));
        //Se define el tamaño de largo para cada columna y su contenido
        tablaPersonas.getColumnModel().getColumn(Utilidades.id).setPreferredWidth(20);//id
        tablaPersonas.getColumnModel().getColumn(Utilidades.nombre).setPreferredWidth(100);//nombre
        tablaPersonas.getColumnModel().getColumn(Utilidades.telefono).setPreferredWidth(100);//telefono
        tablaPersonas.getColumnModel().getColumn(Utilidades.correo).setPreferredWidth(100);//correo
        tablaPersonas.getColumnModel().getColumn(Utilidades.estado).setPreferredWidth(100);//estado
        tablaPersonas.getColumnModel().getColumn(Utilidades.ciudad).setPreferredWidth(100);//ciudad
        tablaPersonas.getColumnModel().getColumn(Utilidades.direccion).setPreferredWidth(100);//direccion

        //personaliza el encabezado
        JTableHeader jtableHeader = tablaPersonas.getTableHeader();
        jtableHeader.setFont(new Font("Verdana", Font.BOLD, 15));
        jtableHeader.setDefaultRenderer(new EncabezadoTabla());
        tablaPersonas.setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane
        scrollPaneTabla.setViewportView(tablaPersonas);

    }

    public void mostrarCompradoresMayoreo() {
        String datos[] = new String[10];
        listaCompradorMayoreo = new SQL.Conexion().obtenerRegistroCompradoresMayoreo();
        for (int i = 0; i < listaCompradorMayoreo.size(); i++) {

            datos[0] = String.valueOf(listaCompradorMayoreo.get(i).getId());
            datos[1] = listaCompradorMayoreo.get(i).getNombre();
            datos[2] = listaCompradorMayoreo.get(i).getTelefono();
            datos[3] = listaCompradorMayoreo.get(i).getCorreo();
            datos[4] = listaCompradorMayoreo.get(i).getEstado();
            datos[5] = listaCompradorMayoreo.get(i).getCiudad();
            datos[6] = listaCompradorMayoreo.get(i).getDireccion();
            modelo.addRow(datos);
        }

    }

    private void limpiarTabla() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Regresar-1.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Regresar-2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 4, 84, 85));

        jPanel1.setLayout(new java.awt.BorderLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1270, 490));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar-1.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar-2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 620, 264, 86));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Registro de compradores.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String[] info = new String[7];

        for (int i = 0; i < listaCompradorMayoreo.size(); i++) {

            info[0] = (String) modelo.getValueAt(i, 0);
            info[1] = (String) modelo.getValueAt(i, 1);
            info[2] = (String) modelo.getValueAt(i, 2);
            info[3] = (String) modelo.getValueAt(i, 3);
            info[4] = (String) modelo.getValueAt(i, 4);
            info[5] = (String) modelo.getValueAt(i, 5);
            info[6] = (String) modelo.getValueAt(i, 6);

            new SQL.Conexion().actualizarCompradorMayoreo(Integer.parseInt(info[0]), info[1], info[2], info[3], info[4], info[5], info[6]);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new CambiarPanel(Plantilla.jPanel1, ObAn.admin1);
        limpiarTabla();
        //mostrarCompradoresMayoreo();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
