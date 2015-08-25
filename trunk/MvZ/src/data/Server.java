/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import datenbank.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {


private Verwaltung verwaltung = null; //Hier wird ein Verwaltungsobjekt mit den Mapper gespeichert

/**
 * Die Methode Server wird in der main aufgerufen und Instanziert ein Verwaltungsobjekt und
 * hinterlegt dies in der Registry.
 */



Server(){
    try{
       verwaltung = new VerwaltungImpl(es_beziehungMapper.es_beziehungMapper(), personMapper.personMapper(), rolleMapper.rolleMapper(), verlagMapper.verlagMapper(), werkMapper.werkMapper(), zitatMapper.zitatMapper());
       //Schauen was hier passiert
       Naming.rebind("rmi:/localhost:1099/DRM", verwaltung);
       System.out.println(System.getProperty("java.rmi.registry.hostname","localhost"));
    }
    //Dieser Catch ist für RMI - Fehler zuständig
    catch (RemoteException e){
        e.printStackTrace();
    }
    //Dieser Catch ist für den Name rebind zuständig
    catch (MalformedURLException e) {
        e.printStackTrace();
    }

}

    /**
     * Die Main Methode wird beim ausführen der Klasse ausgeführt. Sie erstellt
     * eine Registry für RMI und erstellt einen SecurityManager. Außerdem wird
     * die Server Methode aufgerufen(siehe Server() )
     * @param args
     * @throws RemoteException
     */
    public static void main(String args[]) throws RemoteException{

        //Durch diesen Code wird RMi in der Registry bekannt gemacht
        LocateRegistry.createRegistry(Registry.REGISTRY_PORT); //RMI-Port 1099

       System.out.println("Verwaltung in Registry eingetragen...");
//        //Falls es noch keine Sicherheitsmanager auf dem System gibt, wird er hier gestartet
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new RMISecurityManager());
//
//        System.out.println("Security Manager gestartet...");

        new Server();

        System.out.println("Server gestartet...Ready!");

        }
    }
//}


