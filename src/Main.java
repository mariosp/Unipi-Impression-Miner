import views.MainMenu;
import util.DbConnect;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("--- STARTING APP ---");
		DbConnect.createNewDatabase("data.db");
		DbConnect.connect();
		DbConnect.createNewTable();
		//DbConnect.selectAll();
		
		
		MainMenu mainmenu = new MainMenu();
		mainmenu.showOptions();
		
		
		System.out.println("END OF APP");
	
	}

}
