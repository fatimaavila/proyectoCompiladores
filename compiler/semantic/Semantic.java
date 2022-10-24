import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class App {


  public static void main(String[] args) {

    int j = 1;
    ArrayList<String> vars2Check = new ArrayList<String>();


    JSONParser parser = new JSONParser();

    try (Reader reader = new FileReader("datablocks.json")) {
      System.out.println("Inicio el Parser R: ");

      JSONObject jsonObject = (JSONObject) parser.parse(reader);
      //System.out.println(jsonObject);
      //System.out.println("----------================--------------=========");
      
      JSONArray Blocks = (JSONArray) jsonObject.get("blocks");

      //System.out.println(Blocks);
     // System.out.println("----------================--------------=========");

      for(int i=0; i<Blocks.size(); i++){
        JSONObject tempJsonObject = (JSONObject) Blocks.get(i);
        vars2Check.clear(); // limpiamos array de chequeo
        JSONArray tempJsonArray = (JSONArray) tempJsonObject.get("varDeclaration");
        for(int k=0; k<tempJsonArray.size(); k++){
            //System.out.println(tempJsonArray);
            JSONObject tempVarDecObject = (JSONObject) tempJsonArray.get(k);
            String typito = (String) tempVarDecObject.get("type");
            String name = (String) tempVarDecObject.get("name");
            boolean ans =vars2Check.contains(name);
            if(ans==true){
                System.out.println(String.format("Variable %s local duplicada", name));
                System.exit(0);
            }
            else {
                System.out.println(String.format("ID: %d SCOPE: %d IDENTIFIER: %s TYPE: %s",j, i, name, typito));
                j++;
                vars2Check.add(name);
            }
            
           // System.out.println("-------=========");

        }//for del array vardeclaration
      } // for para que se vea todos los bloques, saque los blocks
      System.out.println("----------================--------------=========");
  
    }  catch (IOException e) { // try hasta aqui
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

  } // fin de app

} // fin de todo


/* public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

 */
