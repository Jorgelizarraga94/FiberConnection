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
	private List<Localidad> localidades = new ArrayList<>();
	private ControladoraLogica controladoraLogica = new ControladoraLogica();
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
		this.setBounds(100, 100, 472, 303);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		panel.setBounds(10, 11, 231, 248);
		getContentPane().add(panel);
		panel.setLayout(null);
		JLabel titulo = new JLabel("Carga de Localidades");
		titulo.setBounds(164, 23, 128, 20);
		panel.add(titulo);
		latitud = new JTextField();
		latitud.setBounds(199, 54, 120, 20);
		panel.add(latitud);
		longitud = new JTextField();
		longitud.setBounds(199, 85, 120, 20);
		panel.add(longitud);
		prov = new JTextField();
		prov.setBounds(199, 116, 120, 20);
		panel.add(prov);
		nombreLocalidad = new JTextField();
		nombreLocalidad.setBounds(199, 147, 120, 20);
		panel.add(nombreLocalidad);
		JLabel lblNom = new JLabel("Localidad");
		lblNom.setBounds(109, 150, 80, 14);
		panel.add(lblNom);
		JLabel lblProv = new JLabel("Provincia");
		lblProv.setBounds(109, 119, 80, 14);
		panel.add(lblProv);
		JLabel lblLon = new JLabel("Longitud");
		lblLon.setBounds(109, 88, 80, 14);
		panel.add(lblLon);
		JLabel lblLat = new JLabel("Latitud");
		lblLat.setBounds(109, 57, 80, 14);
		panel.add(lblLat);
		JButton btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = nombreLocalidad.getText();
					String provincia = prov.getText();
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
		btnAgregarLocalidad.setBounds(109, 196, 210, 34);
		panel.add(btnAgregarLocalidad);
	}
	public void cargarTabla(Localidad localidad) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		Object[] nuevaFila = {localidad.getLatitud(), localidad.getLongitud(), localidad.getProvincia(), localidad.getNombre()};
		modelo.addRow(nuevaFila);
	}
}

