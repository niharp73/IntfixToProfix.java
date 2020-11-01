import java.util.Scanner;
import java.util.Stack;

public class InfixToPostfix {

/**
* Checks if the input is operator or not
* @param c input to be checked
* @return true if operator
*/
static boolean isOperator(String c1){
char c = c1.charAt(0);
if(c == '+' || c == '-' || c == '*' || c =='/' || c == '^')
return true;
return false;
}

/**
* Checks if c2 has same or higher precedence than c1
* @param c1 first operator
* @param c2 second operator
* @return true if c2 has same or higher precedence
*/
static boolean checkPrecedence(String s1, String s2){
char c1 = s1.charAt(0);
char c2 = s2.charAt(0);
if((c2 == '+' || c2 == '-') && (c1 == '+' || c1 == '-'))
return true;
else if((c2 == '*' || c2 == '/') && (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/'))
return true;
else if((c2 == '^') && (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/'))
return true;
else
return false;
}

public static boolean valid_paren(String input_str) {
// Declaring a stack
Stack<Character> s = new Stack<Character>();

// Iterating over the entire string.
for (char st : input_str.toCharArray()) {
if("{}[]()".indexOf(st)>=0){
// If the input string contains an opening parenthesis,
// push in on to the stack.
if (st == '(' || st == '{' || st == '[') {
s.push(st);
}
else {
// In the case of valid parentheses, the stack cannot be
// be empty if a closing parenthesis is encountered.
if(s.empty()) {
  
return false;
}
// If the input string contains a closing bracket,
// then pop the corresponding opening parenthesis if
// present.
char top = (Character) s.peek();
if(st == ')' && top == '(' ||
st == '}' && top == '{' ||
st == ']' && top == '['){
s.pop();
}
}
}
}
// Checking the status of the stack to determine the
// validity of the string.
if(s.empty()) {
return true;
}
else{
return false;
}
}
/**
* Converts infix expression to postfix
* @param infix infix expression to be converted
* @return postfix expression
*/

static String[] convert(String[] infix){

System.out.printf("%-8s%-10s%-15s\n", "Input","Stack","Postfix");
String postfix = "" ; //equivalent postfix is empty initially
Stack<String> s = new Stack<>(); //stack to hold symbols
s.push("#"); //symbol to denote end of stack

System.out.printf("%-8s%-10s%-15s\n", "",format(s.toString()),postfix);

for(String i : infix){
String inputSymbol = i; //symbol to be processed
if(isOperator(inputSymbol)){ //if a operator
//repeatedly pops if stack top has same or higher precedence
while(checkPrecedence(inputSymbol, s.peek()))
postfix += s.pop()+" ";
s.push(inputSymbol);
}
else if(inputSymbol == "(")
s.push(inputSymbol); //push if left parenthesis
else if(inputSymbol == ")"){
//repeatedly pops if right parenthesis until left parenthesis is found
while(s.peek() != "(" )
postfix += s.pop();
s.pop();
}
else
postfix += inputSymbol+" ";
System.out.printf("%-8s%-10s%-15s\n", ""+inputSymbol,format(s.toString()),postfix);
}

//pops all elements of stack left
while(s.peek() != "#"){
postfix += s.pop()+" ";
System.out.printf("%-8s%-10s%-15s\n", "",format(s.toString()),postfix);

}
  
return postfix.split(" ");
}

// Method to evaluate value of a postfix expression
static int evaluatePostfix(String[] postfix)
{
//create a stack
Stack<Integer> stack=new Stack<>();
  
// Scan all characters one by one
for(String c : postfix)
{
  
System.out.println(c);
// If the scanned character is an operand (number here),
// push it to the stack.
if(c.chars().allMatch( Character::isDigit ))
stack.push(Integer.parseInt(c));
  
// If the scanned character is an operator, pop two
// elements from stack apply the operator
else
{
int val1 = stack.pop();
int val2 = stack.pop();
  
switch(c)
{
case "+":
stack.push(val2+val1);
break;
  
case "-":
stack.push(val2- val1);
break;
  
case "/":
stack.push(val2/val1);
break;
  
case "*":
stack.push(val2*val1);
break;
}
}
}
return stack.pop();   
}

/**
* Formats the input stack string
* @param s It is a stack converted to string
* @return formatted input
*/
static String format(String s){
s = s.replaceAll(", "," "); //removes all , in stack string

s = s.substring(1, s.length()-1); //removes [] from stack string
  
return s;
}

public static void main(String[] args) {
  
InfixToPostfix obj = new InfixToPostfix();
  
String infix = "( 100 + 200 ) ";
  
if (obj.valid_paren(infix)){
String[] infixarr = infix.split(" ");
System.out.println(infixarr);
String[] postfix = obj.convert(infixarr);

System.out.println("Postfix: ");
for (String i : postfix){
System.out.print(i+" ");
}
int postvalue = obj.evaluatePostfix(postfix);
System.out.print(postvalue);

  
}
  
else{
System.out.print(infix);
}
}
}
