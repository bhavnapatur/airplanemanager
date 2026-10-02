/*
 * AI Used: Gemini
 * Date Created: 09/18/2026
 * Verified By: Bhavna P
 * How was it verified:
 *   - Ran web application locally in browser
 *   - Used AI for debugging reservation routes
 *   - Verified seat submission and cancellation behavior
 */

package com.mycompany.route;

import com.mycompany.service.ReservationService;
import io.javalin.config.JavalinConfig;

/**
 * ReservationRoute --- handles web routing for booking, checking, and canceling seat reservations.
 * 
 * @author Bhavna P
 */
public class ReservationRoute 
{
   /**
    * Registers endpoints for seat submission, availability checking, and cancellation.
    * 
    * @param config Javalin configuration reference
    * @param reservationService Shared reservation service instance
    */
   public static void register(JavalinConfig config, ReservationService reservationService) 
   {
      // Post handler for submitting a new seat reservation
      config.routes.post("/submitSeat", ctx -> {
         String seatNumber = ctx.formParam("seat"); // Selected seat designation from form
         String passengerName = ctx.formParam("name");

         boolean success = reservationService.reserveSeat(seatNumber, passengerName); 

         ctx.redirect("/SeatMap.html");
      });

      // Get handler for rendering availability check page
      config.routes.get("/checkAvailability", ctx -> {
         String seatNum = ctx.queryParam("seatNum");
         
         String html = new String(
            ReservationRoute.class.getResourceAsStream("/ui/RequestSeat.html").readAllBytes()
         ); // Raw template HTML content string
         
         ctx.html(html);
      });

      // Post handler for canceling an existing reservation
      config.routes.post("/cancelSeat", ctx -> {
         String seatNumber = ctx.formParam("seatNumber"); 

         reservationService.cancelReservation(seatNumber);

         ctx.redirect("/SeatMap.html");
      });
   }
}