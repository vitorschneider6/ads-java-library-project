package tools;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JTextField;

import entities.Pessoa;
import entities.Emprestimo;
import entities.Livro;

public class Utilitario {
	
	public boolean validarCadastroUsuario(String nomeCadastroUsuario, String sobrenomeCadastroUsuario, String telefoneUsuario, int dia, int mes,
			int ano) {
		if(nomeCadastroUsuario.length() >= 3 && sobrenomeCadastroUsuario.length() >= 3 && telefoneUsuario.length() == 14 && dia >= 1 && dia <= 31
				&& mes >= 1 && mes <= 12 && ano <= 2012) {
			return true;
	}
		return false;
	}
	
	public boolean validarCadastroLivro(String nomeCadastroLivro, String autorCadastroLivro, String dataLivroCadastro) {
		int ano = Integer.parseInt(dataLivroCadastro);
		if(autorCadastroLivro.length() >= 3 && nomeCadastroLivro.length() >= 3 && ano <= 2022) {
				return true;
	}
		return false;	
}
	

	public boolean isNumeric(String data) {
		try {
            Integer.parseInt(data);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
	}
	
	public boolean validaString (String str) {
		
		for (int i = 0; i <str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	public String infoData(){
		LocalDateTime data = LocalDateTime.now();
	    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    String dataFormatada = data.format(formatador);
	    return dataFormatada;
	}
	
	public boolean validarEmprestimo(ArrayList<Livro> livros, ArrayList<Pessoa> pessoas, String numRegistroEmprestimo, String idUsuarioEmprestimo) {
		try {
			int testeEmprestimoR = Integer.parseInt(numRegistroEmprestimo);
			int testeEmprestimoID = Integer.parseInt(idUsuarioEmprestimo);
		}catch(NumberFormatException ex2) {
			return false;
		}
		
		int testeEmprestimoR = Integer.parseInt(numRegistroEmprestimo);
		int testeEmprestimoID = Integer.parseInt(idUsuarioEmprestimo);
		
		try {
			livros.get(testeEmprestimoR - 1);
			pessoas.get(testeEmprestimoID - 1);
			return true;
			} catch (IndexOutOfBoundsException ex) {
				return false;
			}
	}
	
	public boolean validarDevolucao(ArrayList<Livro> livros, ArrayList<Pessoa> pessoas, String numRegistroDevolucao, String idUsuarioDevolucao) {
		try {
			int testeDevolucaoR = Integer.parseInt(numRegistroDevolucao);
			int testeDevolucaoID = Integer.parseInt(idUsuarioDevolucao);
		}catch(NumberFormatException ex2) {
			return false;
		}
		int testeDevolucaoR = Integer.parseInt(numRegistroDevolucao);
		int testeDevolucaoID = Integer.parseInt(idUsuarioDevolucao);
		
		try {
			livros.get(testeDevolucaoR - 1);
			pessoas.get(testeDevolucaoID - 1);
			return true;
			} catch (IndexOutOfBoundsException ex) {
				return false;
			}
		
	}
	
	public boolean verificaAtraso(int ano, int mes, int dia) {
		String[] vect = infoData().split("/");
		int diaAtual = Integer.parseInt(vect[0]);
		int mesAtual = Integer.parseInt(vect[1]);
		int anoAtual = Integer.parseInt(vect[2]);
		LocalDate dataRetirada = LocalDate.of(ano, mes, dia);
		LocalDate dataDevolucao = LocalDate.of(anoAtual, mesAtual ,diaAtual);
		
		long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
		
		if (dias > 7) {
			return true;
		}
		
		return false;
	}
	
	public String formataInput(String inpt) {
		return inpt = inpt.replaceAll("[^0-9]+" , "");
		
	}
	
	public boolean verificaExistencia(ArrayList<Pessoa> pessoas, int idUsuario) {
		for(Pessoa people : pessoas) {
			if(people.getId() == idUsuario) {
				return true;
			}
		}
		return false;
	};
}
