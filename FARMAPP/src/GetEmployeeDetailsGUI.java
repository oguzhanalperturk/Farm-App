import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class GetEmployeeDetailsGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel empIDLabel;
    private JTextField empIDText;
    private JButton button;
    private JTable vetTable;
    private  JTable fworkerTable;
    private JScrollPane scrollVet;
    private JScrollPane scrollFworker;
    private String[][] vetData = new String[1][7];
    private String[][] fworkerData = new String[1][6];
    private String[] vetHeader = {"empID","Gender","DateOfBirth","BScDegree","dateOfGraduation","expertiseLevel"};
    private String[] fworkerHeader = {"empID","Gender","DateOfBirth","previousFarmName","workexperience"};

    private JButton exitButton;

    GetEmployeeDetailsGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Get Employee Details");
        panel = new JPanel();

        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


        empIDLabel = new JLabel("EmpID");
        panel.add(empIDLabel);

        empIDText = new JTextField(20);
        panel.add(empIDText);

        button = new JButton("Show Employee");
        button.addActionListener(this);
        panel.add(button);

        exitButton = new JButton("Exit");
        exitButton.setBounds(10,560,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);

        vetTable = new JTable(this.vetData, this.vetHeader);

        fworkerTable = new JTable(this.fworkerData, this.fworkerHeader);

        scrollVet = new JScrollPane(vetTable);

        scrollFworker = new JScrollPane(fworkerTable);


        panel.add(scrollVet);
        panel.add(scrollFworker);

        scrollFworker.setVisible(false);
        scrollVet.setVisible(false);

        frame.setVisible(true);
    }


    /**
     * <p>
     *     This is for getting the details of the specific employee from the farm
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
            int error_flag = 0,i,empID = 0;

            if(empIDText.getText().equals("")){
                new MessageBoxGUI("Please enter an EmpID!");
                error_flag = 1;
            }
            else{
                empID = Integer.parseInt(empIDText.getText());

                if (!FarmApp.isEmployeeInTheList(empID, this.farm.employees)) {
                    new MessageBoxGUI("There is no employee has empID: " + empID + " in this farm.");
                    error_flag = 1;
                }
            }

            if(error_flag == 0){
                for (i = 0; i < this.farm.employees.size(); i++) {
                    if (this.farm.employees.get(i).getEmpID() == empID) {
                        if (this.farm.employees.get(i) instanceof Veterinary) {

                            vetData[0][0] =  Integer.toString(this.farm.employees.get(i).getEmpID());
                            vetData[0][1] =  this.farm.employees.get(i).getGender();
                            String date_in_str = FarmApp.dateToString(this.farm.employees.get(i).getDateOfBirth());
                            vetData[0][2] = date_in_str;
                            if (((Veterinary) this.farm.employees.get(i)).isBScDegree()) {
                                vetData[0][3] = "has";
                            } else {
                                vetData[0][3] = "does not have";
                            }
                            date_in_str = FarmApp.dateToString(((Veterinary) this.farm.employees.get(i)).getDateOfGraduation());
                            vetData[0][4] = date_in_str;
                            vetData[0][5] = Integer.toString(((Veterinary) this.farm.employees.get(i)).getExpertiseLevel());

                            scrollVet.setVisible(false);
                            scrollVet.setVisible(true);
                            scrollFworker.setVisible(false);
                            frame.setVisible(true);

                        } else {

                            fworkerData[0][0] =  Integer.toString(this.farm.employees.get(i).getEmpID());
                            fworkerData[0][1] =  this.farm.employees.get(i).getGender();
                            String date_in_str = FarmApp.dateToString(this.farm.employees.get(i).getDateOfBirth());
                            fworkerData[0][2] = date_in_str;
                            fworkerData[0][3] =  ((FarmWorker) this.farm.employees.get(i)).getPreviousFarmName();
                            fworkerData[0][4] =  Integer.toString(((FarmWorker)this.farm.employees.get(i)).getWorkexperience());


                            scrollVet.setVisible(false);
                            scrollFworker.setVisible(false);
                            scrollFworker.setVisible(true);
                            frame.setVisible(true);
                        }
                    }
                }
            }
        }
    }
}
