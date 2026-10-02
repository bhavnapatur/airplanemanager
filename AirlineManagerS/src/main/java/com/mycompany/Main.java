/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany;

import com.mycompany.model.Airplane;
// Removed unused Seat import
import com.mycompany.route.LayoutRoute;
import com.mycompany.route.ReservationRoute;
import com.mycompany.route.SeatMapRoute;
import com.mycompany.route.StartRoute;
import com.mycompany.service.ReservationService;
import com.mycompany.utility.NetworkUtility;
import io.javalin.Javalin;

/**
 * Main --- entry point for launching the Javalin web application and initializing services.
 * @author Samhitha S
 */
public class Main {

   /**
    * Configures, registers endpoints, and starts the server instance.
    * @param args A string array containing the command line arguments.
    * @exception Any exception
    * @return No return value.
    * Author: Samhitha S
    */
    public static void main(String[] args) {
        
        // 1. Initialize airplane models and the shared reservation service
        Airplane airplane = new Airplane("Boeing 737", new int[]{4, 4, 6, 6, 6}); // instantiates target airplane model
        ReservationService reservationService = new ReservationService(airplane); // holds core reservation service logic

        int port = NetworkUtility.findAvailablePort(7070); // port number selected for hosting server         
       
        // Initializes Javalin app configuration and registers static files and routes
        Javalin app = Javalin.create(config -> {                        
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "ui";
            });
            
            // Redirects base path to default landing page
            config.routes.get("/", ctx -> {                            
                ctx.redirect("/Start.html");                            
            });
            
            // 2. Register all route handlers
            StartRoute.register(config);
            ReservationRoute.register(config, reservationService);
            SeatMapRoute.register(config, reservationService);
            
            // IF your LayoutRoute method is `public static void register(JavalinConfig config)`:
            LayoutRoute.register(config,reservationService);  
            
        }).start(port);                        
       
        System.out.println("Server running at http://localhost:" + port);         
       
        // Adds shutdown hook to cleanly stop application upon JVM termination
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down server...");
            app.stop();
        }));
    }    
}