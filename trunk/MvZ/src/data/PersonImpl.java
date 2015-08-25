/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;




public class PersonImpl extends java.rmi.server.UnicastRemoteObject implements Person {

private int person_id;
private String vorname;
private String name;
private String anrede;
private String titel;



//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public PersonImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt den vorname vom typ String und wirft eine RemoteException.

public void set_person_id(int PersonID)throws RemoteException {
this.person_id = PersonID;}



//Diese Methode setzt den vorname vom typ String und wirft eine RemoteException.

public void set_vorname(String Vorname)throws RemoteException {
this.vorname = Vorname;}

//Diese Methode setzt den name vom typ String und wirft eine RemoteException.

public void set_name(String Name)throws RemoteException {
this.name = Name;}


//Diese Methode setzt die anrede vom typ String und wirft eine RemoteException.

public void set_anrede(String Anrede)throws RemoteException {
this.anrede = Anrede;}


//Diese Methode setzt den titel vom typ String und wirft eine RemoteException.

public void set_titel(String Titel)throws RemoteException {
this.titel = Titel;}



//Diese Methode gibt die person_id vom typ int zurück und es wird eine RemoteException geworfen.

public int get_person_id()throws RemoteException {
return person_id;}

//Diese Methode gibt den vorname vom typ String zurück und es wird eine RemoteException geworfen.

public String get_vorname()throws RemoteException {
return vorname;}


//Diese Methode gibt den name vom typ String zurück und es wird eine RemoteException geworfen.

public String get_name()throws RemoteException {
return name;}


//Diese Methode gibt die anrede vom typ String zurück und es wird eine RemoteException geworfen.

public String get_anrede()throws RemoteException {
return anrede;}


//Diese Methode gibt den titel vom typ String zurück und es wird eine RemoteException geworfen.

public String get_titel()throws RemoteException {
return titel;}


}