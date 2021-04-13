/*
 *  autor: Parte de Javier
 */

public class Profesional extends Contacto {
	private String empresa;

	public Profesional(String nombre, String apellidos, String telefono, String email, String empresa) {
		super(nombre, apellidos, telefono, email);
		this.empresa = empresa;
	}

	@Override
	public String getFirmaEmail() {
		int opciones = (int) (Math.random() * 5 + 1);
		String opcion = "";
		switch (opciones) {

		case 1:
			opcion = "Atentamente";
		case 2:
			opcion = "Saludos";
		case 3:
			opcion = "Saludos cordiales";
		case 4:
			opcion = "Mis mejores deseos";

		}
		return opcion;

	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return super.getApellidos() + ", " + super.getNombre() + " (" + this.getClass().getSimpleName() + ")\n"
				+ "Tfno: " + super.getTelefono() + " | " + "email: " + super.getEmail() + "\nEmpresa: "
				+ this.getEmpresa();
	}

}
