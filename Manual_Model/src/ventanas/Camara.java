package ventanas;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class Camara {
	
    public void detectAndDisplay(Mat frame) {
    		
        Mat frameGray = new Mat();
        Imgproc.cvtColor(frame, frameGray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(frameGray, frameGray);
        HighGui.imshow("Deteccion de drones", frame );
    }

    public void run(String[] args) {
    	
        int cameraDevice = args.length > 2 ? Integer.parseInt(args[2]) : 0;

        VideoCapture capture = new VideoCapture(cameraDevice);
        
        if (!capture.isOpened()) {
            System.err.println("Error al abrir la camara");
            System.exit(0);
        }

        Mat frame = new Mat();
        
        while (capture.read(frame)) {
        	
            if (frame.empty()) {
                System.err.println("Error al leer el frame");
                break;
            }

            detectAndDisplay(frame);

            if (HighGui.waitKey(10) == 27) { // esc
                break;
            }
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        // Load the native OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        new Camara().run(args);
   }
}
