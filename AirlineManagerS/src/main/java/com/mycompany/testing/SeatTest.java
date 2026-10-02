/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/*
 * AI Used: Gemini
 * Date Created: 09/20/2026
 * Verified By: Bhargava
 * How was it verified:
 *   - Verified test coverage for Seat getters, setters, and constructors
 *   - Visually inspected conditional check statements
 *   - Executed main method to confirm all test cases pass successfully
 */
package com.mycompany.testing;

import com.mycompany.model.Seat;

/**
 * SeatTest --- class to unit test all methods and constructors of the Seat class.
 * @author Bhargava
 */
public class SeatTest {

   /**
    * Tests the constructors, getters, and setters of the Seat class.
    * @param args A string array containing the command line arguments.
    * @exception Any exception
    * @return No return value.
    * Author: Bhargava
    */
    public static void main(String[] args) {

        // Test Seat Constructor & Getters

        // Normal Case
        Seat seat = new Seat("12B", 12, "Economy"); // reference variable for Seat object under test

        // Validates seat number and seat class match constructor inputs
        if ("12B".equals(seat.getSeatNumber()) 
                && "Economy".equals(seat.getSeatClass())) {
            System.out.println("Seat Constructor - Test 1: PASS");
        } else {
            System.out.println("Seat Constructor - Test 1: FAIL");
        }

        // Edge Case: Check initial occupancy state and passenger name
        if (!seat.isOccupied() && "".equals(seat.getPassengerName())) {
            System.out.println("Seat Constructor - Test 2: PASS");
        } else {
            System.out.println("Seat Constructor - Test 2: FAIL");
        }

        // Test setOccupied Method

        // Normal Case: Set occupancy to true
        seat.setOccupied(true);
        if (seat.isOccupied()) {
            System.out.println("setOccupied Method - Test 1: PASS");
        } else {
            System.out.println("setOccupied Method - Test 1: FAIL");
        }

        // Normal Case: Set occupancy back to false
        seat.setOccupied(false);
        if (!seat.isOccupied()) {
            System.out.println("setOccupied Method - Test 2: PASS");
        } else {
            System.out.println("setOccupied Method - Test 2: FAIL");
        }

        // Test setPassengerName Method
        
        // Normal Case: Assign valid passenger name
        seat.setPassengerName("Jane Doe");
        if ("Jane Doe".equals(seat.getPassengerName())) {
            System.out.println("setPassengerName Method - Test 1: PASS");
        } else {
            System.out.println("setPassengerName Method - Test 1: FAIL");
        }

        // Edge Case: Assign empty string to passenger name
        seat.setPassengerName("");
        if ("".equals(seat.getPassengerName())) {
            System.out.println("setPassengerName Method - Test 2: PASS");
        } else {
            System.out.println("setPassengerName Method - Test 2: FAIL");
        }
    }
}