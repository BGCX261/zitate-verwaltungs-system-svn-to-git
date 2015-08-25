/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Container.*;

/**
 *
 * @author Ben
 */
public class reporter {

JButton literaturlistenAusgeben;
JButton zitiervorkommenAusgeben;
JFrame frame;
JMenuBar menuebar;
JMenu reporter;
JMenuItem reporterBeenden;


public static void main(String[] args) {
        reporter gui = new reporter();
        gui.reporter();
        }

public void reporter(){

frame = new JFrame ("Reporter");


JPanel reporterPanel = new JPanel();

GridBagLayout gbl = new GridBagLayout();

reporterPanel.setLayout(gbl);

reporterPanel.setBackground(Color.ORANGE);

GridBagConstraints constraints = new GridBagConstraints();

menuebar = new JMenuBar();
reporter = new JMenu("EXIT");
reporterBeenden = new JMenuItem("Reporter beenden");
reporterBeenden.addActionListener(new reporterBeendenListener());

menuebar.add(reporter);
reporter.add(reporterBeenden);

literaturlistenAusgeben = new JButton("Literaturlisten ausgeben");
constraints.insets = new Insets( 16,26,26,26 );
constraints.gridwidth = 1;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
literaturlistenAusgeben.setPreferredSize(new Dimension(200,100));
reporterPanel.add(literaturlistenAusgeben, constraints);
literaturlistenAusgeben.addActionListener(new literaturlistenAusgebenListener());


zitiervorkommenAusgeben = new JButton("Zitiervorkommen ausgeben");
constraints.gridwidth = GridBagConstraints.REMAINDER;
constraints.weightx = 0;
constraints.weighty = 0;
constraints.fill = GridBagConstraints.NONE;
zitiervorkommenAusgeben.setPreferredSize(new Dimension(200,100));
reporterPanel.add(zitiervorkommenAusgeben, constraints);
zitiervorkommenAusgeben.addActionListener(new zitiervorkommenAusgebenListener());


frame.getContentPane().add(menuebar,BorderLayout.NORTH);
frame.getContentPane().add(reporterPanel);
frame.setSize(600, 400);
frame.setResizable(false);
frame.setVisible(true);

}

class reporterBeendenListener implements ActionListener{

    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == reporterBeenden){
            frame.setVisible(false);}

 }
}

class literaturlistenAusgebenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){

     Reporter_Literaturlisten r = new Reporter_Literaturlisten();
     r.Reporter_Literaturlisten();
 }


    }
}

class zitiervorkommenAusgebenListener implements ActionListener{

    public void actionPerformed(ActionEvent event){
      
    Reporter_Zitiervorkommen z = new Reporter_Zitiervorkommen();
     z. Reporter_Zitiervorkommen();

    }
}






