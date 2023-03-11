import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * <p>
 *     This class represents an animal in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public abstract class Animal implements Serializable {
    private int tagNo;
    private String gender;
    private Date dateOfBirth;
    private boolean purchased;

    /**
     * <p>
     *     list of treatments that the animal has
     * </p>
     */
    public ArrayList<Treatment> treatments;
    /**
     * the milking information of the animal
     */
    private HashMap<Date, Double> milking;


    /**
     * <p>
     *     constructor of the animal object
     * </p>
     * @author Oguzhan Alperturk
     * @param tagNo tag number of the animal
     * @param gender gender of the animal
     * @param dateOfBirth date of birth of the animal
     * @param purchased true if the animal is purchased, false if the animal is farm raised
     * @param milking milking information of the animal
     * @param treatments the list of treatments that the animal has
     */
    public Animal(int tagNo, String gender, Date dateOfBirth, boolean purchased, HashMap<Date, Double> milking, ArrayList<Treatment> treatments) {
        this.tagNo = tagNo;
        this.gender = gender.toLowerCase();
        this.dateOfBirth = dateOfBirth;
        this.purchased = purchased;
        this.milking = milking;
        this.treatments = treatments;
    }

    /**
     * <p>
     *     constructor of the animal object
     * </p>
     * @author Oguzhan Alperturk
     * @param tagNo tag number of the animal
     * @param gender gender of the animal
     * @param dateOfBirth date of birth of the animal
     * @param purchased true if the animal is purchased, false if the animal is farm raised
     */
    public Animal(int tagNo, String gender, Date dateOfBirth, boolean purchased) {
        this.tagNo = tagNo;
        this.gender = gender.toLowerCase();
        this.dateOfBirth = dateOfBirth;
        this.purchased = purchased;
        this.milking = new HashMap<Date, Double>();
        this.treatments = new ArrayList<Treatment>();
    }

    /**
     * <p>
     *     get tag number of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @return tag number of the animal
     */
    public int getTagNo() {
        return tagNo;
    }

    /**
     * <p>
     *     set tag number of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @param tagNo tag number of the animal
     */
    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    /**
     * <p>
     *     get gender of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @return gender of the animal
     */
    public String getGender() {
        return gender;
    }

    /**
     * <p>
     *     Set gender of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @param gender gender of the animal
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * <p>
     *     get date of birth of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @return date of birth of the animal
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * <p>
     *     set date of birth of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfBirth date of birth of the animal
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * <p>
     *     get the purchased (true) or farm raised (false) information of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @return purchased (true) farm raised(false)
     */
    public boolean isPurchased() {
        return purchased;
    }

    /**
     * <p>
     *     set the purchsed (true) or farm raised(false) information of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @param purchased purchased (true) farm raised (false)
     */
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    /**
     * <p>
     *     get the milking information of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @return milking information hash map
     */
    public HashMap<Date, Double> getMilking() {
        return milking;
    }

    /**
     * <p>
     *     set the milking information of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @param milking milking information hash map
     */
    public void setMilking(HashMap<Date, Double> milking) {
        this.milking = milking;
    }

    /**
     * <p>
     *     get the age of the animal
     * </p>
     * @author Oguzhan Alperturk
     * @return the age of the animal
     */
    public int getAge(){
        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        now.setTime(new Date());
        dob.setTime(this.dateOfBirth);

        return now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    }

    /**
     * <p>
     *     feeding each animal (will be defined into subclasses)
     * </p>
     * @author Oguzhan Alperturk
     * @return response of operation
     */
    public abstract String feeding();

    /**
     * <p>
     *     Listing the treatments of the animal
     * </p>
     * @author Oguzhan Alperturk
     */
    public void listTreatments(){
        int i;
        for (i=0;i<this.treatments.size();i++){
            this.treatments.get(i).listTreatment();
        }
    }

    /**
     * <p>
     *     List the treatments of the animal on a given date
     * </p>
     *  @author Oguzhan Alperturk
     *  @param dateOfTreatment date of the treatment
     */
    public void listTreatments(Date dateOfTreatment){
        int i,flag = 0;
        for (i=0;i<this.treatments.size();i++){
            if(this.treatments.get(i).getDateOfTreatment().equals(dateOfTreatment)){
                flag = 1;
                this.treatments.get(i).listTreatment();

            }
        }
        if(flag == 0){
            System.out.println("The animal with tag no: " + tagNo + " has no treatment on " + FarmApp.dateToString(dateOfTreatment) + "!");
        }
    }

    /**
     * <p>
     *     adding milking information to the hash map
     * </p>
     * @author Oguzhan Alperturk
     * @param amount the amount of milk getting from the animal
     */
    public void addInfoToHashmap(double amount){
        LocalDate localDate_now = LocalDate.now();
        Date date_now = java.sql.Date.valueOf(localDate_now);

        milking.put(date_now, amount);
    }
}
