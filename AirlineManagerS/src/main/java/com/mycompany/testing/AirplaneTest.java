/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * AI Used: Gemini
 * Date Created: 09/20/2026
 * Verified By: Bhargava
 * How was it verified:
 *   - Checked for complete test case coverage against Airplane model
 *   - Inspected conditional check statements
 *   - Executed main method to confirm all tests pass successfully
 */
package com.mycompany.testing;

import com.mycompany.model.Airplane;
import com.mycompany.model.Seat;

/**
 * AirplaneTest --- class to test the functions and constructors of the Airplane class.
 * @author Bhargava
 */
public class AirplaneTest {

   /**
    * Tests the Airplane class constructors, getter methods, and setter methods.
    * @param args A string array containing the command line arguments.
    * @exception Any exception
    * @return No return value.
    * Author: Bhargava
    */
    public static void main(String[] args) {
        int[] rowCapacities = {2, 2, 4, 4}; // array containing maximum seat capacity per row

        // ==========================================
        // Test Airplane Constructor & Getters
        // ==========================================

        // Normal Case: Constructor creates correct model name
        Airplane airplane = new Airplane("Boeing 737", rowCapacities); // instantiates airplane test object
        
        // Checks if getModel returns the correct model name
        if ("Boeing 737".equals(airplane.getModel())) {
            System.out.println("Airplane Constructor - Test 1: PASS");
        } else {
            System.out.println("Airplane Constructor - Test 1: FAIL");
        }

        // Normal Case: Check layout initialization and dimensions
        Seat[][] seatData = airplane.getSeatData(); // stores 2D matrix of airplane seats
        
        // Validates seat array matrix dimensions match expected layout bounds
        if (seatData != null && seatData.length == 4 && seatData[0].length == 2 && seatData[2].length == 4) {
            System.out.println("Airplane Constructor - Test 2: PASS");
        } else {
            System.out.println("Airplane Constructor - Test 2: FAIL");
        }

        // Edge Case: Check First Class initialization (Row 1, Seat A -> "1A", First Class, $500)
        Seat firstClassSeat = seatData[0][0]; // references target first class seat for verification
        
        // Validates first class seat properties match expectation
        if (firstClassSeat != null && "1A".equals(firstClassSeat.getSeatNumber())&& "First Class".equals(firstClassSeat.getSeatClass())) {
            System.out.println("Airplane Constructor - Test 3: PASS");
        } else {
            System.out.println("Airplane Constructor - Test 3: FAIL");
        }

        // Edge Case: Check Economy initialization (Row 3, Seat A -> "3A", Economy, $150)
        Seat economySeat = seatData[2][0]; // references target economy seat for verification
        
        // Validates economy seat properties match expectation
        if (economySeat != null 
                && "3A".equals(economySeat.getSeatNumber()) 
                && "Economy".equals(economySeat.getSeatClass()) 
                ) {
            System.out.println("Airplane Constructor - Test 4: PASS");
        } else {
            System.out.println("Airplane Constructor - Test 4: FAIL");
        }

        // Test setSeatData Method
       

        // Normal Case: Replace full seat array matrix
        Seat[][] newGrid = new Seat[1][1]; // holds new custom matrix layout for setter testing
        newGrid[0][0] = new Seat("1A", 1, "First Class");
        airplane.setSeatData(newGrid);

        // Verifies airplane object properly overwrites old seat matrix with new grid
        if (airplane.getSeatData().length == 1 && airplane.getSeatData()[0][0] != null) {
            System.out.println("setSeatData Method - Test 1: PASS");
        } else {
            System.out.println("setSeatData Method - Test 1: FAIL");
        }
    }
}