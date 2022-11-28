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
public class Asign extends Node{

	/**
	*	Identificador de variable
	*/
	public Node id;

	/**
	*	tipo de asignacion (=, -=, +=)
	*/
	public String asig;

	/**
	*	Valor a Asignar este puede ser una expresion o un literal
	*/
	public Node value;
	
	/**
	*	Constructor de clase inicializa los campos
	*/
	public Asign(Node id, String asig, Node value){
		this.id = id;
		this.asig = asig;
		this.value = value;
	}

	/**
	*	Chequea la semantica de la asignacion tipos y existencia de variables
	*	@param tb 			tabla del scope al que peretenece
	*	@param st 			Listado de todas las tablas
	*	@param  errorList	Listado con los errores
	*	@param  isfor		Indicador para saber si la asignacion esta como inicializador de variable de un for
	*/
	public void checkAsign(Table tb, SymbolTable st, LinkedList<String> errorList, int isfor){
		boolean prueba = true;
		String verificacion = "", tipo = "",expArreglo = "";
		if (this.id instanceof VarLiteral){
			VarLiteral var = (VarLiteral)this.id;
			boolean declaracion = false;
			boolean b = false;
			boolean no = false;
			//if (var.dimension == null){
				if (this.value instanceof Exp){
					Exp expr = (Exp)this.value;
					verificacion = expr.checkExp(tb,st,errorList);
				}else if (this.value instanceof Statement){
					Statement stm = (Statement)this.value;
					stm.checkStatement(tb,st,errorList);
				}else if (this.value instanceof BinOp){
					BinOp bo = (BinOp)this.value;
					verificacion = bo.checkBinOp(tb,st,errorList);
				}else if (this.value instanceof Literal){
					Literal lit = (Literal)this.value;
					verificacion = lit.checkLiteral(tb,st);
				}
				if (var.dimension != null){
					if (var.dimension instanceof Exp){
						Exp expr2 = (Exp)var.dimension;
						expArreglo = expr2.checkExp(tb,st,errorList);
					}else if (var.dimension instanceof BinOp){
						BinOp bo2 = (BinOp)var.dimension;
						expArreglo = bo2.checkBinOp(tb,st,errorList);
					}else if (var.dimension instanceof Literal){
						Literal lit2 = (Literal)var.dimension;
						expArreglo = lit2.checkLiteral(tb,st);
					}
				}
			if (tb.containsKey(var.name) == false){
				Table tableaux = tb;
				Table tableaux2 = null;
				while(!(tableaux.parent.equals("NULL")) && !b){
				
					for (int i=0; i<st.listaTablas.size(); i++){
						tableaux2 = st.listaTablas.get(i);
						if (tableaux2.name.equals(tableaux.parent)){
							
							if (tableaux2.containsKey(var.name) == true){
								declaracion = true;
								tipo = tableaux2.get(var.name).tipo;
								if (var.dimension == null){
									if (this.asig.equals("=")){
										if ((!tipo.equals(verificacion))&&(isfor == 0)&&(!verificacion.equals("error"))){
											errorList.add("Error de tipos, int y boolean incompatibles en la asignacion a la variable " + var.name);
										}else if ((!tipo.equals(verificacion))&&(isfor == 0)&&(verificacion.equals("error"))){
											errorList.add("Error de tipos, la variable " + var.name + " de tipo " + tipo + " incompatible con la asignacion");
										}else if((!tipo.equals("int")) && (verificacion.equals("int"))&&(isfor == 1)){
											errorList.add("Error de tipos en la inicializacion del for, boolean = int es invalido");
										}else if((tipo.equals("int")) && (!verificacion.equals("int"))&&(isfor == 1)){
											errorList.add("Error de tipos en la inicializacion del for, la asignacion es invalida ");
										}else if((!tipo.equals("int")) && (!verificacion.equals("int"))&&(isfor == 1)){
											errorList.add("Error de tipos en la inicializacion del for, boolean es invalido");
										}
									}else {
										if (isfor == 0){
											if ((!tipo.equals("int")) || (!verificacion.equals("int"))){
												errorList.add("Error de tipos en una asignacion " + this.asig + ", deben ser ambos de tipo int");
											}
										}else{
											errorList.add(" \"+=\" y \"-=\" invalidos en for");
										}
									}
								}else {
									if (expArreglo.equals("int")){
										if (this.asig.equals("=")){
											if (tipo.equals("int[]")){
												if ((verificacion.equals("int[]")) || (verificacion.equals("boolean[]"))){
													errorList.add("Error de tipos, int y " + verificacion + " incompatibles");
												}else if (!verificacion.equals("int")){
													errorList.add("Error de tipos, int y boolean incompatibles");
												}
											}else if (tipo.equals("boolean[]")){
												if ((verificacion.equals("int[]")) || (verificacion.equals("boolean[]"))){
													errorList.add("Error de tipos, boolean y " + verificacion + " incompatibles");
												}else if (!verificacion.equals("boolean")){
													errorList.add("Error de tipos, boolean y int incompatibles");
												}
											}
										}else {
											if (tipo.equals("int[]")){
												if (!verificacion.equals("int")){
													errorList.add("Error de tipos, una asignacion " + this.asig + " debe ser tipo int");
												}
											}else if (tipo.equals("boolean[]")){
												errorList.add("Error de tipos " + this.asig + " incompatible con boolean");
											}
										}
									}else{
										errorList.add("El indice del arreglo debe ser int en la variable " + var.name);
									}
								}
								i=st.listaTablas.size();
								b = true;
							}else{tableaux = tableaux2;i=st.listaTablas.size();}
							//b=false;}
						}
						//}
					}
				}
			}else{
				declaracion = true;
				tipo = tb.get(var.name).tipo;
				if (var.dimension == null){
					if (this.asig.equals("=")){
						if ((!tipo.equals(verificacion))&&(isfor == 0)&&(!verificacion.equals("error"))){
							errorList.add("Error de tipos, int y boolean incompatibles en la asignacion a la variable " + var.name);
						}else if ((!tipo.equals(verificacion))&&(isfor == 0)&&(verificacion.equals("error"))){
							errorList.add("Error de tipos, la variable " + var.name + " de tipo " + tipo + " incompatible con la asignacion");
						}else if((!tipo.equals("int")) && (verificacion.equals("int"))&&(isfor == 1)){
							errorList.add("Error de tipos en la inicializacion del for, boolean = int es invalido");
						}else if((tipo.equals("int")) && (!verificacion.equals("int"))&&(isfor == 1)){
							errorList.add("Error de tipos en la inicializacion del for, la asignacion es invalida");
						}else if((!tipo.equals("int")) && (!verificacion.equals("int"))&&(isfor == 1)){
							errorList.add("Error de tipos en la inicializacion del for, boolean es invalido");
						}
					}else {
						if (isfor == 0){
							if ((!tipo.equals("int")) || (!verificacion.equals("int"))){
								errorList.add("Error de tipos en una asignacion " + this.asig + ", deben ser ambos de tipo int");
							}
						}else{
							errorList.add(" \"+=\" y \"-=\" invalidos en for");
						}
					}
				}else{
					if (expArreglo.equals("int")){
						if (this.asig.equals("=")){
							if (tipo.equals("int[]")){
								if ((verificacion.equals("int[]")) || (verificacion.equals("boolean[]"))){
									errorList.add("Error de tipos, int y " + verificacion + " incompatibles");
								}else if (!verificacion.equals("int")){
									errorList.add("Error de tipos, int y boolean incompatibles");
								}
							}else if (tipo.equals("boolean[]")){
								if ((verificacion.equals("int[]")) || (verificacion.equals("boolean[]"))){
									errorList.add("Error de tipos, boolean y " + verificacion + " incompatibles");
								}else if (!verificacion.equals("boolean")){
									errorList.add("Error de tipos, boolean y int incompatibles");
								}
							}
						}else {
							if (tipo.equals("int[]")){
								if (!verificacion.equals("int")){
									errorList.add("Error de tipos, una asignacion " + this.asig + " debe ser tipo int");
								}
							}else if (tipo.equals("boolean[]")){
								errorList.add("Error de tipos " + this.asig + " incompatible con boolean");
							}
						}
					}else{
						errorList.add("El indice del arreglo debe ser int en la variable " + var.name);
					}
				}
			}
			if (!declaracion){
				errorList.add(var.name + " no esta declarada");
			}
			//}
			
		}
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		String str = padding + asig+"\n";
		str += id.print(padding + "\t");
		if(value != null){
			str += value.print(padding + "\t");
		}
		return str;
	}
	
