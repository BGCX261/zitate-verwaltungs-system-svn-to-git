/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;



public interface Zitat extends java.rmi.Remote {
    
    public int get_zitat_id () throws java.rmi.RemoteException ;
    //Diese Methode gibt die zitat_id vom typ int zurück und es wird eine RemoteException geworfen.
    public int get_quellseite () throws java.rmi.RemoteException ;
    //Diese Methode gibt die quellseite vom typ int zurück und es wird eine RemoteException geworfen.
    public int get_quellabsatz () throws java.rmi.RemoteException ;
    //Diese Methode gibt den quellabsatz vom typ String zurück und es wird eine RemoteException geworfen.
    public int get_zielseite () throws java.rmi.RemoteException ;
    //Diese Methode gibt die zielseite_id vom typ int zurück und es wird eine RemoteException geworfen.
    public int get_zielabsatz () throws java.rmi.RemoteException ;
    //Diese Methode gibt den zielabsatz vom typ int zurück und es wird eine RemoteException geworfen.
    public String get_inhalt () throws java.rmi.RemoteException ;
    //Diese Methode gibt den inhalt vom typ String zurück und es wird eine RemoteException geworfen.
    public int get_quellwerk () throws java.rmi.RemoteException ;
    //Diese Methode gibt das quellwerk vom typ int zurück und es wird eine RemoteException geworfen.
    public int get_zielwerk () throws java.rmi.RemoteException ;
    //Diese Methode gibt das zielwerk vom typ int zurück und es wird eine RemoteException geworfen.


    public void set_zitat_id (int zitat_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt die zitat_id und es wird eine RemoteException geworfen.
    public void set_quellseite (int quellseite) throws java.rmi.RemoteException ;
    //Diese Methode setzt die quellseite und es wird eine RemoteException geworfen.
    public void set_quellabsatz (int quellabsatz) throws java.rmi.RemoteException ;
    //Diese Methode setzt den quellabsatz und es wird eine RemoteException geworfen.
    public void set_zielseite (int zielseite) throws java.rmi.RemoteException ;
    //Diese Methode setzt die zielseite und es wird eine RemoteException geworfen.
    public void set_zielabsatz (int zielabsatz) throws java.rmi.RemoteException ;
    //Diese Methode setzt den zielabsatz und es wird eine RemoteException geworfen.
    public void set_inhalt (String inhalt) throws java.rmi.RemoteException ;
    //Diese Methode setzt den inhalt und es wird eine RemoteException geworfen.
    public void set_quellwerk (int werk_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt das quellwerk und es wird eine RemoteException geworfen.
    public void set_zielwerk (int werk_id) throws java.rmi.RemoteException ;
    //Diese Methode setzt das zielwerk und es wird eine RemoteException geworfen.


}