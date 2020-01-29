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
    
    
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", NIF=" + NIF + ", verificado=" + verificado + '}';
    }
    
    public String data() {
        
        return this.getId()+"|"+this.getNombre()+"|" + this.getApellidos() + "|"+this.getEmail()
             + "|" + this.getNIF() +"|"+this.getVerificado();
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
}
