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
import compiler.irt.Register;
import compiler.irt.instructions.*;
/**
 *
 * @author yanet
 */
public class Cycle extends Node{

	/**
	*	Identificador de tipo de ciclo FOR
	*/
	public static final String FOR = "for";

	/**
	*	Identificador de tipo de ciclo WHILE
	*/
	public static final String WHILE = "while";

	/**
	*	Contador de ciclos creados para numerar tablas de simbolos
	*/
	private static int correlativo = 0;

	/**
	*	Identificador de ciclo para tabla de simbolos
	*/
	protected int id;

	/**
	*	Tipo de ciclo (WHILE o FOR)
	*/
	public String tipoCiclo;
	
	/**
	*	Incializacion de variable para el for
	*	la inicializacion de variable solo se usara en el for 
	*/
	public Node inicializacionVar;

	/**
	*	Condicion de ejecucion del ciclo
	*/
	public Node condicion;

	/**
	*	Bloque de codigo a ejecutar en el ciclo
	*/
	public Node bloque;
	
	/**
	*	Constructor general de la clase
	*	@param tipo 	String para indicar que tipo de ciclo es FOR o WHILE
	*	@param inicializacionVar	Incializacion de variable para el ciclo
	* 	@param condicion
	* 	@param bloque
	*/
	public Cycle(String tipo, Node inicializacionVar, Node condicion, Node bloque){
		this.inicializacionVar = inicializacionVar;
		this.condicion = condicion;
		this.bloque = bloque;
		this.tipoCiclo = tipo;
		this.id = correlativo++;
	}
	
	/**
	*	Constructor para ciclos FOR
	*	@param inicializacionVar	Incializacion de variable para el ciclo
	* 	@param condicion
	* 	@param bloque
	*/
	public Cycle(Node inicializacionVar, Node condicion, Node bloque){
		this(FOR, inicializacionVar, condicion, bloque);
	}

	/**
	*	Constructor para ciclos WHILE
	* 	@param condicion
	* 	@param bloque
	*/
	public Cycle(Node condicion, Node bloque){
		this(WHILE, null, condicion, bloque);
	}
	

