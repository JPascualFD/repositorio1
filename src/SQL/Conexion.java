/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import Objetos.CamisaPlayera;
import Objetos.Carrito;
import Objetos.CompradorMayoreo;
import Objetos.Inventario;
import Objetos.TarjetasDebito;
import Objetos.Usuario;
import Objetos.Ventas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class Conexion {

    Connection con;
    Statement st = null;
    ResultSet rs = null;
    String query;

    String conexionUrl = "jdbc:sqlserver://localhost: 1433;"
            + "database = Bijou;"
            + "user = sa;"
            + "password = Bobesponja10;"
            + "loginTimeout = 30";

    //Realizo la conecsion cada que se cree un objeto de esta clase ¿pq? pq ya le se al void.
    public Conexion() {
        try {
            con = DriverManager.getConnection(conexionUrl);
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }

    //M E T O D O S para realizar consultas pa'.
    public Usuario obtenerDatosUsuario(String email, String password) {
        query = "SELECT * FROM Usuarios WHERE  email = '" + email + "'AND pasword= '" + password + "'";
        Usuario usuario = new Usuario();
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setEmail(rs.getString(5));
                usuario.setIdentificador(rs.getInt(6));
                usuario.setCelular(rs.getString(7));

            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return usuario;
    }
    
    public Usuario obtenerDatosUsuario(int idUsuario) {
        query = "SELECT * FROM Usuarios WHERE  IdUsuario = " + idUsuario;
        Usuario usuario = new Usuario();
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setEmail(rs.getString(5));
                usuario.setIdentificador(rs.getInt(6));
                usuario.setCelular(rs.getString(7));

            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return usuario;
    }

    public boolean verificarContraseña(String email, String password) {
        query = "SELECT * FROM Usuarios WHERE email = '" + email + "' AND pasword = '" + password + "'";
        boolean existe = false;
        try {
            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            //Lo que se almacena en rs es el conjunto de valores almacenados en la tabla
            //Dependiendo de la sentencia que hay en la query. xd
            //rs.next() me ayuda a posicionarle en la fila 1 de mi tabla y devuelve true o false (dependiendo si puede ir al sig. registro)
            while (rs.next()) {
                //rs.getString me devuelve lo que hay almacenado en la columna numero 1.
                if (rs.getString(1) != null) {
                    existe = true;
                } else {
                    existe = false;
                }

            }
        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return existe;
    }

    public boolean verificarExistenciaUsuario(String email) {

        //Declaracion de variable booleana para determinar si existe o no.
        boolean existe = false;
        boolean administrador = false;

        query = "SELECT * FROM Usuarios WHERE email = '" + email + "'";

        try {
            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            //Lo que se almacena en rs es el conjunto de valores almacenados en la tabla
            //Dependiendo de la sentencia que hay en la query. xd
            //rs.next() me ayuda a posicionarle en la fila 1 de mi tabla y devuelve true o false (dependiendo si puede ir al sig. registro)
            while (rs.next()) {
                //rs.getString me devuelve lo que hay almacenado en la columna numero 1.
                if (rs.getString(1) != null) {
                    existe = true;
                    if (rs.getInt("identificador") == 1) {
                        administrador = true;
                    }
                } else {
                    existe = false;
                }

            }
        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }

        Ventanas.login.Login.loginAdminOrUser = administrador;
        return existe;
    }

    public boolean verificarExistenciaTarjeta(int idUsuario) {
        boolean existe = false;
        query = "SELECT * FROM TarjetasDebito WHERE idUsuario = " + idUsuario;

        try {
            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            //Lo que se almacena en rs es el conjunto de valores almacenados en la tabla
            //Dependiendo de la sentencia que hay en la query. xd
            //rs.next() me ayuda a posicionarle en la fila 1 de mi tabla y devuelve true o false (dependiendo si puede ir al sig. registro)
            while (rs.next()) {
                //rs.getString me devuelve lo que hay almacenado en la columna numero 1.
                if (rs.getString(1) != null) {
                    existe = true;

                } else {
                    existe = false;
                }

            }
        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return existe;
    }

    public boolean verificarExistenciaTarjeta(String numeroTarjeta) {
        boolean existe = false;
        query = "SELECT * FROM TarjetasDebito WHERE NumeroTarjeta = '" + numeroTarjeta + "'";

        try {
            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            //Lo que se almacena en rs es el conjunto de valores almacenados en la tabla
            //Dependiendo de la sentencia que hay en la query. xd
            //rs.next() me ayuda a posicionarle en la fila 1 de mi tabla y devuelve true o false (dependiendo si puede ir al sig. registro)
            while (rs.next()) {
                //rs.getString me devuelve lo que hay almacenado en la columna numero 1.
                if (rs.getString(1) != null) {
                    existe = true;

                } else {
                    existe = false;
                }

            }
        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return existe;
    }

    public ArrayList obtenerRegistroTarjetas(int idUsuario) {
        query = "SELECT * FROM TarjetasDebito WHERE IdUsuario = " + idUsuario + " ;";
        ArrayList<TarjetasDebito> listaTarjetas = new ArrayList<TarjetasDebito>();
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                listaTarjetas.add(new TarjetasDebito(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return listaTarjetas;
    }

    public void insertarTarjeta(String numeroTarjeta, String metodoPago, String nombreTitular, String fechaExpiracion, int CVC, int idUsuario) {
        query = "INSERT INTO TarjetasDebito VALUES ('" + numeroTarjeta + "','" + metodoPago + "', '" + nombreTitular + "' ,'" + fechaExpiracion + "'," + CVC + "," + idUsuario + ");";
        boolean existe;
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
    }

    public void identificarUsuarioOrAdministrador() {

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
    }

    //Consulta para insercion de nuevos clientes
    public void insertarCliente(String nombre, String apellidos, String pasword, String email, String telefono) {

        query = "INSERT INTO Usuarios VALUES ('" + nombre + "','" + apellidos + "', '" + pasword + "' ,'" + email + "'," + 0 + ", '" + telefono + "');";
        boolean existe;
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }

    }
    //Esta consulta esta oxidada, la utilizare para otras cosas mas PUERCAS.
    public int obtenerStock(int idProducto) {
        int stock = 0;
        query = "SELECT Stock FROM Inventario WHERE IdProducto = "+  idProducto +  ";";
        
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                stock = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return stock;
    }
    //este metodo estaba inservible asi q toca usarlo.
    public void actualizarStock(int idProducto, int stock) {
        query = "UPDATE Inventario SET Stock = " + stock +  " WHERE IdProducto = " + idProducto + ";";
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
    }

    public void insertarCompradorMayoreo(String nombre, String telefono, String correo, String Estado, String Ciudad, String Direccion) {
        query = "INSERT INTO COMPRADORESMAYOREO VALUES ('" + nombre + "','" + telefono + "', '" + correo + "' ,'" + Estado + "','" + Ciudad + "', '" + Direccion + "');";
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }

    }

    public void actualizarCompradorMayoreo(int id, String nombre, String telefono, String correo, String estado, String ciudad, String direccion) {
        query = "UPDATE COMPRADORESMAYOREO SET Nombre = " + nombre + ", CamisaMC = " + telefono + ", Playera = " + correo + ", Estado = " + estado + ", Ciudad = " + ciudad + ", Direccion = " + direccion + " WHERE id = " + id + ";";
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }

    }

    public ArrayList obtenerRegistroCompradoresMayoreo() {
        query = "SELECT * FROM COMPRADORESMAYOREO;";
        ArrayList<CompradorMayoreo> listaCompradorMayoreo = new ArrayList<CompradorMayoreo>();
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                listaCompradorMayoreo.add(new CompradorMayoreo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return listaCompradorMayoreo;
    }

    //Metodos para el inventario PLAN B
    public Inventario obtenerStock(String escuela, String carrera, String tipo, String sexo, int talla) {
        query = "SELECT * FROM Inventario WHERE Carrera = '" + carrera + "'AND Escuela = '" + escuela + "' AND Tipo = '" + tipo + "' AND Sexo = '" + sexo + "' AND Talla = " + talla;
        Inventario inventario1 = new Inventario();
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                inventario1.setIdProducto(rs.getInt(1));
                inventario1.setTitulo(rs.getString(2));
                inventario1.setTipo(rs.getString(3));
                inventario1.setSexo(rs.getString(4));
                inventario1.setTalla(rs.getInt(5));
                inventario1.setColor(rs.getString(6));
                inventario1.setEscuela(rs.getString(7));
                inventario1.setCarrera(rs.getString(8));
                inventario1.setStock(rs.getInt(9));
                inventario1.setMateriales(rs.getString(10));
                inventario1.setDescripcion(rs.getString(11));
                inventario1.setPrecio(rs.getInt(12));
                inventario1.setFoto(rs.getBytes(13));
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return inventario1;
    }

    public byte[] obtenerStockFoto(int id) {
        byte[] foto = null;
        query = "SELECT Foto FROM Inventario WHERE IdProducto =" + id;
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {

                foto = rs.getBytes(1);
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return foto;
    }
    //Buscar foto de acuerdo a los siguientes parametros y siempre se buscara la foto con talla 32 por defecto
    public byte[] obtenerStockFoto(String escuela, String carrera, String tipo, String sexo){
        byte[] foto = null;
        query = "SELECT Foto FROM Inventario WHERE Escuela = '" + escuela + "' AND Carrera = '" + carrera + "' AND Tipo = '" + tipo + "' AND Sexo = '" + sexo + "' AND Talla = 32";
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {

                foto = rs.getBytes(1);
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return foto;
    }

    public void actualizarStock(String escuela, String carrera, String tipo, String sexo, int talla, int stock) {
        query = "UPDATE Inventario SET Stock = " + stock + "WHERE Carrera = '" + carrera + "'AND Escuela = '" + escuela + "' AND Tipo = '" + tipo + "' AND Sexo = '" + sexo + "' AND Talla = " + talla;
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
    }

    public void agregarVenta(int unidades, String fechaVenta, int precioVenta, int idProducto, int idUsuario) {
        query = "INSERT INTO Ventas VALUES (" + unidades + ", " + precioVenta + ",'" + fechaVenta + "'," + idProducto + ", " + idUsuario + ");";
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
    }

    public ArrayList obtenerVenta() {
        query = "SELECT * FROM Ventas;";
        ArrayList<Ventas> listaVentas = new ArrayList<Ventas>();
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                listaVentas.add(new Ventas(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        return listaVentas;
    }

    //Obtencion de datos para el pdf, solo genero el reporte de los elementos que se encuentren 
    //en el carrito, no genero reporte de TODAS LAS COMPRAS QUE HA HECHO, solo las que esta comprando
    //en el momento, osea las que esten en el carritou. uwu
    public ArrayList obtenerDatosPDF(int idUsuario, int tamañoArrayList) {
        //La query que debo usar es : SELECT TOP 2 * FROM Ventas ORDER BY IdVenta DESC;
        query = "SELECT TOP "+ tamañoArrayList + " * FROM Ventas INNER JOIN Inventario ON Ventas.IdProducto = Inventario.IdProducto WHERE idUsuario =" + idUsuario + " ORDER BY IdVenta DESC";
        //query = "SELECT * FROM Ventas INNER JOIN Inventario ON Ventas.IdProducto = Inventario.IdProducto WHERE Ventas.idUsuario =" + idUsuario + ";";
        ArrayList <Carrito> listaDatosPDF= new ArrayList <Carrito>();
        Carrito carrito;
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                carrito = new Carrito();
                carrito.setIdVenta(rs.getInt(1));
                carrito.setUnidades(rs.getInt(2));
                carrito.setPrecioVenta(rs.getInt(3));
                carrito.setFechaVenta(rs.getString(4));
                carrito.setIdProducto(rs.getInt(5));
                carrito.setIdUsuario(rs.getInt(6));
                carrito.setTitulo(rs.getString(8));
                carrito.setTipo(rs.getString(9));
                carrito.setSexo(rs.getString(10));
                carrito.setTalla(rs.getInt(11));
                carrito.setColor(rs.getString(12));
                carrito.setEscuela(rs.getString(13));
                carrito.setCarrera(rs.getString(14));
                carrito.setStock(rs.getInt(15));
                carrito.setMateriales(rs.getString(16));
                carrito.setDescripcion(rs.getString(17));
                carrito.setPrecio(rs.getInt(18));
                carrito.setFoto(rs.getBytes(19));
                
                
                listaDatosPDF.add(carrito);
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        System.out.println("EN LA CONSULTA-----------------");
        for (int i = 0; i < listaDatosPDF.size(); i++) {
            System.out.println("carrito: " + i + " = " +  listaDatosPDF.get(i).getDescripcion());
        }
        System.out.println("TERMINO CONSULTAAAAAAAA----------------");
        
        return listaDatosPDF;
    }
    
        //A diferencia del anterior metodo, aqui SI OBTENGO TODAS LAS COMPRAS QUE HA HECHO 
        //el usuario hasta el momento, este metodo devuelve el historial de compras. (le agregue que se ordenara de mayor a menor)
      public ArrayList obtenerHistorialCompras(int idUsuario) {
        //La query que debo usar es : SELECT TOP 2 * FROM Ventas ORDER BY IdVenta DESC;
        query = "SELECT * FROM Ventas INNER JOIN Inventario ON Ventas.IdProducto = Inventario.IdProducto WHERE Ventas.idUsuario =" + idUsuario + " ORDER BY FechaVenta DESC;";
        //query = "SELECT * FROM Ventas INNER JOIN Inventario ON Ventas.IdProducto = Inventario.IdProducto WHERE Ventas.idUsuario =" + idUsuario + ";";
        ArrayList <Carrito> historialCompras= new ArrayList <Carrito>();
        Carrito carrito;
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                carrito = new Carrito();
                carrito.setIdVenta(rs.getInt(1));
                carrito.setUnidades(rs.getInt(2));
                carrito.setPrecioVenta(rs.getInt(3));
                carrito.setFechaVenta(rs.getString(4));
                carrito.setIdProducto(rs.getInt(5));
                carrito.setIdUsuario(rs.getInt(6));
                carrito.setTitulo(rs.getString(8));
                carrito.setTipo(rs.getString(9));
                carrito.setSexo(rs.getString(10));
                carrito.setTalla(rs.getInt(11));
                carrito.setColor(rs.getString(12));
                carrito.setEscuela(rs.getString(13));
                carrito.setCarrera(rs.getString(14));
                carrito.setStock(rs.getInt(15));
                carrito.setMateriales(rs.getString(16));
                carrito.setDescripcion(rs.getString(17));
                carrito.setPrecio(rs.getInt(18));
                carrito.setFoto(rs.getBytes(19));
                
                
                historialCompras.add(carrito);
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        //  ESTO DE AQUI ERA MERAMENTE PARA SABER QUE COÑO ESTABA FALLANDO.
        System.out.println("EN LA CONSULTA-----------------");
        for (int i = 0; i < historialCompras.size(); i++) {
            System.out.println("carrito: " + i + " = " +  historialCompras.get(i).getDescripcion());
        }
        System.out.println("TERMINO CONSULTAAAAAAAA----------------");
        
        return historialCompras;
    }
    
      //Este metodo me obtiene todas las ventas hasta el momento para el administrador.
      public ArrayList obtenerHistorialVentas() {
 
        query = "SELECT * FROM Ventas INNER JOIN Inventario ON Ventas.IdProducto = Inventario.IdProducto ORDER BY Ventas.FechaVenta DESC";
        //query = "SELECT * FROM Ventas INNER JOIN Inventario ON Ventas.IdProducto = Inventario.IdProducto WHERE Ventas.idUsuario =" + idUsuario + ";";
        ArrayList <Carrito> historialCompras= new ArrayList <Carrito>();
        Carrito carrito;
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd COPY PASTE. 
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

            while (rs.next()) {
                carrito = new Carrito();
                carrito.setIdVenta(rs.getInt(1));
                carrito.setUnidades(rs.getInt(2));
                carrito.setPrecioVenta(rs.getInt(3));
                carrito.setFechaVenta(rs.getString(4));
                carrito.setIdProducto(rs.getInt(5));
                carrito.setIdUsuario(rs.getInt(6));
                carrito.setTitulo(rs.getString(8));
                carrito.setTipo(rs.getString(9));
                carrito.setSexo(rs.getString(10));
                carrito.setTalla(rs.getInt(11));
                carrito.setColor(rs.getString(12));
                carrito.setEscuela(rs.getString(13));
                carrito.setCarrera(rs.getString(14));
                carrito.setStock(rs.getInt(15));
                carrito.setMateriales(rs.getString(16));
                carrito.setDescripcion(rs.getString(17));
                carrito.setPrecio(rs.getInt(18));
                carrito.setFoto(rs.getBytes(19));
                
                
                historialCompras.add(carrito);
            }

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
        //  ESTO DE AQUI ERA MERAMENTE PARA SABER QUE COÑO ESTABA FALLANDO.
        System.out.println("EN LA CONSULTA-----------------");
        for (int i = 0; i < historialCompras.size(); i++) {
            System.out.println("carrito: " + i + " = " +  historialCompras.get(i).getDescripcion());
        }
        System.out.println("TERMINO CONSULTAAAAAAAA----------------");
        
        return historialCompras;
    }
    
    
    public void actualizarContraseña(String email, String pasword){
        query = "UPDATE Usuarios SET pasword = '" + pasword +  "' WHERE email = '" + email + "';";
        try {

            //Creo un Statement y la almaceno en una variable de tipo Statement xd
            st = con.createStatement();
            //Ejecuto la query
            rs = st.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Errro padre: " + e);
        }
    }

}
