/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;




public class RolleImpl extends java.rmi.server.UnicastRemoteObject implements Rolle {

private int person_id;
private int werk_id;
private char typ;




//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public RolleImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt die person_id vom typ int und wirft eine RemoteException.
public void set_person_id(int PersonID)throws RemoteException {
this.person_id = PersonID;}

//Diese Methode setzt die werk_id vom typ int und wirft eine RemoteException.
public void set_werk_id(int WerkID)throws RemoteException {
this.werk_id = WerkID;}

//Diese Methode setzt den typ vom typ char und wirft eine RemoteException.
public void set_typ(char Typ)throws RemoteException {
this.typ = Typ;}



//Diese Methode gibt die person_id vom typ int zurück und es wird eine RemoteException geworfen.
public int get_person_id()throws RemoteException {
return person_id;}

//Diese Methode gibt die werk_id vom typ int zurück und es wird eine RemoteException geworfen.
public int get_werk_id()throws RemoteException {
return werk_id;}

//Diese Methode gibt den typ vom typ char zurück und es wird eine RemoteException geworfen.
public char get_typ()throws RemoteException {
return typ;}


    }


