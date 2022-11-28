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
public abstract class Instruction {

	// Node next;
	public abstract String toString();	


	public abstract Register getRd();
}
