/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import datenbank.*;
import java.rmi.*;
import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;




public class VerwaltungImpl extends java.rmi.server.UnicastRemoteObject implements Verwaltung {

    private zitatMapper ziMapper;
    private verlagMapper veMapper;
    private rolleMapper roMapper;
    private personMapper peMapper;
    private werkMapper weMapper;
    private es_beziehungMapper esMapper;



   public VerwaltungImpl( es_beziehungMapper es_beziehungMapper, personMapper personMapper, rolleMapper rolleMapper, verlagMapper verlagMapper, werkMapper werkMapper, zitatMapper zitatMapper) throws RemoteException {

       super();
        this.ziMapper = zitatMapper;
        this.veMapper = verlagMapper;
        this.roMapper = rolleMapper;
        this.peMapper = personMapper;
        this.weMapper = werkMapper;
        this.esMapper = es_beziehungMapper;
    }

    /**
     * Die addProdukt Methode legt ein neues Produkt in der Datenbank an.
     * Dafür bekommt sie als Parameter das Objekt des Produkts, welches in der
     * Datenbank angelegt werden soll.
     * Sie überprüft dann ob es die Bezeichnung bereits gibt. Falls nicht wird es eingetragen, andernfalls wird
     * eine Fehlermeldung angezeigt
     * @param pr ist das Produkt, das angelegt werden soll
     * @return das Produkt, das angelegt wurde, falls die Bezeichnung noch nicht vergeben wurde
     * @throws RemoteException
     */
    public Person createPerson(int person_id, String vorname, String name, String anrede, String titel) throws RemoteException {

        Person pe = new PersonImpl();
        pe.set_person_id(2);
        pe.set_vorname(vorname);
        pe.set_name(name);
        pe.set_anrede(anrede);
        pe.set_titel(titel);
        
      if (!pe.get_vorname().isEmpty() && !pe.get_name().isEmpty() && !pe.get_anrede().isEmpty() && !pe.get_titel().isEmpty() )
        return peMapper.insert(pe); //Speichert das Produkt


    else {
        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");

        }
    }


//    public boolean compareFLbezeichnung(Fluglinie x) throws RemoteException {
//        Vector<Fluglinie> fl = this.flMapper.findAll_fluglinie();
//        Iterator itrPr = fl.iterator();
//
//        while (itrPr.hasNext()) {
//            Fluglinie flTemp = (Fluglinie) itrPr.next();
//
//            if (flTemp.get_bezeichnung().contentEquals(x.get_bezeichnung())) {
//                return false;
//            }
//        }
//        return true;
//    }
/**
     * Diese Methode speichert eine Änderung bei einem Produkt in der Datenbank
     * ab. Dabei wird überprüft ob die Bezeichnung bereits vorhanden ist und ansonsten
     * wird die Änderung gespeichert. Sollte die Bezeichnung vorhanden sein, wird eine Remote Exception geworfen.
     * @param x ist das geänderte Produkt
     * @return das geänderte Produkt wird auch wieder zurückgegeben und in die Datenbank gespeichert
     * @throws RemoteException
     */
    public Person updatePerson(int person_id, String vorname, String name, String anrede, String titel) throws RemoteException {

       Person pe = new PersonImpl();
       pe.set_person_id(person_id);
       pe.set_vorname(vorname);
       pe.set_name(name);
       pe.set_anrede(anrede);
       pe.set_titel(titel);

//       if (this.compareFLbezeichnung(fl) && !fl.get_bezeichnung().isEmpty())
        return peMapper.update(pe); //Speichert das Produkt


//        else {
//        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");
//
//        }
    }


