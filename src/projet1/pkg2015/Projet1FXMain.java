package projet1.pkg2015;

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
        Canvas canvas = new Canvas(4000, 4000); // would need to compute canvas size based on window's size
        GraphicsContext gc = canvas.getGraphicsContext2D();        
        
        if(args.length >= 2) {
        	// Pars args
            String path = args[0];
            int n = Integer.parseInt(args[1]);
            
            LSystem lSystem = new LSystem();
            lSystem.schildkrote.setDrawingBoard(gc);
        	LSystem.readJSONFile(path, lSystem, (Turtle) lSystem.schildkrote);
    		lSystem.tell(lSystem.schildkrote, lSystem.getAxiom(), n);
    		
    		// Postscript output
			System.out.println("stroke");
            System.out.println("%%Trailer");
                
        	Group root = new Group();
            root.getChildren().add(canvas);
            
        	
        	Scene scene = new Scene(root, 1600, 800);
        	
        	primaryStage.setTitle("Lindenmayer system");
        	primaryStage.setScene(scene);
        	primaryStage.show();
    	}else {
    		System.out.println("No args!");
    		System.exit(0);
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
