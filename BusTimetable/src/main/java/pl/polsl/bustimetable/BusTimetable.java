package pl.polsl.bustimetable;
import pl.polsl.bustimetable.controller.Controller;
import pl.polsl.bustimetable.view.Window;

/**
 * Bus timetable application.
 * Allows users to add and delete bus stops,
 * add bus lines and departure times,
 * and display bus stop timetables and all bus stops.
 *
 * @author Bartosz Dyba
 * @version 1.2
 */
public class BusTimetable {

    /** Creates a new BusTimetable object. */
    public BusTimetable() {
    }
    
    /**
     * Main method of application.
     * If no arguments are provided, the main menu is displayed.
     * If arguments are provided, only the first argument is taken into account.
     * The main menu is displayed and the argument is passed to the controller 
     * and from controller into text field in view.window as a default 
     * text written in option "menuShowBusStopTimetable"
     * 
     * @param args bus stop name to be written as a default option in text 
     * field choosing to show bus stop timetable
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        
        if (args.length != 0){
            controller.setArg(args[0]);
        } 
        
        java.awt.EventQueue.invokeLater(() -> new Window(controller).setVisible(true));       
    } 
}