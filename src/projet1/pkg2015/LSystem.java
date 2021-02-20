/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import projet1.pkg2015.Interface.AbstractLSystem;
import projet1.pkg2015.Interface.ITurtle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author WickkaWizz
 */
public class LSystem extends AbstractLSystem {
    /**
     * constructeur vide monte un système avec alphabet vide et sans règles
     */
    Map<String, Symbol> alphabet; // Dictionary
    Map<Symbol, List<Symbol.Seq>>  rules;
    
    
    public LSystem() {
        alphabet = new HashMap<String, Symbol>();
    }
    
    // Maybe create another class for it
    public static void readJSONFile(String file, LSystem S, Turtle T) throws java.io.IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(file))); // lecture de fichier JSON avec JSONTokener
        JSONArray alphabet = input.getJSONArray("alphabet");
        
        JSONObject rules = input.getJSONObject("rules");
        
        String axiom = input.getString("axiom");
        S.setAxiom(axiom);
        
        for (int i=0; i<alphabet.length(); i++){
            String letter = alphabet.getString(i);
            Symbol sym = S.addSymbol(letter.charAt(0)); // un caractère
        }
        
        
        // Parsing rules        
        // Get array of rules
        JSONArray names = rules.names();
        for(int i = 0; i < names.length(); i++){
            // Get current Symbol
            String symbol = names.getString(i);

            //Get the Symbol's array of rules
            JSONArray ruleArray = rules.getJSONArray(symbol);
            for(int j = 0; j < ruleArray.length(); j++){
                // Add every rules
                S.addRule(new Symbol(symbol.charAt(0)), ruleArray.getString(j));
            }
        }
    }
    
    /* méthodes d'initialisation de système */
    
    //public Symbol addSymbol(char sym) {...}
    //public void addRule(Symbol sym, String expansion) {...}
    //public void setAction(Symbol sym, String action) {...}
    //public void setAxiom(String str){...}
    
    /* accès aux règles et exécution */
    //public Symbol.Seq getAxiom(){ ...}
    //public Symbol.Seq rewrite(Symbol sym) {...}
    //public void tell(Turtle turtle, Symbol sym) {...}
 
    /* opérations avancées */
    //public Symbol.Seq applyRules(Symbol.Seq seq, int n) {...}
    /* retourne BoundingBox pour le dessin */
    //public Rectangle2D tell(Turtle turtle, Symbol.Seq seq, int n){ ...}

    @Override
    public Symbol addSymbol(char sym) {
        Symbol symbol = new Symbol(sym);
        alphabet.putIfAbsent(symbol.toString(), symbol);
        return symbol;
    }

    @Override
    public void addRule(Symbol sym, String expansion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAction(Symbol sym, String action) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAxiom(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Symbol.Seq getAxiom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Symbol.Seq rewrite(Symbol sym) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tell(ITurtle turtle, Symbol sym) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Symbol.Seq applyRules(Symbol.Seq seq, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle2D tell(ITurtle turtle, Symbol sym, int rounds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
