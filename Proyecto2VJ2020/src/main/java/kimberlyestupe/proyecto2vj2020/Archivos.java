package kimberlyestupe.proyecto2vj2020;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KimberlyEstupe
 */
public class Archivos {
    private static String TEMP_DIR = "c:\\temp";
    
    public  void Archivo(String titulo,String text, String fileName){
        String texto="digraph G { \n rankdir = Lista; \n" +
                        "   node [shape = rectangle fontname=\"Arial\" fontsize=\"10\"]\n" +
                        "   graph [nodesep = 0.4]\n" +
                        "   label = < <font color='mediumvioletred'> <font point-size='20'>"+ titulo +"</font></font>>;\n" +
                        "   labelloc = \"t\"; \n" ;           
            texto+=text;
            texto+="   \n}";
        File file = new File(fileName);
        BufferedWriter buffer = null;
        try {
            buffer = new BufferedWriter(new FileWriter(file));
            buffer.write(texto+"\n");
        } catch (IOException e) {
            System.out.println("Error E/S: "+e);
        }
        finally{
            try {
                buffer.close(); 
                String f=fileName;                
                f = f.substring(0, f.length() - 4);
                imagen(f);
                } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /////////////////////////  GENERA IMAGEN //////////////////////
    public void imagen(String fName){
        try {   
            Runtime rt = Runtime.getRuntime();
            Runtime rt2 = Runtime.getRuntime();
            rt.exec("cmd.exe /c dot -Tjpg "+fName+".txt -o "+fName+".jpg");
            rt2.exec("cmd.exe /c "+fName+".jpg");
        } catch (Exception ex) {
          ex.printStackTrace();
        } finally {
        }
    }
    
    //----------------ABRIR ARCHIVO-----------
    public  String[] leerArchivo(String fileName){
        File file = new File(fileName);
        BufferedReader buffer = null;
        String cad  ="";
        try {
            buffer = new BufferedReader(new FileReader(file));
            while(buffer.ready()){
                cad+=buffer.readLine();
                if(buffer.ready()){
                    cad += "\n";
                }
            }
            return cad.split(";\n");
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        finally{
            try {
                buffer.close();
                } catch (IOException ex) {
                Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
        
    }
    
    
}

