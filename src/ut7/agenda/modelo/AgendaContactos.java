package ut7.agenda.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
     * Parte de Javier.Devuelve todos los contactos de la letra dada como parametro
     */
    public Set<Contacto> contactosEnLetra(char letra) {
	letra = Character.toUpperCase(letra);
	return agenda.get(letra);

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
	    totalContactos += agenda.get(character).size();
	}
	return totalContactos;
    }

    /*
     * Parte de Javier Representacion textual
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
		if (contacto.getApellidos().contains(texto.toUpperCase())
			|| contacto.getNombre().contains(texto.toUpperCase())) {
		    contactosBuscados.add(contacto);
		}
	    }
	}
	return contactosBuscados;

    }

    /*
     * Parte de Javier. Devuelve todos los contocatos que sean personales y que
     * tengan la letra pasada como parametro
     */
    public List<Personal> personalesEnLetra(char letra) {
	letra = Character.toUpperCase(letra);
	ArrayList<Personal> personalLetra = new ArrayList<>();

	Set<Map.Entry<Character, Set<Contacto>>> entradas = agenda.entrySet();
	for (Entry<Character, Set<Contacto>> entrada : entradas) {
	    Set<Contacto> contactos = entrada.getValue();
	    for (Contacto contacto : contactos) {
		if (contacto instanceof Personal && contacto.getPrimeraLetra() == letra) {
		    personalLetra.add((Personal) contacto);
		} else if (agenda.containsKey(letra) == false) {
		    return null;

		}
	    }
	}
	return personalLetra;
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
		if (contacto instanceof Personal && ((Personal) contacto).esCumpleaños()) {
		    contactosFelicitados.add((Personal) contacto);
		}
	    }
	}

	return contactosFelicitados;
    }

    /*
     * Parte de Javier Devuelve un nuevo map en el que aparecen solo contactos
     * personales pero organizados de forma que la clave en el nuevo map es la
     * relación (un enumerado) y el valor asociado una colección List de cadenas con
     * los apellidos y nombre de todos los contactos personales que hay en la agenda
     */
    public Map<Relacion, List<String>> personalesPorRelacion() {
	Map<Relacion, List<String>> perso = new TreeMap<>();
	Iterator<Map.Entry<Character, Set<Contacto>>> it = agenda.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry<Character, Set<Contacto>> mp = it.next();
	    for (Contacto con : mp.getValue()) {
		if (con instanceof Personal) {
		    String str = con.getApellidos() + " " + con.getNombre();
		    Relacion rel = ((Personal) con).getRelacion();

		    if (perso.containsKey(rel)) {
			perso.get(rel).add(str);
		    } else {

			List<String> personales = new ArrayList<>();
			personales.add(str);
			perso.put(rel, personales);

		    }
		}

	    }

	}

	return perso;

    }

    /*
     * Parte de Andrés. Devuelve una lista de contactos personales ordenados por
     * fecha de nacimiento.
     */
    public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {
	ArrayList<Personal> personalesOrdenadosFecha = new ArrayList<>();
	letra = Character.toUpperCase(letra);
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
