package main;

import Parser.ProgramNode;
import Tokenizer.Tokenizer;

/**
 *      CSE 3341 Programming Assignment 01
 *      Main.java
 *      @author Xiakan Xu
 *
 */
public class Main {
    public static void main(String args[]) {


       /*Tokenizer tokens = new Tokenizer("test/TokenizerTests/invalidSymbolError.txt");
        do{

            System.out.println(tokens.currentToken());
            tokens.nextToken();

        }while(tokens.currentToken()!=33);
        System.out.println(tokens.currentToken());*/

        //Tokenizer tokens = new Tokenizer("main/java/Tokenizer/test1");



        Tokenizer tokens = new Tokenizer("test/ParserTests/test1.txt");
        ProgramNode prog = new ProgramNode();
        prog.parseProgram(tokens);
        prog.printProgram();
        System.out.println("========================");
        prog.execProgram();



/*        File folder = new File("test/TokenizerTests/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().substring(0,5).equals("valid")) {
                System.out.println(file.getName());
                Tokenizer tokens = new Tokenizer(file.getPath());
                do{

                    System.out.println(tokens.currentToken());
                    tokens.nextToken();

                }while(tokens.currentToken()!=33);
                System.out.println(tokens.currentToken());
            }
        }*/

/*
        File folder = new File("test/TokenizerTests/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().substring(0,5).equals("valid")) {
                System.out.println(file.getName());
                Tokenizer tokens = new Tokenizer(file.getPath());
                ProgramNode prog = new ProgramNode();
                prog.parseProgram(tokens);
                prog.printProgram();
            }
        }



/*      ParserTests: All passed
        File folder = new File("test/ParserTests/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                Tokenizer tokens = new Tokenizer(file.getPath());
                do{

                    System.out.println(tokens.currentToken());
                    tokens.nextToken();

                }while(tokens.currentToken()!=33);
                System.out.println(tokens.currentToken());
            }
        }*/

/*        Tokenizer tokens = new Tokenizer(args[0]);
        do{

            System.out.println(tokens.currentToken());
            tokens.nextToken();

        }while(tokens.currentToken()!=33);
        System.out.println(tokens.currentToken());*/
    }
}
