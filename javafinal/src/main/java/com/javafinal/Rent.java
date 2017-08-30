package com.javafinal;

import java.util.Date;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Rent {
    
    public static int _rents = 0;
    
    Integer _idRent;
    String _customer;
    String _car;
    Date _dateFrom;
    Date _dateTo;

    public String getCustomer() {
        return _customer;
    }

    public void setCustomer(String pCustomer) {
        this._customer = pCustomer;
    }

    public String getCar() {
        return _car;
    }

    public Integer getIdRent() {
        return _idRent;
    }

    public void setCar(String pCar) {
        this._car = pCar;
    }

    public Date getDateFrom() {
        return _dateFrom;
    }

    public void setDateFrom(Date pDateFrom) {
        this._dateFrom = pDateFrom;
    }

    public Date getDateTo() {
        return _dateTo;
    }

    public void setDateTo(Date pDateTo) {
        this._dateTo = pDateTo;
    }
    
    private Rent(){}
    
    private Rent(String pCustomer, String pCar, Date pFrom, Date pTo)
    {
        _car = pCar;
        _customer = pCustomer;
        _dateFrom = pFrom;
        _dateTo = pTo;
    }

    public void save() {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] sets = {
            "domainCar = " + "'"+_car+"'",
            "dniCustomer = " + "'"+_customer+"'",
            "dateFrom = " + "'"+format.format(_dateFrom)+"'",
            "dateTo = " + "'"+format.format(_dateTo)+"'"
        };
        
        String[] cond = { "idRent = " + _idRent };
        
        DBClient.getInstance().update("Rent", sets, cond);
    }
	
    public static Rent createNew(String pCustomer, String pCar, Date pFrom, Date pTo) 
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	_rents += 1;
        String[] values = 
        {
            "'"+_rents+"'",
            "'"+pCustomer+"'",
            "'"+pCar+"'",
            "'"+format.format(pFrom)+"'",
            "'"+format.format(pTo)+"'"
        };
        
        DBClient.getInstance().insert("Rent", values);
        
        Rent newRent = new Rent(pCustomer, pCar, pFrom, pTo);
        
        newRent._idRent = _rents;
        
        return newRent;
    }
    
    public static Rent load(Integer idRenta)
    {
        String[] tablas = { "Rent" };
        String[] campos = { "*" };
        String[] cond = { "idRent = " + "'"+idRenta.toString()+"'"};
        
        ResultSet rs = DBClient.getInstance().select(campos, tablas, cond);
        Rent newRent = new Rent();
        
        try
        {
            rs.next();
            newRent._car = rs.getString("domainCar");
            newRent._customer = rs.getString("dniCustomer");
            newRent._dateFrom = rs.getDate("dateFrom");
            newRent._dateTo = rs.getDate("dateTo");
            newRent._idRent = rs.getInt("idRent");
            rs.close();
        }
        catch(SQLException e)
        {
            newRent = null;
        }
        
        return newRent;
    }
	
    public static List<Rent> search(String pCustomer) {
        String[] tablas = { "Rent" };
        String[] campos = { "*" };
        String[] cond = {"dniCustomer = " + "'"+pCustomer+"'"};
        
        ResultSet rs = DBClient.getInstance().select(campos, tablas, cond);
        ArrayList<Rent> results = new ArrayList<Rent>();
        
        try
        {
            while(rs.next())
            {
                Rent newRent = new Rent();
                newRent._car = rs.getString("domainCar");
                newRent._customer = rs.getString("dniCustomer");
                newRent._dateFrom = rs.getDate("dateFrom");
                newRent._dateTo = rs.getDate("dateTo");
                newRent._idRent = rs.getInt("idRent");
                results.add(newRent);
            }
            rs.close();
        }
        catch(SQLException e)
        {
            System.out.println("Error: " + e.getMessage());
            results = null;
        }
        
        return results;
    }

}
