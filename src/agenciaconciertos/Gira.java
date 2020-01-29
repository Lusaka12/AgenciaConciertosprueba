/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author david
 * @version 1.01
 */
public class Gira {
    protected long id;//atributo que sirve para identificar a la gira //  valores validos numero entero mayor que 0
    public Date fechaApertura;//atributo que sirve para identificar la fecha de apertura// valores validos cadena de caracteres de 20 caracteres
    public Date fechaCierre;//atributo que sirve para identificar la fecha de cierre // valores validos cadena de caracteres de 20 caracteres
    private String nombre;// atributo que sirve para identificar el nombre de la gira // valores validos "" si no se ha aportado ningun codigo y un codigo si se ha aportad
    private ArrayList<Concierto> listaConciertos;//lista de conciertos que hay en la gira// valores permitidos minimo 1 y maximo N

    public String getNombre() {
        return nombre;
    }

    public void setNombreNegociar(String nombreNegociar) {
        this.nombre = nombre;
    } 
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public ArrayList<Concierto> getListaConciertos() {
        return listaConciertos;
    }

    public void setListaConciertos(ArrayList<Concierto> listaConciertos) {
        this.listaConciertos = listaConciertos;
    }

    public Gira(long id, Date fechaApertura, Date fechaCierre, String nombre, ArrayList<Concierto> listaConciertos) {
        this.id = id;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.nombre = nombre;
        this.listaConciertos = listaConciertos;
    }
    
    public Gira(Gira g) {
        this.fechaApertura = g.getFechaApertura();
        this.fechaCierre = g.getFechaCierre();
        this.listaConciertos = g.getListaConciertos();
     }
            
    public Gira () {
        listaConciertos=new ArrayList<Concierto>();
    }
    
    @Override
    public String toString() {
        return "Gira" + "identificador=" + id + ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre + ", nombre="+nombre+ ", listaConciertos=" + listaConciertos + '}';
    }
    
    public String data(){
        
        return this.getId()+"|" +this.getFechaApertura()+ "|"+this.getFechaCierre()+"|"+this.getNombre();
       
    }
    
    public Gira getGiraById(long id){
        /*for (Gira gira : listaGiras) {
           if (gira.getId() == id) {
               return gira;
           }
        }*/
        return null;
    }
    
    
    public ArrayList<Gira> getAllGira (){
        ArrayList<Gira> nuevaListaGiras = new ArrayList<Gira>();
        /*
        for(Gira gira:listaGira) {
            nuevaListaGira.add(gira);
        }
        */
        /*Este método recorrerá un ArrayList con todas las giras comparando con el id 
        que le introduzcamos, y devolverá esa gira si es que existe o 
        nulo si es que no existe*/
    return nuevaListaGiras;
    }
    
    public Gira nuevoGira(){
    Gira gira = new Gira();
    
    Scanner in=new Scanner (System.in);
    boolean confirmacion;
    do{
    System.out.println(" ¿Cuándo empieza la gira?");
    Date fecha= ToolBox.readDate();
    gira.setFechaApertura(fecha);
    System.out.println(" ¿Cuándo acaba la gira?");
    fecha= ToolBox.readDate();
    gira.setFechaCierre(fecha);
    confirmacion=ToolBox.readBoolean();
    }while (confirmacion!=true);
    in.close();
    return gira;
    }
    
    public Concierto getConciertoByPos(int i){
        return listaConciertos.get(i);
    }
}
