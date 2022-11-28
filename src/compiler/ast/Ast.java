/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast;
import compiler.parser.CC4Parser;
import compiler.lib.Debug;
import compiler.lib.OutputFile;
import compiler.ast.nodes.Root;
import java.util.List;
import java.util.LinkedList;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import compiler.parser.DecafParser;
import compiler.parser.DecafParserBaseVisitor;
/**
 *
 * @author yanet
 */
public class Ast {
	
	public Debug debug;
	/**
	*	Archivo de salida del programa
	*/
	public OutputFile of;

	/**
	*	Parser
	*/
	public CC4Parser parser;

	/**
	*	Raiz del AST
	*/
	public Root root;

	/**
	*	Constructor de clase
	*	@param parser CC4Parser necesario para obtener el arbol de parseo
	*/
	public Ast(CC4Parser parser) throws Exception {
		this.parser = parser;
		AstVisitor visitor = new AstVisitor();
		this.root = (Root)visitor.visit(parser.tree);
		of = parser.getOutFile();
	}	

	/**
	*	Se establece el Debuger para la fase
	*	@param d debuger
	*/
	public void setDebuger(Debug d){
		debug = d;
		debug.println("debugging: Ast");
	}

	/**
	*	Retorna el archivo de salida
	*/
	public OutputFile getOutFile(){
		return of;
	}

	/**
	*	Ejecuta la fase y se despliega el arbol de parseo
	*/
	public void start() {
		try {
			String msg = "stage: Ast";
			if(debug != null) 
				debug.println(msg);
			of.writeln(msg);
			if(root != null){
				String strTree = this.root.print();
				if(debug != null)
					debug.println(strTree);
				of.writeln(strTree);
			
				GraphicTree gt = new GraphicTree(root);
				String dotRepresentation = gt.build();
				of.writeln(dotRepresentation);
			}
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.err.println("ArrayIndexOutOfBoundsException");
			aiobe.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception");
			e.printStackTrace();
		} 
  	}
	
}