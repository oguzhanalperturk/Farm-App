import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class ListVeterinaryGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private String[] titles = {"empID","Gender","DateOfBirth","BScDegree","dateOfGraduation","expertiseLevel"};
    private String[][] data;
    private JButton exitButton;
    ListVeterinaryGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        title = new JLabel("Veterinaries");
        panel.add(title);

        int vet_count = 0;
        for(int i=0;i<farm.employees.size();i++){
            if(farm.employees.get(i) instanceof Veterinary){
                vet_count++;
            }
        }

        data = new String[vet_count][6];
        int count = 0;
        for(int i=0;i<farm.employees.size();i++){
            if(farm.employees.get(i) instanceof Veterinary){
                data[count][0] = Integer.toString(farm.employees.get(i).getEmpID());
                data[count][1] = farm.employees.get(i).getGender();
                data[count][2] = FarmApp.dateToString(farm.employees.get(i).getDateOfBirth());
                data[count][3] = Boolean.toString(((Veterinary) farm.employees.get(i)).isBScDegree());
                data[count][4] = FarmApp.dateToString(((Veterinary) farm.employees.get(i)).getDateOfGraduation());
                data[count][5] = Integer.toString(((Veterinary) farm.employees.get(i)).getExpertiseLevel());
                count ++;
            }
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
     *     This is for listing all the veterinary information in the farm
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
