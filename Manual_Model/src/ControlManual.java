import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import com.fazecast.jSerialComm.SerialPort;

public class ControlManual {

	public static SerialPort arduino = SerialPort.getCommPort("COM3");

	public static void leerAccion(){

		arduino.setComPortParameters(9600, 8, 1, 0);
		arduino.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

		JFrame ventana = new JFrame("Control Manual");
		ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		ventana.setSize(300, 300);
		ventana.setVisible(true);

		// Agregamos un KeyListener al JFrame para capturar eventos de teclado
		ventana.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// No necesitamos implementar esta función en este caso
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// Obtenemos el código de la tecla presionada
				int keyCode = e.getKeyCode();
				Integer num;
				// Procesamos el código de la tecla
				switch (keyCode) {
				
				case KeyEvent.VK_UP:

					num = 1;
					break;

				case KeyEvent.VK_DOWN:

					num = 2;
					break;

				case KeyEvent.VK_LEFT:

					num = 3;
					break;

				case KeyEvent.VK_RIGHT:
					
					num = 4;
					break;

				case KeyEvent.VK_Q:
					
					num = 5;
					break;
				
				case KeyEvent.VK_X:
					
					num = 6;
					break;

				default:
					num = 0;
					System.out.println("Tecla presionada no reconocida.");
				}
				
				try {
					serial(num);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// No necesitamos implementar esta función en este caso
			}
		});

	}

	public static void serial(Integer valor) throws IOException {
		
		if(!arduino.openPort()) {
            System.out.println("\nCOM no disponible\n"); return;
        }

		arduino.getOutputStream().write(valor.byteValue());

	}

}

