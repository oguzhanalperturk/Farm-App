import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class GetEmployeeSalaryGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel empIDLabel;
    private JTextField empIDText;
    private JButton button;
    private JButton exitButton;

    GetEmployeeSalaryGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Get Employee Salary");
        panel = new JPanel();

        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        empIDLabel = new JLabel("Emp ID");
        empIDLabel.setBounds(10,10,80,25);
        panel.add(empIDLabel);

        empIDText = new JTextField(20);
        empIDText.setBounds(100,10,165,25);
        panel.add(empIDText);

        button = new JButton("Get Employee Salary");
        button.setBounds(100,50,165,25);
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
     *     This is for getting the salary pf the specific employee
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
            if(empIDText.getText().equals("")){
                new MessageBoxGUI("Please enter an employee ID!");
            }
            else{
                int empID = Integer.parseInt(empIDText.getText());
                int i;
                if(!FarmApp.isEmployeeInTheList(empID, farm.employees)){
                    new MessageBoxGUI("The employee with " + empID + " is not in the list!");
                }
                else{
                    String salary = Double.toString(farm.getEmpSalary(empID));
                    new MessageBoxGUI("The Salary of the employee is: " + salary);
                }
            }
        }
    }
}
