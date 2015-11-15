package gestionficherosapp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];
		carpetaDeTrabajo = new File("C:\\");
		actualiza();
	}

	private void actualiza() {

		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el número de filas necesario
		filas = ficheros.length | columnas;
		if (filas * columnas < ficheros.length) {
			filas++; // si hay resto necesitamos una fila más
		}

		// dimensionar la matriz contenido según los resultados
		contenido = new String[filas][columnas];
		// Rellenar contenido con los nombres obtenidos
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	public void arriba() {

		System.out.println("holaaa");
		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}
	}

	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		
		//que se pueda escribir -> lanzará una excepción
		if(!carpetaDeTrabajo.canWrite()){
			throw new GestionFicherosException("Error de escritura");
		}
		//que no exista -> lanzará una excepción
		if(file.exists()){
			throw new GestionFicherosException("Error, ya existe el directorio");
		}
			file.mkdir();		
			actualiza();
	}

	public void creaFichero(String arg0) throws GestionFicherosException {
		File file=new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzará una excepción
		if(!carpetaDeTrabajo.canWrite()) throw new GestionFicherosException("No se puede modificar el archivo");
		//que no exista -> lanzará una excepción
		if(file.exists()) throw new GestionFicherosException("Ya existe un archivo con este nombre");
		//crear fichero -> sino lanzará una excepción
		try{
			file.createNewFile();
		}catch(IOException o){
			o.printStackTrace();
		}
		actualiza();
	}

	public void elimina(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que no exista -> lanzará una excepción
		if(!file.exists())
				throw new GestionFicherosException("No existe el archivo");
		//Si no puede escribir no se borrará
		if(!carpetaDeTrabajo.canWrite())
				throw new GestionFicherosException("No hay permisos de escritura");
		//Si borras un directorio y dentro tiene archivos salta un mensaje
		if(file.isDirectory()){
			if(file.list().length > 0){
				throw new GestionFicherosException("Borra los archivos de dentro");
			}
		}
		//eliminar el fichero -> lanzará una excepción
		if(!file.delete())
			throw new GestionFicherosException("No se puede borrar este archivo");
		actualiza();
	}

	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		// nueva asignación de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();
	}

	public int getColumnas() {return columnas;}
	public Object[][] getContenido() {return contenido;}

	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	public String getEspacioDisponibleCarpetaTrabajo() {return null;}
	public String getEspacioTotalCarpetaTrabajo() {return null;}
	public int getFilas() {return filas;}

	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	public String getInformacion(String arg0) throws GestionFicherosException {
		
		StringBuilder strBuilder=new StringBuilder();
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Controlar que existe. Si no, se lanzará una excepción
		//Controlar que haya permisos de lectura. Si no, se lanzará una excepción
		
		//Título
		strBuilder.append("INFORMACIÓN DEL SISTEMA");
		strBuilder.append("\n\n");
		
		//Nombre
		strBuilder.append("Nombre: ");
		strBuilder.append(arg0);
		strBuilder.append("\n");
		
		//Tipo: fichero o directorio
		strBuilder.append("Tipo: ");

		if(file.isFile()){
			strBuilder.append("fichero");
			strBuilder.append("Tamaño: ");
			strBuilder.append(file.length()+" bytes");
			strBuilder.append("\n");
		}
		
		if(file.isDirectory()){
			strBuilder.append("directorio");
			strBuilder.append("\n");		
		
			strBuilder.append("Espacio libre: ");
			strBuilder.append(file.getFreeSpace()+" bytes");
			strBuilder.append("\n");
		
			strBuilder.append("Espacio disponible : ");
			strBuilder.append(file.getUsableSpace()+" bytes");
			strBuilder.append("\n");
		
			strBuilder.append("Espacio total : ");
			strBuilder.append(file.getTotalSpace()+" bytes");
			strBuilder.append("\n");
		}
		
		//Ubicación
		strBuilder.append("Ubicación : ");
		strBuilder.append(file.getAbsolutePath());
		strBuilder.append("\n");
		
		//Fecha de última modificación
		strBuilder.append("Ultima modificaión: ");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		strBuilder.append(formato.format(file.lastModified()));
		strBuilder.append("\n");
		
		//Si es un fichero oculto o no
		if(file.isHidden()){
			strBuilder.append("Es un fichero oculto");
		}else{
			strBuilder.append("No es un fichero oculto");
		}
		
		return strBuilder.toString();
	}

	public boolean getMostrarOcultos() {return false;}

	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	public TipoOrden getOrdenado() {
		return ordenado;
	}

	public String[] getTituloColumnas() {return null;}
	public long getUltimaModificacion(String arg0)throws GestionFicherosException {return 0;}
	public String nomRaiz(int arg0) {return null;}
	public int numRaices() {return 0;}

	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		File file2 = new File(carpetaDeTrabajo,arg1);//El método renameTo no admite Strings, por lo tanto creamos un objeto file
		//que se pueda escribir -> lanzará una excepción
		if(!carpetaDeTrabajo.canWrite())
			throw new GestionFicherosException("No se puede modificar el archivo");

		//que no exista -> lanzará una excepción
		if(!file.exists())
			throw new GestionFicherosException("No existe el fichero origen");
		if(file2.exists())
			throw new GestionFicherosException("Ya existe un archivo con el mismo nombre");
		
		//renombrar la carpeta -> lanzará una excepción
		file.renameTo(file2);
		actualiza();
	}

	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {return false;}
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {return false;}
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {return false;}

	public void setColumnas(int arg0) {
		columnas = arg0;
	}

	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

		// se controla que la dirección exista y sea directorio
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error.");
		}

		// se controla que haya permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("No se puede acceder a  " + file.getAbsolutePath());
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// actualizar el contenido
		actualiza();

	}

	public void setFormatoContenido(FormatoVistas arg0) {}
	public void setMostrarOcultos(boolean arg0) {}
	public void setOrdenado(TipoOrden arg0) {}
	public void setSePuedeEjecutar(String arg0, boolean arg1)throws GestionFicherosException {}
	public void setSePuedeEscribir(String arg0, boolean arg1)throws GestionFicherosException {}
	public void setSePuedeLeer(String arg0, boolean arg1)throws GestionFicherosException {}
	public void setUltimaModificacion(String arg0, long arg1)throws GestionFicherosException {}
}
