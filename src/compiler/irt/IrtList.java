/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.irt;
import compiler.irt.instructions.Instruction;
import compiler.irt.instructions.Label;
import compiler.irt.Register;
import java.util.LinkedList;
/**
 *
 * @author yanet
 */
public class IrtList {

	/**
	*	Lista con a la que se añadiran las instrucciones
	*/
	protected LinkedList<Instruction> list;

	public IrtList(){
		list = new LinkedList<Instruction>();
	}

	/**
	*	Agrega un nodo a la lista
	*	@param n Nodo a agregar
	*	@return true si se logro agregar y false si no se logro agregar
	*/
	public boolean add(Instruction n) {
		return list.add(n);
	}

	/**
	*	Agrega una lista al final de esta lista
	*	@param l lista de Nodos de irt
	*	@return true si se logro agregar y false si no se logro agregar
	*/
	public boolean add(IrtList l) {
		return list.addAll(l.getList());
	}

	/**
	*	Retorna el ultimo nodo de la lista
	*	@return Instruction correspondiente a la cola de la lista
	*/
	public Instruction getTail(){
		return  list.getLast();
	}

	/**
	*	Retorna la <code>LinkedList<Instruction></code> asociada al objeto
	*/
	public LinkedList<Instruction> getList() {
		return list;
	}

	@Override
	public String toString() {
		String str = "";
		for ( Instruction i : list ) {
			if(!(i instanceof Label)) {	
				str += "\t";
			}
			str += i + "\n";
		}
		return str;
	}


}
