package agenda.interfaz;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import agenda.io.AgendaIO;
import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import agenda.modelo.Personal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class GuiAgenda extends Application {
	private AgendaContactos agenda;
	private MenuItem itemImportar;
	private MenuItem itemExportarPersonales;
	private MenuItem itemSalir;

	private MenuItem itemBuscar;
	private MenuItem itemFelicitar;

	private MenuItem itemAbout;

	private TextArea areaTexto;

	private RadioButton rbtListarTodo;
	private RadioButton rbtListarSoloNumero;
	private Button btnListar;

	private Button btnPersonalesEnLetra;
	private Button btnPersonalesOrdenadosPorFecha;

	private TextField txtBuscar;

	private Button btnClear;
	private Button btnSalir;

	@Override
	public void start(Stage stage) {
		agenda = new AgendaContactos(); // el modelo

		BorderPane root = crearGui();

		Scene scene = new Scene(root, 1100, 700);
		stage.setScene(scene);
		stage.setTitle("Agenda de contactos");
		scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
		stage.show();

	}

	private BorderPane crearGui() {
		BorderPane panel = new BorderPane();
		panel.setTop(crearBarraMenu());
		panel.setCenter(crearPanelPrincipal());
		return panel;
	}

	private BorderPane crearPanelPrincipal() {
		BorderPane panel = new BorderPane();
		panel.setPadding(new Insets(10));
		panel.setTop(crearPanelLetras());

		areaTexto = new TextArea();
		areaTexto.getStyleClass().add("textarea");
		panel.setCenter(areaTexto);

		panel.setLeft(crearPanelBotones());
		return panel;
	}

	/**
	 * Crea el panel vertical con distintos botones que realizan diferentes
	 * acciones.
	 * 
	 * @return VBox
	 */
	private VBox crearPanelBotones() {
		// a completar
		VBox panel = new VBox();
		panel.setPadding(new Insets(10));
		panel.setSpacing(10);

		txtBuscar = new TextField();
		txtBuscar.setPromptText("Buscar");
		txtBuscar.setMinHeight(40);
		VBox.setVgrow(txtBuscar, Priority.ALWAYS);
		VBox.setMargin(txtBuscar, new Insets(0, 0, 40, 0));
		txtBuscar.setOnAction(event -> buscar());

		ToggleGroup grupo = new ToggleGroup();
		rbtListarTodo = new RadioButton();
		rbtListarTodo.setText("Listar toda la agenda");
		rbtListarTodo.setSelected(true);
		rbtListarTodo.setToggleGroup(grupo);

		rbtListarSoloNumero = new RadioButton();
		rbtListarSoloNumero.setText("Listar nº de contactos");
		rbtListarSoloNumero.setToggleGroup(grupo);

		btnListar = new Button("Listar");
		btnListar.setPrefWidth(250);
		btnListar.getStyleClass().add("botones");
		VBox.setMargin(btnListar, new Insets(0, 0, 40, 0));
		btnListar.setOnAction(event -> listar());

		btnPersonalesEnLetra = new Button("Contactos personales en letra");
		btnPersonalesEnLetra.setPrefWidth(250);
		btnPersonalesEnLetra.getStyleClass().add("botones");
		btnPersonalesEnLetra.setOnAction(event -> contactosPersonalesEnLetra());

		btnPersonalesOrdenadosPorFecha = new Button("Contactos personales\nordenados por fecha");
		btnPersonalesOrdenadosPorFecha.setPrefWidth(250);
		btnPersonalesOrdenadosPorFecha.getStyleClass().add("botones");
		btnPersonalesOrdenadosPorFecha.setOnAction(event -> personalesOrdenadosPorFecha());

		btnClear = new Button("Clear");
		btnClear.setPrefWidth(250);
		btnClear.getStyleClass().add("botones");
		VBox.setMargin(btnClear, new Insets(40, 0, 0, 0));
		btnClear.setOnAction(event -> clear());

		btnSalir = new Button("Salir");
		btnSalir.setPrefWidth(250);
		btnSalir.getStyleClass().add("botones");
		btnSalir.setOnAction(event -> salir());

		panel.getChildren().addAll(txtBuscar, rbtListarTodo, rbtListarSoloNumero, btnListar, btnPersonalesEnLetra,
				btnPersonalesOrdenadosPorFecha, btnClear, btnSalir);
		return panel;
	}

	/**
	 * Crea un contenedor GridPane que contendra los 27 botones de letras
	 * 
	 * @return GridPane
	 */
	private GridPane crearPanelLetras() {

		GridPane panel = new GridPane();
		panel.setPadding(new Insets(10));
		panel.setVgap(5);
		panel.setHgap(5);
		panel.setAlignment(Pos.CENTER);

		String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

		int pos = 0;

		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 14 && pos < letras.length(); col++) {

				String nombreBtn = String.valueOf(letras.charAt(pos));

				Button btn = new Button(nombreBtn);
				btn.getStyleClass().add("botonletra");
				btn.setMaxSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
				GridPane.setHgrow(btn, Priority.ALWAYS);
				GridPane.setVgrow(btn, Priority.ALWAYS);
				btn.setOnAction(event -> contactosEnLetra(btn.getText().charAt(0)));

				panel.add(btn, col, row);
				pos++;
			}
		}
		return panel;
	}

	/**
	 * Crea una barra de menú con diferentes opciones que realizarán distintas
	 * acciones.
	 * 
	 * @return MenuBar
	 */
	private MenuBar crearBarraMenu() {

		MenuBar barra = new MenuBar();

		Menu menu1 = new Menu("Archivo");
		itemImportar = new MenuItem("_Importar agenda");
		itemImportar.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
		itemImportar.setOnAction(event -> importarAgenda());

		itemExportarPersonales = new MenuItem("_Exportar Personales");
		itemExportarPersonales.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
		itemExportarPersonales.setOnAction(event -> exportarPersonales());
		itemExportarPersonales.setDisable(true);

		itemSalir = new MenuItem("_Salir");
		itemSalir.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		itemSalir.setOnAction(event -> salir());

		menu1.getItems().addAll(itemImportar, itemExportarPersonales, new SeparatorMenuItem(), itemSalir);

		Menu menu2 = new Menu("Operaciones");
		itemBuscar = new MenuItem("_Buscar");
		itemBuscar.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
		itemBuscar.setOnAction(event -> buscar());

		itemFelicitar = new MenuItem("_Felicitar");
		itemFelicitar.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
		itemFelicitar.setOnAction(event -> felicitar());

		menu2.getItems().addAll(itemBuscar, itemFelicitar);

		Menu menu3 = new Menu("Help");
		itemAbout = new MenuItem("_About");
		itemAbout.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
		itemAbout.setOnAction(event -> about());

		menu3.getItems().addAll(itemAbout);

		barra.getMenus().addAll(menu1, menu2, menu3);
		return barra;
	}

	/**
	 * Se abre un cuadro de diálogo que permite seleccionar el fichero agenda.csv.
	 * Se importan los datos de la agenda y se muestran en el area de texto.
	 */
	private void importarAgenda() {
		FileChooser selector = new FileChooser();
		selector.setTitle("Abrir fichero de datos");
		selector.getExtensionFilters().addAll(new ExtensionFilter("csv", "*.csv"));
		File f = selector.showOpenDialog(null);

		itemImportar.setDisable(true);
		itemExportarPersonales.setDisable(false);

		if (f != null) {
			int numeroErrores = AgendaIO.importar(agenda, f.getName());
			areaTexto.setText("Importada agenda\n\nNúmero de errores: " + numeroErrores);
		} else {
			areaTexto.setText("Agenda no importada");
		}

	}

	/**
	 * Se abre un cuadro de diálogo que permite guardar los datos personales en el
	 * fichero seleccionado.
	 */
	private void exportarPersonales() {
		FileChooser selector = new FileChooser();
		selector.setTitle("Guardar fichero de datos");
		selector.getExtensionFilters().addAll(new ExtensionFilter("txt", "*.txt"));
		File f = selector.showSaveDialog(null);

		try {
			AgendaIO.exportarPersonales(agenda, f.getName());
			areaTexto.setText("Exportados datos personales");

		} catch (IOException e) {
			areaTexto.setText("Error al exportar fichero" + e.getMessage());
		}

	}

	/**
	 *  
	 */
	private void listar() {
		clear();
		// a completar

	}

	private void personalesOrdenadosPorFecha() {
		clear();
		// a completar

	}

	/**
	 * Mostrará los contactos personales cuyo apellido comienzen por la letra que el
	 * usuario elija.
	 */
	private void contactosPersonalesEnLetra() {
		clear();

		if (agenda.totalContactos() == 0) {

			areaTexto.setText("Importa primero la agenda");

		} else {

			List<String> opciones = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
					"Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

			ChoiceDialog<String> dialogo = new ChoiceDialog<>("A", opciones);
			dialogo.setTitle("Selector de letra");
			dialogo.setHeaderText(null);
			dialogo.setContentText("Elija letra:");

			Optional<String> resul = dialogo.showAndWait();
			if (resul.isPresent()) {

				String letra = resul.get();
				List<Personal> personales = agenda.personalesEnLetra(letra.charAt(0));

				if (personales == null) {

					areaTexto.setText("No se han encontrado contactos personales en la letra: " + letra);

				} else {
					areaTexto.setText(
							"Contactos personales en la letra: " + letra + " (" + personales.size() + " contacto/s)\n");
					for (Personal personal : personales) {
						areaTexto.appendText("\n" + personal.toString());
					}

				}
			}
		}

	}

	private void contactosEnLetra(char letra) {
		clear();
		// a completar
	}

	private void felicitar() {
		clear();
		// a completar

	}

	/**
	 * Muestra en el área de texto los contactos de la agenda que contienen el texto
	 * tecleado.
	 */
	private void buscar() {
		clear();

		String texto = txtBuscar.getText();

		if (agenda.totalContactos() == 0) {

			areaTexto.setText("Importa primero la agenda");

		} else if (txtBuscar.getText().isEmpty()) {

			areaTexto.setText("No se ha introducido un texto para buscar");

		} else if (agenda.buscarContactos(texto).isEmpty()) {

			areaTexto.setText("No se han encontrado contactos con el texto: " + texto);

		} else {
			List<Contacto> contactos = agenda.buscarContactos(texto);

			areaTexto.setText("Contactos en la agenda que contienen: " + texto + "\n");

			for (Contacto contacto : contactos) {
				areaTexto.appendText("\n" + contacto.toString());
			}

		}

		cogerFoco();

	}

	/**
	 * Devuelve una ventada que da información sobre el proyecto.
	 */
	private void about() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

		alert.setTitle("About Agenda de Contactos");
		alert.setHeaderText(null);
		alert.setContentText("Mi agenda de\ncontactos");
		alert.showAndWait();

	}

	private void clear() {
		areaTexto.setText("");
	}

	private void salir() {
		Platform.exit();
	}

	private void cogerFoco() {
		txtBuscar.requestFocus();
		txtBuscar.selectAll();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
