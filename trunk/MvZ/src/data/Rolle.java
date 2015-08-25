/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;



public interface Rolle extends java.rmi.Remote {

    public int get_person_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die person_id vom typ int zurück und es wird eine RemoteException geworfen.
    public int get_werk_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die werk_id vom typ int zurück und es wird eine RemoteException geworfen.
    public char get_typ () throws java.rmi.RemoteException ;
    //Diese Methode gibt den typ vom typ char zurück und es wird eine RemoteException geworfen.

    public void set_person_id (int person_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die person_id und es wird eine RemoteException geworfen.
    public void set_werk_id (int werk_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die werk_id und es wird eine RemoteException geworfen.
    public void set_typ (char typ) throws java.rmi.RemoteException ;
    //Diese Methode setzt den typ und es wird eine RemoteException geworfen.

}