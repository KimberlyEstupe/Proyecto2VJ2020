package kimberlyestupe.proyecto2vj2020;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);

import Ventanas.Viajes;
import Ventanas.RutasIn;
import Estructuras.DoblementeEnlazada;
import Estructuras.ListaAdyacencia;
import Estructuras.TablaH;
import Estructuras.BlochChain;
/**
 *
 * @author KimberlyEstupe
 */
public class Menu {
    public static void main(String[] args) {
        DoblementeEnlazada DE = new DoblementeEnlazada();
        ListaAdyacencia LA = new ListaAdyacencia();
        TablaH TH = new TablaH();
        BlochChain BC = new BlochChain();
        
        RutasIn RI= new RutasIn(DE, TH, LA, BC);
        RI.setVisible(true);
        
//        Viajes V = new Viajes(DE, TH, LA);
//        V.setVisible(true);
    }
    
}
