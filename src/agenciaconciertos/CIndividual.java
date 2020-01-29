/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenciaconciertos;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author david
 */
public class CIndividual extends Concierto {
    
    public CIndividual() {
    }

    public CIndividual( Date fechaHora,ArrayList<Actuacion> listaActuaciones) {
        super(fechaHora,listaActuaciones);
    }

    public CIndividual(CIndividual cindividual) {
        super(cindividual);
    }

    @Override
    public String data() {
        return super.data(); 
    }
    /*
    public CIndividual getCIndividualbyid (long id) {
        for (int i=0;i<listaobjetos;i++){
            CIndividual aux=listaobjetos.get(i);
            if (aux.getID()==id){
                return aux;
            } 
        }
        return null;
    }
    /*
    public ArrayList<CIndividual> getAllCindividual(){
        ArrayList<CIndividual> Lci = new ArrayList<CIndividual>();
        for (int i=0;i<listaobjetos.size();i++){
            CIndividual aux=listaobjetos.get(i);
            Lci.add(aux);
        }
        return Lci;
    }*/
   
    public static CIndividual nuevoCIndividual() {
        Concierto concierto=new CIndividual();;
        Scanner in=new Scanner(System.in);
        boolean confirmacion;
        do{   
        Concierto.nuevoConcierto(concierto);
        confirmacion=ToolBox.readBoolean();
        } while (confirmacion!=true);
        in.close();
        return (CIndividual)concierto;
  
    }
}


