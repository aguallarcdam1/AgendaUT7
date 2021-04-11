import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    
	@Override
	public String toString() {
		
		return super.getApellidos() + ", " + super.getNombre() + " (" + this.getClass().getSimpleName()
				+ ")\n" + "Tfno: " + super.getTelefono() + " | " + "email: " + super.getEmail() 
				+ "\nFecha nacimiento: " + this.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd MMM. yyyy"))
				+ "\nRelación: " + this.getRelacion();
	}

}

