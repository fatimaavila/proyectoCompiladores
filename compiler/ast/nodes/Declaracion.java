package compiler.ast.nodes;

import java.util.LinkedList;
import java.util.List;
import compiler.semantic.*;

/**
*	Nodo para declaraciones de callouts, metodos, fields y parametros
*/
public class Declaracion extends Node {
	
	/**
	*	Tipo de declaracion de callout
	*/
	public static final String CALLOUT = "callout";

	/**
	*	Tipo de declaracion de metodo
	*/
	public static final String METODO = "metodo";

	/**
	*	Tipo de declaracion de field
	*/
	public static final String FIELD = "field";

	/**
	*	Tipo de declaracion de parametro
	*/
	public static final String PARAMETRO = "parametro";
	 
	/**
	*	tipo de dato de variable o de retorno de metodo (int, boolean)
	*/
	public String type;

	/**
	*	Tipo de declaracion
	*/
	public String typeDec;

	////////////////////////////////////
	// 		para method_decl	   /////
	////////////////////////////////////
	/**
	*	Nombre de metodo
	*/
	public String nameMethod;

	/**
	*	Bloque de instrucciones de metodo
	*/
	public Node bloque;

	/**
	*	Listado de declaracion de parametros del metodo
	*/
	public LinkedList<Declaracion> parametros;

	////////////////////////////////////
	//        para field_decl      /////
	////////////////////////////////////

	/**
	*	Lista de de nombres de variables 
	*/
	public List<VarLiteral> nameFields;
	
	////////////////////////////////////
	//        para callout_decl    /////
	////////////////////////////////////
	/**
	*	Identificador de callout
	*/
	public String id;

	/**
	*	Consructor para CALLOUT
	*	@param id Identificador del callout
	*/
	public Declaracion(String id){
		this.typeDec = CALLOUT;
		this.nameMethod = "callout";
		this.nameFields = null;
		this.parametros = null;
		this.bloque = null;
		this.id = id;
		this.type="";
	}

	/**
	*	Consructor para METODO
	*	@param type 			tipo de valor de retorno del metodo
	*	@param name 			nombre del metood
	*	@param listaparametros	Listado con los parametros
	*	@param bloque 			Bloque con instrucciones del metodo
	*/
	public Declaracion(String type, String name, LinkedList<Declaracion> listaparametros, Node bloque){
		this.type = type;
		this.typeDec = METODO;
		this.nameMethod = name;
		this.bloque = bloque;
		this.parametros = listaparametros;
		this.nameFields = null;
		this.id = null;
	}
	
	/**
	*	Consructor general para declaracion
	*/
	public Declaracion(String type, List<VarLiteral> names, String tp){
		this.type = type;
		this.nameFields = names;
		this.typeDec = tp;
		this.parametros = null;
		this.nameMethod = null;
		this.bloque = null;
		this.id = null;
	}

