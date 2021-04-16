package ut7.agenda.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Andrés & Javier
 */
public class Personal extends Contacto {
    private LocalDate fechaNacimiento;
    private Relacion relacion;

    /**
     * 
     * Constructor de Personal
     */
    public Personal(String nombre, String apellidos, String telefono, String email, String fechaNacimiento,
	    Relacion relacion) {
	super(nombre, apellidos, telefono, email);
	this.setFechaNacimiento(fechaNacimiento);
	this.setRelacion(relacion);
    }

    /**
     * 
     * Accesor de relacion
     */
    public Relacion getRelacion() {
	return relacion;
    }

    /**
     * 
     * Mutador de relacion
     */
    public void setRelacion(Relacion relacion) {
	this.relacion = relacion;
    }

    /**
     * 
     * Accesor de fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
	return fechaNacimiento;
    }

    /**
     * 
     * Mutador de fechaNacimiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
	DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	this.fechaNacimiento = LocalDate.parse(fechaNacimiento, formateadorFecha);
    }

    /**
     * 
     * Redefinición del método abstracto getFirmaEmail de la clase padre Contacto
     */
    @Override
    public String getFirmaEmail() {

	return "Un abrazo!!";
    }

    /**
     * 
     * Método que devuelve true si la fecha del día de ejecucón del método coincide
     * con la fecha de la variable fechaNacimiento
     */
    public boolean esCumpleaños() {
	LocalDate today = LocalDate.now();
	if (fechaNacimiento.getDayOfMonth() == today.getDayOfMonth()
		&& fechaNacimiento.getMonthValue() == today.getMonthValue()) {
	    return true;
	} else {
	    return false;
	}

    }

    /**
     * 
     * Método toString de la clase PersonaL. Se le llama al toString de la clase padre Contacto
     */
    @Override
    public String toString() {

	return super.toString() + "\nFecha nacimiento: "
		+ this.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + "\nRelación: "
		+ this.getRelacion() + "\n";
    }

}
