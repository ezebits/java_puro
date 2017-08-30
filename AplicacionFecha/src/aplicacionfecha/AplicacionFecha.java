/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionfecha;

/**
 *
 * @author Ezequiel
 */
public class AplicacionFecha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Fecha miFecha = new Fecha();
        
        miFecha = new Fecha("31/08/2017");
        
        boolean esValida = miFecha.estaCorrecta("10/03/2010");

        System.out.println(esValida);
        
        // avanza dia
        System.out.println(miFecha);
        miFecha.avanzarDia();
        System.out.println(miFecha);
    }
    
}
