package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.*;
/**
 *      CSE 3341
 *      Programming Assignment 01
 *      Tokenizer
 */

public class Tokenizer {

    private static Scanner file_scanner;


    // Spiltor including single or multiple space, tab, new line
    private static Pattern SPLITORS = Pattern.compile("\\s+|\\t+|\\n+|\\r+");
    private static String SPECIALSYMBOLS = "[;:,=\\-\\!\\[\\]\\(\\)\\&\\|\\+\\*<>]";

    public Tokenizer(String file_name)
    {
        try{
            file_scanner= new Scanner(new FileReader(file_name));
            if(file_scanner.hasNext()){
                currentLine = file_scanner.nextLine();
                // set current line number to 1
                lineNum = 1;
            }
            else{
                System.out.println("Error: No token loaded. Line: " + lineNum);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // read first token as the current token
        nextToken();
    }

    private static String currentLine;
    private static int lineNum;

    public static String currentToken;

    //TODO: close file at the end
    public static void nextToken(){
        String nextToken = new String();
        if(currentLine.length() == 0){
            currentLine = file_scanner.nextLine();
            // increament current line number by 1
            lineNum++;
        }


        for(int i = 0; i < currentLine.length(); i++){
            char nextChar = currentLine.charAt(i);
            if(SPLITORS.matcher(Character.toString(nextChar))){

            }
        }

        // debug only commented all the time
        file_scanner.close();
    }

    private static String nextElement(){

        return "";
    }

    public static int currentTokenID(){
        return 0;
    }

    public static void main(String args[]) {


        Tokenizer tokens = new Tokenizer("test1");
        System.out.println(tokens.currentToken);

    }
}
