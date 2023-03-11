import java.util.Date;

/**
 * <p>
 *     This class represents a cleaning treatment done in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class CleaningTreatment extends Treatment {
    private String materialsused;
    private FarmWorker farmWorker;

    /**
     * <p>
     *     Cleaning Treatmet constructor
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfTreatment date of the treatment
     * @param materialsused materials used during the treatment process
     * @param farmWorker the farmworker who did the cleaning treatment
     */
    public CleaningTreatment(Date dateOfTreatment, String materialsused, FarmWorker farmWorker) {
        super(dateOfTreatment);
        this.materialsused = materialsused;
        this.farmWorker = farmWorker;
    }

    /**
     * <p>
     *     cleaning treatment constructor
     * </p>
     * @author Oguzhan Alperturk
     */
    public CleaningTreatment() {
        this.materialsused = "Not provided";
        this.farmWorker = null;
    }

    /**
     * <p>
     *     get the materials used during the cleaning treatment process
     * </p>
     * @author Oguzhan Alperturk
     * @return the materials used during the cleaning treatment process
     */
    public String getMaterialsused() {
        return materialsused;
    }

    /**
     * <p>
     *     set materials used during the cleaning treatment process
     * </p>
     * @author Oguzhan Alperturk
     * @param materialsused materials used during the cleaning treatment process
     */
    public void setMaterialsused(String materialsused) {
        this.materialsused = materialsused;
    }

    /**
     * <p>
     *     get the farmworker who did the cleaning treatment
     * </p>
     * @author Oguzhan Alperturk
     * @return the farmworker object who did the cleaning treatment
     */
    public FarmWorker getFarmWorker() {
        return farmWorker;
    }

    /**
     * <p>
     *     set the farmworker object who did the cleaning treatment
     * </p>
     * @author Oguzhan Alperturk
     * @param farmWorker the farmworker object who did the cleaning treatment
     */
    public void setFarmWorker(FarmWorker farmWorker) {
        this.farmWorker = farmWorker;
    }

    /**
     * <p>
     *     List all the information about the cleaning treatment
     * </p>
     * @author Oguzhan Alperturk
     */
    public void listTreatment(){
        System.out.println("CLEANING TREATMENT");
        System.out.println("Date of the treatment: " + FarmApp.dateToString(super.getDateOfTreatment()));
        System.out.println("Materials used: " + this.materialsused);
        System.out.println("Emp ID of Farm Worker: " + this.farmWorker.getEmpID());
        System.out.println("");
    }
}
