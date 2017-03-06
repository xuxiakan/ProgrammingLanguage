package Parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import Tokenizer.Tokenizer;

/**
 *  CSE 3341 Programming Assignment 02
 *  IdNode.java
 *  @author Xiakan Xu
 */

class IdNode {
    private String name;
    private int value;
    private boolean initialized;
    private boolean declared;
    private static Map<String, IdNode> symTab = new HashMap<String, IdNode>();

    private IdNode(String n){
        this.name = n;
        this.initialized = false;
        this.declared = false;
    }

    protected static IdNode parserID(Tokenizer t){

        String tok = t.currentToken;
        if(!t.isValidIdentifier(tok)){
            System.err.println("Parser error(Line " + t.lineNum+"): expect a valid ID, " +
                    "but currentToken is: '" + t.currentToken + "'");
            System.exit(2);
        }
        else if(t.isReservedWords(tok)){
            System.err.println("Parser error(Line " + t.lineNum+"): expect a valid ID, " +
                    "but '" + t.currentToken + "' is a reserved word.");
            System.exit(2);
        }
        t.nextToken();
        if(!symTab.containsKey(tok)){
            IdNode node = new IdNode(tok);
            symTab.put(tok, node);
        }
        return symTab.get(tok);
    }

    protected void setDeclared(){
        this.declared = true;
    }

    protected boolean isDeclared(){
        return this.declared;
    }

    protected  void setValue(int v){
        this.value = v;
        this.initialized = true;
    }

    protected void initialize(){
        if(this.initialized){
            System.err.println("Warning: read a variable who already has a value");
        }
        System.out.print(this.getName() + " =? ");
        Scanner stdin = new Scanner(System.in);

        String temp = stdin.nextLine();
        if(isValidInt(temp)){
            setValue(Integer.parseInt(temp));
        }
        else{
            System.err.println("ID initialize error, should never happen");
            System.exit(99);
        }
    }

    protected int getValue(){
        if(!this.declared){
            System.err.print("Execute error: id : "+ this.getName() + " has not been declared.");
            System.exit(3);
        }
        if(!this.initialized){
            System.err.print("Execute error: id : "+ this.getName() + " has not been assigned a value.");
            System.exit(3);
        }
        return this.value;
    }

    protected  String getName(){
        return this.name;
    }

    protected void printId(){
        System.out.print(getName());
    }
    protected void write(){
        if(!this.declared){
            System.err.print("Execute error: id : "+ this.getName() + " has not been declared.");
            System.exit(3);
        }
        if(!this.initialized){
            System.err.print("Execute error: id : "+ this.getName() + " has not been assigned a value.");
            System.exit(3);
        }
        System.out.println(this.getName() + " = " + this.getValue());
    }

    protected static boolean isValidInt(String token){
        boolean isValid = true;
        Pattern integers = Pattern.compile("[0-9]+");


        // Compare with integers regex pattern
        if(token.length() > 8){
            System.err.print("Execute error: integral cannot exceed 8 characters.");
            System.exit(3);
            isValid = false;
        }
        if(!integers.matcher(token).matches()){
            System.err.print("Execute error: your input is not a valid integral");
            System.exit(3);
            isValid = false;
        }
        return isValid;
    }
}
