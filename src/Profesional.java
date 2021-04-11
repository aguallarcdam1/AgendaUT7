
public class Profesional extends Contacto {
	private String empresa;

	public Profesional(String nombre, String apellidos, String telefono, String email, String empresa)  {
			super(nombre, apellidos, telefono, email);
	}

	@Override
	public String Firma() {
		
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
		
		return getApellidos() + " , " + getNombre() + "(PROFESIONAL)\n" + "Tfno: " 
		+ getTelefono() + " | email: " + getEmail() + "\n Empresa: " + getEmpresa();
	}


}
