/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

import projet1.pkg2015.Interface.AbstractLSystem;
import projet1.pkg2015.Interface.ITurtle;
import projet1.pkg2015.Symbol.Seq;

import java.awt.geom.Rectangle2D;
import java.util.concurrent.ThreadLocalRandom;
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
    ITurtle schildkrote;
    Map<String, Symbol> alphabet; // Dictionary
    Map<Symbol, List<Symbol>> rules;
    Map<Symbol, String> actions;
    List<Symbol> axiom;
    
    
    public LSystem() {
        alphabet = new HashMap<String, Symbol>();
        rules = new HashMap<Symbol, List<Symbol>>();
        actions = new HashMap<Symbol, String>();
        axiom = new ArrayList<Symbol>();
        schildkrote = new Turtle();
    }

    public LSystem(ITurtle schildkrote) {
        alphabet = new HashMap<String, Symbol>();
        rules = new HashMap<Symbol, List<Symbol>>();
        this.schildkrote = schildkrote;
        axiom = new ArrayList<Symbol>();
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

    @Override
    public Symbol addSymbol(char sym) {
        Symbol symbol = new Symbol(sym);
        alphabet.putIfAbsent(symbol.toString(), symbol);
        return symbol;
    }

    @Override
    public void addRule(Symbol sym, String expansion) {
        List<Symbol> sequence = rules.get(sym);
        if(sequence != null){
            for (char c : expansion.toCharArray()) {
                sequence.add(new Symbol(c));                
            }
        }
        // if the rule doesnt exist yet, add it
        else{
            var temp = new ArrayList<Symbol>();
            for (char c : expansion.toCharArray()) {
                temp.add(new Symbol(c));                
            }
            rules.putIfAbsent(sym, temp);
        }
    }

    @Override
    public void setAction(Symbol sym, String action) {
        actions.putIfAbsent(sym, action);
    }

    @Override
    public void setAxiom(String str) {
        for (char c : str.toCharArray())
            axiom.add(new Symbol(c));
    }

    @Override
    public Symbol.Seq getAxiom() {
        return (Symbol.Seq) axiom;
    }

    @Override
    public Symbol.Seq rewrite(Symbol sym) {
        // Find rules
        List<Symbol> rulesList = rules.get(sym);
        int numberOfRules = rulesList.size();
        
        // Randomly select a rule and return it
        int randomNum = ThreadLocalRandom.current().nextInt(0, numberOfRules);
        return (Symbol.Seq) rulesList.get(randomNum);
    }

    @Override
    public void tell(ITurtle turtle, Symbol sym) {
        switch(actions.get(sym)){
            case("draw"): 
                turtle.draw();
                break;
            case("push"):
                turtle.push();
                break;
            case("pop"):
                turtle.pop();
                break;
            case("turnL"):
                turtle.turnL();
                break;
            case("turnR"):
                turtle.turnR();
                break;
        }
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
