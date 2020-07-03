package Estructuras;
import kimberlyestupe.proyecto2vj2020.Archivos;
import Estructuras.Grafo.NVertice;
import Estructuras.Grafo.NArista;
import javax.swing.JOptionPane;
/**
 *
 * @author KimberlyEstupe
 */
public class Camino {
    Grafo GF;
    LCamino ultimo;
    LCamino visitado;
    int NVer;
    
    public Camino(){
        ultimo = null;
        visitado = null;
    }
    
    private void Insertar(String nombre, int pos, LCamino cabeza){
        LCamino Cabeza = cabeza;
        LCamino nuevo = new LCamino(nombre,pos);
                if(Cabeza == null) Cabeza = nuevo;
                    else {
                        LCamino aux = Cabeza;
                        while (aux.SIG!=null){
                            aux=aux.SIG;
                        }                        
                        aux.SIG=nuevo;
                        nuevo.ANT=aux;
                    }
        
        try{
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void caminom(String origen, String destino,Grafo  bc){
        GF = bc;
        try{
            NVertice VerOrigen = GF.getVetice(origen);
            if(VerOrigen != null){
                NVer = GF.TamanoV();
                Insertar(VerOrigen.Nombre, VerOrigen.AdyAr.Distancia,ultimo);
                
            }else{}
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public class LCamino{
        LCamino SIG;
        LCamino ANT;
        String Nombre;
        int Peso;
        boolean visitado;
        int posicion;
        LCamino(String nombre, int pos){
            this.SIG = null;
            this.ANT = null;
            this.Nombre = nombre;
            this.Peso = 0;
            this.visitado = false;
            this.posicion = pos;
        }
    }    
}
