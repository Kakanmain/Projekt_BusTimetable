package pl.polsl.bustimetable.exceptions;

/**
 * Exception class used to handle errors related to the bus timetable.
 * 
 * @author Bartosz Dyba
 * @version 1.2
 */
public class BusTimetableException extends Exception{
    
    /**
     * Non-parameter constructor
     */
    public BusTimetableException(){
        
    }
    
    /**
     * Exception class constructor.
     *
     * @param message message explaining the reason for the error
     */
    public BusTimetableException(String message){
        super(message);
    }
}
