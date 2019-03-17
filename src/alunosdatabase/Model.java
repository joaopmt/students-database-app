package alunosdatabase;

import java.io.*;
import java.util.ArrayList;

public class Model implements Serializable{
	
	class Aluno implements Serializable{
		private String nome;
		private String matricula;
		private int idade;
		
		public Aluno(String nome, String matricula, int idade){
			this.nome = nome;
			this.matricula = matricula;
			this.idade = idade;
		}
		
		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getMatricula() {
			return matricula;
		}
		
		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}
		
		public int getIdade() {
			return idade;
		}
		
		public void setIdade(int idade) {
			this.idade = idade;
		}
		
		@Override
		public String toString(){
			return ("Nome: " + this.nome
				  + "\nMatrícula: " + this.matricula
				  + "\nIdade: " + this.idade);
		}
	}
	
	// Deals with lack of resources to append an object to a file by serialization:
	
	// Define a class that extends ObjectOutputSream just to Override
	// the writeStreamHeader method so it doesn't write a header every time
	// the file is accessed in output mode
	private static class AppendableObjectOutputStream extends ObjectOutputStream {
		public AppendableObjectOutputStream(OutputStream out) throws IOException {
			super(out);
		}
		@Override
		protected void writeStreamHeader() throws IOException {
			// do not write a header
		}
	}
	// Define a class that extends ObjectInputSream just to Override
	// the readStreamHeader method so it doesn't try to read a header every time
	// the file is accessed in input mode
	private static class AppendableObjectInputStream extends ObjectInputStream {
	
	    public AppendableObjectInputStream(InputStream in) throws IOException {
	        super(in);
	    }
	    @Override
	    protected void readStreamHeader() throws IOException {
	        // do not read a header
	    }
	}
	
	// Save a aluno's data to file by serialization of object Aluno
	public void saveToFile(String nome, String matricula, int idade) throws IOException{
		Aluno a = new Aluno(nome, matricula, idade);
		File f = new File("alunosdb");
		FileOutputStream fo = new FileOutputStream(f, true);
		AppendableObjectOutputStream output = new AppendableObjectOutputStream(fo);
		output.writeObject(a);
		
		output.close();
		fo.close();
	}
	// Retrieve all aluno's data from file by de-serialization 
	public ArrayList<Aluno> getAlunosList() throws IOException, ClassNotFoundException, FileNotFoundException{
		File f = new File("alunosdb");
		FileInputStream fi = new FileInputStream(f);
		AppendableObjectInputStream input = new AppendableObjectInputStream(fi);
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		try{
			while(true){
				alunos.add((Aluno) input.readObject());
			}
		} catch(EOFException ex){}
		
		input.close();
		fi.close();
		return alunos;
	}
}
