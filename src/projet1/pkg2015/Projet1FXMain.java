/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
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
        Canvas canvas = new Canvas(1600,800);
        //Point2D pos = new Point2D.Double(0, 0);// pas senser utiliser ca, 
        //car le fichier json le donne en parametre. C'est normal de commencer en haut, mais pas normal de dessiner en dehors
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        
        if(args.length >= 2) {
            String path = /*args[0];*/"./src/json/i.json";
            int n = /*Integer.parseInt(args[1]);*/4; 
            LSystem lSystem = new LSystem();
            //lSystem.schildkrote.setPos(pos);
            lSystem.schildkrote.setDrawingBoard(gc);
        	LSystem.readJSONFile(path, lSystem, (Turtle) lSystem.schildkrote);
        	/*
		        Implement the axiom getter. 
		        Run the system with the tell function (in a loop here, or in the function)
		        The draw action in the turtle draws in the canvas everytime it is called
        
        	 */
    		Rectangle2D boundingBox = lSystem.tell(lSystem.schildkrote, lSystem.getAxiom(), n);
                //canvas.setWidth(rect.getBounds2D().getMaxX() - rect.getBounds().getMinX());
                //canvas.setHeight(rect.getBounds2D().getMaxY()- rect.getBounds().getMinY());
                //System.out.println(rect.getBounds2D().getMaxX() - rect.getMinX());
			System.out.println("stroke");
            System.out.println("%%Trailer");
                
        	Group root = new Group();
            root.getChildren().add(canvas);
        	
        	Scene scene = new Scene(/*vbox*/root, 1600, 800);
        	
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
