import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class ListFarmWorkerGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private String[] header = {"empID","Gender","DateOfBirth","previousFarmName","workexperience"};
    private String[][] data;
    private JButton exitButton;




    ListFarmWorkerGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        JLabel title = new JLabel("Farmworkers");
        panel.add(title);


        int fworker_count = 0;
        for(int i=0;i<farm.employees.size();i++){
            if(farm.employees.get(i) instanceof FarmWorker){
                fworker_count++;
            }
        }

        data = new String[fworker_count][5];
        int count = 0;
        for(int i=0;i<farm.employees.size();i++){
            if(farm.employees.get(i) instanceof FarmWorker){
                data[count][0] = Integer.toString(farm.employees.get(i).getEmpID());
                data[count][1] = farm.employees.get(i).getGender();
                data[count][2] = FarmApp.dateToString(farm.employees.get(i).getDateOfBirth());
                data[count][3] = ((FarmWorker)farm.employees.get(i)).getPreviousFarmName();
                data[count][4] = Integer.toString(((FarmWorker) farm.employees.get(i)).getWorkexperience());
                count ++;
            }
        }


        JTable infoTable = new JTable(data, header);

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
     *     This is for listing all the farmworkers in the farm
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            new FarmAppGUI(this.farm);
            frame.dispose();
        }
    }
}
