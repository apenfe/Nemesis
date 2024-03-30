package ventanas;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;

public class WebcamVideoDisplay {

    static {
        // Carga la biblioteca nativa de OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // Crea un objeto de captura de video desde la webcam
        VideoCapture capture = new VideoCapture(0);

        // Verifica si la captura de video desde la webcam se ha abierto correctamente
        if (!capture.isOpened()) {
            System.out.println("No se pudo abrir la webcam.");
            return;
        }

        // Crea un JFrame para mostrar el video
        JFrame frame = new JFrame("Webcam Video Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea un JLabel para mostrar el video
        JLabel label = new JLabel();
        frame.getContentPane().add(label);

        // Establece el tamaño del JFrame según el tamaño del video
        int frameWidth = (int) capture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        int frameHeight = (int) capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        //frame.setSize(1000, 1000);

        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);

        // Bucle para capturar y mostrar cada fotograma del video
        Mat frameMat = new Mat();
        BufferedImage bufferedImage;
        ImageIcon imageIcon;
        while (true) {
            // Captura un fotograma del video desde la webcam
            capture.read(frameMat);

            // Verifica si el fotograma capturado no es nulo
            if (!frameMat.empty()) {
                // Convierte el fotograma a un formato que se puede mostrar en un JLabel
                bufferedImage = MatToBufferedImage(frameMat);
                imageIcon = new ImageIcon(bufferedImage);

                // Actualiza el contenido del JLabel con el nuevo fotograma
                label.setIcon(imageIcon);
                label.repaint(); // Redibuja el JLabel

                // Pequeño retardo para ajustar la velocidad de visualización del video
                try {
                    Thread.sleep(30); // Ajusta el retardo según la velocidad deseada
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método para convertir un objeto Mat de OpenCV a un BufferedImage
    private static BufferedImage MatToBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
        final byte[] targetPixels = ((java.awt.image.DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }
}
