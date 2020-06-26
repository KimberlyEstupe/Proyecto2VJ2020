package kimberlyestupe.proyecto2vj2020;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);
import Ventanas.Conductores;
import Ventanas.RutasIn;
/**
 *
 * @author KimberlyEstupe
 */
public class Menu {
    public static void main(String[] args) {     
        //Conductores C= new Conductores();
        //RutasIn RI= new RutasIn();
       
   ListaAdyacencia LA = new ListaAdyacencia();
        LA.Ingesar("Casa", "Jalapa", "3");
        LA.Ingesar("Casa2", "Hurugua", "3");
        LA.Ingesar("Casa4", "Madrid", "3");
        LA.Ingesar("Pisto", "Jamica", "3");
        LA.Ingesar("Pisto", "Puerto Rico", "3");
        LA.Ingesar("Hogar", "Madrid", "3");
        LA.Ingesar("Casa", "Holanda", "3");
        LA.Ingesar("Casa", "Jalisco", "3");
        LA.RListaAd();
        
        
    /*Ejeplo de Split
        String dias = "Lunes,Martes,Miercoles,Jueves,Viernes,Sabado,Domingo";
    String diaArray[] = dias.split(",");
		
    System.out.println("--Ejemplo 1--");
    for(String dia : diaArray){
	System.out.println(dia);
        JOptionPane.showMessageDialog(null, "Aux: "+dia, "ERROR", JOptionPane.ERROR_MESSAGE);
    }*/
        // TODO code application logic here
    }
    
}
