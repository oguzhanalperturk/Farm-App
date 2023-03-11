import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class AddEmployeeGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JRadioButton vetButton;
    private JRadioButton fworkerButton;
    private JRadioButton haveBScDegree;
    private JRadioButton dontHaveBScDegree;
    private JLabel empIdLabel;
    private JTextField empIdText;
    private JLabel genderLabel;
    private JTextField genderText;
    private JLabel dobLabel;
    private JTextField dobText;
    private JLabel dateOfGraduationLabel;
    private JTextField dateOfGraduationText;
    private JLabel expertiseLevelLabel;
    private JTextField expertiseLevelText;
    private JButton button;
    private JLabel previousFarmNameLabel;
    private JTextField previousFarmNameText;
    private JLabel workExperienceLabel;
    private JTextField workExperienceText;
    private JButton exitButton;

    AddEmployeeGUI(FarmApp farm) {
        this.farm = farm;
        frame = new JFrame("Add Employee");
        panel = new JPanel();

        frame.setSize(350,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        ButtonGroup cow_or_sheep = new ButtonGroup();
        vetButton = new JRadioButton("Veterinary",true);
        vetButton.setBounds(10,10,90,25);

        fworkerButton = new JRadioButton("FarmWorker");
        fworkerButton.setBounds(100,10,180,25);

        cow_or_sheep.add(vetButton);
        cow_or_sheep.add(fworkerButton);

        panel.add(vetButton);
        panel.add(fworkerButton);


        empIdLabel = new JLabel("EmpID");
        empIdLabel.setBounds(10,40,120,25);
        panel.add(empIdLabel);

        empIdText = new JTextField(20);
        empIdText.setBounds(100,40,165,25);
        panel.add(empIdText);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(10,80,80,25);
        panel.add(genderLabel);

        genderText = new JTextField(20);
        genderText.setBounds(100,80,165,25);
        panel.add(genderText);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(10,120,80,25);
        panel.add(dobLabel);

        dobText = new JTextField(20);
        dobText.setBounds(100,120,165,25);
        panel.add(dobText);

        ButtonGroup BScDegreeGroup = new ButtonGroup();
        haveBScDegree = new JRadioButton("Have BScDegree",true);
        haveBScDegree.setBounds(10,160,130,25);

        dontHaveBScDegree = new JRadioButton("Don't have BSc Degree");
        dontHaveBScDegree.setBounds(140,160,180,25);

        BScDegreeGroup.add(haveBScDegree);
        BScDegreeGroup.add(dontHaveBScDegree);

        panel.add(haveBScDegree);
        panel.add(dontHaveBScDegree);


        dateOfGraduationLabel = new JLabel("Date of Graduation");
        dateOfGraduationLabel.setBounds(10,200,120,25);
        panel.add(dateOfGraduationLabel);

        dateOfGraduationText = new JTextField(20);
        dateOfGraduationText.setBounds(130,200,165,25);
        panel.add(dateOfGraduationText);

        expertiseLevelLabel = new JLabel("Expertise Level");
        expertiseLevelLabel.setBounds(10,240,120,25);
        panel.add(expertiseLevelLabel);

        expertiseLevelText = new JTextField(20);
        expertiseLevelText.setBounds(130,240,165,25);
        panel.add(expertiseLevelText);

        previousFarmNameLabel = new JLabel("Previous farm name");
        previousFarmNameLabel.setBounds(10,160,120,25);
        panel.add(previousFarmNameLabel);

        previousFarmNameText = new JTextField(20);
        previousFarmNameText.setBounds(130,160,165,25);
        panel.add(previousFarmNameText);

        workExperienceLabel = new JLabel("Work Experience");
        workExperienceLabel.setBounds(10,200,120,25);
        panel.add(workExperienceLabel);

        workExperienceText = new JTextField(20);
        workExperienceText.setBounds(130,200,165,25);
        panel.add(workExperienceText);


        previousFarmNameLabel.setVisible(false);
        previousFarmNameText.setVisible(false);
        workExperienceText.setVisible(false);
        workExperienceLabel.setVisible(false);

        vetButton.addActionListener(this);
        fworkerButton.addActionListener(this);

        button = new JButton("Add Employee");
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
     *     Adds an employee when add employee button is clicked
     *     exits when exit button is clicked
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton){
            new FarmAppGUI(farm);
            frame.dispose();
        }
        if(this.vetButton.isSelected()){
            haveBScDegree.setVisible(true);
            dontHaveBScDegree.setVisible(true);
            dateOfGraduationLabel.setVisible(true);
            dateOfGraduationText.setVisible(true);
            expertiseLevelLabel.setVisible(true);
            expertiseLevelText.setVisible(true);
            previousFarmNameLabel.setVisible(false);
            previousFarmNameText.setVisible(false);
            workExperienceText.setVisible(false);
            workExperienceLabel.setVisible(false);
        }
        else if(this.fworkerButton.isSelected()){
            haveBScDegree.setVisible(false);
            dontHaveBScDegree.setVisible(false);
            dateOfGraduationLabel.setVisible(false);
            dateOfGraduationText.setVisible(false);
            expertiseLevelLabel.setVisible(false);
            expertiseLevelText.setVisible(false);
            previousFarmNameLabel.setVisible(true);
            previousFarmNameText.setVisible(true);
            workExperienceText.setVisible(true);
            workExperienceLabel.setVisible(true);
        }
        if(e.getSource() == this.button){

            String response = "";
            int error_flag = 0;

            if(this.fworkerButton.isSelected()){
                if(empIdText.getText().equals("") || genderText.getText().equals("") || dobText.getText().equals("") || previousFarmNameText.getText().equals("") || workExperienceText.getText().equals("")){
                    new MessageBoxGUI("Please fill all the FarmWorker information!");
                    error_flag = 1;
                }
            }
            else if(this.vetButton.isSelected()){
                if(empIdText.getText().equals("") || genderText.getText().equals("") || dobText.getText().equals("") || expertiseLevelText.getText().equals("") || dateOfGraduationText.getText().equals("")){
                    new MessageBoxGUI("Please fill all the Veterinary information");
                    error_flag = 1;
                }
            }

            if(error_flag == 0){

                int vet_or_fworker = 0, exp_level = 0, wexperience = 0;
                int empid = Integer.parseInt(empIdText.getText());
                String gender = genderText.getText();

                if(gender.toLowerCase().contains("f")){
                    gender = "f";
                }
                else{
                    gender = "m";
                }

                String str_date = dobText.getText();
                Boolean BScDegree = false;
                String grad_date_str = "", pfarmname = "";

                if(this.fworkerButton.isSelected()){
                    vet_or_fworker = 1;
                    pfarmname = previousFarmNameText.getText();
                    wexperience = Integer.parseInt(workExperienceText.getText());
                }
                else if(this.vetButton.isSelected()){
                    vet_or_fworker = 0;
                    exp_level = Integer.parseInt(expertiseLevelText.getText());
                    grad_date_str = dateOfGraduationText.getText();
                    if(haveBScDegree.isSelected()){
                        BScDegree = true;
                    }
                    else{
                        BScDegree = false;
                    }
                }
                response = this.farm.addEmployee(vet_or_fworker, empid, exp_level, wexperience,BScDegree, str_date, gender, grad_date_str, pfarmname);
                frame.dispose();
                new FarmAppGUI(farm);
                new MessageBoxGUI(response);
            }
        }
    }
}
