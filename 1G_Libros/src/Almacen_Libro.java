import java.lang.reflect.Array;
import java.util.ArrayList;

public class Almacen_Libro {

	public static void main(String[] args) {
		//Insertamos varios libros
		Libro l1 = new Libro("Harry Potter y la piedra filosofal","J. K. Rowling",2001,"Salamandra",254);
		Libro l2 = new Libro("Las luces de septiembre","Carlos Ruiz Zafón",2008,"Booket", 320);
		Libro l3 = new Libro("Romeo y Julieta","Willian Shakespeare",2004,"Booket", 208);
		
		//Creamos un nuevo objeto de tipo Almacen
		Almacen almacen = new Almacen();
		
		//Guardar libro individualmente
		almacen.guardar(l1, "libro1.txt");
		almacen.guardar(l2, "libro2.txt");
		almacen.guardar(l3, "libro3.txt");
		
		//Guarda los libros en un ArrayList 
		Libro[] lista =new Libro[]{l1,l2,l3};
		//Guarda la lista en un documento de texto
		almacen.guardarLista(lista, "listaLibro.txt");
		
		//Se puede recuperar los libros guardados
		lista = almacen.recuperarLista("listaLibro.txt");
		System.out.println(lista);
		
		//Modificar titulo
		l1.setTitulo("Acceso a datos");
		System.out.println(l1.toString());
		
		//Modificar autor
		l1.setAutor("andrea");
		System.out.println(l1.toString());
		
		//funció modificar:
		//1- demanar a l'usuari quin llibre vol modificar
		//2- demanar quin titol nou vol posar
		//3- recuperar llibre del titol que li passa (mètode recuperar)
		//4- set
		//5- guardar llibre (mètode guardar)
		
	}
}
