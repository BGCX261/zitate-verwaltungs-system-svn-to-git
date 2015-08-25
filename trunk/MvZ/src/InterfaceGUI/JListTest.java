/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InterfaceGUI;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest {

  public static void main(final String args[]) {
    final String labels[] = { "A", "B", "C", "D", "E" };
    JFrame frame = new JFrame("Selecting JList");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JList jlist = new JList(labels);
    JScrollPane scrollPane1 = new JScrollPane(jlist);
    frame.add(scrollPane1);

    ListSelectionListener listSelectionListener = new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
        System.out.println("First index: " + listSelectionEvent.getFirstIndex());
        System.out.println(", Last index: " + listSelectionEvent.getLastIndex());
        boolean adjust = listSelectionEvent.getValueIsAdjusting();
        System.out.println(", Adjusting? " + adjust);
        if (!adjust) {
          JList list = (JList) listSelectionEvent.getSource();
          int selections[] = list.getSelectedIndices();
          Object selectedValues[] = list.getSelectedValues();
          for (int i = 0, n = selections.length; i < n; i++) {
            if (i == 0) {
              System.out.println("  Selections: ");
            }
            System.out.println(selections[i] + "/" + selectedValues[i] + " ");
          }
        }
      }
    };
    jlist.addListSelectionListener(listSelectionListener);

    frame.setSize(350, 200);
    frame.setVisible(true);
  }
}