    /**
     * Die Methode löscht ein Produkt aus der Datenbank, falls dieses in keiner
     * Bestellung mehr vorhanden ist. Sollte das Produkt noch in einer Bestellung vorhanden sein,
     * wird eine RemoteException geworfen.
     * @param x das Produkt, welches gelöscht werden soll
     * @throws RemoteException
     */
 public void deletePerson(Person x)throws RemoteException {
        try {
            if (roMapper.existsZuordnung(x)==false) {
                peMapper.delete(x);
            }else {
                  throw new RemoteException("Der Person sind noch Rollen zugewiesen. Bitte erst Rollen entfernen");
            }
       }
        catch (Exception ex) {
            ex.printStackTrace();
        }
 }


 public Vector <Person> getAll_Person()throws RemoteException  {

        return peMapper.findAll_person();
    }

 public Person findbyid_Person(int id)throws RemoteException  {

        return peMapper.findbyid(id);
    }
 

  public Verlag createVerlag(int verlag_id, String name, String ort) throws RemoteException {

        Verlag ve = new VerlagImpl();
        ve.set_verlag_id(2);
        ve.set_name(name);
        ve.set_ort(ort);
      
if (!ve.get_name().isEmpty() && !ve.get_ort().isEmpty() )
        return veMapper.insert(ve); //Speichert das Produkt

else {
        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");

        }
        }



      public Verlag updateVerlag(int verlag_id,String name, String ort) throws RemoteException {

      Verlag ve = new VerlagImpl();
 
        ve.set_verlag_id(verlag_id);
        ve.set_name(name);
        ve.set_ort(ort);


        return veMapper.update(ve); //Speichert das Produkt


    }

      public void deleteVerlag(Verlag x)throws RemoteException {
//        try {
//            if (veMapper.existsZuordnung(x.get_verlag_id())==false) {
                veMapper.delete(x);
//            }else {
//                  throw new RemoteException("Die Fluglinie ist noch Flügen zugeordnet und kann nicht gelöscht werden");
//            }
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
 }


      public Vector <Verlag> getAll_Verlag()throws RemoteException  {

        return veMapper.findAll_verlag();
    }

      public Verlag findbyid_Verlag(int id)throws RemoteException  {

        return veMapper.findbyid(id);
    }


    public Zitat createZitat(int zitat_id, int quellwerk, int quellseite, int quellabsatz, int zielwerk, int zielseite, int zielabsatz, String inhalt) throws RemoteException {

        Zitat zi = new ZitatImpl();
        zi.set_zitat_id(2);
        zi.set_quellwerk(quellwerk);
        zi.set_quellseite(quellseite);
        zi.set_quellabsatz(quellabsatz);
        zi.set_zielwerk(zielwerk);
        zi.set_zielseite(zielseite);
        zi.set_zielabsatz(zielabsatz);
        zi.set_inhalt(inhalt);


//    if (!zi.get_quellseite().isEmpty() && !zi.get_quellabsatz().isEmpty() && !zi.get_zielseite().isEmpty() && !zi.get_zielabsatz().isEmpty() && !zi.get_inhalt().isEmpty() && !zi.get_quellwerk().isEmpty()&& !zi.get_zielwerk().isEmpty())
       return ziMapper.insert(zi); //Speichert das Produkt


//    else {
//        throw new RemoteException("Eingabe überprüfen.");
//
//        }
    }


/**
     * Diese Methode speichert eine Änderung bei einem Produkt in der Datenbank
     * ab. Dabei wird überprüft ob die Bezeichnung bereits vorhanden ist und ansonsten
     * wird die Änderung gespeichert. Sollte die Bezeichnung vorhanden sein, wird eine Remote Exception geworfen.
     * @param x ist das geänderte Produkt
     * @return das geänderte Produkt wird auch wieder zurückgegeben und in die Datenbank gespeichert
     * @throws RemoteException
     */
    public Zitat updateZitat(int zitat_id, int quellwerk, int quellseite, int quellabsatz, int zielwerk, int zielseite, int zielabsatz, String inhalt) throws RemoteException {

        Zitat zi = new ZitatImpl();
        zi.set_zitat_id(2);
        zi.set_quellwerk(quellwerk);
        zi.set_quellseite(quellseite);
        zi.set_quellabsatz(quellabsatz);
        zi.set_zielwerk(zielwerk);
        zi.set_zielseite(zielseite);
        zi.set_zielabsatz(zielabsatz);
        zi.set_inhalt(inhalt);


        return ziMapper.update(zi); //Speichert das Produkt


        }



