
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
    NodoDE nodobuscado;
    
    public DoblementeEnlazada(){
        Cabeza=null;
        nodobuscado=null;
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
        JOptionPane.showMessageDialog(null, "Accion Ejecutada Exitosammente"); 
    }
    
    public void Eliminar(String dato){
        if(Cabeza!=null){
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
                JOptionPane.showMessageDialog(null, "Accion Ejecutada Exitosammente");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay datos ingresados");
        } 
    }
    
    public void Modificar(String anterior, String dato, String tel, String direc, String tiL, String gen, String name, String lastname){
        Eliminar(anterior);
        Insertar(dato, tel, direc, tiL, gen, name, lastname);
    }
    
    public String MostrarInfo(String dato){
        if(Cabeza!=null){
            long buscado=Long.parseLong(dato);
            NodoDE aux=Buscar(buscado);
            if(aux!=null){
                JOptionPane.showMessageDialog(null, "DATOS ACTUALES: \nDPI: "+aux.DPI+"\n Nombre: "+aux.nombre+" \nApelliod: "+aux.apellido+"\n Genero: "+aux.genero+"\n Telefono: "+aux.telefono+"\n Tipo de Licencia: "+aux.tipoL);
                nodobuscado=aux;
                return aux.nombre;
            }else{
                JOptionPane.showMessageDialog(null, "Ese DPI no existe");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay datos ingresados");
        } 
        nodobuscado=null;
        return "";
    }
    
    public String MiDpi(){
        return String.valueOf(nodobuscado.DPI);
    }
    public String Apellido(){
        return nodobuscado.apellido;
    }
    
    public String Direccion(){
        return nodobuscado.direccion;
    }
    
    public String Telefono(){
        return nodobuscado.telefono;
    }
    
    public int Genero(){
        int index=0;
        if(nodobuscado.genero=="Femenino") index=0;
        else if(nodobuscado.genero=="Masculino") index=1;
        return index;
    }
    
    public int MiLincencia(){
        int index=0;
        if (nodobuscado.tipoL=="A") index=0;
        else if (nodobuscado.tipoL=="B") index=1;
        else if (nodobuscado.tipoL=="C") index=2;
        return index;
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
    
    public void CargMasiva(String filaname){
        String leer[]= archivos.leerArchivo(filaname);        
        String datos[];
        for(String linea : leer){
	       datos = linea.split("%");
               for(String dos : datos){
                   System.out.println(dos);
               }
        }
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
