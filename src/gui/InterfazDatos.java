package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import entidades.Localidad;
import grafo.Grafo;
import servicio.FiberConnection;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class InterfazDatos extends JFrame {

    private JTextField latitud;
    private JTextField longitud;
    private JTextField prov;
    private JTextField nombreLocalidad;

    private List<Localidad> localidades = new ArrayList<>();

    public InterfazDatos() {
        initialize();
    }

    private void initialize() {

        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titulo = new JLabel("Carga de Localidades");
        titulo.setBounds(10, 10, 200, 20);
        this.getContentPane().add(titulo);

        JLabel lblLat = new JLabel("Latitud");
        lblLat.setBounds(10, 60, 80, 14);
        this.getContentPane().add(lblLat);

        JLabel lblLon = new JLabel("Longitud");
        lblLon.setBounds(10, 90, 80, 14);
        this.getContentPane().add(lblLon);

        JLabel lblProv = new JLabel("Provincia");
        lblProv.setBounds(10, 120, 80, 14);
        this.getContentPane().add(lblProv);

        JLabel lblNom = new JLabel("Localidad");
        lblNom.setBounds(10, 150, 80, 14);
        this.getContentPane().add(lblNom);

        latitud = new JTextField();
        latitud.setBounds(100, 60, 120, 20);
        this.getContentPane().add(latitud);

        longitud = new JTextField();
        longitud.setBounds(100, 90, 120, 20);
        this.getContentPane().add(longitud);

        prov = new JTextField();
        prov.setBounds(100, 120, 120, 20);
        this.getContentPane().add(prov);

        nombreLocalidad = new JTextField();
        nombreLocalidad.setBounds(100, 150, 120, 20);
        this.getContentPane().add(nombreLocalidad);
        
        
        //Boton para ver el Mapa
        JButton btnMapa = new JButton("Ver mapa");
        btnMapa.setBounds(250, 230, 150, 23);

        btnMapa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                

                Mapa mapa = new Mapa(localidades);
                mapa.setVisible(true);
                dispose();
            }
        });

        this.getContentPane().add(btnMapa);
       
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(250, 150, 150, 23);

        //Boton para cargar datos
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	
                    String nombre = nombreLocalidad.getText();
                    String provincia = prov.getText();
                    double lat = Double.parseDouble(latitud.getText());
                    double lon = Double.parseDouble(longitud.getText());

                    Localidad loc = new Localidad(nombre, provincia, lat, lon);

                    localidades.add(loc);

                    JOptionPane.showMessageDialog(null, "Localidad agregada");

                    nombreLocalidad.setText("");
                    prov.setText("");
                    latitud.setText("");
                    longitud.setText("");

                
            }
        });

        this.getContentPane().add(btnAgregar);

       
        JButton btnPlanificar = new JButton("Planificar");
        btnPlanificar.setBounds(250, 200, 150, 23);

        btnPlanificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FiberConnection fc = new FiberConnection();
                fc.construirGrafo(localidades);

                Grafo grafo = fc.getGrafo();

                JOptionPane.showMessageDialog(null,
                        "Grafo construido con " + grafo.obtenerLocalidades().size() + " localidades");
            }
        });
        this.getContentPane().add(btnPlanificar);
    }
}