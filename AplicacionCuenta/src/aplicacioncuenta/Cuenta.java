/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncuenta;
import java.util.Random;

/**
 *
 * @author Ezequiel
 */
public class Cuenta {
    
    private String _nombre;
    private float _saldo;
    private int _id;
    
    private static int _idThreshold = 1000; //Limite de id
    
    private void logEstadoDeCuenta(){
        
        System.out.format("cuenta %d de %s saldo $%.2f %n ", _id, _nombre, _saldo);
    }

    public String getNombre() {
        
        return _nombre;
    }

    public void setNombre(String nombre) {
        
        _nombre = nombre;
    }

    public float getSaldo() {
        
        return _saldo;
    }

    public void setSaldo(float saldo) {
        
        _saldo = saldo;
    }

    public int getId() {
        
        return _id;
    }

    public void setId(int id) {
        
        _id = id;
    }
    
    //Genera una id aleatoria entre 0 y _idThreshold
    public static int generarId(){
        
        Random randomEngine = new Random();
        return randomEngine.nextInt(_idThreshold);
    }
    
    /**
     * Constructor por defecto
     */
    public Cuenta(){
        
        _id = generarId();
        _saldo = 0f;
    }
    
    /**
     * Instancia una nueva cuenta tomando su monto inicial y el nombre del dueño.
     * 
     * @param pMontoInicial monto inicial de la cuenta
     * 
     * @param pNombreDueño nombre del titular de la cuenta
     */
    public Cuenta(float pMontoInicial, String pNombreDueño){
        // llama al constructor por defecto
        this();
        
        _nombre = pNombreDueño;
        _saldo = pMontoInicial;
    }
    
    /**
     * Constructor por copia, instancia una nueva cuenta a base de otra.
     * 
     * @param pCuenta una instancia de <class>Cuenta</class>
     */
    public Cuenta(Cuenta pCuenta){
        // llama al constructor sobrecargado
        this(pCuenta.getSaldo(), pCuenta.getNombre());
    }
    
    /**
     * Deposita dinero en la cuenta
     * 
     * @param pMonto monto a depositar
     */
    public void ingreso(float pMonto){
        
        float nuevoMonto = _saldo + pMonto;
        this.setSaldo(nuevoMonto);
        this.logEstadoDeCuenta();
    }
    
    /**
     * Reintegra un monto a la cuenta
     * 
     * @param pMonto 
     */
    public void reintegro(float pMonto){
        
        this.ingreso(pMonto);
    }
    
    /**
     * Genera un debito a la cuenta y devuelve su valor
     * 
     * @param pMonto monto a debitar
     * 
     * @return debito realizado
     * 
     * @throws SaldoInsuficienteException es lanzada si el monto es mayor al 
     * saldo de la cuenta 
     */
    public float transferencia(float pMonto) throws SaldoInsuficienteException{
        
        // se verifica si hay saldo suficiente
        // sino lanza una excepcion
        if(_saldo < pMonto)
            throw new SaldoInsuficienteException("saldo insuficiente!");
        
        // se debita el monto
        _saldo -= pMonto;
        
        // se imprime el estado de la cuenta
        this.logEstadoDeCuenta();
        
        // se devuelve el monto debitado
        return pMonto;
    }
}
