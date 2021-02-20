/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import java.io.IOException;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author WickkaWizz
 */
public class Projet12015 {

    /**
     * @param args the command line arguments
     */
    /**/
	public static void main(String[] args) {
		try {
			LSystem s = new LSystem();
			Turtle t = new Turtle();
			LSystem.readJSONFile("./src/i.json", s, t);
			//LSystem.readJSONFile("./src/i.json", new LSystem(), new Turtle());
			
			List<Symbol> symbols = (List<Symbol>) s.getAxiom();
			for (Symbol sym : symbols) {
				s.tell(t, sym);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*public void start(Stage stage){
        //Creating a Group 
      Group root = new Group(); 
         
      //Creating a Scene 
      Scene scene = new Scene(root, 600, 300); 
         
      //Setting title to the scene 
      stage.setTitle("Sample application"); 
         
      //Adding the scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of a scene 
      stage.show(); 
      
      }*/
}
