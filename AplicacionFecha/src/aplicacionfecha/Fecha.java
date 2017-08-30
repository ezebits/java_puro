/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionfecha;

import java.util.Date;
import java.util.Calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Fecha {
    
    private Date _fecha;
    private DateFormat _format;
    
    public Date getFecha() {
        return _fecha;
    }

    public void setFecha(Date fecha) {
        _fecha = fecha;
    }
    
    public Fecha()
    {
        _fecha = new Date();
        _format = new SimpleDateFormat("dd/MM/yy");
    }

    public Fecha(String pFecha)
    {
        this();
        
        try 
        {
            _fecha = _format.parse(pFecha);
        } 
        
        catch (Exception e) 
        {
            System.out.format("%s%n", e.getMessage());
        }
    }

    public boolean estaCorrecta(String pFecha) {
        
        try 
        {
            _format.parse(pFecha);
        } 
        
        catch (Exception e) 
        {
            System.out.format("%s%n", e.getMessage());
            return false;
        }
        
        return true;
    }

    public void avanzarDia() {
        
        Calendar calendario = Calendar.getInstance();
        
        calendario.setTime(_fecha);
        
        calendario.add(Calendar.DAY_OF_MONTH, 1);
        
        _fecha = calendario.getTime();
    }
    
    @Override
    public String toString() {
        // instancia de calendario
    	Calendar calendario = Calendar.getInstance();
        
        calendario.setTime(_fecha);
        
        // obtiene los valores
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int anio = calendario.get(Calendar.YEAR);
        
        return String.format("Fecha: %d/%d/%d", dia, mes, anio);
    }
}
