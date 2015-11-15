import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

public class Marshaller_Libro {

	public static void main(String[] args) {
		ArrayList<Libro> libros;
		
		//cargamos datos
		libros = new ArrayList<Libro>();
		libros.add(new Libro("Harry Potter y la piedra filosofal","J. K. Rowling",2001,"Salamandra",254));
		libros.add(new Libro("Las luces de septiembre","Carlos Ruiz Zafón",2008,"Booket", 320));
		
		//se crea un objeto de tipo marshaller
		Marshaller marshaller = new Marshaller(libros);
		
		marshaller.crearDocumento(); //creara un documento y una instancia DOM
		marshaller.crearArbolDOM(); //creara el arbol del XML
		
		//se creara un xml con lo datos pasados
		File file = new File("libro.xml");
		
		try {
			marshaller.escribirDocumentAXml(file);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
	}

}
