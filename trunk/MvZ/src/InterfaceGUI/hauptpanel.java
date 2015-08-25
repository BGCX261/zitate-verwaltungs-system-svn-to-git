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
public class hauptpanel {

JMenuItem editormenue;
JMenuItem reportermenue;
JMenuItem systemBeenden;
JFrame frame;



public void hauptpanel() {

frame = new JFrame("Zitatverwaltungsystem");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());

JPanel hauptpanel = new JPanel();
hauptpanel.setBackground(Color.ORANGE);
hauptpanel.setLayout(new BorderLayout());


JLabel überschrift = new JLabel("      Zitatverwaltungsystem");
überschrift.setFont(new Font("Arial",46,46));

hauptpanel.add(überschrift,BorderLayout.CENTER);

JMenuBar menuebar = new JMenuBar();
JMenu gui = new JMenu("GUI");
JMenu exit = new JMenu("Exit");

editormenue = new JMenuItem("Editor");
editormenue.addActionListener(new editormenueListener());
reportermenue = new JMenuItem("Reporter");
reportermenue.addActionListener(new reportermenueListener());
systemBeenden = new JMenuItem("System beenden");
systemBeenden.addActionListener(new beendenmenueListener());

menuebar.add(gui);
menuebar.add(exit);

gui.add(editormenue);
gui.add(reportermenue);
exit.add(systemBeenden);

frame.getContentPane().add(menuebar,BorderLayout.NORTH);
frame.getContentPane().add(hauptpanel,BorderLayout.CENTER);
frame.setSize(600, 400);
frame.setResizable(false);
frame.setVisible(true);

}

public static void main(String[] args) {
        hauptpanel test1 = new hauptpanel();
        test1.hauptpanel();
    }


class editormenueListener implements ActionListener{


    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == editormenue){

editor editor1 = new editor();
editor1.editor();
 frame.setVisible(false);
   }
    }
    }


class reportermenueListener implements ActionListener{


    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == reportermenue){

reporter reporter1 = new reporter();
reporter1.reporter(); }

}

 }

class beendenmenueListener implements ActionListener{

    public void actionPerformed(ActionEvent object) {
          if (object.getSource() == systemBeenden){
             frame.setVisible(false) ;}

}
 }
}



