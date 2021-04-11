import java.time.format.DateTimeFormatter;

public class Profesional extends Contacto {
	private String empresa;

	public Profesional(String nombre, String apellidos, String telefono, String email, String empresa)  {
			super(nombre, apellidos, telefono, email);
	}
	

	@Override
	public  String Firma() {
		int opciones = 4;
		switch(opciones) {
		
		case 1: System.out.println("Atentamente");
		case 2: System.out.println("Saludos");
		case 3: System.out.println("Saludos cordiales");
		case 4: System.out.println("Mis mejores deseos");
		
		}
		return null;
	
	}
		

	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {	
			return super.getApellidos() + ", " + super.getNombre() + " (" + this.getClass().getSimpleName()
					+ ")\n" + "Tfno: " + super.getTelefono() + " | " + "email: " + super.getEmail() 
					+ "\nEmpresa: " + this.getEmpresa();
		}

		
		


}
