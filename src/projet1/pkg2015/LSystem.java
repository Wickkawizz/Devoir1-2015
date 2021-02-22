package projet1.pkg2015;

import java.awt.geom.Point2D;
import projet1.pkg2015.Interface.AbstractLSystem;
import projet1.pkg2015.Interface.ITurtle;

import java.awt.geom.Rectangle2D;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author WickkaWizz
 * @author Arkapin£
 */
public class LSystem extends AbstractLSystem {
    Map<Symbol, List<List<Symbol>>> rules;
    Map<String, Symbol> alphabet;
    Map<Symbol, String> actions;
    List<Symbol> axiom;
    ITurtle schildkrote;
    
    
    public LSystem() {
        rules = new HashMap<Symbol, List<List<Symbol>>>();
        alphabet = new HashMap<String, Symbol>();
        actions = new HashMap<Symbol, String>();
        axiom = new ArrayList<Symbol>();
        schildkrote = new Turtle();
    }

    public LSystem(ITurtle schildkrote) {
        rules = new HashMap<Symbol, List<List<Symbol>>>();
        alphabet = new HashMap<String, Symbol>();
        actions = new HashMap<Symbol, String>();
        axiom = new ArrayList<Symbol>();
        this.schildkrote = schildkrote;
    }
    
    
    // Maybe create another class for it
    public static void readJSONFile(String file, LSystem S, Turtle T) throws java.io.IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(file))); // lecture de fichier JSON avec JSONTokener
        JSONArray alphabet = input.getJSONArray("alphabet");
        
        JSONObject rules = input.getJSONObject("rules");
        JSONObject actions = input.getJSONObject("actions");
        JSONObject parameters = input.getJSONObject("parameters");
        
        String axiom = input.getString("axiom");
        S.setAxiom(axiom);
        
        for (int i=0; i<alphabet.length(); i++){
            String letter = alphabet.getString(i);
            S.addSymbol(letter.charAt(0));
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


        // Actions
        JSONArray actionsNames = actions.names();
        for (int i = 0; i < actionsNames.length(); i++) {
            Symbol currentAction = new Symbol(actionsNames.get(i).toString().charAt(0));
            S.actions.putIfAbsent(currentAction, (String) actions.get(currentAction.toString()));
        }
        
        // Parameters
        T.setUnits(Double.valueOf(parameters.get("step").toString()), Double.valueOf(parameters.get("angle").toString()));
        
        Point2D pos = new Point2D.Double(parameters.getJSONArray("start").getDouble(0), parameters.getJSONArray("start").getDouble(1));
        T.init(pos, parameters.getJSONArray("start").getDouble(2));
    }

    @Override
    public Symbol addSymbol(char sym) {
        Symbol symbol = new Symbol(sym);
        alphabet.putIfAbsent(symbol.toString(), symbol);
        return symbol;
    }

    @Override
    public void addRule(Symbol sym, String expansion) {
    	//Fetch current rules for symbol
    	List<List<Symbol>> currentRules = rules.get(sym);
        if(currentRules != null){
        	// If not null, we need to append
        	// Create new List
        	List<Symbol> sList = new ArrayList<Symbol>();
            for (char c : expansion.toCharArray()) {
            	// Fill new list with symbol sequence
            	sList.add(new Symbol(c));
            }
            // Add new rule to the list of rules
            currentRules.add(sList);                
        }
        // if the rule doesn't exist yet, add it
        else{
            List<Symbol> temp = new ArrayList<Symbol>();
            for (char c : expansion.toCharArray()) {
                temp.add(new Symbol(c));                
            }
            List<List<Symbol>> list = new ArrayList<List<Symbol>>();
            list.add(temp);
            rules.putIfAbsent(sym, list);
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
    public List<Symbol> getAxiom() {
        return axiom;
    }

    @Override
    public List<Symbol> rewrite(Symbol sym) {
        // Find rules
        List<List<Symbol>> rulesList = rules.get(sym);
        if(rulesList == null)
        	return null;
        int numberOfRules = rulesList.size();
        
        // Randomly select a rule and return it
        int randomNum = ThreadLocalRandom.current().nextInt(0, numberOfRules);
        return rulesList.get(randomNum);
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
    public List<Symbol> applyRules(List<Symbol> symbols, int n) {
        if(n == 0)
            for (Symbol symbol : symbols)
                tell(schildkrote, symbol);
        else{
            if(symbols.iterator().hasNext())
            	symbols.iterator().remove();
            applyRules(symbols, n - 1);
        }

        return symbols;
    }

    @Override
    public Rectangle2D tell(ITurtle turtle, List<Symbol> symbols, int n) {        
        if(n == 0)
            for (Symbol sym : symbols)
                tell(turtle, sym);
        else{
        	// Can not use foreach here because we modify the List while iterating through it
            for (int i = 0; i < symbols.size(); i++){
            	Symbol sym = symbols.get(i);

                List<Symbol> newSymbols = rewrite(sym);
                if(newSymbols != null) {
                	symbols.remove(i);// Remove only if the rewrite method returned an expression
                	symbols.addAll(i, newSymbols);
                	
                	// Adjust index value
                	i += newSymbols.size() - 1;
                }
                
            }

            tell(turtle, symbols, n - 1);
        }

        return new Rectangle2D.Double(
            0, 
            0, 
            turtle.getPosition().getX(), 
            turtle.getPosition().getY()
        );
    }
    
}
