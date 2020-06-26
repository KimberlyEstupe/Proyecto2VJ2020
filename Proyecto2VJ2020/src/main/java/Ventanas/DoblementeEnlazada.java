
package Ventanas;

import kimberlyestupe.proyecto2vj2020.Archivos;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);

/**
 *
 * @author KimberlyEstupe
 */
public class DoblementeEnlazada {
    Archivos archivos=new Archivos();
    NodoDE Cabeza;
    
    public DoblementeEnlazada(){
        Cabeza=null;
    }
    
    public void Insertar(String dpi, String tel, String direc, String tiL, String gen, String name, String lastname){
        long dp=Long.parseLong(dpi);
        NodoDE nuevo=new NodoDE(dp,tel,direc,tiL,gen,name,lastname);
        if(Cabeza==null){
            Cabeza=nuevo;
            Cabeza.Sig=Cabeza;      
        }else{            
            NodoDE aux=Cabeza;
            if(Cabeza.DPI>dp){
                aux=Cabeza.Ante;
                nuevo.Sig=Cabeza;
                nuevo.Ante=aux;
                aux.Sig=nuevo;                
                Cabeza.Ante=nuevo;
                Cabeza=nuevo;
            }else{
                while(aux.Sig!=Cabeza && aux.Sig.DPI<=dp){
                aux=aux.Sig;
                }
                if(aux.DPI==dp){}
                else if(aux.Sig.DPI>dp){
                    NodoDE pos3=aux.Sig;
                    nuevo.Sig=pos3;
                    nuevo.Ante=aux;
                    aux.Sig=nuevo;
                    pos3.Ante=nuevo;
                }else if(aux.Sig==Cabeza){
                    nuevo.Sig=Cabeza;
                    nuevo.Ante=aux;
                    aux.Sig=nuevo;                
                    Cabeza.Ante=nuevo;                
                } 
            }                        
        }
        System. out. println("Ingresado\n"); 
    }
    
    public void Eliminar(String dato){
        long buscado=Long.parseLong(dato);
        NodoDE Nbuscado=Buscar(buscado);
        if(Nbuscado!=null){
            NodoDE anterior=Nbuscado.Ante;
            NodoDE siguiente=Nbuscado.Sig;
            siguiente.Ante=anterior;
            anterior.Sig=siguiente; 
            if(Nbuscado==Cabeza) Cabeza=siguiente;
            Nbuscado.Sig=null;
            Nbuscado.Ante=null;
            JOptionPane.showMessageDialog(null, "Dato Eliminado Exitosammente");
        }
    }
    
    public void MostrarInfo(String dato){
        long buscado=Long.parseLong(dato);
        NodoDE aux=Buscar(buscado);
        if(aux!=null){
            JOptionPane.showMessageDialog(null, "DPI: "+aux.DPI+"\n Nombre: "+aux.nombre+" \nApelliod: "+aux.apellido+"\n Genero: "+aux.genero+"\n Telefono: "+aux.telefono+"\n Tipo de Licencia: "+aux.tipoL);
        }
    }
    
    public NodoDE Buscar(long buscar){
        if(Cabeza!=null){
            NodoDE aux=Cabeza;            
            do{
                if(aux.DPI==buscar) return aux;
                aux=aux.Sig;
            }while(aux!=Cabeza);
        }
        return null;
    }
    
    public void ReporteListaD(){
        if(Cabeza!=null){            
            String texto=GenerarLista();
            archivos.Archivo("Doblemente Enlazada",texto, "ReporteLitsa.txt");
        }else{
            JOptionPane.showMessageDialog(null, "ERROR \n No hay datos ingresado ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
    public String GenerarLista(){
        String texto="   {rank=same \n";
        if(Cabeza!=null){
            NodoDE aux=Cabeza;          
                do{   
                    texto+="      NDE"+aux.DPI+" [label= \"DPI: "+aux.DPI+"\n         Nombre: "+aux.nombre+" \n         Apelliod: "+aux.apellido+"\n         Genero: "+aux.genero+"\n         Telefono: "+aux.telefono+"\n         Tipo de Licencia: "+aux.tipoL+"\n         Direccion: "+aux.direccion+"\" ] \n";
                    texto+="      NDE"+aux.DPI+" -> NDE"+aux.Sig.DPI+" [color=blue3][dir=both]\n";
                    aux=aux.Sig;                    
                }while(aux!=Cabeza);
                texto+="   }\n";
            return texto;
        } 
        else{            
            return null;
        }
    }
    
    private class NodoDE{
        public NodoDE Sig;
        public NodoDE Ante;
        public long DPI;
        public String telefono;
        public String tipoL;
        public String genero;
        public String direccion;
        public String nombre;
        public String apellido;        
        public NodoDE(long dpi, String tel, String direc, String tiL, String gen, String name, String lastname){
            Sig = null;
            Ante = null;
            tipoL = tiL;
            telefono = tel;
            DPI = dpi;
            genero = gen;
            direccion = direc;
            nombre=name;
            apellido=lastname;
        }
    }   
}
