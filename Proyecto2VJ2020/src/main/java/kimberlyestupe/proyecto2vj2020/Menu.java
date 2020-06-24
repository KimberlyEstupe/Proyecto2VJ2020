package kimberlyestupe.proyecto2vj2020;

/**
 *
 * @author KimberlyEstupe
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DoblementeEnlazada DE=new DoblementeEnlazada();
        
        DE.Insertar(20, 32, "Hola", 'A', "kk", "Nombre","Apellido");
        DE.Insertar(35, 32, "Hola", 'A', "kk", "Nombre","Apellido");
        DE.Insertar(45, 32, "Hola", 'A', "kk", "Nombre","Apellido");
        DE.Insertar(22, 32, "Hola", 'A', "kk", "Nombre","Apellido");
        DE.Insertar(85, 32, "Hola", 'A', "kk", "Nombre","Apellido");
        DE.Insertar(20, 32, "Hola", 'A', "kk", "Nombre2","Apellido");
        DE.Insertar(10, 32, "Hola", 'A', "kk", "Nombre","Apellido");
        DE.ReporteListaD();
        // TODO code application logic here
    }
    
}
