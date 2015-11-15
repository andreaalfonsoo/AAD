import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	
	private Document dom = null;
	private ArrayList<Libro> libros = null;
	
	public Parser(){
		libros = new ArrayList<Libro>();
	}
	
	public void parseFicherosXML(String fichero){
		//creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder(); //creamos un documentbuilder
			dom = db.parse(fichero); //parseamos el XML y obtenemos una representación DOM
		//se tratan las excepciones	
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		//comprobamos que funciona 
		System.out.println("parseFicherosXML funciona");
	}
	
	//recorrera el elemento "libro"
	public void parseDocument() {
		//obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName("libro"); //nombre de la etiqueta
		//comprobacion e iteracion
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				//obtenemos un elemento de la lista (libros)
				Element el = (Element) nl.item(i);

				//obtenemos un libro
				Libro l = getLibro(el);

				//lo añadimos al array
				libros.add(l);
			}
		}
		
	}

	//obtendremos los datos a traves de los metodos creados como son atributo(),getTextValue(),getSubTextValue(),getIntValue
	private Libro getLibro(Element el_gl) {
		
		String titulo = getTextValue(el_gl, "titulo");
		String autor = getSubTextValue(el_gl, "autor");
		int año = Integer.parseInt(atributo(el_gl, "titulo"));
		String editor = getTextValue(el_gl,"editor");
		int numpaginas = getIntValue(el_gl,"paginas");
		
		//creamos un nuevo libro con los elementos leidos del nodo
		Libro l = new Libro(titulo, autor, año, editor, numpaginas);
		return l;
	}
	
	//metodo para buscar un atributo de una etiqueta
	private String atributo(Element ela, String tagName){
		NodeList nl = ela.getElementsByTagName(tagName); //nombre de la etiqueta
		NamedNodeMap nm = nl.item(0).getAttributes(); //obtiene el atributo con un namednodemap
		String año = nm.item(0).getTextContent(); //pasamos el atributo a un string
		
		return año;//devuelve el atributo
	}
	
	//metodo para buscar una etiqueta
	private String getTextValue(Element el_gtv, String tagName) {
		String textVal = null; //guardará texto
		NodeList nl = el_gtv.getElementsByTagName(tagName); //nombre de la etiqueta
		//comprobacion e iteracion, recupera los datos
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}		
		return textVal;
	}
	
	//metodo para buscar una subetiqueta
	private String getSubTextValue(Element el_gtv2, String tag){
		String textVal = null; //guardará texto
		NodeList nl = el_gtv2.getElementsByTagName(tag); //nombre de la etiqueta
		//comprobacion e iteracion, recupera los datos
		if(nl != null && nl.getLength() > 0){
			Element el = (Element) nl.item(0);
			textVal = ((Node)el.getChildNodes()).getTextContent();
		}
		return textVal;
	}
	
	//metodo para buscar una etiqueta de tipo int
	private int getIntValue(Element el_giv, String tagName){
		return Integer.parseInt(getTextValue(el_giv,tagName));
	}
	
	//imprime por pantalla el libro o la lista de libros que hayan en un XML
	public void print(){
		Iterator it = libros.iterator();
		while(it.hasNext()) {
			Libro l=(Libro) it.next();
			l.print();
		}
	}
}
