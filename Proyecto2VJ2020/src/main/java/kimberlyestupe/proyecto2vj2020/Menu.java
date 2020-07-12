package kimberlyestupe.proyecto2vj2020;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);
import Ventanas.Viajes;
import Ventanas.RutasIn;
import Estructuras.DoblementeEnlazada;
import Estructuras.TablaH;
import Estructuras.BlochChain;
import Estructuras.Grafo;
/**
 *
 * @author KimberlyEstupe
 */
public class Menu {
    public static void main(String[] args) {
        DoblementeEnlazada DE = new DoblementeEnlazada();
        TablaH TH = new TablaH();
        BlochChain BC = new BlochChain();
        Grafo G = new Grafo();
        RutasIn RI= new RutasIn(DE, TH, G, BC);
        RI.setVisible(true);
    }
}
