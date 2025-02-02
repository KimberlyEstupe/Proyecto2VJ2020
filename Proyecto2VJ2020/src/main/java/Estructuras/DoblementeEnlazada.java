
package Estructuras;
import kimberlyestupe.proyecto2vj2020.Archivos;
import javax.swing.JOptionPane;//JOptionPane.showMessageDialog(null, "Aux: "+Cabeza.DPI, "ERROR", JOptionPane.ERROR_MESSAGE);


/**
 *
 * @author KimberlyEstupe
 */
public class DoblementeEnlazada {
    Archivos archivos=new Archivos();
    
    private NodoDE Cabeza;
    private NodoDE nodobuscado;
    
    public DoblementeEnlazada(){
        Cabeza=null;
        nodobuscado=null;
    }
    
    public NodoDE getCabeza(){
        return Cabeza;
    }
    //============================= Insertar ========================================================
    public void Insertar(String dpi, String tel, String direc, String tiL, String gen, String name, String lastname, String nacimiento){
        try{
                long dp=Long.parseLong(dpi);        
                NodoDE nuevo=new NodoDE(dp,tel,direc,tiL,gen,name,lastname, nacimiento);
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
                JOptionPane.showMessageDialog(null, "Nuevo conductor ingresado correctamente"); 
        }catch(Exception e){}
    }
    
    // ==================================== ELIMINAR ===========================================
    public void Eliminar(String dato){
        try{
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
        }catch(Exception e){}
    }
    
    //============================== MODIFICAR =============================================
    public void Modificar(String anterior, String dato, String tel, String direc, String tiL, String gen, String name, String lastname, String nacimiento){
       try{
            if(Cabeza!=null){
                NodoDE Nbuscado=Buscar(Long.parseLong(anterior));
                if(anterior.equalsIgnoreCase(dato)){
                    if(Nbuscado!=null){
                        long dp=Long.parseLong(dato);  
                        Nbuscado.nombre=name;
                        Nbuscado.apellido=lastname;
                        Nbuscado.Nacimiento=nacimiento;
                        Nbuscado.telefono=tel;
                        Nbuscado.direccion=direc;
                        Nbuscado.genero=gen;
                        JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS: \n DPI: "+Nbuscado.DPI+"\n Nombre: "+Nbuscado.nombre+" \n Apelliod: "+Nbuscado.apellido+"\n Genero: "+Nbuscado.genero+"\n Telefono: "+Nbuscado.telefono+"\n Tipo de Licencia: "+Nbuscado.tipoL+"\n Fecha de nacimiento: "+Nbuscado.Nacimiento);
                    }
                }else{
                    Eliminar(anterior);
                    Insertar(dato, tel, direc, tiL, gen, name, lastname, nacimiento);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "No hay datos ingresados");
            }
        }catch(Exception e){}
    }
    
