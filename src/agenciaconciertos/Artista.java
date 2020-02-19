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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAW102
 * @version 1.01
 */
public class Artista implements Serializable {

    
    protected long id;//atributo que sirve para identificar al artista |  valores validos numero entero mayor que 0
    private String  nombreArtistico; //nombre artistico del artista | valores validos cadena de caracteres de 20 caracteres pudiendo tener simbolos y numeros
    private String generoMusica; //genero de musica del artista | valores validos cadena de caracteres de 20 caracteres pudiendo tener simbolos y numeros
    
    public long getId() {
        return id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public String getGeneroMusica() {
        return generoMusica;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public void setGeneroMusica(String generoMusica) {
        this.generoMusica = generoMusica;
    }

    public Artista(String nombreArtistico, String generoMusica) {
        this.nombreArtistico = nombreArtistico;
        this.generoMusica = generoMusica;
    }
    
    private Artista (long id, String nombreArtistico, String generoMusica) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
        this.generoMusica = generoMusica;
    }
     public Artista(Artista a) {
        this.nombreArtistico = a.getNombreArtistico();
        this.generoMusica = a.getGeneroMusica();
    }
    public Artista(){
       
    }

    @Override
    public String toString() {
        return "Artista{" + "identificador=" + id + ", nombreArtistico=" + nombreArtistico + ", generoMusica=" + generoMusica + '}';
    }
    
    public String data() { 
        return this.getId()+"|"+this.getNombreArtistico()+"|"+this.getGeneroMusica();
    }
    public Artista getArtistaById (long id){
      /*
        for(Artista artista:listaArtista){
            if(artista.getId()==id){
                return artista;
            }
        }
        */
        return null;
    }
    public  ArrayList<Artista> getAllArtista (){
        ArrayList<Artista> nuevaLista=new ArrayList<Artista>();
        /*for(Artista artista:listaArtista){
            if(artista.getId()==id){
                nuevaLista.add(artista);
            }
        }*/
        return nuevaLista;
    }
    public static Artista nuevoArtista() { 
        Artista artista=new Artista();
        Scanner in=new Scanner(System.in);
        boolean confirmacion; 
        do{    
        System.out.println("¿Cuál es el nombre artistico del artista?"); 
        artista.setNombreArtistico(in.next());
        System.out.println(" ¿Cuál es el genero musical del artista?");
        artista.setGeneroMusica(in.next());
        confirmacion=ToolBox.readBoolean();
        }while (confirmacion!=true);
        in.close();
        return artista; 
    }
    
    public void exportaArtistaCaracteres(String rutaFichero) {
        FileWriter escritura = null;
        BufferedWriter bW = null;
        try {
            escritura = new FileWriter(rutaFichero, true);
            bW = new BufferedWriter(escritura);
            bW.write(data()+"\n");
            bW.flush();

        } catch (IOException ex) {
            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (bW != null) {
                try {
                    bW.close();
                } catch (IOException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (escritura != null) {
                try {
                    escritura.close();
                } catch (IOException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<Artista> importaArtistaCaracter(String rutaFichero) {
        ArrayList<Artista> listaArtistas = new ArrayList<Artista>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                Artista art = new Artista(Long.parseLong(atributos.get(0)), atributos.get(1), atributos.get(2));
                listaArtistas.add(art);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("fichero no encontrado");
        } catch (IOException ex) {
            Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fR != null) {
                try {
                    fR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (bR != null) {
                try {
                    bR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listaArtistas;
        }
    }

    public void exportaArtistaBinario(String rutaFichero) {
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

    public static ArrayList<Artista> importaArtistaBinario(String rutaFichero) {
        ArrayList<Artista> listaArtistas = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        Artista art = null;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((art = (Artista) oIS.readObject()) != null) {
                listaArtistas.add(art);
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
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (oIS != null) {
                try {
                    oIS.close();
                } catch (IOException ex) {
                    Logger.getLogger(Artista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listaArtistas;
    }
}
