/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast.nodes;
import java.util.LinkedList;
import java.util.List;
import compiler.semantic.*;
import compiler.irt.IrtList;
import compiler.irt.instructions.*;
import compiler.irt.RegisterManager;
import compiler.irt.Register;
/**
 *
 * @author yanet
 */
public class MethodCall extends Node {
	
	public String nameMethod;
	public LinkedList<Node> expresiones;

	/**
	*	Constructor de clase
	*	@param name 	Nombre de metodo
	*	@param ex 		parametros
	*/
	public MethodCall(String name, LinkedList<Node> ex){
		this.nameMethod = name;
		this.expresiones = ex;
	}

	/**
	*	Verifica los tipos de los paremetros de una llamada a metodo
	*	@param 	tb 			tabla del scope al que peretenece la operacion
	*	@param 	st 			Listado de todas las tablas
	*	@param 	errorList 	Lista de errores
	*	@return tipo de dato que retorna el metodo
	*/
	public String checkMethodCall(Table tb, SymbolTable st, LinkedList<String> errorList){
		boolean p = false;
		String resultado = "";
		LinkedList<String> lista = new LinkedList<String>();
		Table tabla = null;
		for (Node n : this.expresiones){
			if (n instanceof Exp){
				Exp ex = (Exp)n;
				lista.add(ex.checkExp(tb,st,errorList));
			}else if (n instanceof Literal){
				Literal lit = (Literal)n;
				lista.add(lit.checkLiteral(tb,st));
			}else if (n instanceof BinOp){
				BinOp bin = (BinOp)n;
				lista.add(bin.checkBinOp(tb,st,errorList));
			}
		}
		
		for (Table t : st.listaTablas){
			if (t.name.equals("ROOT")){
				tabla = t;
			}
		}
		if (tabla.containsKey(this.nameMethod) == true){
			resultado = tabla.get(this.nameMethod).tipo;
			if (lista.size() != tabla.get(this.nameMethod).tiposparametros.size()){
				errorList.add("Error en el numero de argumentos en el Metodo " + this.nameMethod);
			}
			if (!lista.equals(tabla.get(this.nameMethod).tiposparametros)){
				errorList.add("parametro invalido en la llamada a " + this.nameMethod);
			}
		}else{
			errorList.add(this.nameMethod + "es un metodo no declarado");
		}
		return resultado;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding) {
		String str = padding + "Llamada " +  nameMethod + "\n";
		if(expresiones != null){
			for ( Node e : expresiones ) {
				str += e.print(padding + "\t");
			}
		}
		return str;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		return "Method Call";
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel) {
		int nodoActual = i;

		dec.add("n" + ( ++i ) + "[label=\"" + nameMethod + "\"];");
		rel.add("n" + parent + " -> n" + i);		
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();
		LinkedList<Register> usedRegisters = symbolTable.getRegisterManager().getUsedRegisters();

		irtList.add(new Comment("llamada a funcion"));
		// reservar espacio en stack para parametros
		int cant = expresiones.size() + usedRegisters.size();
		LoadStore li;
		if(cant > 0){
			Register temp = symbolTable.getRegisterManager().getS();
			irtList.add(new Alu(Alu.ADD, temp, RegisterManager.SP, RegisterManager.ZERO));
			li = new LoadStore(
				LoadStore.LI, 
				symbolTable.getRegisterManager().getT(),
				Integer.toString(cant*-4)
				);
			irtList.add(li);
			irtList.add(new Alu(
				Alu.ADD,
				RegisterManager.SP,
				RegisterManager.SP,
				li.getRd()
				));
			symbolTable.getRegisterManager().returnRegister(li.getRd());

			// se guardan todos los parametros en el stack 
			irtList.add(new Comment("Parametros"));
			for (int i = 0;  i < expresiones.size(); i++ ) {
				IrtList listExp = expresiones.get(i).destruct(parent, symbolTable);
				for (Instruction inst : listExp.getList() ) {
					if(inst instanceof LoadStore){
						LoadStore ls = (LoadStore)inst;
						if(ls.getRs() != null && ls.getRs().equals(RegisterManager.SP)){
							ls.setRs(temp);
						}
					}
				}
				irtList.add(listExp);
				irtList.add(new LoadStore(
					LoadStore.SW,
					listExp.getTail().getRd(),
					i*4,
					RegisterManager.SP
					));
				symbolTable.getRegisterManager().returnRegister(listExp.getTail().getRd());
			}
			symbolTable.getRegisterManager().returnRegister(temp);

			for (int i = expresiones.size(); i< cant; i++) {
				irtList.add(new LoadStore(
						LoadStore.SW,
						usedRegisters.get(i-expresiones.size()),
						i*4,
						RegisterManager.SP
					));	
			}
			symbolTable.getRegisterManager().reset();
		}
		irtList.add(new Alu(Alu.ADD, RegisterManager.FP, RegisterManager.SP, RegisterManager.ZERO));
		// Jump hacia la funcion
		irtList.add(new Jump(Jump.JAL, nameMethod));

		irtList.add(new Comment("end llamada a funcion"));

		// retornar los registros usados
		for (int i = expresiones.size(); i< cant; i++) {
			irtList.add(new LoadStore(
				LoadStore.LW,
				usedRegisters.get(i-expresiones.size()),
				i*4,
				RegisterManager.SP
			));	
		}
		symbolTable.getRegisterManager().setUsedRegisters(usedRegisters);

		// retornar el spacio al stack
		li = new LoadStore(
			LoadStore.LI, 
			symbolTable.getRegisterManager().getT(),
			Integer.toString(cant*4)
			);
		irtList.add(li);
		irtList.add(new Alu(
			Alu.ADD,
			RegisterManager.SP,
			RegisterManager.SP,
			li.getRd()
			));
		symbolTable.getRegisterManager().returnRegister(li.getRd());
		// se agrega una suma de $v0 con $v0 y $0 solo para que al obtener 
		// el tail de la lista devuelta se pueda obtener como rd el $v0
		irtList.add(new Alu(
			Alu.ADD,
			RegisterManager.V0,
			RegisterManager.V0,
			RegisterManager.ZERO
			));
		return irtList;
	}
}
