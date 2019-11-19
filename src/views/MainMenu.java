package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class MainMenu {
	InsertKeywords insertKeywords = new InsertKeywords();

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
		System.out.println("5. Exit application");

		try {
			readUserOption();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * User is selecting an option
	 * by pressing a valid menu option
	 * and causes selectedOption() to execute.
	 */
	private void readUserOption() throws InterruptedException, ExecutionException {
		try{
			// reading user input line by line
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = reader.readLine();
			// execute selectedOption()
			onSelectOption(line);

        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 *
	 * @param option
	 */
	private void onSelectOption(String option) throws InterruptedException, ExecutionException {
		
		switch(option) {
		case "1":
			insertKeywords.showMenu();
			clearScreen();
			showOptions();
			break;
		case "2":
			if(insertKeywords.getSearchKeywords().size() != 0) {
				ImpressionMiner im = new ImpressionMiner(insertKeywords.getSearchKeywords());
				im.setTasks();
				clearScreen();
				showOptions();
			}else {
			System.out.println("The list is empty, please select option 1 first.");
			showOptions();
			}
			break;
		case "3":
			Statistics statistics = new Statistics();
			statistics.showOptionMenu();
			break;
		case "4":
			SearchInfo searchinfo = new SearchInfo();
			searchinfo.showOptionMenu();
			break;
		case "5":
			System.exit(0);
			break;
			default:
				System.out.println("Please Try Again. With a correct option.");
				showOptions();
		}

		
	}

	public static void clearScreen() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}catch (Exception E) {
			System.out.println((E));
		}
//	    System.out.print("\033[H\033[2J");
//	    System.out.flush();
	}
	
}