    //============================== MOSTRAR INFORMACION DE UN DATO  =============================================
    public String MostrarInfo(String dato){
        try{
                if(Cabeza!=null){
                    long buscado=Long.parseLong(dato);
                    NodoDE aux=Buscar(buscado);
                    if(aux!=null){
                        JOptionPane.showMessageDialog(null, "DATOS ACTUALES: \nDPI: "+aux.DPI+"\n Nombre: "+aux.nombre+" \nApelliod: "+aux.apellido+"\n Genero: "+aux.genero+". \n Telefono: "+aux.telefono+"\n Tipo de Licencia: "+aux.tipoL);
                        nodobuscado=aux;
                        return aux.nombre;
                    }else{
                        JOptionPane.showMessageDialog(null, "Ese DPI no existe");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No hay datos ingresados");
                } 
                nodobuscado=null;
                
        }catch(Exception e){}
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
    public String Nacimiento(){
        return nodobuscado.Nacimiento;
    }
    
    public int Genero(){
        if(nodobuscado.genero.equalsIgnoreCase("Femenino")) return 0;
        else if(nodobuscado.genero.equalsIgnoreCase("Masculino")) return 1;
        return -1;
    }
    
    public int MiLincencia(){
        if (nodobuscado.tipoL.equalsIgnoreCase("A")) return 0;
        else if (nodobuscado.tipoL.equalsIgnoreCase("B")) return 1;
        else if (nodobuscado.tipoL.equalsIgnoreCase("C")) return 2;
        return -1;
    }
    
    //============================== BUSCAR =============================================
    public NodoDE Buscar(long buscar){
        try{
            if(Cabeza!=null){
                NodoDE aux=Cabeza;            
                do{
                    if(aux.DPI==buscar) return aux;
                    aux=aux.Sig;
                }while(aux!=Cabeza);
            }
        }catch(Exception e){}
        return null;
    }
    
    public String MonstrarConductore(){
        try{            
            if(Cabeza!=null){
                String texto="<html><body>";
                NodoDE aux=Cabeza;
                do{
                    texto+=aux.DPI+"<br>";
                    aux=aux.Sig;
                }while (aux!=Cabeza);
                texto+="</body></html>";
                return texto;
            }
        }catch(Exception e){}
        return "";
    }
    
    //============================== CARGA MASISIVA DE DATOS  =============================================
    public void CargMasiva(String filaname){
        try{
            String leer[]= archivos.leerArchivo(filaname);  
            String juntos;
            String divicion[];

            for(int i = 0; i<leer.length; i++){
                juntos=leer[i];
                divicion = juntos.split("%");
                        String dir = divicion[7].substring(0, divicion[7].length() - 1);
                        Insertar(divicion[0], divicion[6], dir, divicion[3], divicion[4], divicion[1], divicion[2], divicion[5]);            
            }
            JOptionPane.showMessageDialog(null, "Todos los datod fueron ingresados correctamente"); 
        }catch(Exception e){}
    }
    
    //============================== REPORTE =============================================
    public void ReporteListaD(){
        try{
            if(Cabeza!=null){            
                String text=GenerarLista();
                archivos.Archivo("Reporte de Conductores ",text, "ReporteLitsa.txt");
            }else{
                JOptionPane.showMessageDialog(null, "ERROR \n No hay datos ingresado ", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){}        
    }
    
    public String GenerarLista(){
        try{
            String texto=" rankdir = Lista; \n   node [shape=tab] \n   {rank=same \n";
            if(Cabeza!=null){
                NodoDE aux=Cabeza;          
                    do{   
                        texto+="      NDE"+aux.DPI+" [label= \"DPI: "+aux.DPI+
                                    "\\n         Nombre: "+aux.nombre+
                                    " \\n         Apelliod: "+aux.apellido+
                                    "\\n         Genero: "+aux.genero+
                                    "\\n         Telefono: "+aux.telefono+
                                    "\\n         Tipo de Licencia: "+aux.tipoL+
                                    "\\n         Direccion: "+aux.direccion+"\" ] \n";
                        texto+="      NDE"+aux.DPI+" -> NDE"+aux.Sig.DPI+" [color=blue3][dir=both]\n";
                        aux=aux.Sig;                    
                    }while(aux!=Cabeza);
                    texto+="   }\n";
                return texto;
            } 
            else{            
                return null;
            }
        }catch(Exception e){}
        return "";
    }
    
    public class NodoDE{
        NodoDE Sig;
        NodoDE Ante;
        long DPI;
        String telefono;
        String tipoL;
        String genero;
        String direccion;
        String nombre;
        String apellido;  
        String Nacimiento;
        int viajes;
        
        private NodoDE(long dpi, String tel, String direc, String tiL, String gen, String name, String lastname, String nacimiento){
            Sig = null;
            Ante = null;
            tipoL = tiL;
            telefono = tel;
            DPI = dpi;
            genero = gen;
            direccion = direc;
            nombre=name;
            apellido=lastname;
            Nacimiento = nacimiento;
            viajes=0;
        }
    }   
}
