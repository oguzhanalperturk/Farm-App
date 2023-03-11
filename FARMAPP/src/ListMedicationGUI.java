import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class ListMedicationGUI implements ActionListener {
    private ArrayList<Medication> medications;
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private String[] titles = {"details","duration","start date","dosage","notes"};;
    private String[][] data;
    private JButton exitButton;


    ListMedicationGUI(ArrayList<Medication> medications){

        this.farm = farm;
        this.medications = medications;

        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        title = new JLabel("Medications");
        panel.add(title);


        data = new String[medications.size()][6];

        for(int i=0;i<medications.size();i++){
            data[i][0] = medications.get(i).getDetails();
            data[i][1] = Integer.toString(medications.get(i).getDuration());
            data[i][2] = FarmApp.dateToString(medications.get(i).getStartDate());
            data[i][3] = Double.toString(medications.get(i).getDosage());
            data[i][4] = medications.get(i).getNotes();
        }


        JTable infoTable = new JTable(data, titles);

        JScrollPane scroll = new JScrollPane(infoTable);
        scroll.setPreferredSize( new Dimension(650,700));
        panel.add(scroll);

        int vericalPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
        scroll.setVerticalScrollBarPolicy(vericalPolicy);


        exitButton = new JButton("Exit");
        exitButton.setBounds(10,560,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        frame.setVisible(true);
    }

    /**
     * <p>
     *     This is for listing medications belonging to a specific health treatment
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            new FarmAppGUI(farm);
            frame.dispose();
        }
    }
}
