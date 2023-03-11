import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class GetAnimalDetailsGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JButton button;
    private JTable cowTable;
    private  JTable sheepTable;
    private JScrollPane scrollCow;
    private JScrollPane scrollSheep;
    private String[][] cowData = new String[1][6];
    private String[][] sheepData = new String[1][5];
    private String[] cowHeader = {"tagNo","Gender","DateOfBirth","purchased","weight"};
    private String[] sheepHeader = {"tagNo","Gender","DateOfBirth","purchased"};

    private JButton exitButton;

    GetAnimalDetailsGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Get Animal Details");
        panel = new JPanel();

        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


        tagNoLabel = new JLabel("Tag Number");
        panel.add(tagNoLabel);

        tagNoText = new JTextField(20);
        panel.add(tagNoText);

        button = new JButton("Show Animal");
        button.addActionListener(this);
        panel.add(button);

        exitButton = new JButton("Exit");
        exitButton.setBounds(10,560,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        cowTable = new JTable(this.cowData, this.cowHeader);

        sheepTable = new JTable(this.sheepData, this.sheepHeader);

        scrollCow = new JScrollPane(cowTable);

        scrollSheep = new JScrollPane(sheepTable);



        panel.add(scrollCow);
        panel.add(scrollSheep);

        scrollSheep.setVisible(false);
        scrollCow.setVisible(false);

        frame.setVisible(true);
    }


    /**
     * <p>
     *     This is for getting details of the specific animal from the farm
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            new FarmAppGUI(farm);
            frame.dispose();
        }
        else if(e.getSource() == button){
            int error_flag = 0,i,tagNo = 0;

            if(tagNoText.getText().equals("")){
                new MessageBoxGUI("Please enter a tag number!");
                error_flag = 1;
            }
            else{
                tagNo = Integer.parseInt(tagNoText.getText());

                if (!FarmApp.isAnimalInTheList(tagNo, this.farm.animals)) {
                    new MessageBoxGUI("There is no animal has tagNo: " + tagNo + " in this farm.");
                    error_flag = 1;
                }
            }

            if(error_flag == 0){
                for (i = 0; i < this.farm.animals.size(); i++) {
                    if (this.farm.animals.get(i).getTagNo() == tagNo) {
                        if (this.farm.animals.get(i) instanceof Cow) {

                            cowData[0][0] =  Integer.toString(this.farm.animals.get(i).getTagNo());
                            cowData[0][1] =  this.farm.animals.get(i).getGender();
                            String date_in_str = FarmApp.dateToString(this.farm.animals.get(i).getDateOfBirth());
                            cowData[0][2] = date_in_str;
                            if (this.farm.animals.get(i).isPurchased()) {
                                cowData[0][3] = "purchased";
                            } else {
                                cowData[0][3] = "farm raising";
                            }
                            cowData[0][4] = Double.toString(((Cow) this.farm.animals.get(i)).getWeight());

                            scrollCow.setVisible(false);
                            scrollCow.setVisible(true);
                            scrollSheep.setVisible(false);
                            frame.setVisible(true);

                        } else {

                            sheepData[0][0] =  Integer.toString(this.farm.animals.get(i).getTagNo());
                            sheepData[0][1] =  this.farm.animals.get(i).getGender();
                            String date_in_str = FarmApp.dateToString(this.farm.animals.get(i).getDateOfBirth());
                            sheepData[0][2] = date_in_str;
                            if (this.farm.animals.get(i).isPurchased()) {
                                sheepData[0][3] = "purchased";
                            } else {
                                sheepData[0][3] = "farm raising";
                            }

                            scrollCow.setVisible(false);
                            scrollSheep.setVisible(false);
                            scrollSheep.setVisible(true);
                            frame.setVisible(true);
                        }
                    }
                }
            }
        }
    }
}
