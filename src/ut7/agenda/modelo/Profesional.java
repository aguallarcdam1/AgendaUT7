package ut7.agenda.modelo;

/**
 * @author Andrés & Javier
 */

public class Profesional extends Contacto {
	private String empresa;

	public Profesional(String nombre, String apellidos, String telefono, String email, String empresa) {
		super(nombre, apellidos, telefono, email);
		this.empresa = empresa.toUpperCase();
	}

	@Override
	public String getFirmaEmail() {
		int opciones = (int) (Math.random() * 4 + 1);
		String opcion = "";
		switch (opciones) {

		case 1:
			opcion = "Atentamente";
			break;
		case 2:
			opcion = "Saludos";
			break;
		case 3:
			opcion = "Saludos cordiales";
			break;
		case 4:
			opcion = "Mis mejores deseos";
			break;

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
		return super.toString() + "\nEmpresa: " + this.getEmpresa() + "\n";
	}

}
