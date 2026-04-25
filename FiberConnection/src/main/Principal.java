package main;

	
import javax.swing.*;

import gui.InterfazDatos;
	

	public class Principal extends JFrame
	{
		public static void main(String[] args) 
		{
			InterfazDatos interfaz = new InterfazDatos();
			interfaz.setVisible(true);
			interfaz.setLocationRelativeTo(null);
		}
	}