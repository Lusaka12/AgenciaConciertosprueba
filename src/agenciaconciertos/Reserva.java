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
 * @author DAW113
 * @version 1.01
 */
public class Reserva {
    private int numEntradas; //numero de entradas de la compra | valores validos hasta el maximo numero de entradas del concierto.
    protected long id; //atributo que sirve para identificar a la compra |  valores validos numero entero mayor que 0
    private Date fechaMaxima; // Limite para reservar la entrada // valores validos cadena de caracteres de 20 caracteres no pudiendo tener simbolos y numeros
    private String codigoDescuento; //contiene un codigo de descuento que puede ser valido| valores validos "" si no se ha aportado ningun codigo y un codigo si se ha aportado
    private Date fechaCanjeo; // fecha en la que se hace efefctiva la reserva y se trata como una compra "" valores vaalidos fecha con hora.
    public int getNumEntradas(){
        return numEntradas;
    }
    
    public long getId(){
        return id;
    }
    
    public Date getFechaMaxima(){
        return fechaMaxima;
    }
    
    public String getCodigoDescuento(){
        return codigoDescuento;
    }

    public Date getFechaCanjeo() {
        return fechaCanjeo;
    }

    public void setFechaCanjeo(Date fechaCanjeo) {
        this.fechaCanjeo = fechaCanjeo;
    }
    
    

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }
    
    public void setCodigoDescuento(String codigoDescuento) {
        this.codigoDescuento = codigoDescuento;
    }
    
    public Reserva(int numEntradas, Date fechaMaxima, String codigoDescuento) {
        this.numEntradas = numEntradas;
        this.fechaMaxima = fechaMaxima;
        this.codigoDescuento = codigoDescuento;
    }
    public Reserva(Reserva r) {
        this.numEntradas = r.getNumEntradas();
        this.id = r.getId();
        this.fechaMaxima = r.getFechaMaxima();
        this.codigoDescuento = r.getCodigoDescuento();
    }
    public Reserva() {
    }
    
    @Override
    public String toString() {
        return "Reserva{" + "numEntradas=" + numEntradas + ", identificador=" + id + ", fechaMaxima=" + fechaMaxima + ", codigoDescuento=" + codigoDescuento + '}';
    }
    
    public String data() {
        return this.getNumEntradas()+"|"+this.getId()+"|"+
            this.getFechaMaxima()+"|"+this.getCodigoDescuento()+"|"+this.getCodigoDescuento();
    }
 public Reserva getReservaById (long id){
        /*for (Reserva reserva : listaReserva) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }*/
        return null;
 }
 
 public ArrayList<Reserva> getAllReserva (){
        ArrayList<Reserva> nuevaListaReserva=new ArrayList<Reserva>();
            /*for(Reserva reserva:listaReserva) {
            nuevaListaReserva.add(reserva);
        } 
        */  
        return nuevaListaReserva;
    }
 public Reserva nuevoReserva(){
        Reserva reserva=new Reserva();
        Scanner in=new Scanner(System.in);
        boolean confirmacion; 
        do{    
        System.out.println("¿Cuantas entradas se han reservado?"); 
        reserva.setNumEntradas(in.nextInt());
        System.out.println("¿?");
        reserva.setFechaMaxima(ToolBox.readDate());
        System.out.println("¿?");
       // reserva.setNIF(in.next());
        System.out.println("¿?");
       // reserva.setNumero(in.next());
        //Artista a=Artista.buscaPorNombreArtistico;
        //actuacion.setListaArtistas(a);
        confirmacion=ToolBox.readBoolean();
        }while (confirmacion!=true);
        in.close();
        return reserva;
    }
}



          
        