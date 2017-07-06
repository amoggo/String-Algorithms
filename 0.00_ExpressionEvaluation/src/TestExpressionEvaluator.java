
public class TestExpressionEvaluator {
	public static void main(String[] args){
		ExpressionEvaluator obj = new ExpressionEvaluator();
		String exp1 = "1+23*(4/2)-3";
		System.out.println(exp1+ " = "+ obj.evaluate(exp1));
		
		String exp2 = "(1*39+(100/20)/2)";
		System.out.println(exp2+ " = "+ obj.evaluate(exp2));
		
		String exp3 = "(1)";
		System.out.println(exp3+ " = "+ obj.evaluate(exp3));
		
		String exp4 = "()";
		System.out.println(exp4+ " = "+ obj.evaluate(exp4));
		
		//String exp5 = "(1*39+(100/(20)/2)";
		//System.out.println(exp5+ " = "+ obj.evaluate(exp5));
		
		String exp6 = "10+2*6";
		System.out.println(exp6+ " = "+ obj.evaluate(exp6));
		
		String exp7 = "100*2+12";
		System.out.println(exp7+ " = "+ obj.evaluate(exp7));
		
		String exp8 = "100*(2+12)";
		System.out.println(exp8+ " = "+ obj.evaluate(exp8));
		
		String exp9 = "100*(2+12)/14";
		System.out.println(exp9+ " = "+ obj.evaluate(exp9));
		
	}
}
