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
public class Algoritmos {

    String password;

    public Algoritmos(String password) {
        this.password = password;
    }

    int length = 0;                     // Almacenamos numero de caracteres en el pass
    int numCount = 0;                   // Variable usada para almacenar numeros en el password
    int capCount = 0;                   // Variable usada para almacenar mayusculas en el password
    int capSignos = 0;                  // Variable usada para almacenar los signos
    int Arroba = 0;                     // solo la arroba
    
    boolean min1signo;
    boolean min1arroba; 
    boolean min1numero;
    boolean min1mayuscula;
    boolean min8caracteres;

    public void analizarContraseña() {
        

        
        for (int x = 0; x < password.length(); x++) {
            if ((password.charAt(x) >= 47 && password.charAt(x) <= 58) //numeros
                    || (password.charAt(x) >= 64 && password.charAt(x) <= 91) //mayusculas
                    || (password.charAt(x) >= 63 && password.charAt(x) <= 65) //Arroba
                    || (password.charAt(x) >= 32 && password.charAt(x) <= 44) //signos
                    || (password.charAt(x) >= 97 && password.charAt(x) <= 122)) {  //minusculas

            }
            if ((password.charAt(x) > 63 && password.charAt(x) < 65)) { // Cuenta laS arrobas
                Arroba++;
            }
            if ((password.charAt(x) > 32 && password.charAt(x) < 44)) { // Cuenta la cantidad signos
                capSignos++;
            }
            if ((password.charAt(x) > 47 && password.charAt(x) < 58)) { // Cuenta la cantidad de numero
                numCount++;
            }

            if ((password.charAt(x) > 64 && password.charAt(x) < 91)) { // Cuenta la cantidad de mayuscula
                capCount++;
            }

            length = (x + 1);// Cuenta la longitud del password

        } // Final del bucle
        
        // Case 1. no tiene caracteres especiales
        // Case 2. Colocar un @para mayor seguridad
        // Case 3. Medio   
        // Case 4. Demasiado facil como el Palomino.
        // Case 5. Inutilizable, como el Palomino.
        
        if(length >= 8){
            min8caracteres = true;
            if(capSignos >= 1){
                min1signo = true;
            }
            if(Arroba >= 1){
                min1arroba = true;
            }
            if(numCount >= 1){
                min1numero = true;
            }
            if(capCount >= 1){
                min1mayuscula = true;
            }
        }
    }

    public boolean isMin1signo() {
        return min1signo;
    }

    public boolean isMin1arroba() {
        return min1arroba;
    }

    public boolean isMin1numero() {
        return min1numero;
    }

    public boolean isMin1mayuscula() {
        return min1mayuscula;
    }

    public boolean isMin8caracteres() {
        return min8caracteres;
    }
    
    

}
