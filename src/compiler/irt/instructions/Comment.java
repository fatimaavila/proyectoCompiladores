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
public class Comment extends Instruction{
	protected String comment;

	public Comment(String c) {
		comment = c;
	}

	public String toString() {
		return  "# " + comment;
	}

	public Register getRd(){
		return null;
	}
}
