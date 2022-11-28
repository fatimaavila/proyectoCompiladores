/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.ast.nodes;
import java.util.List;
import java.util.LinkedList;
import compiler.semantic.*;
import compiler.irt.IrtList;
import compiler.irt.RegisterManager;
import compiler.irt.instructions.*;
/**
 *
 * @author yanet
 */
public class Cond extends Node{
	/**
	*	Contador de Nodos del tipo Cond creados
	*/
	private static int correlativo = 0;

	/**
	*	Identificador unico para cada Cond
	*/
	protected int id;

	/**
	*	Condicion para que se ejecute la consecuencia o alternativa
	*/
	public Node condicion;

	/**
	*	Bloque de instrucciones correspondientes a la consecuencia del if
	*/
	public Node consecuencia;

	/**
	*	Bloque de instrucciones correspondientes a la alternativa del if
	*/
	public Node alternativa;
	
	/**
	*	Constructor para un if sin alternativa
	*/
	public Cond(Node condicion, Node consecuencia){
		this(condicion, consecuencia, null);
	}
	
	/**
	*	Constructor para un if con consecuencia y alternativa
	*/
	public Cond(Node condicion, Node consecuencia, Node alternativa){
		this.id = correlativo++;
		this.condicion = condicion;
		this.consecuencia = consecuencia;
		this.alternativa = alternativa;
		
	}
	
	/**
	*	Verifica que la condicion sea bolean y se verifica el bloque del if
	*	@param 	tab 		Tabla del scope donde se encuentra el if
	*	@param 	tb 			Tabla del scope del bloque correspondiente al if
	*	@param 	nombre 		Nombre de la tabla padre
	*	@param 	st 			Listado de todas las tablas
	*	@param 	errorList 	Lista de errores
	*/
	public void checkCond(Table tab, Table tb, String nombre, SymbolTable st, LinkedList<String> errorList){
		boolean condicionValida = false;
		String verificacionCondicion = "";
		if (this.condicion instanceof Exp){
			Exp expr = (Exp)this.condicion;
			expr.checkExp(tab,st,errorList);
		}else if (this.condicion instanceof Literal){
			Literal lit = (Literal)this.condicion;
			verificacionCondicion = lit.checkLiteral(tab,st);
		}else if (this.condicion instanceof BinOp){
			BinOp bo = (BinOp)this.condicion;
			verificacionCondicion = bo.checkBinOp(tab,st,errorList);
		}
		if (verificacionCondicion.equals("boolean")){
			condicionValida = true;
		}
		if (condicionValida){
			Root rt = (Root)this.consecuencia;
			for (Node n : rt.declaraciones){
				if (n instanceof Declaracion){
					Declaracion decl = (Declaracion)n;
					for(VarLiteral vl : decl.nameFields){
						if (tb.containsKey(vl.name) == false){
							if (vl.dimension == null){
								tb.put(vl.name,new Tipos(decl.type, 1));
							}else {
								try{
									Literal literal = (Literal)vl.dimension;
									int dim = Integer.parseInt(literal.value);
									if(dim == 0){
										errorList.add(vl.name + "[0]  la dimension no puede ser 0");
									}
									tb.put(vl.name,new Tipos(decl.type + "[]", dim));
								} catch(Exception e){ }
							}
						}
					}
				}else if (n instanceof Asign){
					Asign as = (Asign)n;
					as.checkAsign(tb,st,errorList,0);
				}else if (n instanceof MethodCall){
					MethodCall mc = (MethodCall)n;
					mc.checkMethodCall(tb,st,errorList);
				}else if (n instanceof Cond){
					Cond c = (Cond)n;
					Table t = new Table("IF_"+c.id, nombre);
					st.listaTablas.add(t);
					c.checkCond(tb,t,"IF_"+c.id,st, errorList);
				} else if (n instanceof Cycle) {
					Cycle cy = (Cycle)n;
					// si es un for verifica la existencia y los tipos de la inicializacion de variables
					if (cy.tipoCiclo.equals(Cycle.FOR)) {
						Asign init = (Asign)cy.inicializacionVar;
						init.checkAsign(tb,st, errorList,1);
					}

					Table t = new Table("CICLO_"+cy.id, nombre);
					st.listaTablas.add(t);
					cy.checkCycle(t,"CICLO_"+cy.id,st,errorList);
				}else if (n instanceof Statement){
					Statement state = (Statement)n;
					//state.checkStatement(tb,st);
					if (state.checkBreakContinue(tb,st) == false){
						errorList.add("no puede haber un break o continue fuera de un For o While");
					}
				}
			}
			if (this.alternativa != null){
				Root rt2 = (Root)this.alternativa;
				Table telse = new Table("ELSE_"+this.id, tb.parent);
				st.listaTablas.add(telse);
				for (Node n : rt2.declaraciones){
					if (n instanceof Declaracion){
						Declaracion decl = (Declaracion)n;
						for(VarLiteral vl : decl.nameFields){
							if (telse.containsKey(vl.name) == false){
								if (vl.dimension == null){
									telse.put(vl.name,new Tipos(decl.type, 1));
								}else {
									try{
										Literal literal = (Literal)vl.dimension;
										int dim = Integer.parseInt(literal.value);
										if(dim == 0){
											errorList.add(vl.name + "[0]  la dimension no puede ser 0");
										}
										// tb.put(vl.name,new Tipos(decl.type + "[]", dim));
										telse.put(vl.name,new Tipos(decl.type + "[]", dim));
									} catch(Exception e){ }
								}
							}
						}	
					}else if (n instanceof Asign){
						Asign as = (Asign)n;
						as.checkAsign(telse,st,errorList,0);
					}else if (n instanceof MethodCall){
						MethodCall mc = (MethodCall)n;
						mc.checkMethodCall(telse,st,errorList);
					}else if (n instanceof Cond){
						Cond c = (Cond)n;
						Table t = new Table("IF_"+c.id, nombre);
						st.listaTablas.add(t);
						c.checkCond(telse,t,"IF_"+c.id,st,errorList);
					}else if (n instanceof Cycle){
						Cycle cy = (Cycle)n;
						// si es un for verifica la existencia y los tipos de la inicializacion de variables
						if (cy.tipoCiclo.equals(Cycle.FOR)) {
							Asign init = (Asign)cy.inicializacionVar;
							init.checkAsign(telse,st, errorList,1);
						}

						Table t = new Table("CICLO_"+cy.id, nombre);
						st.listaTablas.add(t);
						cy.checkCycle(t,"CICLO_"+cy.id,st,errorList);
					}else if (n instanceof Statement){
						Statement state = (Statement)n;
						//state.checkStatement(telse,st);
						if (state.checkBreakContinue(telse,st) == false){
							errorList.add("no puede haber un break o continue fuera de un For o While");
						}
					}
				}
			}
		}else{
			errorList.add("Condicion invalida en un IF");
		}
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		return "Cond";
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		String str = padding + "If\n";
		if(condicion != null) {
			str += condicion.print(padding + "\t");
		}
		if(consecuencia != null) {
			str += consecuencia.print(padding + "\t");
		}
		if(alternativa != null){
			str += padding + "\tElse\n";
			str += alternativa.print(padding + "\t");
		}
		return str;
	}

