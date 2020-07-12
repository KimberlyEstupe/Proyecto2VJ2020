package Estructuras;
import kimberlyestupe.proyecto2vj2020.Archivos;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);
import Estructuras.DoblementeEnlazada.NodoDE;
import Estructuras.TablaH.NodoTH;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BlochChain {
    Archivos archivos=new Archivos();
    DoblementeEnlazada DE;
    TablaH TH;
    NodoBC Cabeza;
    NodoBC NodoBuscado;
    
    public BlochChain(){
        Cabeza = null;
        NodoBuscado=null;
    }
    
    public String Ingreso(String origen, String destino, String fecha, String hora, String deConductor, String thCliente, DoblementeEnlazada de, TablaH th){
        try{
            DE = de;
            TH = th;
            NodoDE Doble = DE.Buscar(Long.parseLong(deConductor));
            NodoTH tabla = TH.Busqueda(thCliente);
            if(Doble !=null){
                if(tabla !=null){
                    String clave = ContraseñaMD5(fecha+hora);
                    System.out.println("Clave "+fecha+hora);
                    Doble.viajes++;
                    tabla.viajes++;
                    NodoBC nuevo = new NodoBC(origen, destino, fecha, hora, clave, Doble, tabla);  
                    //System.out.println(nuevo.nodoDE.viajes+" "+nuevo.nodoTH.viajes);
                    if(Cabeza == null) Cabeza = nuevo;
                    else {
                        NodoBC aux = Cabeza;
                        while (aux.siguiente!=null){
                            aux=aux.siguiente;
                        }
                        aux.siguiente=nuevo;
                        nuevo.anterior=aux;
                    }
                    JOptionPane.showMessageDialog(null, "Nuevo ingreso"); 
                    return "Realizado";

                }else JOptionPane.showMessageDialog(null, "No se encontre ese cliente ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else JOptionPane.showMessageDialog(null, "No se encontro ese conductor", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }catch(Exception e){           
        }
        return "No";
    }
    
    public String ContraseñaMD5(String input){      
        try {
                MessageDigest md = MessageDigest.getInstance("MD5");//Seleciona el metodo o tipo de algoritmo
                byte[] messageDigest = md.digest(input.getBytes());//Se digiere 
                BigInteger number = new BigInteger(1, messageDigest);//almacena todo en un gran numero
                String hashtext = number.toString(16);// lo convierte en estring 

                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }
                return hashtext;
                }
        catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
        }
    }
    // ===============================  BUSCAR =================================
    public NodoBC Buscar(String DATO){
        try{
            String clave = ContraseñaMD5(DATO);
            if(Cabeza!=null){
                NodoBC aux = Cabeza;
                while(aux!=null){
                    if(aux.Clave.equals(clave)) {
                        NodoBuscado= aux;
                        return aux;}
                    aux = aux.siguiente;
                }
            }
        }catch(Exception e){}  
        return null;
    }
    
    // ===============================  Mostrar =================================
    public String OrigenV(String busca){
        try{
            NodoBC t = Buscar(busca);
            if (t != null) {
                String texto ="<html><body>Origen: "+t.Origen+
                            " <br>Destino: "+t.Destino+
                            "<br>Conductor: "+t.nodoDE.DPI+", "+t.nodoDE.nombre+
                            "<br>Cliente: "+t.nodoTH.DPI+", "+t.nodoTH.Nombre+
                            " <br>Fecha "+t.Fecha+
                            " <br>Hora: "+t.Hora+
                            "</body></html>";
                return texto;}
            else JOptionPane.showMessageDialog(null, "No se encontro esta clave ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){}  
        return "";        
    }
    
    
    // =============================== REPORTES =================================
    public void ReporteBC(){
        try{
            if(Cabeza!=null){            
                String texto=RecorridoBC();                            
                archivos.Archivo("Reporte de Viajes",texto, "ReporteBlockChain.txt");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR \n No hay datos ingresado ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){}   
    }
    
    public String RecorridoBC(){
        try{
            String texto=" rankdir = Lista; \n   node [shape=note]\n   {rank=same \n";
            if(Cabeza!=null){
                NodoBC aux=Cabeza;  
                do{   
                        texto+="      NBC"+aux.Clave+" [label= \" Clave: "+aux.Clave+
                                "\\n Conductor: "+aux.nodoDE.DPI+", "+aux.nodoDE.nombre+
                                "\\n Cliente: "+aux.nodoTH.DPI+", "+aux.nodoTH.Nombre+
                                "\\n Origen: "+aux.Origen+
                                "\\n Destino: "+aux.Destino+
                                "\\n Fecha: "+aux.Fecha+
                                "\\n Hora: "+aux.Hora+"\" ] \n";                        
                        if(aux.siguiente!=null)  texto+="      NBC"+aux.Clave+" -> NBC"+aux.siguiente.Clave+" [color=darkorchid4][dir=both]\n";
                        aux=aux.siguiente;                    
                }while(aux!=null);
                    texto+="   }\n";
                return texto;
            }
        }catch(Exception e){}
        return "";
    }
    
    public String UnionNodos(){
        try{
            if(Cabeza!=null){
                String texto="\n";
                NodoBC aux=Cabeza;  
                do{
                    texto+="NBC"+aux.Clave+" -> NDE"+aux.nodoDE.DPI+" [color=deeppink3][dir=both]\n";
                     texto+="NBC"+aux.Clave+" -> NTH"+aux.nodoTH.DPI+" [color=darkgreen][dir=both]\n";
                    aux=aux.siguiente;   
                }while(aux!=null);
                    texto+="  \n";
                return texto;   
                
            }
        }catch(Exception e){}
        return "";
    }
    
    public void ReporteCompleto(){
        try{                       
            String text="";            
            text += DE.GenerarLista()+"\n \n";
            text +=RecorridoBC()+"\n \n";
            text += TH.RecorridoTabla()+"\n \n";
            text+=UnionNodos();
            archivos.Archivo("ESTRUCTURAS",text, "ReporteTotal.txt");
            
        }catch(Exception e){}  
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
