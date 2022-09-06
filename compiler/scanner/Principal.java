/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.io.File;

/**
 *
 * @author loren
 */
public class Principal {
    public static void main(String[] args) {
        String ruta = "C:/Users/loren/OneDrive/Datos adjuntos de correo electrónico/Documentos/NetBeansProjects/compiproy/src/scanner/lexer.flex";
        generarLexer(ruta);
    }
    
    public static void generarLexer(String ruta) {
        File archivo = new File (ruta);
        jflex.Main.generate(archivo);
    }
}
