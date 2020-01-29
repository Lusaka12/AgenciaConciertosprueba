/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class CColaboracion extends Concierto {
    
    public CColaboracion() {
    }
    
    public CColaboracion (Date fechaHora,ArrayList<Actuacion> listaActuaciones) {
        super(fechaHora,listaActuaciones);   
    }
    
    public CColaboracion (CColaboracion ccolaboracion) {
        super (ccolaboracion);
    }

    @Override
    public String data() {
        return super.data();
    }
    public static CColaboracion nuevoColaboracion() {
        Concierto concierto=new CColaboracion();;
        Scanner in=new Scanner(System.in);
        boolean confirmacion;
        do{   
        Concierto.nuevoConcierto(concierto);
        confirmacion=ToolBox.readBoolean();
        } while (confirmacion!=true);
        in.close();
        return (CColaboracion)concierto;
  
    }
}
