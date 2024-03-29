package ventanas;

import javax.swing.*;

public class ModoManual extends JFrame{
	
    public ModoManual() {
    	
        setTitle("NEMESIS // MODO MANUAL");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panelBotones = new JPanel();

        JButton botonEncender = new JButton("ENCENDER");

        panelBotones.add(botonEncender);

        add(panelBotones);
        
        botonEncender.addActionListener(e -> {ControlManual.leerAccion();});
        
    }
    
}
