package pl.polsl.bustimetable.model;

import java.util.ArrayList;

/**
 * Represents a bus stop with its name and bus lines.
 * 
 * @author Bartosz Dyba
 * @version 1.0
 */
public class BusStop {
    /** Name of the bus stop. */
    private String busStopName;
    
    /** List of bus lines assigned to the bus stop. */
    private ArrayList<Bus> buses = new ArrayList<>();
    
    /**
    * Creates a new bus stop with the entered name.
    * Throws an exception if the name is empty.
    *
    * @param name the name of the bus stop
    * @throws BusTimetableException if the bus stop name is empty
    */
    public BusStop(String name) throws BusTimetableException {
        if (name.isEmpty()) {
            throw new BusTimetableException("Bus stop name cannot be empty!");
        }
        this.busStopName = name;
    }
    
    /**
    * Returns the name of the bus stop.
    *
    * @return name of the bus stop
    */
    public String getBusStopName(){
        return busStopName;
    }
    
    /**
     * Adds a new bus line to the bus stop.
     * Throws an exception if the bus line already exists.
     * 
     * @param line name of the bus line
     * @throws BusTimetableException if the bus line already exists at this bus stop
     */
    public void addBusLine(Bus line)throws BusTimetableException{
        for (Bus buses : buses) {
            if(buses.getBusName().equals(line.getBusName())){
                throw new BusTimetableException("Bus already exists at this bus stop!");
            }
        }
        buses.add(line);
    }
    
    /**
     * Returns a copy of the list of bus lines.
     * 
     * AI helped with creating a copy of the ArrayList instead of
     * returning the original list. This prevents other classes
     * from directly modifying the original list of bus lines
     * 
     * @return A copy list of bus lines at a bus stop
     */
    public ArrayList<Bus> getBusLines() {
        return new ArrayList<>(buses);
    }
}
