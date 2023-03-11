import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class AddAnimalGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JRadioButton cowButton;
    private JRadioButton sheepButton;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JLabel genderLabel;
    private JTextField genderText;
    private JLabel dobLabel;
    private JTextField dobText;
    private JLabel weightLabel;
    private JTextField weightText;
    private JButton button;
    private JButton exitButton;
    private JComboBox purchased_info;

    AddAnimalGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Add Animal");
        panel = new JPanel();

        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        ButtonGroup cow_or_sheep = new ButtonGroup();
        cowButton = new JRadioButton("Cow",true);
        cowButton.setBounds(10,10,50,25);

        sheepButton = new JRadioButton("Sheep");
        sheepButton.setBounds(80,10,180,25);

        cow_or_sheep.add(cowButton);
        cow_or_sheep.add(sheepButton);

        panel.add(cowButton);
        panel.add(sheepButton);

        tagNoLabel = new JLabel("Tag Number");
        tagNoLabel.setBounds(10,80,80,25);
        panel.add(tagNoLabel);

        tagNoText = new JTextField(20);
        tagNoText.setBounds(100,80,165,25);
        panel.add(tagNoText);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(10,120,80,25);
        panel.add(genderLabel);

        genderText = new JTextField(20);
        genderText.setBounds(100,120,165,25);
        panel.add(genderText);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(10,160,80,25);
        panel.add(dobLabel);

        dobText = new JTextField(20);
        dobText.setBounds(100,160,165,25);
        panel.add(dobText);

        weightLabel = new JLabel("Weight");
        weightLabel.setBounds(10,200,80,25);
        panel.add(weightLabel);

        weightText = new JTextField(20);
        weightText.setBounds(100,200,165,25);
        panel.add(weightText);



        String s1[] = { "purchased", "farm raised" };

        purchased_info = new JComboBox(s1);
        purchased_info.setBounds(10,40,100,25);
        purchased_info.addActionListener(this);

        panel.add(purchased_info);




        cowButton.addActionListener(this);
        sheepButton.addActionListener(this);

        button = new JButton("Add Animal");
        button.setBounds(150,240,100,25);
        button.addActionListener(this);
        panel.add(button);

        exitButton = new JButton("Exit");
        exitButton.setBounds(10,240,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        frame.setVisible(true);
    }


    /**
     * <p>
     *     Adds animal to the farm when button is clicked
     *     also exits when exit buton is clicked
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            new FarmAppGUI(farm);
            frame.dispose();
        }

        if(this.cowButton.isSelected()){

            weightLabel.setVisible(true);
            weightText.setVisible(true);
        }
        else if(this.sheepButton.isSelected()){

            weightLabel.setVisible(false);
            weightText.setVisible(false);
        }

        if(e.getSource() == this.button){

            int error_flag = 0;
            String response = "";

            if(this.cowButton.isSelected()){
                if(tagNoText.getText().equals("") || genderText.getText().equals("") || dobText.getText().equals("") || weightText.getText().equals("")){
                    new MessageBoxGUI("Please fill all the Cow information!");
                    error_flag = 1;
                }
            }
            else if(this.sheepButton.isSelected()){
                if(tagNoText.getText().equals("") || genderText.getText().equals("") || dobText.getText().equals("")){
                    new MessageBoxGUI("Please fill all the Sheep information!");
                    error_flag = 1;
                }
            }

            if(error_flag == 0){
                boolean purchased;
                int tagNo, cow_or_sheep;
                double weight = 0;
                String gender, date_str;

                if(purchased_info.getSelectedItem().equals("purchased")){
                    purchased = true;
                }
                else{
                    purchased = false;
                }
                tagNo = Integer.parseInt(tagNoText.getText());
                gender = genderText.getText();
                if(gender.toLowerCase().contains("f")){
                    gender = "f";
                }
                else{
                    gender = "m";
                }
                date_str = dobText.getText();

                if(cowButton.isSelected()){
                    weight = Double.parseDouble(weightText.getText());
                    cow_or_sheep = 0;
                }
                else{
                    cow_or_sheep = 1;
                }
                response = farm.addAnimal(tagNo, gender, purchased , date_str, weight, cow_or_sheep);
                frame.dispose();
                new FarmAppGUI(farm);
                new MessageBoxGUI(response);
            }
        }
    }
}