    /**
     * Die Methode löscht ein Produkt aus der Datenbank, falls dieses in keiner
     * Bestellung mehr vorhanden ist. Sollte das Produkt noch in einer Bestellung vorhanden sein,
     * wird eine RemoteException geworfen.
     * @param x das Produkt, welches gelöscht werden soll
     * @throws RemoteException
     */
 public void deleteZitat(Zitat x)throws RemoteException {

               ziMapper.delete(x);

 }
    
 public Vector <Zitat> getAll_Zitat()throws RemoteException  {

        return ziMapper.findAll_zitat();
    }

 public Zitat findbyid_Zitat(int id)throws RemoteException  {

        return ziMapper.findbyid(id);
    }





    public Werk createWerk(String titel, int jahr, int seiten, char werk_typ) throws RemoteException {

        Werk we = new WerkImpl();
        we.set_werk_id(2);
        we.set_titel(titel);
        we.set_jahr(jahr);
        we.set_seiten(seiten);
        we.set_werk_typ(werk_typ);


//        if (!we.get_titel().isEmpty() && !we.get_jahr().isEmpty() && !we.get_seiten().isEmpty() && !we.get_werk_typ().isEmpty() )
        return weMapper.insert(we); //Speichert das Produkt


//    else {
//        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");
//
//        }
    }


//    public boolean compareFHkuerzel(Flughafen x) throws RemoteException {
//        Vector<Flughafen> fh = this.fhMapper.findAll_flughafen();
//        Iterator itrPr = fh.iterator();
//
//        while (itrPr.hasNext()) {
//            Flughafen fhTemp = (Flughafen) itrPr.next();
//
//            if (fhTemp.get_kuerzel().contentEquals(x.get_kuerzel())) {
//                return false;
//            }
//        }
//        return true;
//    }
/**
     * Diese Methode speichert eine Änderung bei einem Produkt in der Datenbank
     * ab. Dabei wird überprüft ob die Bezeichnung bereits vorhanden ist und ansonsten
     * wird die Änderung gespeichert. Sollte die Bezeichnung vorhanden sein, wird eine Remote Exception geworfen.
     * @param x ist das geänderte Produkt
     * @return das geänderte Produkt wird auch wieder zurückgegeben und in die Datenbank gespeichert
     * @throws RemoteException
     */
    public Werk updateWerk(String titel, int jahr, int seiten, char werk_typ) throws RemoteException {

        Werk we = new WerkImpl();
        we.set_werk_id(2);
        we.set_titel(titel);
        we.set_jahr(jahr);
        we.set_seiten(seiten);
        we.set_werk_typ(werk_typ);



//        if (this.compareFHkuerzel(we) && !we.get_kuerzel().isEmpty())
        return weMapper.update(we); //Speichert das Produkt


//        else {
//        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");
//
//        }
    }


