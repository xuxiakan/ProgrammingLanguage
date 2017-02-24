package Tokenizer;


/**
 *      CSE 3341 Programming Assignment 01
 *      Main.java
 *      @author Xiakan Xu
 *
 */
public class Main {
    public static void main(String args[]) {

        //Tokenizer tokens = new Tokenizer(args[0]);
        Tokenizer tokens = new Tokenizer("test/validWhitespaceBetweenTokens.txt");
        do{

            System.out.println(tokens.currentToken());
            tokens.nextToken();

        }while(tokens.currentToken()!=33);
        System.out.println(tokens.currentToken());
    }
}
