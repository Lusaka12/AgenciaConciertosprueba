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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase contiene los atributos y metodos de la clase compra
 *
 * @author daw101
 * @version 1.00
 * @see Descuento
 * @see Usuario
 * @see Entrada
 * @see Reserva
 */
public class Compra {

    protected long id;//atributo que sirve para identificar a la compra |  valores validos numero entero mayor que 0
    private int numEntradas;//numero de entradas de la compra | valores validos hasta el maximo numero de entradas del concierto.
    private double precioTotal;//precio que se pagara , se calculara a partir del precio de la entrada y numEntradas de la compra
    //valores validos de precio un numero entero o real mayor que 0
    private String metodoPago;//atributo que guarda el metodo de pago | valores validos podran ser en efectivo y con tarjeta
    private long idDescuento;
    private Date fechaCompra;
    //private Usuario usuarioCompra;
    private long idUsuario;
    private long idEntrada;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(long idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public long getId() {
        return id;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public long getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(long idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Compra(int numEntradas, double precioTotal, String metodoPago, String codigoDescuento, Usuario usuarioCompra) {
        this.numEntradas = numEntradas;
        this.precioTotal = precioTotal;
        this.metodoPago = metodoPago;
        // this.usuarioCompra=usuarioCompra;

    }

    public Compra(long id, int numEntradas, double precioTotal, String metodoPago, long idDescuento, Date fechaCompra, long idUsuario, long idEntrada) {
        this.id = id;
        this.numEntradas = numEntradas;
        this.precioTotal = precioTotal;
        this.metodoPago = metodoPago;
        this.idDescuento = idDescuento;
        this.fechaCompra = fechaCompra;
        this.idUsuario = idUsuario;
        this.idEntrada = idEntrada;
    }

    public Compra(Compra c) {
        this.numEntradas = c.getNumEntradas();
        this.precioTotal = c.getPrecioTotal();
        this.metodoPago = c.getMetodoPago();
        this.idDescuento = c.getIdDescuento();
        this.fechaCompra = c.getFechaCompra();
        this.idUsuario = c.getIdUsuario();
        this.idEntrada = c.getIdEntrada();
    }

    public Compra() {
    }

    @Override
    public String toString() {
        return "Compra{" + "numEntradas=" + numEntradas + ", identificador=" + id + ", precioTotal=" + precioTotal + ", metodoPago=" + metodoPago + '}';
    }

    public String data() {
        return this.getId() + "|" + this.getNumEntradas() + "|" + this.getPrecioTotal() + "|" + this.getMetodoPago() + "|" + this.getIdDescuento() + "|" + this.getFechaCompra() + "|" + this.getIdUsuario() + "|" + this.getIdEntrada();

    }

    public Compra getCompraById(long id) {
        /*
        for(Compra compra:listacompras) {
            if (compra.getId()==id) {
                return compra;
            }
        }    
         */
        return null;
    }

    public ArrayList<Compra> getAllCompra() {

        ArrayList<Compra> nuevaListaCompras = new ArrayList<Compra>();
        /*
        for(Compra compra:listaCompra) {
            nuevaListaCompra.add(compra);
        } 
        for(int i=0;i<listaCompra.size();i++){
            nuevaListaCompra.add(listaCompra.get(i));
        }
         */
        return nuevaListaCompras;
    }

    public Compra nuevoCompra() {
        Compra compra = new Compra();
        boolean confirmacion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("¿Cuantas entradas has comprado?");
            compra.setNumEntradas(sc.nextInt());
            System.out.println("¿Hasta cuando es la fecha de validez? formato:dd/MM/yyyy hh:mm");
            compra.setFechaCompra(ToolBox.readDate());
            System.out.println("¿Cuál es el metodo de pago?");
            compra.setMetodoPago(sc.next());
            System.out.println("¿precio total de las entradas?");
            compra.setPrecioTotal(sc.nextDouble());
            System.out.println("Pulse s para confirmar:");
            confirmacion = ToolBox.readBoolean();
        } while (!confirmacion);
        return compra;
    }

    public void exportaCompraCaracteres(String rutaFichero) {
        FileWriter escritura = null;
        BufferedWriter bW = null;
        try {
            escritura = new FileWriter(rutaFichero, true);
            bW = new BufferedWriter(escritura);
            bW.write(data() + "\n");
            bW.flush();

        } catch (IOException ex) {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (bW != null) {
                try {
                    bW.close();
                } catch (IOException ex) {
                    Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (escritura != null) {
                try {
                    escritura.close();
                } catch (IOException ex) {
                    Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static ArrayList<Compra> importaCompraCaracter(String rutaFichero) {
        ArrayList<Compra> listaCompras = new ArrayList<Compra>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                Compra com = new Compra(Long.parseLong(atributos.get(0)), Integer.parseInt(atributos.get(1)), Double.parseDouble(atributos.get(2)), atributos.get(3), Long.parseLong(atributos.get(4)), df.parse(atributos.get(5)), Long.parseLong(atributos.get(6)), Long.parseLong(atributos.get(7)));
                listaCompras.add(com);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("fichero no encontrado");
        } catch (ParseException ex) {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if (fR != null) {
                try {
                    fR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (bR != null) {
                try {
                    bR.close();
                } catch (IOException ex) {
                    Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listaCompras;
    }

    public void exportaCompraBinario(String rutaFichero) {
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

    public static ArrayList<Compra> importaCompraBinario(String rutaFichero) {
        ArrayList<Compra> listaCompras = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        Compra com;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((com = (Compra) oIS.readObject()) != null) {
                listaCompras.add(com);
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
                    Logger.getLogger(Descuento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (oIS != null) {
                try {
                    oIS.close();
                } catch (IOException ex) {
                    Logger.getLogger(Descuento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listaCompras;
    }

}
