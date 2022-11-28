/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.parser;
import compiler.lib.OutputFile;
import compiler.lib.Debug;
import java.util.List;
import java.util.LinkedList;
import org.antlr.v4.runtime.tree.TerminalNode;
/**
 *
 * @author yanet
 */
public class ParseListener extends DecafParserBaseListener {
	
	public OutputFile out;
	public Debug debug;

	protected LinkedList<String> errorList;

	public ParseListener(){
		errorList = new LinkedList<String>();
	}

	public LinkedList<String> getErrorList() {
		return errorList;
	}
	

	public void enterMethod_call_error(DecafParser.Method_call_errorContext ctx) {
		errorList.add("Error en la llamada al metodo en la linea " + ctx.getStart().getLine());
	}


	@Override
	public void enterStatement_error(DecafParser.Statement_errorContext ctx) {
		//errorList.add("Error en la llamada al metodo en la linea " + ctx.getStart().getText());
		if(ctx.location() != null){
			if(ctx.PUNTO_COMA() == null){
				errorList.add("Error falto ; en la linea: " + ctx.getStart().getLine() );
			}else{
				errorList.add("Error en la asignaion en la linea " + ctx.getStart().getLine());
			}
		}
		if(ctx.KW_RETURN() != null || ctx.KW_BREAK() != null || ctx.KW_CONTINUE() != null || ctx.method_call() != null ){
			errorList.add("Error falto ; en la linea: " + ctx.getStart().getLine() );
		}
	}

	@Override
	public void enterProgram_error(DecafParser.Program_errorContext ctx) {
		// verificar el orden de las delcaraciones
		// se toma en cuenta el caso en el que se tengan declaraciones con error y declaraciones sin error se toma el numero de linea de la primera
		int calloutLine = -1;
		if(ctx.callout_decl().size() > 0 && ctx.callout_decl_error().size() > 0 ) {
			int callout 		= ctx.callout_decl(0).getStart().getLine();
			int callout_error 	= ctx.callout_decl_error(0).getStart().getLine();
			calloutLine = (callout < callout_error)? callout : callout_error;
		} else if(ctx.callout_decl().size() > 0) {
			calloutLine = ctx.callout_decl(0).getStart().getLine();
		} else if(ctx.callout_decl_error().size() > 0) {
			calloutLine = ctx.callout_decl_error(0).getStart().getLine();
		}

		int fieldLine = -1;
		if(ctx.field_decl().size() > 0 && ctx.field_decl_error().size() > 0) {
			int field 		= ctx.field_decl(0).getStart().getLine();
			int field_error = ctx.field_decl_error(0).getStart().getLine();
			fieldLine = (field < field_error)? field : field_error;
		} else if(ctx.field_decl().size() > 0) {
			fieldLine 	= ctx.field_decl(0).getStart().getLine();
		} else if(ctx.field_decl_error().size() > 0) {
			fieldLine 	= ctx.field_decl_error(0).getStart().getLine();
		}

		int methodLine = -1;
		if(ctx.method_decl().size() > 0 && ctx.method_decl_error().size() > 0) {
			int method 		= ctx.method_decl(0).getStart().getLine();
			int method_error = ctx.method_decl_error(0).getStart().getLine();
			methodLine = (method < method_error)? method : method_error;
		} else if(ctx.method_decl().size() > 0) {
			methodLine 	= ctx.method_decl(0).getStart().getLine();
		} else if(ctx.method_decl_error().size() > 0) {
			methodLine 	= ctx.method_decl_error(0).getStart().getLine();
		}

		if(calloutLine != -1 && ((fieldLine != -1 && calloutLine > fieldLine) || (methodLine != -1 && calloutLine > methodLine)) ) {
			errorList.add("Error los callout deben ser declarados antes de los fields y los metodos en la linea : " + calloutLine);
		}
		if(fieldLine != -1 && methodLine != -1 && fieldLine > methodLine) {
			errorList.add("Error los fields deben ser declarados antes de los metodos en la linea : " + fieldLine);
		}

		// errorList.add("Las declaraciones van primero en la linea: " + ctx.getStart().getLine());
	}

	@Override
	public void enterWhile_error(DecafParser.While_errorContext ctx) {
		if(ctx.PARENTESIS_I() == null && ctx.PARENTESIS_D() == null ){
			errorList.add("Error faltaron los parentesis () en la linea: " + ctx.getStart().getLine());
		} else if(ctx.PARENTESIS_I() == null){
			errorList.add("Error falta el parentesis izquierdo en la linea: " + ctx.getStart().getLine());
		} else {
			errorList.add("Error falta el parentesis derecho en la linea: " + ctx.getStart().getLine());
		}

		if (ctx.block_error() != null) {
			errorList.add("Error en el bloque del while en la linea: " + ctx.getStart().getLine());
		}

		if(ctx.block() == null && ctx.block_error() == null){
			errorList.add("Error No escribio el bloque de codigo del while en la linea : " + ctx.getStart().getLine());
		}
		if(ctx.expr() == null){
			errorList.add("Error Falta la condicion del ciclo while en la linea: " + ctx.getStart().getLine());
		}
	}

