import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {
	
	private Document dom = null;
	private ArrayList<Libro> libros = null;
	
	public Marshaller(ArrayList<Libro> l){
		libros = l;
	}
	
	public void crearDocumento(){
		//creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder(); //creamos un documentbuilder
			dom = db.newDocument();	//creamos una instancia de DOM 	
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}
	
	//crea el arbol del XML
	public void crearArbolDOM() {
		//creamos el elemento raíz "libros"
		Element docEle = dom.createElement("libros");
		dom.appendChild(docEle);

		//recorremos
		Iterator it = libros.iterator();
		while (it.hasNext()) {
			//se recupera el objeto del array list y se tranforma a Libro
			Libro e = (Libro) it.next();
			// para cada objeto persona creamos el elemento <persona> y lo añadimos a la raíz
			Element libroEle = setLibro(e);
			docEle.appendChild(libroEle);
		}
	}	
	
	private Element setLibro(Libro l){
		//creamos el elemento persona
		Element LibroEle = dom.createElement("libro");
		
		//creamos el elemento nombre y el nodo de texto y lo añadimos al elemento libro
		Element nombreEle = dom.createElement("nombre");
		Text nombreTexto = dom.createTextNode(l.getTitulo());
		nombreEle.appendChild(nombreTexto); //añade texto en el elemento nombreEle
		LibroEle.appendChild(nombreEle);
		
		//creamos el elemento autor y el nodo de texto y lo añadimos al elemento libro
		Element autorEle = dom.createElement("autor");
		Text autorTexto = dom.createTextNode(l.getAutor());
		
		return LibroEle;
		
	}
	
	public void escribirDocumentAXml(File file) throws TransformerException{
		//creamos una instacia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes"); //permite que se contemplen los saltos de linea
		
		//especificamos dónde escribimos y la fuente de datos
		StreamResult result = new StreamResult(file); //fichero donde se quiere escribir
		DOMSource source = new DOMSource(dom); //fuente de datos
		trans.transform(source, result); //destino,tipo de fuente
	}
	
	
}
