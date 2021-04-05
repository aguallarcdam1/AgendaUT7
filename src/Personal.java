import java.time.LocalDate;

public class Personal extends Contacto{
    private LocalDate fechaNacimiento;
    private Relacion relacion;
    public Personal(String nombre, String apellidos, String telefono, String email, LocalDate fechaNacimiento, Relacion relacion) {
	super(nombre, apellidos, telefono, email);
	this.setFechaNacimiento(fechaNacimiento);
	this.setRelacion(relacion);
    }
    public Relacion getRelacion() {
	return relacion;
    }
    public void setRelacion(Relacion relacion) {
	this.relacion = relacion;
    }
    public LocalDate getFechaNacimiento() {
	return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
    }

	

}
