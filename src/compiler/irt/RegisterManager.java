/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.irt;
import java.util.LinkedList;
/**
 *
 * @author yanet
 */
public class RegisterManager {


	public static final Register ZERO 	= new Register("zero");
	public static final Register SP 	= new Register("sp");
	public static final Register GP		= new Register("gp");
	public static final Register FP		= new Register("fp");
	public static final Register RA		= new Register("ra");
	public static final Register V0		= new Register("v", 0);
	
	public boolean s[];
	public boolean t[];

	public RegisterManager() {
		s = new boolean[8];
		t = new boolean[8];
	}

	public Register getS() {
		for (int i = 0;  i < s.length; i++ ) {
			if(!s[i]) {
				s[i] = true;
				return new Register("s", i);
			}
		}
		return null;
	}

	public Register getT() {
		for (int i = 0;  i < s.length; i++ ) {
			if(!t[i]) {
				t[i] = true;
				Register r = new Register("t", i);
				return r;
			}
		}
		return null;
	}

	public void returnRegister(Register r) {
		if(r != null) {
			switch (r.getType()) {
				case "s":
					s[r.getNumber()] = false;
					break;
				case "t":
					t[r.getNumber()] = false;
					break;
			}
		} 
	}

	/**
	*	Este metodo retorna una lista con los registros que estan siendo usados
	*	en el momento que es llamado
	*	@return Lista de registros usados en el momento
	*/
	public LinkedList<Register> getUsedRegisters() {
		LinkedList<Register> list = new LinkedList<Register>();
		for (int i = 0; i< t.length; i++) {
			if(t[i])
				list.add(new Register("t", i));
		}
		for (int i = 0; i< s.length; i++) {
			if(s[i])
				list.add(new Register("s", i));
		}
		// list.add(V0);
		return list;
	}

	public void reset() {
		s = new boolean[8];
		t = new boolean[8];
	}

	public void setUsedRegisters(LinkedList<Register> registers){
		reset();
		for (Register r:  registers) {
			switch (r.getType()) {
				case "s":
					s[r.getNumber()] = true;
					break;
				case "t":
					t[r.getNumber()] = true;
					break;
			}
		}

	}


}