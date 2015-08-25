/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;




public class SammelwerkImpl extends java.rmi.server.UnicastRemoteObject implements Sammelwerk {

private boolean s_typ = true;





//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public SammelwerkImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt den s_typ vom typ boolean und wirft eine RemoteException.
public void set_s_typ(boolean S_typ)throws RemoteException {
this.s_typ = S_typ;}


//Diese Methode gibt die flughafen_id vom typ int zurück und es wird eine RemoteException geworfen.
public boolean get_s_typ()throws RemoteException {
return s_typ;}


}