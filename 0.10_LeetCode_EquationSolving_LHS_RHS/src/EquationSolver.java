import java.util.*;

public class EquationSolver {
    public String solveEquation(String equation) {
        if(equation == null || equation.length() == 0)
            return "";
        String[] eqSplit = equation.split("=");        
        Integer[] eqSplitLeft = parse(eqSplit[0]); 
        Integer[] eqSplitRight = parse(eqSplit[1]);
        
        System.out.println(Arrays.toString(eqSplitLeft));
        System.out.println(Arrays.toString(eqSplitRight));
        if(eqSplitLeft[0] == eqSplitRight[0] && eqSplitLeft[1] == eqSplitRight[1])
        	return "Infinite solutions";
        if(eqSplitLeft[1] == eqSplitRight[1])
        	return "No solution";
        
        int constLeft = eqSplitLeft[0]- eqSplitRight[0];
        int xRight = eqSplitRight[1] -eqSplitLeft[1];
        
        double result = (float)constLeft / xRight;
        return "x=" + String.valueOf((int)result);
    }
    
    private Integer[] parse(String equation){
        int p = 0;
        Integer c = 0;
        Integer xc = 0;
        Deque<Character> operatorC = new ArrayDeque<>();
        Deque<Integer> numC = new ArrayDeque<>();
        Deque<Character> operatorXC = new ArrayDeque<>();
        Deque<Integer> numXC = new ArrayDeque<>();
        
        while(p < equation.length()){
            char ch = equation.charAt(p);
            if(Character.isDigit(ch) || isSign(ch)){
                boolean signFlag = isSign(ch);
                char sign = ch;
                if(signFlag)
                    ch = equation.charAt(++p);
                
                if(ch == 'x'){
                    numXC.push(1);
                    if(signFlag)
                        operatorXC.push(sign);
                    p++; continue;
                }
                
                StringBuilder num = new StringBuilder();
                num.append(ch);
                while((p < equation.length() -1) && Character.isDigit(equation.charAt(p+1))){
                    ch = equation.charAt(++p);
                    num.append(ch);
                }
                if((p < equation.length() -1) && equation.charAt(p+1) == 'x'){
                    numXC.push(Integer.parseInt(num.toString())); 
                    if(signFlag)
                        operatorXC.push(sign);
                    p++;
                }
                else{
                    numC.push(Integer.parseInt(num.toString())); 
                    if(signFlag)
                        operatorC.push(sign);
                }
            }
            else if(ch == 'x')
                numXC.push(1);            
            p++;
        }
        Integer[] result = new Integer[2];
        result[0] = solve(operatorC, numC);
        result[1] = solve(operatorXC, numXC);
        return result;
    }
    
    private Integer solve(Deque<Character> operator, Deque<Integer> operand){
    	//System.out.println(operator +" "+ operand);
    	if((!operator.isEmpty()) && operator.size() == operand.size()) {
    		operand.addLast(changeSign(operand.pollLast(), operator.pollLast()));
    	}
    	//System.out.println(operator +" "+ operand);
        while(operand.size() > 0){
        	if(operand.size() == 1) {
        		return operand.pop();
        	}
        	// NOTE: stack operations don't work. Need queue operations. front to back solving.
            //operand.push(solve(operand.pop(), operator.pop(), operand.pop()));
        	operand.add(solve(operand.pollLast(), operator.pollLast(), operand.pollLast()));
        
        }
        return 0;
    }
    
    private int changeSign(int x, char sign) {
    	switch(sign) {
    		case '+' : return x;
    		case '-' : return -x;
    		default: return 0;
    	}
    }
    
    private int solve(int x, char op, int y){
        switch(op){
            case '+' : return x+y;
            case '-' : return x-y;
            default : return 0;
        }
    }
    
    private boolean isSign(char ch ){
        return (ch == '+' || ch == '-');
    }
    
}