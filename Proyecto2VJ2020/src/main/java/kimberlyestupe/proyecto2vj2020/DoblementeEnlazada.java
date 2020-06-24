package kimberlyestupe.proyecto2vj2020;
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
    
    public void Insertar(int dpi, int tel, String direc, char tiL, String gen, String name, String lastname){
        NodoDE nuevo=new NodoDE(dpi,tel,direc,tiL,gen,name,lastname);
        if(Cabeza==null){
            Cabeza=nuevo;
            Cabeza.Sig=Cabeza;      
        }else{            
            NodoDE aux=Cabeza;
            if(Cabeza.DPI>dpi){
                aux=Cabeza.Ante;
                nuevo.Sig=Cabeza;
                nuevo.Ante=aux;
                aux.Sig=nuevo;                
                Cabeza.Ante=nuevo;
                Cabeza=nuevo;
            }else{
                while(aux.Sig!=Cabeza && aux.Sig.DPI<=dpi){
                aux=aux.Sig;
                }
                if(aux.DPI==dpi){}
                else if(aux.Sig.DPI>dpi){
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
    
    public void ReporteListaD(){
        if(Cabeza!=null){
            NodoDE aux=Cabeza;
            String texto="digraph G { \n rankdir = Lista; \n" +
                        "   node [shape = rectangle fontname=\"Arial\" fontsize=\"10\"]\n" +
                        "   graph [nodesep = 0.4]\n" +
                        "   label = < <font color='mediumvioletred'> <font point-size='20'> DOBLEMENTE   ENLAZADA </font></font>>;\n" +
                        "   labelloc = \"t\"; \n   {rank=same \n" ;           
                do{   
                    texto+="      NDE"+aux.DPI+" [label= \""+aux.nombre+","+aux.apellido+"\" ] \n";
                    texto+="      NDE"+aux.DPI+" -> NDE"+aux.Sig.DPI+" [color=blue3][dir=both]\n";
                    aux=aux.Sig;                    
                }while(aux!=Cabeza);
            texto+="   }\n}";
            archivos.Archivo(texto, "ReporteLitsa.txt");
        }        
    }
    
    private class NodoDE{
        public NodoDE Sig;
        public NodoDE Ante;
        public int DPI;
        public int telefono;
        public char tipoL;
        public String genero;
        public String direccion;
        public String nombre;
        public String apellido;        
        public NodoDE(int dpi, int tel, String direc, char tiL, String gen, String name, String lastname){
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