	/**
	*	{@inheritDoc}
	*/
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel){
		int nodoActual = i;

		dec.add("n" + ( ++i ) + "[label=\"if\"];");
		rel.add("n" + nodoActual + " -> n" + i);		
		i = condicion.getDotTree(i, nodoActual, dec, rel);

		dec.add("n" + ( ++i ) + "[label=\"ID\"];");
		rel.add("n" + nodoActual + " -> n" + i);

		// dec.add("n" + ( ++i ) + "[label=\"Exp\"];");
		// rel.add("n" + nodoActual + " -> n" + i);
		// i = hijo1.getDotTree(i, dec, rel);		
		
		return i;
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();
		irtList.add(new Comment("IF_" + id));
		IrtList condicionList = condicion.destruct(parent, symbolTable);
		irtList.add(condicionList);
		irtList.add(new Jump(
			Jump.BEQ, 
			condicionList.getTail().getRd(),
			RegisterManager.ZERO,
			"IF_" + id + "_alernativa"
		));
		symbolTable.getRegisterManager().returnRegister(condicionList.getTail().getRd());	
		// agregar etiqueta para consecuencia
		irtList.add(new Label("IF_" + id + "_consecuencia"));
		irtList.add(consecuencia.destruct("IF_" + id, symbolTable));
		irtList.add(new Jump("j", "IF_" + id + "_end"));
		irtList.add(new Label("IF_" + id + "_alernativa"));
		if(alternativa != null) {
			irtList.add(alternativa.destruct("ELSE_" + id, symbolTable));
		}
		irtList.add(new Label("IF_" + id + "_end"));

		irtList.add(new Comment("end IF_" + id));
		return irtList;
	}
} 
