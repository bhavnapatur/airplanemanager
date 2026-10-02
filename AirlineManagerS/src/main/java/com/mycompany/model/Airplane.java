package com.mycompany.model;

/*
 * AI Used: Gemini
 * Date Created: 09/18/2026
 * Verified By: Bhavna P
 * How was it verified:
 *   - Used the AI to help with generating seat codes
 *   - Verified by Bhargava's unit tests of the Airplane constructor
 */

/**
 * Airplane --- model class representing an airplane structure and seat configuration.
 * 
 * @author Bhavna P & Samhitha S
 */
public class Airplane 
{
   private String model; // Stores airplane model name
   private Seat[][] seatData; // 2D array storing seat grid layout

   /**
    * Constructs an Airplane instance and initializes seat map matrix.
    * 
    * @param model Model name of the airplane
    * @param rowCapacities Array defining number of seats per row
    */
   public Airplane(String model, int[] rowCapacities) 
   {
      this.model = model;
      this.seatData = new Seat[rowCapacities.length][];
      
      // Initialize rows and default Seat objects
      for (int r = 0; r < rowCapacities.length; r++) 
      {
         this.seatData[r] = new Seat[rowCapacities[r]];
         for (int c = 0; c < rowCapacities[r]; c++) 
         {
            String seatNumber = (r + 1) + String.valueOf((char) ('A' + c)); // Formats seat identifier
            String seatClass; // Category designation for seat

            if (r < 2) 
            {
               seatClass = "First Class";
            } 
            else 
            {
               seatClass = "Economy";
            }
            
            this.seatData[r][c] = new Seat(seatNumber, r + 1, seatClass);
         }
      }
   }

   /**
    * Accessor for airplane model name.
    * 
    * @return Model name string
    */
   public String getModel() 
   {
      return this.model;
   }

   /**
    * Accessor for seat layout matrix.
    * 
    * @return 2D array of Seat objects
    */
   public Seat[][] getSeatData() 
   {
      return this.seatData;
   }

   /**
    * Mutator for replacing full seat matrix data.
    * 
    * @param newSeatData Replacement 2D seat array
    */
   public void setSeatData(Seat[][] newSeatData)
   {
      this.seatData = newSeatData;
   }
}