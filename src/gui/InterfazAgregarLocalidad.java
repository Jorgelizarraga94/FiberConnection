package gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import entidades.ControladoraLogica;
import entidades.Localidad;
import persistencia.ControladoraPersistencia;
import servicio.FiberConnection;
import servicio.LogicaMapa;
public class InterfazAgregarLocalidad extends JFrame {
	private JFrame frame;
	private JTextField latitud;
	private JTextField longitud;
	private JTextField prov;
	private JTextField nombreLocalidad;
	private JMapViewer mapaLocalidadesJMapViewer;
	private FiberConnection fiberConnection;
	private LogicaMapa logicaMapa;
	private InterfazDatos interfazDatos;
	private JTable tabla;
	private ControladoraLogica controladoraLogica = new ControladoraLogica();
	
	private List<Localidad> localidades = new ArrayList<>();
	
	public InterfazAgregarLocalidad(JMapViewer mapaLocalidadesJMapViewer, FiberConnection fiberConnection,
			LogicaMapa logicaMapa, JTable table, InterfazDatos interfazDatos) {
		this.logicaMapa = logicaMapa;
		this.mapaLocalidadesJMapViewer = mapaLocalidadesJMapViewer;
		this.fiberConnection = fiberConnection;
		tabla = table;
		this.interfazDatos = interfazDatos;
		this.initialize();
	}
	
	private void initialize() {
		JPanel panel = new JPanel();
		JLabel titulo = new JLabel("Carga de Localidades");
		JLabel lblNom = new JLabel("Localidad");
		JLabel lblProv = new JLabel("Provincia");
		JLabel lblLon = new JLabel("Longitud");
		JLabel lblLat = new JLabel("Latitud");
		JButton btnAgregarLocalidad = new JButton("Agregar");
		
		longitud = new JTextField();
		nombreLocalidad = new JTextField();
		latitud = new JTextField();
		prov = new JTextField();
		
		//Configuración diseño Frame
		this.setBounds(100, 100, 472, 303);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel.setBounds(10, 11, 231, 248);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		titulo.setBounds(164, 23, 128, 20);
		panel.add(titulo);
		
		latitud.setBounds(199, 54, 120, 20);
		panel.add(latitud);
		
		longitud.setBounds(199, 85, 120, 20);
		panel.add(longitud);
		
		prov.setBounds(199, 116, 120, 20);
		panel.add(prov);
		
		nombreLocalidad.setBounds(199, 147, 120, 20);
		
		//Labels
		lblNom.setBounds(109, 150, 80, 14);
		lblProv.setBounds(109, 119, 80, 14);	
		lblLon.setBounds(109, 88, 80, 14);
		lblLat.setBounds(109, 57, 80, 14);
		
		//Agregado de Objetos a Panel
		panel.add(lblLon);
		panel.add(lblProv);
		panel.add(lblNom);
		panel.add(lblLat);
		panel.add(nombreLocalidad);
		panel.add(btnAgregarLocalidad);
		
		//Boton AgregarLocalidad
		btnAgregarLocalidad.setBounds(109, 196, 210, 34);
		btnAgregarLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (nombreLocalidad.getText().trim().isEmpty() || prov.getText().trim().isEmpty()) {
                        // Muestra un mensaje de error si está en blanco
                        JOptionPane.showMessageDialog(null, 
                            "La localidad o la casilla de provincia no pueden estar en blanco.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                            throw new RuntimeException("La localidad o la casilla de provincia no pueden estar en blanco.");
                    }
					String nombre = nombreLocalidad.getText().toUpperCase();
					String provincia = prov.getText().toUpperCase();
					double latit = Double.parseDouble(latitud.getText());
					double longit = Double.parseDouble(longitud.getText());
					fiberConnection.construirGrafo(new Localidad(latit, longit, provincia, nombre));
					cargarTabla(fiberConnection.getGrafo().obtenerLocalidades().get(0));
					controladoraLogica.saveLocalidad(latit, longit, provincia, nombre);
					interfazDatos.refrescarTabla();
					interfazDatos.inicializarDatos();
					logicaMapa.actualizarMapa(fiberConnection.getGrafo(), mapaLocalidadesJMapViewer);
					JOptionPane.showMessageDialog(null, "Localidad agregada: " + nombre);
					dispose();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error: Latitud y Longitud deben ser números.");
				}
			}
		});	
	}
	
	public void cargarTabla(Localidad localidad) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		Object[] nuevaFila = {localidad.getLatitud(), localidad.getLongitud(), localidad.getProvincia(), localidad.getNombre()};
		modelo.addRow(nuevaFila);
	}
}

