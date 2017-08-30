
package aplicacionlibro;

/**
 *
 * @author Ezequiel
 */
public class Libro {
    
    private String _nombre;
    private EstadosDeLibro _ubicacion;
    private String _ultimoResponsable;

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public EstadosDeLibro getUbicacion() {
        return _ubicacion;
    }

    public void setUbicacion(EstadosDeLibro _ubicacion) {
        this._ubicacion = _ubicacion;
    }

    public String getUltimoResponsable() {
        return _ultimoResponsable;
    }

    public void setUltimoResponsable(String _ultimoResponsable) {
        this._ultimoResponsable = _ultimoResponsable;
    }
    
    private boolean estaDisponible()
    {
        return _ubicacion == EstadosDeLibro.EnBiblioteca;
    }
    
    public Libro(){
        
        _nombre = "Un Libro";
        _ubicacion = EstadosDeLibro.EnBiblioteca;
        _ultimoResponsable = "(Nadie)";
    }
    
    public Libro(String pNombre) {
        this();
        _nombre = pNombre;
    }
    
    /**
     * Realiza el prestamo de un libro
     * @param pNombreAlumno alumno que lo retira
     * @return <strong>true</strong> si se realizo el prestamo correctamente.
     */
    public boolean prestamo(String pNombreAlumno)
    {
        boolean estaDisponible = this.estaDisponible();
        
        if(estaDisponible)
        {
            _ultimoResponsable = pNombreAlumno;
            _ubicacion = EstadosDeLibro.EnPoderDeAlumno;
        }
        
        return estaDisponible;
    }
    
    public void devolucion()
    {
        _ubicacion = EstadosDeLibro.EnBiblioteca;
    }

    @Override
    public String toString() {
        
        // se prepara el mensaje
        StringBuilder mensaje = new StringBuilder();
        
        // se verifica si esta disponible
        boolean disponible = this.estaDisponible();
        
        // se obtiene el estado
        String estado = disponible? "En biblioteca, el ultimo responsable fue " 
                                  : "Libro en posecion de ";
        
        // se agrega el estado al mensaje
        mensaje.append(estado);
        
        // se devuelve el mensaje junto al ultimo responsable
        return mensaje.append(_ultimoResponsable).toString();
    }
    
}
