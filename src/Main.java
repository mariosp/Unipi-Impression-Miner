import views.MainMenu;
import util.DbConnect;

public class Main {

	public static void main(String[] args) {
		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("             | Starting App |");
		System.out.println("******************************************");
		System.out.println("\n");
//		DbConnect.createNewDatabase("data.db");
//		DbConnect.createNewTable("data.db");

		// SQLite connection
		DbConnect.connect();

		// Create mainMenu object
		MainMenu mainMenu = new MainMenu();
		// Run showOption()
		mainMenu.showOptions();

		System.out.println("END OF APP");
	
	}

}
