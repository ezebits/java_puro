/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javafinal;
import java.sql.ResultSet;

/**
 *
 * @author Ezequiel
 */
public interface IdBClient {
         void update(String pTabla, String[] pSets, String[] pCondiciones);
        
         void delete(String pTabla, String[] pCondiciones);
        
         void insert(String pTabla, String[] valores);
        
         ResultSet select(String[] pCampos, String[] pTablas, String[] condiciones);
}
