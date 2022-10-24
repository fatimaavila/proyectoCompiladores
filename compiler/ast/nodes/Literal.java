package compiler.ast.nodes;

import java.util.List;
import java.util.regex.*;
import compiler.semantic.*;

/**
*	Clase para los nodos literal (char. string, int y hex)
*/
public class Literal extends Node {
	/*char, string, int, hex */ 
	public String value;
	
	/**
	*	Constructor de clase 
	*	@param s valor en char, string, int o hex
	*/
	public Literal(String s){
		this.value = s;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		return padding + value + "\n";
	}

	/**
	*	Devuelve el tipo de dato del literal
	*	@param tb Tabla de simbolos del scope
	*	@param st Objeto con todas las tablas
	*	@return retorna el tipo de dato del literal
	*/
	public String checkLiteral(Table tb, SymbolTable st){
		String resultado = "";
		if(Pattern.matches("[0-9]+",this.value)){
			resultado = "int";
		}else if(Pattern.matches("0x([0-9]|[A-F]|[a-f])+",this.value)){
			resultado = "int";
		}else if(Pattern.matches(".",this.value)){
			resultado = "char";
		}else if ((this.value.equals("\'"))||(this.value.equals("\""))||(this.value.equals("\\"))||(this.value.equals("\t"))||(this.value.equals("\n"))){
			resultado = "char";
		}else if ((this.value.equals("true"))||(this.value.equals("false"))){
			resultado = "boolean";
		}
		return resultado;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		return value;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel) {
		int nodoActual = i;

		dec.add("n" + ( ++i ) + "[label=\"" + value + "\"];");
		rel.add("n" + parent + " -> n" + i);		

		// if(tipoCiclo.equals(FOR)){
		// 	dec.add("n" + ( ++i ) + "[label=\"exp\"];");
		// 	rel.add("n" + nodoActual + " -> n" + i);		
		// 	i = inicializacionVar.getDotTree(nodoActual, dec, rel);
		// }
		// dec.add("n" + ( ++i ) + "[label=\"condicion\"];");
		// rel.add("n" + nodoActual + " -> n" + i);
		// i = condicion.getDotTree(i, dec, rel);

		// dec.add("n" + ( ++i ) + "[label=\"bloque\"];");
		// rel.add("n" + nodoActual + " -> n" + i);
		// i = bloque.getDotTree(i, dec, rel);
		
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();
		Register temp = symbolTable.registerManager.getT();
		if(value.equals("true") || value.equals("false")){
			String booleanBinario = value.equals("true")?"1":"0"; 
			irtList.add(new LoadStore("li", temp, booleanBinario));
		} else if(Pattern.matches("[0-9]+",this.value)){
			irtList.add(new LoadStore("li", temp, value));
		} else if(Pattern.matches(".",this.value)) {
			irtList.add(new LoadStore("li", temp, "'" + value + "'"));
		} else if ((this.value.equals("\'"))||(this.value.equals("\""))||(this.value.equals("\\"))||(this.value.equals("\t"))||(this.value.equals("\n"))){
			irtList.add(new LoadStore("li", temp, "'" + value + "'"));
		} else {
			irtList.add(new LoadStore("li", temp, value));
		}
		return irtList;
	}
} 

