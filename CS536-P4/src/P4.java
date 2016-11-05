import java.io.*;
import java_cup.runtime.*;

/**
 * Main program to test the harambe parser.
 *
 * There should be 2 command-line arguments:
 *    1. the file to be parsed
 *    2. the output file into which the AST built by the parser should be
 *       unparsed
 * The program opens the two files, creates a scanner and a parser, and
 * calls the parser.  If the parse is successful, the AST is unparsed.
 */

public class P4 {
    public static void main(String[] args)
        throws IOException // If Scanner can't find file/can't open.
    { 
        // check for command-line args
        if (args.length != 2) {
            System.err.println("please supply name of file to be parsed " +
			                   "and name of file for unparsed version.");
            System.exit(-1);
        }

        // open input file
        FileReader inFile = null;
        try {
            inFile = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            System.err.println("File " + args[0] + " not found.");
            System.exit(-1);
        }

        // open output file
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(args[1]);
        } catch (FileNotFoundException e) {
            System.err.println("File " + args[1] +
                               " could not be opened for writing.");
            System.exit(-1);
        }

        parser P = new parser(new Yylex(inFile));

        Symbol root = null; // the parser will return a Symbol with the value
                            // is the translation of the root nonterm

        try {
        	// Parsing
            System.out.println("Attempting to parse...");
        	root = P.parse(); 
            System.out.println ("Parse Successful!");
            
                        
            // Name Analysis
            System.out.println("Attempting to analyze names...");
            SymTable stable = new SymTable();
            ((ASTnode) root.value).nameAnalysis(stable);
            
            //Failure
            if(ErrMsg.failed) {
            	System.out.println("Name Analysis Unsuccessful!");
            	System.exit(-1);
            }
            
            //Success
            System.out.println("Name Analysis Successful!");
            ErrMsg.failed = false;          
            
            
            // Type Analysis
            System.out.println("Attempting to analyze types...");
            ((ASTnode)root.value).typeAnalysis();
            
            // Failure
            if(ErrMsg.failed) {
            	System.out.println("Type Analysis Unsuccessful!");
            	System.exit(-1);
            }
            
            // Success
            System.out.println("Type Analysis Successful!");
            ErrMsg.failed = false;          
            
        } catch (Exception e){
        	// Failure
            System.err.println("Exception occured during: " + e);
            e.printStackTrace();
        }
        
        // Unparse
        ((ASTnode)root.value).unparse(outFile, 0);
        outFile.close();

        return;
    }
}
