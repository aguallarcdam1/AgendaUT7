package agenda.modelo;

/**
 * @author Andrés & Javier
 */

public class Profesional extends Contacto {
    private String empresa;

    public Profesional(String nombre, String apellidos, String telefono, String email, String empresa) {
	super(nombre, apellidos, telefono, email);
	this.empresa = this.capitalizarEmpresa(empresa);
    }

    @Override
    /*Devuelve una de las firmas aleatoriamente   */
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
    public String toString() /* Representacion textual */
    {
	return super.toString() + "\nEmpresa: " + this.getEmpresa() + "\n";
    }

    private String capitalizarEmpresa(String empresa)/* Capitaliza las primeras letras del nombre de la empresa*/
    {
	String empresaCapitalizada = "";
	String[] datos = empresa.trim().split("\\s+");
	for (String string : datos) {
	    empresaCapitalizada = empresaCapitalizada + Character.toUpperCase(string.charAt(0))
		    + string.substring(1, string.length()) + " ";

	}

	return empresaCapitalizada.trim();

    }
}
