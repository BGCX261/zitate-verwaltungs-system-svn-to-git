/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;



public interface Einzelwerk extends java.rmi.Remote {

    public boolean get_s_typ () throws java.rmi.RemoteException ;
    //Diese Methode gibt die buchung_nr vom typ int zurück und es wird eine RemoteException geworfen.

    public void set_s_typ (boolean S_typ) throws java.rmi.RemoteException ;
    //Diese Methode setzt die buchung_nr und es wird eine RemoteException geworfen.

}