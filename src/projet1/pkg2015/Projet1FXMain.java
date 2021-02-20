/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import java.awt.geom.Point2D;
import java.io.FileReader;
import java.io.IOException;
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
    public void start(Stage primaryStage) throws IOException {
        Point2D pos = new Point2D.Double(0, 0);
        
        Canvas canvas = new Canvas(1800,900);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        LSystem lSystem = new LSystem();
        LSystem.readJSONFile("C:\\Users\\WickkaWizz\\OneDrive\\Desktop\\buisson.JSON", lSystem, (Turtle) lSystem.schildkrote);
        
        /*
        Implement the axiom getter. 
        Run the system with the tell function (in a loop here, or in the function)
        The draw action in the turtle draws in the canvas everytime it is called
        
        */
        System.out.println(lSystem.getAxiom().get(0));
        //bug here, cannot cast the symbol.seq for the list. Will need to check
        lSystem.tell(lSystem.schildkrote, (Symbol.Seq) lSystem.getAxiom(), 0/*lSystem.getAxiom().size()*/);
        
        Group root = new Group();
        
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
