import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class GetTreatmentWTagNoAndDateGUI implements ActionListener {
    private FarmApp farm;
    private JFrame frame;
    private JPanel panel;
    private JLabel tagNoLabel;
    private JTextField tagNoText;
    private JLabel dateLabel;
    private JTextField dateText;
    private JButton button;
    private String[] cleaning_treatment_header = {"date of treatment", "used materials"};
    private String[][] cleaning_treatment_data = new String[40][3];
    private JTable clean_table;
    private JScrollPane clean_scroll;
    private String[] health_treatment_header = {"date of treatment", "emergency"};
    private String[][] health_treatment_data = new String[40][3];
    private JTable health_table;
    private JScrollPane health_scroll;
    private JButton exitButton;

    GetTreatmentWTagNoAndDateGUI(FarmApp farm){
        this.farm = farm;
        frame = new JFrame("Get Treatment with tagno and date");
        panel = new JPanel();

        frame.setSize(600,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


        tagNoLabel = new JLabel("Tag Number");
        panel.add(tagNoLabel);

        tagNoText = new JTextField(20);
        panel.add(tagNoText);

        dateLabel = new JLabel("Date");
        panel.add(dateLabel);

        dateText = new JTextField(20);
        panel.add(dateText);

        button = new JButton("Show Treatment");
        button.addActionListener(this);
        panel.add(button);

        exitButton = new JButton("Exit");
        exitButton.setBounds(10,560,80,25);
        exitButton.addActionListener(this);
        panel.add(exitButton);



        clean_table = new JTable(cleaning_treatment_data, cleaning_treatment_header);
        health_table = new JTable(health_treatment_data, health_treatment_header);

        clean_scroll = new JScrollPane(clean_table);
        health_scroll = new JScrollPane(health_table);

        panel.add(clean_scroll);
        panel.add(health_scroll);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane window_scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(window_scroll);

        frame.setVisible(true);
    }


    /**
     * <p>
     *     This is for getting treatment information with tag number
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
            int error_flag = 0,i,j,tagNo = 0;

            for(i=0;i<40;i++){
                for(j=0;j<2;j++){
                    health_treatment_data[i][j] = "";
                }
            }

            for(i=0;i<40;i++){
                for(j=0;j<2;j++){
                    cleaning_treatment_data[i][j] = "";
                }
            }

            if(tagNoText.getText().equals("") || dateText.getText().equals("")){
                new MessageBoxGUI("Please enter all information!");
                error_flag = 1;
            }
            else{
                tagNo = Integer.parseInt(tagNoText.getText());

                if (!FarmApp.isAnimalInTheList(tagNo, this.farm.animals)) {
                    new MessageBoxGUI("There is no animal has tagNo: " + tagNo + " in this farm.");
                    error_flag = 1;
                }
            }
            if(error_flag == 0){
                int health_count = 0;
                int clean_count = 0;

                for(i=0;i<this.farm.animals.size();i++){
                    if(this.farm.animals.get(i).getTagNo() == tagNo){
                        for(j = 0;j<this.farm.animals.get(i).treatments.size();j++){
                            if(FarmApp.dateToString(this.farm.animals.get(i).treatments.get(j).getDateOfTreatment()).equals(dateText.getText())){
                                if(this.farm.animals.get(i).treatments.get(j) instanceof HealthTreatment){
                                    health_treatment_data[health_count][0] = FarmApp.dateToString(this.farm.animals.get(i).treatments.get(j).getDateOfTreatment());
                                    health_treatment_data[health_count][1] = Boolean.toString(((HealthTreatment) this.farm.animals.get(i).treatments.get(j)).isEmergency());
                                    health_count++;
                                }
                                else{
                                    cleaning_treatment_data[clean_count][0] = FarmApp.dateToString(this.farm.animals.get(i).treatments.get(j).getDateOfTreatment());
                                    cleaning_treatment_data[clean_count][1] = ((CleaningTreatment) this.farm.animals.get(i).treatments.get(j)).getMaterialsused();
                                    clean_count++;
                                }
                            }
                        }



                        health_scroll.setVisible(false);
                        health_scroll.setVisible(true);
                        clean_scroll.setVisible(false);
                        clean_scroll.setVisible(true);
                        frame.setVisible(true);
                    }
                }
            }
        }
    }
}

