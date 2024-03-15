import java.util.Scanner;
import jssc.SerialPort;
import jssc.SerialPortException;

public class Principal {

	public static Scanner teclado = new Scanner(System.in);
	private static final String PUERTO_SERIAL = "COM3"; // Cambiar según el puerto correcto en el sistema
	public static SerialPort serialPort = new SerialPort(PUERTO_SERIAL);

	public static void main(String[] args) {

		try {

			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			while (true) {

				String input = teclado.nextLine();

				if (input.equalsIgnoreCase("exit")) {
					break;
				}

				try {

					int angle = Integer.parseInt(input);
					if (angle >= 0 && angle <= 180) {
						// Enviar el ángulo al Arduino para controlar el servo
						serialPort.writeInt(angle);
						System.out.println("Ángulo enviado al servo: " + angle);
					} else {
						System.out.println("El ángulo debe estar entre 0 y 180.");
					}

				} catch (NumberFormatException e) {

					System.out.println("Entrada no válida. Por favor, ingresa un número entre 0 y 180.");

				}

			}

			serialPort.closePort();

		} catch (SerialPortException ex) {

			System.out.println("Error al abrir el puerto serial: " + ex.getMessage());
			ex.printStackTrace();

		}

	}

}