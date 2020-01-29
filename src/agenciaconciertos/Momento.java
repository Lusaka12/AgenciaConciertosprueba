/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author DAW102
 */
public class Momento {
    protected  long id;//atributo que sirve para identificar a la momento //  valores validos numero entero mayor que 0
    private Date hora;//Fecha de tipo util.date que guarda la fecha en la que el reportero documenta la actuacion // los valores validos para este campo seran posteriores a la fecha del concierto pero no mas de un dia. 
    private String descripcion;//descripcion del momento que rellenara el reportero// los valores validos seran una cadena de 150 caracteres como maximo 
    private Reportero reportero; // Objeto que guardara que reportero ha documentado el momento // valores validos un objeto del reportero que captura el momento
    private Actuacion actuacion; // la actuacion a la cual se esta documentando // valores validos un objeto de la actuacion
    
    public Momento(Date hora, String descripcion, Reportero reportero,Actuacion actuacion) {
        this.hora = hora;
        this.descripcion = descripcion;
        this.reportero= reportero;
        this.actuacion=actuacion;
    }
    public Momento(Momento m) {
        this.hora = m.getHora();
        this.descripcion = m.getDescripcion();
        this.reportero= m.getReportero();
        this.actuacion=m.getActuacion();
    } 

    public Actuacion getActuacion() {
        return actuacion;
    }

    public void setActuacion(Actuacion actuacion) {
        this.actuacion = actuacion;
    }
    public Momento(){
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Reportero getReportero() {
        return reportero;
    }

    public void setReportero(Reportero reportero) {
        this.reportero = reportero;
    }
    
    /*public Momento getMomentobyid (long id) {
        for (int i=0;i<listaobjetos;i++){
            Momento aux=listaobjetos.get(i);
            if (aux.getID()==id){
                return aux;
    */
    
    /*public ArrayList<Momento> getAllMomento(){
        ArrayList<Momento> LMM = new ArrayList<Momento>();
        for (int i=0;i<listaobjetos.size();i++){
            Momento aux=listaobjetos.get(i);
            LMM.add(aux);
        }
        return LMM;
    }*/
    public Momento nuevoMomento(){
         
        Momento momento= new Momento();
        boolean confirmacion;
        Scanner sc= new Scanner(System.in);
        do{            
            System.out.println("¿a qué hora ocurrió el momento? formato:dd/MM/yyyy hh:mm");
            momento.setHora(ToolBox.readDate());
            System.out.println("Descibe el momento");
            momento.setDescripcion(sc.next());
            System.out.println("Dime el DNI del reportero que hizo el momento");
            Reportero rep=BaseDatos.buscaReporteroByNIF(sc.nextLine());
            if(rep!=null){
                momento.setReportero(rep);
            }else{
                //el reportero con el nif aportado no existe
            }
            confirmacion=ToolBox.readBoolean();
        } while (!confirmacion);
        return momento;
     
    }
}