	/**
	*	Verifica la condicion del ciclo y cuando es for se verifica la incializacion de variable
	*	y verifica el bloque correspondiente
	*	@param 	tb 			tabla del scope al que peretenece
	*	@param 	st 			Listado de todas las tablas
	*	@param 	errorList 	Lista de errores
	*/
	public void checkCycle(Table tb, String nombre, SymbolTable st, LinkedList<String> errorList){
		if (this.tipoCiclo.equals("for")){
			
			boolean condicionForValida = false;
			if (this.condicion instanceof Exp){
				Exp expr = (Exp)this.condicion;
				if (!(expr.checkExp(tb,st,errorList).equals("int"))){
					//System.out.println("condicion invalida");
					errorList.add("Condicion Invalida en un FOR");
				}else{condicionForValida = true;}
			}else if (this.condicion instanceof Literal){
				Literal lit = (Literal)this.condicion;
				if (!(lit.checkLiteral(tb,st).equals("int"))){
					//System.out.println("condicion invalida");
					errorList.add("Condicion Invalida en un FOR");
				}else{condicionForValida = true;}
			}else if (this.condicion instanceof BinOp){
				BinOp bo = (BinOp)this.condicion;
				if (!(bo.checkBinOp(tb,st,errorList).equals("int"))){
					//System.out.println("condicion invalida");
					errorList.add("Condicion Invalida en un FOR");
				}else{condicionForValida = true;}
			}
			
			Root rt = (Root)this.bloque;
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
				c.checkCond(tb,t,"IF_"+c.id,st,errorList);
			}else if (n instanceof Cycle){
				Cycle cy = (Cycle)n;
				// si es un for verifica la existencia y los tipos de la inicializacion de variablesz
				if (cy.tipoCiclo.equals(Cycle.FOR)) {
					Asign init = (Asign)cy.inicializacionVar;
					init.checkAsign(tb,st, errorList,1);
				}
				Table t = new Table("CICLO_"+cy.id, nombre);
				st.listaTablas.add(t);
				cy.checkCycle(t,"CICLO_"+cy.id,st,errorList);
			}else if (n instanceof Statement){
				Statement state = (Statement)n;
				state.checkStatement(tb,st,errorList);
			}
		}
		}else if (this.tipoCiclo.equals("while")){
			boolean condicionValida = false;
			if (this.condicion instanceof Exp){
				Exp expr = (Exp)this.condicion;
				if (!(expr.checkExp(tb,st,errorList).equals("boolean"))){
					//System.out.println("condicion invalida");
					errorList.add("Condicion Invalida en un WHILE");
				}else{condicionValida = true;}
			}else if (this.condicion instanceof Literal){
				Literal lit = (Literal)this.condicion;
				if (!(lit.checkLiteral(tb,st).equals("boolean"))){
					//System.out.println("condicion invalida");
					errorList.add("Condicion Invalida en un WHILE");
				}else{condicionValida = true;}
			}else if (this.condicion instanceof BinOp){
				BinOp bo = (BinOp)this.condicion;
				if (!(bo.checkBinOp(tb,st,errorList).equals("boolean"))){
					//System.out.println("condicion invalida");
					errorList.add("Condicion Invalida en un WHILE");
				}else{condicionValida = true;}
			}
			//condicionValida = true;
			Root rt = (Root)this.bloque;
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
					c.checkCond(tb,t,"IF_"+c.id,st,errorList);
				}else if (n instanceof Cycle){
					Cycle cy = (Cycle)n;
					// si es un for verifica la existencia y los tipos de la inicializacion de variablesz
					if (cy.tipoCiclo.equals(Cycle.FOR)) {
						Asign init = (Asign)cy.inicializacionVar;
						init.checkAsign(tb,st, errorList,1);
					}
					Table t = new Table("CICLO_"+cy.id, nombre);
					st.listaTablas.add(t);
					cy.checkCycle(t,"CICLO_"+cy.id,st,errorList);
				}else if (n instanceof Statement){
					Statement state = (Statement)n;
					state.checkStatement(tb,st,errorList);
				}
			}
		}
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String toString(){
		return "Ciclo";
	}
	
	/**
	*	{@inheritDoc}
	*/	
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel){
		int nodoActual = i;

		dec.add("n" + ( ++i ) + "[label=\""+ tipoCiclo + "\"];");
		rel.add("n" + nodoActual + " -> n" + i);		

		if(tipoCiclo.equals(FOR)){
			dec.add("n" + ( ++i ) + "[label=\"exp\"];");
			rel.add("n" + nodoActual + " -> n" + i);		
			i = inicializacionVar.getDotTree(i, nodoActual, dec, rel);
		}
		dec.add("n" + ( ++i ) + "[label=\"condicion\"];");
		rel.add("n" + nodoActual + " -> n" + i);
		i = condicion.getDotTree(i, i, dec, rel);

		dec.add("n" + ( ++i ) + "[label=\"bloque\"];");
		rel.add("n" + nodoActual + " -> n" + i);
		i = bloque.getDotTree(i, i, dec, rel);
		
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding){
		String str = padding + tipoCiclo + "\n";
		if(inicializacionVar != null) {
			str += inicializacionVar.print(padding + "\t");
		}
		if(condicion != null) {
			str += condicion.print(padding + "\t");
		}
		if(bloque != null) {
			str += bloque.print(padding + "\t");
		}
		return str;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable  symbolTable) {
		IrtList irtList = new IrtList();

		if(tipoCiclo.equals(WHILE)){
			// while
			irtList.add(new Label("CICLO_" + id));
			IrtList irtListCondicion = condicion.destruct(parent, symbolTable);
			irtList.add(irtListCondicion);
			Register rd = irtListCondicion.getTail().getRd();
			irtList.add(new Jump(Jump.BEQ, rd, RegisterManager.ZERO, "CICLO_" + id + "_end"));
			symbolTable.getRegisterManager().returnRegister(rd);
			irtList.add(new Comment("condicion/bloque"));
			irtList.add(bloque.destruct("CICLO_" + id, symbolTable));
			// irtList.add(new Label("CICLO_" + id + "_step"));		
			irtList.add(new Label("CICLO_" + id + "_step"));
			irtList.add(new Jump(Jump.J, "CICLO_" + id));
			irtList.add(new Label("CICLO_" + id + "_end"));
		} else {
			// for
			IrtList iniIrtList = inicializacionVar.destruct(parent, symbolTable);
			Register counter = symbolTable.getRegisterManager().getS();
			irtList.add(iniIrtList);
			// se mueve a un s
			irtList.add(new Alu(Alu.ADD, counter, iniIrtList.getTail().getRd(), RegisterManager.ZERO));
			irtList.add(new Label("CICLO_" + id));
			IrtList irtListCondicion = condicion.destruct(parent, symbolTable);
			irtList.add(irtListCondicion);
			Register rd = irtListCondicion.getTail().getRd();
			irtList.add(new Jump(Jump.BEQ, counter, rd , "CICLO_" + id + "_end"));
			irtList.add(bloque.destruct("CICLO_" + id, symbolTable));
			symbolTable.getRegisterManager().returnRegister(rd);
			
			irtList.add(new Label("CICLO_" + id + "_step"));
			// se solicita un registro temporal para cargar un 1 y hacer el step
			Register temp = symbolTable.getRegisterManager().getT();
			LoadStore li = new LoadStore(LoadStore.LI, temp, "1");

			irtList.add(li);
			irtList.add(new Alu(Alu.ADD, counter, counter, temp));
			// se retorna el registro temporal para sumar 1
			symbolTable.getRegisterManager().returnRegister(temp);
			LoadStore sw = ((LoadStore)iniIrtList.getTail()).copia();
			sw.setRd(counter);
			irtList.add(sw);
			irtList.add(new Jump(Jump.J, "CICLO_" + id));
			irtList.add(new Label("CICLO_" + id + "_end"));
			
		}

		return irtList;
	}
} 
