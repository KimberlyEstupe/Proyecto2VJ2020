package Estructuras;
import javax.swing.JOptionPane;
import kimberlyestupe.proyecto2vj2020.Archivos;
/**
 *
 * @author KimberlyEstupe
 */
public class Grafo {
    Archivos archivos=new Archivos();
    NVertice Inicio;
    
    public Grafo(){
        Inicio = null;
    }
    
    private boolean Vacio(){
        if (Inicio == null) return true;
        else return false;
    }
    
//    private int Tamano(){
//        int cont =0;
//        NVertice aux = Inicio;
//        if(!Vacio()){
//            while(aux != null){
//                aux = aux.SigV;
//                cont++;
//            }
//        }        
//        return cont;
//    }
    
    public void Insertar (String origen, String destino, int Distancia){
        NVertice NOrigen = InsertarVertice(origen);
        NVertice NDestino = InsertarVertice(destino);
        InsertarA(NOrigen, NDestino, Distancia);
    }
    
    private NVertice InsertarVertice(String nombre){
        try{
            NVertice nuevo = new NVertice(nombre);
            
            if(Vacio()) {
                Inicio=nuevo;
                return Inicio;
            }
            else{
                NVertice aux = Inicio;
                while(aux.SigV!= null && (!aux.Nombre.equalsIgnoreCase(nombre))){
                    aux = aux.SigV;
                }
                
                if(aux.Nombre.equalsIgnoreCase(nombre)){
                    return aux;
                }
                else{
                    aux.SigV = nuevo;
                    nuevo.Valor=aux.Valor+1;
                    return nuevo;
                }
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
       
    private void InsertarA(NVertice origen, NVertice Destino, int distancia){
        try{
            NArista nuevo = new NArista(distancia);
            NArista aux = origen.AdyAr;
            
            if(aux == null){
                origen.AdyAr = nuevo;
                nuevo.AdyVer = Destino;
            }else{
                while(aux.SigA != null){
                    aux=aux.SigA;
                } 
                aux.SigA=nuevo;
                nuevo.AdyVer = Destino;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void CargMasivaA(String filaname){
        String leer[]= archivos.leerArchivo(filaname);  
        String juntos;
        String divicion[];
        for(int i = 0; i<leer.length; i++){
            juntos=leer[i];
            divicion = juntos.split("/");
                    String dir = divicion[2].substring(0, divicion[2].length() - 1);
                    Insertar(divicion[0],divicion[1],Integer.parseInt(dir));
        }
        
    }
    
//    private NVertice getVetice(String Nombre){
//        try{
//            if(!Vacio()){
//                NVertice aux = Inicio;
//                while(aux != null){
//                    if (aux.Nombre.equalsIgnoreCase(Nombre)) return aux;
//                    aux = aux.SigV;
//                }
//            }
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
//        }
//        return null;
//    }
    
    // ===================================  Lista ADyacente ================================================
    public void ListaAd(){
        try{
            if(!Vacio()){
                NVertice AuxV = Inicio;
                NArista AuxA;
                String texto= " rankdir=LR; \n   node [shape=record,width=.1,height=.1]; \n   NA [label = \" \n";
                    //===== Origenes ==============                    
                    do{
                        AuxA=AuxV.AdyAr;                    
                        if(AuxA!=null){
                             texto+="    <f"+AuxV.Valor+">"+ AuxV.Nombre +"|\n";
                        }
                        AuxV=AuxV.SigV;
                    }while(AuxV != null);
                    texto = texto.substring(0, texto.length() - 3);                    
                    texto+="\n   \", height=2.5]; \n    node [shape=box3d] \n";
                    
                    //===== DESTINOS ==============
                    AuxV = Inicio;
                    do{
                        AuxA=AuxV.AdyAr;                    
                        if(AuxA!=null){
                            texto+="\n   \n  NA:f"+AuxV.Valor+" -> ND"+AuxV.Valor+AuxA.AdyVer.Valor+"\n"; 
                            texto+="        ND"+AuxV.Valor+AuxA.AdyVer.Valor+" [label= \""+AuxA.AdyVer.Nombre+", "+AuxA.Distancia+"\" ] \n";
                            while(AuxA.SigA!=null){
                                texto+="        ND"+AuxV.Valor+AuxA.AdyVer.Valor+" -> ND"+AuxV.Valor+AuxA.SigA.AdyVer.Valor+"\n";
                                AuxA=AuxA.SigA;
                                texto+="        ND"+AuxV.Valor+AuxA.AdyVer.Valor+" [label= \""+AuxA.AdyVer.Nombre+", "+AuxA.Distancia+"\" ] \n";                                
                            }
                        }
                        AuxV=AuxV.SigV;
                    }while(AuxV != null);
                    
                archivos.Archivo("LISTA DE RUTAS", texto, "ListaAdyacencia.txt");
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    // ===================================  GRAFO ================================================
    public void RecorridoG(){
        try{
            if(!Vacio()){
                NVertice AuxV = Inicio;
                NArista AuxA;
                String texto="   node [shape = ellipse ]\n";
                do{
                    AuxA=AuxV.AdyAr;
                    texto+="   NG"+AuxV.Valor+" [label= \""+AuxV.Nombre+"\" ] \n";
                    if(AuxA!=null){
                        do{
                            texto+="   NG"+AuxV.Valor+" -> NG"+AuxA.AdyVer.Valor+"[label= \""+AuxA.Distancia+"\", color=brown1]\n"; 
                            AuxA=AuxA.SigA;
                        }while(AuxA!=null);
                        
                    }
                    AuxV=AuxV.SigV;
                }while(AuxV != null);
                archivos.Archivo("MAPA DE RUTAS ", texto, "Grafo.txt");
            }
            
        }catch(Exception e){            
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ===================================  NODOS A UTILIZAR ================================================
    public class NVertice{
        NVertice SigV;
        NArista AdyAr;
        String Nombre;
        int Valor;
        
        public NVertice(String nombre){
            this.SigV = null;   
            this.Nombre = nombre;
            this.AdyAr = null;
            Valor=0;
        }
    }
    
    public class NArista{
        NArista SigA;
        NVertice AdyVer;
        int Distancia;
        
        public NArista(int dis){
            this.SigA = null;
            this.Distancia = dis;
            this.AdyVer = null;
        }
    }
}
