package ventanas;

import javax.swing.*;

import org.opencv.core.Core;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
    	
        setTitle("NEMESIS  V-0.0.1");
     // Crea un objeto ImageIcon usando la ruta de la imagen
        ImageIcon icono = new ImageIcon("images\\6754301.png");

        // Establece el icono del JFrame usando el método setIconImage
        setIconImage(icono.getImage());
        pack();
        setLocationRelativeTo(null);

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel panelBotones = new JPanel();

        JButton botonVentana1 = new JButton("Control Manual");
        JButton botonVentana2 = new JButton("Control semiautomatico");
        JButton botonVentana3 = new JButton("Control automatico");
        JButton botonCerrar = new JButton("Cerrar Programa");

        panelBotones.add(botonVentana1);
        panelBotones.add(botonVentana2);
        panelBotones.add(botonVentana3);
        panelBotones.add(botonCerrar);

        add(panelBotones);

        botonVentana1.addActionListener(e -> {ControlManual.leerAccion();});
        botonVentana2.addActionListener(e -> new ModoSemiAutomatico().setVisible(true));
        botonVentana3.addActionListener(e -> new ModoAutomatico().setVisible(true));
        botonCerrar.addActionListener(e -> System.exit(0));

        setVisible(true);
        
    }

    public static void main(String[] args) {
    	
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	
        new VentanaPrincipal();
        
    }
    
}