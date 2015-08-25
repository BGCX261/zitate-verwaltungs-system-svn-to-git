package InterfaceGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 *
 * @author Ben
 */
public class zitatAnlegen {

JButton zitatAnlegen;
JButton abbrechen;
JFrame frame;
JTextField absatzField;
JTextField seitenzahlField;
JTextArea zitat;
String ergebnisFelder;
JScrollPane scroller;
JComboBox werkzuordnen;



public static void main(String[] args) {
        zitatAnlegen gui = new zitatAnlegen();
        gui.zitatAnlegen();
        }


public void zitatAnlegen(){

frame = new JFrame ("Zitat anlegen");


JPanel zitatAnlegenPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

zitatAnlegenPanel.setLayout(gbl);


GridBagConstraints constraints = new GridBagConstraints();

JLabel zitatEingeben= new JLabel("Zitat eingeben:");
constraints.insets = new Insets( 9,9,9,9 );
constraints.anchor = GridBagConstraints.WEST;
constraints.weightx = 0;
zitatAnlegenPanel.add(zitatEingeben, constraints);

zitat = new JTextArea (8,25);
scroller = new JScrollPane(zitat);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitat.setLineWrap(true);
scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
zitatAnlegenPanel.add(scroller, constraints);


JLabel absatzLabel= new JLabel("Absatz:");
constraints.insets = new Insets(56,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(absatzLabel, constraints);

absatzField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(absatzField, constraints);

JLabel seitenzahlLabel= new JLabel("Seitenzahl:");
constraints.insets = new Insets(9,9,9,9);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(seitenzahlLabel, constraints);


seitenzahlField = new JTextField(20);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(seitenzahlField, constraints);


JLabel werkzuordnenLabel= new JLabel("Werk zuordnen:");
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(werkzuordnenLabel, constraints);

werkzuordnen = new JComboBox();
Dimension werkzuordnenDimension = new Dimension(200, 20);
werkzuordnen.setPreferredSize(werkzuordnenDimension);
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 1;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(werkzuordnen, constraints);


zitatAnlegen = new JButton("anlegen");
constraints.insets = new Insets(89,9,0,0);
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(zitatAnlegen, constraints);
zitatAnlegen.addActionListener(new zitatanlegenListener());

abbrechen = new JButton("abbrechen");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitatAnlegenPanel.add(abbrechen, constraints);
abbrechen.addActionListener(new zitatabbrechenListener());

frame.getContentPane().add(zitatAnlegenPanel);
frame.setSize(470, 500);
frame.setResizable(false);
frame.setVisible(true);

}

class zitatanlegenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie dieses Zitat anlegen?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION){

        JOptionPane.showMessageDialog(frame,"Zitat angelegt!");
            frame.setVisible(false);
      }
        else if (antwort == JOptionPane.NO_OPTION)
        frame.setVisible(false);
      }
}

class zitatabbrechenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

      int antwort = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Vorgang wirklich beenden?",
      "", JOptionPane.YES_NO_OPTION);
      if (antwort == JOptionPane.YES_OPTION)
      frame.setVisible(false);
    }
}
}
