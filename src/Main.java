import views.MainMenu;
import util.DbConnect;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("--- STARTING APP ---");
//		DbConnect.createNewDatabase("data.db");
//		DbConnect.createNewTable("data.db");

		// SQLite connection
		DbConnect.connect();
		
		MainMenu mainMenu = new MainMenu();
		mainMenu.showOptions();

		System.out.println("END OF APP");
	
	}

}
