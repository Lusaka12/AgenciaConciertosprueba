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
 * @author david
 * @version 1.01
 */
public class Concierto {
    protected long id; //atributo que sirve para identificar al concierto //  valores validos numero entero mayor que 0
    private Date fechaHora; //atributo que sirve para idenfiticar la fechaHora y la hora del concierto// valores validos cadena de caracteres de 20 caracteres
    protected ArrayList<Actuacion> listaActuaciones;//lista que contiene las actuaciones de un concierto //minimo 5 y maximo 10.
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public ArrayList<Actuacion> getListaActuaciones() {
        return listaActuaciones;
    }

    public void setListaArtistas(ArrayList<Actuacion> listaActuaciones) {
        this.listaActuaciones = listaActuaciones;
    }
    public void anadeActuacion(Actuacion actuacion){
        listaActuaciones.add(actuacion);
    }
    protected Concierto(Date fechaHora,ArrayList<Actuacion> listaActuaciones) {
        this.fechaHora = fechaHora;
        this.listaActuaciones=listaActuaciones;
        }
    
    protected Concierto(Concierto c) {
        this.fechaHora = c.getFechaHora();
        listaActuaciones=c.getListaActuaciones();
    }
    
    protected Concierto() {
        listaActuaciones=new ArrayList<Actuacion>();
    }
    @Override
    public String toString() {
        return "Concierto{" + "identificador=" + id + ", fecha=" + fechaHora +'}';
    }
   
    public String data() {
        
        return this.getId()+"|"+this.getFechaHora();
    }
    public Concierto getConciertoById (long id){
        /*
        for(Concierto concierto:listaConciertos) {
            if (concierto.getId()==id) {
                return concierto;
            }
        }    
        */
        return null;
    }
    public ArrayList<Concierto> getAllConciertos (){
        ArrayList<Concierto> nuevaListaConciertos=new ArrayList<Concierto>();
        /*for(Concierto concierto:listaConcierto) {
            nuevaListaconciertos.add(concierto);
        } 
        */  
        return nuevaListaConciertos;
    }
    public static Concierto nuevoConcierto() {
        Concierto concierto=new Concierto();
        Scanner in=new Scanner(System.in);
        boolean confirmacion;
        do{   
        System.out.println("Es un concierto invidual o colaborativo"); 
        String aux=in.next().trim().toLowerCase();
        if(aux=="individual"){
            concierto=new CColaboracion();
        }else{
            if(aux=="colaborativo"){
                concierto=new CIndividual();
            }
        }          
        System.out.println("¿Qué día tienes el concierto?");
        Date fecha= ToolBox.readDate();
        concierto.setFechaHora(fecha);

        confirmacion=ToolBox.readBoolean();
        } while (confirmacion!=true);
        in.close();
        return concierto;         
       
    }
    public static Concierto nuevoConcierto(Concierto c) {
        
        Scanner in=new Scanner(System.in);
        boolean confirmacion;
        do{ 
       
        System.out.println("cuantas actuaciones tiene el concierto");
        int n=in.nextInt();
        for(int i=0;i<n;i++){
            c.anadeActuacion(Actuacion.nuevaActuacion());
        }
                  
        System.out.println("¿Qué día tienes el concierto?");
        Date fecha= ToolBox.readDate();
        c.setFechaHora(fecha);

        confirmacion=ToolBox.readBoolean();
        } while (confirmacion!=true);
        in.close();
        return c;         
    }
    public Actuacion getActuacionByPos(int i){
        return listaActuaciones.get(i);
    }
}