	/**
	*	{@inheritDoc} 
	*/
	@Override
	public String toString(){
		return "Asignacion";
	}
	
	/**
	*	{@inheritDoc} 
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel) {
		int nodoActual = i;

		// dec.add("n" + ( ++i ) + "[label=\"ID\"];");
		// rel.add("n" + nodoActual + " -> n" + i);
		dec.add("n" + (++i) + "[label=\"" + asig + "\"];");
		rel.add("n" + (parent) + " -> n" + i);
		i = id.getDotTree(parent+1, i, dec, rel);
		i = value.getDotTree(parent+1, i, dec, rel);
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();

		// posicion en el stack de la variable
		String varName = ((VarLiteral)id).getName();
		int varPos = symbolTable.searchByName(parent).getPositionVar(symbolTable, varName);
		Register rd = RegisterManager.SP;

		if(varPos == -1){
			varPos = symbolTable.searchByName(parent).getPositionVarGlobal(symbolTable, varName);
			rd = RegisterManager.GP;
		}
		// listado con operaciones para la asignacion
		IrtList valueList = value.destruct(parent, symbolTable);
		irtList.add(valueList);
		// se guarda el valor en el espacio de memoria de la variable en el stack
		irtList.add(
			new LoadStore(
				LoadStore.SW,
				valueList.getTail().getRd(),
				varPos,
				rd
			)
		);
		
		// se retorna el ultimo registro usado para guardar el valor de la asig
		symbolTable.getRegisterManager().returnRegister(valueList.getTail().getRd());
		return irtList;
	}

} 
