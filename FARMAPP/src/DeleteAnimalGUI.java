import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class DeleteAnimalGUI implements ActionListener{
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JButton button;
    private JButton exitButton;

    DeleteAnimalGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Delete Animal");
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

        button = new JButton("Delete Animal");
        button.setBounds(120,50,120,25);
        button.addActionListener(this);
        panel.add(button);

        exitButton = new JButton("Exit");
        exitButton.setBounds(10,50,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        frame.setVisible(true);
    }


    /**
     * <p>
     *     This is for deleting an animal from the farm
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

            if(tagNoText.getText().equals("")){
                new MessageBoxGUI("Please enter a tag number!");
            }
            else{
                String response = this.farm.deleteAnimal(Integer.parseInt(tagNoText.getText()));
                frame.dispose();
                new FarmAppGUI(farm);
                new MessageBoxGUI(response);
            }
        }
    }
}