    /**
     * Die Methode löscht ein Produkt aus der Datenbank, falls dieses in keiner
     * Bestellung mehr vorhanden ist. Sollte das Produkt noch in einer Bestellung vorhanden sein,
     * wird eine RemoteException geworfen.
     * @param x das Produkt, welches gelöscht werden soll
     * @throws RemoteException
     */
 public void deleteWerk(Werk x)throws RemoteException {

                weMapper.delete(x);

 }
    public Vector <Werk> getAll_Werk()throws RemoteException  {

        return weMapper.findAll_werk();
    }








//
//    public Sammelwerk createSammelwerk(boolean s_typ, int sammelwerk_id, String titel, int jahr, int seiten, char werk_typ) throws RemoteException {
//
//        Sammelwerk sa = new SammelwerkImpl();
//
//        sa.set_s_typ(s_typ);
//
//
//    if (this.compareFZkennzeichen(fz) && !fz.get_kennzeichen().isEmpty())
//        return weMapper.insert(sa); //Speichert das Produkt
//
//
//    else {
//        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");
//
//        }
//    }

//
//    public boolean compareFZkennzeichen(Flugzeug x) throws RemoteException {
//        Vector<Flugzeug> fz = this.fzMapper.findAll_flugzeug();
//        Iterator itrPr = fz.iterator();
//
//        while (itrPr.hasNext()) {
//            Flugzeug fzTemp = (Flugzeug) itrPr.next();
//
//            if (fzTemp.get_kennzeichen().contentEquals(x.get_kennzeichen())) {
//                return false;
//            }
//        }
//        return true;
//    }
///**
//     * Diese Methode speichert eine Änderung bei einem Produkt in der Datenbank
//     * ab. Dabei wird überprüft ob die Bezeichnung bereits vorhanden ist und ansonsten
//     * wird die Änderung gespeichert. Sollte die Bezeichnung vorhanden sein, wird eine Remote Exception geworfen.
//     * @param x ist das geänderte Produkt
//     * @return das geänderte Produkt wird auch wieder zurückgegeben und in die Datenbank gespeichert
//     * @throws RemoteException
//     */
//    public Sammelwerk updateSammelwerk(String titel, int jahr, int seiten, char werk_typ) throws RemoteException {
//
//        Sammelwerk sa = new SammelwerkImpl();
//        sa.set_flugzeug_id(2);
//        sa.set_typ_id(typ_id);
//        sa.set_fluglinie_id(fluglinie_id);
//        sa.set_kennzeichen(kennzeichen);
//        sa.set_bezeichnung(bezeichnung);
//
//
//        if (this.compareFZkennzeichen(fz) && !fz.get_kennzeichen().isEmpty())
//        return fzMapper.update(fz); //Speichert das Produkt
//
//
//        else {
//        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");
//
//        }
//    }
//
//
//    /**
//     * Die Methode löscht ein Produkt aus der Datenbank, falls dieses in keiner
//     * Bestellung mehr vorhanden ist. Sollte das Produkt noch in einer Bestellung vorhanden sein,
//     * wird eine RemoteException geworfen.
//     * @param x das Produkt, welches gelöscht werden soll
//     * @throws RemoteException
//     */
// public void delete(Flugzeug x)throws RemoteException {
//
//                fzMapper.delete(x);
//
// }
//    public Vector <Sammelwerk> getAll_Sammelwerk()throws RemoteException  {
//
//        return fzMapper.findAll_flugzeug();
//    }






   public Rolle createRolle_herausgeber(int person_id, int werk_id) throws RemoteException {

        Rolle ro = new RolleImpl();
        ro.set_person_id(2);
        ro.set_werk_id(werk_id);
      
      

//    if (this.compareFTbezeichnung(ro) && !ro.get_bezeichnung().isEmpty())
        return roMapper.insert(ro); //Speichert das Produkt

//
//    else {
//        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");
//
//        }
//    }
//
//
//    public boolean compareFTbezeichnung(Flugzeugtyp x) throws RemoteException {
//        Vector<Flugzeugtyp> ft = this.ftMapper.findAll_flugzeugtyp();
//        Iterator itrPr = ft.iterator();
//
//        while (itrPr.hasNext()) {
//            Flugzeugtyp ftTemp = (Flugzeugtyp) itrPr.next();
//
//            if (ftTemp.get_bezeichnung().contentEquals(x.get_bezeichnung())) {
//                return false;
//            }
//        }
//        return true;
    }
/**
     * Diese Methode speichert eine Änderung bei einem Produkt in der Datenbank
     * ab. Dabei wird überprüft ob die Bezeichnung bereits vorhanden ist und ansonsten
     * wird die Änderung gespeichert. Sollte die Bezeichnung vorhanden sein, wird eine Remote Exception geworfen.
     * @param x ist das geänderte Produkt
     * @return das geänderte Produkt wird auch wieder zurückgegeben und in die Datenbank gespeichert
     * @throws RemoteException
     */
    public Rolle updateRolle_herausgeber(int person_id, int werk_id) throws RemoteException {

        Rolle ro = new RolleImpl();
        ro.set_person_id(2);
        ro.set_werk_id(werk_id);


//        if (this.compareFTbezeichnung(ro) && !ro.get_bezeichnung().isEmpty())
        return roMapper.update(ro); //Speichert das Produkt


//        else {
//        throw new RemoteException("Bezeichnung ist leer oder schon vorhanden.");
//
//        }
    }


