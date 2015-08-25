/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;

import data.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import javax.swing.text.MaskFormatter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Tobias
 */
public class werkAnlegen {

//Deklarierung der Variablen
JButton abbrechen;
JButton anlegen;
JFrame frame;
JTextField titelField;     
JFormattedTextField jahrField;
JFormattedTextField seitenzahlField;
JComboBox werktyp; 
JComboBox verlag;   
DefaultComboBoxModel comboBoxModel1;      
JList listHerausgeber;
JList listWerk;
JScrollPane scroller;
JLabel titelLabel;
JLabel jahrLabel;
JLabel seitenzahlLabel;
JLabel herausgeberLabel;
JLabel autorLabel;
JLabel typLabel;
JLabel verlagLabel;
String Name;
String Ort;
String Titel;
String Vorname;
String Anrede;
int ID;

public static void main(String[] args) {
        werkAnlegen gui = new werkAnlegen();
        gui.werkAnlegen();
        }   

//Methode welche den Frame, mitsamt den Panels und Komponenten erstellt
public void werkAnlegen(){

//ein neuer Frame wird erstellt   
frame = new JFrame ("Werk anlegen");

//ein neues Panel wird erstellt
JPanel werkAnlegenPanel = new JPanel();

//ein neues Gridbaglayout wird erstellt
GridBagLayout gbl = new GridBagLayout();
    
//dem buchungAnlegenPanel wird das neue Gridbaglayout zugewiesen
werkAnlegenPanel.setLayout(gbl);
 
//es werden neue GridBagConstraints erstellt    
GridBagConstraints constraints = new GridBagConstraints();

// Titel Label und Titel Textfeld erstellen. 
titelLabel= new JLabel("Titel*:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
werkAnlegenPanel.add(titelLabel, constraints);

titelField = new JTextField(50);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(titelField, constraints);

// Jahr Label und Jahr Textfeld erstellen. 
jahrLabel = new JLabel("Jahr:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(jahrLabel, constraints);
        
try {
            jahrField = new JFormattedTextField(new MaskFormatter("####"));
        } catch (ParseException ex) {
            Logger.getLogger(sammelwerkAnlegen.class.getName()).log(Level.SEVERE, null, ex);
        }
jahrField.setColumns(8);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(jahrField, constraints);

// Seitenzahl Label und Seitenzahl Textfeld erstellen. 
seitenzahlLabel = new JLabel("Seitenzahl:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(seitenzahlLabel, constraints);
        
try {
            seitenzahlField = new JFormattedTextField(new MaskFormatter("#####"));
        } catch (ParseException ex) {
            Logger.getLogger(sammelwerkAnlegen.class.getName()).log(Level.SEVERE, null, ex);
        }
seitenzahlField.setColumns(5);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(seitenzahlField, constraints);

// Werktyp Label und Werktyp Combobox erstellen.
typLabel= new JLabel("Werktyp:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(typLabel, constraints);

//Combobox anlegen
werktyp = new JComboBox();
Dimension groesseWerktyp = new Dimension(310, 25);
werktyp.setPreferredSize(groesseWerktyp);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
//Combobox mit vordefinierten Daten f�llen
werktyp.addItem("Buch");
werktyp.addItem("Zeitschrift");
werktyp.addItem("Thesis");
werktyp.addItem("");
werkAnlegenPanel.add(werktyp, constraints);

//Herausgeber Label erstellen
herausgeberLabel = new JLabel("Herausgeber:");
constraints.insets = new Insets(46,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(herausgeberLabel, constraints);


try {
    // Zunächst benötigen wir eine Verbindung mit der Verwaltung.
    Verwaltung verwaltung =
        (Verwaltung)Naming.lookup("rmi:/localhost:1099/DRM");


    Vector<Person> ergebnis1 = verwaltung.getAll_Person();
    Vector inhalte = new Vector();

    //Hier wird dem Vector 'inhalte' das Objekt Person mit den Elementen Titel, Vorname und Name
    //übergeben und der Combobox 'person' hinzugefügt
    for ( Person test : ergebnis1 ) {
        if (test != null ) {
            //Es wird überprüft ob die Person einen Titel besitzt. Trifft dies nicht zu wird dieser
            //in der Combobox weggelassen.
            if (test.get_titel().equals("no title")) {
                inhalte.add(test.get_vorname() + "  " + test.get_name());
            }
            else
                inhalte.add(test.get_titel()+ ". " + test.get_vorname() + "  " + test.get_name());
        }
    }


listHerausgeber = new JList(inhalte);
listHerausgeber.setSelectionMode(
ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
scroller = new JScrollPane(listHerausgeber);
listHerausgeber.setSelectedIndex(2);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
constraints.gridwidth = 1;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(scroller, constraints);

 
   
//"Autor zuordnen" Label erstellen
autorLabel= new JLabel("Autor zuordnen:");
constraints.weightx = 0;
constraints.weighty = 0;
werkAnlegenPanel.add(autorLabel, constraints);



listWerk = new JList(inhalte);
listWerk.setSelectionMode(
ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
scroller = new JScrollPane(listWerk);
listWerk.setSelectedIndex(2);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(scroller, constraints);

  }
    catch (MalformedURLException murle) {
            System.out.println("MalformedURLException");
            System.out.println(murle);
    }
    catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);
    }
    catch (NotBoundException e) {
            System.out.println("NotBoundException");
            System.out.println(e);
    }
    catch (NullPointerException np) {
            System.out.println("NullPointerException");
            System.out.println(np);
    }



//Label Verlag und Comboboc für Verlag erstellen
verlagLabel= new JLabel("Verlag:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(verlagLabel, constraints);


try {
    // Zunächst benötigen wir eine Verbindung mit der Verwaltung.
    Verwaltung verwaltung =
        (Verwaltung)Naming.lookup("rmi:/localhost:1099/DRM");


    Vector<Verlag> ergebnis1 = verwaltung.getAll_Verlag();
    Vector inhalte = new Vector();

    //Hier wird dem Vector 'inhalte' das Objekt verlag mit deM Element Name
    //übergeben und der Combobox 'verlag' hinzugefügt
    for ( Verlag test : ergebnis1 ) {
        if (test != null ) {
            inhalte.add(test.get_name());
        }
    }


//Combobox anlegen
verlag = new JComboBox(inhalte);
Dimension groesseVerlag = new Dimension(310, 25);
verlag.setPreferredSize(groesseVerlag);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
//Combobox mit vordefinierten Daten f�llen
werkAnlegenPanel.add(verlag, constraints);


}
    catch (MalformedURLException murle) {
            System.out.println("MalformedURLException");
            System.out.println(murle);
    }
    catch (RemoteException re) {
            System.out.println("RemoteException");
            System.out.println(re);
    }
    catch (NotBoundException e) {
            System.out.println("NotBoundException");
            System.out.println(e);
    }
    catch (NullPointerException np) {
            System.out.println("NullPointerException");
            System.out.println(np);
    }



//Button anlegen erstellen + ActionListener hinzufügen
anlegen = new JButton("anlegen");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(anlegen, constraints);
anlegen.addActionListener(new anlegenListener());

//Button abbrechen erstellen + ActionListener hinzufügen
abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
werkAnlegenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new werkAnlegen.werkabbrechenListener());

//Framegröße festlegen
frame.getContentPane().add(werkAnlegenPanel);
frame.setSize(750, 600);
frame.setResizable(false);
frame.setVisible(true);
}

//Button Events zuordnen (anlegen)
class anlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

    int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie dieses Werk anlegen?",
    "", JOptionPane.YES_NO_OPTION);
    if (antwort == JOptionPane.YES_OPTION){
          
    //auslesen der einzelnen Strings der Felder. Diese werden dann in der Variable Ergebnisfelder zusammengefasst
    String titel = titelField.getText();
    int jahr = Integer.parseInt(jahrField.getText());
    int seitenzahl = Integer.parseInt(seitenzahlField.getText());
    String werk_typ = (String)werktyp.getSelectedItem();
    String ausgewaehlterVerlag = (String)verlag.getSelectedItem();
    
    //Überprüfen ob alle Pflichtfelder ausgefüllt sind
    if (titel.equals("")) {
        JOptionPane.showMessageDialog(frame,"Bitte alle Pflichtfelder ausfüllen!");            

    //Hier muss noch eingebaut bwerden dass der Prozess nicht weiterläuft     

    }

    try {
                    // Zunächst benötigen wir eine Verbindung mit der Verwaltung.
                    Verwaltung verwaltung =
                            (Verwaltung)Naming.lookup("rmi:/localhost:1099/DRM");

                    /*
                     * Wenn wir hier ankommen, dann besteht eine Verbinung zum Server.
                     * Wäre die Verbindung nicht zustande gekommen, so wäre eine Exception
                     * ausgeworfen worden und das Programm wäre in den entsprechenden
                     * Catch-Block (s.u.) gesprungen.
                     */
                    System.out.println("Verbindung hergestellt...");
                    
                    Vector<Verlag> ergebnis1 = verwaltung.getAll_Verlag();
                    Vector inhalte = new Vector();

                    //Hier wird Jedes Objekt des Vectors auf ggf. Titel, Name, Vorname überprüft und mit dem
                    //zuvor ausgelesenen String der Combobox verglichen. Wird die entsprechende Person
                    //der Combobox gefunden wird von dieser die ID ausgelesen.
                    for ( Verlag test : ergebnis1 ) {
                        if (test != null ) {
                            String nameVerlag  = test.get_name();
                            if (nameVerlag.equals(ausgewaehlterVerlag)) {                               
                                int verlagID = test.get_verlag_id();                               

                                // Hier werden die Daten der entsprechende Person aus der DB gewählt. Selektiert werden diese
                                // anhand der davor festgestellten ID.
                                Verlag ve = verwaltung.findbyid_Verlag(verlagID);
                                Name = ve.get_name();
                                ID = ve.get_verlag_id();
                                Ort = ve.get_ort();
                                
                    //Auslesen der ausgewählten Elemente der JList "Herausgeber"
                    int selectionsHerausgeber[] = listHerausgeber.getSelectedIndices();
                    Object selectedValuesHerausgeber[] = listHerausgeber.getSelectedValues();
                    //Auslesen der ausgewählten Elemente der JList "Werk"
                    int selectionsAutorzuordnen[] = listWerk.getSelectedIndices();
                    Object selectedValuesAutorzuordnen[] = listWerk.getSelectedValues();
                          for (int i = 0, n = selectionsHerausgeber.length; i < n; i++) {
                            if (i == 0) {
                            }
                            Vector<Person> ergebnishe = verwaltung.getAll_Person();
                            Vector he = new Vector();
                            for ( Person testhe : ergebnishe ) {
                                if (test != null ) {
                                String nameHerausgeber  = testhe.get_titel()+ ". " + testhe.get_vorname() + "  " + testhe.get_name();;
                                String nameHerausgeberOhneTitel  = testhe.get_vorname() + "  " + testhe.get_name(); 
    
                                if (nameHerausgeber.equals(selectedValuesHerausgeber[i])|| nameHerausgeberOhneTitel.equals(selectedValuesHerausgeber[i]) ) {                               
                                    int HerausgeberID = testhe.get_person_id();                               

                                    // Hier werden die Daten der entsprechende Person aus der DB gewählt. Selektiert werden diese
                                    // anhand der davor festgestellten ID.
                                    Person herausgeber = verwaltung.findbyid_Person(HerausgeberID);

                            Vector<Person> ergebnisau = verwaltung.getAll_Person();
                            Vector au = new Vector();
                            for ( Person testau : ergebnisau ) {
                                if (test != null ) {
                                String nameAutor  = testau.get_titel()+ ". " + testau.get_vorname() + "  " + testau.get_name();;
                                String nameAutorOhneTitel  = testau.get_vorname() + "  " + testau.get_name(); 
    
                                if (nameAutor.equals(selectedValuesAutorzuordnen[i])|| nameAutor.equals(selectedValuesAutorzuordnen[i]) ) {                               
                                    int AutorID = testau.get_person_id();                               

                                    // Hier werden die Daten der entsprechende Person aus der DB gewählt. Selektiert werden diese
                                    // anhand der davor festgestellten ID.
                                    Person autor = verwaltung.findbyid_Person(AutorID);        
            
                            Werk w = verwaltung.create_einzelwerk_complete(titel, jahr, seitenzahl, werk_typ, autor, herausgeber, ve);
                            
                          }
          
                    // Hier wird entsprechend eine neue Person angelegt.
                    
            
            }}}}}}}}}}
            catch (MalformedURLException murle) {
                    System.out.println("MalformedURLException");
                    System.out.println(murle);
            }
            catch (RemoteException re) {
                    System.out.println("RemoteException");
                    System.out.println(re);
            }
            catch (NotBoundException e) {
                    System.out.println("NotBoundException");
                    System.out.println(e);
            }
            catch (NullPointerException np) {
                    System.out.println("NullPointerException");
                    System.out.println(np);
            }

    
    JOptionPane.showMessageDialog(frame,"Werk angelegt!");
        frame.setVisible(false);
  }
    else if (antwort == JOptionPane.NO_OPTION)
    frame.setVisible(false);
  }
}

//Button Events zuordnen (abbrechen)
class werkabbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
      
      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);       
    }
}}
