import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 *     This class represents a health treatment done in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class HealthTreatment extends Treatment implements Serializable {
    private boolean emergency;
    private Veterinary vet;
    /**
     * <p>
     *     The medications list should be used during the treatment process
     * </p>
     */
    public ArrayList<Medication> medications;


    /**
     * <p>
     *     constructor for a health treatment object
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfTreatment date of the health treatment
     * @param emergency the health treatment is emergency (true) or not (false)
     * @param vet the veterinary who gave the health treatment
     */
    public HealthTreatment(Date dateOfTreatment, boolean emergency, Veterinary vet) {
        super(dateOfTreatment);
        this.emergency = emergency;
        this.vet = vet;
        this.medications = new ArrayList<Medication>();
    }

    /**
     * <p>
     *     constructor for a health treatment object
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfTreatment date of the health treatment
     * @param emergency emergency (true) not emergency (false) information of the health treatment
     * @param vet the veterinary object who gave the treatment
     * @param medications medications list that is used during the treatment process
     */
    public HealthTreatment(Date dateOfTreatment, boolean emergency, Veterinary vet, ArrayList<Medication> medications) {
        super(dateOfTreatment);
        this.emergency = emergency;
        this.vet = vet;
        this.medications = medications;
    }

    /**
     * <p>
     *     constructor for a health treatment object
     * </p>
     * @author Oguzhan Alperturk
     */
    public HealthTreatment() {
        this.emergency = false;
        this.vet = null;
    }

    /**
     * <p>
     *     get the emergency information of the treatment
     *     emergency (true)
     *     not emergency (false)
     * </p>
     * @author Oguzhan Alperturk
     * @return emergency information (true or false)
     */
    public boolean isEmergency() {
        return emergency;
    }

    /**
     * <p>
     *     set the emergency information of the treatment
     * </p>
     * @author Oguzhan Alperturk
     * @param emergency emergency(true) not emergency(false)
     */
    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    /**
     * <p>
     *     get the vet object who gave the treatment
     * </p>
     * @author Oguzhan Alperturk
     * @return the vet object who gave the treatment
     */
    public Veterinary getVet() {
        return vet;
    }

    /**
     * <p>
     *     set the vet object who gave the treatment
     * </p>
     * @author Oguzhan Alperturk
     * @param vet the vet object who gave the treatment
     */
    public void setVet(Veterinary vet) {
        this.vet = vet;
    }

    /**
     * <p>
     *     List the treatment information and medications used during the treatment process
     * </p>
     * @author Oguzhan Alperturk
     */
    public void listTreatment() {
        int i,count = 1;
        System.out.println("HEALTH TREATMENT");
        System.out.println("Date of the treatment: " + FarmApp.dateToString(super.getDateOfTreatment()));
        System.out.println("Emergency: " + this.emergency);
        System.out.println("Emp id of the veterinary: " + this.vet.getEmpID());
        System.out.println("");
        System.out.println("-- MEDICATIONS --\n");
        for(i=0;i<this.medications.size();i++){
            System.out.println("Medication " + count++ + ":");
            this.medications.get(i).listMedication();
            System.out.println("");
        }
    }
}
