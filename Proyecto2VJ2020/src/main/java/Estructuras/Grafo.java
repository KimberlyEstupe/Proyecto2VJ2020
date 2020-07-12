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
    NVertice nbuscado;
    
    public Grafo(){
        Inicio = null;
    }
    
    private boolean Vacio(){
        if (Inicio == null) return true;
        else return false;
    }
    
    //------------------* CANTIDAD DE VERTICES *---------------------------------
    public int TamanoV(){
        int cont =0;
        NVertice aux = Inicio;
        if(!Vacio()){
            while(aux != null){
                cont++;
                aux = aux.SigV;
            }
        }        
        return cont;
    }
    
    //------------------* CANTIDAD DE ARISTAS *---------------------------------
    public int TamanoA(){
        int cont =0;
        NVertice auxV = Inicio;
        NArista auxA;
        
        if(!Vacio()){
            while(auxV != null){
                auxA = auxV.AdyAr;
                while(auxA != null){
                    cont++;
                    auxA = auxA.SigA;
                }
                auxV = auxV.SigV;
            }
        }        
        return cont;
    }
    
    public void Insertar (String origen, String destino, int Distancia){
        //Inserta primero vertices
        NVertice NOrigen = InsertarVertice(origen);
        NVertice NDestino = InsertarVertice(destino);
        
        //Une los vertices con una aristas de un peso o distancia determinada
        InsertarA(NOrigen, NDestino, Distancia);
    }
    
    //Vetcies es una lista simplemente enlazada (es un grafo diriguido en una direción)
    private NVertice InsertarVertice(String nombre){
        try{
            NVertice nuevo = new NVertice(nombre);
            
            if(Vacio()) {//Es el primer vertice ingresado
                Inicio=nuevo;
                return Inicio;
            }else{
                NVertice aux = Inicio;
                
                //Busca el ultimo vertice o uno repetido
                while(aux.SigV!= null && (!aux.Nombre.equalsIgnoreCase(nombre))){
                    aux = aux.SigV;
                }
                
                if(aux.Nombre.equalsIgnoreCase(nombre)){//Si el vertice ya existe lo devuelve 
                    return aux;
                }else{//Si es nevo lo inserta y devulve ese
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
      
    
    //Aristas es una lista:   VerticeOrigen -> Arsita1 -> Arista2 -> Arista3 ...
    // Luego Arista apuntara a VerticeDestino 
    private void InsertarA(NVertice origen, NVertice Destino, int distancia){
        try{
            NArista nuevo = new NArista(distancia);
            NArista aux = origen.AdyAr;//Apunta a la Arista1
            
            if(aux == null){//Si Arista1 es nula
                origen.AdyAr = nuevo;//Apunta a la nueva arista
                nuevo.AdyVer = Destino;// Apunta la arista a destino
            }else{//Si no es la primera busca hasta encontrar la ultimo y la inserta
                while(aux.SigA != null){
                    aux=aux.SigA;
                } 
                aux.SigA=nuevo; //Apunta auxilir a la nueva arista:  Arsita1 -> Arista2 -> Arista3
                nuevo.AdyVer = Destino;// Apunta la arista a destino
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void CargMasivaA(String filaname){//Lee un archivo de texto: Origen(String)/Destino(String)/Distancia(int)%
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
    
    public NVertice getVetice(String Nombre){// Deveulve un vertice
        try{
            if(!Vacio()){
                NVertice aux = Inicio;
                while(aux != null){
                    if (aux.Nombre.equalsIgnoreCase(Nombre)) {
                        nbuscado = aux;
                        return aux;
                    }
                    aux = aux.SigV;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
   
    
    public String nombreN(String Nombre){// Deveulve el nombre del vertice 
        try{
            NVertice buscar = getVetice(Nombre); //Busca el vertice
            if (buscar!=null) return buscar.Nombre;
            else return "";
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "SE HA PRODUCIDO UN ERRO ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    } 
    
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
                    texto+="   NG"+AuxV.Valor+" [label= \""+AuxV.Nombre+"\" ] \n"; //Crea los vertices sin importar si es origen o destino
                    
                    AuxA=AuxV.AdyAr;//Aux apunta a la primera arista del vertice                    
                    if(AuxA!=null){// Si aux es diferente de nulo singnifica que el vertive es un origen y entra en el siclo
                                   //Si aux es nulo significa que llego a un destino sin 
                        do{
                            texto+="   NG"+AuxV.Valor+" -> NG"+AuxA.AdyVer.Valor+"[label= \""+AuxA.Distancia+"\", color=brown1]\n"; 
                            // NodoORIGEN -> NodoDESTINO ( AuxA.AdyVer.Valor representa a destino ).
                            // Label graficara la distancia entre los nodos
                            AuxA=AuxA.SigA;
                        }while(AuxA!=null); //El ciclo termina cuando ya no haya mas aristas en el vertice  
                        
                    }// cierre de if de arista
                    
                    AuxV=AuxV.SigV;
                }while(AuxV != null);
                
                archivos.Archivo("MAPA DE RUTAS ", texto, "Grafo.txt");
            }// cierra de verificacion que la lista no este vacia 
            
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
