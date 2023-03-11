import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class ListCowGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private String[] titles = {"tagNo","Gender","DateOfBirth","purchased","Weight"};;
    private String[][] data;
    private JButton exitButton;


    ListCowGUI(FarmApp farm){

        this.farm = farm;
        frame = new JFrame("List Cow");
        panel = new JPanel();

        frame.setSize(700,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        title = new JLabel("Cows");
        panel.add(title);
        int cow_count = 0;
        for(int i=0;i<farm.animals.size();i++){
            if(farm.animals.get(i) instanceof Cow){
                cow_count++;
            }
        }

        data = new String[cow_count][5];
        int count = 0;
        for(int i=0;i<farm.animals.size();i++){
            if(farm.animals.get(i) instanceof Cow){
                data[count][0] = Integer.toString(farm.animals.get(i).getTagNo());
                data[count][1] = farm.animals.get(i).getGender();
                data[count][2] = FarmApp.dateToString(farm.animals.get(i).getDateOfBirth());
                data[count][3] = Boolean.toString(farm.animals.get(i).isPurchased());
                data[count][4] = Double.toString(((Cow) farm.animals.get(i)).getWeight());
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
     *     This is for listing all the cows in the farm
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
