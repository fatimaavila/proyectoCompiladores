/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast.nodes;
import java.util.List;
import java.util.LinkedList;
import compiler.semantic.*;
import compiler.irt.IrtList;
import compiler.irt.instructions.LoadStore;
import compiler.irt.RegisterManager;
import compiler.irt.Register;
/**
 *
 * @author yanet
 */
public class VarLiteral extends Node {
	
	/**
	*	Nombre de variable
	*/
	public String name;

	/**
	*	Dimension del arreglo, en el caso de no ser arreglo este campo sera null
	*/
	public Node dimension;

	/**
	*	Constructor de clase con dimension entera
	*	@param 	name 	nombre de variable
	*	@param 	dim 	dimension del arreglo
	*/
	public VarLiteral(String name, int dim){
		this.name = name;
		this.dimension = new Literal(Integer.toString(dim));
	}

	/**
	*	Constructor de arreglo
	*	@param name 	Nombre de la variable
	*	@param n 		Dimension del arreglo
	*/
	public VarLiteral(String name, Node n){
		this.name = name;
		this.dimension = n;
	}

	/**
	*	Constructor de clase para variables
	*	@param name Nombre de variable
	*/
	public VarLiteral(String name){
		this.name = name;
		this.dimension = null;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		String str = padding + "VarLiteral\n";
		str += "\t" + padding + name + "\n";
		if(dimension != null){
			str += dimension.print(padding + "\t");
		}
		return str;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		if (dimension != null)
			return name + " " + dimension;
		else
			return name;
	}

	/**
	*	Verifica la existencia y unicidad de la variable
	*	@param 	tb 			tabla del scope al que peretenece la operacion
	*	@param 	st 			Listado de todas las tablas
	*	@param 	errorList 	Lista de errores
	*	@return tipo de dato de la variable
	*/
	public String checkVarLiteral(Table tb, SymbolTable st, LinkedList<String> errorList){
		String resultado = "";
		boolean b = false;
		if (tb.containsKey(this.name) == false) {
			Table tableaux = tb;
			Table tableaux2 = null;
			while(!(tableaux.parent.equals("NULL")) && !b){
				for (int i=0; i<st.listaTablas.size(); i++){
					tableaux2 = st.listaTablas.get(i);
					if (tableaux2.name.equals(tableaux.parent)){
						if (tableaux2.containsKey(this.name) == true){
							if (this.dimension != null){
								if (this.dimension instanceof Exp){
									Exp expr2 = (Exp)this.dimension;
									resultado = expr2.checkExp(tb,st,errorList);
								}else if (this.dimension instanceof BinOp){
									BinOp bo2 = (BinOp)this.dimension;
									resultado = bo2.checkBinOp(tb,st,errorList);
								}else if (this.dimension instanceof Literal){
									Literal lit2 = (Literal)this.dimension;
									resultado = lit2.checkLiteral(tb,st);
								}
							}else{
								resultado = tableaux2.get(this.name).tipo;
							}
							i=st.listaTablas.size();
							b = true;
						}else{tableaux = tableaux2;i=st.listaTablas.size();}
					}
				}
			}
		} else {
			if (this.dimension != null){
				if (this.dimension instanceof Exp){
					Exp expr2 = (Exp)this.dimension;
					resultado = expr2.checkExp(tb,st,errorList);
				}else if (this.dimension instanceof BinOp){
					BinOp bo2 = (BinOp)this.dimension;
					resultado = bo2.checkBinOp(tb,st,errorList);
				}else if (this.dimension instanceof Literal){
					Literal lit2 = (Literal)this.dimension;
					resultado = lit2.checkLiteral(tb,st);
				}
			}else {
				resultado = tb.get(this.name).tipo;
			}
		}
		return resultado;
	}
	
	/**
	*	Retorna el nombre de la variable
	*	@return	nombre de variable
	*/
	public String getName() {
		return name;
	}

	/**
	*	Devuelve si la variable es un arreglo
	*	@return true si es arreglo, false si no lo es
	*/
	public boolean isArray(){
		return dimension != null;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel) {
		int nodoActual = i;

		dec.add("n" + ( ++i ) + "[label=\"VarLiteral\"];");
		rel.add("n" + (parent ) + " -> n" + i);

		dec.add("n" + ( ++i ) + "[label=\""+ name +"\"];");
		rel.add("n" + (nodoActual + 1) + " -> n" + i);


		if(dimension != null) {
			i = dimension.getDotTree(parent+1, i, dec, rel);
		}

		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();
		Register rd = RegisterManager.SP;
		// solicitamos la tabla del scope correspondiente
		Table t = symbolTable.searchByName(parent);
		// se obtiene la posicion en el stack de la variable
		int p = t.getPositionVar(symbolTable, name);
		// si no la encontro entonces se busca en las variables globales
		if(p == -1){
			p = t.getPositionVarGlobal(symbolTable, name);
			rd = RegisterManager.GP;
		}
		// se agrega un load word para cargar el valor de la posicion de memoria hacia un registro
		irtList.add(new LoadStore("lw", symbolTable.registerManager.getT(), p, rd));
		return irtList;
	}
}
