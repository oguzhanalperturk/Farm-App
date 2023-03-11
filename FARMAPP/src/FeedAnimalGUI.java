import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class FeedAnimalGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JButton button;
    private JButton exitButton;

    FeedAnimalGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Feed Animal");
        panel = new JPanel();

        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        tagNoLabel = new JLabel("TagNo");
        tagNoLabel.setBounds(10,10,80,25);
        panel.add(tagNoLabel);

        tagNoText = new JTextField(20);
        tagNoText.setBounds(100,10,165,25);
        panel.add(tagNoText);

        button = new JButton("Feed Animal");
        button.setBounds(150,50,120,25);
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
     *     This is for feeding an animal
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
            String response = "";
            if(tagNoText.getText().equals("")){
                new MessageBoxGUI("Please enter a tag number information!");
            }
            else{
                int tagNo = Integer.parseInt(tagNoText.getText());
                if(!FarmApp.isAnimalInTheList(tagNo,farm.animals)){
                    new MessageBoxGUI("TagNo you entered not exists in the farm.");
                }
                else{
                    response = farm.feedingAnimal(tagNo);
                    new MessageBoxGUI(response);
                }
            }
        }

    }
}
