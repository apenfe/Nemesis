import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

//import jssc.SerialPort;
//import jssc.SerialPortException;
//import jssc.*;
import java.io.Console;
import java.io.IOException;

public class Principal {

	public static Scanner teclado = new Scanner(System.in);
	private static final String PUERTO_SERIAL = "COM3"; 
	//public static SerialPort serialPort = new SerialPort(PUERTO_SERIAL);

	public static void main(String[] args)throws IOException, InterruptedException {
		
		while(true) {
			
			System.out.println("--- nemesis ---");
			System.out.println("1 --> Control manual");
			System.out.println("2 --> Consola");
			System.out.println("3 --> Salir");
			int opcion = pedirInt("Seleccione una opcion valida");
			
			if(opcion==1) {
				
				serial();
				
			}else if(opcion==3) {
				
				System.out.println("Saliendo del programa...");
				break;
				
			}else if(opcion==2) {
				
				consola();
				
			}else {
				
				System.out.println("Seleccione una opcion valida del menu");

			}
			
		}

	}
	
	public static int pedirInt(String texto) {
		
		System.out.print(texto);
		
		do {
			
			try {
				
				int num = Integer.parseInt(teclado.nextLine().trim());
				return num;
				
			} catch (Exception e) {
				
				System.out.print("ERROR, inserte un numero entero: ");
				
			}
			
		}while(true);
		
	}
	
	public static void controlManual() {
		
		ControlManual.leerAccion();
		/*
		try {
			SerialPort serialPort = new SerialPort(PUERTO_SERIAL);
			serialPort.openPort();
			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

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
		*/
	}
	
	    public static void consola() {
	        Console console = System.console();
	        if (console == null) {
	            System.err.println("No se puede acceder a la consola.");
	            System.exit(1);
	        }

	        System.out.println("Presiona 'q' para salir.");

	        while (true) {
	            char input = console.readLine().charAt(0);
	            if (input == 'q') {
	                System.out.println("Saliendo del programa...");
	                break;
	            }
	            System.out.println("Tecla presionada: " + input);
	        }
	    }
	    
	    public static void serial() throws IOException, InterruptedException
	    {
	        SerialPort sp = SerialPort.getCommPort("COM3");
	        sp.setComPortParameters(9600, 8, 1, 0);
	        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
	        //-------------------------------------------------------------
	        if(!sp.openPort()) {
	            System.out.println("\nCOM port NOT available\n"); return;
	        }
	        //-------------------------------------------------------------
	        Scanner input = new Scanner(System.in);
	        while(true) {
	            System.out.print("\nEnter number of LED blinks (0 to exit): ");
	            Integer blinks = input.nextInt();
	            if(blinks == 0) break;
	            //Thread.sleep(1500);
	            sp.getOutputStream().write(blinks.byteValue());
	        }
	        input.close();
	    }
	

}