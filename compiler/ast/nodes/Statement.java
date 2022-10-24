package compiler.ast.nodes;

import java.util.List;
import java.util.LinkedList;
import compiler.semantic.*;


public class Statement extends Node{
	/**
	*	continue, break, return
	*/
	public String keyword;
	
	/**
	*	valor a retornar
	*/
	public Node value;
	
	/**
	*	Constructor de clase Statement para return
	*	@param k 	keyword return
	*	@param
	*/
	public Statement(String k, Node n1){
		this.keyword = k;
		this.value = n1;
	}

	/**
	*	Constructor para clase Statement return, continue y break
	*	@param	k keyword continue,break y return
	*/
	public Statement(String k){
		this(k, null);
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		String str = padding + keyword + "\n";
		if(value != null){
			str += value.print(padding + "\t");
		}
		return str;
	}

	/**
	*	Verificar el return que su tipo sea correspondiente con tipo de dato 
	*	que indicaron en la definicion del metodo.
	*	@param 	tb 			tabla del scope al que peretenece la operacion
	*	@param 	st 			Listado de todas las tablas
	*	@param 	errorList 	Lista de errores
	*	@return tipo de dato resultando de la expresion del return
	*/
	public String checkStatement(Table tb, SymbolTable st, LinkedList<String> errorList){
		String resultado = "";
		if (this.keyword.equals("return")){
			if (this.value instanceof Exp){
				Exp expr = (Exp)this.value;
				resultado = expr.checkExp(tb,st,errorList);
			}else if (this.value instanceof BinOp){
				BinOp bo = (BinOp)this.value;
				resultado = bo.checkBinOp(tb,st,errorList);
			}else if (this.value instanceof Literal){
				Literal lit = (Literal)this.value;
				resultado = lit.checkLiteral(tb,st);
			}
		}
		return resultado;
	}
	
	/**
	*	Verifica que break o continue esten dentro de un ciclo
	*	@param tb 			tabla del scope al que peretenece la operacion
	*	@param st 			Listado de todas las tablas
	*	@return Devuelve true si el break o continue esta bien de lo contrario retorna false
	*/
	public boolean checkBreakContinue(Table tb, SymbolTable st){
		boolean b = true;
		Table tableaux = tb;
		Table tableaux2 = null;
		if ((this.value == null) &&  ((this.keyword.equals("break")) || (this.keyword.equals("continue")))){
			b = false;
			while(!(tableaux.parent.equals("NULL")) && !b){
				if (tableaux.name.matches("CICLO_[0-9]+")){
					b = true;
					break;
				}
				for (int i=0; i<st.listaTablas.size(); i++){
					tableaux2 = st.listaTablas.get(i);
					if (tableaux2.name.equals(tableaux.parent)){
						tableaux = tableaux2;
						i=st.listaTablas.size();
					}
				}
			}
		}
		return b;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		return "Statement";
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel) {
		int nodoActual = i;

		dec.add("n" + ( ++i ) + "[label=\"Statement\"];");
		rel.add("n" + parent + " -> n" + i);		
		if (value==null){
			dec.add("n" + ( ++i ) + "[label=\"" + keyword +"\"];");
			rel.add("n" + (parent + 1) + " -> n" + i);		
		}else{
			dec.add("n" + ( ++i ) + "[label=\"" + keyword +"\"];");
			rel.add("n" + (parent + 1) + " -> n" + i);	
			i = value.getDotTree(parent+1, nodoActual, dec, rel);			
		}
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();
		String cycleName;
		switch (keyword) {
			case "return":
				IrtList valueList = value.destruct(parent, symbolTable);
				irtList.add(valueList);
				irtList.add(new Alu(
					Alu.ADD,
					RegisterManager.V0,
					valueList.getTail().getRd(),
					RegisterManager.ZERO
					));
				symbolTable.getRegisterManager().returnRegister(valueList.getTail().getRd());
				break;
			case "break":
				cycleName = symbolTable.searchByName(parent).getNearCicloName(symbolTable);
				irtList.add(new Jump(Jump.J, cycleName+ "_end"));
				break;
			case "continue":
				cycleName = symbolTable.searchByName(parent).getNearCicloName(symbolTable);
				irtList.add(new Jump(Jump.J, cycleName + "_step"));
				break;

		}
		return irtList;
	}
} 
