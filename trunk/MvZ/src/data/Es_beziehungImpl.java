/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;




public class Es_beziehungImpl extends java.rmi.server.UnicastRemoteObject implements Es_beziehung {

private int sammelwerk_id;
private int einzelwerk_id;



//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public Es_beziehungImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt die sammelwerk_id vom typ int und wirft eine RemoteException.
public void set_sammelwerk_id(int SammelwerkID)throws RemoteException {
this.sammelwerk_id = SammelwerkID;}

//Diese Methode setzt die einzelwerk_id vom typ int und wirft eine RemoteException.
public void set_einzelwerk_id(int Einzelwerk_id)throws RemoteException {
this.einzelwerk_id = Einzelwerk_id;}

//Diese Methode gibt die sammelwerk_id vom typ int zurück und es wird eine RemoteException geworfen.
public int get_sammelwerk_id()throws RemoteException {
return sammelwerk_id;}

//Diese Methode gibt die einzelwerk_id vom typ int zurück und es wird eine RemoteException geworfen.
public int get_einzelwerk_id()throws RemoteException {
return einzelwerk_id;}


    }

