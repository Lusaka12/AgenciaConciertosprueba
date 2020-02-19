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
 * @author Usuario
 */
public class CColaboracion extends Concierto implements Serializable {

    public CColaboracion() {
    }

    public CColaboracion(Date fechaHora, ArrayList<Actuacion> listaActuaciones) {
        super(fechaHora, listaActuaciones);
    }

    public CColaboracion(CColaboracion ccolaboracion) {
        super(ccolaboracion);
    }
    
    private CColaboracion(long id, Date fechaHora, String nombreConcierto, long idGira) {
        super(id,fechaHora,nombreConcierto, idGira);
    }
    
    @Override
    public String data() {
        return super.data();
    }

    public static CColaboracion nuevoColaboracion() {
        Concierto concierto = new CColaboracion();;
        Scanner in = new Scanner(System.in);
        boolean confirmacion;
        do {
            Concierto.nuevoConcierto(concierto);
            confirmacion = ToolBox.readBoolean();
        } while (confirmacion != true);
        in.close();
        return (CColaboracion) concierto;

    }

    public void exportaCColaboracionCaracteres(String rutaFichero) {
        FileWriter escritura = null;
        BufferedWriter bW = null;
        try {
            escritura = new FileWriter(rutaFichero, true);
            bW = new BufferedWriter(escritura);
            bW.write(data() + "\n");
            bW.flush();

        } catch (IOException ex) {
            Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (bW != null) {
                try {
                    bW.close();
                } catch (IOException ex) {
                    Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (escritura != null) {
                try {
                    escritura.close();
                } catch (IOException ex) {
                    Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<CColaboracion> importaCColaboracionCarcoler(String rutaFichero) {
        ArrayList<CColaboracion> listaCColaboraciones = new ArrayList<CColaboracion>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                CColaboracion col = new CColaboracion(Long.parseLong(atributos.get(0)),df.parse(atributos.get(1)),atributos.get(2),Long.parseLong(atributos.get(3)));
                listaCColaboraciones.add(col);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("fichero no encontrado");
        } catch (IOException ex) {
            Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fR != null) {
                try {
                    fR.close();
                } catch (IOException ex) {
                    Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (bR != null) {
                try {
                    bR.close();
                } catch (IOException ex) {
                    Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return listaCColaboraciones;
        }
    }

    public void exportaCColaboracionBinario(String rutaFichero) {
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

    public static ArrayList<CColaboracion> importaCColaboracionBinario(String rutaFichero) {
        ArrayList<CColaboracion> listaCColaboraciones = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        CColaboracion col = null;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((col = (CColaboracion) oIS.readObject()) != null) {
                listaCColaboraciones.add(col);
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
                    Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (oIS != null) {
                try {
                    oIS.close();
                } catch (IOException ex) {
                    Logger.getLogger(CColaboracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listaCColaboraciones;
    }
}
