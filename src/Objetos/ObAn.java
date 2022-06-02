/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Externos.CambiarPanel;
import Ventanas.login.Login;
import Ventanas.*;
import Ventanas.admin.*;
import Vista.Plantilla;
import Externos.TextPrompt;
import ModeloTabla.Utilidades;
import PopUps.PopUpError;
import PopUps.VentanaDialogo;
import PopUps.VentanaDialogo2;

import Ventanas.usuario.*;
import Vista.RestablecerContraseña;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ObAn {
    public static Image img;
    public static ImageIcon imgi;
    //Creacion de todas las ventanas y clases que me serviran.
    public static TextPrompt txtp;
    public static Login login = new Login();
    public static Register register = new Register();

    //Restablecer contraseña.
    public static Restablecer restablecer = new Restablecer();
    public static Restablecer2 restablecer2 = new Restablecer2();
    public static Restablecer3 restablecer3 = new Restablecer3();

    //Usuario.
    public static User user = new User();
    public static Usuario usuario = new Usuario();
    public static Catalogo1 catalogo1 = new Catalogo1();
    public static Catalogo2 catalogo2 = new Catalogo2();
    public static VisualizarProducto visualizarProducto = new VisualizarProducto();
    public static Ventas ventas = new Ventas();
    public static RegistroTarjeta registroTarjeta = new RegistroTarjeta();
    public static SeleccionarMetodoPago seleccionarMetodoPago = new SeleccionarMetodoPago();
     public static SeleccionarTarjeta seleccionarMetodoPago2 = new SeleccionarTarjeta();
    public static Carrito2 carrito2 = new Carrito2();
    public static PanelCarrito panelCarrito = new PanelCarrito();
    public static Contactanos contactanos = new Contactanos();
    public static CatalogoUV catalogoUV = new CatalogoUV();
    public static TarjetasDebito tarjetasDebito = new TarjetasDebito();
    public static Agradecimiento agradecimiento = new Agradecimiento();
    public static MisCompras misCompras = new MisCompras();
    public static GuiaTallas guiaTallas = new GuiaTallas();
    public static PreguntasFrecuentes preguntasFrecuentes = new PreguntasFrecuentes();
    
   

    //Administrador.
    public static Admin admin1 = new Admin();
    public static AdminModificar admin2 = new AdminModificar();
    public static AdminTec admin3 = new AdminTec();
    public static AdminRegistrarComprador adminRegistrarComprador = new AdminRegistrarComprador();
    public static AdminRegistrarComprador2 adminRegistrarComprador2 = new AdminRegistrarComprador2();
    public static AdminVisualizarRegistro adminVisualizarRegistro = new AdminVisualizarRegistro();
    public static AdminAgregarCarrera adminAgregarCarrera = new AdminAgregarCarrera();
    public static AdminVentas adminVentas = new AdminVentas();
    
    //Administrador Tecnologico PLAN B
    public static AdminElegirCarrera adminElegirCarrera = new AdminElegirCarrera();
    public static PanelElegirTipoCamisa adminElegirTipoCamisa = new PanelElegirTipoCamisa();
    public static PanelEditarProducto adminEditarProducto = new PanelEditarProducto();
    
    
    //Administrador UV
    public static AdminElegirCarreraUV adminEelgirCarreraUV = new AdminElegirCarreraUV();

    //Objetos inventario
    public static Inventario inventario = new Inventario();
    public static Inventario inventarioUsuario = new Inventario("ITSPR", "Ing. en sistemas");
    
  

    //Metodos que me ayudaran con el inventario
    public static void mostrarInformacion(Inventario inventario, int opcion) {
        
        if(opcion == 1){
        ObAn.adminEditarProducto.labelImagen.setIcon(conversionFoto(inventario.getFoto()));        
        ObAn.adminEditarProducto.labelTitulo.setText(inventario.getTitulo());
        ObAn.adminEditarProducto.labelDescripcion.setText(inventario.getDescripcion());
        ObAn.adminEditarProducto.txtFieldCantidad.setText(String.valueOf(inventario.getStock()));    
        }
        else if(opcion == 2){
              
        ObAn.adminEditarProducto.labelTitulo.setText(inventario.getTitulo());
        ObAn.adminEditarProducto.labelDescripcion.setText(inventario.getDescripcion());
        ObAn.adminEditarProducto.txtFieldCantidad.setText(String.valueOf(inventario.getStock()));  
        }
        
    }
    
    public static void mostrarInformacionUsuario(Inventario inventario, int opcion){
         if(opcion == 1){
        ObAn.visualizarProducto.labelImagen.setIcon(conversionFoto(inventario.getFoto()));        
        ObAn.visualizarProducto.labelTitulo.setText(inventario.getTitulo());
        ObAn.visualizarProducto.labelDescripcion.setText(inventario.getDescripcion());
        ObAn.visualizarProducto.labelStock.setText(String.valueOf(inventario.getStock()));
         
        }
        else if(opcion == 2){
              
        ObAn.visualizarProducto.labelTitulo.setText(inventario.getTitulo());
        ObAn.visualizarProducto.labelDescripcion.setText(inventario.getDescripcion());
        ObAn.visualizarProducto.labelStock.setText(String.valueOf(inventario.getStock()));
        }
    }
    
    public static void mostrarDatosParaVisualizarProducto(){
        ObAn.adminEditarProducto.inventario = new SQL.Conexion().obtenerStock(ObAn.inventario.getEscuela(), ObAn.inventario.getCarrera(), ObAn.inventario.getTipo(), ObAn.inventario.getSexo(), ObAn.inventario.getTalla());
        ObAn.mostrarInformacion(ObAn.adminEditarProducto.inventario, 1);
        new CambiarPanel(Plantilla.jPanel1, ObAn.adminEditarProducto);
    }

    public static ImageIcon conversionFoto(byte[] arrayBits) {
        ImageIcon imgi = null;
        byte[] bi = arrayBits;
        System.out.println(bi);
        BufferedImage image = null;
        try {
            InputStream in = new ByteArrayInputStream(bi);
            image = ImageIO.read(in);
            imgi = new ImageIcon(image);
            

        } catch (Exception ex) {
            System.out.println("Error imagen");
        }
        return imgi;
        
    }
    
     
    
    public static void colocarFoto(JLabel label, int id){
        img = ObAn.conversionFoto(new SQL.Conexion().obtenerStockFoto(id)).getImage();
        imgi = new ImageIcon(img.getScaledInstance(215, 240, Image.SCALE_SMOOTH));
        label.setIcon(imgi);
    }
    
    
    //Metodos para corregir fecha y hacerlo mas presentable
    public static String obtenerFecha(String fechaParametro){
        
        String fechaIncompleta;
        String año = fechaParametro.substring(0, 4);
        String mes = fechaParametro.substring(5, 7);
        mes = convertirMes(mes);
        String dia = fechaParametro.substring(8, 10);
        
        fechaIncompleta = dia + " de " + mes + " del " + año;
        return fechaIncompleta;
    }
    
    public static String obtenerHora(String fechaParametro){
        String horaIncompleta;
        String hora = fechaParametro.substring(11, 13);
        String minutos = fechaParametro.substring(14, 16);
        
        horaIncompleta = hora + ":" + minutos;
        
        return horaIncompleta;
        
    }
    
    public static String convertirMes(String mes){
        String mesIncompleto = "";
        int numero = Integer.parseInt(mes);
        switch(numero){
            case 1:
                mesIncompleto = "enero";
                break;
            case 2:
                mesIncompleto = "febrero";
                break;
            case 3:
                mesIncompleto = "marzo";
                break;
            case 4:
                mesIncompleto = "abril";
                break;
            case 5:
                mesIncompleto = "mayo";
                break;
            case 6:
                mesIncompleto = "junio";
                break;
            case 7:
                mesIncompleto = "julio";
                break;
            case 8:
                mesIncompleto = "agosto";
                break;
            case 9:
                mesIncompleto = "septiembre";
                break;
            case 10:
                mesIncompleto = "octubre";
                break;
            case 11:
                mesIncompleto = "noviembre";
                break;
            case 12:
                mesIncompleto = "diciembre";
                break;
                
            default:
                mes = "No existe este mes";
        }
        return mesIncompleto;
    }
    

    //POOOOOOOOOOOOOOOP UPS.
    public static VentanaDialogo2 vd2 = new VentanaDialogo2();
    
    //En estas dos lineas marcaba error porque yo estaba tratando de crear la ventana que funcionara como plantilla
    //para mostrar errores primero, al momento de que se crea esta plantilla se le agrega inmediatamente un panel
    //sobre esta ventana pero ¡OH, SORPRESA! ese panel NO EXISTE JAJAJAJAJA.
    //  Solucion: crear primero el panel que vamos a agregar pendejo. xd
    //            Es por eso que primero puse la linea de creacion de panel.
    public static PopUpErrorPanel popUpErrorPanel = new PopUpErrorPanel(); //Creacion del panel
    public static PopUpError popUpErrorPlantilla = new PopUpError();       //Creacion de la ventana
    
    

    //Creacion de objeto para perosnalizar la tabla
    //lo tuve q borrar lo lamento xd
    //Creacion de animaciones con los objetos que cree anteeriormente.
    public static void placeHolder(String texto, JTextField txtField) {

        txtp = new TextPrompt(texto, txtField);
    }

    public static void animacionLoginToHomeAdmin() {
        Animacion.Animacion.mover_izquierda(0, -1270, 10, 127, login);
        Plantilla.jPanel1.add(Objetos.ObAn.admin1);
        Plantilla.jPanel1.validate();
        Animacion.Animacion.mover_izquierda(1270, 0, 10, 127, admin1);

    }

    public static void animacionLoginToHomeUser() {
        Objetos.ObAn.user.setVisible(false);
        Animacion.Animacion.mover_izquierda(0, -1270, 10, 127, login);
        Plantilla.jPanel1.add(Objetos.ObAn.user);
        Objetos.ObAn.user.setVisible(true);
        Plantilla.jPanel1.validate();
        Animacion.Animacion.mover_izquierda(1270, 0, 10, 127, user);
    }
    
    public static void animacionCatalogo(JPanel catalogo1,JPanel catalogo2){
        Animacion.Animacion.mover_izquierda(0, -1270, 10, 127, catalogo1);
        user.contenedor.add(catalogo2);
        user.contenedor.validate();
        Animacion.Animacion.mover_izquierda(1270, 0, 10, 127, catalogo2);
        
    }
}
