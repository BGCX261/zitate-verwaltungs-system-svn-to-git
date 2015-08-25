/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.rmi.RemoteException;
/**
 *
 * @author Ben
 */
public class EinzelwerkImpl {

private boolean s_typ= false;




//Dieser Konstruktor ruft den Konstruktor der Superklasse UnicastRemoteObject auf.

public EinzelwerkImpl ()throws java.rmi.RemoteException {
super();}


//Diese Methode setzt den s_typ vom typ boolean und wirft eine RemoteException.
public void set_s_typ(boolean S_typ)throws RemoteException {
this.s_typ = S_typ;}


//Diese Methode gibt die flughafen_id vom typ int zurück und es wird eine RemoteException geworfen.
public boolean get_s_typ()throws RemoteException {
return s_typ;}


}