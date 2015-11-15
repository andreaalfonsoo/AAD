import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class stream_bytes {
	
	public static void main(String[] args) {
		try {
			abrirFichero();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/*Definir el perfil que se desee para esta función*/
	public static void abrirFichero() throws FileNotFoundException, IOException, Exception, Throwable {
		
		//Pide los datos del fichero
		String fichero = JOptionPane.showInputDialog("Fichero que deseas buscar");
		
		String fichero2 = JOptionPane.showInputDialog("Nombre del nuevo fichero");
		
		//Crea los objetos file
		File entrada = new File("C:\\Users\\Andrea\\Desktop\\WorkspaceMARS\\Stream_bytes\\src\\"+fichero);
		File salida = new File(fichero2);
		
		//comprobar que no existe
		
		//Pasa los datos al metodo copiaBytes
		copiaBytes(entrada,salida);
		
	}
	
	/*Definir el perfil que se desee para esta función*/
	public static void copiaBytes(File primero, File segundo) throws FileNotFoundException, IOException, Exception, Throwable {
		
		//Crear flujo de entrada-salida
		InputStream in = new FileInputStream(primero);
		OutputStream out = new FileOutputStream(segundo);
		
		//Crea array
		byte[] numByte = new byte[1024];
		int i;
		//Guarda
		int contador = 0;
		while ((i = in.read(numByte)) > 0) {
			  out.write(numByte, 0, i);
			  contador=contador+i;
			  
		}
		System.out.println(contador);
		in.close();
		out.close();
		
	}
	
}