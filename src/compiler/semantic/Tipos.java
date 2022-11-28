/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.semantic;
import java.util.LinkedList;
import compiler.ast.nodes.*;

/**
 *
 * @author yanet
 */
public class Tipos {

	/**
	*	Tipo de metodo o parametro
	*/
	public String tipo;

	/**
	*	Lista de tipos de tipos de parametros del metodo
	*/
	public LinkedList<String> tiposparametros;

	/**
	*	Numero de variable
	*/
	int num = -1;

	/**
	*	Dimension en el caso de los arreglos
	*/
	int dimension;
	
	/**
	*	Constructor de Tipos para metodo recibe dos parametros
	*	@param t 	tipo de valor de retorno del metodo
	*	@param dec 	Lista con tipos de parametros del metodo
	*/
	public Tipos(String t, LinkedList<Declaracion> dec){
		this.tipo = t;
		this.tiposparametros = new LinkedList<String>();
		for (Declaracion d : dec){
			this.tiposparametros.add(d.type);
		}
	}
	
	/**
	*	Constructor de Tipos para variables/arreglos solo recibe tipo de variable
	*	@param t 	tipo de variable
	*	@param dim 	dimension del arreglo
	*/
	public Tipos(String t, int dim) {
		this.tipo = t;
		dimension = dim;
		this.tiposparametros = null;
	}

	public LinkedList<String> getTipoParametros() {
		return tiposparametros;
	}

	public void setNumber(int n) {
		num = n;
	}
	
	public int getNumber() {
		return num;
	}

	/**
	*	Retorna el tamaño del arreglo
	*	@return cantidad de posiciones del arreglo
	*/
	public int getDimension() {
		return dimension;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		String str = "(" + dimension + ")";
		str = str + this.tipo;
		if(this.tiposparametros == null){
			str += " #" + num;
		} else {
			str = str + " - " + this.tiposparametros.toString();
		}
		return str;
	}

}
