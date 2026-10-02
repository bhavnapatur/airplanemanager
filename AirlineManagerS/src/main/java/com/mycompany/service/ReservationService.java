/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*

 * AI Used: Gemini

 * Date Created: 09/18/2026

 * Verified By: Bhavna P

 * How was it verified:
     - Used AI for the addSeat and removeSeat and the generateGridBuilderHTML, Gemini insisted that I needed generateGridBuilderHTML to help with the generation of the seatMap.
     - To verify my methods, I lauched local server @ localhost:7070 and manually tested seat additions and removals for the seatMap, in the perspective of a user, also using bhargavas tests it passed
     - Also made sure that generateGridHTML was displaying the addition and removal of a seat and the reservation and the cancellation of teh reservation

 *   

 */

package com.mycompany.service;

import com.mycompany.model.Airplane;
import com.mycompany.model.Seat;

/**
 * ReservationService --- business logic layer handling seat reservations, updates, layout changes, and HTML grid rendering.
 *
 * @author 227709
 */

public class ReservationService {
    private Airplane airplane; // Internal reference to plane configuration model

    /**
     * Constructs a ReservationService instance with a targeted Airplane instance.
     * 
     * @param airplane Airplane model containing seat grid layout data
     */
    public ReservationService(Airplane airplane) 
    {
        this.airplane = airplane;
    }

    /**
     * Reserves a seat for a passenger by updating occupancy state.
     * 
     * @param seatNumber Target seat identifier code string
     * @param passengerName Name of reserving passenger
     * @return True if seat reservation succeeded, false otherwise
     * @author Samhitha
     */
    // reserves a seat for the use by making the seat ocuupied
    public boolean reserveSeat(String seatNumber, String passengerName) 
    {
        Seat targetSeat = locateSeat(seatNumber); // Resolves string label to matching Seat instance
        if (targetSeat == null || targetSeat.isOccupied()) 
        {
           return false;
        }
        targetSeat.setPassengerName(passengerName);
        targetSeat.setOccupied(true);
        return true;
    }
    
    /**
     * Cancels an active reservation on a specific seat.
     * 
     * @param seatNumber Target seat identifier code string
     * @return True if reservation cancellation succeeded, false otherwise
     * @author Samhitha
     */
    // unreserves a seat for the use by making the previously reserved seat unocuupied
    public boolean cancelReservation(String seatNumber) 
    {
        Seat targetSeat = locateSeat(seatNumber); // Resolves string label to matching Seat instance
        if (targetSeat == null || !targetSeat.isOccupied()) 
        {
            return false;
        }
        targetSeat.setPassengerName("");
        targetSeat.setOccupied(false);
        return true;
    }

    /**
     * Helper method that locates the seat object within the 2D seat matrix.
     * 
     * @param seatNumber Target seat identifier code string
     * @return Matching Seat object, or null if coordinates are invalid
     * @author Bhavna
     */
    //helper method that locates the seat object
    public Seat locateSeat(String seatNumber) 
    {
        int[] coords = convertSeat(seatNumber); // Array containing row and col matrix index values
        if (coords == null || coords.length < 2) 
        {
            return null;
        }

        int row = coords[0]; // Target matrix row array index integer
        int col = coords[1]; // Target matrix column array index integer
        Seat[][] seatData = airplane.getSeatData(); // Complete 2D matrix array from plane model

        if (row < 0 || row >= seatData.length || col < 0 || col >= seatData[row].length) 
        {
            return null;
        }

        return seatData[row][col];
    }

    /**
     * Updates the passenger name on an existing occupied seat reservation.
     * 
     * @param seatNumber Target seat identifier code string
     * @param newName Replacement passenger name string
     * @return True if update succeeded, false if seat is unreserved or missing
     * @author Bhavna
     */
    //upadates the Passenger name to the assigned seat 
    public boolean updatePassengerName(String seatNumber, String newName) 
    {
        Seat targetSeat = locateSeat(seatNumber); // Resolves string label to matching Seat instance
        if (targetSeat == null || !targetSeat.isOccupied()) 
        {
            return false;
        }
        targetSeat.setPassengerName(newName);
        return true;
    }
    
    /**
     * Converts a seat string label into 0-indexed row/column matrix coordinates.
     * 
     * @param seatNumber Seat label string (e.g., "1A")
     * @return Integer array containing row index at 0 and col index at 1
     * @author Bhargava
     */
    //turns the seat Number into an array with coordinates for the 2d array bc of 0 indexing (e.g. 1A ---> [0,0])
    public int[] convertSeat(String seatNumber) {
        if (seatNumber == null || seatNumber.isEmpty()) 
        {
            return null;
        }

        String rowStr = seatNumber.replaceAll("[^0-9]", ""); // Extracted numeric row component string
        String colStr = seatNumber.replaceAll("[^A-Za-z]", "").toUpperCase(); // Extracted alphabetic column component string

        if (rowStr.isEmpty() || colStr.isEmpty()) return null;

        int row = Integer.parseInt(rowStr) - 1; // Converts numeric row to zero-based matrix row index
        int col = colStr.charAt(0) - 'A'; // Converts column character code to zero-based index

        return new int[]{row, col};
    }

