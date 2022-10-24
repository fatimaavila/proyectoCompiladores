package compiler.ast;

import compiler.ast.nodes.*;
import java.util.List; 
import java.util.LinkedList; 

public class Graph {
	private String header;
	private LinkedList<String> definiciones;
	private LinkedList<String> relacion;

	private Root root;

	public Graph(Root root) {
		header = 	"";
		definiciones	= new LinkedList<String>();
		relacion 		= new LinkedList<String>();
		this.root = root;
		

	}

	public String build(){
		String result = "";
		root.getDotTree(0, 0, definiciones, relacion);
		// System.out.println(definiciones);
		for ( String s : definiciones) {
			result += s + "\n";
		}
		result += "\n";
		for ( String s : relacion) {
			result += s + "\n";
		}
		// System.out.println(relacion);
		return result;
	}
}