	/**
	*	Verificacion el scope del bloque del metodo, se verifica existencia y unicidad;
	*	@param tb Tabla del metodo que se va a verificar
	*	@param nombre Nombre del padre
	*	@param stable Encapsula una lista de todas las tablas
	*	@param errorList Se utiliza para acumular los errores generados
	*/
	public void checkMethod(Table tb, String nombre, SymbolTable stable, LinkedList<String> errorList){
		String returntype = "";
		Root block = (Root)this.bloque;
		for (Node n : block.declaraciones){
			if (n instanceof Declaracion){
				Declaracion d = (Declaracion)n;
				for(VarLiteral vl : d.nameFields){
					if (tb.containsKey(vl.name) == false){
						if (vl.dimension == null){
							tb.put(vl.name,new Tipos(d.type, 1));
						}else {
							// verificar que la dimension no se 0 
							try{
								Literal literal = (Literal)vl.dimension;
								int dim = Integer.parseInt(literal.value);
								if(dim == 0){
									errorList.add(vl.name + "[0]  la dimension no puede ser 0");
								}
								tb.put(vl.name,new Tipos(d.type + "[]", dim));
							} catch(Exception e){ }
						}
					}else {
						//System.out.println(vl.name + " no puede ser declarada de nuevo");
						errorList.add(vl.name + " no puede ser declarada de nuevo");
					}
				}
			}else if (n instanceof Cond){
				Cond c = (Cond)n;
				Table t = new Table("IF_"+c.id, nombre);
				stable.listaTablas.add(t);
				c.checkCond(tb,t,"IF_"+c.id,stable,errorList);
			}else if (n instanceof Cycle){
				Cycle cy = (Cycle)n;
				if (cy.tipoCiclo.equals(Cycle.FOR)) {
					Asign init = (Asign)cy.inicializacionVar;
					init.checkAsign(tb,stable, errorList,1);
				}
				Table t = new Table("CICLO_"+cy.id, nombre);
				stable.listaTablas.add(t);
				cy.checkCycle(t,"CICLO_"+cy.id,stable,errorList);
			}else if (n instanceof MethodCall){
				MethodCall mc = (MethodCall)n;
				mc.checkMethodCall(tb,stable,errorList);
			}else if (n instanceof Statement){
				Statement st = (Statement)n;
				returntype = st.checkStatement(tb,stable,errorList);
				if (st.checkBreakContinue(tb,stable) == false){
					//System.out.println("no puede haber un break o continue fuera de un For o While");
					errorList.add("no puede haber un break o continue fuera de un For o While");
				}
			}else if (n instanceof Asign){
				Asign a = (Asign)n;
				a.checkAsign(tb,stable,errorList,0);
			}
			
		}
		if (this.type.equals("void")){
			if (!returntype.equals("")){
				//System.out.println("no tiene que haber return");
				errorList.add("no tiene que haber return en el metodo " + this.nameMethod);
			}
		}else{
			if (returntype.equals("")){
				//System.out.println("Falta el valor de retorno");
				errorList.add("Falta el valor de retorno en el metodo " + this.nameMethod);
			}else{
				if (!this.type.equals(returntype)){
					//System.out.println("tipo de retorno invalido");
					errorList.add("tipo de retorno invalido en el metodo " + this.nameMethod);
				}
			}
		}
	}
	
	/**
	*	{@inheritDoc}
	*/
	@Override
	public String print(String padding) {
		String str = padding + typeDec + "\n";
		if(type != null) {
			str += padding + "\t" + type + "\n";
		}
		if(nameFields != null){
			for ( VarLiteral v : nameFields ) {
				str += v.print(padding + "\t");
			}
		} else {
			str += padding + "\t" +nameMethod + "\n";
		}
		if(parametros != null){
			for(Declaracion d : parametros){
				str += d.print(padding + "\t");
			}
		}
		if(bloque != null){
			str += bloque.print(padding + "\t");
		}
		if(id != null){
			str += padding + "\t" + id + "\n";
		}
		return str;
	}

	/**
	*	Devuelve el tipo de declaracion
	*	@return tipo de declaracion
	*/
	public String getTypeDec(){
		return typeDec;
	}
	