    /**
     * Searches a single array row for a seat matching a specified seat code.
     * 
     * @param seatNumber Target seat string label
     * @param row Single row array of Seat objects
     * @return Column index integer if found, -1 otherwise
     * @author Bhargava
     */
    private int innerLoop(String seatNumber, Seat[] row) {
        if (row == null) return -1;
        for (int i = 0; i < row.length; i++) { // Counter index variable iterating over row array
            if (row[i] != null && row[i].getSeatNumber().equalsIgnoreCase(seatNumber)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Searches the entire seat matrix to find a seat reserved by a specific passenger name.
     * 
     * @param passengerName Passenger name string to query
     * @return Matching assigned Seat object, or null if no active match exists
     * @author Samhitha
     */
    //searches the entire seat map to find the seat that the specific passenger is assigned to 
    
    public Seat findSeatByPassengerName(String passengerName) 
    {
        if (passengerName == null || passengerName.isBlank()) 
        {
            return null;
        }

        Seat[][] seatData = airplane.getSeatData(); // Complete array from plane model
         if (seatData == null) 
         {
             return null;
         }

         for (Seat[] row : seatData) 
         {
                if (row != null) 
                {
                   for (Seat seat : row) 
                   {
                     if (seat != null && seat.isOccupied() && passengerName.equalsIgnoreCase(seat.getPassengerName())) 
                     {
                         return seat; 
                         
                     }
                  }
                }
         }
         return null; 
         
    }
    
    /**
     * Dynamically resizes internal array structures to insert a new Seat instance.
     * 
     * @param seatNumber Designation code string for newly added seat
     * @param seatClass Class category string for new seat
     * @author Bhargava
     */
    //resizes the 2d array to create new seats in the row
    public void addSeat(String seatNumber, String seatClass) 
    {
        int[] seatPlace = convertSeat(seatNumber); // Converted zero-based array index coordinates
        if (seatPlace == null) 
        {
            return;
        }

        int row = seatPlace[0]; // Target array row index
        int col = seatPlace[1]; // Target array column index
        Seat[][] currentData = airplane.getSeatData(); // Current active 2D seat matrix array

        
        if (row >= currentData.length)
        {
            Seat[][] expandedData = new Seat[row + 1][]; // Extended 2D matrix array to hold additional row
            System.arraycopy(currentData, 0, expandedData, 0, currentData.length);
            for (int i = currentData.length; i <= row; i++) 
            {
                expandedData[i] = new Seat[0];
            }
            currentData = expandedData;
        }

        if (col >= currentData[row].length) 
        {
            Seat[] expandedRow = new Seat[col + 1]; // Extended row array to hold additional seat column
            System.arraycopy(currentData[row], 0, expandedRow, 0, currentData[row].length);
            currentData[row] = expandedRow;
        }
        double price = "first class".equalsIgnoreCase(seatClass) ? 500.0 : 150.0; // Base calculated seating cost variable

        currentData[row][col] = new Seat(seatNumber, row + 1, seatClass);
        airplane.setSeatData(currentData);
    }

    /**
     * Removes a seat from internal configuration by setting its matrix element to null.
     * 
     * @param seatNumber Designation code string of seat to remove
     * @author Bhavna
     */
    public void removeSeat(String seatNumber) 
    {
        int[] seatPlace = convertSeat(seatNumber); // Converted zero-based array index coordinates
        if (seatPlace == null) 
        {
            return;
        }

        int row = seatPlace[0]; // Target array row index
        int col = seatPlace[1]; // Target array column index
        Seat[][] currentData = airplane.getSeatData(); // Current active 2D seat matrix array

        if (row < 0 || row >= currentData.length || col < 0 || col >= currentData[row].length) 
        {
            return;
        }

        currentData[row][col] = null;
        airplane.setSeatData(currentData);
    }

    /**
     * Renders full seat matrix into formatted HTML layout tags for frontend rendering.
     * 
     * @return String containing full HTML markup of the active seating layout
     * @author Bhavna
     */
    //gemini insisted that this method was absolutely nessecary for the seatmap
    public String generateSeatGridHTML() 
    {
        StringBuilder htmlBuilder = new StringBuilder(); // String builder accumulator for HTML output
        Seat[][] seatData = airplane.getSeatData(); // Complete 2D matrix array from plane model

        if (seatData == null || seatData.length == 0) 
        {
            return "<p>No seats configured.</p>";
        }

        for (int r = 0; r < seatData.length; r++) { // Row index counter variable
            htmlBuilder.append("<div style=\"display: flex; gap: 8px; justify-content: center; margin-bottom: 8px;\">");
        
            if (seatData[r] != null) {
                for (int c = 0; c < seatData[r].length; c++) { // Column index counter variable
                    Seat seat = seatData[r][c]; // Active seat instance evaluated at matrix coordinate
                    if (seat != null) {
                        String statusClass; // CSS style class string indicating seat reservation state
                        if (seat.isOccupied()) {
                            statusClass = "seat occupied";
                        } else {
                            statusClass = "seat available";
                        }

                        htmlBuilder.append("<div class=\"").append(statusClass).append("\">")
                                   .append(seat.getSeatNumber())
                                   .append("</div>");
                    }
                }
            }

            htmlBuilder.append("</div>");
        }

        return htmlBuilder.toString();
}
  
}