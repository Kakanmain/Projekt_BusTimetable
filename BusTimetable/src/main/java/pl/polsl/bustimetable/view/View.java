package pl.polsl.bustimetable.view;

import java.util.Scanner;
import pl.polsl.bustimetable.model.BusStop;

/**
 * View responsible for communicating with the user.
 * 
 * @author Bartosz Dyba
 * @version 1.0
 */
public class View {
    /** Scanner used to read user input from the console. */
    private Scanner scanner = new Scanner(System.in); 
    
    /**
     * Creates a new View object.
     */
    public View() {
    }
    
    /**
     * Reads input entered by the user.
     * 
     * @return input from the user 
     */
    public String getInput() {
        return scanner.nextLine();
    }
    
    
    /**
     * Displays a message in the console.
     * 
     * @param message a message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
    
    /**
    * Displays the main menu.
    */
    public void showMenu(){
        System.out.println("1. Add bus stop");
        System.out.println("2. Delete bus stop");
        System.out.println("3. Show bus stop timetable");
        System.out.println("4. Show bus stops");
        System.out.println("5. Exit");
        System.out.println("Enter command:");
    }
    
    /**
    * Displays the name of the provided bus stop.
    *
    * @param busStop bus stop whose name will be displayed
    */
    public void showName(BusStop busStop){
        System.out.println(busStop.getBusStopName());
    }
    
    
}
