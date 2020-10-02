// Starter code for Project 1

// Change this to your NetId


import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.io.FileNotFoundException;
import java.io.File;


/** Class to store a node of expression tree
    For each internal node, element contains a binary operator
    List of operators: +|*|-|/|%|^
    Other tokens: (|)
    Each leaf node contains an operand (long integer)
*/

public class Expression {
    public enum TokenType {  // NIL is a special token that can be used to mark bottom of stack
	PLUS, TIMES, MINUS, DIV, MOD, POWER, OPEN, CLOSE, NIL, NUMBER
    }
    
    public static class Token {
	TokenType token;
	int priority; // for precedence of operator
	Long number;  // used to store number of token = NUMBER
	String string;

	Token(TokenType op, int pri, String tok) {
	    token = op;
	    priority = pri;
	    number = null;
	    string = tok;
	}

	// Constructor for number.  To be called when other options have been exhausted.
	Token(String tok) {
	    token = TokenType.NUMBER;
	    number = Long.parseLong(tok);
	    string = tok;
	}
	
	boolean isOperand() { return token == TokenType.NUMBER; }

	public long getValue() {
	    return isOperand() ? number : 0;
	}

	public String toString() { return string; }
    }

    Token element;
    Expression left, right;

    // Create token corresponding to a string
    // tok is "+" | "*" | "-" | "/" | "%" | "^" | "(" | ")"| NUMBER
    // NUMBER is either "0" or "[-]?[1-9][0-9]*
    static Token getToken(String tok) {  // To do
	Token result;
	switch(tok) {
	case "+":
	    result = new Token(TokenType.PLUS, 3, "+"); 
	    break;
        case "*":
	    result = new Token(TokenType.TIMES, 2, "*");  
	    break;
        case "-":
	    result = new Token(TokenType.MINUS, 3, "-");  
	    break;
        case "/":
	    result = new Token(TokenType.DIV, 2, "/");  
	    break;
        case "%":
	    result = new Token(TokenType.MOD, 2, "%");  
	    break;
        case "^":
	    result = new Token(TokenType.POWER, 1, "^");  
	    break;
        case "(":
	    result = new Token(TokenType.OPEN, 0, "(");  
	    break;
        case ")":
	    result = new Token(TokenType.CLOSE, 0, ")");  
	    break;
	default:
	    result = new Token(tok);
	    break;
	}
	return result;
    }
    
    private Expression() {
	element = null;
    }
    
    private Expression(Token oper, Expression left, Expression right) {
	this.element = oper;
	this.left = left;
	this.right = right;
    }

    private Expression(Token num) {
	this.element = num;
	this.left = null;
	this.right = null;
    }

    // Given a list of tokens corresponding to an infix expression,
    // return the expression tree corresponding to it.
    public static Expression infixToExpression(List<Token> exp) {  // To do

        
	return null;
    }

    // Given a list of tokens corresponding to an infix expression,
    // return its equivalent postfix expression as a list of tokens.
    public static List<Token> infixToPostfix(List<Token> exp) {  // To do
        Stack<Token> stack = new Stack<>();
        List<Token> postfix = new LinkedList<>();  
        
        for (int i = 0; i < exp.size(); i++)
        {
            Token t = exp.get(i);
         
            // if token is "("
            if (t.token == TokenType.OPEN) 
            {
                stack.push(t); //pushes ( to stack
            }
            
            // if token is ")"
            else if (t.token == TokenType.CLOSE)
            {
                for (int j = 0; j < stack.size(); j++)
                {
                    Token p = stack.pop();
                    
                    // pops everything from stack until "("
                    if (p.token != TokenType.OPEN)
                    {
                        postfix.add(p);
                    }
                }


            }
            
            // if token is operator
            else if (t.priority > 0)
            {
                // if stack is full, check next element and see which has higher priority
                if(stack.isEmpty() == false)
                {
                    Token peek = stack.peek();
                    
                    // if next element in stack is an operator, and has lower priority, add to list
                    if (peek.priority <= t.priority && peek.priority != 0)
                    {
                        postfix.add(stack.pop());
                    }
                }
                stack.push(t);
            }
            else
            {
                postfix.add(t); // if t is a number, add to postfix list
            }
        }
        
        for (int i = 0; i <= stack.size(); i++)
        {
            Token p = stack.pop();
            
            if (p.token != TokenType.OPEN)
            {
             postfix.add(p);
            }
        }

    
        
	return postfix;
    }

