/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAW102
 * @version 1.01
 */
public class Entrada implements Serializable {

    protected long id;//atributo que sirve para identificar a la entrada |  valores validos numero entero mayor que 0
    private double precio; // precio de la entrada | valores validos un numero real o entero mayor que 0
    private boolean esVIP;//atributo que indica si el usuario es vip o no | valores validos true cuando si es vip o false cuando no lo es
    private Concierto concierto; //atributo que indica el concierto al que pertenece la entrada
    private Reserva reserva; //atributo que indica la reserva a la que pertenece la entrada.
    private boolean disponible; //atributo que indica si la entrada esta disponible para su compra.
    private long idCompra;
    private long idConcierto;
    private long idReserva;

    public long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(long idCompra) {
        this.idCompra = idCompra;
    }

    public long getIdConcierto() {
        return idConcierto;
    }

    public void setIdConcierto(long idConcierto) {
        this.idConcierto = idConcierto;
    }

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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

    public Entrada(long id, double precio, boolean esVIP, Concierto concierto, Reserva reserva, boolean disponible, long idCompra, long idConcierto, long idReserva) {
        this.id = id;
        this.precio = precio;
        this.esVIP = esVIP;
        this.concierto = concierto;
        this.reserva = reserva;
        this.disponible = disponible;
        this.idCompra = idCompra;
        this.idConcierto = idConcierto;
        this.idReserva = idReserva;
    }

    private Entrada(long id, double precio, boolean esVIP, long idCompra, long idConcierto, long idReserva) {
        this.id = id;
        this.precio = precio;
        this.esVIP = esVIP;
        this.idCompra = idCompra;
        this.idConcierto = idConcierto;
        this.idReserva = idReserva;
    }

    public Entrada(Entrada e) {
        this.precio = e.getPrecio();
        this.esVIP = e.getEsVIP();
        this.concierto = e.getConcierto();
        this.reserva = e.getReserva();
        this.disponible = e.isDisponible();
    }

    public Entrada() {

    }

    @Override
    public String toString() {
        return "Entrada{" + "identificador=" + id + ", precio=" + precio + ", esVIP=" + esVIP + ",concierto=" + concierto + '}';
    }

    public String data() {

        return this.getId() + "|" + this.getPrecio() + "|" + this.getEsVIP() + "|" + this.getConcierto() + "|" + this.getReserva() + "|" + this.isDisponible();
    }

    public Entrada getEntradaById(long id) {
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

    public ArrayList<Entrada> getAllEntrada() {
        ArrayList<Entrada> nuevaListaEntradas = new ArrayList<Entrada>();
        /*
       for(Entrada entrada:listaEntradas) {
           nuevaListaEntradas.add(entrada);
       } 
         */
        return nuevaListaEntradas;
    }

    public Entrada nuevaEntrada() {
        Entrada entrada = new Entrada();
        Scanner in = new Scanner(System.in);
        boolean confirmacion;
        do {
            System.out.println("¿Cuánto cuesta la entrada?");
            entrada.setPrecio(in.nextDouble());
            System.out.println("¿La entrada es V.I.P.?");
            entrada.setEsVIP(ToolBox.readBoolean());
            System.out.println("¿A qué concierto pertenece la entrada?");
            //entrada.setConcierto(buscaConcierto(in.next()));
            confirmacion = ToolBox.readBoolean();
        } while (confirmacion != true);
        in.close();
        return entrada;

    }
    
        public void exportaEntradaCaracteres(String rutaFichero) {
        FileWriter escritura = null;
        BufferedWriter bW = null;
        try {
            escritura = new FileWriter(rutaFichero, true);
            bW = new BufferedWriter(escritura);
            bW.write(data()+"\n");
            bW.flush();

        } catch (IOException ex) {
            Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (bW != null) {
                try {
                    bW.close();
                } catch (IOException ex) {
                    Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (escritura != null) {
                try {
                    escritura.close();
                } catch (IOException ex) {
                    Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<Entrada> importaEntradaCaracter(String rutaFichero) {
        ArrayList<Entrada> listaEntradas = new ArrayList<Entrada>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                Entrada ent= new Entrada(Long.parseLong(atributos.get(0)), Double.parseDouble(atributos.get(1)), Boolean.valueOf(atributos.get(2)), Long.parseLong(atributos.get(3)), Long.parseLong(atributos.get(4)), Long.parseLong(atributos.get(5)));
                listaEntradas.add(ent);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("fichero no encontrado");
        } catch (IOException ex) {
            Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fR != null) {
                try {
                    fR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (bR != null) {
                try {
                    bR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listaEntradas;
        }
    }

    public void exportaEntradaBinario(String rutaFichero) {
        FileOutputStream fOS = null;
        ObjectOutputStream escribeObjeto = null;
        try {
            fOS = new FileOutputStream(rutaFichero);
            escribeObjeto = new ObjectOutputStream(fOS);
            escribeObjeto.writeObject(this);
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } finally {
            if (fOS != null) {
                try {
                    fOS.close();
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex.getMessage());
                }
            }
            if (escribeObjeto != null) {
                try {
                    escribeObjeto.close();
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex.getMessage());
                }
            }
        }
    }

    public static ArrayList<Entrada> importaEntradaBinario(String rutaFichero) {
        ArrayList<Entrada> listaEntradas = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        Entrada ent;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((ent= (Entrada) oIS.readObject()) != null) {
                listaEntradas.add(ent);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getMessage());
        } catch (EOFException ex) {
           // System.out.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        } finally {
            if (fIS != null) {
                try {
                    fIS.close();
                } catch (IOException ex) {
                    Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (oIS != null) {
                try {
                    oIS.close();
                } catch (IOException ex) {
                    Logger.getLogger(Entrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listaEntradas;
    }
}
