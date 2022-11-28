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
public class Label extends Instruction {

	protected String label;

	public  Label(String l) {
		label = l;
	}


	public Register getRd() {
		return null;
	}

	/**
	*	{@inheritDoc}
	*/
	public String toString() {
		return label + ":";
	}
}
