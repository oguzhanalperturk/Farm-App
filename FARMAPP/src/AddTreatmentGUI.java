import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class AddTreatmentGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JRadioButton cleaningTreatmentButton;
    private JRadioButton healthTreatmentButton;
    private JRadioButton emergencyButton;
    private JRadioButton notEmergencyButton;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JLabel empIDLabel;
    private JTextField empIDText;
    private JLabel dobLabel;
    private JTextField dobText;
    private JLabel materialsLabel;
    private JTextField materialsText;
    private JLabel medicationCountLabel;
    private JTextField medicationCountText;
    private JButton addMedicineButton;
    private JButton button;
    private JButton exitButton;

    AddTreatmentGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Add Treatment");
        panel = new JPanel();

        frame.setSize(330,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        ButtonGroup treatmentbuttongroup = new ButtonGroup();
        cleaningTreatmentButton = new JRadioButton("Cleaning Treatment",true);
        cleaningTreatmentButton.setBounds(10,10,150,25);

        healthTreatmentButton = new JRadioButton("Health Treatment");
        healthTreatmentButton.setBounds(160,10,180,25);

        treatmentbuttongroup.add(cleaningTreatmentButton);
        treatmentbuttongroup.add(healthTreatmentButton);

        panel.add(cleaningTreatmentButton);
        panel.add(healthTreatmentButton);


        tagNoLabel = new JLabel("Tag Number");
        tagNoLabel.setBounds(10,40,80,25);
        panel.add(tagNoLabel);

        tagNoText = new JTextField(20);
        tagNoText.setBounds(100,40,165,25);
        panel.add(tagNoText);

        empIDLabel = new JLabel("Emp ID");
        empIDLabel.setBounds(10,80,80,25);
        panel.add(empIDLabel);

        empIDText = new JTextField(20);
        empIDText.setBounds(100,80,165,25);
        panel.add(empIDText);

        dobLabel = new JLabel("Date of Treatment");
        dobLabel.setBounds(10,120,110,25);
        panel.add(dobLabel);

        dobText = new JTextField(20);
        dobText.setBounds(120,120,165,25);
        panel.add(dobText);

        materialsLabel = new JLabel("Used Materials");
        materialsLabel.setBounds(10,160,100,25);
        panel.add(materialsLabel);

        materialsText = new JTextField(20);
        materialsText.setBounds(110,160,165,25);
        panel.add(materialsText);


        medicationCountLabel = new JLabel("Medication Count");
        medicationCountLabel.setBounds(10,160,165,25);
        panel.add(medicationCountLabel);

        medicationCountText = new JTextField(20);
        medicationCountText.setBounds(120,160,165,25);
        panel.add(medicationCountText);

        medicationCountText.setVisible(false);
        medicationCountLabel.setVisible(false);

        ButtonGroup emergency = new ButtonGroup();
        emergencyButton = new JRadioButton("emergency",true);
        emergencyButton.setBounds(10,200,100,25);

        notEmergencyButton = new JRadioButton("not emergency");
        notEmergencyButton.setBounds(110,200,180,25);

        emergency.add(emergencyButton);
        emergency.add(notEmergencyButton);

        panel.add(emergencyButton);
        panel.add(notEmergencyButton);

        cleaningTreatmentButton.addActionListener(this);
        healthTreatmentButton.addActionListener(this);

        addMedicineButton = new JButton("Add Medicine");
        addMedicineButton.setBounds(150,240,120,25);
        addMedicineButton.addActionListener(this);
        panel.add(addMedicineButton);

        emergencyButton.setVisible(false);
        notEmergencyButton.setVisible(false);
        addMedicineButton.setVisible(false);


        button = new JButton("Add Treatment");
        button.setBounds(150,280,120,25);
        button.addActionListener(this);
        panel.add(button);

        exitButton = new JButton("Exit");
        exitButton.setBounds(10,280,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);


        frame.setVisible(true);
    }

    /**
     * <p>
     *     This is for adding treatment to an animal
     * </p>
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        ArrayList<Medication> medications = new ArrayList<Medication>();

        if(e.getSource() == exitButton){
            new FarmAppGUI(farm);
            frame.dispose();
        }

        if(e.getSource() == healthTreatmentButton){
            emergencyButton.setVisible(true);
            notEmergencyButton.setVisible(true);
            materialsLabel.setVisible(false);
            materialsText.setVisible(false);
            addMedicineButton.setVisible(true);
            medicationCountLabel.setVisible(true);
            medicationCountText.setVisible(true);
        }
        else if(e.getSource() == cleaningTreatmentButton){
            emergencyButton.setVisible(false);
            notEmergencyButton.setVisible(false);
            materialsLabel.setVisible(true);
            materialsText.setVisible(true);
            addMedicineButton.setVisible(false);
            medicationCountLabel.setVisible(false);
            medicationCountText.setVisible(false);
        }
        else if(e.getSource() == addMedicineButton){
            int medication_count = Integer.parseInt(medicationCountText.getText());
            new AddMedicationGUI(medication_count, medications);
        }
        else if(e.getSource() == button){

            int error = 0;

            if(healthTreatmentButton.isSelected()){
                if(dobText.getText().equals("") || empIDText.getText().equals("") || tagNoText.getText().equals("")){
                    new MessageBoxGUI("Please fill all the Treatment information!");
                    error = 1;
                }
            }
            else if(cleaningTreatmentButton.isSelected()){
                if(dobText.getText().equals("") || empIDText.getText().equals("") || tagNoText.getText().equals("") || materialsText.getText().equals("")){
                    new MessageBoxGUI("Please fill all the Treatment information!");
                    error = 1;
                }
            }
            if(error == 0){
                String response = "";
                int clean_or_health;
                String str_date_of_treatment = dobText.getText();
                String materials_used = materialsText.getText();
                int empID = Integer.parseInt(empIDText.getText());
                int tagNo = Integer.parseInt(tagNoText.getText());
                boolean emergency = false;
                if(healthTreatmentButton.isSelected()){
                    clean_or_health = 1;
                }
                else{
                    clean_or_health = 0;
                }
                if(emergencyButton.isSelected()){
                    emergency = true;
                }
                else{
                    emergency = false;
                }
                response = farm.addTreatment(empID, tagNo, clean_or_health, materials_used, str_date_of_treatment, emergency, medications);
                frame.dispose();
                new FarmAppGUI(farm);
                new MessageBoxGUI(response);

                for(int i=0;i<medications.size();i++){
                    System.out.println(medications.get(i).getDetails());
                }
            }
        }

    }

}
