import java.util.ArrayList;
import org.nfunk.jep.*;
import java.math.*;

import net.sourceforge.jeval.*;
import net.sourceforge.jeval.function.*;
import net.sourceforge.jeval.function.FunctionHelper;

import java.util.Scanner;
public class Parser {
	static JEP parser = new JEP();
	static int max = 5;
	static double tol = 0.01;
	static double a =1000;
	static double b = -1000;
	static String myString;
	static double bisect(double c){
		parseVoid(c);
		for (int i = 0; i<100 && !(Math.abs(parser.getValue())<tol); i++){
			c = (a+b)/2;
			double resultC = parseE(c);
			double resultA  = parseE(a);
			if (resultC * resultA < 0) b = c;
			else a = c;
		}
		return c;
	}
	static void parseVoid( double c){
		parser.initSymTab();
		parser.addStandardConstants();
		parser.addStandardFunctions();
		parser.addVariable("x", c);
		parser.parseExpression(myString);
	}
	static double parseE( double c){
		parser.initSymTab();
		parser.addStandardConstants();
		parser.addStandardFunctions();
		parser.addVariable("x", c);
		parser.parseExpression(myString);
		c = parser.getValue();
		return c;
	}
	public static void main(String [ ] args)
	{
		Evaluator eval = new Evaluator();
		Scanner input = new Scanner(System.in);
		System.out.println("Input the equation: ");
		myString = input.nextLine();
  		//myString = "x^5+3*x-10";	
		double root = bisect((a+b)/2);
		System.out.println(root);
		
		/**
		try{
		String result = eval.evaluate("sqrt(10)");
		Function result1 = eval.getFunction("sqrt");//"exp(2*x)+2*x=0"); 
		FunctionResult  fr = result1.execute(eval, "10");
		System.out.println(fr.getResult());
		System.out.println(result);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		**/
	}
}
