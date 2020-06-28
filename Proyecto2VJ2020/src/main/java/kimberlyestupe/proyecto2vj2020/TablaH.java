/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimberlyestupe.proyecto2vj2020;
import javax.swing.JOptionPane;
import kimberlyestupe.proyecto2vj2020.Archivos;
//https://www.youtube.com/watch?v=1_TVkiVaFgM&t=236s
/**
 *
 * @author KimberlyEstupe
 */
public class TablaH {
    Archivos archivos=new Archivos();
    private int Tam;
    private static int []PTams;
    private int Indice;
    private int Ocupados;
    private float PorcenOcupacion;  
    private float MaxOcupacion;
    private NodoTH []vectorH;
    
    public TablaH(){
        this.PTams=new int []{37,74,111,148,185,222,259,296,333,370,407,444,481,518,555,592,629,666,703,740,777,814,851,888,925};
        this.Indice=0;
        this.Ocupados=0;
        //this.MaxOcupacion=75;
        this.Tam=PTams[Indice];
        this.vectorH = new NodoTH[Tam];
        this.PorcenOcupacion=PorcentajeUtil();
    }
    
    
    
    private float PorcentajeUtil(){
        return (Ocupados*100)/Tam;
    }
    
    private long FunDispercion(long llv){
        return  llv%Long.valueOf(Tam);     
    }
    
    public void CargaMasiva(String filaname){
        String leer[]= archivos.leerArchivo(filaname);  
        String juntos;
        String divicion[];
        String dir;
        
        for(int i = 0; i<leer.length; i++){
            juntos=leer[i];
            divicion = juntos.split(",");
                    dir = divicion[6].substring(0, divicion[6].length() - 1); 
                    Insertar(divicion[0],divicion[1],divicion[2],divicion[3],divicion[4],divicion[5],dir);
        }
        
    }
    
    public void Insertar(String dpi, String nombre,String Ape, String gen, String nacimiento, String Tel, String dir){
        long dp=Long.parseLong(dpi);
        long pos=FunDispercion(dp);
        if(pos>Tam) pos-=Tam;
        NodoTH nuevo = new NodoTH(dp,nombre, Ape, gen, nacimiento, Tel, dir);
        if(vectorH[(int)pos]==null || vectorH[(int)pos].estado==1){
            Ocupados++;
            JOptionPane.showMessageDialog(null, "Insertar");
            PorcenOcupacion=PorcentajeUtil();
        }/*else{
             NodoTH posiguiente = vectorH[(int)pos];
             nuevo.siguiente = posiguiente;
             posiguiente.anterior = nuevo;
        }*/
        vectorH[(int)pos] = nuevo;
        
        if(PorcenOcupacion>75){
            ReHashing();
        }
    }
    
    public void ReHashing(){
    }
    
    public void ReporteTable(){
        String text=RecorridoTabla();
        archivos.Archivo("Clientes del Sistema", text, "TablaHash.txt");
    }
    
    public String RecorridoTabla(){
        String texto = "";
        
        
        return texto;
    }
    
    
    private class NodoTH{
        NodoTH siguiente;
        NodoTH anterior;
        long DPI;
        String Nombre;
        String Apellido;
        String Genero;
        String Telefono;
        String Direccion;
        String Nacimiento;
        int estado;
        NodoTH(long dpi, String nombre,String Ape, String gen, String nacimiento, String Tel, String dir){
            siguiente = null;
            DPI = dpi;
            Nombre = nombre;
            Apellido = Ape;
            Genero = gen;
            Telefono = Tel;
            Direccion = dir;
            estado=0;
            Nacimiento = nacimiento;
            anterior = null;
        }
    }    
}