	@Override
	public void enterFor_error(DecafParser.For_errorContext ctx) {
		// kw_for
		if(ctx.KW_FOR() == null){
			errorList.add("Error for invalido en la linea: " + ctx.getStart().getLine());
		}
		// parentesis
		if(ctx.PARENTESIS_I() == null && ctx.PARENTESIS_D() == null ){
			errorList.add("Error faltaron los parentesis () en la linea: " + ctx.getStart().getLine());
		} else if(ctx.PARENTESIS_I() == null){
			errorList.add("Error falta el parentesis izquierdo en la linea: " + ctx.getStart().getLine());
		} else {
			errorList.add("Error falta el parentesis derecho en la linea: " + ctx.getStart().getLine());
		}

		if (ctx.ID() == null) {
			errorList.add("Error falto el identificador del for en la linea: " + ctx.getStart().getLine());
		}

		if (ctx.block_error() != null ) {
			errorList.add("Error en el bloque del for en la linea: " + ctx.getStart().getLine());
		}
		
	}

	@Override
	public void enterIf_error(DecafParser.If_errorContext ctx) {
		if (ctx.expr() == null) {
			errorList.add("Error no indico la condicion del if en la linea: " + ctx.getStart().getLine());
		}
		if( ctx.KW_ELSE() != null && ctx.block().size() == 1){
			errorList.add("Error falto un bloque de codigo del if en la linea: " + ctx.getStart().getLine());
		}
		
		// parentesis
		if(ctx.PARENTESIS_I() == null && ctx.PARENTESIS_D() == null ){
			errorList.add("Error faltaron los parentesis () en la linea: " + ctx.getStart().getLine());
		} else if(ctx.PARENTESIS_I() == null){
			errorList.add("Error falta el parentesis izquierdo en la linea: " + ctx.getStart().getLine());
		} else if(ctx.PARENTESIS_D() == null){
			errorList.add("Error falta el parentesis derecho en la linea: " + ctx.getStart().getLine());
		}

		if (ctx.block_error().size() > 0) {
			errorList.add("Error en el bloque del if en la linea: " + ctx.getStart().getLine());
		}
	}

	@Override
	public void enterMethod_decl_error(DecafParser.Method_decl_errorContext ctx) {
		if (ctx.KW_VOID() == null && ctx.ID().size() != ctx.type().size() || (ctx.ID().size() - 1) != ctx.type().size()) {
			errorList.add("Error falto un tipo de parametro de la declaracion del metodo en la linea: " + ctx.getStart().getLine());
		}
		if(ctx.block_error() != null){
			errorList.add("Error en el bloque de la declaracion del metodo (" + ctx.ID(0) + ") en la linea:" + ctx.getStop().getLine());
		}
		// no se indico el tipo de retorno
	}

	@Override
	public void enterCallout_decl_error(DecafParser.Callout_decl_errorContext ctx) {
		errorList.add("Falto el ; en la linea " + ctx.getStart().getLine());
	}

	@Override
	public void enterBlock_error( DecafParser.Block_errorContext ctx) {
		// ignorado por que se reporta en otros lugares
		
	}

	/**
	* 	Errores de Declaracion de Fields
	*	@param DecafParser.Field_decl_errorContext
	*/
	@Override
	public void enterField_decl_error( DecafParser.Field_decl_errorContext ctx) {
		// verifica que no se este inicializando la variable en la declaracion
		if(ctx.ASIG_OP().size() > 0) {
			errorList.add("Error no se puede inicializar variables al declararlas en la linea: " + ctx.getStart().getLine());
		}

		// verifica que no haya mas comas que los identificadores
		if(ctx.keywords().size() > 0){
			errorList.add("Error no se pueden usar keywords como identificador de variable en la linea: " + ctx.getStart().getLine());
		} else if(ctx.type() != null && ctx.ID().size() - 1 < ctx.COMA().size() ){
			errorList.add("Error falto un identificador de variable en la linea: " + ctx.getStart().getLine());
		}


		// verifica que finalice con punto y coma
		if(ctx.PUNTO_COMA() == null){
			errorList.add("Error falto el ; de la declaracion de variable en la linea: " + ctx.getStart().getLine());
		}

		// verifica el que todos los arreglos tengan su tamaño
		if((ctx.CORCHETE_I().size() + ctx.CORCHETE_I().size())/2  != ctx.INT_LITERAL().size()){
			errorList.add("Error falto el tamaño en la declaracion del arreglo este solo puede ser un entero en la linea: " + ctx.getStart().getLine());
		}

		// verifica que solo exista una palabra reservada
		if(ctx.keywords().size() > 1){
			errorList.add("Error en declaracion de variable no se permite el uso de palabras reservadas en la linea: " + ctx.getStart().getLine());
		}

		// verifica que si existe mas de una variable este separada por coma
		if(ctx.type() != null && ctx.ID().size()-1 > ctx.COMA().size()){
			errorList.add("Error faltaron comas en la declaracion de varias variables en la linea: " + ctx.getStart().getLine());
		}

		if(ctx.type() == null){
			errorList.add("Error se debe indicar un tipo de dato valido en la linea: " + ctx.getStart().getLine());
		}
	}

}
