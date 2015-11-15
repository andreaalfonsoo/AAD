import java.io.Serializable;

public class Libro implements Serializable{
		
		String titulo = null;
		String autor = null;
		int a�o = 0;
		String editor = null;
		int paginas = 0;
		
	
		public Libro(String tit, String aut, int anyo, String edit, int pag) {
			this.titulo = tit;
			this.autor = aut;
			this.a�o = anyo;
			this.editor = edit;
			this.paginas = pag;
		}
		
		//Getters
		public String getTitulo() {
			return titulo;
		}

		public String getAutor() {
			return autor;
		}

		public int getA�o() {
			return a�o;
		}

		public String getEditor() {
			return editor;
		}

		public int getPaginas() {
			return paginas;
		}
		
		//Setters
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}		
		
		public void setAutor(String autor) {
			this.autor = autor;
		}
		
		public void setA�o(int a�o) {
			this.a�o = a�o;
		}
		
		public void setEditor(String editor) {
			this.editor = editor;
		}

		public void setPaginas(int paginas) {
			this.paginas = paginas;
		}
	
}
