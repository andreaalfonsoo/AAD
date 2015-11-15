import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FicherosComp {
	
	public static void main(String[] args) {
		
		//creamos dos ficheros
		File fichero1 =new File("fichero1.txt");
		File fichero2 =new File("fichero2.txt");
		
		try {
			compararContenido(fichero1, fichero2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		buscarPalabra(fichero1, "Andrea", true);
	}

	public static boolean compararContenido(File fichero1, File fichero2)throws IOException{
	
			//Crear los dos ficheros			
			BufferedReader br1 = new BufferedReader(new FileReader("fichero1.txt"));
			BufferedReader br2 = new BufferedReader(new FileReader("fichero2.txt"));
			
			//Guardara cada linea en un String
			String string1 = null;
			String string2 = null;
			
			//mientras hayan lineas por leer se almacenaran en el ArrayList
			List<String> lista1 = new ArrayList<String>();
			List<String> lista2 = new ArrayList<String>();
			
			//se almacenan los arrays en los strings
			while ((string1 = br1.readLine()) != null){
				lista1.add(string1);				
			}
			while ((string2 = br2.readLine()) != null){
				lista1.add(string2);				
			}
			
			//cerraremos el buffer para indicar que hemos terminado de leer
			br1.close();
			br2.close();
			
			//Compara si son iguales o no los ficheros
			if(lista1.equals(lista2)){
				System.out.println("Son iguales los ficheros");
				return true;
			}else{
				System.out.println("Son diferentes los ficheros");
				return false;
			}
	}
	
	public static int buscarPalabra(File fichero1, String palabra, boolean primeraPalabra){
		int cuentaLinea = 0; //contara las lineas
		int numeroLinea = 0; //contara en que linea  estamos
		String linea = ""; //contenido de la linea
		
		try {
			BufferedReader br3 = new BufferedReader(new FileReader(fichero1));
			
			try {
				//cuando haya contenido en el fichero se pondra en el String
				while((linea = br3.readLine()) != null){
					cuentaLinea++;
					//cuando coincida una palabra parara el contador de lineas
					if(linea.equals(palabra) == true){
						numeroLinea = cuentaLinea;
						//pararemos el bucle en la primera linea que se encuentre la palabra buscada
						if(primeraPalabra){
							break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(palabra +" se encuentra en la linea "+numeroLinea);
		return numeroLinea;
	}
	
	
	
}
