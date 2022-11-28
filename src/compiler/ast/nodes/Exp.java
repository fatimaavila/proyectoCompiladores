/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast.nodes;
import java.util.List;
import java.util.LinkedList;
import compiler.semantic.*;
import compiler.irt.IrtList;
/**
 *
 * @author yanet
 */
public class Exp extends Node{
	/** 
	*	location or method_call 
	*/
	public Node expr;
	
	/**
	*	 MINUS or NEGATION del expr 
	*/
	public String exprModifier;

	/** 
	*	indica si la expresion se encuentra dentro de parentecis 
	*/
	public boolean parentesis;
	
	/**
	*	Constructor de clase general
	*	@param s 			Minus or negation para expr
	*	@param parentesis	indica si la expresion se encuentra dentro de parentecis
	*	@param expr 		expresion
	*/
	public Exp(String s, boolean parentesis, Node expr) {
		this.expr = expr;
		this.exprModifier = s;
		this.parentesis = parentesis;
	}
	
	/**
	*	Constructor de clase para expresiones con modificador minus or negation
	*	@param s 			Minus or negation para expr
	*	@param expr 		expresion
	*/
	public Exp(String s, Node expr) {
		this(s, false, expr);
	}

	/**
	*	Constructor de clase para expresiones entre parentecis
	*	@param parentesis	indica si la expresion se encuentra dentro de parentecis
	*	@param expr 		expresion
	*/
	public Exp(boolean parentesis, Node expr) {
		this("", false, expr);
	}

	/**
	*	Constructor de clase para solo expresion
	*	@param expr 		expresion
	*/
	public Exp(Node expr){
		this("", false, expr);
	}
	
	/**
	*	Chequea los tipos y existencia de variables  de la operación
	*	@param 	tb 			tabla del scope al que peretenece
	*	@param 	st 			Listado de todas las tablas
	*	@param 	errorList 	Lista de errores
	*	@return tipo de dato resultante de la expresion
	*/
	public String checkExp(Table tb, SymbolTable st, LinkedList<String> errorList){
		String str = "";
		if (this.exprModifier.equals("")){
			if (this.expr instanceof VarLiteral){
				VarLiteral v = (VarLiteral)this.expr;
				str = v.checkVarLiteral(tb,st,errorList);
			}else if (this.expr instanceof MethodCall){
				MethodCall llamada = (MethodCall)this.expr;
				str = llamada.checkMethodCall(tb,st,errorList);
			}
		}else if (this.exprModifier.equals("!")){
			if (this.expr instanceof Exp){
				Exp ex = (Exp)this.expr;
				str = ex.checkExp(tb,st,errorList);
			}else if (this.expr instanceof BinOp){
				BinOp bo = (BinOp)this.expr;
				str = bo.checkBinOp(tb,st,errorList);
			}else if (this.expr instanceof Literal){
				Literal lit = (Literal)this.expr;
				str = lit.checkLiteral(tb,st);
			}
			if (!str.equals("boolean")){
				str = "error";
				errorList.add("la expresion de \"!\" debe ser boolean");
			}
		}else if (this.exprModifier.equals("-")){
			if (this.expr instanceof Exp){
				Exp ex = (Exp)this.expr;
				str = ex.checkExp(tb,st,errorList);
			}else if (this.expr instanceof BinOp){
				BinOp bo = (BinOp)this.expr;
				str = bo.checkBinOp(tb,st,errorList);
			}else if (this.expr instanceof Literal){
				Literal lit = (Literal)this.expr;
				str = lit.checkLiteral(tb,st);
			}
			if (!str.equals("int")){
				str = "error";
				errorList.add("la expresion de \"-\" debe ser int");
			}
		}
		return str;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		String str = "";
		if(!(exprModifier.equals(""))){
			str = padding + exprModifier + "\n";
		}
		if(parentesis) {
			str += '(';
		}
		if(expr != null) {
			str += expr.print(padding);
		}
		if(parentesis) {
			str += ')';
		}
		return str;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		return "Exp";
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel){
		int nodoActual = i;

		// dec.add("n" + ( ++i ) + "[label=\"Expresion\"];");
		// rel.add("n" + nodoActual + " -> n" + i);		
		if(exprModifier.equals("")){
			// dec.add("n" + ( ++i ) + "[label=\"Expresion\"];");
			// rel.add("n" + nodoActual + " -> n" + i);		
			i = expr.getDotTree(parent, i, dec, rel);
		}else{
			dec.add("n" + ( ++i ) + "[label=\"" + exprModifier + "\"];");
			rel.add("n" + nodoActual + " -> n" + i);		
			// dec.add("n" + ( ++i ) + "[label=\"Expresion\"];");
			// rel.add("n" + nodoActual + " -> n" + i);		
			i = expr.getDotTree(i, i, dec, rel);
		}
		
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();
		irtList.add(expr.destruct(parent, symbolTable));
		return irtList;
	}
} 
