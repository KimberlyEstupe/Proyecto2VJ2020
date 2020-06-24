/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kimberlyestupe.proyecto2vj2020;

/**
 *
 * @author KimberlyEstupe
 */
public class TablaH {
    
    private class NodoTH{
        NodoTH siguiente;
        int DPI;
        String Nombre;
        String Apellido;
        String Genero;
        String Telefono;
        String Direccion;
        NodoTH(int dpi, String nombre,String Ape, String gen, String Tel, String dir){
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
