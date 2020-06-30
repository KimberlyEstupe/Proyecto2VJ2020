package Estructuras;
import javax.swing.JOptionPane;
import kimberlyestupe.proyecto2vj2020.Archivos;
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
    private NodoTH []vectorH;
    public NodoTH NodoBuscado;
    
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
        try{
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
            JOptionPane.showMessageDialog(null, "Todos los datos fueron insertados correctamente");
        }catch(Exception e){
            
        }
    }
    
    public void Insertar(String dpi, String nombre,String Ape, String gen, String nacimiento, String Tel, String dir){
        try{
                long dp=Long.parseLong(dpi);
                long pos=FunDispercion(dp);
                if(pos>Tam) pos-=Tam;
                NodoTH nuevo = new NodoTH(dp,nombre, Ape, gen, nacimiento, Tel, dir);        
                if(vectorH[(int)pos]==null || vectorH[(int)pos].estado==1){
                    Ocupados++;
                    PorcenOcupacion=PorcentajeUtil();
                    if(PorcenOcupacion>=75){
                        ReHashing();
                    }
                }else{
                     NodoTH posiguiente = vectorH[(int)pos];
                     nuevo.siguiente = posiguiente;
                     posiguiente.anterior = nuevo;
                }
                vectorH[(int)pos] = nuevo;  
        }catch(Exception e){            
        }
    }
    
    public void ReHashing(){
        try{
            NodoTH vectorAux[]=vectorH;
            int tamanoAux=Tam;
            if(Indice<PTams.length){
                Indice++;
                if(Indice==PTams.length-1) JOptionPane.showMessageDialog(null, "Llego al maximo de la tabla, ya no podra realizar otro Rehashing");
                Tam=PTams[Indice];
                vectorH = new NodoTH[Tam];
                Ocupados=0;
                PorcenOcupacion=PorcentajeUtil();

                for(int i = 0; i<tamanoAux; i++){
                    if(vectorAux[i]!=null){
                        NodoTH aux=vectorAux[i];
                        do{
                            Insertar(String.valueOf(aux.DPI), aux.Nombre, aux.Apellido, aux.Genero,aux.Nacimiento, aux.Telefono, aux.Direccion);
                            aux=aux.siguiente;
                        }while(aux!=null);
                    }
                }
            }
        }catch(Exception e){            
        }
    }
    
    public NodoTH Busqueda(String DPI){
        try{
            long dp=Long.parseLong(DPI);
            long pos=FunDispercion(dp);
            if(pos>Tam) pos-=Tam;
            NodoTH aux=vectorH[(int)pos];
            while(aux!=null && aux.DPI!=dp){
                aux=aux.siguiente;
            }
            if(aux.DPI==dp) return aux;    
        }catch(Exception e){            
        }
        return null;
    }
    //============================= Mostrar ==========================================
    public String MiDPI(String dpi){        
        if(Busqueda(dpi)!=null){ 
            NodoBuscado=Busqueda(dpi);
            return String.valueOf(Busqueda(dpi).DPI);}
        else return "nada";
    }
    
    public String BNombre(){return NodoBuscado.Nombre;}    
    public String BApellido(){ return NodoBuscado.Apellido;}
    public String BDirección(){return NodoBuscado.Direccion;}
    public String BTelefono(){return NodoBuscado.Telefono;}
    public String BNacimiento(){return NodoBuscado.Nacimiento;}
    
    public int BGenero(){
        if(NodoBuscado.Genero.equalsIgnoreCase("Femenino")) return 0;
        else if(NodoBuscado.Genero.equalsIgnoreCase("Masculino")) return 1;
        return -1;
    }
    //============================= ELIMINAR ==========================================
    public void Eliminar(String dato){
        try{
            long dp=Long.parseLong(dato);
            long pos=FunDispercion(dp);
            if(pos>Tam) pos-=Tam;
            NodoTH aux=vectorH[(int)pos];
            if(aux.DPI==dp){// si es el primer valor
                if(aux.siguiente==null){
                    vectorH[(int)pos]=null;
                    aux=null;
                    Ocupados--;
                }else{
                    NodoTH aux2=aux.siguiente;
                    NodoTH aux3=aux2.siguiente;
                    aux.siguiente=aux3;
                    aux3.anterior=aux;
                    vectorH[(int)pos]=aux2;
                    aux2=null;
                }
            }else{//Si esta en la lista
                while(aux!=null && aux.DPI!=dp){
                    aux=aux.siguiente;
                }
                if(aux.DPI==dp){
                    NodoTH ante=aux.anterior;
                    NodoTH Sig=aux.siguiente;
                    ante.siguiente=Sig;
                    Sig.anterior=ante;
                    aux=null;
                }else{
                    JOptionPane.showMessageDialog(null, "Este dato no exise");
                }
            }
        }catch(Exception e){            
        }
    }
    
    //============================= Modificar ==========================================
    public void Modificar(String anterior,String dpi, String nombre,String Ape, String gen, String nacimiento, String Tel, String dir){
        try{
            if(anterior.equalsIgnoreCase(dpi)){
                NodoTH BAux = Busqueda(dpi);
                if (BAux!=null){
                    BAux.Nombre = nombre;
                    BAux.Apellido = Ape;
                    BAux.Genero=gen;
                    BAux.Nacimiento=nacimiento;
                    BAux.Telefono=Tel;
                    BAux.Direccion=dir;
                }
            }else{
                Eliminar(anterior);
                Insertar(dpi, nombre, Ape, gen, nacimiento, Tel, dir);
            }
            JOptionPane.showMessageDialog(null, "Dato modificado");
        }catch(Exception e){            
        }
    } 
    
    public String MostrarClientes(){        
        try{
            NodoTH aux;
            String texto="<html><body>";
            for(int i=0; i< Tam; i++){
                aux=vectorH[i];
                if(aux!=null){
                    do{
                        texto+=aux.DPI+" <br>";
                        aux=aux.siguiente;
                    }while(aux!=null);
                }
            }
            texto+="</body></html>";
            return texto;
        }catch(Exception e){}
        
        return "";
    }
    //============================= REPORTE ==========================================
    public void ReporteTabla(){
        String text="   rankdir=LR; \n   node [shape=record,width=.1,height=.1]; \n";
        text+=RecorridoTabla();
        archivos.Archivo("Clientes del Sistema", text, "TablaHash.txt");
    }
    
    public String RecorridoTabla(){
        try{
                NodoTH aux;        
                String texto = "   N0 [label = \" \n";
                //Creando tabla
                for(int i=0; i<Tam; i++){
                    if(vectorH[i]!=null){ texto+="    <f"+vectorH[i].DPI+"> DPI: "+vectorH[i].DPI+
                            " \\n Nombre:"+vectorH[i].Nombre+" "+vectorH[i].Apellido+
                            " \\n Fecha de Nacimiento:"+vectorH[i].Nacimiento+
                            " \\n Genero:"+vectorH[i].Genero+
                            ", Telefono:"+vectorH[i].Telefono+
                            " \\n Dirección:"+vectorH[i].Direccion;}
                    else texto+="    <f> "+i;
                    if(i!=Tam-1) texto+="|\n";
                }

                texto+="   \", height=2.5]; \n    node [shape=component] \n";

                //Listas enlazadas 
                for(int i = 0; i<Tam-1; i++){
                    aux=vectorH[i];
                    if(aux!=null && aux.siguiente!=null){
                        texto+="\n    N0:f"+aux.DPI+" -> N"+aux.siguiente.DPI+" \n";
                        aux=aux.siguiente;
                        texto+="    N"+aux.DPI+"    [label= \" DPI: "+aux.DPI+
                                " \\n Nombre:"+aux.Nombre+" "+aux.Apellido+
                            " \\n Fecha de Nacimiento:"+aux.Nacimiento+
                            " \\n Genero:"+aux.Genero+
                            ", Telefono:"+aux.Telefono+
                            " \\n Dirección:"+aux.Direccion+
                                "\" ] \n";
                        while(aux.siguiente!=null){
                            texto+="    N"+aux.DPI+" -> N"+aux.siguiente.DPI+" \n";
                            aux=aux.siguiente;
                            texto+="    N"+aux.DPI+"    [label= \" DPI: "+aux.DPI+
                                " \\n Nombre:"+aux.Nombre+" "+aux.Apellido+
                            " \\n Fecha de Nacimiento:"+aux.Nacimiento+
                            " \\n Genero:"+aux.Genero+
                            ", Telefono:"+aux.Telefono+
                            " \\n Dirección:"+aux.Direccion+
                                "\" ] \n";
                        }
                    }
                }

                return texto;
        }catch(Exception e){            
        }
        return "";    
    }
    
    
    public class NodoTH{
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
        public NodoTH(long dpi, String nombre,String Ape, String gen, String nacimiento, String Tel, String dir){
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
