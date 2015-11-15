import java.io.Serializable;

public class Libro implements Serializable{
		
		String titulo = null;
		String autor = null;
		int año = 0;
		String editor = null;
		int paginas = 0;
		
	
		public Libro(String tit, String aut, int anyo, String edit, int pag) {
			this.titulo = tit;
			this.autor = aut;
			this.año = anyo;
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

		public int getAño() {
			return año;
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
		
		public void setAño(int año) {
			this.año = año;
		}
		
		public void setEditor(String editor) {
			this.editor = editor;
		}

		public void setPaginas(int paginas) {
			this.paginas = paginas;
		}
	
}
