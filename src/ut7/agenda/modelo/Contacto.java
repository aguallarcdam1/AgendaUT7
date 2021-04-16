package ut7.agenda.modelo;

/**
 * @author Andrés & Javier
 */
public abstract class Contacto implements Comparable<Contacto> {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;

    public Contacto(String nombre, String apellidos, String telefono, String email) {
	this.nombre = nombre.toUpperCase();
	this.apellidos = apellidos.toUpperCase();
	this.telefono = telefono;
	this.email = email.toLowerCase();

    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellidos() {
	return apellidos;
    }

    public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
	result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Contacto other = (Contacto) obj;
	return other.getApellidos().equals(this.getApellidos()) && other.getNombre().equals(this.getNombre())
		&& other.getEmail().equals(this.getEmail());

    }

    /**
     * Se redefine el compareTo para poder comparar objetos de tipo contacto por sus
     * apellidos y si estos coinciden se considerará su nombre.
     */
    @Override
    public int compareTo(Contacto c) {
	if (this.getApellidos().compareToIgnoreCase(c.getApellidos()) == 0) {
	    return this.getNombre().compareToIgnoreCase(c.getNombre());
	} else {
	    return this.getApellidos().compareToIgnoreCase(c.getApellidos());
	}

    }

    /**
     * Método abstracto que devolverá una firma específica dependiendo de la
     * subclase que se use.
     * 
     * @return String
     */
    public abstract String getFirmaEmail();

    /**
     * Método que devuelve la primera letra del apellido
     * 
     * @return char
     */
    public char getPrimeraLetra() {
	return apellidos.charAt(0);
    }

    /**
     * Método toString de la clase padre que contiene las variables comunes de las
     * subclases
     */
    @Override
    public String toString() {

	return getApellidos() + " , " + getNombre() + " (" + this.getClass().getSimpleName() + ")\n" + "Tfno: "
		+ getTelefono() + " | email: " + getEmail();
    }
}
