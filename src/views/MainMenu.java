package views;

import util.UserInputs;
import java.util.concurrent.ExecutionException;

public class MainMenu {
	InsertKeywords insertKeywords = new InsertKeywords();
	UserInputs inputs = new UserInputs();

	/**
	 * Main Menu
	 */
	public void showOptions() {

		System.out.println("--- Main Menu ---");
		System.out.println("Select one option :");
		System.out.println("1. Insert search keywords");
		System.out.println("2. Start Impression miner");
		System.out.println("3. Statistics");
		System.out.println("4. Search History");
		System.out.println("5. Clear Console");
		System.out.println("6. Exit application");
		try {
			/**
			 * User is selecting an option
			 * by pressing a valid menu option
			 * and causes selectedOption() to execute.
			 */
			int userInput = inputs.onInputNumber();
			onSelectOption(userInput);

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}


	/**
	 *
	 * @param option
	 */
	private void onSelectOption(int option) throws InterruptedException, ExecutionException {
		
		switch(option) {
		case 1:
			insertKeywords.showMenu();
			showOptions();
			break;
		case 2:
			if(insertKeywords.getSearchKeywords().size() != 0) {
				ImpressionMiner im = new ImpressionMiner(insertKeywords.getSearchKeywords());
				im.setTasks();
				showOptions();
			}else {
			System.out.println("The list is empty, please select option 1 first.");
			showOptions();
			}
			break;
		case 3:
			Statistics statistics = new Statistics();
			statistics.showOptionMenu();
			showOptions();
			break;
		case 4:
			SearchInfo searchinfo = new SearchInfo();
			searchinfo.showOptionMenu();
			showOptions();
			break;
		case 5:
			clearConsole();
			showOptions();
			break;
		case 6:
			System.exit(0);
			break;
			default:
				System.out.println("Please Try Again. With a correct option.");
				showOptions();
		}

		
	}

	public static void clearConsole() {
		try {
			Process exitCode;
			Runtime r = Runtime.getRuntime();
			if (System.getProperty("os.name").startsWith("Window")) {
				exitCode = r.exec("cls");
			} else {
				exitCode = r.exec("clear");
			}
			System.out.println(exitCode);
			for (int j = 0; j < 200; j++) {
				System.out.println();
			}
			System.out.println("\n");
			System.out.println("******************************************");
			System.out.println("       | The Console is clean |");
			System.out.println("******************************************");
			System.out.println("\n");

		} catch (Exception e) {
			  e.printStackTrace();
		}
	}
	
}
