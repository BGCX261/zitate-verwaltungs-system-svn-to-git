/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;




public class ZitatImpl extends java.rmi.server.UnicastRemoteObject implements Zitat {

private int zitat_id;
private int quellseite;
private int quellabsatz;
private int zielseite;
private int zielabsatz;
private String inhalt;
private int quellwerk;
private int zielwerk;



//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public ZitatImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt die zitat_id vom typ int und wirft eine RemoteException.
public void set_zitat_id(int ZitatID)throws RemoteException {
this.zitat_id = ZitatID;}

//Diese Methode setzt die quellseite vom typ int und wirft eine RemoteException.
public void set_quellseite(int Quellseite)throws RemoteException {
this.quellseite = Quellseite;}

//Diese Methode setzt den quellabsatz vom typ int und wirft eine RemoteException.
public void set_quellabsatz(int Quellabsatz)throws RemoteException {
this.quellabsatz = Quellabsatz;}

//Diese Methode setzt die zielseite vom typ int und wirft eine RemoteException.
public void set_zielseite(int Zielseite)throws RemoteException {
this.zielseite = Zielseite;}

//Diese Methode setzt den zielabsatz vom typ int und wirft eine RemoteException.
public void set_zielabsatz(int Zielabsatz)throws RemoteException {
this.zielabsatz = Zielabsatz;}

//Diese Methode setzt den inhalt vom typ String und wirft eine RemoteException.
public void set_inhalt(String Inhalt)throws RemoteException {
this.inhalt = Inhalt;}

//Diese Methode setzt das quellwerk vom typ int und wirft eine RemoteException.
public void set_quellwerk(int Quellwerk)throws RemoteException {
this.quellwerk = Quellwerk;}

//Diese Methode setzt das ziel vom typ int und wirft eine RemoteException.
public void set_zielwerk(int Zielwerk)throws RemoteException {
this.zielwerk = Zielwerk;}



//Diese Methode gibt die zitat_id vom typ int zurück und es wird eine RemoteException geworfen.
public int get_zitat_id()throws RemoteException {
return zitat_id;}

//Diese Methode gibt die quellseite vom typ intzurück und es wird eine RemoteException geworfen.
public int get_quellseite()throws RemoteException {
return quellseite;}

//Diese Methode gibt den quellabsatz vom typ int zurück und es wird eine RemoteException geworfen.
public int get_quellabsatz()throws RemoteException {
return quellabsatz;}

//Diese Methode gibt die zielseite vom typ int zurück und es wird eine RemoteException geworfen.
public int get_zielseite()throws RemoteException {
return zielseite;}

//Diese Methode gibt den zielabsatz vom typ int zurück und es wird eine RemoteException geworfen.
public int get_zielabsatz()throws RemoteException {
return zielabsatz;}

//Diese Methode gibt den inhalt vom typ String zurück und es wird eine RemoteException geworfen.
public String get_inhalt()throws RemoteException {
return inhalt;}

//Diese Methode gibt das quellwerk vom typ int zurück und es wird eine RemoteException geworfen.
public int get_quellwerk()throws RemoteException {
return quellwerk;}

//Diese Methode gibt das zielwerk vom typ int zurück und es wird eine RemoteException geworfen.
public int get_zielwerk()throws RemoteException {
return zielwerk;}

}

