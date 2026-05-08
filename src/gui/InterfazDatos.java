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

public class InterfazDatos extends JFrame {
	private FiberConnection fiberConnection;
	private LogicaMapa logicaMapa;
	private JMapViewer mapaLocalidadesJMapViewer;
	private JTable table;
	private JTextField textFieldCosto;
	private JComboBox comboBoxLocalidadA;
	private ControladoraLogica controlLogica = new ControladoraLogica();

	public InterfazDatos(JMapViewer mapa, FiberConnection fiberConnection, LogicaMapa logicaMapa) {
		this.logicaMapa = logicaMapa;
		this.fiberConnection = fiberConnection;
		this.mapaLocalidadesJMapViewer = mapa;
		initialize();
	}

	private void initialize() {
		JButton btnEliminar = new JButton("Eliminar");

		this.setBounds(100, 100, 1366, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();

		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(26, 21, 1110, 248);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 11, 1072, 226);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane);

		table = new JTable();
		//Evita que las celdas sean editables
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

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel_2.setBounds(1161, 21, 122, 248);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazAgregarLocalidad interfazAgregarLocalidad = new InterfazAgregarLocalidad(
						mapaLocalidadesJMapViewer, fiberConnection, logicaMapa, table, InterfazDatos.this);
				interfazAgregarLocalidad.setVisible(true);
			}
		});

		btnAgregar.setBounds(10, 11, 102, 34);
		panel_2.add(btnAgregar);

		btnEliminar.setEnabled(false);

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlLogica.deleteSeleccionado(table.getSelectedRow());
				refrescarTabla();
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

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel.setBounds(36, 311, 1243, 366);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel_3.setBounds(10, 11, 270, 344);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("Costo por KM");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(69, 28, 123, 22);
		panel_3.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Localidad A");
		lblNewLabel_1.setBounds(20, 99, 79, 14);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Localidad B");
		lblNewLabel_1_1.setBounds(20, 140, 79, 14);
		panel_3.add(lblNewLabel_1_1);

		comboBoxLocalidadA = new JComboBox();	
		comboBoxLocalidadA.setBounds(123, 91, 123, 22);
		panel_3.add(comboBoxLocalidadA);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		Grafo grafo = fiberConnection.getGrafo();
		for(Localidad localidad : grafo.getAdyacencias().keySet()) {
			
		}
		// 1. Obtenemos los datos frescos de la base/archivo
	    List<String> datosa = controlLogica.getLocalidadesOrdenadas();
	    String[] nombres = new String[datosa.size()];
	    
	    for (int i = 0; i < datosa.size(); i++) {
	        String[] partes = datosa.get(i).split(",");
	        nombres[i] = partes[3]; // El nombre de la localidad
	    }
	    
	    // 2. Seteamos los modelos
	    comboBoxLocalidadA.setModel(new DefaultComboBoxModel<>(nombres));

		JComboBox comboBoxLocalidadB = new JComboBox();
		comboBoxLocalidadB.setBounds(123, 136, 123, 22);
		panel_3.add(comboBoxLocalidadB);

		textFieldCosto = new JTextField();
		textFieldCosto.setBounds(33, 276, 213, 20);
		panel_3.add(textFieldCosto);
		textFieldCosto.setColumns(10);

		JButton btnCalcularCostoKm = new JButton("Calcular");
		btnCalcularCostoKm.setBounds(26, 187, 220, 33);
		panel_3.add(btnCalcularCostoKm);

		JPanel panel_4 = new JPanel();
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
	
	public void refrescarCombos() {
	    // Obtenemos el grafo desde la lógica
	    Grafo grafoActual = fiberConnection.getGrafo();
	    
	    // Obtenemos la lista de objetos Localidad reales
	    List<Localidad> localidades = grafoActual.obtenerLocalidades();
	    
	    // Creamos los modelos para los combos
	    DefaultComboBoxModel<Localidad> modeloA = new DefaultComboBoxModel<>();
	    DefaultComboBoxModel<Localidad> modeloB = new DefaultComboBoxModel<>();
	    
	    for (Localidad localidad : localidades) {
	        modeloA.addElement(localidad);
	    }
	    
	    // Asignamos los modelos a los combos de la interfaz
	    comboBoxLocalidadA.setModel(modeloA);
	    
	}
	
	
}