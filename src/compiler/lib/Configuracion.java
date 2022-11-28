/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.lib;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import compiler.lib.ErrorHandler;

/**
 *
 * @author yanet
 */
public class Configuracion {

	/**
	*	Archivo con el codigo fuente a compilar
	*/
	protected File input;
	// protected String inputFilename = "";
	
	protected String outputFilename = "";

	/**
	*	El target le indica al compilador hasta que fase debe ejecutar,
	* 	los target pueden ser  scan, parse, ast, semantic, irt y codegen
	*/
	protected String target = "codegen";

	/**
	*	Le indica al compilador que optimizacion debe realizar 
	* 	estas pueden ser: "constant folding" o "algebraic simplification"
	*/
	protected String opt = "";

	/**
	*	El debug le indica al compilador que fases se deben mostrar mensajes de debug
	* 	Las fases de debug pueden ser scan, parse, ast, semantic, irt y codegen
	*/
	protected LinkedList<String> debug;

	/**
	*	Archivo al que se imprimira la salida
	*/
	protected OutputFile outFile;



	public Configuracion() {
		debug = new LinkedList<String>();
	}

	public void setInputFile(File f) throws ErrorHandler {
		input = f;
		if(!f.exists()) {
			throw new ErrorHandler("Error! No existe el archivo: " + f.getPath());
		} else if (!Pattern.matches("[^\\-].*\\.dcf", f.getPath())){
			throw new ErrorHandler("Error! extension de archivo invalida solo se aceptan archvios .dcf : " + f.getPath());
		}
	}	

	public void setInputFile(String path) throws IOException, ErrorHandler {
		File f = new File(path);
		setInputFile(f);
	}

	/**
	*	Devuelve el nombre de archivo de Input
	*	@return String con el nombre del archivo del codigo fuente
	*/
	public String getInputFileName() {
		return input.getPath();
	}

	/**
	*	Establece el target de compilacion, los target validos son:
	*		- scan
	*		- parse
	*		- ast
	*		- semantic
	*		- irt 
	*		- codegen
	*	@param target String que indica el target
	*/
	public void setTarget(String target) {
		this.target = target;
	}

	public String getTarget() {
		return target;
	}

	public void addDebugPhases(String[] fases) {
		debug.clear();
		for (String f : fases ) {
			debug.add(f);
		}
	}

	public boolean isADebugPhase(String fase) {
		return debug.contains(fase);
	}	

	public void setOptimization(String opt) {
		this.opt = opt;
	}

	public String getOptimization() {
		return this.opt;
	}

	public void setOutputFile(String path) throws Exception {
		outputFilename = path;
		outFile = new OutputFile(path);
	}

	public OutputFile getOutputFile()  throws Exception {
		if(outFile == null){
			outputFilename = input.getPath().substring(0, input.getPath().lastIndexOf('.')) + ".s";
			outFile = new OutputFile( outputFilename );
		}
		return outFile;
	}

	public String getOutputFileName() {
		return outputFilename;
	}
		
	public boolean checkData() throws Exception {
		return getInputFileName() != null && getOutputFile() != null && input != null && target != null;


	}

}
