import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     this class represents a medication that can take during the treatment process
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class Medication implements Serializable {
    private String details;
    private int duration;
    private Date startDate;
    private double Dosage;
    private String notes;

    /**
     * <p>
     *     constructor for a medication object
     * </p>
     * @author Oguzhan Alperturk
     * @param details details of the medication
     * @param duration duration of the medication
     * @param startDate start date of the medication
     * @param dosage dosage of the medication
     * @param notes notes of the medication
     */
    public Medication(String details, int duration, Date startDate, double dosage, String notes) {
        this.details = details;
        this.duration = duration;
        this.startDate = startDate;
        Dosage = dosage;
        this.notes = notes;
    }

    /**
     * <p>
     *     constructor for the medication object
     * </p>
     * @author Oguzhan Alperturk
     */
    public Medication(){
        this.details = "Not Provided";
        this.duration = 0;
        this.startDate = FarmApp.stringToDate("00/00/0000");
        Dosage = 0;
        this.notes = "Not Provided";
    }

    /**
     * <p>
     *     This method is for getting the details of the medication object
     * </p>
     * @author Oguzhan Alperturk
     * @return details of the medication object
     */
    public String getDetails() {
        return details;
    }

    /**
     * <p>
     *     This method is for setting the details of the medication object
     * </p>
     * @author Oguzhan Alperturk
     * @param details details of the medication object
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * <p>
     *     This method is for getting the duration of the medication object
     * </p>
     * @author Oguzhan Alperturk
     * @return duration of the medication object
     */
    public int getDuration() {
        return duration;
    }

    /**
     * <p>
     *     This method is for setting duration of the medication object
     * </p>
     * @author Oguzhan Alperturk
     * @param duration duration of the medication object
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * <p>
     *     This method is for getting start date of the medication object
     * </p>
     * @author Oguzhan Alperturk
     * @return start date of the medication
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * <p>
     *     This method is for setting the start date of the medication object
     * </p>
     * @author Oguzhan Alperturk
     * @param startDate start date of the medication
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * <p>
     *     This method isfor getting dosage of the medication
     * </p>
     * @author Oguzhan Alperturk
     * @return dosage of the medication
     */
    public double getDosage() {
        return Dosage;
    }

    /**
     * <p>
     *     This method is for setting dosage of the medication
     * </p>
     * @author Oguzhan Alperturk
     * @param dosage dosage of the medication
     */
    public void setDosage(double dosage) {
        Dosage = dosage;
    }

    /**
     * <p>
     *     This method is for getting notes of the medication
     * </p>
     * @author Oguzhan Alperturk
     * @return notes of the medication
     */
    public String getNotes() {
        return notes;
    }

    /**
     * <p>
     *     This method is for setting notes of the medication
     * </p>
     * @author Oguzhan Alperturk
     * @param notes notes of the medication
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * <p>
     *     list all the medication information
     * </p>
     * @author Oguzhan Alperturk
     */
    public void listMedication(){
        System.out.println("Details of the medication: " + this.details);
        System.out.println("Duration of the medication: " + this.duration);
        System.out.println("Start date of this medication: " + FarmApp.dateToString(this.startDate));
        System.out.println("Dosage of the medication: " + this.Dosage);
        System.out.println("Notes of the medication: " + this.notes);
        System.out.println("");
    }
}
