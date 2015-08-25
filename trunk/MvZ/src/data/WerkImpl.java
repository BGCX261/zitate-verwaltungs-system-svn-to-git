/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;




public class WerkImpl extends java.rmi.server.UnicastRemoteObject implements Werk {

private String titel;
private int jahr;
private int seiten;
private int werk_id;
private char werk_typ;
private int verlag_id;
private int s_typ;



//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public WerkImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt den titel vom typ String und wirft eine RemoteException.
public void set_titel(String Titel)throws RemoteException {
this.titel = Titel;}

//Diese Methode setzt das jahr vom typ int und wirft eine RemoteException.
public void set_jahr(int Jahr)throws RemoteException {
this.jahr = Jahr;}

//Diese Methode setzt die seiten vom typ int und wirft eine RemoteException.
public void set_seiten(int Seiten)throws RemoteException {
this.seiten = Seiten;}

//Diese Methode setzt die werk_id vom typ int und wirft eine RemoteException.
public void set_werk_id(int WerkID)throws RemoteException {
this.werk_id = WerkID;}

//Diese Methode setzt den werk_typ vom typ char und wirft eine RemoteException.
public void set_werk_typ(char Werk_typ)throws RemoteException {
this.werk_typ = Werk_typ;}

public void set_verlag_id(int Verlag_id)throws RemoteException {
this.verlag_id = Verlag_id;}

public void set_s_typ(int S_typ)throws RemoteException {
this.s_typ = S_typ;}




//Diese Methode gibt den titel vom typ String zurück und es wird eine RemoteException geworfen.
public String get_titel()throws RemoteException {
return titel;}

//Diese Methode gibt das jahr vom typ int zurück und es wird eine RemoteException geworfen.
public int get_jahr()throws RemoteException {
return jahr;}

//Diese Methode gibt die name vom typ int zurück und es wird eine RemoteException geworfen.
public int get_seiten()throws RemoteException {
return seiten;}

//Diese Methode gibt die werk_id vom typ int zurück und es wird eine RemoteException geworfen.
public int get_werk_id()throws RemoteException {
return werk_id;}

//Diese Methode gibt den werk_typ vom typ char zurück und es wird eine RemoteException geworfen.
public char get_werk_typ()throws RemoteException {
return werk_typ;}

public int get_verlag_id()throws RemoteException {
return verlag_id;}

public int get_s_typ()throws RemoteException {
return s_typ;}


}

