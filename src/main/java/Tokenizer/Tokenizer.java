package Tokenizer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.lang.*;
/**
 *      CSE 3341 Programming Assignment 01
 *      Tokenizer.java
 *      @author Xiakan Xu
 *
 *      The program implemented a tokenizer class, which reads tokens from the input file and
 *      output corresponding token IDs
 */

public class Tokenizer {

    /**input file streaming*/
    private static Scanner file_scanner;
    /**lineNum indicate current position of input file, used when print out error message*/
    public static int lineNum;
    /**Tokenizer read input stream line by line*/
    private static String currentLine;
    /**representation of current token*/
    public static String currentToken;

    /**
     * Constructor Tokenizer object from {@code file_name}
     * and set up first token as {@code currentToken}
     *
     * @param file_name
     *            {@code String} to read tokens from
     * @requires <pre> file_name must exist
     */
    public Tokenizer(String file_name)
    {
        // clean out memory
        this.currentToken = null;
        this.currentLine = null;
        lineNum = 0;

        // set up input stream from the file.
        try{
            file_scanner= new Scanner(new FileReader(file_name));
            if(file_scanner.hasNext()){
                currentLine = file_scanner.nextLine();
                // set current line number to 1
                lineNum = 1;
            }
            else{
                System.err.println("Error: No token loaded. Line: " + lineNum);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // read first token as the current token
        nextToken();
    }

    /**
     * This method make next available token as {@code currentToken}
     *  If any illegal token detected, the method will display the error message and {@code lineNum}.
     *
     * @requires <pre> currentToken is not EOF, although the method will handle this case
     */
    public static void nextToken(){

        // case: currentToken is EOF, no next token exist and error message will be displayed.
        if(currentToken!=null && currentToken.equals("EOF")){
            System.out.print("Tokenizer error(Line " + lineNum + "): nextToken cannot be called at the end of file");
            return;
        }

        String nextToken = new String();
        int numOfBeginningSpaces = 0;

        // When the current line is empty or all white spaces, read next line
        while(currentLine.replaceAll("\\s","").length() == 0){
            if(file_scanner.hasNext()){
                currentLine = file_scanner.nextLine();
                // increment current line number by 1
                lineNum++;
            }
            else if(currentToken.equals("end")){
                currentToken = "EOF";
                // read last token from file, close input stream
                file_scanner.close();
                return;
            }
            else{
                System.err.println("Debug: impossible case in Tokenizer reached.");
                currentToken = "EOF";
                // read last token from file, close input stream
                file_scanner.close();
                return;
            }
        }

        // Read character by character from current line
        for(int i = 0; i < currentLine.length(); i++){
            char nextChar = currentLine.charAt(i);

            // case: next character is a space or new line mark.
            if(Character.isWhitespace(nextChar)){
                if(nextToken.length() == 0){
                    // case: next character is a leading space
                    //      count number of leading spaces of current line
                    numOfBeginningSpaces++;
                }
                else{
                    // case: next character is a following space
                    break;
                }
            }
            else if(isSpecialSymbols(Character.toString(nextChar))){
                if(nextToken.length() == 0){
                    // case: nextToken is a single character special symbol
                    nextToken += nextChar;
                }
                else if(nextChar=='=' && (nextToken.equals("!") || nextToken.equals("=")||nextToken.equals(">")||nextToken.equals("<"))){
                    // case: nextToken is a  special symbol with two characters
                    nextToken += nextChar;
                }
                else{
                    // case: nextToken ends with a special symbol
                    break;
                }

            }
            // case: nextToken is a special symbol but ends up with a non-special symbol
            else if(isSpecialSymbols(nextToken)) {
                break;
            }
            else{
                // case: current character belongs to nextToken, keep reading next character.
                nextToken += nextChar;
            }
        }

        // check nextToken is legal, if not, output error message.
        if(!isSpecialSymbols(nextToken)&&!isReservedWords(nextToken)&&!isValidIdentifier(nextToken)&&!isValidInt(nextToken)){
            //System.out.println("Check currentToken is :" + currentToken);
            System.err.println("Tokenizer error(Line " + lineNum + "): unidentified token \"" + nextToken + "\" found.");
            System.exit(1);
        }

        // set nextToken as current token.
        currentToken = nextToken;

        // delete leading spaces and currentToken from currentLine
        currentLine = currentLine.substring(currentToken.length()+numOfBeginningSpaces);
    }

    /**
     * This method transfer currentToken into currentTokenID
     * based on the requirement.
     *
     * @return int token ID based on {@code currentToken}
     */
    public static int currentToken(){
        int tokenID = -1;
        if(isReservedWords(currentToken)){
            switch(currentToken) {
                case "program":
                    tokenID = 1;
                    break;
                case "begin":
                    tokenID = 2;
                    break;
                case "end":
                    tokenID = 3;
                    break;
                case "int":
                    tokenID = 4;
                    break;
                case "if":
                    tokenID = 5;
                    break;
                case "then":
                    tokenID = 6;
                    break;
                case "else":
                    tokenID = 7;
                    break;
                case "while":
                    tokenID = 8;
                    break;
                case "loop":
                    tokenID = 9;
                    break;
                case "read":
                    tokenID = 10;
                    break;
                case "write":
                    tokenID = 11;
                    break;
                case "and":
                    tokenID = 12;
                    break;
                case "or":
                    tokenID = 13;
                    break;
            }
        }
        else if(isSpecialSymbols(currentToken)){
            switch(currentToken) {
                case ";":
                    tokenID = 14;
                    break;
                case ",":
                    tokenID = 15;
                    break;
                case "=":
                    tokenID = 16;
                    break;
                case "!":
                    tokenID = 17;
                    break;
                case "[":
                    tokenID = 18;
                    break;
                case "]":
                    tokenID = 19;
                    break;
                case "(":
                    tokenID = 20;
                    break;
                case ")":
                    tokenID = 21;
                    break;
                case "+":
                    tokenID = 22;
                    break;
                case "-":
                    tokenID = 23;
                    break;
                case "*":
                    tokenID = 24;
                    break;
                case "!=":
                    tokenID = 25;
                    break;
                case "==":
                    tokenID = 26;
                    break;
                case ">=":
                    tokenID = 27;
                    break;
                case "<=":
                    tokenID = 28;
                    break;
                case "<":
                    tokenID = 29;
                    break;
                case ">":
                    tokenID = 30;
                    break;
            }
        }
        else if(isValidInt(currentToken)){
            tokenID = 31;
        }
        else if(isValidIdentifier(currentToken)){
            tokenID = 32;
        }
        else if(currentToken=="EOF"){
            tokenID = 33;
        }
        else{
            // Normally should never happened.
            System.out.println("Error, unidentified token ID");
            System.exit(1);
        }
        return tokenID;
    }

    /**
     * This method exams {@code token} is a case-insensitive reserved word or not,
     *  where reserved word defined one of
     *      program, begin, end, int, if, then, else, while, loop, read, write, and, or
     *
     * @param token
     *          token to be tested.
     * @return boolean
     *          return true if {@code token} is a reserved word,
     *          otherwise, return false.
     */
    public static boolean isReservedWords(String token){
        boolean isValid = true;
        Pattern rw = Pattern.compile("program|begin|end|int|if|then|else|while|loop|read|write|and|or");


        // Compare with reserved words regex pattern
        if(!rw.matcher(token.toLowerCase()).matches()){
            isValid = false;
        }
        return isValid;
    }

    /**
     * This method exams {@code token} is a special symbol or not,
     *  where reserved word defined one of  ; , = ! [ ] ( ) + - * != == >= <= > <
     *
     * @param token
     *         token to be tested.
     * @return boolean
     *          return true if {@code token} is a special symbol,
     *          otherwise, return false.
     */
    public static boolean isSpecialSymbols(String token){
        boolean isValid = true;
        Pattern specialSymbols = Pattern.compile(";|,|=|!|\\[|\\]|\\(|\\)|\\+|-|\\*|!=|==|>=|<=|>|<");

        // Compare with special symbols regex pattern
        if(token.length() < 1){
            isValid = false;
        }
        else if(!specialSymbols.matcher(token).matches()){
            isValid = false;
        }
        return isValid;
    }

    /**
     * This method exams {@code token} follows convention of integers or not,
     *  where integers define as unsigned integers, maximum size of 8 digits long
     *
     * @param token
     *          token to be tested.
     * @return boolean
     *          return true if {@code token} follows convention of integers,
     *          otherwise, return false.
     */
    public static boolean isValidInt(String token){
        boolean isValid = true;
        Pattern integers = Pattern.compile("[0-9]+");


        // Compare with integers regex pattern
        if(token.length() > 8){
            //TODO display error message when Int invalid length
            isValid = false;
        }
        if(!integers.matcher(token).matches()){
            isValid = false;
        }
        return isValid;
    }

    /**
     * This method exams {@code token} follows convention of identifiers or not,
     *  where identifiers define as a string Start with an uppercase letter,
     *  followed by zero or more uppercase letters and then zero or more digits,
     *  with a maximum length of 8 characters in an id.
     *
     *
     * @param token
     *          token to be tested.
     * @return boolean
     *          return true if {@code token} follows convention of identifiers,
     *          otherwise, return false.
     */
    public static boolean isValidIdentifier(String token){
        boolean isValid = true;
        Pattern identifiers = Pattern.compile("[A-Z][a-z]*[0-9]*");

        // Identifier must has a max length of 8 chars.
        if(token.length() > 8){
            //TODO display error message when ID invalid length
            isValid = false;
        }
        // Compare with identifier regex pattern
        else if(!identifiers.matcher(token).matches()){
            isValid = false;
        }
        return isValid;
    }

    public static void main(String args[]) {

        // initialize tokenizer object from argument.

        Tokenizer tokens = new Tokenizer(args[0]);

        // keep read and print next token until find EOF token.
        do{

            System.out.println(tokens.currentToken());
            tokens.nextToken();

        }while(tokens.currentToken()!=33);

        // print EOF token
        System.out.println(tokens.currentToken());
    }
}
