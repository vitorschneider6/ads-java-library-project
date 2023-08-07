package entities;

public class Emprestimo {
	private Livro nomelivro;
	private Pessoa nomePessoa;
	private String dataEmprestimo;
	private String dataDevolucao;
	private boolean status = false;
	private boolean atrasado = false;
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
		
	

	public Emprestimo(Livro nomelivro, Pessoa nomePessoa, String dataEmprestimo, boolean status, boolean atrasado) {
		super();
		this.nomelivro = nomelivro;
		this.nomePessoa = nomePessoa;
		this.dataEmprestimo = dataEmprestimo;
		this.status = status;
		this.atrasado = atrasado;
	}

	public Emprestimo(Livro nomelivro, Pessoa nomePessoa, String dataEmprestimo, String dataDevolucao, boolean status,
			boolean atrasado) {
		super();
		this.nomelivro = nomelivro;
		this.nomePessoa = nomePessoa;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.status = status;
		this.atrasado = atrasado;
	}

	public Emprestimo(Livro nomelivro, Pessoa nomePessoa, String dataEmprestimo) {
		super();
		this.nomelivro = nomelivro;
		this.nomePessoa = nomePessoa;
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(String dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Livro getNomelivro() {
		return nomelivro;
	}

	public void setNomelivro(Livro nomelivro) {
		this.nomelivro = nomelivro;
	}

	public Pessoa getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(Pessoa nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public boolean isAtrasado() {
		return this.atrasado;
	}

	public void setAtrasado(boolean atrasado) {
		this.atrasado = atrasado;
	}


}
