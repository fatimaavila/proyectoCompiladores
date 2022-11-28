/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

import java.util.regex.Pattern;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import compiler.scanner.Scanner;
import compiler.parser.CC4Parser;
import compiler.ast.Ast;
import compiler.irt.Irt;
import compiler.semantic.Semantic;
import compiler.codegen.Codegen;
import compiler.opt.Algebraic;
import compiler.opt.ConstantFolding;
import compiler.lib.Debug;
import compiler.lib.OutputFile;
import compiler.lib.ErrorHandler;
import compiler.lib.Configuracion;

/**
 *
 * @author yanet
 */
public class Compiler {

	public static void main(String[] args) {
		// Variables de configuracion del Compilador
		Configuracion config = new Configuracion();
		Date date = new Date();
		long time = date.getTime();
		try {
			// leer configuarcion de los argumentos recibidos desde la consola
			readConfiguration(config, args);

			OutputFile outFile;
			if(config.checkData()){
				System.out.println("input: " + config.getInputFileName());
				System.out.println("output: " + config.getOutputFileName());
				execute(config);
			}
		} catch(ErrorHandler e) {
			System.err.println(e.getMessage());
			// e.printStackTrace();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			date = new Date();
			System.out.println("Ejecucion terminada en: " + (date.getTime() - time)/1000.0  + "seg");
		}
    }

    /**
    *	Lee la configuracion desde un arreglo y la guarda en un objeto Configuracion que se recibe como parametro
    *	@param 	config 	Objeto que encapsula la configuracion del compilador
    *	@param 	args 	Arreglo de Strings que contiene todas las banderas y sus valores
    */
	public static void readConfiguration(Configuracion config, String[] args) throws ErrorHandler, IOException, Exception {
		if ( args.length > 0 ) {
			// recorre el arreglo args y setea las variables de configuración 
			for ( int i = 0; i< args.length; i++ ) {
				switch(args[i]){
					case "-o":
						config.setOutputFile(args[++i]);
						break;
					case "-target":
						config.setTarget(args[++i]);
						break;
					case "-opt":
						config.setOptimization(args[++i]);
						break;
					case "-debug":
						config.addDebugPhases(args[++i].split(":"));
						break;
					case "-h":
						printHelp();
						System.exit(0);
						break;
					default:
						if ( args.length-1 == i ) {
							config.setInputFile(args[i]);
						} else{
							throw new ErrorHandler("Error en la opcion: " + args[i]);
						}
						break;
				}
			}
		}else if (args.length==0){
			printHelp();
			System.exit(0);
		}else if ((args[0].equals("-h"))&&(args.length==1)){
			printHelp();
			System.exit(0);
		}
	}

	/**
	*	Ejecuta el compilador hasta la fase solicitada
	*	@param config Configuracion de ejecucion
	*/
	public static void execute(Configuracion config) throws ErrorHandler, Exception {
		Scanner scan;
		CC4Parser parse;
		Ast ast;
		Semantic semantic;
		Irt irt;
		Codegen codegen;
		ConstantFolding cf;
		Algebraic algebraic;
		Debug deb = new Debug();
		OutputFile outFile = config.getOutputFile();

		scan = new Scanner(config.getInputFileName(), outFile);
		if (config.isADebugPhase("scan")) scan.setDebuger(deb);
		scan.start();
		if(config.getTarget().equals("scan")) {
			return;
		}

		parse = new CC4Parser(scan);
		parse.start();
		if (config.isADebugPhase("parse")) parse.setDebuger(deb);
		if(config.getTarget().equals("parse")) {
			return;
		}

		ast = new Ast(parse);
		if (config.isADebugPhase("ast")) ast.setDebuger(deb);
		ast.start();
		if(config.getTarget().equals("ast")) {
			return;
		}

		semantic = new Semantic(ast);
		if (config.isADebugPhase("semantic")) semantic.setDebuger(deb);
		if (semantic.check()){
			System.out.println("Programa Invalido");
		}else {
			if(config.getTarget().equals("semantic")) {
				return;
			}
			irt = new Irt(semantic);
			if (config.isADebugPhase("irt")) irt.setDebuger(deb);
			irt.start();
			if(config.getTarget().equals("irt")) {
				return;
			}
			
			codegen = new Codegen(irt);
			if (config.isADebugPhase("codegen")) codegen.setDebuger(deb);
			codegen.start();
			// Optimizaciones
			if( config.getOptimization().equals("constant") ){
				cf = new ConstantFolding(config.getInputFileName());
			} else if( config.getOptimization().equals("algebraic") ){
				algebraic = new Algebraic(config.getInputFileName());
			}else if(!config.getOptimization().equals("")){
				throw new ErrorHandler("Opcion de optimizacion invalida: " + config.getOptimization());
			}
		}

	}

	/**
	*	Despliega la ayuda en el comandline
	*/
	public static void printHelp() {
		System.out.println("\t-o <outname>Escribir el output a un archivo de texto llamado <outname>.\n");
		System.out.println("\t-target <stage> Donde <stage> es uno de:");
		System.out.println("\t\t\t- scan");
		System.out.println("\t\t\t- parse");
		System.out.println("\t\t\t- ast"); 
		System.out.println("\t\t\t- semantic");
		System.out.println("\t\t\t- irt");
		System.out.println("\t\t\t- codegen"); 
		System.out.println("\t\tla compilación debe proceder hasta la etapa indicada.");
		System.out.println("\t\tPor ejemplo, si <stage> es scan, una instancia de scan debe ser");
		System.out.println("\t\tcreada imprimiendo en el archivo de salida \"stage: scanning\".");
		System.out.println("\t\tSi es parse una instancia de parser debe ser creada a partir de");
		System.out.println("\t\tla instancia de scanner imprimiendo \"stage: parsing\", además ");
		System.out.println("\t\tdel mensaje de scanner y así sucesivamente.\n");
		System.out.println("\t-opt <optimzation>  <optimization> es uno de: constant, algebraic;");
		System.out.println("\t\tla compilacion debe hacer solo la optimización que se le pida,"); 
		System.out.println("\t\ty debe imprimir como en -target \"optimizing: constant folding\""); 
		System.out.println("\t\t\"o optimizing: algebraic simplification\".\n"); 
		System.out.println("\t-debug <stage>  Imprimir información de debugging. Debe haber un"); 
		System.out.println("\t\tmensaje por cada etapa listada en <stage> de la forma ");
		System.out.println("\t\t\"Debugging <stage>\". <stage> tiene las mismas opciones de"); 
		System.out.println("\t\t-target, con la diferencia que se pueden \"debuggear\" varias");
		System.out.println("\t\tetapas, separandolas con ':' de la forma scan:parse:etc.\n");
		System.out.println("\t-h Muestra esta ayuda al usuario.");
	}
}
