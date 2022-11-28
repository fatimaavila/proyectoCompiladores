/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.semantic;
import java.util.LinkedList;
import java.io.IOException;
import compiler.ast.nodes.*;
import compiler.ast.Ast;
import compiler.lib.Debug;
import compiler.lib.OutputFile;
/**
 *
 * @author yanet
 */
public class Semantic {

	public Debug debug;

	public OutputFile of;
	/**
	*	Listado de nodos del root
	*/
	public LinkedList<Node> listaNodos;

	/**
	*	Listado con todos los errores de semantic
	*/
	public LinkedList<String> listaErrores;

	/**
	*	Lista de tablas de todos los scopes del programa
	*/
	public SymbolTable tablaSimbolos;

	/**
	*	Constructor que inicializa los campos a partir de un objeto AST se toma la lista de nodos del root de ast
	*	@param ast 		Abstract Sintax Tree generado en la fase anterior
	*/
	public Semantic(Ast ast) throws Exception {
		tablaSimbolos = new SymbolTable();
		listaNodos = ast.root.declaraciones;
		listaErrores = new LinkedList<String>();
		of = ast.getOutFile();
	}
	
	/**
	*	Verifica las declaraciones de variables globales y metodos y se mandan a llamar a los checks de cada metodo
	*	@return boolean que es true si hay errores y false si no hay errores
	*/
	public boolean check(){
		String msg = "stage: Semantic";
		if(debug != null)
			debug.println(msg);
		try {
			of.writeln(msg);
		} catch(IOException ioe) {}
		boolean error = false;
		boolean main = false;
		Table tglobal = new Table("ROOT", "NULL");

		// se agrega funcion print a la tabla principal
		LinkedList<VarLiteral> var = new LinkedList<VarLiteral>();
		var.add(new VarLiteral("num"));
		LinkedList<Declaracion> printParametros = new LinkedList<Declaracion>();
		printParametros.add(new Declaracion(
			"int",
			var,
			Declaracion.FIELD
			));
		tglobal.put(
			"print",
			new Tipos(
				"void", 
				printParametros
				)
			);
		// fin de agrega funcion print
		
		this.tablaSimbolos.listaTablas.add(tglobal);
		// se recorre el listado de todos los nodos de root
		for (Node n : listaNodos){
			// se verifica que tipo de Nodo es 
			if(n instanceof Declaracion) {
				Declaracion decl = (Declaracion)n;
				if(decl.typeDec.equals(Declaracion.METODO)){
					// si es metodo
					Table t = new Table(decl.nameMethod, "ROOT");
					this.tablaSimbolos.listaTablas.add(t);
					// se verifica que el metodo no este declarado antes si no se agrega
					// un error al listado de errores
					if (tglobal.containsKey(decl.nameMethod) == false){
						tglobal.put(decl.nameMethod, new Tipos(decl.type, decl.parametros));
						// se agregan todas las variables de los parametros 
						// a la tabla de simbolos del scope del metodo
						for (Declaracion d : decl.parametros){
							t.put(d.nameFields.get(0).name, new Tipos(d.type, 1));
						}
					}else {
						this.listaErrores.add(decl.nameMethod + " no puede ser declarada de nuevo");
					}
					decl.checkMethod(t,decl.nameMethod,this.tablaSimbolos,this.listaErrores);
				} else if(decl.typeDec.equals(Declaracion.FIELD)){
					// campo
					for(VarLiteral vl : decl.nameFields){
						if (tglobal.containsKey(vl.name) == false){
							if (vl.dimension == null){
								tglobal.put(vl.name,new Tipos(decl.type, 1));
							}else {
								try{
									Literal literal = (Literal)vl.dimension;
									int dim = Integer.parseInt(literal.value);
									tglobal.put(vl.name,new Tipos(decl.type + "[]", dim));
									if(dim == 0){
										this.listaErrores.add(vl.name + "[0]  la dimension no puede ser 0");
									}
								} catch(Exception e){ }
							}
						}else {
							this.listaErrores.add(vl.name + " no puede ser declarada de nuevo");
						}
					}
				}
			}
		}
		for (Table tab : this.tablaSimbolos.listaTablas){
			if(debug != null)
				debug.println(tab.toTableString());
			if (tab.name.equals("main")){main = true;}
		}
		if (!main){
			this.listaErrores.add("No hay main");
		}
		if (!this.listaErrores.isEmpty()){
			error = true;
			System.out.println("\n- ERRORES -\n");
			for (String se : this.listaErrores){
				System.out.println("- " + se);
			}
		}
		return error;
	}
	

	public LinkedList<Node> getListaNodos() {
		return listaNodos;
	}

	public SymbolTable getSymbolTable() {
		return tablaSimbolos;
	}

	public void setDebuger(Debug d) {
		debug = d;
		debug.println("debugging: Semantic");
	}

	public OutputFile getOutFile() {
		return of;
	}
	
}
