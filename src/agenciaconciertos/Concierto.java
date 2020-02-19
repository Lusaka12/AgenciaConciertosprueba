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
 * @author david
 * @version 1.01
 */
public class Concierto implements Serializable {

    protected long id; //atributo que sirve para identificar al concierto //  valores validos numero entero mayor que 0
    private Date fechaHora; //atributo que sirve para idenfiticar la fechaHora y la hora del concierto// valores validos cadena de caracteres de 20 caracteres
    protected ArrayList<Actuacion> listaActuaciones;//lista que contiene las actuaciones de un concierto //minimo 5 y maximo 10.
    protected String nombreConcierto;
    protected long idGira;

    public String getNombreConcierto() {
        return nombreConcierto;
    }

    public void setNombreConcierto(String nombreConcierto) {
        this.nombreConcierto = nombreConcierto;
    }

    public long getIdGira() {
        return idGira;
    }

    public void setIdGira(long idGira) {
        this.idGira = idGira;
    }

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

    public void anadeActuacion(Actuacion actuacion) {
        listaActuaciones.add(actuacion);
    }

    protected Concierto(Date fechaHora, ArrayList<Actuacion> listaActuaciones) {
        this.fechaHora = fechaHora;
        this.listaActuaciones = listaActuaciones;
    }

    protected Concierto(long id, Date fechaHora, String nombreConcierto, long idGira) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.nombreConcierto = nombreConcierto;
        this.idGira = idGira;
    }

    protected Concierto(Concierto c) {
        this.fechaHora = c.getFechaHora();
        listaActuaciones = c.getListaActuaciones();
    }

    protected Concierto() {
        listaActuaciones = new ArrayList<Actuacion>();
    }

    @Override
    public String toString() {
        return "Concierto{" + "identificador=" + id + ", fecha=" + fechaHora + '}';
    }

    public String data() {

        return this.getId() + "|" + this.getFechaHora() + "|" + this.getNombreConcierto() + "|" + this.getIdGira();
    }

    public Concierto getConciertoById(long id) {
        /*
        for(Concierto concierto:listaConciertos) {
            if (concierto.getId()==id) {
                return concierto;
            }
        }    
         */
        return null;
    }

    public ArrayList<Concierto> getAllConciertos() {
        ArrayList<Concierto> nuevaListaConciertos = new ArrayList<Concierto>();
        /*for(Concierto concierto:listaConcierto) {
            nuevaListaconciertos.add(concierto);
        } 
         */
        return nuevaListaConciertos;
    }

    public static Concierto nuevoConcierto() {
        Concierto concierto = new Concierto();
        Scanner in = new Scanner(System.in);
        boolean confirmacion;
        do {
            System.out.println("Es un concierto invidual o colaborativo");
            String aux = in.next().trim().toLowerCase();
            if (aux == "individual") {
                concierto = new CColaboracion();
            } else if (aux == "colaborativo") {
                concierto = new CIndividual();
            }
            System.out.println("¿Qué día tienes el concierto?");
            Date fecha = ToolBox.readDate();
            concierto.setFechaHora(fecha);

            confirmacion = ToolBox.readBoolean();
        } while (confirmacion != true);
        in.close();
        return concierto;

    }

    public static Concierto nuevoConcierto(Concierto c) {

        Scanner in = new Scanner(System.in);
        boolean confirmacion;
        do {

            System.out.println("cuantas actuaciones tiene el concierto");
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                c.anadeActuacion(Actuacion.nuevaActuacion());
            }

            System.out.println("¿Qué día tienes el concierto?");
            Date fecha = ToolBox.readDate();
            c.setFechaHora(fecha);

            confirmacion = ToolBox.readBoolean();
        } while (confirmacion != true);
        in.close();
        return c;
    }

    public Actuacion getActuacionByPos(int i) {
        return listaActuaciones.get(i);
    }

    public void exportaConciertoCaracteres(String rutaFichero) {
        FileWriter escritura = null;
        BufferedWriter bW = null;
        try {
            escritura = new FileWriter(rutaFichero, true);
            bW = new BufferedWriter(escritura);
            bW.write(data() + "\n");
            bW.flush();

        } catch (IOException ex) {
            Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (bW != null) {
                try {
                    bW.close();
                } catch (IOException ex) {
                    Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (escritura != null) {
                try {
                    escritura.close();
                } catch (IOException ex) {
                    Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<Concierto> importaConciertoCaracter(String rutaFichero) {
        ArrayList<Concierto> listaConciertos = new ArrayList<Concierto>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                new Concierto();
                Concierto con = new Concierto(Long.parseLong(atributos.get(0)), df.parse(atributos.get(1)), atributos.get(2), Long.parseLong(atributos.get(3)));
                listaConciertos.add(con);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("fichero no encontrado");
        } catch (IOException ex) {
            Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fR != null) {
                try {
                    fR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (bR != null) {
                try {
                    bR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listaConciertos;
        }
    }

    public void exportaConciertoBinario(String rutaFichero) {
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

    public static ArrayList<Concierto> importaConciertoBinario(String rutaFichero) {
        ArrayList<Concierto> listaConciertos = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        Concierto con;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((con = (Concierto) oIS.readObject()) != null) {
                listaConciertos.add(con);
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
                    Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (oIS != null) {
                try {
                    oIS.close();
                } catch (IOException ex) {
                    Logger.getLogger(Concierto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listaConciertos;
    }
}
