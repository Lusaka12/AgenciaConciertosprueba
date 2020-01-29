/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author DAW102
 * @version 1.01
 */
public class Entrada {
    protected long id;//atributo que sirve para identificar a la entrada |  valores validos numero entero mayor que 0
    private double precio; // precio de la entrada | valores validos un numero real o entero mayor que 0
    private boolean esVIP;//atributo que indica si el usuario es vip o no | valores validos true cuando si es vip o false cuando no lo es
    private Concierto concierto; //atributo que indica el concierto al que pertenece la entrada
    
    public long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean getEsVIP() {
        return esVIP;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setEsVIP(boolean esVIP) {
        this.esVIP = esVIP;
    }

    public Concierto getConcierto() {
        return concierto;
    }

    public void setConcierto(Concierto concierto) {
        this.concierto = concierto;
    }

    public Entrada(long id, double precio, boolean esVIP, Concierto concierto) {
        this.id = id;
        this.precio = precio;
        this.esVIP = esVIP;
        this.concierto = concierto;
    }
    
    
    public Entrada(Entrada e) {
        this.precio = e.getPrecio();
        this.esVIP = e.getEsVIP();
        this.concierto = e.getConcierto();
    }
     
    public Entrada(){
       
    }

    @Override
    public String toString() {
        return "Entrada{" + "identificador=" + id + ", precio=" + precio + ", esVIP=" + esVIP + ",concierto=" + concierto +'}';
    }
    public String data() {
       
        return this.getId()+"|"+this.getPrecio()+"|"+this.getEsVIP();
    }
    
    public Entrada getEntradaById(long id){
        /*for (Entrada entrada : listaEntradas) {
           if (entrada.getId() == id) {
               return entrada;
           }
       }*/
        
        /*Este método recorrerá un ArrayList con todas las entradas comparando con 
        el id que le introduzcamos, y devolverá la entrada si es que existe o 
        nulo si es que no existe*/
        return null;
    }
    
    public ArrayList<Entrada> getAllEntrada (){
       ArrayList<Entrada> nuevaListaEntradas=new ArrayList<Entrada>();
       /*
       for(Entrada entrada:listaEntradas) {
           nuevaListaEntradas.add(entrada);
       } 
       */
       return nuevaListaEntradas;
    }
     
    public Entrada nuevaEntrada(){
        Entrada entrada=new Entrada();
        Scanner in=new Scanner(System.in);
        boolean confirmacion; 
        do{
        System.out.println("¿Cuánto cuesta la entrada?");
        entrada.setPrecio(in.nextDouble());
        System.out.println("¿La entrada es V.I.P.?");
        entrada.setEsVIP(ToolBox.readBoolean());
        System.out.println("¿A qué concierto pertenece la entrada?");
        //entrada.setConcierto(buscaConcierto(in.next()));
        confirmacion=ToolBox.readBoolean();
        }while (confirmacion!=true);
        in.close();
        return entrada;



    }

}
