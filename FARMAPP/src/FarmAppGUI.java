import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class FarmAppGUI implements ActionListener {
    private FarmApp farm;
    public JFrame frame;
    private JPanel panel;
    private JLabel entryLabel;
    private JRadioButton addAnimalButton;
    private JRadioButton deleteAnimalButton;
    private JRadioButton getAnimalDetails;

    private JRadioButton addEmployeeButton;
    private JRadioButton deleteEmployeeButton;
    private JRadioButton getEmployeeDetailsButton;
    private JRadioButton addTreatment;
    private JRadioButton getTreatmentWithTagNo;
    private JRadioButton getTreatmentWithTagNoAndDate;

    private JRadioButton listCowButton;
    private JRadioButton listSheepButton;
    private JRadioButton listVetButton;

    private JRadioButton listFarmWorker;
    private JRadioButton feedingAnimalButton;
    private JRadioButton getEmpSalaryButton;
    private JRadioButton addMilkingMeasurement;
    private JButton button;
    private JButton closeButton;
    private JMenuItem exitMenuItem;

    FarmAppGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Farm App");
        panel = new JPanel();

        frame.setSize(500,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        entryLabel = new JLabel("Choose your operation: ");
        entryLabel.setBounds(10,10,200,25);
        panel.add(entryLabel);

        ButtonGroup functions = new ButtonGroup();

        addAnimalButton = new JRadioButton("Add an animal into the farm");
        addAnimalButton.setBounds(20,40,280,25);

        deleteAnimalButton = new JRadioButton("Delete an animal from the farm");
        deleteAnimalButton.setBounds(20,70,280,25);

        getAnimalDetails = new JRadioButton("Get animal details");
        getAnimalDetails.setBounds(20,100,280,25);

        addEmployeeButton = new JRadioButton("Add an employee into the farm");
        addEmployeeButton.setBounds(20,130,280,25);

        deleteEmployeeButton = new JRadioButton("Delete an employee from the farm");
        deleteEmployeeButton.setBounds(20,160,280,25);

        getEmployeeDetailsButton = new JRadioButton("Get employee details");
        getEmployeeDetailsButton.setBounds(20,190,280,25);

        addTreatment = new JRadioButton("Add treatment to an animal");
        addTreatment.setBounds(20,220,280,25);

        getTreatmentWithTagNo = new JRadioButton("Get treatment information with tag number");
        getTreatmentWithTagNo.setBounds(20,250,280,25);

        getTreatmentWithTagNoAndDate = new JRadioButton("Get treatment information with tag number and date");
        getTreatmentWithTagNoAndDate.setBounds(20,280,480,25);

        listCowButton = new JRadioButton("List Cow");
        listCowButton.setBounds(20,310,310,25);

        listSheepButton = new JRadioButton("List Sheep");
        listSheepButton.setBounds(20,340,310,25);

        listVetButton = new JRadioButton("List Veterinary");
        listVetButton.setBounds(20,370,310,25);

        listFarmWorker = new JRadioButton("List Farmworker");
        listFarmWorker.setBounds(20,400,310,25);

        feedingAnimalButton = new JRadioButton("Feed Animal");
        feedingAnimalButton.setBounds(20,430,310,25);

        getEmpSalaryButton = new JRadioButton("Get Employee Salary");
        getEmpSalaryButton.setBounds(20,460,310,25);

        addMilkingMeasurement = new JRadioButton("Add Milking Measurement");
        addMilkingMeasurement.setBounds(20,490,310,25);

        button = new JButton("Select");
        button.setBounds(215,540,80,25);
        button.addActionListener(this);
        panel.add(button);

        closeButton = new JButton("Exit");
        closeButton.setBounds(15,540,80,25);
        closeButton.addActionListener(this);
        panel.add(closeButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Exit Menu");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        exitMenuItem = new JMenuItem("Exit");
        menu.add(exitMenuItem);
        exitMenuItem.addActionListener(this);

        frame.setJMenuBar(menuBar);


        functions.add(addAnimalButton);
        functions.add(deleteAnimalButton);
        functions.add(getAnimalDetails);
        functions.add(addEmployeeButton);
        functions.add(deleteEmployeeButton);
        functions.add(getEmployeeDetailsButton);
        functions.add(addTreatment);
        functions.add(getTreatmentWithTagNo);
        functions.add(getTreatmentWithTagNoAndDate);
        functions.add(listCowButton);
        functions.add(listSheepButton);
        functions.add(listVetButton);
        functions.add(listFarmWorker);
        functions.add(feedingAnimalButton);
        functions.add(getEmpSalaryButton);
        functions.add(addMilkingMeasurement);


        panel.add(addAnimalButton);
        panel.add(deleteAnimalButton);
        panel.add(getAnimalDetails);
        panel.add(addEmployeeButton);
        panel.add(deleteEmployeeButton);
        panel.add(getEmployeeDetailsButton);
        panel.add(addTreatment);
        panel.add(getTreatmentWithTagNo);
        panel.add(getTreatmentWithTagNoAndDate);
        panel.add(listCowButton);
        panel.add(listSheepButton);
        panel.add(listVetButton);
        panel.add(listFarmWorker);
        panel.add(feedingAnimalButton);
        panel.add(getEmpSalaryButton);
        panel.add(addMilkingMeasurement);


        frame.setVisible(true);
    }


    /**
     * <p>
     *     This operates the main interface of the program
     * </p>
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitMenuItem){
            frame.dispose();
            try {
                farm.exit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == closeButton){
            frame.dispose();
            try {
                farm.exit();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(this.addAnimalButton.isSelected()){
            new AddAnimalGUI(farm);
            frame.dispose();
        }
        else if(this.deleteAnimalButton.isSelected()){
            new DeleteAnimalGUI(farm);
            frame.dispose();
        }
        else if(this.getAnimalDetails.isSelected()){
            new GetAnimalDetailsGUI(farm);
            frame.dispose();
        }
        else if(addEmployeeButton.isSelected()){
            new AddEmployeeGUI(farm);
            frame.dispose();
        }
        else if(deleteEmployeeButton.isSelected()){
            new DeleteEmployeeGUI(farm);
            frame.dispose();
        }
        else if(getEmployeeDetailsButton.isSelected()){
            new GetEmployeeDetailsGUI(farm);
            frame.dispose();
        }
        else if(addTreatment.isSelected()){
            new AddTreatmentGUI(farm);
            frame.dispose();
        }
        else if(getTreatmentWithTagNo.isSelected()){
            new GetTreatmentWTagNoGUI(farm);
            frame.dispose();
        }
        else if(getTreatmentWithTagNoAndDate.isSelected()){
            new GetTreatmentWTagNoAndDateGUI(farm);
            frame.dispose();
        }
        else if(this.listCowButton.isSelected()){
            new ListCowGUI(farm);
            frame.dispose();
        }
        else if(this.listSheepButton.isSelected()){
            new ListSheepGUI(farm);
            frame.dispose();
        }
        else if(this.listVetButton.isSelected()){
            new ListVeterinaryGUI(farm);
            frame.dispose();
        }
        else if(this.listFarmWorker.isSelected()){
            new ListFarmWorkerGUI(farm);
            frame.dispose();
        }
        else if(this.feedingAnimalButton.isSelected()){
            new FeedAnimalGUI(farm);
            frame.dispose();
        }
        else if(this.getEmpSalaryButton.isSelected()){
            new GetEmployeeSalaryGUI(farm);
            frame.dispose();
        }
        else if(this.addMilkingMeasurement.isSelected()){
            new AddMilkingMeasurementGUI(farm);
            frame.dispose();
        }
    }
}