	/**
	*	Devuelve el tipo de dato de la variable o retorno de metodo
	*	@return tipo de dato de variable o de retorno de metodo
	*/
	public String getTypeData(){
		return type;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public int getDotTree(int parent, int i, List<String> dec, List<String> rel){
		int nodoActual = i;
		dec.add("n" + ( ++i ) + "[label=\"Declaracion\"];");
		rel.add("n" + parent + " -> n" + i);		
		if(typeDec.equals(FIELD)){
			dec.add("n" + ( ++i ) + "[label=\"" + type + "\"];");
			rel.add("n" + (nodoActual + 1) + " -> n" + i);
			int fieldsParent = nodoActual + 1;
			for ( Node n : nameFields ) {
				i = n.getDotTree(fieldsParent, i, dec, rel);		
			}

		}else if(typeDec.equals(METODO)){
			dec.add("n" + ( ++i ) + "[label=\"" + type + "\"];");
			rel.add("n" + (nodoActual + 1) + " -> n" + i);
			dec.add("n" + ( ++i ) + "[label=\"" + nameMethod + "\"];");
			rel.add("n" + (nodoActual + 1) + " -> n" + i);
			for ( Node n : parametros ) {
				i = n.getDotTree(nodoActual + 1, i, dec, rel);
				// dec.add("n" + (++i ) + "[label=\"" + n.toString() + "\"];");
				// rel.add("n" + (nodoActual + 1) + " -> n" + i);				
			}
			i = bloque.getDotTree(nodoActual + 1, i, dec, rel);
		}else if(typeDec.equals(CALLOUT)){
			dec.add("n" + ( ++i ) + "[label=\"" + nameMethod + "\"];");
			rel.add("n" + (nodoActual + 1) + " -> n" + i);
			dec.add("n" + ( ++i ) + "[label=\"" + id + "\"];");
			rel.add("n" + (nodoActual + 1) + " -> n" + i);
		}
		return i;
	}

	/**
	*	{@inheritDoc}
	*/
	@Override
	public IrtList destruct(String parent, SymbolTable symbolTable) {
		IrtList instructions = new IrtList();
		if(getTypeDec().equals(METODO)){
			instructions.add(destructMethod(parent, symbolTable));
		} else if (getTypeDec().equals(FIELD)) {
			// field instructions
			instructions.add(destructField(parent, symbolTable));
		}
		return instructions;
	}

	protected IrtList destructMethod(String parent, SymbolTable symbolTable){
		IrtList instructions = new IrtList();

		// agrega label con nombre de metodo
		instructions.add(new Label(nameMethod));





		// solicita el espacio al stack
		int cantidadVar = symbolTable.searchByName(nameMethod).getCantidadVariables();
		LoadStore li = new LoadStore	(
											"li", 
											symbolTable.getRegisterManager().getT(),
											Integer.toString((cantidadVar + 1)*-4)
										);
		instructions.add(li);
		instructions.add(new Alu(
			Alu.ADD, 
			RegisterManager.SP, 
			RegisterManager.SP, 
			li.getRd()
			));
		symbolTable.getRegisterManager().returnRegister(li.getRd());

		// se mueven de $fp a $sp los parametros
		Register reg = symbolTable.getRegisterManager().getT();
		for (int i = 0; i< parametros.size(); i++) {
			instructions.add(new LoadStore(
				LoadStore.LW,
				reg,
				i*4,
				RegisterManager.FP
				));
			instructions.add(new LoadStore(
				LoadStore.SW,
				reg,
				i*4,
				RegisterManager.SP
				));
		}
		symbolTable.getRegisterManager().returnRegister(reg);

		// LoadStore temp = new LoadStore	(
		// 									LoadStore.LI, 
		// 									symbolTable.registerManager.getT(),
		// 									// "skffsdf"
		// 									Integer.toString(-4)
		// 								);
		// instructions.add(temp);
		// instructions.add(new Alu(Alu.ADD, RegisterManager.SP, RegisterManager.SP, temp.getRd() ));
		// symbolTable.registerManager.returnRegister(temp.getRd());

		// // almacena los registros
		instructions.add(new LoadStore("sw", RegisterManager.RA, cantidadVar*4 , RegisterManager.SP));


		// agrega instrucciones del bloque
		Root rootMethod = (Root)bloque;
		int limit = rootMethod.size();
		for (int i = 0; i < limit; i++) {
			instructions.add(rootMethod.getChild(i).destruct(nameMethod, symbolTable));
		}

		// instructions.add(((Root)bloque).destruct(nameMethod, symbolTable));


		// restaura los registros guardados
		instructions.add(new LoadStore("lw", RegisterManager.RA, cantidadVar*4 , RegisterManager.SP));
		// restaura el espacio al stack
		LoadStore temp = new LoadStore	(
									"li",
									symbolTable.registerManager.getT(),
									Integer.toString((cantidadVar+1)*4)
								);
		instructions.add(temp);
		instructions.add(new Alu(Alu.ADD, RegisterManager.SP, RegisterManager.SP, temp.getRd() ));
		symbolTable.registerManager.returnRegister(temp.getRd());
		// regresa
		instructions.add(new Jump("jr", RegisterManager.RA));
		return instructions;
	}

	protected IrtList destructField(String parent, SymbolTable symbolTable){
		IrtList instructions = new IrtList();
		
		return instructions;
	}

}
