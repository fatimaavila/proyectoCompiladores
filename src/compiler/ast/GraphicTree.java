/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast;
import compiler.ast.nodes.*;
import java.util.List; 
import java.util.LinkedList; 
/**
 *
 * @author yanet
 */
public class GraphicTree {
	private String header;
	private LinkedList<String> definiciones;
	private LinkedList<String> relacion;

	private Root root;

	public GraphicTree(Root root) {
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
