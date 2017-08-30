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
public class Calculadora {
    
    public static int sumar(int pNum1, int pNum2)
    {
        return pNum1 + pNum2;
    }
    
    public static float sumar(float pNum1, float pNum2)
    {
        return pNum1 + pNum2;
    }
    
    public static int restar(int pNum1, int pNum2)
    {
        return sumar(pNum1, -pNum2);
    }
    
    public static float restar(float pNum1, float pNum2)
    {
        return sumar(pNum1, -pNum2);
    }
    
    // multiplicacion de enteros
    public static int multiplicar(int pNum1, int pNum2)
    {
        int signo = 1;
        
        if(pNum2 < 0)
            signo = -1;
        
        if(pNum2 == signo)
                return pNum1 * signo; //TODO: Solucionar *
            else
                return sumar(pNum1 * signo , multiplicar(pNum1, pNum2 - signo));
        
    }
    
    // division de enteros sin parte decimal
    public static int dividir(int pNum1, int pNum2) throws Exception
    {
        int signo = 1;
        
        if(pNum1 < 0 || pNum2 < 0)
            signo = -1;
        
        if(pNum1 < 0 && pNum2 < 0)
            signo = 1;
        
        pNum1 = Math.abs(pNum1);
        pNum2 = Math.abs(pNum2);
        
        if(pNum1 == 0 && pNum2 != 0)
            return 0;
        
        if(pNum2 == 0)
            throw new Exception("division por cero");
        
        if(pNum1 == pNum2)
            return 1;
        
        if(pNum1 < pNum2)
            return 0;
        
        return multiplicar(1 + dividir(pNum1 - pNum2, pNum2), signo);
    }
}
