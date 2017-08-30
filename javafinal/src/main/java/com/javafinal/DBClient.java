package com.javafinal;

import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

public final class DBClient implements IdBClient {
        private Connection _coneccion;
        private static DBClient _instance = null;
        private ResourceBundle _dbData;
        private ResourceBundle _queryData;
        
        
        private DBClient()
        {
                this.init();
        }
        
        private void startConnection()
        {
            if(_coneccion == null)
            {
                    String user = _dbData.getString("usr");
                    String password = _dbData.getString("pwd");
                    String conn = _dbData.getString("connection");

                    try
                    {
                        _coneccion = DriverManager.getConnection(conn, user, password);
                        _coneccion.setAutoCommit(false);
                    }
                    catch(SQLException e)
                    {
                        System.out.println("Error en la conexion!");
                    }

                    Runtime.getRuntime().addShutdownHook(new ApplicationEndsHandler(_instance));
            }
        }
        
	private void createDB() {
            
		String queryBatch1 = "CREATE TABLE Car("
                        + " domain varchar(255) primary key,"
                        + " doors int,"
                        + " color varchar(255),"
                        + " fuel varchar(255),"
                        + " kilometers varchar(255),"
                        + " baggageSpace double,"
                        + " passengersSpace int);";
                
                String queryBatch2 = "CREATE TABLE Customer(dniCustomer int primary key,"
                        + " name varchar(255),"
                        + " birthDay date,"
                        + " phone varchar(255));";
                
                String queryBatch3 = "CREATE TABLE Rent(idRent int primary key AUTO_INCREMENT,"
                        + " dniCustomer varchar(255),"
                        + " domainCar varchar(255),"
                        + " dateFrom date,"
                        + " dateTo date);";
                try
                {
                    Statement st = _coneccion.createStatement();
                    st.addBatch(queryBatch1);
                    st.addBatch(queryBatch2);
                    st.addBatch(queryBatch3);
                    st.executeBatch();
                    _coneccion.commit();
                    st.close();
                    st = null;
                }
                catch(SQLException e)
                {
                    System.out.println("error: " + e.getMessage());
                }
	}
        
        private void init()
        {
            _dbData = ResourceBundle.getBundle("dataBaseBundle");
            _queryData = ResourceBundle.getBundle("queryBundle");
            
            String driver = _dbData.getString("driver");
            
            try
            {
                Class.forName(driver);
            }
            
            catch(ClassNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage() + ", driver no encontrado " );
            }
            
            this.startConnection();
            
            this.createDB();
        }
        
	public Connection getConnection() {
                return  _coneccion;
	}
        
        public void flush() throws SQLException
        {
            if(_coneccion != null)
            {
                try
                {
                    _coneccion.close();
                    _coneccion = null;
                }
                catch(SQLException e)
                {
                    throw e;
                }
            }
            _instance = null;
        }
        
        public static DBClient getInstance()
        {
            if(_instance == null)
                _instance = new DBClient();
            
            return _instance;
        }
        
        private String getFromStringArray(String[] arr)
        {
            StringBuilder result = new StringBuilder();
            for(String set : arr)
            {
                result.append(set);
                result.append(',');
            }
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
        
        private void runStatement(String query)
        {
            try
            {
                PreparedStatement pr = _coneccion.prepareStatement(query);
                int changes = pr.executeUpdate();

                if(changes == 1)
                {
                    _coneccion.commit();
                }
                
                _coneccion.rollback();
                pr.close();
            }
            catch(SQLException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        public void update(String pTabla, String[] pSets, String[] pCondiciones)
        {
            String query = _queryData.getString("update");
            query = query.replace("[tabla]", pTabla);
            query = query.replace("[sets]", getFromStringArray(pSets));
            query = query.replace("[condiciones]", getFromStringArray(pCondiciones));
            runStatement(query);
        }
        
        public void delete(String pTabla, String[] pCondiciones)
        {
            String query = _queryData.getString("delete");
            query = query.replace("[tabla]", pTabla);
            query = query.replace("[condiciones]", getFromStringArray(pCondiciones));
            runStatement(query);
        }
        
        public void insert(String pTabla, String[] pValores)
        {
            String query = _queryData.getString("insert");
            query = query.replace("[tabla]", pTabla);
            query = query.replace("[valores]", getFromStringArray(pValores));
            runStatement(query);
        }
        
        public ResultSet select(String[] pCampos, String[] pTablas, String[] condiciones)
        {
            
            String query = _queryData.getString("select");
            query = query.replace("[tablas]", getFromStringArray(pTablas));
            query = query.replace("[campos]", getFromStringArray(pCampos));
            query = query.replace("[condiciones]", getFromStringArray(condiciones));
            
            
            ResultSet rs = null;
            try
            {
                PreparedStatement pr =  _coneccion.prepareStatement(query);
                rs = pr.executeQuery();
            }
            catch(SQLException e)
            {
                System.err.println("error: " + e.getMessage());
            }
            
            return rs;
        }
        
        class ApplicationEndsHandler extends Thread
        {
            DBClient _owner;
            
            @Override
            public void run()
            {
                try
                {
                    _owner.flush();
                    _owner = null;
                }
                catch(SQLException e)
                {
                    System.out.println("La conexion ya está cerrada");
                }
            }
            
            ApplicationEndsHandler (DBClient pClient)
            {
                _owner = pClient;
            }
            
        }
        
    }
