import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class AddMilkingMeasurementGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JLabel amountLabel;
    private JTextField amountText;
    private JButton button;
    private JButton exitButton;

    AddMilkingMeasurementGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Add Milking Measurement");
        panel = new JPanel();

        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        tagNoLabel = new JLabel("Tag Number");
        tagNoLabel.setBounds(10,10,80,25);
        panel.add(tagNoLabel);

        tagNoText = new JTextField(20);
        tagNoText.setBounds(100,10,165,25);
        panel.add(tagNoText);

        amountLabel = new JLabel("Amount");
        amountLabel.setBounds(10,50,80,25);
        panel.add(amountLabel);

        amountText = new JTextField(20);
        amountText.setBounds(100,50,165,25);
        panel.add(amountText);

        button = new JButton("Add Milking Measurement");
        button.setBounds(50,90,190,25);
        button.addActionListener(this);
        panel.add(button);

        exitButton = new JButton("Exit");
        exitButton.setBounds(10,130,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        frame.setVisible(true);
    }

    /**
     * <p>
     *     This is for adding milking measurement to an animal
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

            if(tagNoText.getText().equals("") || amountText.getText().equals("")){
                new MessageBoxGUI("Please enter all the information!!");
            }
            else{
                String response = this.farm.addMilkingMeasurement(Integer.parseInt(tagNoText.getText()), Double.parseDouble(amountText.getText()));
                frame.dispose();
                new FarmAppGUI(farm);
                new MessageBoxGUI(response);
            }
        }
    }
}
