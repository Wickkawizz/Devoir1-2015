/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1.pkg2015;

/**
 *
 * @author WickkaWizz
 */

/**
 * Symbol in an L-system's alphabet. 
 * 
 * @author Mikl&oacute;s Cs&#369;r&ouml;s
 */
public class Symbol
{
    private final char value;
    
    public Symbol(char c)
    {
        this.value = c;
    }
    
    @Override
    public String toString()
    {
        return Character.toString(value);
    }

    // Needed to make HashMap functions work
    @Override
    public int hashCode(){
        return value; // implicit char to int conversion
    }
    @Override
    public boolean equals(Object s){
        if(s == null)
            return false;
        else
            return s.toString().equals(this.toString());
    }
    
    /**
     * Common interface to a string of symbols. 
     * 
     */
    public interface Seq extends Iterable<Symbol>
    {}    
}
