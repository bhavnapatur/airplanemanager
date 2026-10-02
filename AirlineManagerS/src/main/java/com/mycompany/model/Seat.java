/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Seat --- represents an individual airplane seat and its reservation state.
 * 
 * @author Bhavna P
 */
package com.mycompany.model;

public class Seat 
{
    private String seatNumber;
    private String seatClass;
    private int rowNumber;
    private boolean occupancyStatus;
    private String passengerName;
    
    /**
    * Constructs a Seat object with initial unreserved status.
    * 
    * @param seatNumber Unique seat identifier string
    * @param rowNumber Row index integer
    * @param seatClass Seat class string
    */
    
    public Seat(String seatNumber, int rowNumber, String seatClass) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.seatClass = seatClass;
        this.occupancyStatus = false;
        this.passengerName = "";
    }
    
    //getter
    /**
    * Accessor for seat number identifier.
    * 
    * @return Seat identifier string
    */
    public String getSeatNumber() {
        return seatNumber;
    }
    //getter
    /**
    * Accessor for seat class designation.
    * 
    * @return Seat class category string
    */
    public String getSeatClass() {
        return seatClass;
    }

    //getter
    /**
    * Accessor for passenger name assigned to seat.
    * 
    * @return Assigned passenger name string
    */
    public String getPassengerName() {
        return passengerName;
    }
    //getter
    /**
    * Accessor for occupancy state.
    * 
    * @return True if occupied, false otherwise
    */
    public boolean isOccupied() {
        return occupancyStatus;
    }
    //setter
    /**
    * Mutator for assigning passenger name.
    * 
    * @param passengerName Passenger name string to assign
    */
    public void setPassengerName(String newName) {
        this.passengerName = newName;
    }
    //setter
    /**
    * Mutator for updating occupancy status.
    * 
    * @param occupancyStatus True to mark occupied, false for available
    */
    public void setOccupied(boolean status) {
        this.occupancyStatus = status;
    }
}
