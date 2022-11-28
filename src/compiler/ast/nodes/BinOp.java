/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast.nodes;
import java.util.List;
import java.util.LinkedList;
import compiler.semantic.*;
import compiler.irt.IrtList;
import compiler.irt.instructions.Alu;
import compiler.irt.instructions.Instruction;
/**
 *
 * @author yanet
 */
public class BinOp extends Node{
	/*
		BinOp tiene una cantidad definida de nodos hijos
		BinOp puede tener distintos signos
	*/
	/**
	*	Operando 1 
	*/
	public Node hijo1;

	/**
	*	Operacion
	*/
	public String operation;

	/**
	*	Operando 2
	*/
	public Node hijo2;
	
	/**
	*	Constructor que recibe el operador y los operandos
	*/
	public BinOp(String op, Node n1, Node n2){
		// incluir argumentos con el signo y los hijos
		this.hijo1 = n1;
		this.hijo2 = n2;
		this.operation = op;
	}
	
	
	/**
	*	Verificacion de tipos de la operacion
	*	@param tb 			tabla del scope al que peretenece la operacion
	*	@param st 			Listado de todas las tablas
	*	@param errorList 	Lista de errores
	*	@return tipo de dato resultando de la operacion
	*/
	public String checkBinOp(Table tb, SymbolTable st, LinkedList<String> errorList){
		boolean e = true;
		String resultado = "";
		String str = "", str2 = "", str3 = "";
		if (this.hijo1 instanceof BinOp){
			BinOp b = (BinOp)this.hijo1;
			str = b.checkBinOp(tb,st,errorList);
		}else if (this.hijo1 instanceof Literal){
			Literal litr = (Literal)this.hijo1;
			str = litr.checkLiteral(tb,st);
		}else if (this.hijo1 instanceof Exp){
			Exp exps = (Exp)this.hijo1;
			str = exps.checkExp(tb,st,errorList);
		}
		str2 = this.operation;
		if (this.hijo2 instanceof BinOp){
			BinOp b2 = (BinOp)this.hijo2;
			str3 = b2.checkBinOp(tb,st,errorList);
		}else if (this.hijo2 instanceof Literal){
			Literal litr2 = (Literal)this.hijo2;
			str3 = litr2.checkLiteral(tb,st);
		}else if (this.hijo2 instanceof Exp){
			Exp exps2 = (Exp)this.hijo2;
			str3 = exps2.checkExp(tb,st,errorList);
		}
		if (!str.equals("error") && !str3.equals("error")){
		if ((str2.equals("&&") || str2.equals("||")) && ((str.equals("boolean")) && (str3.equals("boolean")))){
			resultado = "boolean";
		}else if ( (str2.equals("==") || str2.equals("!=")) && (((str.equals("boolean")) && (str3.equals("boolean"))) || ((str.equals("int")) && (str3.equals("int")))) ){
			resultado = "boolean";
		}else if ((str2.equals("+") || str2.equals("-") || str2.equals("*") || str2.equals("/") || str2.equals("%") ) && ((str.equals("int")) && (str3.equals("int")))){
			resultado = "int";
		}else if ((str2.equals("<") || str2.equals(">") || str2.equals("<=") || str2.equals(">=") ) && ((str.equals("int")) && (str3.equals("int")))){
			resultado = "boolean";			
		}else if ((str2.equals("&&") || str2.equals("||")) && (((!str.equals("boolean")) || (!str3.equals("boolean"))))){
			resultado = "error";
			errorList.add("Operacion Invalida, los operandos de \"&&\" y \"||\" deben ser boolean");
		}else if ( (str2.equals("==") || str2.equals("!=")) && ((((str.equals("boolean")) && (!str3.equals("boolean")))) || (((str.equals("int")) && (!str3.equals("int"))))) ){
			resultado = "error";
			errorList.add("Operacion Invalida, los operandos de \"==\" y \"!=\" deben ser del mismo tipo");
		}else if ((str2.equals("+") || str2.equals("-") || str2.equals("*") || str2.equals("/") || str2.equals("%") ) && (((!str.equals("int")) || (!str3.equals("int"))))){
			resultado = "error";
			errorList.add("Operacion Invalida, los operandos de \"+\", \"-\", \"*\", \"/\" y \"%\" deben ser int");
		}else if ((str2.equals("<") || str2.equals(">") || str2.equals("<=") || str2.equals(">=") ) && (((!str.equals("int")) || (!str3.equals("int"))))){
			resultado = "error";
			errorList.add("Operacion Invalida, los operandos de \"<\", \">\", \"<=\" y \">=\" deben ser int");	
		}else {
			resultado = "error";
			errorList.add("Operacion Invalida");
		}
		}else {
			resultado = "error";
		}
		return resultado;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		String str = padding + operation + "\n";
		if(hijo1 != null) {
			str += hijo1.print(padding + "\t");
		}
		if(hijo2 != null) {
			str += hijo2.print(padding + "\t");
		}
		return str;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		return "BinOp";
		/*String str = hijo1.toString() + " " + this.operation + " " + hijo2.toString();
		return str;*/
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel){
		int nodoActual = i;

		dec.add("n" + ( ++i ) + "[label=\"" + operation + "\"];");
		rel.add("n" + parent + " -> n" + i);

		i = hijo1.getDotTree(nodoActual + 1, i, dec, rel);
		
		i = hijo2.getDotTree(nodoActual + 1, i, dec, rel);		
		
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();

		// se solicita la lista de instrucciones de cada hijo
		IrtList listHijo1 = hijo1.destruct(parent, symbolTable);
		IrtList listHijo2 = hijo2.destruct(parent, symbolTable);

		Instruction i1 = listHijo1.getTail();
		Instruction i2 = listHijo2.getTail();

		// se verifica si las instrucciones eran operaciones aritmeticas y se retornan los registros
		if (i1 instanceof Alu) {
			Alu alu = (Alu)i1;
			symbolTable.registerManager.returnRegister(alu.getOp1());
			symbolTable.registerManager.returnRegister(alu.getOp2());
		}
		if (i2 instanceof Alu) {
			Alu alu = (Alu)i2;
			symbolTable.registerManager.returnRegister(alu.getOp1());
			symbolTable.registerManager.returnRegister(alu.getOp2());
		}
		String op = "";
		switch(operation) {
			case "+":
				op = Alu.ADD;
				break;
			case "-":
				op = Alu.SUB;
				break;
			case "/":
				op = Alu.DIV;
				break;
			case "%":
				op = Alu.DIV;
				break;
			case "*":
				op = Alu.MULT;
				break;
			case "==":
				op = Alu.EQUAL;
				break;
			case "!=":
				op = Alu.NOTEQUAL;
				break;
			case "&&":
				op = Alu.AND;
				break;
			case "||":
				op = Alu.OR;
				break;
			case "<":
				op = Alu.LESS;
				break;
			case ">":
				op = Alu.GREATER;
				break;
			case "<=":
				op = Alu.LESS_EQUAL;
				break;
			case ">=":
				op = Alu.GREATER_EQUAL;
				break;
			default:
				// System.out.println(operation);
				break;
		}

		irtList.add(listHijo1);
		irtList.add(listHijo2);
		irtList.add(new Alu(op, symbolTable.registerManager.getT(), i1.getRd(), i2.getRd()));
		symbolTable.registerManager.returnRegister(i2.getRd());
		symbolTable.registerManager.returnRegister(i2.getRd());
		return irtList;
	}
} 
