/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimberlyestupe.proyecto2vj2020;
//https://www.youtube.com/watch?v=1_TVkiVaFgM&t=236s
/**
 *
 * @author KimberlyEstupe
 */
public class TablaH {
    private int Tam;
    private static int []Tams;
    private int Indice;
    private int Ocupados;
    private float PorcenOcupacion;  
    private float MaxOcupacion;
    private NodoTH []vecHash;
    
    private TablaH(){
        this.Tams=new int []{37,74,111,148,185,222,259,296,333,370,407,444,481,518,555,592,629,666,703,740,777,814,851,888,925};
        this.Indice=0;
        this.Ocupados=0;
        this.MaxOcupacion=65;
        this.Tam=Tams[Indice];
        this.PorcenOcupacion=PorcentajeUtil();
    }
    
    private float PorcentajeUtil(){
        return (Ocupados*100)/Tam;
    }
    
    private long FunDispercion(long llv){
        return  llv%Long.valueOf(Tam);     
    }
    
    private class NodoTH{
        NodoTH siguiente;
        long DPI;
        String Nombre;
        String Apellido;
        String Genero;
        String Telefono;
        String Direccion;
        NodoTH(long dpi, String nombre,String Ape, String gen, String Tel, String dir){
            siguiente=null;
            DPI=dpi;
            Nombre=nombre;
            Apellido=Ape;
            Genero=gen;
            Telefono=Tel;
            Direccion=dir;
        }
    }    
}
