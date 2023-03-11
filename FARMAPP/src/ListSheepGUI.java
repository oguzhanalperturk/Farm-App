import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class ListSheepGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JButton exitButton;
    private String[] titles = {"tagNo","Gender","DateOfBirth","purchased"};
    private String data[][];


    ListSheepGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        title = new JLabel("Sheeps");
        panel.add(title);


        int sheep_count = 0;
        for(int i=0;i<farm.animals.size();i++){
            if(farm.animals.get(i) instanceof Sheep){
                sheep_count++;
            }
        }

        data = new String[sheep_count][5];
        int count = 0;
        for(int i=0;i<farm.animals.size();i++){
            if(farm.animals.get(i) instanceof Sheep){
                data[count][0] = Integer.toString(farm.animals.get(i).getTagNo());
                data[count][1] = farm.animals.get(i).getGender();
                data[count][2] = FarmApp.dateToString(farm.animals.get(i).getDateOfBirth());
                data[count][3] = Boolean.toString(farm.animals.get(i).isPurchased());
                count ++;
            }
        }


        JTable infoTable = new JTable(this.data, this.titles);


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
     *     This is listing all the sheep information into the farm
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            frame.dispose();
            new FarmAppGUI(this.farm);
        }
    }
}
