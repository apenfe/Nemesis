package ventanas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

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
		
		VideoCapture videoCapture = new VideoCapture(0);

        if (!videoCapture.isOpened()) {
            System.err.println("Error al abrir la camara");
            System.exit(0);
        }
        
        JPanel panelImagen = new JPanel();
        ventana.add(panelImagen);
        
     // Crear la etiqueta para la imagen
        JLabel etiquetaImagen = new JLabel();
        panelImagen.add(etiquetaImagen);

        // Iniciar un hilo para mostrar la cámara
        new Thread(() -> {
        	
            while (true) {
                // Leer un fotograma de la cámara
                Mat frame = new Mat();
                videoCapture.read(frame);
                display(frame);
               
                // Salir del bucle si se presiona la tecla "q"
                if (HighGui.waitKey(10) == 27) {
                    break;
                }
            }

            // Liberar la cámara
            videoCapture.release();
            
        }).start();
		
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
	
	 public static void display(Mat frame) {
			
	        HighGui.imshow("NEMESIS ---> MODO MANUAL (CAMERA)", frame );
	        
	    }

}

