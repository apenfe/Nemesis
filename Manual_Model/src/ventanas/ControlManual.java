package ventanas;

import java.awt.event.KeyEvent;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import com.fazecast.jSerialComm.SerialPort;

public class ControlManual {

	public static SerialPort arduino = SerialPort.getCommPort("COM3");
	

	public static void leerAccion(){

		arduino.setComPortParameters(9600, 8, 1, 0);
		arduino.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

		VideoCapture videoCapture = new VideoCapture(0);

        if (!videoCapture.isOpened()) {
            System.err.println("Error al abrir la camara");
            System.exit(0);
        }
  
        // Iniciar un hilo para mostrar la cámara
        new Thread(() -> {
        	
            while (true) {
            	
                // Leer un fotograma de la cámara
            	
            	Mat frame = new Mat();
                videoCapture.read(frame);
                
             // Usa la librería Java AWT para obtener el tamaño de la pantalla
              //  int ancho = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                //int alto = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                int ancho = 1600;
                int alto = 900;
                /*
                 * 1920
                 * 995
                 */
                Mat frameRedimensionado = new Mat();
                Imgproc.resize(frame, frameRedimensionado, new Size(ancho, alto), 0.0, 0.0, Imgproc.INTER_AREA);
                
                HighGui.imshow("NEMESIS ---> MODO MANUAL (CAMERA)", frameRedimensionado);
                
                int key = HighGui.waitKey(5); // Wait for 1 millisecond

                if (key == KeyEvent.VK_Q) {
                	
                    System.out.println("Exiting...");
                    
                    break;
                } else if (key == KeyEvent.VK_SPACE) {
                    System.out.println("espacio");
                } else if (key == KeyEvent.VK_UP) {
                	System.out.println("arriba");
                } else if (key == KeyEvent.VK_DOWN) {
                	System.out.println("abajo");
                }
                
            }
            
            videoCapture.release();
            HighGui.destroyAllWindows();
            
        }).start(); 

	}

}