    // Given a list of tokens corresponding to an infix expression,
    // return its equivalent postfix expression as a list of tokens. Done by Jade Rodriguez
    public static List<Token> infixToPostfix(List<Token> exp) {  // To do
        Stack<Token> stack = new Stack<>();
        List<Token> postfix = new LinkedList<>();  
        
        for (int i = 0; i < exp.size(); i++)
        {
            Token t = exp.get(i);
         
            // if token is "("
            if (t.token == TokenType.OPEN) 
            {
                stack.push(t); //pushes ( to stack
            }
            
            // if token is ")"
            else if (t.token == TokenType.CLOSE)
            {
                for (int j = 0; j < stack.size(); j++)
                {
                    Token p = stack.pop();
                    
                    // pops everything from stack until "("
                    if (p.token != TokenType.OPEN)
                    {
                        postfix.add(p);
                    }
                }


            }
            
            // if token is operator
            else if (t.priority > 0)
            {
                // if stack is full, check next element and see which has higher priority
                if(stack.isEmpty() == false)
                {
                    Token peek = stack.peek();
                    
                    // if next element in stack is an operator, and has lower priority, add to list
                    if (peek.priority <= t.priority && peek.priority != 0)
                    {
                        postfix.add(stack.pop());
                    }
                }
                stack.push(t);
            }
            else
            {
                postfix.add(t); // if t is a number, add to postfix list
            }
        }
        
        for (int i = 0; i <= stack.size(); i++)
        {
            Token p = stack.pop();
            
            // "(" still in stack, but doesn't add it to list
            if (p.token != TokenType.OPEN)
            {
             postfix.add(p);
            }
        }

    
        
	return postfix;
    }

    // Given a postfix expression, evaluate it and return its value. Done by Jade Rodriguez
    public static long evaluatePostfix(List<Token> exp) {  // To do

        Stack<Long> numStack = new Stack<>();
        long pval = 0;
        
        // loop through the list of tokens
        for (int i = 0; i < exp.size(); i++)
        {
            Token t = exp.get(i);
            
            // if number, push onto stack
            if (t.token == TokenType.NUMBER)
            {
                numStack.push(t.number);
            }
           
            // if operator, pop two numbers from the stack and do operation
            else
            {
                long num2 = numStack.pop();
                long num1 = numStack.pop();
                long result = 1;
                
                // switch case depending on operand, pushes result back onto stack for next operation
                switch(t.string)
		{
                case "+":
                    result = num1 + num2;
                    numStack.push(result);
                    break;
                case "-":
                    result = num1 - num2;
                    numStack.push(result);
                    break;
                case "*":
                    result = num1 * num2;
                    numStack.push(result);
                    break;
                case "/":
                    result = num1 / num2;
                    numStack.push(result);
                    break;
                case "%":
                    result = num1 % num2;
                    numStack.push(result);
                    break;
                case "^":
                    // loop to calculate num1 to the power of num2
                    for (int j = 0; j < num2; j++)
                    {
                        result = result * num1;
                    }
                    numStack.push(result);
                    break;
                default:
                        break;
                    
                }
                
                
            }
        
        
        }
  
        pval = numStack.pop(); // after all operations, final result will be only thing left in the stack
	return pval;
    }

    // Given an expression tree, evaluate it and return its value.
    public static long evaluateExpression(Expression tree) {  // To do
	return 0;
    }

    // sample main program for testing
    public static void main(String[] args) throws FileNotFoundException {
        
	Scanner in;
	System.out.println("Enter Expression: ");
        
	if (args.length > 0) {
	    File inputFile = new File(args[0]);
	    in = new Scanner(inputFile);
	} else {
	    in = new Scanner(System.in);
	}

	int count = 0;
	while(in.hasNext()) {
	    String s = in.nextLine();
	    List<Token> infix = new LinkedList<>();
	    Scanner sscan = new Scanner(s);
	    int len = 0;
	    while(sscan.hasNext()) {
		infix.add(getToken(sscan.next()));
		len++;
	    }
	    if(len > 0) {
		count++;
		System.out.println("Expression number: " + count);
		System.out.println("Infix expression: " + infix);
		Expression exp = infixToExpression(infix);
		List<Token> post = infixToPostfix(infix);
		System.out.println("Postfix expression: " + post);
		long pval = evaluatePostfix(post);
		long eval = evaluateExpression(exp);
		System.out.println("Postfix eval: " + pval + " Exp eval: " + eval + "\n");
	    }
	}
    }
}
