/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import java.awt.geom.Point2D;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author WickkaWizz
 */
public class Projet1FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Point2D pos = new Point2D.Double(0, 0);
        
        Canvas canvas = new Canvas(1800,900);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        LSystem lSystem = new LSystem(new Turtle(5, 30, pos, 90, gc));
        
        /*
        Implement the axiom getter. 
        Run the system with the tell function (in a loop here, or in the function)
        The draw action in the turtle draws in the canvas everytime it is called
        
        */
        
        Group root = new Group();
        
        gc.strokeLine(0, 0, 0, 0);
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, 1800, 900);
        
        primaryStage.setTitle("Lindenmayer system");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
