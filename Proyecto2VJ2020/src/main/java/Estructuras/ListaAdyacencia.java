package Estructuras;
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
    
    //============================== CARGA MASIVA DE DATOS =============================================
    public void CargMasivaA(String filaname){
        String leer[]= archivos.leerArchivo(filaname);  
        String juntos;
        String divicion[];
        for(int i = 0; i<leer.length; i++){
            juntos=leer[i];
            divicion = juntos.split("/");
                    String dir = divicion[2].substring(0, divicion[2].length() - 1);
                    Ingesar(divicion[0],divicion[1],dir);
        }
        
    }
    
    //============================== INGRESA =============================================
    public void Ingesar(String origen, String Destino, String tiempo){
        NodoLA norigen=Origen(origen);
        NodoLA NEW=new NodoLA(origen,Destino,tiempo,norigen.cabecera,0);
        Destinos(norigen,NEW);
        JOptionPane.showMessageDialog(null,"Ingreso Exitoso ");
    }
    
    private NodoLA Origen(String Origen){       
        if(Inicio==null){
            Inicio=new NodoLA(Origen,"","",0,0);
            return Inicio;
        }else{
            
            NodoLA aux=Inicio;
            while((aux.Abajo!=null) && !(aux.CiudadOrigen.equalsIgnoreCase(Origen))){
                aux=aux.Abajo;
            }            
            if(aux.CiudadOrigen.equalsIgnoreCase(Origen)){    
                return aux;
            }else if(aux.Abajo==null){
                NodoLA nuevo=new NodoLA(Origen,"","",aux.cabecera+1,0);
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
            nuevo.lista=segundo.lista+1;
            nuevo.Siguiente=segundo;
            segundo.Anterior=nuevo;
            Norigen.Siguiente=nuevo;
            nuevo.Anterior=Norigen;
        }
    }
    
    //============================== REPORTE DE LISTA =============================================
    public void RListaAd(){        
        if(Inicio!=null){
            String text=RecorridoLista();
            archivos.Archivo("LISTA DE RUTAS", text, "ListaAdyacencia.txt");
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY RUTAS INGRESADAS ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String RecorridoLista(){
        String texto="";
        if(Inicio!=null){
            NodoLA aux=Inicio;
            NodoLA auxX;
            do{
                texto+="      NO"+aux.cabecera+" [label= \""+aux.CiudadOrigen+"\" ] \n";
                auxX=aux.Siguiente;
                //-------Destinos-----
                texto+="      rank=same{ \n";
                texto+="        NO"+aux.cabecera+" -> ND"+auxX.cabecera+auxX.lista+"\n";
                texto+="        ND"+auxX.cabecera+auxX.lista+" [label= \""+auxX.CiudadDestino+" "+auxX.Tiempo+"\" ] \n";
                        while(auxX.Siguiente!=null){
                            texto+="        ND"+auxX.cabecera+auxX.lista+" -> ND"+auxX.cabecera+auxX.Siguiente.lista+"\n";
                            auxX=auxX.Siguiente;
                            texto+="        ND"+auxX.cabecera+auxX.lista+" [label= \""+auxX.CiudadDestino+" "+auxX.Tiempo+"\" ] \n";
                        }
                texto+="      }";
                ////---Fin de destinos
                if(aux.Abajo!=null){
                    texto+="      NO"+aux.cabecera+" -> NO"+aux.Abajo.cabecera+"[arrowhead=none] \n";
                }                
                aux=aux.Abajo;
            }while(aux!=null);         
            
            
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY RUTAS INGRESADAS ", "ERROR", JOptionPane.ERROR_MESSAGE);
            texto="label=\"Sin datos ingresados\";";
        }  
        return texto;
    }
    
    public void RGrafo(){
        if(Inicio!=null){
            String text=" rankdir = Lista; \n ";
            text+=RecorridoLista();
            archivos.Archivo("MAPA DE RUTAS", text, "Grafo.txt");
            
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY RUTAS INGRESADAS ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String Grafo(){        
        String texto="";
        if(Inicio!=null){
            NodoLA aux=Inicio;
            texto+="      NO"+aux.cabecera+" [label= \""+aux.CiudadOrigen+"\" ] \n";
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY RUTAS INGRESADAS ", "ERROR", JOptionPane.ERROR_MESSAGE);
            texto="label=\"Sin datos ingresados\";";
        }   
        return texto;
    }
    
    
    private class NodoLA{
        NodoLA Abajo;
        NodoLA Siguiente;
        NodoLA Anterior;
        NodoLA Arriba;
        String CiudadOrigen;
        String CiudadDestino;
        String Tiempo;
        int cabecera;
        int lista;
        //int grafo;
        NodoLA(String Origen, String Destino, String tiempo, int cabeza, int lis){
            Abajo=null;
            Siguiente =null;
            Anterior=null;
            Arriba = null;
            CiudadOrigen=Origen;
            CiudadDestino=Destino;
            Tiempo=tiempo;
            cabecera=cabeza;
            lista=lis;
        }
    }
}
