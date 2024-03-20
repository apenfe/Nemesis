import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class ControlManual {

	public static void leerAccion() {
        JFrame frame = new JFrame("Control Manual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);

        // Agregamos un KeyListener al JFrame para capturar eventos de teclado
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // No necesitamos implementar esta función en este caso
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Obtenemos el código de la tecla presionada
                int keyCode = e.getKeyCode();
                // Procesamos el código de la tecla
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        System.out.println("Tecla Arriba presionada");
                        break;

                    case KeyEvent.VK_DOWN:
                        System.out.println("Tecla Abajo presionada");
                        break;

                    case KeyEvent.VK_LEFT:
                        System.out.println("Tecla Izquierda presionada");
                        break;

                    case KeyEvent.VK_RIGHT:
                        System.out.println("Tecla Derecha presionada");
                        break;

                    case KeyEvent.VK_Q:
                        System.out.println("Saliendo del programa.");
                        System.exit(0); // Salir del programa cuando se presiona 'q'
                        break;

                    default:
                        System.out.println("Tecla presionada no reconocida.");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No necesitamos implementar esta función en este caso
            }
        });
	}}

