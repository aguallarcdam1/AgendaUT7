import java.time.LocalDate;

public class Personal extends Contacto{
    private LocalDate fechaNacimiento;
    private Relacion relacion;
    public Personal(String nombre, String apellidos, String telefono, String email, String fechaNacimiento, Relacion relacion) {
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
    public void setFechaNacimiento(String fechaNacimiento) {
	this.fechaNacimiento = LocalDate.parse(fechaNacimiento);
    }
    @Override
    public String Firma() {

	return "Un abrazo!!";
    }

    public boolean esCumpleaños() {
	
	return fechaNacimiento.equals(LocalDate.now());
    
    }

}
