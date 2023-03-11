import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class MessageBoxGUI implements ActionListener {
    private String message;
    private JFrame frame;
    private JPanel panel;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JButton button;

    MessageBoxGUI(String message){
        this.message = message;
        frame = new JFrame("Message");
        panel = new JPanel();

        frame.setSize(800,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        tagNoLabel = new JLabel(message, SwingConstants.CENTER);
        tagNoLabel.setBounds(0,20,780,25);
        panel.add(tagNoLabel);

        button = new JButton("Okay");
        button.setBounds(350,60,80,25);
        button.addActionListener(this);
        panel.add(button);

        frame.setVisible(true);
    }

    /**
     * <p>
     *     This is for showing a message to user
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            frame.dispose();
        }
    }
}
