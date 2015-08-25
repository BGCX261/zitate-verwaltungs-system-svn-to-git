/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;



public interface Verlag extends java.rmi.Remote {
    
    public int get_verlag_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die verlag_id vom typ int zurück und es wird eine RemoteException geworfen.
    public String get_name () throws java.rmi.RemoteException ;
    //Diese Methode gibt den name vom typ String zurück und es wird eine RemoteException geworfen.
    public String get_ort () throws java.rmi.RemoteException ;
    //Diese Methode gibt den ort vom typ String zurück und es wird eine RemoteException geworfen.

    public void set_verlag_id (int verlag_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die verlag_id und es wird eine RemoteException geworfen.
    public void set_name (String name) throws java.rmi.RemoteException ;
    //Diese Methode setzt den name und es wird eine RemoteException geworfen.
    public void set_ort (String ort) throws java.rmi.RemoteException ;
    //Diese Methode setzt den ort und es wird eine RemoteException geworfen.

}