/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import java.util.Vector;

/**
 *
 * @author Ben
 */
public class objektAnlegen {

    JLabel listelabel;
    JButton anlegen;
    JButton abbrechen;
    JFrame frame;
    JComboBox boxanlegen;


    public static void main(String[] args) {
        objektAnlegen gui = new objektAnlegen();
        gui.objektAnlegen();
        }


public void objektAnlegen(){



        frame = new JFrame("anlegen");

        JPanel anlegenPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();

        anlegenPanel.setLayout(gbl);


        GridBagConstraints constraints = new GridBagConstraints();

       
        listelabel = new JLabel("<html>Was möchten sie neu anlegen?<br/>Bitte wählen:");
        listelabel.setFont(new Font("Arial",18,18));

        constraints.insets = new Insets( 16,16,16,16 );
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        anlegenPanel.add(listelabel, constraints);

        //Vector erstellen und Jcombobox mit diesen Daten füllen
        Vector<String> anlegenvector = new Vector<String>();
        anlegenvector.add("Zitat anlegen");
        anlegenvector.add("Person anlegen");
        anlegenvector.add("Sammelwerk anlegen");
        anlegenvector.add("Werk anlegen");
        anlegenvector.add("Verlag anlegen");
        
        boxanlegen = new JComboBox(anlegenvector);
        Dimension groesseObjekt = new Dimension(300, 25);
        boxanlegen.setPreferredSize(groesseObjekt);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.NONE;
        anlegenPanel.add(boxanlegen, constraints);

        anlegen = new JButton("anlegen");
        constraints.insets = new Insets( 56,16,0,0 );
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        anlegenPanel.add(anlegen, constraints);
        anlegen.addActionListener(new anlegenListener());

        abbrechen = new JButton("abbrechen");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        anlegenPanel.add(abbrechen, constraints);
        abbrechen.addActionListener(new abbrechenListener());


        frame.getContentPane().add(anlegenPanel);

        //die Größe des frames wird festgelegt
        frame.setSize(400, 300);

        frame.setResizable(false);
        //der frame wird sichtbar gemacht
        frame.setVisible(true);
        }

    class anlegenListener implements ActionListener{

          public void actionPerformed(ActionEvent event){

        String auswahl = (String)boxanlegen.getSelectedItem();

        if (auswahl.equals("Zitat anlegen")) {
          zitatAnlegen z = new zitatAnlegen();
          z.zitatAnlegen();
          frame.setVisible(false);}

         if (auswahl.equals("Person anlegen")) {
          personAnlegen p = new personAnlegen();
          p.personAnlegen();
          frame.setVisible(false);}

         if (auswahl.equals("Sammelwerk anlegen")) {
          sammelwerkAnlegen s = new sammelwerkAnlegen();
          s.sammelwerkAnlegen();
          frame.setVisible(false);}

         if (auswahl.equals("Werk anlegen")) {
          werkAnlegen w = new werkAnlegen();
          w.werkAnlegen();
          frame.setVisible(false);}

         if (auswahl.equals("Verlag anlegen")) {
          verlagAnlegen v = new verlagAnlegen();
          v.verlagAnlegen();
          frame.setVisible(false);}

    }
    }

class abbrechenListener implements ActionListener{

     public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);
    }
}
}


