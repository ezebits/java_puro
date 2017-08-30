/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncalculadora;

/**
 *
 * @author Ezequiel
 */
public class AplicacionCalculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int resultado = Calculadora.sumar(2, 3);
        System.out.println(resultado);
        
        resultado = Calculadora.restar(2, 3);
        System.out.println(resultado);
        
        resultado = Calculadora.multiplicar(2, -3);
        System.out.println(resultado);
        
        try
        {
            resultado = Calculadora.dividir(15, -3);
            System.out.println(resultado);
            
            resultado = Calculadora.dividir(0, 3);
            System.out.println(resultado);
            
            resultado = Calculadora.dividir(2, 0);
            System.out.println(resultado);
        }
        catch(Exception e)
        {
            System.out.println("error: " + e.getMessage());
        }
    }
    
}
