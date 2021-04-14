package ut7.agenda.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Andrés & Javier
 */
public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {
		this.agenda = new TreeMap<>();
	}

	/*
	 * Parte de Andrés. Se añade una entrada en el mapa 'agenda', comprobando si la
	 * clave ya está dentro del mapa.
	 */
	public void añadirContacto(Contacto contacto) {
		if (!agenda.containsKey(contacto.getPrimeraLetra())) {
			TreeSet<Contacto> value = new TreeSet<>();
			value.add(contacto);
			agenda.put(contacto.getPrimeraLetra(), value);
		} else {
			agenda.get(contacto.getPrimeraLetra()).add(contacto);
		}
	}

	/*
	 * Parte de Javier
	 */
	public void contactosEnLetra() {

	}

	/*
	 * Parte de Andrés. Devuelve el total de contactos que hay almacenados en la
	 * agenda.
	 * 
	 */
	public int totalContactos() {
		int totalContactos = 0;
		Set<Character> keys = agenda.keySet();
		for (Character character : keys) {
			totalContactos += totalContactos + agenda.get(character).size();
		}
		return totalContactos;
	}

	/*
	 * Parte de Javier
	 */
	@Override
	public String toString() {

		return null;
	}

	/*
	 * Parte de Andrés. Se pasa un texto como parámetro y se devuelve una lista con
	 * los contactos que contengan ese texto en su nombre o apellidos.
	 */
	public ArrayList<Contacto> buscarContactos(String texto) {
		ArrayList<Contacto> contactosBuscados = new ArrayList<>();

		Set<Map.Entry<Character, Set<Contacto>>> entradas = agenda.entrySet();
		for (Entry<Character, Set<Contacto>> entrada : entradas) {
			Set<Contacto> contactos = entrada.getValue();
			for (Contacto contacto : contactos) {
				if (contacto.getApellidos().contains(texto) || contacto.getNombre().contains(texto)) {
					contactosBuscados.add(contacto);
				}
			}
		}
		return contactosBuscados;

	}

	/*
	 * Parte de Javier
	 */
	public List<Personal> personalesEnLetra(char letra) {

		return null;
	}

	/*
	 * Parte de Andrés. Devuelve una lista con los contactos personales que cumplen
	 * años.
	 */
	public ArrayList<Personal> felicitar() {
		ArrayList<Personal> contactosFelicitados = new ArrayList<>();

		Set<Map.Entry<Character, Set<Contacto>>> entradas = agenda.entrySet();
		for (Entry<Character, Set<Contacto>> entrada : entradas) {
			Set<Contacto> contactos = entrada.getValue();
			for (Contacto contacto : contactos) {
				if (contacto instanceof Personal) {
					contactosFelicitados.add((Personal) contacto);
				}
			}
		}

		return contactosFelicitados;
	}

	/*
	 * Parte de Javier
	 */
	public void personalesPorRelacion() {

	}

	/*
	 * Parte de Andrés. Devuelve una lista de contactos personales ordenados por
	 * fecha de nacimiento.
	 */
	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {
		ArrayList<Personal> personalesOrdenadosFecha = new ArrayList<>();

		Set<Contacto> contactos = agenda.get(letra);
		for (Contacto contacto : contactos) {
			if (contacto instanceof Personal) {
				personalesOrdenadosFecha.add((Personal) contacto);
			}
		}
		Collections.sort(personalesOrdenadosFecha, new Comparator<Personal>() {

			@Override
			public int compare(Personal c1, Personal c2) {

				return c1.getFechaNacimiento().compareTo(c2.getFechaNacimiento());

			}
		});
		return personalesOrdenadosFecha;

	}

}
