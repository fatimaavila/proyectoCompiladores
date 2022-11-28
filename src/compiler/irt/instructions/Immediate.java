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
public class Immediate extends Instruction {
	
	protected int immediate;

	public Immediate(int i) {
		immediate = i;
	}


	public Register getRd() {
		return null;
	}

	/**
	*	{@inheritDoc}
	*/
	public String toString() {
		return null;
	}
}
