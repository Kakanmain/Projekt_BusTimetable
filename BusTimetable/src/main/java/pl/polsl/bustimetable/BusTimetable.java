package pl.polsl.bustimetable;

import pl.polsl.bustimetable.controller.Controller;
import pl.polsl.bustimetable.view.View;

/**
 * Bus timetable application.
 * Allows users to add and delete bus stops,
 * add bus lines and departure times,
 * and display bus stop timetables.
 *
 * @author Bartosz Dyba
 * @version 1.0
 */
public class BusTimetable {

    /**
     * Creates a new BusTimetable object.
     */
    public BusTimetable() {
    }
    
    /**
     * Main method of application.
     * If no arguments are provided, the main menu is displayed.
     * If arguments are provided, each argument represents a bus stop name.
     * Bus stop timetables are displayed in the order of the arguments.
     * Arguments right now are not usable, but in future they can be used
     * to read instantly a bus stop timetable that was loaded from a file.
     * 
     * @param args bus stop names in the order they should be displayed
     */
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        
        
        if (args.length == 0){
            controller.readArguments();
        } else {
            for (int i=0; i < args.length; i++){
                controller.showTimetable(args[i]);
            }
            return;
        }
    } 
}

