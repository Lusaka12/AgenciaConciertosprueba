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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private long idActuacion;
    private long idReportero;
    
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
    private Momento(long id,long idActuacion,long idReportero,String descripcion,Date hora) {
        this.id=id;
        this.idActuacion=idActuacion;
        this.idReportero=idReportero;
        this.hora = hora;
        this.descripcion = descripcion;
    }
    public void setActuacion(Actuacion actuacion) {
        this.actuacion = actuacion;
    }
    public Momento(){
    }
    public String data(){
        return this.id+"|"+this.idActuacion+"|"+this.idReportero+"|"+
                this.getDescripcion()+"|"+this.getHora();
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
    public static Reportero nuevoReportero() throws ReporteroException{
        Reportero reportero;
        Scanner in=new Scanner(System.in);
        boolean confirmacion; 
        String nombre,apellidos,NIF,numTelefono;
        do{    
        System.out.println("¿Cual es el nombre del reportero?");
        nombre=in.next();
        ReporteroException.validaNombre(nombre);       
        System.out.println("¿Cuales son los apellidos del reportero?");
        apellidos=in.next();
        ReporteroException.validaApellidos(apellidos);     
        System.out.println("¿Que NIF tiene el reportero?");
        NIF=in.next();
        ReporteroException.validaNIF(NIF);   
        System.out.println("¿Cual es el numero de telefono del reportero?");
        numTelefono=in.next();
        ReporteroException.validanNumero(numTelefono);
        //Artista a=Artista.buscaPorNombreArtistico;
        //actuacion.setListaArtistas(a);
        confirmacion=ToolBox.readBoolean();
        }while (confirmacion!=true);
        reportero = new Reportero(nombre, apellidos, NIF, numTelefono);
        in.close();
        return reportero;
    }
    public void exportaMomentoCaracteres(String rutaFichero) {
        FileWriter escritura = null;
        BufferedWriter bW = null;
        try {
            escritura = new FileWriter(rutaFichero, true);
            bW = new BufferedWriter(escritura);
            bW.write(data()+"\n");
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

    public static ArrayList<Momento> importaReporteroCaracter(String rutaFichero) {
            ArrayList<Momento> listaMomentos = new ArrayList<Momento>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                Momento momento = new Momento(Long.parseLong(atributos.get(0)),Long.parseLong(atributos.get(1)),Long.parseLong(atributos.get(2)),
                        atributos.get(3),df.parse(atributos.get(4)));
                listaMomentos.add(momento);
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
                return listaMomentos;
        }
    }

    public void exportaMomentoBinario(String rutaFichero) {
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

    public static ArrayList<Momento> importaMomentoBinario(String rutaFichero) {
        ArrayList<Momento> listaMomentos = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        Momento momento;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((momento = (Momento) oIS.readObject()) != null) {
                listaMomentos.add(momento);
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
        return listaMomentos;
    }
}
