package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import entidades.ControladoraLogica;
import entidades.Localidad;
import grafo.Grafo;
import logica.AlgoritmoAGM;
import persistencia.ControladoraPersistencia;
import servicio.FiberConnection;
import servicio.LogicaMapa;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DropMode;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class InterfazDatos extends JFrame {
	private FiberConnection fiberConnection;
	private LogicaMapa logicaMapa;
	private JMapViewer mapaLocalidadesJMapViewer;
	private JTable table;
	private ControladoraLogica controlLogica = new ControladoraLogica();

	public InterfazDatos(JMapViewer mapa, FiberConnection fiberConnection, LogicaMapa logicaMapa) {

		this.logicaMapa = logicaMapa;
		this.fiberConnection = fiberConnection;
		this.mapaLocalidadesJMapViewer = mapa;
		initialize();
	}

	private void initialize() {

		JButton btnEliminar = new JButton("Eliminar");
		JPanel panel_1 = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		JPanel panel_2 = new JPanel();
		JButton btnAgregar = new JButton("Agregar");
		JPanel panel = new JPanel();
		JPanel panel_3 = new JPanel();
		JTextArea textAreaDistancia = new JTextArea();
		textAreaDistancia.setEditable(false);
		textAreaDistancia.setAutoscrolls(false);
		JPanel panel_4 = new JPanel();

		this.setBounds(100, 100, 1366, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(26, 21, 1110, 248);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		scrollPane.setBounds(10, 11, 1072, 226);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane);

		table = new JTable();
		// Evita que las celdas sean editables
		table.setDefaultEditor(Object.class, null);

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Latitud", "Longitud", "Provincia", "Localidad" }));
		scrollPane.setViewportView(table);

		List<String> datos = controlLogica.getLocalidadesOrdenadas();
		String[][] datosLocalidades = new String[datos.size()][4];
		for (int i = 0; i < datos.size(); i++) {
			String[] partes = datos.get(i).split(",");
			datosLocalidades[i][0] = partes[0];
			datosLocalidades[i][1] = partes[1];
			datosLocalidades[i][2] = partes[2];
			datosLocalidades[i][3] = partes[3];

			cargarTabla(datosLocalidades);
		}

		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel_2.setBounds(1161, 21, 122, 248);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		// Boton Agregar
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazAgregarLocalidad interfazAgregarLocalidad = new InterfazAgregarLocalidad(
						mapaLocalidadesJMapViewer, fiberConnection, logicaMapa, table, InterfazDatos.this);
				interfazAgregarLocalidad.setVisible(true);
			}
		});

		btnAgregar.setBounds(10, 11, 102, 34);
		panel_2.add(btnAgregar);

		// Boton Eliminar
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();

				if (filaSeleccionada != -1) {
					// 1. Borra físicamente del .txt y de la lista lógica
					controlLogica.deleteSeleccionado(filaSeleccionada);

					// 2. Limpia el Grafo en memoria para que coincida con el disco
					// Es vital que fiberConnection también pierda ese nodo
					fiberConnection.eliminarLocalidadGrafo(filaSeleccionada);

					// 3. Refresca la tabla (Vista)
					refrescarTabla();

					// 4. Redibuja el mapa (Lee el .txt actualizado)
					logicaMapa.dibujar(mapaLocalidadesJMapViewer, fiberConnection.getGrafo());

					JOptionPane.showMessageDialog(null, "Eliminado con éxito.");
				}
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (fila != -1) {
					btnEliminar.setEnabled(true);
				}
			}

		});

		btnEliminar.setBounds(10, 61, 102, 34);
		panel_2.add(btnEliminar);

		// Boton Calcular Costo
		JButton btnCalcularCostoKm = new JButton("Calcular");
		btnCalcularCostoKm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlgoritmoAGM agm = new AlgoritmoAGM();
				Grafo grafoAgm = agm.generarAGM(fiberConnection.getGrafo());
				logicaMapa.dibujar(mapaLocalidadesJMapViewer, grafoAgm);
				/*
				 * List<Localidad> nodos = new
				 * ArrayList<>(fiberConnection.getGrafo().getAdyacencias().keySet());
				 * System.out.println(nodos.size()); if (nodos.size() >= 2) { Localidad puntoA =
				 * nodos.get(0); Localidad puntoB = nodos.get(1);
				 * 
				 * Double distancia = puntoA.distanciaEntreDosPuntos(puntoB); String
				 * distanciaString = String.format("%.2f", distancia);
				 * textAreaDistancia.setText(distanciaString); }
				 */
			}
		});

		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel.setBounds(36, 311, 1243, 366);
		getContentPane().add(panel);
		panel.setLayout(null);

		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel_3.setBounds(10, 11, 270, 344);
		panel.add(panel_3);
		panel_3.setLayout(null);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();

		btnCalcularCostoKm.setBounds(27, 72, 220, 33);
		panel_3.add(btnCalcularCostoKm);

		textAreaDistancia.setCaretColor(new Color(255, 255, 255));
		textAreaDistancia.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaDistancia.setBackground(new Color(255, 255, 255));
		textAreaDistancia.setBounds(34, 128, 213, 22);
		panel_3.add(textAreaDistancia);

		JTextArea textAreaCostoKm = new JTextArea();
		textAreaCostoKm.setEditable(false);
		textAreaCostoKm.setCaretColor(Color.WHITE);
		textAreaCostoKm.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaCostoKm.setBackground(Color.WHITE);
		textAreaCostoKm.setAutoscrolls(false);
		textAreaCostoKm.setBounds(34, 167, 213, 22);
		panel_3.add(textAreaCostoKm);

		panel_4.setBounds(290, 11, 931, 344);
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		InterfazMapa interfazMapa = new InterfazMapa(mapaLocalidadesJMapViewer);
		panel_4.add(interfazMapa, BorderLayout.CENTER);

	}

	public void cargarTabla(String[][] localidades) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (String[] fila : localidades) {
			modelo.addRow(new Object[] { fila[0], fila[1], fila[2], fila[3] });
		}
	}

	public void refrescarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);

		List<String> datosNuevos = controlLogica.getLocalidadesOrdenadas();

		for (String linea : datosNuevos) {
			String[] campos = linea.split(",");
			modelo.addRow(new Object[] { campos[0], campos[1], campos[2], campos[3] });
		}
	}

	public void inicializarDatos() {
		// 1. Obtenemos los datos de la BD
		List<String> localidadesLista = controlLogica.getLocalidadesOrdenadas();
		// 2. Recorremos la lista de String, la transformamos en Objeto Localidad y
		// agregamos las localidades al grafo
		for (String linea : localidadesLista) {
			String[] partes = linea.split(",");

			Localidad loc = new Localidad(Double.parseDouble(partes[0]), Double.parseDouble(partes[1]), partes[2],
					partes[3]);

			fiberConnection.construirGrafo(loc);
		}
		logicaMapa.dibujar(mapaLocalidadesJMapViewer, fiberConnection.getGrafo());
	}
}