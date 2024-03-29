package ventanas;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.*;

import javax.swing.*;

public class CamaraOpenCVPanel extends JPanel {

    private JLabel etiquetaImagen;

    public CamaraOpenCVPanel(JFrame ventana) {

    	VideoCapture videoCapture = new VideoCapture(0);

        if (!videoCapture.isOpened()) {
            System.err.println("Error al abrir la camara");
            System.exit(0);
        }

        // Crear un panel para mostrar la imagen
        JPanel panelImagen = new JPanel();
        ventana.add(panelImagen);

        // Crear la etiqueta para la imagen
        etiquetaImagen = new JLabel();
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
    }
    
    public void display(Mat frame) {
		
        Mat frameGray = new Mat();
        Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(frameGray, frameGray);
        HighGui.imshow("NEMESIS ---> MODO MANUAL (CAMERA)", frame );
    }
}
