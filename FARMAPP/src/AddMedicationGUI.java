import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class AddMedicationGUI implements ActionListener {
    private int medication_count;
    private ArrayList<Medication> medications;
    private JFrame frame;
    private JPanel panel;
    private JRadioButton emergencyButton;
    private JRadioButton notEmergencyButton;
    private JLabel durationLabel;
    private JTextField durationText;
    private JLabel startDateLabel;
    private JTextField startDateText;
    private JLabel dosageLabel;
    private JTextField dosageText;
    private JLabel detailsLabel;
    private JTextField detailsText;
    private JLabel notesLabel;
    private JTextArea notesText;
    private JButton addMedicineButton;

    AddMedicationGUI(int medication_count, ArrayList<Medication> medications){
        this.medication_count = medication_count;
        this.medications = medications;

        frame = new JFrame("Add Medication");
        panel = new JPanel();

        frame.setSize(330,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        durationLabel = new JLabel("Duration");
        durationLabel.setBounds(10,10,80,25);
        panel.add(durationLabel);

        durationText = new JTextField(20);
        durationText.setBounds(100,10,165,25);
        panel.add(durationText);

        startDateLabel = new JLabel("Start Date");
        startDateLabel.setBounds(10,50,80,25);
        panel.add(startDateLabel);

        startDateText = new JTextField(20);
        startDateText.setBounds(100,50,165,25);
        panel.add(startDateText);

        dosageLabel = new JLabel("Dosage");
        dosageLabel.setBounds(10,90,100,25);
        panel.add(dosageLabel);

        dosageText = new JTextField(20);
        dosageText.setBounds(100,90,165,25);
        panel.add(dosageText);

        detailsLabel = new JLabel("Details");
        detailsLabel.setBounds(10,130,100,25);
        panel.add(detailsLabel);

        detailsText = new JTextField(20);
        detailsText.setBounds(100,130,165,25);
        panel.add(detailsText);

        notesLabel = new JLabel("Notes");
        notesLabel.setBounds(10,180,100,25);
        panel.add(notesLabel);

        notesText = new JTextArea();
        notesText.setBounds(100,180,165,75);
        panel.add(notesText);



        addMedicineButton = new JButton("Add Medication");
        addMedicineButton.setBounds(150,280,140,25);
        addMedicineButton.addActionListener(this);
        panel.add(addMedicineButton);



        frame.setVisible(true);
    }


    /**
     * This is for adding medication for the health treatment
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addMedicineButton){

            if(detailsText.getText().equals("") || durationText.getText().equals("") || startDateText.getText().equals("") || dosageText.getText().equals("") || notesText.getText().equals("")){
                new MessageBoxGUI("Please fill all the Medication information!");
            }
            else{
                if(medication_count > 0){
                    String details = detailsText.getText();
                    int duration = Integer.parseInt(durationText.getText());
                    Date startDate = FarmApp.stringToDate(startDateText.getText());
                    double dosage = Double.parseDouble(dosageText.getText());
                    String notes = notesText.getText();

                    Medication new_med = new Medication(details, duration, startDate, dosage, notes);
                    medications.add(new_med);
                    if(medication_count != 1){
                        new MessageBoxGUI("Medication is added successfully!  " + --medication_count + " remaining.");
                    }
                }
                if(medication_count == 0){
                    for(int i=0;i<medications.size();i++){
                        System.out.println("Med: " + medications.get(i).getDetails());
                    }
                    new MessageBoxGUI("All medications are added successfulLy!");
                    frame.dispose();
                }

            }
        }
    }
}
