package pl.polsl.bustimetable.controller;

import java.util.ArrayList;
import pl.polsl.bustimetable.model.BusStop;
import pl.polsl.bustimetable.model.Bus;
import pl.polsl.bustimetable.exceptions.BusTimetableException;
import pl.polsl.bustimetable.view.Window;

/**
 * Controller responsible for managing bus stops and handling user commands.
 * Connects the model with the window.
 * 
 * @author Bartosz Dyba
 * @version 1.2
 */
public class Controller {
    
    /** List of all created bus stops. */
    private ArrayList<BusStop> busStops = new ArrayList<>();
    
    /** Holds currently used bus stop. */
    private BusStop currentBusStop;
    
    /** Holds currently used bus line. */
    private Bus currentBusLine;
    
    /** Holds argument provided by user. */
    private String arg;
    
    /** Holds the Window object used for communication with the GUI. */
    private Window window;
    
    /** Creates a new Controller  object. */
    public Controller() {}
    
    /**
     * Saves argument to variable for further usage.
     * 
     * @param input argument given by user
     */
    public void setArg(String input){
        arg = input;
    }
    
    /**
     * Returns argument given by user.
     * 
     * @return argument given by user
     */
    public String getArg(){
        return arg;
    }
    
    /**
     * Sets the window used by the controller.
     * 
     * @param w the Window object used by the program
     */
    public void setWindow (Window w){
        window  = w;
    }
    
    /**
     * Adds departure time to currentBusLine.
     * Displays an error message if the input is invalid.
     * 
     * @param time time to add
     * @return boolean whether the operation was successful or not
     */
    public boolean addDepartureTime(String time){
        boolean success = true;
        try{
            int intInput = Integer.parseInt(time);
            currentBusLine.addDepartureTime(intInput);
        } catch (BusTimetableException e) {
            window.popupError(e.getMessage());
            success = false;
        } catch (NumberFormatException e) {
            window.popupError("Time must be a number!!!");
            success = false;
        }
        return success;
    }
    
    /**
     * Adds bus line to currentBusStop 
     * and changes currentBusLine to the new one.
     * Displays an error message if the input is invalid.
     * 
     * @param name the name of bus line to add
     * @return boolean whether the operation was successful or not
     */
    public boolean addBusLine(String name){
        boolean success = true;
        
        try{
            Bus bus = new Bus(name);
            currentBusStop.addBusLine(bus);
            currentBusLine = bus;
        } catch (BusTimetableException e) {
            window.popupError(e.getMessage());
            success = false;
        }
        return success;
    }
       
    /**
     * Adds new bus stop and changes currentBusStop to the new one.
     * Displays an error message if the input is invalid.
     * 
     * @param name the name of bus stop to add
     * @return boolean whether the operation was successful or not
     */
    public boolean addBusStop(String name){
        boolean success = true;
        String busStopName = name;
        for (BusStop busStop : busStops) {
            if(busStop.getBusStopName().equals(busStopName)){
                window.popupError("Bus stop already exists!!!");
                return false;
            }
        }
        
        try {
            BusStop busStop = new BusStop(busStopName);
            busStops.add(busStop);
            currentBusStop = busStop;

        } catch (BusTimetableException e) {
            window.popupError(e.getMessage());
            success = false;
        }
        return success;
    }
    
    /**
     * Deletes an existing bus stop.
     * Displays a warning message if name is empty.
     * Displays an error message if the input is invalid.
     * 
     * @param name the name of bus stop to delete
     * @return boolean whether the operation was successful or not
     */
    public boolean deleteBusStop(String name){
        boolean success = true;
        if(name.isBlank()){
            success = false;
            window.popupWarning("Please enter bus stop name to delete!");
        } else {
            int i = 0;
            
            try{
            for (BusStop busStop : busStops) {
                if(busStop.getBusStopName().equals(name)) break;
                i++;
            }
            busStops.remove(i);
            } catch (IndexOutOfBoundsException e){
                window.popupError("There is no bus stop of given name to delete!!!");
                success = false;
            }
        }
        return success;
    }

    /**
     * Creates new 2D table with rows equal to number of bus lines
     * at the given bus stop and two columns (bus line | times of departure).
     * Then it inputs bus lines and their time of departure to this 2D table.
     * 
     * AI-assisted code:
     * With complexity of javax.swing.table.DefaultTableModel,
     * AI helped create the code to prepare data for a JTable in Swing.
     * 
     * @param name the name of bus stop timetable to show
     * @return 2D table data of bus lines with their times of departure
     *         or null if the bus stop does not exist
     */
    public Object[][] showBusStopTimetable(String name) {
        for (BusStop busStop : busStops) {
            if (busStop.getBusStopName().equals(name)) {
                Object[][] data = new Object[busStop.getBusLines().size()][2];

                int i = 0;
                for (Bus bus : busStop.getBusLines()) {
                    StringBuilder message = new StringBuilder();
                    
                    for (int time : bus.getDepartureTimes()) {
                       message.append(time).append(" ");
                    }

                    data[i][0] = bus.getBusName();
                    data[i][1] = message.toString();
                    i++;
                }
                return data;
            }
        }    
        return null;
    }
    
    /**
     * Creates a list of all bus stop names.
     * Uses StringBuilder to place each bus stop name under the other.
     * 
     * AI-assisted code:
     * As being new to java AI helped create for loop that iterates
     * through the busStops list.
     * 
     * @return StringBuilder to correctly show in GUI
     */
    public StringBuilder showBusStops(){
        StringBuilder message = new StringBuilder();
        for (BusStop busStop : busStops) {
            message.append(busStop.getBusStopName());
            message.append("\n");
        }
        return message;
    }
}
