import java.util.*;

public class ExpressionEvaluator {
	public Integer evaluate(String input) {
		if(input == null || input.length() == 0)
			throw new IllegalArgumentException("Input cannot be null or empty!");
		return evaluator(input);
	}
	
	private Integer evaluator(String input) {
		
		if(input == null || input.length() == 0)
			return null;
		
		Deque<Integer> operandStack = new ArrayDeque<>();
		Deque<Character> operatorStack = new ArrayDeque<>();
		Integer result = null;                                                 // cannot return an object without initialization
		int start = 0;
		
		while(start < input.length()){
			char ch = input.charAt(start);
			//System.out.println("ch: "+ ch);
			
			if(isClosingScope(ch)){
				handleException();
			}
			else if(isOpeningScope(ch)){
				String acc = accumulatorStr(input, start+1);
				Integer i = evaluator(acc);
				if(i != null)
					operandStack.push(i);
				start= start+acc.length()+1;
			}
			else if(isOperator(ch)){
				while(!operatorStack.isEmpty() && hasPrecedence(ch, operatorStack.peek())){
					evaluateStacks(operatorStack, operandStack, "SINGLE");
				}
				operatorStack.push(ch);
			}
			else{
				Integer i = accumulatorOpr(input, start);
				operandStack.push(i);
				while(start+1 < input.length() && Character.isDigit(input.charAt(start+1)))
					start++;
			}
			start++;
		}
		
		//System.out.println("Operator Stack: " + operatorStack);
		//System.out.println("Operand Stack: " + operandStack);
		
		evaluateStacks(operatorStack, operandStack, "FULL");
		if(operandStack.isEmpty())
			return result;
		//System.out.println(result = operandStack.pop());
		//System.out.println();
		result = operandStack.pop();
		return result;
	}
	
	private void evaluateStacks(Deque<Character> operatorStack, Deque<Integer> operandStack, String type){
		while(!operatorStack.isEmpty() && operandStack.size() > 1){
			Integer result = arithmetic(operandStack.pop(), operandStack.pop(), operatorStack.pop());
			operandStack.push(result);
			if(type.equals("SINGLE"))
				return;
		}
		return;
	}
	
	private Integer arithmetic(Integer b, Integer a, Character op){
		switch(op){
			case '+' : return a + b;
			case '-' : return a - b;
			case '/' : return a / b;
			case '*' : return a * b;
			default : return null;
		}                                                                     // Do not have to add a return statement, as it becomes unreachable.
	}
	
	private boolean hasPrecedence(Character a, Character b){
		if((a == '+' || a == '-') && (b == '*' || b == '/'))
				return true;
		return false;
	}
	
	private boolean isOperator(Character ch){
		return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
	}
	
	private boolean isOpeningScope(Character ch){
		if(ch == '(')
			return true;
		return false;
	}
	
	private boolean isClosingScope(Character ch){
		if(ch == ')')
			return true;
		return false;
	}
	
	private String accumulatorStr(String input, int start){
		Deque<Character> scopeStack = new ArrayDeque<>();
		scopeStack.push('(');
		StringBuilder sb = new StringBuilder();
		
		while(start < input.length()){
			char ch = input.charAt(start);
			if(ch == '(')
				scopeStack.push(ch);
			else if(ch == ')'){
				if(scopeStack.isEmpty())
					handleException();
				scopeStack.pop();
				if(scopeStack.isEmpty())
					return sb.toString();
			}
			sb.append(ch);
			start++;
		}
		handleException();
		return null;
	}
	
	private Integer accumulatorOpr(String input, int start){
		StringBuilder sb = new StringBuilder();
		char ch;
		while(start < input.length() && Character.isDigit(ch = input.charAt(start))){           // can't initialize 'ch' here. can only assign.
			sb.append(ch);
			start++;
		}
		return Integer.parseInt(sb.toString());
	}
	
	private void handleException(){
		System.out.println("The Scope in expression is invalid!");
	}
	
}
