package alunosdatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
	private View view;
	private Model model;
	
	public Controller(View view, Model model){
		this.view = view;
		this.model = model;
		
		this.view.addCadastroListener(new CadastroListener());
		this.view.addListListener(new ListListener());
	}
	
	// Button "Cadastro"
	class CadastroListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String nome = null, matricula = null;
			int idade = 0;
			boolean erroInput;
			
			// Get the data from view's text fields 
			try{
				erroInput = false;
				nome = view.getNome();
				matricula = view.getMatricula();
				idade = view.getIdade();
			} catch(NumberFormatException ex){
				view.displayErrorMsg("Wrong format for input!");
				erroInput = true;
			}
			
			if(!erroInput){
				// Save data to file
				try {
					model.saveToFile(nome, matricula, idade);
				} catch(IOException ex){
					System.out.printf("ERRO: %s\n", ex);
				}
			}
		}
	}
	// Button "List"
	class ListListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// List all alunos' data
			try {
				ArrayList<Model.Aluno> alunos = model.getAlunosList();
				view.listAlunos(alunos);
			} catch(IOException ex){
				view.displayErrorMsg("ERRO: " + ex);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
