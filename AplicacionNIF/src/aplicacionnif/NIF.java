/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionnif;


public class NIF {
    private String _id;

    public String getId() {
        return _id;
    }

    public void setId(String pId) {
        _id = pId;
    }

    public String getDni() {
        return _id.substring(0,8);
    }

    public String getNombre() {
        return _id.substring(8);
    }
    
    public NIF(){}

    public NIF(String pId){
        _id = pId;
    }

    public NIF(Double pDni, String pNombre){
        _id = pDni+pNombre;
    }
}
