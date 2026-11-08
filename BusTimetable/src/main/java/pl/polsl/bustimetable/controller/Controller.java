package pl.polsl.bustimetable.controller;

import java.util.ArrayList;
import pl.polsl.bustimetable.model.BusStop;
import pl.polsl.bustimetable.model.Bus;
import pl.polsl.bustimetable.model.BusTimetableException;
import pl.polsl.bustimetable.view.View;

/**
 * Controller responsible for managing bus stops and handling user commands.
 * Connects the model with the view.
 * 
 * @author Bartosz Dyba
 * @version 1.0
 */
public class Controller {
    /** View used for communication with the user. */
    private View view;
    
    /** List of all created bus stops. */
    private ArrayList<BusStop> busStops = new ArrayList<>();
    
    /**
    * Creates a controller with view.
    *
    * @param view the view used for user interaction
    */
    public Controller(View view)
    {
        this.view = view;
    }
    
    /**
    * Displays the main menu and handles the user's selected option.
    * Displays an error message if the input is invalid.
    */
    public void readArguments(){
        view.showMenu();
        try{
            String input = view.getInput();
            int answer = Integer.parseInt(input);
            if((answer > 0) && (answer <= 5)){
                switch (answer){
                    case 1 -> addBusStop();
                    case 2 -> deleteBusStop();
                    case 3 -> showBusStopTimetable();
                    case 4 -> showBusStops();
                    case 5 -> exit();
                }
            }else{
                view.showMessage("Input must be between 1 and 5!");
            }
        }catch(NumberFormatException e) {
            view.showMessage("Input must be a number!");
        }
    }
    
    /**
    * After writing the name of a non-existing bus stop it creates one, then it
    * asks the user to input a correct bus line to add to this bus stop and then
    * it asks to add departure times to this bus line. Once the user enters
    * "N", he can continue adding bus lines again and after another "N", program 
    * stops returns to main menu.
    * Displays an error message if the input is invalid.
    */
    private void addBusStop(){
        view.showMessage("Please enter the name for new bus stop: ");
        String busStopName = view.getInput();
        for (BusStop busStop : busStops) {
            if(busStop.getBusStopName().equals(busStopName)){
                view.showMessage("Bus stop already exists!");
                readArguments();
                return;
            }
        }
        
        try {
            BusStop busStop = new BusStop(busStopName);
            busStops.add(busStop);

            view.showMessage("Enter a line to add to this bus stop or type 'N' to stop adding buses");
            do {
                String input = view.getInput();
                if (input.equals("N")) break;
                Bus bus = new Bus(input);
                busStop.addBusLine(bus);
                
                view.showMessage("Enter a departure time to add to this bus or type 'N' to stop adding times");
                do {
                    String input2 = view.getInput();
                    if (input2.equals("N")) break;
                    int intInput = Integer.parseInt(input2);
                    bus.addDepartureTime(intInput);

                } while (true);    
            view.showMessage("Enter another line or type 'N' to stop adding buses");
            } while (true);
            
        } catch (BusTimetableException e) {
            view.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            view.showMessage("Time must be a number!");
        }
        readArguments();
    }
    
    /**
    * Deletes an existing bus stop after writing its name.
    * Displays an error message if the bus stop does not exist.
     */
    private void deleteBusStop(){
        view.showMessage("Please enter the name of bus stop to delete: ");
        String busStopName = view.getInput();
        int i = 0;
        try{
        for (BusStop busStop : busStops) {
            if(busStop.getBusStopName().equals(busStopName)) break;
            i++;
        }
        busStops.remove(i);
        } catch (IndexOutOfBoundsException e){
            view.showMessage("Bus stop does not exist!");
        }
        readArguments();
    }
    
    /**
    * Displays the timetable of the given bus stop.
    * 
    * AI helped with combining strings using a StringBuilder.
    * It allows departure times to be combined into one string,
    * making the displayed message cleaner and easier to read.
    *
    * @param name the name of the bus stop
    */
    public void showTimetable(String name){ 
        for (BusStop busStop : busStops) {
            if(busStop.getBusStopName().equals(name)){
                for (Bus bus : busStop.getBusLines()) {
                    StringBuilder message = new StringBuilder(); // AI
                    view.showMessage(bus.getBusName()+": ");
                            
                    for (int time : bus.getDepartureTimes()){
                        message.append(time).append(" "); // AI
                    }
                    view.showMessage(message.toString()); // AI
                }
            }
        }
    }
    
    /**
    * Asks the user for a bus stop name and displays its timetable.
    * Displays a message if no bus stops are created.
    */
    private void showBusStopTimetable(){ 
        if (busStops.isEmpty()){
            view.showMessage("There are no bus stops to show");
        } else {
        view.showMessage("Enter the name of the desired bus stop");
        String input = view.getInput();
        showTimetable(input);
        }
        readArguments();
    }
    
    /**
     * Displays the names of all created bus stops.
     * 
     * AI-assisted code:
     * As being new to java AI helped create for loop that iterates
     * through the busStops list.
     * Each bus stop is passed to the showName() method to display its name.
     */
    private void showBusStops(){
        for (BusStop busStop : busStops) {
            view.showName(busStop);
        }
        readArguments();
    }
    
    
    /**
     * Exits the program
     */
    private void exit(){
        return;
    }
}
