/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuenta;


public class AplicacionCuenta {


    public static void main(String[] args) {
       
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta(100f, "Juan");
        Cuenta cuenta3 = new Cuenta(cuenta2);
        
        cuenta3.ingreso(1000f);
        try
        {
            cuenta3.transferencia(100f);
            cuenta3.transferencia(100f);
        }
        catch(SaldoInsuficienteException e)
        {
            System.out.format("error: %s %n", e.getMessage());
        }
        
        cuenta3.reintegro(102.25f);
        
    }
    
}
