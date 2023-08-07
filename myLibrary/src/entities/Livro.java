package entities;

public class Livro {
	private String nome;
	private String autor;
	private int numRegistro;
	private String data;
	private boolean disponibilidade = true;
	private String local;

	
	

	public Livro(String nome, String autor, int numRegistro, String data, boolean disponibilidade, String local) {
		super();
		this.nome = nome;
		this.autor = autor;
		this.numRegistro = numRegistro;
		this.data = data;
		this.disponibilidade = disponibilidade;
		this.local = local;
	}



	public Livro(String nome, String autor, int numRegistro, String data, String local) {
		this.nome = nome;
		this.autor = autor;
		this.numRegistro = numRegistro;
		this.data = data;
		this.local = local;
	}


	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public int getNumRegistro() {
		return numRegistro;
	}


	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public boolean isDisponibilidade() {
		return disponibilidade;
	}


	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	public boolean checarDisponibilidade() {
		if(this.disponibilidade == true) {
			return true;
		}
		else {
			return false;
		}
	}

}
