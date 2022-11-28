/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.parser;
import org.antlr.v4.runtime.*;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
/**
 *
 * @author yanet
 */
public class DecafErrorListener extends BaseErrorListener {
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		System.out.println(e);
		System.err.println("line "+line+":"+charPositionInLine+" "+msg);
		// underlineError(recognizer,(Token)offendingSymbol, line, charPositionInLine);
	}

	protected void underlineError(Recognizer recognizer, Token offendingToken, int line, int charPositionInLine) {
		CommonTokenStream tokens = (CommonTokenStream)recognizer.getInputStream();
		String input = tokens.getTokenSource().getInputStream().toString();
		String[] lines = input.split("\n");
		System.out.println(Arrays.toString(lines));
		String errorLine = lines[line - 1];
		System.err.println(errorLine);
		for (int i=0; i<charPositionInLine; i++) 
			System.err.print(" ");
		int start = offendingToken.getStartIndex();
		int stop = offendingToken.getStopIndex();
		if ( start>=0 && stop>=0 ) {
			for (int i=start; i<=stop; i++) 
				System.err.print("^");
		}
		System.err.println();
	}

}
