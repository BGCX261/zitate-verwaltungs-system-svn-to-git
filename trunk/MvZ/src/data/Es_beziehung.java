/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;



public interface Es_beziehung extends java.rmi.Remote {

    public int get_sammelwerk_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die sammelwerk_id vom typ int zurück und es wird eine RemoteException geworfen.
    public int get_einzelwerk_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die einzelwerk_id vom typ int zurück und es wird eine RemoteException geworfen.
    public void set_sammelwerk_id (int sammelwerk_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die sammelwerk_id und es wird eine RemoteException geworfen.
    public void set_einzelwerk_id (int einzelwerk_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die einzelwerk_id und es wird eine RemoteException geworfen.

}