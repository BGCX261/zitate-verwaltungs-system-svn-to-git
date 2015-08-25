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
public class objektEditieren {

    JLabel listelabel;
    JButton editieren;
    JButton abbrechen;
    JFrame frame;
    JComboBox boxanlegen;

    public static void main(String[] args) {
        objektEditieren gui = new objektEditieren();
        gui.objektEditieren();
        }


public void objektEditieren(){



        frame = new JFrame("editieren");

        JPanel editierenPanel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();

        editierenPanel.setLayout(gbl);


        GridBagConstraints constraints = new GridBagConstraints();


        listelabel= new JLabel("<html>Was möchten sie aktualisieren?<br/>Bitte wählen:");
        listelabel.setFont(new Font("Arial",18,18));

        constraints.insets = new Insets( 16,16,16,16 );
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        editierenPanel.add(listelabel, constraints);

        //Vector flugData erstellen und Jcombobox mit diesen Daten füllen
        Vector<String> editierenvector = new Vector<String>();
        editierenvector.add("Zitat editieren");
        editierenvector.add("Person editieren");
        editierenvector.add("Sammelwerk editieren");
        editierenvector.add("Werk editieren");
        editierenvector.add("Verlag editieren");


        boxanlegen = new JComboBox(editierenvector);
        Dimension groesseObjekt = new Dimension(300, 25);
        boxanlegen.setPreferredSize(groesseObjekt);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.NONE;
        editierenPanel.add(boxanlegen, constraints);


        editieren = new JButton("editieren");
        constraints.insets = new Insets( 56,16,0,0 );
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        editierenPanel.add(editieren, constraints);
        editieren.addActionListener(new objektEditierenListener());


        abbrechen = new JButton("abbrechen");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.NONE;
        editierenPanel.add(abbrechen, constraints);
        abbrechen.addActionListener(new abbrechenListener());


        frame.getContentPane().add(editierenPanel);

        //die Größe des frames wird festgelegt
        frame.setSize(400, 300);

        frame.setResizable(false);
        //der frame wird sichtbar gemacht
        frame.setVisible(true);


        }

class objektEditierenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
        String auswahl = (String)boxanlegen.getSelectedItem();

        if (auswahl.equals("Zitat editieren")) {
          zitatEditierenAuswahl z = new zitatEditierenAuswahl();
         // z.zitatAktualisieren();
          frame.setVisible(false);}

         if (auswahl.equals("Person editieren")) {
          personEditierenAuswahl p = new personEditierenAuswahl();
          p.personAktualisieren();
          frame.setVisible(false);}

         if (auswahl.equals("Sammelwerk editieren")) {
          sammelwerkEditierenAuswahl s = new sammelwerkEditierenAuswahl();
          s.sammelwerkAktualisieren();
          frame.setVisible(false);}

         if (auswahl.equals("Werk editieren")) {
          werkAktualisieren w = new werkAktualisieren();
          w.werkAktualisieren();
          frame.setVisible(false);}

         if (auswahl.equals("Verlag editieren")) {
          verlagEditierenAuswahl v = new verlagEditierenAuswahl();
          v.verlagAktualisieren();
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



