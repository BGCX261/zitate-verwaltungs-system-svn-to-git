/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;



public interface Werk extends java.rmi.Remote {
    
    public int get_werk_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die werk_id vom typ int zurück und es wird eine RemoteException geworfen.
    public String get_titel () throws java.rmi.RemoteException ;
    //Diese Methode gibt den titel vom typ String zurück und es wird eine RemoteException geworfen.
    public int get_jahr () throws java.rmi.RemoteException ;
    //Diese Methode gibt das jahr vom typ int zurück und es wird eine RemoteException geworfen.
     public int get_seiten () throws java.rmi.RemoteException ;
    //Diese Methode gibt die seiten vom typ int zurück und es wird eine RemoteException geworfen.
     public char get_werk_typ () throws java.rmi.RemoteException ;
    //Diese Methode gibt den werk_typ vom typ char zurück und es wird eine RemoteException geworfen.
    public int get_verlag_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die verlag_id vom typ int zurück und es wird eine RemoteException geworfen.
    public int get_s_typ () throws java.rmi.RemoteException ;
    //Diese Methode gibt die verlag_id vom typ int zurück und es wird eine RemoteException geworfen.
    
    
    public void set_werk_id (int werk_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die werk_id und es wird eine RemoteException geworfen.
    public void set_titel (String titel) throws java.rmi.RemoteException ;
    //Diese Methode setzt den titel und es wird eine RemoteException geworfen.
    public void set_jahr (int jahr) throws java.rmi.RemoteException ;
    //Diese Methode setzt das jahr und es wird eine RemoteException geworfen.
    public void set_seiten (int seiten) throws java.rmi.RemoteException ;
    //Diese Methode setzt die seiten und es wird eine RemoteException geworfen.
    public void set_werk_typ (char werk_typ) throws java.rmi.RemoteException ;
    //Diese Methode setzt den werk_typ und es wird eine RemoteException geworfen.
    public void set_verlag_id (int verlag_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt den werk_typ und es wird eine RemoteException geworfen.
    public void set_s_typ (int s_typ) throws java.rmi.RemoteException ;
    //Diese Methode setzt den werk_typ und es wird eine RemoteException geworfen.



}