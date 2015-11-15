
public class Parser_Libro {

	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.parseFicherosXML("bibliotecaJM.xml"); //indicamos que xml
		parser.parseDocument(); //recorrera el elemento libro y lo añadira a un array
		parser.print(); //imprime por pantalla los datos del XML
	}

}
