package agenda.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import agenda.modelo.Personal;
import agenda.modelo.Profesional;
import agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda
 * 
 * @author Andrés & Javier
 */
public class AgendaIO {
    /**
     * Se extrae los datos del fichero que se proporciona y se añaden los contactos
     * a la agenda.
     * 
     * @param agenda
     * @param nombre
     * @return número de errores cuando se lee el fichero
     */
    public static int importar(AgendaContactos agenda, String nombre) {
	File fichero = new File(nombre);
	int lineas_erroneas = 0;
	Scanner sc = null;
	try {
	    sc = new Scanner(fichero);
	    while (sc.hasNextLine()) {
		try {
		    String linea = sc.nextLine();
		    parsearLinea(linea);
		} catch (DateTimeParseException e) {
		    System.out.println("Error al parsear una fecha: " + e.getMessage());
		    lineas_erroneas++;
		} catch (NumberFormatException e) {
		    System.out.println("Error al parsear un número: " + e.getMessage());
		    lineas_erroneas++;
		} catch (IllegalArgumentException e) {
		    System.out.println("Error al cargar la línea: " + e.getMessage());
		    lineas_erroneas++;
		}
	    }
	} catch (FileNotFoundException e) {
	    System.out.println("Error al cargar el fichero: " + e.getMessage());
	} finally {
	    if (sc != null) {
		sc.close();
	    }
	}
	return lineas_erroneas;
    }

    /**
     * Se le pasa una línea la cual se le extrae los datos para crear un Contacto
     * 
     * @param linea
     * @return un Contacto
     * @throws DateTimeParseException
     * @throws IllegalArgumentException
     * @throws NumberFormatException
     */
    private static Contacto parsearLinea(String linea)
	    throws DateTimeParseException, IllegalArgumentException, NumberFormatException {
	String[] datos = linea.split("\\,+");
	if (Integer.parseInt(datos[0].trim()) == 1) {
	    return new Profesional(datos[1].trim(), datos[2].trim(), datos[3].trim(), datos[4].trim(), datos[5].trim());
	} else {
	    return new Personal(datos[1].trim(), datos[2].trim(), datos[3].trim(), datos[4].trim(), datos[5].trim(),
		    Relacion.valueOf(datos[6].toUpperCase().trim()));
	}
    }

}