    /**
     * Die Methode löscht ein Produkt aus der Datenbank, falls dieses in keiner
     * Bestellung mehr vorhanden ist. Sollte das Produkt noch in einer Bestellung vorhanden sein,
     * wird eine RemoteException geworfen.
     * @param x das Produkt, welches gelöscht werden soll
     * @throws RemoteException
     */
 public void deleteRolle_herausgeber(Rolle x)throws RemoteException {
//        try {
//            if (fzMapper.existsZuordnung(x.get_typ_id())==false) {
                roMapper.delete(x);
//            }else {
//                  throw new RemoteException("Die Fluglinie ist noch Flügen zugeordnet und kann nicht gelöscht werden");
//            }
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
 }
//    public Vector <Flugzeugtyp> findAll_flugzeugtyp()throws RemoteException  {
//
//        return ftMapper.findAll_flugzeugtyp();
//    }


   public Rolle createRolle_autor(int person_id, int werk_id) throws RemoteException {

        Rolle ro = new RolleImpl();
        ro.set_person_id(2);
        ro.set_werk_id(werk_id);

        return roMapper.insert(ro); //Speichert das Produkt



    }


//    public boolean compareBUbuchung_nr(Buchung x) throws RemoteException {
//        Vector<Buchung> bu = this.buMapper.findAll_buchung();
//        Iterator itrPr = bu.iterator();
//
//        while (itrPr.hasNext()) {
//            Buchung buTemp = (Buchung) itrPr.next();
//
//
//        }
//        return true;
//    }
/**
     * Diese Methode speichert eine Änderung bei einem Produkt in der Datenbank
     * ab. Dabei wird überprüft ob die Bezeichnung bereits vorhanden ist und ansonsten
     * wird die Änderung gespeichert. Sollte die Bezeichnung vorhanden sein, wird eine Remote Exception geworfen.
     * @param x ist das geänderte Produkt
     * @return das geänderte Produkt wird auch wieder zurückgegeben und in die Datenbank gespeichert
     * @throws RemoteException
     */
    public Rolle updateRolle_autor(int person_id, int werk_id) throws RemoteException {

        Rolle ro = new RolleImpl();
        ro.set_person_id(2);
        ro.set_werk_id(werk_id);


        return roMapper.update(ro); //Speichert das Produkt



    }


    /**
     * Die Methode löscht ein Produkt aus der Datenbank, falls dieses in keiner
     * Bestellung mehr vorhanden ist. Sollte das Produkt noch in einer Bestellung vorhanden sein,
     * wird eine RemoteException geworfen.
     * @param x das Produkt, welches gelöscht werden soll
     * @throws RemoteException
     */
 public void deleteRolle_autor(Rolle x)throws RemoteException {

                roMapper.delete(x);

 }
//    public Vector <Buchung> findAll_buchung()throws RemoteException  {
//
//        return buMapper.findAll_buchung();
//    }
//
//    public Fluggast addFluggaeste(String nachname, String vorname, String strasse, int plz, String ort) throws RemoteException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

















