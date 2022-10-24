/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scanner;
//import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {
        try {
            Path path = Paths.get("");
            String ruta = path.toAbsolutePath().toString() + "C:/Users/yanet/Documents/NetBeansProjects/compi/src/scanner/data.txt";
            char n = (char) 92; 
            ruta = ruta.replace(n, '/');
            System.out.println(ruta);
            Reader obj = new BufferedReader(new FileReader(ruta));
            lexer lex = new lexer(obj);

            while (true) {
                String tokens;
                try {
                    tokens = lex.yylex().toString();

                } catch (Exception e) {
                    System.out.println("FIN");
                    return;
                }
                System.out.println(tokens + ": " + lex.yytext());
            }

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            // Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
