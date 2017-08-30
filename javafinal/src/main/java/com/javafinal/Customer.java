package com.javafinal;

import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Customer {
	private String _dni;
	private String _name;
	private Date _birthDay;
	private String _phone;

    public String getDni() {
        return _dni;
    }

    public String getName() {
        return _name;
    }

    public Date getBirthday() {
        return _birthDay;
    }

    public String getPhone() {
        return _phone;
    }
    
    public void setName(String pName)
    {
        _name = pName;
    }
    
    public void setBirthday(Date pBirthday)
    {
        _birthDay = pBirthday;
    }
    
    public void setPhone(String pPhone)
    {
        _phone = pPhone;
    }
	
    private Customer(){}
	
    private Customer(String pDni, String pName, Date pBirthday, String pPhone) {
		_dni = pDni;
		_name = pName;
		_birthDay = pBirthday;
		_phone = pPhone;
    }
	
    public void save() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String[] sets = {
            "name = '"+_name+"'",
            "birthDay = '"+format.format(_birthDay)+"'",
            "phone = '"+_phone+"'"
        };
        
        String[] cond = { "Customer.dniCustomer = " + "'"+_dni+"'" };
        
        DBClient.getInstance().update("Customer", sets, cond);
    }
	
    public static Customer load(String pDni) {
        String[] tablas = { "Customer" };
        String[] campos = { "*" };
        String[] cond = {"Customer.dniCustomer = " + "'"+pDni+"'"};
        
        ResultSet rs = DBClient.getInstance().select(campos, tablas, cond);
        
        Customer theCustomer = new Customer();
        
        try
        {
            rs.next();
            theCustomer._birthDay = rs.getDate("birthDay");
            theCustomer._dni = rs.getString("dniCustomer");
            theCustomer._name = rs.getString("name");
            theCustomer._phone = rs.getString("phone");
            rs.close();
        }
        
        catch(SQLException e)
        {
            theCustomer = null;
            System.out.println("Error: " + e.getMessage());
        }
        
        return theCustomer;
    }
	
    public static Customer createNew(String pDni, String pName, Date pBirthday, String pPhone) {
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        String[] sets = {
            "'"+pDni+"'",
            "'"+pName+"'",
            "'"+format.format(pBirthday)+"'",
            "'"+pPhone+"'"
        };
        
        DBClient.getInstance().insert("Customer", sets);
        
        Customer newCustomer = new Customer(pDni, pName, pBirthday, pPhone);
        return newCustomer;
    }
	
    public static List<Customer> search(String pAttribute, String pValue) {
	
        String[] tablas = { "Customer" };
        String[] campos = { "*" };
        String[] cond = {pAttribute + " = " + "'"+pValue+"'"};
        
        ResultSet rs = DBClient.getInstance().select(campos, tablas, cond);
        
        ArrayList<Customer> results = new ArrayList<Customer>();
        
        try
        {
            while(rs.next())
            {
                Customer theCustomer = new Customer();
                theCustomer._birthDay = rs.getDate("birthDay");
                theCustomer._dni = rs.getString("dniCustomer");
                theCustomer._name = rs.getString("name");
                theCustomer._phone = rs.getString("phone");
                
                results.add(theCustomer);
            }
            
            rs.close();
        }
        
        catch(SQLException e)
        {
            results = null;
            System.out.println("Error " + e.getMessage());
        }
        
        return results;
    }
}
