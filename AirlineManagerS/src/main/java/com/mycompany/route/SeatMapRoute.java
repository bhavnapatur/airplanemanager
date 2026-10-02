/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * AI Used: Gemini
 * Date Created: 09/18/2026
 * Verified By: Samhitha S
 * How was it verified:
 *   - Ran web application locally in browser
 *   - Used AI for debugging dynamic template replacement
 *   - Verified passenger search routing behavior
 */

package com.mycompany.route;

import com.mycompany.model.Seat;
import com.mycompany.service.ReservationService;
import io.javalin.config.JavalinConfig;

/**
 * SeatMapRoute --- handles web routing for dynamic seat map rendering and passenger search.
 * 
 * @author Samhitha S
 */

public class SeatMapRoute {
    /**
    * Registers endpoints for viewing seat maps and searching passenger seat locations.
    * 
    * @param config Javalin configuration reference
    * @param reservationService Shared reservation service instance
    */
    
    public static void register(JavalinConfig config, ReservationService reservationService) { 

        // Initial Load for SeatMap
        config.routes.get("/SeatMap.html", ctx -> {
            String html = new String(
                SeatMapRoute.class.getResourceAsStream("/ui/SeatMap.html").readAllBytes()
            );

            
            String gridHtml = reservationService.generateSeatGridHTML();

            html = html.replace("{Seating Grid Output}", gridHtml);
            html = html.replace("{{Output}}", "Ready");

            ctx.html(html);
        });        

        // Search Passenger Handler
        config.routes.get("/searchPassenger", ctx -> {         
            String name = ctx.queryParam("name");
            
            String html = new String(                            
                SeatMapRoute.class.getResourceAsStream("/ui/SeatMap.html").readAllBytes()
            );         
            
            String resultText;
            if (name == null || name.trim().isEmpty()) {
                resultText = "Please enter a valid passenger name to search.";
            } else {
                Seat seatLocation = reservationService.findSeatByPassengerName(name);
                if (seatLocation != null) {
                    resultText = "Passenger " + name + " is at seat: " + seatLocation.getSeatNumber();
                } else {
                    resultText = "No reservation found for passenger: " + name;
                }
            }

            String gridHtml = reservationService.generateSeatGridHTML();
            html = html.replace("{Seating Grid Output}", gridHtml);
            html = html.replace("{{Output}}", resultText);          
            
            ctx.html(html);
        });
    }
}