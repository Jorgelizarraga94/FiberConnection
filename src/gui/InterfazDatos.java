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
	private InterfazMapa interfazMapa;

	public InterfazDatos(JMapViewer mapa, FiberConnection fiberConnection, LogicaMapa logicaMapa) {
		this.logicaMapa = logicaMapa;
		this.fiberConnection = fiberConnection;
		this.mapaLocalidadesJMapViewer = mapa;
		initialize();
	}

	private void initialize() {
		// Instancia de Objetos de la interfaz
		JButton btnEliminar = new JButton("Eliminar");
		JPanel panel_1 = new JPanel();
		JScrollPane scrollPane = new JScrollPane();
		JPanel panel_2 = new JPanel();
		JButton btnAgregar = new JButton("Agregar");
		JPanel panel = new JPanel();
		JPanel panel_3 = new JPanel();
		JButton btnCalcularCosto = new JButton("CALCULAR CONEXIONES");
		JLabel lblKmTotalesConexion = new JLabel("TOTAL CONEXIÓN");
		JTextArea textAreaCostoTotal = new JTextArea();
		JLabel lblCostoTotal = new JLabel("COSTO TOTAL");
		JTextArea textAreaKmTotales = new JTextArea();
		JPanel panel_4 = new JPanel();
		JTextArea textAreaPorcentajeMayor300 = new JTextArea();

		interfazMapa = new InterfazMapa(mapaLocalidadesJMapViewer);

		// Configuración diseño frame
		this.setBounds(100, 100, 1366, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		scrollPane.setBounds(10, 11, 1072, 226);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Tabla
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Latitud", "Longitud", "Provincia", "Localidad" }));

		scrollPane.setViewportView(table);

		List<String> datos = controlLogica.getLocalidades();
		String[][] datosLocalidades = new String[datos.size()][4];
		for (int i = 0; i < datos.size(); i++) {
			String[] partes = datos.get(i).split(",");
			datosLocalidades[i][0] = partes[0];
			datosLocalidades[i][1] = partes[1];
			datosLocalidades[i][2] = partes[2];
			datosLocalidades[i][3] = partes[3];
		}
		cargarTabla(datosLocalidades);

		// Panel
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel.setBounds(36, 311, 1243, 366);
		getContentPane().add(panel);
		panel.setLayout(null);
		// Panel 1
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(26, 21, 1110, 248);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		// Panel 2
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel_2.setBounds(1161, 21, 122, 248);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		// Panel 3
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel_3.setBounds(10, 11, 270, 344);
		panel_3.setLayout(null);
		// Panel 4
		panel_4.setBounds(290, 11, 931, 344);
		panel_4.setLayout(new BorderLayout(0, 0));

		// Diseño textAreaKmTotales
		textAreaKmTotales.setCaretColor(new Color(255, 255, 255));
		textAreaKmTotales.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaKmTotales.setBackground(new Color(255, 255, 255));
		textAreaKmTotales.setBounds(140, 212, 107, 25);
		textAreaKmTotales.setEditable(false);
		textAreaKmTotales.setAutoscrolls(false);

		// Diseño textAreaCostoTotal
		textAreaCostoTotal.setEditable(false);
		textAreaCostoTotal.setCaretColor(Color.WHITE);
		textAreaCostoTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaCostoTotal.setBackground(Color.WHITE);
		textAreaCostoTotal.setAutoscrolls(false);
		textAreaCostoTotal.setBounds(114, 255, 133, 25);

		lblCostoTotal.setBounds(10, 259, 99, 14);
		lblKmTotalesConexion.setBounds(10, 216, 121, 14);

		// Agregados a paneles
		panel.add(panel_4);
		panel.add(panel_3);
		panel_1.add(scrollPane);
		panel_2.add(btnAgregar);
		panel_2.add(btnEliminar);
		panel_3.add(textAreaCostoTotal);
		panel_3.add(lblKmTotalesConexion);
		panel_3.add(btnCalcularCosto);
		panel_3.add(textAreaKmTotales);
		panel_3.add(lblCostoTotal);
		panel_4.add(interfazMapa, BorderLayout.CENTER);

		// ActionListener Boton Agregar
		btnAgregar.setBounds(10, 11, 102, 34);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazAgregarLocalidad interfazAgregarLocalidad = new InterfazAgregarLocalidad(
						mapaLocalidadesJMapViewer, fiberConnection, logicaMapa, table, InterfazDatos.this);
				interfazAgregarLocalidad.setVisible(true);
			}
		});

		// ActionListener Eliminar
		btnEliminar.setBounds(10, 61, 102, 34);
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					Localidad localidad = controlLogica
							.convertirListaAobjetoLocalidad(controlLogica.Getlocalidad(filaSeleccionada));
					controlLogica.deleteSeleccionado(filaSeleccionada);
					fiberConnection.eliminarLocalidadGrafo(localidad);
					refrescarTabla();
					logicaMapa.actualizarMapa(fiberConnection.getGrafo(), mapaLocalidadesJMapViewer);
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

		// ActionListener Boton Calcular Costo
		btnCalcularCosto.setBounds(27, 72, 220, 33);

		textAreaPorcentajeMayor300.setEditable(false);
		textAreaPorcentajeMayor300.setCaretColor(Color.WHITE);
		textAreaPorcentajeMayor300.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaPorcentajeMayor300.setBackground(Color.WHITE);
		textAreaPorcentajeMayor300.setAutoscrolls(false);
		textAreaPorcentajeMayor300.setBounds(140, 123, 107, 25);
		panel_3.add(textAreaPorcentajeMayor300);

		JLabel lblPocentajeAumentoMayor300km = new JLabel("PORCENTAJE  > 300KM");
		lblPocentajeAumentoMayor300km.setBounds(10, 127, 121, 14);
		panel_3.add(lblPocentajeAumentoMayor300km);

		JLabel lblCostoAdicionalProvDist = new JLabel("ADICIONAL PROV DIST");
		lblCostoAdicionalProvDist.setBounds(10, 163, 121, 14);
		panel_3.add(lblCostoAdicionalProvDist);

		JTextArea textAreaAdicionalProvDist = new JTextArea();
		textAreaAdicionalProvDist.setEditable(false);
		textAreaAdicionalProvDist.setCaretColor(Color.WHITE);
		textAreaAdicionalProvDist.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaAdicionalProvDist.setBackground(Color.WHITE);
		textAreaAdicionalProvDist.setAutoscrolls(false);
		textAreaAdicionalProvDist.setBounds(140, 159, 107, 25);
		panel_3.add(textAreaAdicionalProvDist);
		btnCalcularCosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlgoritmoAGM agm = new AlgoritmoAGM();
				Grafo grafoAgm = agm.generarAGM(fiberConnection.getGrafo());
				logicaMapa.actualizarMapa(grafoAgm, mapaLocalidadesJMapViewer);
				textAreaKmTotales.setText(String.format("%.2f", fiberConnection.calcularKmTotales(grafoAgm)) + " KM");
				textAreaCostoTotal.setText("$" + String.format("%.2f", fiberConnection.calcularPresupuesto(grafoAgm)));

				textAreaPorcentajeMayor300.setText(fiberConnection.calcularPorcentajeMayorA300km());
				textAreaAdicionalProvDist.setText(fiberConnection.hayProvinciasDistintas());
			}
		});
	}

	///////////////////////////////////////////// FUNCIONES////////////////////////////////////////////

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
		List<String> datosNuevos = controlLogica.getLocalidades();
		for (String linea : datosNuevos) {
			String[] campos = linea.split(",");
			modelo.addRow(new Object[] { campos[0], campos[1], campos[2], campos[3] });
		}
	}

	public void inicializarDatos() {
		List<String> localidadesLista = controlLogica.getLocalidades();
		for (String linea : localidadesLista) {
			Localidad loc = controlLogica.convertirListaAobjetoLocalidad(linea);
			fiberConnection.construirGrafo(loc);
		}
		logicaMapa.dibujar(mapaLocalidadesJMapViewer, fiberConnection.getGrafo());
	}
}