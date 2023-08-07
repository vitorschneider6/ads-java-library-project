package myLibrary;

import entities.Livro;
import tools.Utilitario;
import entities.Pessoa;
import entities.Emprestimo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class projectScreen{
	
	
	
	
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	private ArrayList<Livro> livros = new ArrayList<Livro>();
	private ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	
	private Utilitario util = new Utilitario();
	
	private JFrame frame;
	// Livro
	private JTextField inCadastroNomeLivro;
	private JTextField inCadastroNomeAutor;
	private JTextField inCadastroData;
	// Cadastro Usuario
	private JTextField inCadastroNomeUsuario;
	private JTextField inCadastroSobrenomeUsuario;
	private JTextField inCadastroTelefoneUsuario;
	private JTextField inCadastroBdayUsuario;
	// Devolucao
	private JTextField inIdUsuarioDevolucao;
	private JTextField inNumRegistroDevolucao;
	// Registros
	private JTextField inNumeroUsuarioRegistro;
	// Emprestimos
	private JTextField inIdUsuarioRetirada;
	private JTextField inNumRegistroEmprestimo;
	private JLabel lblMensagemErroU;
	private JLabel lblSucessoCadastroU;
	private JLabel lblSucessoCadastroL;
	private JLabel lblErroCadastroL;
	private JComboBox comboBox;
	private JTable table;
	private JLabel lblSucessoEmprestimo;
	private JLabel lblFalhaEmprestimo;
	private JLabel lblSucessoDevolucao;
	private JLabel lblErroDevolucao;
	private JTable table_User;
	private JTable tableLivros;
	private JPanel telaBuscaLivros;
	private JLabel lblerroBuscaId;
	
	File arquivoUsuarios = new File("usuarios.txt");
	File arquivoLivros = new File("livros.txt");
	File arquivoEmprestimos = new File("emprestimos.txt");
	



	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					projectScreen window = new projectScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public projectScreen() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if(!arquivoUsuarios.exists()) {
					try {
						arquivoUsuarios.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				if(!arquivoLivros.exists()) {
					try {
						arquivoLivros.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				if(!arquivoEmprestimos.exists()) {
					try {
						arquivoEmprestimos.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
							
			}
				
				try {
					FileReader leitor = new FileReader(arquivoUsuarios);
					BufferedReader buffer = new BufferedReader(leitor);
					String linha = null;
					while ((linha = buffer.readLine()) != null) {
						String[] vect = linha.split(";");
						String nome = vect[0];
						String sobrenome = vect[1];
						String telefone = vect[2];
						String dataNascimento = vect[3];
						int id = Integer.parseInt(vect[4]);
						
						pessoas.add(new Pessoa(nome, sobrenome, telefone, dataNascimento, id));
					}
					buffer.close();
					leitor.close();
					} catch (IOException exc) {
					}
				
				arquivoUsuarios.delete();
				
				try {
					FileReader leitor = new FileReader(arquivoLivros);
					BufferedReader buffer = new BufferedReader(leitor);
					String linha = null;
					while ((linha = buffer.readLine()) != null) {
						String[] vect = linha.split(";");
						String nome = vect[0];
						String autor = vect[1];
						int numRegistro = Integer.parseInt(vect[2]);
						String data = vect[3];
						boolean disponibilidade = Boolean.parseBoolean(vect[4]);
						String local = vect[5];
						
						livros.add(new Livro(nome, autor, numRegistro, data, disponibilidade, local));
					}
					buffer.close();
					leitor.close();
					} catch (IOException exc) {
					}
				
				arquivoLivros.delete();
				
				try {
					FileReader leitor = new FileReader(arquivoEmprestimos);
					BufferedReader buffer = new BufferedReader(leitor);
					String linha = null;
					while ((linha = buffer.readLine()) != null) {
						String[] vect = linha.split(";");
						int idLivro = Integer.parseInt(vect[0]);
						int idUsuario = Integer.parseInt(vect[1]);
						String dataEmprestimo = vect[2];
						String dataDevolucao = vect[3];
						boolean status = Boolean.parseBoolean(vect[4]);
						boolean atraso = Boolean.parseBoolean(vect[5]);
						
						emprestimos.add(new Emprestimo(livros.get(idLivro - 1), pessoas.get(idUsuario - 1), dataEmprestimo, dataDevolucao, status, atraso));
						
			
					}
					buffer.close();
					leitor.close();
					} catch (IOException exc) {
					}
				
				arquivoEmprestimos.delete();
				
		}			@Override
			public void windowClosing(WindowEvent e) {
			
				try {
					FileWriter gravarLivro = new FileWriter(arquivoLivros);
					for(Livro books : livros) {
					gravarLivro.write(books.getNome() + ";" + books.getAutor() + ";" + books.getNumRegistro() + ";" + books.getData() + 
							";" + books.checarDisponibilidade() + ";" + books.getLocal() + "\n");
					
					}
					gravarLivro.flush();
					gravarLivro.close();
					} catch(IOException e1) {
				}
				
				try {
					FileWriter gravarUsuarios = new FileWriter(arquivoUsuarios);
					for(Pessoa person : pessoas) {
					gravarUsuarios.write(person.getNome() + ";" + person.getSobrenome() + ";" + person.getTelefone() + ";" + 
					person.getDataNascimento() + ";" + person.getId() + "\n");
					
					}
					gravarUsuarios.flush();
					gravarUsuarios.close();
					} catch(IOException e1) {
				}
				
				try {
					FileWriter gravarEmprestimos = new FileWriter(arquivoEmprestimos);
					for(Emprestimo emprestimo : emprestimos) {
						if(emprestimo.getDataDevolucao() == null) {
							gravarEmprestimos.write(emprestimo.getNomelivro().getNumRegistro() + ";" + emprestimo.getNomePessoa().getId() + 
									";" + emprestimo.getDataEmprestimo() + ";" + "" + ";" + emprestimo.isStatus() +
									";" + emprestimo.isAtrasado() + "\n");
						}else {
							gravarEmprestimos.write(emprestimo.getNomelivro().getNumRegistro() + ";" + emprestimo.getNomePessoa().getId() + 
									";" + emprestimo.getDataEmprestimo() + ";" + emprestimo.getDataDevolucao() + ";" + emprestimo.isStatus() +
									";" + emprestimo.isAtrasado() + "\n");
						}
					}
					gravarEmprestimos.flush();
					gravarEmprestimos.close();
					} catch(IOException e1) {
				}
				}
				
});
		
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel telaCadastroLivros = new JPanel();
		telaCadastroLivros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroL.setText("");
				lblErroCadastroL.setText("");

			}
		});
		frame.getContentPane().add(telaCadastroLivros, "name_173722905447600");
		telaCadastroLivros.setLayout(null);
		
		JLabel lblCadastroLivros = new JLabel("CADASTRO DE LIVROS");
		lblCadastroLivros.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastroLivros.setBounds(182, 24, 209, 25);
		telaCadastroLivros.add(lblCadastroLivros);
		
		JLabel lblCadastroNomeLivro = new JLabel("Nome do Livro:");
		lblCadastroNomeLivro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroNomeLivro.setBounds(45, 80, 113, 14);
		telaCadastroLivros.add(lblCadastroNomeLivro);
		
		JLabel lblCadastroNomeAutor = new JLabel("Nome do Autor:");
		lblCadastroNomeAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroNomeAutor.setBounds(45, 140, 122, 14);
		telaCadastroLivros.add(lblCadastroNomeAutor);
		
		JLabel lblCadastroData = new JLabel("Ano:");
		lblCadastroData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroData.setBounds(45, 260, 46, 14);
		telaCadastroLivros.add(lblCadastroData);
		
		inCadastroNomeLivro = new JTextField();
		inCadastroNomeLivro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroL.setText("");
				lblErroCadastroL.setText("");
			}
		});
		inCadastroNomeLivro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inCadastroNomeLivro.setBounds(160, 79, 290, 20);
		telaCadastroLivros.add(inCadastroNomeLivro);
		inCadastroNomeLivro.setColumns(10);
		
		inCadastroNomeAutor = new JTextField();
		inCadastroNomeAutor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroL.setText("");
				lblErroCadastroL.setText("");
			}
		});;
		inCadastroNomeAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inCadastroNomeAutor.setBounds(170, 139, 280, 20);
		telaCadastroLivros.add(inCadastroNomeAutor);
		inCadastroNomeAutor.setColumns(10);
		
		inCadastroData = new JTextField();
		inCadastroData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (inCadastroData.getText().length() >= 4) {
					e.consume();
				}
			}
		});
		inCadastroData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroL.setText("");
				lblErroCadastroL.setText("");
			}
		});
		inCadastroData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inCadastroData.setBounds(91, 259, 95, 20);
		telaCadastroLivros.add(inCadastroData);
		inCadastroData.setColumns(10);
		
		JButton btnCadastrarLivro = new JButton("CADASTRAR");
		btnCadastrarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeLivroCadastro = inCadastroNomeLivro.getText();
				String autorLivroCadastro = inCadastroNomeAutor.getText();
				String dataLivroCadastro = inCadastroData.getText();
				String localLivro = (String) comboBox.getSelectedItem();
							
				if((util.validaString(autorLivroCadastro)) &&(util.isNumeric(dataLivroCadastro))) {
					if(util.validarCadastroLivro(autorLivroCadastro, nomeLivroCadastro, dataLivroCadastro)) {
						livros.add(new Livro(nomeLivroCadastro, autorLivroCadastro, livros.size() + 1, dataLivroCadastro, localLivro));
						int numRegistroL = livros.size() + 1;
						
						
						
						lblSucessoCadastroL.setText("Livro cadastrado com sucesso!");
						inCadastroNomeLivro.setText("");
						inCadastroNomeAutor.setText("");
						inCadastroData.setText("");
					} else {
						lblErroCadastroL.setText("Campos preenchidos incorretamente!");
					}
				}
				
				else {
					lblErroCadastroL.setText("Campos preenchidos incorretamente!");
					
				}
				
			}
		});
		btnCadastrarLivro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrarLivro.setBounds(226, 306, 131, 23);
		telaCadastroLivros.add(btnCadastrarLivro);
		
		lblSucessoCadastroL = new JLabel("");
		lblSucessoCadastroL.setForeground(new Color(0, 128, 0));
		lblSucessoCadastroL.setBounds(341, 169, 235, 32);
		telaCadastroLivros.add(lblSucessoCadastroL);
		
		lblErroCadastroL = new JLabel("");
		lblErroCadastroL.setForeground(Color.RED);
		lblErroCadastroL.setBounds(341, 241, 235, 32);
		telaCadastroLivros.add(lblErroCadastroL);
		
		JLabel lblNewLabel = new JLabel("Local do livro:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(45, 198, 113, 13);
		telaCadastroLivros.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A1", "A2", "B1", "B2", "C1", "C2"}));
		comboBox.setBounds(160, 196, 87, 21);
		telaCadastroLivros.add(comboBox);
		
		JPanel telaCadastroUsuario = new JPanel();
		telaCadastroUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroU.setText("");
				lblMensagemErroU.setText("");
			}
		});
		telaCadastroUsuario.setLayout(null);
		frame.getContentPane().add(telaCadastroUsuario, "name_173722917500200");
		
		JLabel lblCadastroUsuario = new JLabel("CADASTRO DE USU\u00C1RIO");
		lblCadastroUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastroUsuario.setBounds(180, 24, 223, 25);
		telaCadastroUsuario.add(lblCadastroUsuario);
		
		JLabel lblCadastroNomeUsuario = new JLabel("Nome:");
		lblCadastroNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroNomeUsuario.setBounds(45, 80, 113, 14);
		telaCadastroUsuario.add(lblCadastroNomeUsuario);
		
		JLabel lblCadastroSobrenomeUsuario = new JLabel("Sobrenome:");
		lblCadastroSobrenomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroSobrenomeUsuario.setBounds(45, 140, 122, 14);
		telaCadastroUsuario.add(lblCadastroSobrenomeUsuario);
		
		JLabel lblCadastroTelefoneUsuario = new JLabel("Telefone:");
		lblCadastroTelefoneUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroTelefoneUsuario.setBounds(45, 200, 90, 25);
		telaCadastroUsuario.add(lblCadastroTelefoneUsuario);
		
		JLabel lblCadastroDataNasciUsuario = new JLabel("Data de Nascimento:");
		lblCadastroDataNasciUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroDataNasciUsuario.setBounds(45, 260, 150, 14);
		telaCadastroUsuario.add(lblCadastroDataNasciUsuario);
		
		inCadastroNomeUsuario = new JTextField();
		inCadastroNomeUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroU.setText("");
				lblMensagemErroU.setText("");
			}
		});

		inCadastroNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inCadastroNomeUsuario.setColumns(10);
		inCadastroNomeUsuario.setBounds(99, 79, 191, 20);
		telaCadastroUsuario.add(inCadastroNomeUsuario);
		
		
		inCadastroSobrenomeUsuario = new JTextField();
		inCadastroSobrenomeUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroU.setText("");
				lblMensagemErroU.setText("");
			}
		});
		inCadastroSobrenomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inCadastroSobrenomeUsuario.setColumns(10);
		inCadastroSobrenomeUsuario.setBounds(140, 139, 201, 20);
		telaCadastroUsuario.add(inCadastroSobrenomeUsuario);
		
		inCadastroTelefoneUsuario = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		/*inCadastroTelefoneUsuario.setText("");*/
		inCadastroTelefoneUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (inCadastroTelefoneUsuario.getText().length() >= 15) {
					e.consume();
				}
			}
		});
		inCadastroTelefoneUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroU.setText("");
				lblMensagemErroU.setText("");
			}
		});
		inCadastroTelefoneUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inCadastroTelefoneUsuario.setColumns(10);
		inCadastroTelefoneUsuario.setBounds(120, 204, 185, 20);
		telaCadastroUsuario.add(inCadastroTelefoneUsuario);
		
		inCadastroBdayUsuario = new JFormattedTextField(new MaskFormatter("##/##/####"));
		inCadastroBdayUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoCadastroU.setText("");
				lblMensagemErroU.setText("");
			}
		});
		inCadastroBdayUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inCadastroBdayUsuario.setColumns(10);
		inCadastroBdayUsuario.setBounds(205, 259, 122, 20);
		telaCadastroUsuario.add(inCadastroBdayUsuario);
		
		JButton btnCadastrarUsuario = new JButton("CADASTRAR");
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String nomeCadastroUsuario = inCadastroNomeUsuario.getText();
					String sobrenomeCadastroUsuario = inCadastroSobrenomeUsuario.getText();
					String telefoneUsuario = (inCadastroTelefoneUsuario.getText());
					String dataNascimentoUsuario = inCadastroBdayUsuario.getText();
					String[] vectData = dataNascimentoUsuario.split("/");
					int dia =  Integer.parseInt(vectData[0]);
					int mes =  Integer.parseInt(vectData[1]);
					int ano =  Integer.parseInt(vectData[2]);
					util.formataInput(telefoneUsuario);
		
				
					if((util.validaString(nomeCadastroUsuario)) && (util.validaString(sobrenomeCadastroUsuario))) {
						
						if(util.validarCadastroUsuario(nomeCadastroUsuario, sobrenomeCadastroUsuario, telefoneUsuario, dia, mes, ano)) {
							pessoas.add(new Pessoa(nomeCadastroUsuario, sobrenomeCadastroUsuario, telefoneUsuario, dataNascimentoUsuario, 
									pessoas.size() + 1));
							inCadastroNomeUsuario.setText("");
							inCadastroSobrenomeUsuario.setText("");
							inCadastroTelefoneUsuario.setText("");
							inCadastroBdayUsuario.setText("");
							
							lblSucessoCadastroU.setText("Cadastro realizado com sucesso!");
		
						}
						
						else {
							lblMensagemErroU.setText("Campos preenchidos incorretamente!");	
						}
					}
					
					else {
						lblMensagemErroU.setText("Campos preenchidos incorretamente!");	
					}
				} catch (NumberFormatException ex) {
					lblMensagemErroU.setText("Preencha todos os campos!");	
				}
				
			}
		});
		
		btnCadastrarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrarUsuario.setBounds(226, 306, 131, 23);
		telaCadastroUsuario.add(btnCadastrarUsuario);
		
		lblMensagemErroU = new JLabel("");
		lblMensagemErroU.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensagemErroU.setForeground(Color.RED);
		lblMensagemErroU.setBounds(334, 80, 242, 48);
		telaCadastroUsuario.add(lblMensagemErroU);
		
		lblSucessoCadastroU = new JLabel("");
		lblSucessoCadastroU.setForeground(new Color(0, 128, 0));
		lblSucessoCadastroU.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSucessoCadastroU.setBounds(334, 172, 242, 55);
		telaCadastroUsuario.add(lblSucessoCadastroU);
		
		JPanel telaDevolucaoLivro = new JPanel();
		telaDevolucaoLivro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoDevolucao.setText("");
				lblErroDevolucao.setText("");
			}
		});
		telaDevolucaoLivro.setLayout(null);
		frame.getContentPane().add(telaDevolucaoLivro, "name_173722929876200");
		
		JLabel lblDevolucaoDeLivros = new JLabel("DEVOLU\u00C7\u00C3O DE LIVROS");
		lblDevolucaoDeLivros.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDevolucaoDeLivros.setBounds(182, 25, 220, 25);
		telaDevolucaoLivro.add(lblDevolucaoDeLivros);
		
		JLabel lblDevolucaoNomeUsuario = new JLabel("ID do Usu\u00E1rio:");
		lblDevolucaoNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDevolucaoNomeUsuario.setBounds(35, 98, 136, 14);
		telaDevolucaoLivro.add(lblDevolucaoNomeUsuario);
		
		JLabel lblDevolucaoNumRegistro = new JLabel("N\u00B0 Registro:");
		lblDevolucaoNumRegistro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDevolucaoNumRegistro.setBounds(30, 191, 90, 25);
		telaDevolucaoLivro.add(lblDevolucaoNumRegistro);
		
		inIdUsuarioDevolucao = new JTextField();
		inIdUsuarioDevolucao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblSucessoDevolucao.setText("");
				lblErroDevolucao.setText("");
			}
		});
		inIdUsuarioDevolucao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inIdUsuarioDevolucao.setColumns(10);
		inIdUsuarioDevolucao.setBounds(150, 95, 131, 20);
		telaDevolucaoLivro.add(inIdUsuarioDevolucao);
		
		inNumRegistroDevolucao = new JTextField();
		inNumRegistroDevolucao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblSucessoDevolucao.setText("");
				lblErroDevolucao.setText("");
			}
		});
		inNumRegistroDevolucao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inNumRegistroDevolucao.setColumns(10);
		inNumRegistroDevolucao.setBounds(131, 193, 121, 20);
		telaDevolucaoLivro.add(inNumRegistroDevolucao);
		
		JButton btnDevolverLivro = new JButton("DEVOLVER");
		btnDevolverLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numRegistroDevolucao = inNumRegistroDevolucao.getText();
				String idUsuarioDevolucao = inIdUsuarioDevolucao.getText();
				
				int dia;
				int mes;
				int ano;				//
				if(util.validarDevolucao(livros, pessoas, numRegistroDevolucao, idUsuarioDevolucao)) {
					int numRegistroDevolucaoL = Integer.parseInt(numRegistroDevolucao);
					int idUsuarioDevolucaoU = Integer.parseInt(idUsuarioDevolucao);
					String dataDevol = util.infoData();
					
					for(Emprestimo emp : emprestimos) {
						
						if((emp.getNomelivro().getNumRegistro() == numRegistroDevolucaoL && !emp.isStatus() 
								&& emp.getNomePessoa().getNome() == pessoas.get(idUsuarioDevolucaoU - 1).getNome())){
							emp.setDataDevolucao(dataDevol);
							emp.setStatus(true);
							livros.get(numRegistroDevolucaoL - 1).setDisponibilidade(true);
							lblSucessoDevolucao.setText("Devolução realizada com sucesso!");
							inNumRegistroDevolucao.setText("");
							inIdUsuarioDevolucao.setText("");
							String [] data = emp.getDataEmprestimo().split("/");
							dia = Integer.parseInt(data[0]);
							mes = Integer.parseInt(data[1]);
							ano = Integer.parseInt(data[2]);
							if (util.verificaAtraso(ano, mes, dia)) {
								lblErroDevolucao.setText("Livro devolvido com atraso!");
							}
		
							break;	
		}
					}
				}else{
					lblErroDevolucao.setText("Usuário ou livro não encontrado!");
				};
			}
		});
		btnDevolverLivro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDevolverLivro.setBounds(224, 295, 131, 23);
		telaDevolucaoLivro.add(btnDevolverLivro);
		
		lblSucessoDevolucao = new JLabel("");
		lblSucessoDevolucao.setForeground(new Color(0, 128, 0));
		lblSucessoDevolucao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSucessoDevolucao.setBounds(327, 136, 249, 25);
		telaDevolucaoLivro.add(lblSucessoDevolucao);
		
		lblErroDevolucao = new JLabel("");
		lblErroDevolucao.setForeground(Color.RED);
		lblErroDevolucao.setBounds(327, 199, 249, 25);
		telaDevolucaoLivro.add(lblErroDevolucao);
		
		JPanel telaRegistroLivro = new JPanel();
		telaRegistroLivro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblerroBuscaId.setText("");
			}
		});
		telaRegistroLivro.setLayout(null);
		frame.getContentPane().add(telaRegistroLivro, "name_173722941409200");
		
		JLabel lblRegistroDevolRetira = new JLabel("Registros Devolu\u00E7\u00F5es/Retiradas");
		lblRegistroDevolRetira.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRegistroDevolRetira.setBounds(150, 25, 287, 25);
		telaRegistroLivro.add(lblRegistroDevolRetira);
		
		JLabel lblRegistroNomeUsuario = new JLabel("N\u00FAmero ID:");
		lblRegistroNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegistroNomeUsuario.setBounds(106, 75, 110, 14);
		telaRegistroLivro.add(lblRegistroNomeUsuario);
		
		inNumeroUsuarioRegistro = new JTextField();
		inNumeroUsuarioRegistro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.setRowCount(0);
			
				for(Emprestimo em : emprestimos) {
					String dDate;
					int numRegistroLivro = em.getNomelivro().getNumRegistro();
					String book = em.getNomelivro().getNome();
					String user = em.getNomePessoa().getNome();
					String eDate = em.getDataEmprestimo();
					
					String status = "";
					String[] data = em.getDataEmprestimo().split("/");
					int dia = Integer.parseInt(data[0]);
					int mes = Integer.parseInt(data[1]);
					int ano = Integer.parseInt(data[2]);
					if(em.isStatus() && !util.verificaAtraso(ano, mes, dia)) {
						status = "Devolvido";
					} else if(!em.isStatus() && !util.verificaAtraso(ano, mes, dia)){
						status = "Não devolvido";
					} else if(util.verificaAtraso(ano, mes, dia)) {
						status = "Atrasado";
					}
					if(em.getDataDevolucao() == "null") {
						dDate = "";
					}else {
						dDate = em.getDataDevolucao();
					}
					Object[] dados = {numRegistroLivro, book, user, eDate, dDate, status};
					
					m.addRow(dados);
					
				}
				
				lblerroBuscaId.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.setRowCount(0);
				for(Emprestimo em : emprestimos) {
					int numRegistroLivro = em.getNomelivro().getNumRegistro();
					String book = em.getNomelivro().getNome();
					String user = em.getNomePessoa().getNome();
					String eDate = em.getDataEmprestimo();
					String dDate = em.getDataDevolucao();
					String status = "";
					String[] data = em.getDataEmprestimo().split("/");
					int dia = Integer.parseInt(data[0]);
					int mes = Integer.parseInt(data[1]);
					int ano = Integer.parseInt(data[2]);
					if(em.isStatus() && !util.verificaAtraso(ano, mes, dia)) {
						status = "Devolvido";
					} else if(!em.isStatus() && !util.verificaAtraso(ano, mes, dia)){
						status = "Não devolvido";
					} else if(util.verificaAtraso(ano, mes, dia)) {
						status = "Atrasado";
					}
					Object[] dados = {numRegistroLivro, book, user, eDate, dDate, status};
					m.addRow(dados);
			}
			}
		});
		inNumeroUsuarioRegistro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inNumeroUsuarioRegistro.setColumns(10);
		inNumeroUsuarioRegistro.setBounds(198, 72, 190, 20);
		telaRegistroLivro.add(inNumeroUsuarioRegistro);
		
		JButton btnRegistrosBuscar = new JButton("BUSCAR");
		btnRegistrosBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NumeroUsuarioRegistro = inNumeroUsuarioRegistro.getText();
				if(util.isNumeric(NumeroUsuarioRegistro)) {
					int inIdUser = Integer.parseInt(NumeroUsuarioRegistro);
				
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					m.setRowCount(0);
						
						if (util.verificaExistencia(pessoas, inIdUser)) {
							for(Emprestimo em : emprestimos) {
								if(em.getNomePessoa().getId() == inIdUser) {
									int numRegistro = em.getNomelivro().getNumRegistro();
									String book = em.getNomelivro().getNome();
									String eUser = em.getNomePessoa().getNome();
									String eDate = em.getDataEmprestimo();
									String dDate = em.getDataDevolucao();
									String status = "";
									String[] data = em.getDataEmprestimo().split("/");
									int dia = Integer.parseInt(data[0]);
									int mes = Integer.parseInt(data[1]);
									int ano = Integer.parseInt(data[2]);
									if(em.isStatus() && !util.verificaAtraso(ano, mes, dia)) {
										status = "Devolvido";
									} else if(!em.isStatus() && !util.verificaAtraso(ano, mes, dia)){
										status = "Não devolvido";
									} else if(util.verificaAtraso(ano, mes, dia)) {
										status = "Atrasado";
									}
									
									Object[] dados = {numRegistro, book, eUser, eDate, dDate, status};
									m.addRow(dados);
								}
								
								
						}
						
					}else {
						lblerroBuscaId.setText("ID não cadastrado!");
				}
						
						
				}else {
					lblerroBuscaId.setText("Digite algum número!");
				}
				inNumeroUsuarioRegistro.setText("");
			}
			});
		btnRegistrosBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrosBuscar.setBounds(398, 71, 89, 23);
		telaRegistroLivro.add(btnRegistrosBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 122, 564, 209);
		telaRegistroLivro.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0 Registro", "Livro", "Usu\u00E1rio", "Empr\u00E9stimo", "Devolu\u00E7\u00E3o", "Situa\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(68);
		scrollPane.setViewportView(table);
		
		lblerroBuscaId = new JLabel("");
		lblerroBuscaId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblerroBuscaId.setForeground(Color.RED);
		lblerroBuscaId.setBounds(395, 97, 110, 14);
		telaRegistroLivro.add(lblerroBuscaId);
		
		JPanel telaEmprestimoLivro = new JPanel();
		telaEmprestimoLivro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSucessoEmprestimo.setText("");
				lblFalhaEmprestimo.setText("");
			}
		});
		telaEmprestimoLivro.setLayout(null);
		frame.getContentPane().add(telaEmprestimoLivro, "name_173722952974500");
		
		JLabel lblRetiradaLivros = new JLabel("EMPR\u00C9STIMO DE LIVROS");
		lblRetiradaLivros.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRetiradaLivros.setBounds(172, 25, 235, 25);
		telaEmprestimoLivro.add(lblRetiradaLivros);
		
		JLabel lblRetiradaNomeUsuario = new JLabel("ID do Usu\u00E1rio:");
		lblRetiradaNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRetiradaNomeUsuario.setBounds(35, 94, 136, 14);
		telaEmprestimoLivro.add(lblRetiradaNomeUsuario);
		
		JLabel lblRetiradaNumRegistro = new JLabel("N\u00B0 Registro do livro:");
		lblRetiradaNumRegistro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRetiradaNumRegistro.setBounds(35, 198, 152, 25);
		telaEmprestimoLivro.add(lblRetiradaNumRegistro);
		
		inIdUsuarioRetirada = new JTextField();
		inIdUsuarioRetirada.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblSucessoEmprestimo.setText("");
				lblFalhaEmprestimo.setText("");
			}
		});
		inIdUsuarioRetirada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inIdUsuarioRetirada.setColumns(10);
		inIdUsuarioRetirada.setBounds(155, 91, 76, 20);
		telaEmprestimoLivro.add(inIdUsuarioRetirada);
		
		inNumRegistroEmprestimo = new JTextField();
		inNumRegistroEmprestimo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblSucessoEmprestimo.setText("");
				lblFalhaEmprestimo.setText("");
			}
		});
		inNumRegistroEmprestimo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inNumRegistroEmprestimo.setColumns(10);
		inNumRegistroEmprestimo.setBounds(197, 200, 103, 20);
		telaEmprestimoLivro.add(inNumRegistroEmprestimo);
		
		JButton btnRetirarLivro = new JButton("RETIRAR");
		btnRetirarLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!util.isNumeric(inIdUsuarioRetirada.getText()) || !util.isNumeric(inNumRegistroEmprestimo.getText())) {
					
				}
				String idUsuarioEmprestimo = inIdUsuarioRetirada.getText();
				String numRegistroEmprestimo = inNumRegistroEmprestimo.getText();
					
				String data = util.infoData();
				if(util.validarEmprestimo(livros, pessoas, numRegistroEmprestimo, idUsuarioEmprestimo)) {
					int idUsuarioEmprestimoL = Integer.parseInt(idUsuarioEmprestimo);
					int numRegistroEmprestimoL = Integer.parseInt(numRegistroEmprestimo);
					
					if(livros.get(numRegistroEmprestimoL - 1).checarDisponibilidade()) {
						emprestimos.add(new Emprestimo(livros.get(numRegistroEmprestimoL - 1), pessoas.get(idUsuarioEmprestimoL - 1), data));
						livros.get(numRegistroEmprestimoL - 1).setDisponibilidade(false);
						lblSucessoEmprestimo.setText("Empréstimo realizado com sucesso");
						inIdUsuarioRetirada.setText("");
						inNumRegistroEmprestimo.setText("");
					}
					
					else {
						lblFalhaEmprestimo.setText("Livro não disponível para empréstimo!");
					}
					
				}
				else {
					lblFalhaEmprestimo.setText("Usuário ou livro não encontrado!");
				}
			}
		});
		btnRetirarLivro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRetirarLivro.setBounds(216, 293, 131, 23);
		telaEmprestimoLivro.add(btnRetirarLivro);
		
		lblSucessoEmprestimo = new JLabel("");
		lblSucessoEmprestimo.setForeground(new Color(0, 128, 0));
		lblSucessoEmprestimo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSucessoEmprestimo.setBounds(327, 75, 235, 25);
		telaEmprestimoLivro.add(lblSucessoEmprestimo);
		
		lblFalhaEmprestimo = new JLabel("");
		lblFalhaEmprestimo.setForeground(Color.RED);
		lblFalhaEmprestimo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFalhaEmprestimo.setBounds(304, 151, 282, 30);
		telaEmprestimoLivro.add(lblFalhaEmprestimo);
		
		JPanel telaBuscaUsers = new JPanel();
		frame.getContentPane().add(telaBuscaUsers, "name_17851588731600");
		telaBuscaUsers.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setToolTipText("");
		scrollPane_1.setBounds(68, 102, 451, 209);
		telaBuscaUsers.add(scrollPane_1);
		
		table_User = new JTable();
		table_User.setColumnSelectionAllowed(true);
		table_User.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Usu\u00E1rio", "Telefone"
			}
		));
		table_User.getColumnModel().getColumn(0).setPreferredWidth(30);
		scrollPane_1.setViewportView(table_User);
		
		JLabel lblusersCadastradosl_1 = new JLabel("USU\u00C1RIOS CADASTRADOS");
		lblusersCadastradosl_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblusersCadastradosl_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblusersCadastradosl_1.setBounds(150, 41, 271, 25);
		telaBuscaUsers.add(lblusersCadastradosl_1);
		
		telaBuscaLivros = new JPanel();
		telaBuscaLivros.setLayout(null);
		frame.getContentPane().add(telaBuscaLivros, "name_94031368630000");
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setToolTipText("");
		scrollPane_1_1.setBounds(68, 102, 451, 209);
		telaBuscaLivros.add(scrollPane_1_1);
		
		tableLivros = new JTable();
		tableLivros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Livro", "Local", "Disponibilidade"
			}
		));
		scrollPane_1_1.setViewportView(tableLivros);
		
		JLabel lblusersCadastradosl_1_1 = new JLabel("LIVROS CADASTRADOS");
		lblusersCadastradosl_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblusersCadastradosl_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblusersCadastradosl_1_1.setBounds(150, 41, 271, 25);
		telaBuscaLivros.add(lblusersCadastradosl_1_1);
		
		JMenuBar menuBar_1 = new JMenuBar();
		frame.setJMenuBar(menuBar_1);
		
		JMenu mnNewMenu_2 = new JMenu("Usu\u00E1rio");
		menuBar_1.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Cadastro");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(telaCadastroUsuario);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Checar Registros");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(telaRegistroLivro);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				
				DefaultTableModel m = (DefaultTableModel) table.getModel();
				m.setRowCount(0);
				
				for(Emprestimo em : emprestimos) {
					String dDate;
					int numRegistroLivro = em.getNomelivro().getNumRegistro();
					String book = em.getNomelivro().getNome();
					String user = em.getNomePessoa().getNome();
					String eDate = em.getDataEmprestimo();
					
					String status = "";
					String[] data = em.getDataEmprestimo().split("/");
					int dia = Integer.parseInt(data[0]);
					int mes = Integer.parseInt(data[1]);
					int ano = Integer.parseInt(data[2]);
					dDate = em.getDataDevolucao();
					if(em.isStatus()) {
						status = "Devolvido";
						if(util.verificaAtraso(ano, mes, dia)) {
							status = "Devol. c/ atraso";
						}
					} else if(!em.isStatus() && !util.verificaAtraso(ano, mes, dia)){
						status = "Não devolvido";
					} else if(util.verificaAtraso(ano, mes, dia) && !em.isStatus()) {
						status = "Atrasado";
					}
					
					Object[] dados = {numRegistroLivro, book, user, eDate, dDate, status};
					
					m.addRow(dados);
					
				}
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Visualizar");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(telaBuscaUsers);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				
				DefaultTableModel m = (DefaultTableModel) table_User.getModel();
				m.setRowCount(0);
				
				for(Pessoa people : pessoas) {
					String user = people.getNome();
					int idUsuario = people.getId();
					String telefUsuario = people.getTelefone();
					
					Object[] dadosUser = {idUsuario, user, telefUsuario};
					m.addRow(dadosUser);
					
				}
			}
		});
		
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_1_1 = new JMenu("Livros");
		menuBar_1.add(mnNewMenu_1_1);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Cadastro");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(telaCadastroLivros);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
			}
		});
		mnNewMenu_1_1.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_3_1 = new JMenuItem("Devolu\u00E7\u00E3o");
		mntmNewMenuItem_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(telaDevolucaoLivro);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
			}
		});
		mnNewMenu_1_1.add(mntmNewMenuItem_3_1);
		
		JMenuItem mntmNewMenuItem_4_1 = new JMenuItem("Empr\u00E9stimo");
		mntmNewMenuItem_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(telaEmprestimoLivro);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				
				lblSucessoEmprestimo.setText("");
				lblFalhaEmprestimo.setText("");
			}
		});
		mnNewMenu_1_1.add(mntmNewMenuItem_4_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Visualizar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(telaBuscaLivros);
				frame.getContentPane().repaint();
				frame.getContentPane().revalidate();
				
				DefaultTableModel m = (DefaultTableModel) tableLivros.getModel();
				m.setRowCount(0);
				
				for(Livro book : livros) {
					String nomeLivro = book.getNome();
					int idLivro = book.getNumRegistro();
					String localLivro = book.getLocal();
					boolean disponibilidade = book.isDisponibilidade();
					String disponivel;
					if(disponibilidade) {
						disponivel = "Disponível";
					}else {
						disponivel = "Indisponível";
					}
					
					Object[] dadosLivro = {idLivro, nomeLivro, localLivro, disponivel};
					
					m.addRow(dadosLivro);
					
				}
			}
		});
		mnNewMenu_1_1.add(mntmNewMenuItem);
	}	
}

