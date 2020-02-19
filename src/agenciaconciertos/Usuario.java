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
 * @author DAW102
 * @version 1.01
 */
public class Usuario {
    protected long id;
    private String nombre, // nombre del usuario | valores validos cadena de caracteres de 20 caracteres no pudiendo tener simbolos y numeros
            apellidos, // apellidos del usuario | valores validos cadena de caracteres de 40 caracteres pudiendo tener simbolos y numeros
            email, // correo electronico del usuario | valores validos cadena de caracters de 40 caracteres pudiendo tener simbolos y numeros
            NIF; // el NIF del usuario | valores validos cadena de caracters de 8 caracteres siendo los 7 de primeros  ellos numeros 
                 // y el ultimo una letra no pudiendo tener simbolos 
    private boolean verificado;//atributo que indica si el usuario ha sido verificado | valores validos true cuando si esta verificado o false cuando no lo esta
    private Reserva reserva;
    
    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getNIF() {
        return NIF;
    }

    public boolean getVerificado() {
        return verificado;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public boolean isVerificado() {
        return verificado;
    }
 
    public Usuario(Usuario u) {
        this.nombre = u.getNombre();
        this.apellidos = u.getApellidos();
        this.email = u.getEmail();
        this.NIF = u.getNIF();
        this.verificado = false;
    }
    public Usuario() {
        this.verificado = false;
    }

    public Usuario(String nombre, String apellidos, String email, String NIF, boolean verificado, Reserva reserva) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.NIF = NIF;
        this.verificado = verificado;
        this.reserva = reserva;
    }
    
     private Usuario(long id,String NIF,String nombre, String apellidos, String email, boolean verificado) {
        this.id=id;
        this.email=email;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.NIF = NIF;
        this.verificado = verificado;
    }
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", NIF=" + NIF + ", verificado=" + verificado + '}';
    }
    
    public String data() {
        
        return this.getId()+"|"+ this.getNIF()+"|"+this.getNombre()+"|" + this.getApellidos() + "|"+this.getEmail()
               +"|"+this.getVerificado();
    }
    public Usuario getUsuarioById (long id){
        /*for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }*/
        return null;
    }
    public ArrayList<Usuario> getAllUsuario (){
        ArrayList<Usuario> nuevaListaUsuario=new ArrayList<Usuario>();
            /*for(Usuario usuario:listaUsuario) {
            nuevaListaUsuario.add(usuario);
        } 
        */  
        return nuevaListaUsuario;
    }
    public Usuario nuevoUsuario(){
        Usuario usuario=new Usuario();
        Scanner in=new Scanner(System.in);
        boolean confirmacion; 
        do{    
        System.out.println("¿Cual es tu nombre?"); 
        usuario.setNombre(in.next());
        System.out.println("¿Cuales son tus apellidos?");
        usuario.setApellidos(in.next());
        System.out.println("¿Que NIF tiene el reportero?");
        usuario.setNIF(in.next());
        System.out.println("¿Cual es tu Email?");
        usuario.setEmail(in.next());
        //Artista a=Artista.buscaPorNombreArtistico;
        //actuacion.setListaArtistas(a);
        confirmacion=ToolBox.readBoolean();
        }while (confirmacion!=true);
        in.close();
        return usuario;
    }
    public void exportaReporteroCaracteres(String rutaFichero) {
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

    public static ArrayList<Usuario> importaUsuarioCaracter(String rutaFichero) {
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
        FileReader fR = null;
        BufferedReader bR = null;
        try {

            fR = new FileReader(rutaFichero);
            bR = new BufferedReader(fR);
            String lineaActual = "";
            while ((lineaActual = bR.readLine()) != null) {
                ArrayList<String> atributos = ToolBox.separaPorCampos(lineaActual);
                Usuario usu = new Usuario(Long.parseLong(atributos.get(0)),atributos.get(1),atributos.get(2),atributos.get(3)
                        ,atributos.get(4),Boolean.valueOf(atributos.get(5)));
                listaUsuario.add(usu);
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
            return listaUsuario;
        }
    }

    public void exportaUsuarioBinario(String rutaFichero) {
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

    public static ArrayList<Usuario> importaUsuarioBinario(String rutaFichero) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        Usuario usu;
        try {
            fIS = new FileInputStream(rutaFichero);
            oIS = new ObjectInputStream(fIS);
            while ((usu = (Usuario) oIS.readObject()) != null) {
                listaUsuarios.add(usu);
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
        return listaUsuarios;
    }
}
