/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;



public interface Person extends java.rmi.Remote {
    
    public int get_person_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die person_id vom typ int zurück und es wird eine RemoteException geworfen.
    public String get_vorname () throws java.rmi.RemoteException ;
    //Diese Methode gibt den vorname vom typ String zurück und es wird eine RemoteException geworfen.
    public String get_name () throws java.rmi.RemoteException ;
    //Diese Methode gibt den namen vom typ String zurück und es wird eine RemoteException geworfen.
    public String get_anrede () throws java.rmi.RemoteException ;
    //Diese Methode gibt die anrede vom typ String zurück und es wird eine RemoteException geworfen.
    public String get_titel () throws java.rmi.RemoteException ;
    //Diese Methode gibt den titel vom typ String zurück und es wird eine RemoteException geworfen.
    public void set_person_id (int person_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die person_id und es wird eine RemoteException geworfen.
    public void set_vorname (String vorname) throws java.rmi.RemoteException ;
    //Diese Methode setzt den vornamen und es wird eine RemoteException geworfen.
    public void set_name (String name) throws java.rmi.RemoteException ;
    //Diese Methode setzt den namen und es wird eine RemoteException geworfen.
    public void set_anrede (String anrede) throws java.rmi.RemoteException ;
    //Diese Methode setzt die anrede und es wird eine RemoteException geworfen.
    public void set_titel (String titel) throws java.rmi.RemoteException ;
    //Diese Methode setzt den titel und es wird eine RemoteException geworfen.

}