/*
 *
 */
package agenciaconciertos;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.text.ParseException;

/**
 * clase que contiene  metodos utiles para otras clases
 * @author DAW102
 */
public class ToolBox {
    
    public static Date readDate(){
        try {
            Scanner sc=new Scanner(System.in);
            String fechaTexto = sc.nextLine();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            Date fecha = (Date) df.parse(fechaTexto);
            
        return fecha;
           
                   
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean readBoolean(){
        Scanner sc=new Scanner(System.in);
        char confirmacion;
        do{
            confirmacion = sc.next().toLowerCase().charAt(0);
            if(confirmacion == 's'||confirmacion=='n'){
                return confirmacion=='s';
            }
        }while(true);
    }
}
