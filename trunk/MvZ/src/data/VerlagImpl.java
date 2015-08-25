/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;




public class VerlagImpl extends java.rmi.server.UnicastRemoteObject implements Verlag {

private int verlag_id;
private String name;
private String ort;




//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public VerlagImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt die verlag_id vom typ int und wirft eine RemoteException.
public void set_verlag_id(int VerlagID)throws RemoteException {
this.verlag_id = VerlagID;}

//Diese Methode setzt den name vom typ String und wirft eine RemoteException.
public void set_name(String Name)throws RemoteException {
this.name = Name;}

//Diese Methode setzt den ort vom typ String und wirft eine RemoteException.
public void set_ort(String Ort)throws RemoteException {
this.ort = Ort;}



//Diese Methode gibt die verlag_id vom typ int zurück und es wird eine RemoteException geworfen.
public int get_verlag_id()throws RemoteException {
return verlag_id;}

//Diese Methode gibt den name vom typ String zurück und es wird eine RemoteException geworfen.
public String get_name()throws RemoteException {
return name;}

//Diese Methode gibt den ort vom typ String zurück und es wird eine RemoteException geworfen.
public String get_ort()throws RemoteException {
return ort;}


    }

