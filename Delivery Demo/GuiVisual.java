import javax.swing.JFrame;

public class GuiVisual extends JFrame {
	
	public static GuiVisual gui = new GuiVisual();
	
	public void closeProgram (){
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void sizeUp (){
		gui.setSize(200, 200);
	}
	
	public void visibility (){
		gui.setVisible(true);
	}
	
	public void title(){
		gui.setTitle("My Second Gui");
	}
	
	
	
	
	
	//Ideally I can just delete this main method, but I'm leaving it for reference. When it runs, it will create an empty box.
	public static void main(String[] args) {
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(200, 200);
		gui.setVisible(true);
		gui.setTitle("First Gui");
	
			  

	}

}
