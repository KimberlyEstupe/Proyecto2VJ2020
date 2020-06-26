package kimberlyestupe.proyecto2vj2020;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);
import kimberlyestupe.proyecto2vj2020.Archivos;
/**
 *
 * @author KimberlyEstupe
 */
public class ListaAdyacencia {
    Archivos archivos=new Archivos();
    NodoLA Inicio;
    
    public ListaAdyacencia(){
        Inicio=null;
    }
    public void Ingesar(String origen, String Destino, String tiempo){
        NodoLA Origen=Origen(origen);
        NodoLA NEW=new NodoLA(origen,Destino,tiempo);
        //JOptionPane.showMessageDialog(null,"Destino "+Origen.CiudadOrigen);
        Destinos(Origen,NEW);
    }
    
    private NodoLA Origen(String Origen){        
        NodoLA nuevo=new NodoLA(Origen,"","");
        if(Inicio==null){
            Inicio=nuevo;
            return nuevo;
        }else{
            NodoLA aux=Inicio;
            while((aux.Abajo!=null) && (aux.CiudadOrigen!=Origen)){
                aux=aux.Abajo;
            }
            if(aux.CiudadOrigen==Origen){    
                return aux;
            }else if(aux.Abajo==null){
                aux.Abajo=nuevo;
                nuevo.Arriba=aux;
                return nuevo;
            }
        }
        return null;
    }
    
    private void Destinos(NodoLA Norigen, NodoLA nuevo){        
        if(Norigen.Siguiente==null){
            Norigen.Siguiente=nuevo;
            nuevo.Anterior=Norigen;
        }else{
            NodoLA segundo=Norigen.Siguiente;
            nuevo.Siguiente=segundo;
            segundo.Anterior=nuevo;
            Norigen.Siguiente=nuevo;
            nuevo.Anterior=Norigen;
        }
    }
      
    public void RListaAd(){        
        if(Inicio!=null){
            String text=RecorridoLista();
            archivos.Archivo("Lista de Rutas", text, "ListaAdyacencia.txt");
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY RUTAS INGRESADAS ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String RecorridoLista(){
        if(Inicio!=null){
            NodoLA aux=Inicio;
            String texto="";
            texto+="      NO"+aux.CiudadOrigen+" [label= \""+aux.CiudadOrigen+"\" ] \n";
            while(aux.Abajo!=null){
                texto+="      NO"+aux.CiudadOrigen+" -> NO"+aux.Abajo.CiudadOrigen;
                aux=aux.Abajo;
                texto+="      NO"+aux.CiudadOrigen+" [label= \""+aux.CiudadOrigen+"\" ] \n";
            }
            
            
            
            
            
            return texto;
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY RUTAS INGRESADAS ", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }        
    }
    private class NodoLA{
        NodoLA Abajo;
        NodoLA Siguiente;
        NodoLA Anterior;
        NodoLA Arriba;
        String CiudadOrigen;
        String CiudadDestino;
        String Tiempo;
        NodoLA(String Origen, String Destino, String tiempo){
            Abajo=null;
            Siguiente =null;
            Anterior=null;
            Arriba = null;
            CiudadOrigen=Origen;
            CiudadDestino=Destino;
            Tiempo=tiempo;
        }
    }
}
