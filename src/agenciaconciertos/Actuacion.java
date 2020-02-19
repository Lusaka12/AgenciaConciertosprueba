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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DAW113
 * @version 1.01
 */
public class Actuacion {

    protected long id; //atributo que sirve para identificar a la actuacion // valores validos numero entero mayor que 0
    private int numeroSecuencia; // numero de actuacion que forma parte del concierto // valores validos hasta el numero maximo de actuaciones en el concierto
    private int duracion; //numero de minutos que dura la actuacion // valores validos hasta el numero maximo de minutos que dura la actuacion
    private ArrayList<Artista> listaArtistas;//lista de artistas que actuan en la actuacion// valores permitidos minimo 1 y maximo 2
    private long idConcierto;// guarda el concierto de la actuacion
    private Reportero reportero = null;// Guarda el reportero que cubre esa actuacion.//valores validos un objeto reportero cuando se le asigne un reportero y antes de eso null
    private long idArtista;
    private long idReportero;


    public Reportero getReportero() {
        return reportero;
    }

    public void setReportero(Reportero reportero) {
        this.reportero = reportero;
    }

    public long getId() {
        return id;
    }

    public int getNumeroSecuencia() {
        return numeroSecuencia;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdconcierto() {
        return idConcierto;
    }

    public void setIdconcierto(long idConcierto) {
        this.idConcierto = idConcierto;
    }

    public ArrayList<Artista> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(ArrayList<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public void setNumeroSecuencia(int numeroSecuencia) {
        this.numeroSecuencia = numeroSecuencia;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Actuacion(int numeroSecuencia, int duracion, ArrayList<Artista> listaArtistas, long idConcierto) {
        this.numeroSecuencia = numeroSecuencia;
        this.duracion = duracion;
        this.listaArtistas = listaArtistas;
        this.idConcierto = idConcierto;
    }

    public Actuacion(long id, int numeroSecuencia, int duracion, ArrayList<Artista> listaArtistas) {
        this.id = id;
        this.numeroSecuencia = numeroSecuencia;
        this.duracion = duracion;
        this.listaArtistas = listaArtistas;
    }

    /**
     * metodo constructor que usaran los metodos para recuperar los objetos.
     *
     * @param id
     * @param numeroSecuencia
     * @param duracion
     * @param idArtista
     * @param idConcierto
     */
    private Actuacion(long id, long idArtista, long idReportero, long idConcierto, int numeroSecuencia, int duracion) {
        this.id = id;
        this.numeroSecuencia = numeroSecuencia;
        this.duracion = duracion;
        this.idArtista = idArtista;
        this.idConcierto = idConcierto;
        this.idReportero = idReportero;
    }

    public Actuacion(Actuacion a) {
        this.numeroSecuencia = a.getNumeroSecuencia();
        this.duracion = a.getDuracion();
        this.listaArtistas = a.getListaArtistas();
        this.idConcierto = a.getIdconcierto();
    }

    public Actuacion() {
        listaArtistas = new ArrayList<Artista>();
    }

    @Override
    public String toString() {
        return "Actuacion{" + "identificador=" + id + ", numeroSecuencia=" + numeroSecuencia + ", duracion=" + duracion + '}';
    }

    public String data() {
        return this.getId() + "|" + this.idArtista + "|" + this.idReportero + "|" + this.idConcierto + "|" + this.getNumeroSecuencia() + "|" + this.getDuracion();
    }

    public Actuacion getActuacionById(long id) {
        /*for (Actuacion actuacion : listaActuaciones) {
            if (actuacion.getId() == id) {
                return actuacion;
            }
        }*/
        return null;
    }

    public ArrayList<Actuacion> getAllActuacion() {
        ArrayList<Actuacion> nuevaListaActuacion = new ArrayList<Actuacion>();
        /*for(Actuacion actuacion:listaActuaciones) {
            nuevaListaDescuentos.add(descuento);
        } 
         */
        return nuevaListaActuacion;
    }

    public static Actuacion nuevaActuacion() {
        Actuacion actuacion = new Actuacion();
        Scanner in = new Scanner(System.in);
        boolean confirmacion;
        do {
            System.out.println("¿En qué posicion es la actuacion?");
            actuacion.setNumeroSecuencia(in.nextInt());
            System.out.println("¿Cuánto dura la actuacion?");
            actuacion.setDuracion(in.nextInt());
            System.out.println("Dame nombre Artistico");
            //Artista a=Artista.buscaPorNombreArtistico();
            //actuacion.setListaArtistas(a);
            confirmacion = ToolBox.readBoolean();
        } while (confirmacion != true);
        in.close();
        return actuacion;
    }

    /* Metodo que Asigna el reportero a esta actuacion.
     
     */
    public void asignaReporteroActuacion() { //throws formatoNifIncorrecto,reporteroNoExiste
        if (reportero != null) {
            //throw new ActuacionYaTieneReporteroAsignado();
        }
        Scanner in = new Scanner(System.in);
        System.out.println("introduzca el nif del reportero: NNNNL N numero L letra");
        String nif = in.nextLine().trim().toLowerCase();
        char letra = nif.charAt(nif.length() - 1);
        //he puesto tamano 5 por poner uno.
        if (nif.length() != 5 && "0123456789".indexOf(letra) != -1) {
            //throw new formatoNifIncorrecto();
        }//compruebo el resto de caracteres
        for (int i = 0; i < nif.length() - 1; i++) {
            letra = nif.charAt(i);
            if ("0123456789".indexOf(letra) == -1) {
                //throw new formatoNifIncorrecto();
            }
        }
        Reportero rep = BaseDatos.buscaReporteroByNIF(nif);
        if (rep == null) {
            //throw new reporteroNoExiste
        }
        reportero = rep;
    }

    public void exportaActuacionCaracteres(String rutaFichero) {
        FileWriter escritura = null;
        BufferedWriter bW = null;
        try {
            escritura = new FileWriter(rutaFichero, true);
            bW = new BufferedWriter(escritura);
            bW.write(data() + "\n");
            bW.flush();

        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } finally {

            if (bW != null) {
                try {
                    bW.close();
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex.getMessage());
                }
            }
            if (escritura != null) {
                try {
                    escritura.close();
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex.getMessage());
                }
            }
        }
    }

    public static ArrayList<Actuacion> importaActuacionCaracter(String rutaFichero) {
        ArrayList<Actuacion> listaActuaciones = new ArrayList<Actuacion>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                Actuacion act = new Actuacion(Long.parseLong(atributos.get(0)), Long.parseLong(atributos.get(1)), Long.parseLong(atributos.get(2)), Long.parseLong(atributos.get(3)),
                        Integer.parseInt(atributos.get(4)), Integer.parseInt(atributos.get(5)));
                listaActuaciones.add(act);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } finally {
            if (fR != null) {
                try {
                    fR.close();
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex.getMessage());
                }
            }
            if (bR != null) {
                try {
                    bR.close();
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex.getMessage());
                }
            }
            return listaActuaciones;
        }
    }

    public void exportaActuacionBinario(String rutaFichero) {
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

    public static ArrayList<Actuacion> importaActuacionBinario(String rutaFichero) {
        ArrayList<Actuacion> listaActuaciones = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        Actuacion act;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((act = (Actuacion) oIS.readObject()) != null) {
                listaActuaciones.add(act);
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
                    System.out.println("IOException: " + ex.getMessage());
                }
            }
            if (oIS != null) {
                try {
                    oIS.close();
                } catch (IOException ex) {
                    System.out.println("IOException: " + ex.getMessage());
                }
            }

        }
        return listaActuaciones;
    }
}
