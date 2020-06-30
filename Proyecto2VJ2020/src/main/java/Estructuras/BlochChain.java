package Estructuras;
import kimberlyestupe.proyecto2vj2020.Archivos;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);
import Estructuras.DoblementeEnlazada.NodoDE;
import Estructuras.TablaH.NodoTH;

public class BlochChain {
    DoblementeEnlazada DE;
    TablaH TH;
    NodoBC Cabeza;
    
    public BlochChain(){
        Cabeza = null;
    }
    
    public void Ingreso(String origen, String destino, String fecha, String hora, String deConductor, String thCliente, DoblementeEnlazada de, TablaH th){
        DE = de;
        TH = th;
        NodoDE Doble = DE.Buscar(Long.parseLong(deConductor));
        NodoTH tabla = TH.Busqueda(thCliente);
        
        
        // INICIO DE METODO DE INGRESO COMO EN LISTA
        NodoBC nuevo = new NodoBC(origen, destino, fecha, hora, "clave", Doble, tabla);        
        if(Cabeza == null) Cabeza = nuevo;
        else {
            NodoBC aux = Cabeza;
            while (aux.siguiente!=null){
                aux=aux.siguiente;
            }
            aux.siguiente=nuevo;
            nuevo.anterior=aux;
        }
    }
    
    public void NodoDE(String dato, DoblementeEnlazada dd){
        DE = dd;
        NodoDE Doble = DE.Buscar(Long.parseLong(dato));
        System.out.println(Doble.DPI);
    }
    
    public class NodoBC{        
        NodoDE nodoDE;
        NodoTH nodoTH;
        NodoBC siguiente;
        NodoBC anterior;
        String Origen;
        String Destino;
        String Fecha;
        String Hora;
        String Clave;
        NodoBC (String origen, String destino, String fecha, String hora, String clave, NodoDE nDE, NodoTH nTH){
            siguiente = null;
            anterior = null;
            Origen = origen;
            Destino = destino;
            Fecha = fecha;
            Hora = hora;
            Clave = clave;
            nodoDE = nDE;
            nodoTH = nTH;
        }
        
    }
}
