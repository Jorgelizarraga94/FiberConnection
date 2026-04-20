package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import entidades.Localidad;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;


public class InterfazDatos extends JFrame{

	
	private JTextField latitud;
	private JTextField longitud;
	private JTextField provincia;
	private JTextField Localidad;
	
	//Localidades 
	
	


	/**
	 * Create the application.
	 */
	public InterfazDatos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("InterfazDatos");
		lblNewLabel.setBounds(10, 0, 77, 34);
		this.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Latitud");
		lblNewLabel_1.setBounds(10, 62, 46, 14);
		this.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Longitud");
		lblNewLabel_2.setBounds(10, 87, 77, 14);
		this.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Provincia");
		lblNewLabel_3.setBounds(10, 115, 86, 14);
		this.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Localidad");
		lblNewLabel_4.setBounds(10, 156, 77, 14);
		this.getContentPane().add(lblNewLabel_4);
		
		latitud = new JTextField();
		latitud.setBounds(112, 59, 86, 20);
		this.getContentPane().add(latitud);
		latitud.setColumns(10);
		
		longitud = new JTextField();
		longitud.setBounds(112, 84, 86, 20);
		this.getContentPane().add(longitud);
		longitud.setColumns(10);
		
		provincia = new JTextField();
		provincia.setBounds(112, 112, 86, 20);
		this.getContentPane().add(provincia);
		provincia.setColumns(10);
		
		Localidad = new JTextField();
		Localidad.setBounds(112, 153, 86, 20);
		this.getContentPane().add(Localidad);
		Localidad.setColumns(10);
		
		JButton botonCarga = new JButton("New button");
		botonCarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pasar al mapa al darle click al boton
				
			}
		});
		botonCarga.setBounds(112, 197, 89, 23);
		this.getContentPane().add(botonCarga);
	}
	
	

	

	
}
