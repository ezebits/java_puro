/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuenta;

/**
 *
 * @author Ezequiel
 */
public class SaldoInsuficienteException extends Exception
{
    public SaldoInsuficienteException(String mensaje)
    {
        super(mensaje);
    }
}
