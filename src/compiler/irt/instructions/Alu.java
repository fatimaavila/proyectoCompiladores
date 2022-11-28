/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.irt.instructions;
import compiler.irt.Register;
/**
 *
 * @author yanet
 */
public class Alu extends Instruction {

	public static final String ADD 				= "add";
	public static final String SUB 				= "sub";
	public static final String MULT 			= "mul";
	public static final String DIV 				= "div";
	public static final String REM 				= "rem";
	public static final String EQUAL			= "seq";
	public static final String NOTEQUAL			= "sne";

	public static final String OR 				= "or";
	public static final String AND 				= "and";
	public static final String LESS 			= "slt";
	public static final String LESS_EQUAL 		= "sle";
	public static final String GREATER 			= "sgt";
	public static final String GREATER_EQUAL	= "sge";

	protected Register rd;

	protected Register op1;	

	/**
	*	add, mult, div. sub, rem 
	*/
	protected String type;
	
	protected Register op2;	

	/**
	*	
	*/
	public Alu(String type, Register rd, Register op1, Register op2) {
		this.rd 	= rd;
		this.op1 	= op1;
		this.type 	= type;
		this.op2 	= op2;
	}

	public Register getRd() {
		return rd;
	}

	public String getType() {
		return type;
	}

	public Register getOp1() {
		return op1;
	}

	public Register getOp2() {
		return op2;
	}

	/**
	*	{@inheritDoc}
	*/
	public String toString() {
		return getType() + " " + getRd().toString() + " " + getOp1() + " " + getOp2();
	}
}