    public Es_beziehung create_es_Beziehung(int sammelwerk_id, int werk_id) throws RemoteException {

        Es_beziehung es = new Es_beziehungImpl();
        es.set_sammelwerk_id(2);
        es.set_einzelwerk_id(2);

        return esMapper.insert(es); //Speichert das Produkt



    }


//    public boolean compareBUbuchung_nr(Buchung x) throws RemoteException {
//        Vector<Buchung> bu = this.buMapper.findAll_buchung();
//        Iterator itrPr = bu.iterator();
//
//        while (itrPr.hasNext()) {
//            Buchung buTemp = (Buchung) itrPr.next();
//
//
//        }
//        return true;
//    }
/**
     * Diese Methode speichert eine Änderung bei einem Produkt in der Datenbank
     * ab. Dabei wird überprüft ob die Bezeichnung bereits vorhanden ist und ansonsten
     * wird die Änderung gespeichert. Sollte die Bezeichnung vorhanden sein, wird eine Remote Exception geworfen.
     * @param x ist das geänderte Produkt
     * @return das geänderte Produkt wird auch wieder zurückgegeben und in die Datenbank gespeichert
     * @throws RemoteException
     */
    public Es_beziehung update_es_Beziehung(int sammelwerk_id, int quellwerk_id) throws RemoteException {

        Es_beziehung es = new Es_beziehungImpl();
        es.set_sammelwerk_id(2);
        es.set_einzelwerk_id(2);


        return esMapper.update(es); //Speichert das Produkt



    }


    /**
     * Die Methode löscht ein Produkt aus der Datenbank, falls dieses in keiner
     * Bestellung mehr vorhanden ist. Sollte das Produkt noch in einer Bestellung vorhanden sein,
     * wird eine RemoteException geworfen.
     * @param x das Produkt, welches gelöscht werden soll
     * @throws RemoteException
     */
 public void delete_es_Beziehung(Es_beziehung x)throws RemoteException {

                esMapper.delete(x);

 }
//    public Vector <Buchung> findAll_buchung()throws RemoteException  {
//
//        return buMapper.findAll_buchung();
//    }
//
//    public Fluggast addFluggaeste(String nachname, String vorname, String strasse, int plz, String ort) throws RemoteException {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }

 public Werk create_einzelwerk_complete(String titel, int jahr, int seitenzahl, String werktyp, Person herausgeber, Person autor, Verlag verlag) throws java.rmi.RemoteException {
        
        char werk_final = 'x';
        //Werktyp auslesen und umsetzen
        if(werktyp.equals("Buch"))
        werk_final = 'b';
        if(werktyp.equals("Zeitschrift"))
        werk_final = 'z';
        if(werktyp.equals("Thesis"))
        werk_final = 't';
     

        Werk we = new WerkImpl();
        we.set_werk_id(0);  //Nicht entgültige ID, diese wird in der DB ermittelt und geschrieben
        we.set_titel(titel);
        we.set_jahr(jahr);
        we.set_seiten(seitenzahl);
        we.set_werk_typ(werk_final);
        we.set_verlag_id(verlag.get_verlag_id());
        we.set_s_typ(0);   // Kein Sammelwerk, deswegen 0

        Werk we2 = weMapper.insert(we);                             //Speichert das Werk
     
        int werk_id = we2.get_werk_id();                            //Die vergebene WerkID auslesen
        int person_id_herausgeber = herausgeber.get_person_id();    //Die personID vom Herausgeber lesen
        int person_id_autor = autor.get_person_id();                //Die personID vom Autor lesen
       
        // Herausgeber Mapping vornehmern
        Rolle re_herausgeber = new RolleImpl();
        re_herausgeber.set_werk_id(werk_id);
        re_herausgeber.set_person_id(person_id_herausgeber);
        re_herausgeber.set_typ('h');
        return (Werk) roMapper.insert(re_herausgeber);

        // Autor Mapping vornehmen
        Rolle re_autor = new RolleImpl();
        re_autor.set_werk_id(werk_id);
        re_autor.set_person_id(person_id_autor);
        re_autor.set_typ('a');
       return  (Werk) roMapper.insert(re_autor);
 }


}







