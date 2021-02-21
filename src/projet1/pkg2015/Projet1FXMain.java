/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import java.awt.geom.Point2D;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 *
 * @author WickkaWizz
 * @author Arkapin£
 */
public class Projet1FXMain extends Application {
    
    //Not really optimal but does the trick
    private static String[] args;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Point2D pos = new Point2D.Double(0, 0);// never used?
        
        Canvas canvas = new Canvas(1800,900);
        GraphicsContext gc = canvas.getGraphicsContext2D();// never used?
        
        LSystem lSystem = new LSystem();
        
        if(args.length >= 2) {
        	String path = args[0];
        	int n = Integer.parseInt(args[1]);
        	LSystem.readJSONFile(path, lSystem, (Turtle) lSystem.schildkrote);
        	
        	/*
		        Implement the axiom getter. 
		        Run the system with the tell function (in a loop here, or in the function)
		        The draw action in the turtle draws in the canvas everytime it is called
        
        	 */
        	System.out.println(lSystem.getAxiom());
        	lSystem.tell(lSystem.schildkrote, lSystem.getAxiom(), n);
        	
        	Group root = new Group();
        	
        	root.getChildren().add(canvas);
        	
        	Scene scene = new Scene(root, 1800, 900);
        	
        	primaryStage.setTitle("Lindenmayer system");
        	primaryStage.setScene(scene);
        	primaryStage.show();
    	}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	// Not optimal but works
    	Projet1FXMain.args = args;
    	
        launch(args);
    }
    
}
