/*
 *
 */
package agenciaconciertos;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.text.ParseException;
import java.util.ArrayList;

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
    
    public static ArrayList<String> separaPorCampos(String linea){
        ArrayList<String> listaAtributos=new ArrayList<>();
        String atributo = "";
        char caracter;
        for(int i =0 ;i<linea.length();i++){
            caracter=linea.charAt(i);
            if(caracter!='|'){
                atributo+=caracter;
            }else{
                listaAtributos.add(atributo);
                atributo="";
            }
        }
        return listaAtributos;
    }
}
