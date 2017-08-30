package com.javafinal;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Car {
    private String _domain;
    private Integer _doors;
    private String _color;
    private String _fuel;
    private String _kilometers;
    private Double _baggageSpace;
    private Integer _passengersSpace;

    public String getDomain() {
        return _domain;
    }

    public void setDomain(String pDomain) {
        this._domain = pDomain;
    }

    public Integer getDoors() {
        return _doors;
    }

    public void setDoors(Integer pDoors) {
        _doors = pDoors;
    }

    public String getColor() {
        return _color;
    }

    public void setColor(String pColor) {
        _color = pColor;
    }

    public String getFuel() {
        return _fuel;
    }

    public void setFuel(String pFuel) {
        this._fuel = pFuel;
    }

    public String getKilometers() {
        return _kilometers;
    }

    public void setKilometers(String pKilometers) {
        this._kilometers = pKilometers;
    }

    public Double getBaggageSpace() {
        return _baggageSpace;
    }

    public void setBaggageSpace(Double pBaggageSpace) {
        this._baggageSpace = pBaggageSpace;
    }

    public Integer getPassengersSpace() {
        return _passengersSpace;
    }

    public void setPassengersSpace(Integer pPassengersSpace) {
        this._passengersSpace = pPassengersSpace;
    }
        
    private Car() {}
	
    private Car(String pDomain, Integer pDoors, String pColor, String pFuel, String pKilometers, Double pBaggageSpace, Integer pPassengersSpace) {
		
                _domain = pDomain;
		_doors = pDoors;
		_color = pColor;
		_fuel = pFuel;
		_kilometers = pKilometers;
		_baggageSpace = pBaggageSpace;
		_passengersSpace = pPassengersSpace;
    }
    
    public static Car createNew(String pDomain, Integer pDoors, String pColor, String pFuel, String pKilometers, Double pBaggageSpace, Integer pPassengersSpace)
    {
        String[] sets = 
        {
            "'"+pDomain+"'",
            "'"+pDoors.toString()+"'",
            "'"+pColor+"'",
            "'"+pFuel+"'",
            "'"+pKilometers+"'",
            "'"+pBaggageSpace.toString()+"'",
            "'"+pPassengersSpace.toString()+"'"
        };
                
        DBClient.getInstance().insert("Car", sets);
                

        
        Car newCar = new Car(pDomain, pDoors, pColor, pFuel, pKilometers, pBaggageSpace, pPassengersSpace);
        
        return newCar;
    }
        
    public void save() {
        
        String[] sets = 
        {
            "domain = " + "'"+_domain+"'",
            "doors = " + "'"+_doors+"'",
            "color = " + "'"+_color+"'",
            "fuel = " + "'"+_fuel+"'",
            "kilometers = " + "'"+_kilometers+"'",
            "baggageSpace = " + "'"+_baggageSpace+"'",
            "passengersSpace = " + "'"+_passengersSpace+"'"
        };
        
        String[] cond = { "domain = " + "'"+_domain+"'" };
        
	DBClient.getInstance().update("Car", sets, cond);
    }
	
    public static Car load(String domain) {
        
        String[] campos = { "*" };
        String[] tablas = { "Car" };
        String[] cond = { "domain = " + "'"+domain+"'" };
        
	ResultSet rs = DBClient.getInstance().select(campos, tablas, cond);
        
        Car theCar = new Car();
        
        try
        {
            rs.next();
            theCar._domain = rs.getString("domain");
            theCar._baggageSpace = rs.getDouble("baggageSpace");
            theCar._color = rs.getString("color");
            theCar._fuel = rs.getString("fuel");
            theCar._doors = rs.getInt("doors");
            theCar._passengersSpace = rs.getInt("passengersSpace");
            theCar._kilometers = rs.getString("kilometers");
            rs.close();
        }
        catch(SQLException e)
        {
            theCar = null;
            System.out.println("error: " + e.getMessage());
        }
        
        return theCar;
    }
	
    public static List<Car> search(String attribute, String value) {
	String[] campos = { "*" };
        String[] tablas = { "Car" };
        String[] cond = { attribute + " = " + "'"+value+"'" };
        
	ResultSet rs = DBClient.getInstance().select(campos, tablas, cond);
        
        ArrayList<Car> cars = new ArrayList<Car>();
        
        try
        {
            while( rs.next() )
            {
                Car theCar = new Car();
                theCar._domain = rs.getString("domain");
                theCar._baggageSpace = rs.getDouble("baggageSpace");
                theCar._color = rs.getString("color");
                theCar._fuel = rs.getString("fuel");
                theCar._doors = rs.getInt("doors");
                theCar._passengersSpace = rs.getInt("passengersSpace");
                theCar._kilometers = rs.getString("kilometers");
                
                cars.add(theCar);
            }
            
            rs.close();
        }
        catch(SQLException e)
        {
            cars = null;
            System.out.println("error: " + e.getMessage());
        }
        
        return cars;
    }
}
