/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionlibro;

/**
 *
 * @author Ezequiel
 */
public class AplicacionLibro {

    public static void main(String[] args) {
        Libro libro1 = new Libro("Cancion de Hielo y Fuego");
        System.out.println(libro1);
        libro1.prestamo("juan");
        System.out.println(libro1);
        libro1.devolucion();
        System.out.println(libro1);
    }
    
}
