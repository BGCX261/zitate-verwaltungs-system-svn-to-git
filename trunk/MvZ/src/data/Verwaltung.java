///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
package data;
import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Vector;



public interface Verwaltung extends java.rmi.Remote {


    public Person createPerson(int person_id, String vorname, String name, String anrede, String titel) throws java.rmi.RemoteException ;
    public Person updatePerson(int person_id, String vorname, String name, String anrede, String titel) throws java.rmi.RemoteException ;
    public void deletePerson(Person x) throws java.rmi.RemoteException ;
    public Person findbyid_Person(int id) throws java.rmi.RemoteException ;

    public Verlag createVerlag(int verlag_id, String name, String ort) throws java.rmi.RemoteException ;
    public Verlag updateVerlag(int verlag_id, String name, String ort) throws java.rmi.RemoteException ;
    public void deleteVerlag(Verlag x) throws java.rmi.RemoteException ;
    public Verlag findbyid_Verlag(int id) throws java.rmi.RemoteException ;
    
    public Zitat createZitat(int zitat_id, int quellwerk, int quellseite, int quellabsatz, int zielwerk, int zielseite, int zielabsatz, String inhalt) throws java.rmi.RemoteException;
    public Zitat updateZitat(int zitat_id, int quellwerk, int quellseite, int quellabsatz, int zielwerk, int zielseite, int zielabsatz, String inhalt) throws java.rmi.RemoteException ;
    public void deleteZitat(Zitat x) throws java.rmi.RemoteException ;
    public Zitat findbyid_Zitat(int id) throws java.rmi.RemoteException ;
    
    public Werk createWerk(String titel, int jahr, int seiten, char werk_typ) throws java.rmi.RemoteException;
    public Werk updateWerk(String titel, int jahr, int seiten, char werk_typ) throws java.rmi.RemoteException ;
    public void deleteWerk(Werk x) throws java.rmi.RemoteException ;
    public Werk create_einzelwerk_complete(String titel, int jahr, int seitenzahl, String werktyp, Person herausgeber, Person autor, Verlag verlag) throws java.rmi.RemoteException ;


//    public Sammelwerk createSammelwerk(String titel, int jahr, int seiten, char werk_typ) throws java.rmi.RemoteException;
//    public Sammelwerk updateSammelwerk(String titel, int jahr, int seiten, char werk_typ) throws java.rmi.RemoteException ;
//    public void deleteSammelwerk(Sammelwerk x) throws java.rmi.RemoteException ;


    public Rolle createRolle_herausgeber(int person_id, int werk_id) throws java.rmi.RemoteException;
    public Rolle updateRolle_herausgeber(int person_id, int werk_id) throws java.rmi.RemoteException ;
    public void deleteRolle_herausgeber(Rolle x) throws java.rmi.RemoteException ;

    
    public Rolle createRolle_autor(int person_id, int werk_id) throws java.rmi.RemoteException;
    public Rolle updateRolle_autor(int person_id, int werk_id) throws java.rmi.RemoteException ;
    public void deleteRolle_autor(Rolle x) throws java.rmi.RemoteException ;


    public Es_beziehung create_es_Beziehung(int sammelwerk_id, int quellwerk_id) throws java.rmi.RemoteException;
    public Es_beziehung update_es_Beziehung(int sammelwerk_id, int quellwerk_id) throws java.rmi.RemoteException ;
    public void delete_es_Beziehung(Es_beziehung x) throws java.rmi.RemoteException ;





    public Vector <Person> getAll_Person() throws java.rmi.RemoteException;
    public Vector <Zitat> getAll_Zitat() throws java.rmi.RemoteException;
    public Vector <Werk> getAll_Werk() throws java.rmi.RemoteException;
//    public Vector <Sammelwerk> getAll_Sammelwerk() throws java.rmi.RemoteException;
    public Vector <Verlag> getAll_Verlag() throws java.rmi.RemoteException;



//public int getPRAnzahl_byTime (Timestamp start, Timestamp end, Produkt pr) throws java.rmi.RemoteException;
//
//public void addZuordnung(Bestellung be, Produkt pr, int anzahl) throws java.rmi.RemoteException;
//
// public boolean compareFLbezeichnung(Fluglinie x) throws java.rmi.RemoteException;
// public boolean compareFGflug(Flug x) throws java.rmi.RemoteException;
// public boolean compareGAfluggast(Fluggast x) throws java.rmi.RemoteException;
// public boolean compareFHkuerzel(Flughafen x) throws java.rmi.RemoteException;
// public boolean compareFZkennzeichen(Flugzeug x) throws java.rmi.RemoteException;
// public boolean compareFTbezeichnung(Flugzeugtyp x) throws java.rmi.RemoteException;
// public boolean compareBUbuchung_nr(Buchung x) throws java.rmi.RemoteException;
//
//
//    public boolean compareTInummer(Tisch x) throws java.rmi.RemoteException;
//
//    public boolean equals () throws java.rmi.RemoteException ;
}
