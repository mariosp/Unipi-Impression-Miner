package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class MainMenu {
	InputMenu inputMenu = new InputMenu();

	public MainMenu(){
	}
	
	public void showOptions() {
		System.out.println("--- Main Menu ---");
		System.out.println("Press one option :");
		System.out.println("1. Insert search keywords");
		System.out.println("2. Start Impression miner");
		System.out.println("3. Statistics");
		System.out.println("4. Exit application");
		try {
			readOption();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	private void readOption() throws InterruptedException, ExecutionException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			int read = in.read();
			while(read<49 || read>51) {
				System.out.println(read);
				read = in.read();
				
			}
			
			selectedOption((char) read);
            
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void selectedOption(char read) throws InterruptedException, ExecutionException {
		
		switch(read) {
		case '1':
			inputMenu.showMenu();
			clearScreen();
			showOptions();
			break;
		case '2':
			if(inputMenu.getSearchKeywords().size() != 0) {
				ImpressionMiner im = new ImpressionMiner(inputMenu.getSearchKeywords());
				im.setTasks();
				
			}else {
			System.out.println("The list is empy, please select option 1 first.");
			showOptions();
			}
			break;
		case '3':
			Statistics statistics = new Statistics();
			statistics.showOptionMenu();
			break;
		case '4':
			System.exit(0);
			break;
		}
		
	}
	
//	private void clearMenu() {
//		 try {
//			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//		} catch (InterruptedException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    System.out.flush();  
//	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
	
}
