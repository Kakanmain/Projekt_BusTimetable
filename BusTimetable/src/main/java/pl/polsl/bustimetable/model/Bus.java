package pl.polsl.bustimetable.model;

import java.util.ArrayList;

/**
 * Represents a bus with its name and departure times.
 * 
 * @author Bartosz Dyba
 * @version 1.0
 */
public class Bus {
    /** Name of the bus line. */
    private String busName;
    
    /** List of departure hours. */
    private ArrayList<Integer> departureTimes = new ArrayList<>();
    
    /**
     * Creates a new bus with the entered name.
     * Throws an exception if the name is empty or too long.
     * 
     * @param name the name of the bus
     * @throws BusTimetableException if the name is empty or too long
     */
    public Bus(String name) throws BusTimetableException {
        if (name.isEmpty()) {
            throw new BusTimetableException("Bus line cannot be empty!");
        }else if (name.length() > 3) {
            throw new BusTimetableException("Bus line name is too long!");
        }
        
        this.busName = name;
    }
    
    /**
     * Returns the name of the bus line.
     * 
     * @return name of the bus line
     */
    public String getBusName(){
        return busName;
    }
    
    /**
     * Adds a departure time to the bus line.
     * Throws an exception if the time is outside the allowed range.
     * 
     * @param time the departure time to add
     * @throws BusTimetableException if the time is not between 0 and 24
     */
    public void addDepartureTime(int time) throws BusTimetableException {
        if ((time > 24) || (time < 0)) {
            throw new BusTimetableException("Time of departure must be between 0 and 24!");
        }
        departureTimes.add(time);
    }
    
    /**
     * Returns a copy of the list of departure times.
     * 
     * AI helped with creating a copy of the ArrayList instead of
     * returning the original list. This prevents other classes
     * from directly modifying the original list of bus lines
     * 
     * @return A copy list of departure times
     */
    public ArrayList<Integer> getDepartureTimes() {
        return new ArrayList<>(departureTimes);
    }
}
