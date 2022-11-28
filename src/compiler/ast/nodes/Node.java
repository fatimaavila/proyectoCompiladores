/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast.nodes;
import java.util.List;
import compiler.irt.IrtList;
import compiler.semantic.SymbolTable;
/**
 *
 * @author yanet
 */
public abstract class Node {
	
	/**
	*	Imprime el ast con un padding definido como parametro
	*	@param padding margen para imprimir puede ser \t 
	*/
	public abstract String print(String padding);

	/**
	*	Genera una representacion dot del ast para visualizarla en graphiz
	*	@param parent 	numero de nodo del padre
	*	@param i 		contador de la numeracion de nodos
	*	@param dec		Listado de declaraciones de nodos
	*	@param rel		Lista de relaciones entre los nodos
	*	@return entero con el ultimo numero que fue asignado a un nodo
	*/
	public abstract int getDotTree(int parent, int i, List<String> dec, List<String> rel);


	/**
	*	
	*/
	public abstract IrtList destruct(String parent, SymbolTable  symbolTable);

	
}
