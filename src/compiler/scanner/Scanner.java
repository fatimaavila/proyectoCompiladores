package compiler.scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author yanet
 */
import compiler.lib.Debug;
import compiler.lib.OutputFile;
import org.antlr.v4.runtime.*;

/**
*	Clase principal de la fase de Scanner se encarga de tokenizar todo el programa input y genera una lista de tokens
*/
public class Scanner {

	public Debug debug;
	public OutputFile of;
	public DecafLexer lexer;
	public String inputFile;
	public BaseErrorListener listener;

	/**
	*	Constructor que inicializa los campos de la clase
	*	@param inputFile	Path Codigo fuente a compilar
	*	@param outFile 		Archivo de salida para escribir el output
	*/
	public Scanner(String inputFile, OutputFile outFile) throws Exception {
		of = outFile;
		this.inputFile = inputFile;
	    lexer =  new DecafLexer(new ANTLRFileStream(inputFile));
	}

	/**
	*	Inicia la ejecucion de la fase de scanner
	*/
	public void start() {
		try {
			String msg = "stage: Scanner";
			of.writeln(msg);
			if(debug != null) debug.println(msg);
		    DecafLexer lexerTemp = new DecafLexer(new ANTLRFileStream(inputFile));

		    // remover los listeners originales de ANTLr
		  	lexerTemp.removeErrorListeners(); 

		    // agregamos nuestro Listener 
	    	listener = new ErrorListener(); 
			lexerTemp.addErrorListener(listener);

		    // Se recorren los tokens
		    String str;
		    Token t = lexerTemp.nextToken();
		    while (t.getType() != Token.EOF){
		    	if(getRuleName(t.getType()).contains("ERROR")){
		    		str = error(t);
		    		of.writeln(str);
					if(debug != null) debug.println(str);
		    	} else {
					str = t.getLine() 
							+ " " + getRuleName(t.getType())  // concatena el nombre de la regla 
							+ ": " + t.getText(); // se agrega el texto de token
					if(debug != null) debug.println(str);
					of.writeln(str);
		    	}
				t = lexerTemp.nextToken();
		    } 
		}catch(ArrayIndexOutOfBoundsException aiobe){
		    System.err.println("Must provide a valid path to the filename with the tokens");
		    aiobe.printStackTrace();
		    System.exit(1);
		}catch(Exception e){
		    System.err.println("Must provide a valid path to the filename with the tokens");
		    e.printStackTrace();
		    System.exit(1);
		}
	}

	public void setDebuger(Debug d){
		debug = d;
		debug.println("debugging: Scanner");
	}

	public OutputFile getOutFile(){
		return of;
	}

	/**
	*	Retorna el nombre de la regla correspondiente al idType	
	*	se le resta 1 por que el arreglo empieza en 0 y en los tokens antlr 
	*	los numero a partir de 1
	*/
	public String getRuleName(int id) {
		if (id <= DecafLexer.ruleNames.length) {
			return DecafLexer.ruleNames[ id - 1 ];
		} else {
			return null;
		}
	}

	/**
	*	Metodo que recibe un token de error y retorna el mensaje de error corespondiente
	* 	@param Token t token con error
	*	@return String mensaje de error del token
	*/
	public String error(Token t){
		String error = "Error en";

		switch (getRuleName(t.getType())) {
			case "HEX_ERROR":
				error += " hexadecimal \"" + t.getText() + "\"";
				break;
			case "CHAR_ERROR":
				error += " char " + t.getText();
				break;
			case "STRING_ERROR":
				error += " string " + t.getText();
				break;
			default:
				error += " caracter no esperado \'" + t.getText() + "\'";
				break;
		}

		error +=  " en la linea " + t.getLine();
		return error;
	}

	/**
	*	Listener para el manejo de los errores que no fueron contemplados 
	*/
	class ErrorListener extends BaseErrorListener	{
		 @Override
	    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
	                            int line, int charPositionInLine,
	                            String msg, RecognitionException e) {
	        String sourceName = recognizer.getInputStream().getSourceName();
	        if (!sourceName.isEmpty()) {
	            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
	        }
	        try {
	        	sourceName += " " + msg + " en la linea " + line;
	        	System.out.println(sourceName);
	        	of.writeln(sourceName);
	        } catch (Exception ex) { 
	        	// si se produce un error al escribir en el archivo ignoramos la exception
	        }
		}
	}
}
