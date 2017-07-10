import java.util.*;

public class TestEquationSolver {
	public static void main(String[] args) {
		EquationSolver obj = new EquationSolver();
		System.out.println(obj.solveEquation("x+5-3+x=6+x-2"));
		System.out.println(obj.solveEquation("2x+3x-6x=x+2"));
		System.out.println(obj.solveEquation("19x-2=30+3x"));
		System.out.println(obj.solveEquation("2x=x"));
		System.out.println(obj.solveEquation("x=2x"));
		System.out.println(obj.solveEquation("x=x"));
		System.out.println(obj.solveEquation("x=x+2"));
		System.out.println(obj.solveEquation("2-x+x+3x=2x-x+x+3"));
		System.out.println(obj.solveEquation("x-10=10-x+x+2x"));
		System.out.println(obj.solveEquation("2-x+x+3x=2x-x+x+3"));
		
		Deque<Character> blah = new ArrayDeque<>();
		blah.push('-');
		blah.push('+');
		blah.push('+');
		blah.pollLast();
		System.out.println(blah);
		
	}
}
