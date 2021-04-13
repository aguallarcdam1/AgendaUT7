import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * Parte conjunta (Javier y Andrés).
 */
public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {
		this.agenda = new TreeMap<>();
	}

	/*
	 * Parte de Andrés
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
	 * Parte de Andrés
	 */
	public void totalContactos() {

	}

	/*
	 * Parte de Javier
	 */
	@Override
	public String toString() {

		return null;
	}

	/*
	 * Parte de Andrés
	 */
	public List<Contacto> buscarContactos(String texto) {

		return null;

	}

	/*
	 * Parte de Javier
	 */
	public List<Personal> personalesEnLetra(char letra) {

		return null;
	}

	/*
	 * Parte de Andrés
	 */
	public List<Personal> felicitar() {

		return null;
	}

	/*
	 * Parte de Javier
	 */
	public void personalesPorRelacion() {

	}

	/*
	 * Parte de Andrés
	 */
	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {

		return null;

	}

}
