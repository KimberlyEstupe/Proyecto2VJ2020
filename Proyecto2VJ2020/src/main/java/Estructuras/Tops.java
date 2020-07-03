/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import javax.swing.JOptionPane;

import Estructuras.DoblementeEnlazada.NodoDE;
import Estructuras.TablaH.NodoTH;

/**
 *
 * @author KimberlyEstupe
 */
public class Tops {
    NodoT Cabeza;
    DoblementeEnlazada DE;
    TablaH TH;
    private void Insertar (String dpi, String nombre, String apellido, String genero, String nacimiento, String telefono, String direccion, String licencia, int viajes){
        try{ 
                NodoT nuevo=new NodoT(dpi, nombre, apellido, genero, nacimiento, telefono, direccion, licencia, viajes);
                if(Cabeza==null){
                    Cabeza=nuevo;     
                }else{            
                    NodoT aux=Cabeza;
                    if(Cabeza.viajes<viajes){
                        nuevo.Sig=Cabeza;               
                        Cabeza.Ante=nuevo;
                        Cabeza=nuevo;
                    }else{
                        while(aux.Sig!=null && aux.Sig.viajes>=viajes){
                            aux=aux.Sig;
                        }
                        
                        if(aux.Sig==null){
                            aux.Sig=nuevo;
                            nuevo.Ante = aux;
                        }
                        else{
                            NodoT pos3=aux.Sig;
                            nuevo.Sig=pos3;
                            nuevo.Ante=aux;
                            aux.Sig=nuevo;
                            pos3.Ante=nuevo;
                        } 
                    }                        
                }
                
        }catch(Exception e){}
    }
    
    public void InsertTHClientes(TablaH th){
        try{
        TH = th;
        NodoTH aux;
        NodoTH vector[] = TH.getTabla();
        int tamano=TH.getTam();
        
        for (int i=0; i<tamano; i++){
            aux=vector[i];
                if(aux!=null){
                    do{
                        if(aux.viajes>0) Insertar(String.valueOf(aux.DPI), aux.Nombre, aux.Apellido, aux.Genero, aux.Nacimiento, aux.Telefono, aux.Direccion, "", aux.viajes);
                        aux=aux.siguiente;
                    }while(aux!=null);
                }
        }
        
        }catch(Exception e){}
        
    }
    
    
    public void InsertDEConductor (DoblementeEnlazada de){
        try{
        DE = de;
        NodoDE cabezaDE = DE.getCabeza();
        if(cabezaDE != null){        
            NodoDE aux= cabezaDE;
            do{
                if (aux.viajes>0) Insertar(String.valueOf(aux.DPI), aux.nombre, aux.apellido, aux.genero, aux.Nacimiento, aux.telefono, aux.direccion, aux.tipoL, aux.viajes);
                aux = aux.Sig;
            }while(aux != cabezaDE && aux!=null);
            JOptionPane.showMessageDialog(null, "Datos ingresados"); 
        }
        }catch(Exception e){}
    }
    
    public String Top(){
        try{
            if(Cabeza != null){
                NodoT aux = Cabeza;
                String texto="";
                int contador = 1;
                do{  
                    texto +=" <br>"+contador+") "+aux.DPI+", "+aux.Nombre+", "+aux.Apellido+" "+aux.Genero+" ("+aux.viajes+")";
                    aux = aux.Sig;
                    contador++;
                }while(aux!=null && contador <=10);                
                return texto;
            }
        }catch(Exception e){}   
        return "";
    }
    
    public class NodoT{
        NodoT Sig;
        NodoT Ante;
        String DPI;
        String Telefono;
        String Licencia;
        String Genero;
        String Direccion;
        String Nombre;
        String Apellido;  
        String Nacimiento;
        int viajes;
        
        private NodoT(String dpi, String nombre, String apellido, String genero, String nacimiento, String telefono, String direccion, String licencia, int v){
            Sig = null;
            Ante = null;
            Licencia = licencia;
            Telefono = telefono;
            DPI = dpi;
            Genero = genero;
            Direccion = direccion;
            Nombre=nombre;
            Apellido=apellido;
            Nacimiento = nacimiento;
            viajes=v;
        }
    } 
}
