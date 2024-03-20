import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

//import jssc.SerialPort;
//import jssc.SerialPortException;
//import jssc.*;
import java.io.Console;
import java.io.IOException;

public class Principal {

	public static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args){
		
		while(true) {
			
			System.out.println("--- nemesis ---");
			System.out.println("1 --> Control manual");
			System.out.println("2 --> Control semi-automatico");
			System.out.println("3 --> Control automatico");
			System.out.println("4 --> Salir");
			int opcion = pedirInt("Seleccione una opcion valida [1-4]: ");
			
			if(opcion==1) {
				
				ControlManual.leerAccion();
				
			}else if(opcion==3) {
				
				System.out.println("Saliendo del programa...");
				break;
				
			}else if(opcion==2) {
				
				System.out.println("Proximamente");
				
			}else if(opcion==4) {
				
				System.out.println("Proximamente");
				
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

}