import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     This class represents a treatment given in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class Treatment implements Serializable {
    private Date dateOfTreatment;

    /**
     * <p>
     *    constructor for a treatment object
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfTreatment date of the treatment
     */
    public Treatment(Date dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * <p>
     *     constructor for the treatment object
     * </p>
     * @author Oguzhan Alperturk
     */
    public Treatment() {
        this.dateOfTreatment = FarmApp.stringToDate("00/00/0000");
    }

    /**
     * <p>
     *     get the date of the treatment
     * </p>
     * @author Oguzhan Alperturk
     * @return the date of the treatment
     */
    public Date getDateOfTreatment() {
        return dateOfTreatment;
    }

    /**
     * <p>
     *     set the date of the treatment
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfTreatment the date of the treatment
     */
    public void setDateOfTreatment(Date dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * <p>
     *     display the treatment information
     * </p>
     * @author Oguzhan Alperturk
     */
    public void listTreatment() {
        System.out.println("Date of the treatment: " + FarmApp.dateToString(this.dateOfTreatment));
    }
}
