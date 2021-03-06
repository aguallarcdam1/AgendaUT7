package agenda.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
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

		int lineas_erroneas = 0;
		Scanner sc = null;
		try {
			sc = new Scanner((AgendaIO.class.getClassLoader().getResourceAsStream(nombre)));
			while (sc.hasNextLine()) {
				try {
					String linea = sc.nextLine();
					agenda.añadirContacto(parsearLinea(linea));
				} catch (DateTimeParseException e) {

					lineas_erroneas++;
				} catch (NumberFormatException e) {

					lineas_erroneas++;
				} catch (IllegalArgumentException e) {

					lineas_erroneas++;
				}
			}
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

	/**
	 * 
	 * El metodo guarda en el fichero de texto los contactos personales agrupados
	 * por relación
	 * 
	 * @param agenda
	 * @param nombre
	 * @throws IOException
	 */
	public static void exportarPersonales(AgendaContactos agenda, String nombre) throws IOException {
		File f = new File(nombre);
		PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(f)));

		Map<Relacion, List<String>> map = agenda.personalesPorRelacion();
		map.forEach((key, value) -> salida.println(key + "\n\t" + value));
		salida.close();

	}

}
