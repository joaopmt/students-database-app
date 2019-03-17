package alunosdatabase;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import alunosdatabase.Model.Aluno;

public class View extends JFrame{
	private JTextField nomeTextField = new JTextField(12);
	private JTextField matriculaTextField = new JTextField(12);
	private JTextField idadeTextField = new JTextField(12);
	private JButton cadastroButton = new JButton("Cadastrar");
	private JButton listButton = new JButton("Listar alunos");
	
	View(){
		JPanel cadastroPanel = new JPanel();
		cadastroPanel.setLayout(new FlowLayout());
		cadastroPanel.add(new JLabel("Nome"));
		cadastroPanel.add(nomeTextField);
		cadastroPanel.add(new JLabel("Matrícula"));
		cadastroPanel.add(matriculaTextField);
		cadastroPanel.add(new JLabel("Idade"));
		cadastroPanel.add(idadeTextField);
		cadastroPanel.add(cadastroButton);
		
		JPanel listPanel = new JPanel();
		listPanel.add(listButton);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setSize(700, 200);
		this.add(cadastroPanel);
		this.add(listPanel);
	}
	
	public String getNome(){
		return nomeTextField.getText();
	}
	
	public String getMatricula(){
		return matriculaTextField.getText();
	}
	
	public int getIdade(){
		return Integer.parseInt(idadeTextField.getText());
	}
	
	// Add Listener for Button cadastro
	void addCadastroListener(ActionListener listenerForCadastroButton){
		cadastroButton.addActionListener(listenerForCadastroButton);
	}
	// Add Listener for Button list
	void addListListener(ActionListener listenerForListButton){
		listButton.addActionListener(listenerForListButton);
	}
	
	void displayErrorMsg(String msg){
		JOptionPane.showMessageDialog(this, msg);
	}
	
	// Displays all ArrayList alunos info in a msg dialog
	void listAlunos(ArrayList<Aluno> alunos){
		String infos = "";
		for(Aluno a : alunos){
			infos += "Nome: ";
			infos += a.getNome();
			infos += '\n';
			infos += "Matrícula: ";
			infos += a.getMatricula();
			infos += '\n';
			infos += "Idade: ";
			infos += a.getIdade();
			infos += "\n\n";
		}
		JOptionPane.showMessageDialog(this, infos);
	}
}
