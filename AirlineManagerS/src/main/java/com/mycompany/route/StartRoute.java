/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * AI Used: Gemini
 * Date Created: 09/18/2026
 * Verified By: Bhavna P
 * How was it verified:
 *   - I used the AI to debug
     - verified by opening the progeam in localhost 7070
 */


package com.mycompany.route;

import io.javalin.config.JavalinConfig;
import java.io.InputStream;
/**
 * StartRoute --- handles web routing for initiating customer reservation sessions.
 * 
 * @author Bhavna P
 */
public class StartRoute {
    /**
     * Registers endpoint for starting a new reservation session.
     * 
     * @param config Javalin configuration reference
     */
    public static void register(JavalinConfig config) {   
        config.routes.post("/startReservation", ctx -> {             
            String passengerName = ctx.formParam("name");
            if (passengerName == null) {
                passengerName = "Guest";
            }
            
            try (InputStream is = StartRoute.class.getResourceAsStream("/ui/SeatMap.html")) {
                if (is == null) {
                    ctx.status(404).result("Template SeatMap.html not found.");
                    return;
                }
                
                String html = new String(is.readAllBytes());
                html = html.replace("{{Name}}", passengerName);            
                
                ctx.html(html);
            }
        });
    }
}