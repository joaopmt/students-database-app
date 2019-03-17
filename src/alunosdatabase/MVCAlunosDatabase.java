package alunosdatabase;

public class MVCAlunosDatabase {
	
	public static void main(String[] args){
		Model model = new Model();
		View view = new View();
		@SuppressWarnings("unused")
		Controller controller = new Controller(view, model);
		
		view.setVisible(true);
	}
}
