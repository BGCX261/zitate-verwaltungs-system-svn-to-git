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
public class personLöschen {

JButton löschen;
JButton abbrechen;
JFrame frame;


    public static void main(String[] args) {
        personLöschen gui = new personLöschen();
        gui.personLöschen();
        }


public void personLöschen(){



frame = new JFrame("Person löschen");

JPanel löschenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

löschenPanel.setLayout(gbl);


GridBagConstraints constraints = new GridBagConstraints();


JLabel listelabel= new JLabel("Bitte wählen Sie eine Person:");
listelabel.setFont(new Font("Arial",18,18));

constraints.insets = new Insets( 16,16,16,16 );
constraints.anchor = GridBagConstraints.WEST;
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
löschenPanel.add(listelabel, constraints);

//Vector flugData erstellen und Jcombobox mit diesen Daten füllen
Vector<String> editierenvector = new Vector<String>();


JComboBox boxanlegen = new JComboBox(editierenvector);
Dimension groesseFluggast = new Dimension(300, 25);
boxanlegen.setPreferredSize(groesseFluggast);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
löschenPanel.add(boxanlegen, constraints);


löschen = new JButton("löschen");
constraints.insets = new Insets( 56,16,0,0 );
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
löschenPanel.add(löschen, constraints);
löschen.addActionListener(new löschenListener());


abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
löschenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new abbrechenListener());


frame.getContentPane().add(löschenPanel);

//die Größe des frames wird festgelegt
frame.setSize(400, 300);

frame.setResizable(false);

//der frame wird sichtbar gemacht
frame.setVisible(true);


}

class löschenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie diese Person löschen?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION) {

            JOptionPane.showMessageDialog(frame,"Person gelöscht!");
            frame.setVisible(false);
        }

        else if (antwort == JOptionPane.NO_OPTION)
        frame.setVisible(false);
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
