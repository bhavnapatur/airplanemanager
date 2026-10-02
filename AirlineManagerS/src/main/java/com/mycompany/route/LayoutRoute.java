package com.mycompany.route;

/*
 * AI Used: Gemini
 * Date Created: 09/18/2026
 * Verified By: Samhitha 
 * How was it verified:
 *   - Used the AI to debug form action handlers
 *   - Verified by opening the program on local server port
 *   - Tested adding and removing seats to ensure proper redirect behavior
 */

import com.mycompany.service.ReservationService;
import io.javalin.config.JavalinConfig;

/**
 * LayoutRoute --- handles HTTP POST routes for managing layout modification requests.
 * 
 * @author Samhitha
 */
public class LayoutRoute 
{
   /**
    * Registers layout management routes for adding and removing seats.
    * 
    * @param config Javalin configuration reference
    * @param reservationService Shared reservation service instance
    */
   public static void register(JavalinConfig config, ReservationService reservationService) 
   {
      // Post endpoint handler for adding a seat to the layout
      config.routes.post("/addSeat", ctx -> {
         String seatNum = ctx.formParam("seatNum"); // Stores seat number input from form
         String seatClass = ctx.formParam("class"); // Stores seat class input from form

         reservationService.addSeat(seatNum, seatClass);

         ctx.redirect("/SeatMap.html");
      });

      // Post endpoint handler for removing a seat from the layout
      config.routes.post("/removeSeat", ctx -> {
         String seatNum = ctx.formParam("seatNum"); // Stores target seat number to remove

         reservationService.removeSeat(seatNum);

         ctx.redirect("/SeatMap.html");
      });
   }
}