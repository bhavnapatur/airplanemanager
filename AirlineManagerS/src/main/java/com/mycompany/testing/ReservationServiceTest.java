/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * AI Used: Gemini
 * Date Created: 09/20/2026
 * Verified By: Bhargava
 * How was it verified:
 *   - Verified test coverage across all ReservationService methods
 *   - Checked branch coverage for success, fail, and null edge cases
 *   - Executed main method to confirm all assertion prints pass
 */
package com.mycompany.testing;

import com.mycompany.model.Airplane;
import com.mycompany.model.Seat;
import com.mycompany.service.ReservationService;

/**
 * ReservationServiceTest --- class to unit test all features of ReservationService.
 * @author Bhargava
 */
public class ReservationServiceTest {

   /**
    * Tests ReservationService operations including reservation, seat lookup, updates, and cancellations.
    * @param args A string array containing the command line arguments.
    * @exception Any exception
    * @return No return value.
    * Author: Bhargava
    */
    public static void main(String[] args) {
        int[] rowCapacities = {2, 2, 4, 4}; // array containing row capacity counts
        Airplane airplane = new Airplane("Boeing 737", rowCapacities); // airplane object under test
        ReservationService service = new ReservationService(airplane); // reservation service instance

        // Test reserveSeat Method
        
        // Normal Case: Reserve available seat
        if (service.reserveSeat("3B", "Alice")) {
            System.out.println("reserveSeat Method - Test 1: PASS");
        } else {
            System.out.println("reserveSeat Method - Test 1: FAIL");
        }

        // Edge Case: Reserve first class seat
        if (service.reserveSeat("1A", "Bob")) {
            System.out.println("reserveSeat Method - Test 2: PASS");
        } else {
            System.out.println("reserveSeat Method - Test 2: FAIL");
        }

        // Failure Case: Reserve already occupied seat
        if (!service.reserveSeat("1A", "Charlie")) {
            System.out.println("reserveSeat Method - Test 3: PASS");
        } else {
            System.out.println("reserveSeat Method - Test 3: FAIL");
        }

        // Failure Case: Invalid seat
        if (!service.reserveSeat("99Z", "David")) {
            System.out.println("reserveSeat Method - Test 4: PASS");
        } else {
            System.out.println("reserveSeat Method - Test 4: FAIL");
        }

        // Test locateSeat Method

        // Normal Case: Locate valid seat
        Seat seat1 = service.locateSeat("3B"); // reference variable for found seat
        if (seat1 != null && "3B".equals(seat1.getSeatNumber())) {
            System.out.println("locateSeat Method - Test 1: PASS");
        } else {
            System.out.println("locateSeat Method - Test 1: FAIL");
        }

        // Failure Case: Locate non-existent seat
        Seat seat2 = service.locateSeat("100F"); // reference variable for missing seat
        if (seat2 == null) {
            System.out.println("locateSeat Method - Test 2: PASS");
        } else {
            System.out.println("locateSeat Method - Test 2: FAIL");
        }

        // Test updatePassengerName Method

        // Normal Case: Update name on reserved seat
        if (service.updatePassengerName("3B", "Alice Smith")) {
            System.out.println("updatePassengerName Method - Test 1: PASS");
        } else {
            System.out.println("updatePassengerName Method - Test 1: FAIL");
        }

        // Failure Case: Update passenger name for unreserved seat
        if (!service.updatePassengerName("4A", "Eve")) {
            System.out.println("updatePassengerName Method - Test 2: PASS");
        } else {
            System.out.println("updatePassengerName Method - Test 2: FAIL");
        }

        // Test findSeatByPassengerName Method

        // Normal Case: Find seat by passenger name
        Seat foundSeat = service.findSeatByPassengerName("Alice Smith"); // reference to seat matched by passenger
        if (foundSeat != null && "3B".equals(foundSeat.getSeatNumber())) {
            System.out.println("findSeatByPassengerName Method - Test 1: PASS");
        } else {
            System.out.println("findSeatByPassengerName Method - Test 1: FAIL");
        }

        // Failure Case: Search for non-existent passenger
        Seat missingSeat = service.findSeatByPassengerName("Unknown Person"); // reference to expected null result
        if (missingSeat == null) {
            System.out.println("findSeatByPassengerName Method - Test 2: PASS");
        } else {
            System.out.println("findSeatByPassengerName Method - Test 2: FAIL");
        }

        // Edge Case: Null name search
        if (service.findSeatByPassengerName(null) == null) {
            System.out.println("findSeatByPassengerName Method - Test 3: PASS");
        } else {
            System.out.println("findSeatByPassengerName Method - Test 3: FAIL");
        }

        // Test cancelReservation Method

        // Normal Case: Cancel existing reservation
        if (service.cancelReservation("3B")) {
            System.out.println("cancelReservation Method - Test 1: PASS");
        } else {
            System.out.println("cancelReservation Method - Test 1: FAIL");
        }

        // Failure Case: Cancel reservation on an already empty seat
        if (!service.cancelReservation("3B")) {
            System.out.println("cancelReservation Method - Test 2: PASS");
        } else {
            System.out.println("cancelReservation Method - Test 2: FAIL");
        }

        // Test convertSeat Method

        // Normal Case: Standard string input
        int[] coords = service.convertSeat("12C"); // stores row and column coordinates
        if (coords != null && coords[0] == 11 && coords[1] == 2) {
            System.out.println("convertSeat Method - Test 1: PASS");
        } else {
            System.out.println("convertSeat Method - Test 1: FAIL");
        }

        // Failure Case: Invalid format
        if (service.convertSeat("Invalid") == null) {
            System.out.println("convertSeat Method - Test 2: PASS");
        } else {
            System.out.println("convertSeat Method - Test 2: FAIL");
        }

        // Edge Case: Null input
        if (service.convertSeat(null) == null) {
            System.out.println("convertSeat Method - Test 3: PASS");
        } else {
            System.out.println("convertSeat Method - Test 3: FAIL");
        }

        // Test addSeat Method

        // Edge Case: Dynamically add seat outside initial array bounds
        service.addSeat("10A", "Economy");
        Seat newlyAdded = service.locateSeat("10A"); // holds seat reference for newly added seat
        if (newlyAdded != null && "10A".equals(newlyAdded.getSeatNumber())) {
            System.out.println("addSeat Method - Test 1: PASS");
        } else {
            System.out.println("addSeat Method - Test 1: FAIL");
        }

        // Test removeSeat Method

        // Normal Case: remove existing seat
        service.removeSeat("10A");
        if (service.locateSeat("10A") == null) {
            System.out.println("removeSeat Method - Test 1: PASS");
        } else {
            System.out.println("removeSeat Method - Test 1: FAIL");
        }
    }
}