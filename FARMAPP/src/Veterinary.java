import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *     This class is used for representing a veterinary in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class Veterinary extends Employee {
    private boolean BScDegree;
    private Date dateOfGraduation;
    private int expertiseLevel;

    /**
     * <p>
     *     constructor for a veterinary object
     * </p>
     * @author Oguzhan Alperturk
     * @param empID employee id of the veterinary
     * @param gender gender of the veterinary
     * @param dateOfBirth date of birth of the veterinary
     * @param BScDegree the veterinary has a BScDegree (true) / not (false)
     * @param dateOfGraduation the date of the graduation of the veterinary
     * @param expertiseLevel expertise level of the veterinary
     */
    public Veterinary(int empID, String gender, Date dateOfBirth, boolean BScDegree, Date dateOfGraduation, int expertiseLevel) {
        super(empID, gender, dateOfBirth);
        this.BScDegree = BScDegree;
        this.dateOfGraduation = dateOfGraduation;
        this.expertiseLevel = expertiseLevel;
    }

    /**
     * <p>
     *     constructor of the veterinary object
     * </p>
     * @author Oguzhan Alperturk
     */
    public Veterinary() {
        this.dateOfGraduation = FarmApp.stringToDate("00/00/0000");
        this.BScDegree = false;
        this.expertiseLevel = 0;
    }

    /**
     * <p>
     *     get the information does the veterinary has a BSc Degree
     *     true yes
     *     false no
     * </p>
     * @author Oguzhan Alperturk
     * @return true or false
     */
    public boolean isBScDegree() {
        return BScDegree;
    }

    /**
     * <p>
     *     set the information that if the veterinary has a BSc Degree or not
     *     true yes
     *     false no
     * </p>
     * @param BScDegree true or false
     */
    public void setBScDegree(boolean BScDegree) {
        this.BScDegree = BScDegree;
    }

    /**
     * <p>
     *     get the date of the graduation of the veterinary
     * </p>
     * @author Oguzhan Alperturk
     * @return the date of the graduation of the veterinary
     */
    public Date getDateOfGraduation() {
        return dateOfGraduation;
    }

    /**
     * <p>
     *     set the date of the graduation of the veterinary
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfGraduation date of the graduation of the veterinary
     */
    public void setDateOfGraduation(Date dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }

    /**
     * <p>
     *     get the expertise level of the veterinary
     * </p>
     * @author Oguzhan Alperturk
     * @return expertise level of the veterinary
     */
    public int getExpertiseLevel() {
        return expertiseLevel;
    }

    /**
     * <p>
     *     set the expertise level of the veterinary
     * </p>
     * @author Oguzhan Alperturk
     * @param expertiseLevel expertise level of the veterinary
     */
    public void setExpertiseLevel(int expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }

    /**
     * <p>
     *     get the salary of the veterinary
     * </p>
     * @author Oguzhan Alperturk
     * @return salary of the veterinary
     */
    public double getSalary(){
        return grossSalary + (0.1 * grossSalary * (LocalDateTime.now().getYear() - (dateOfGraduation.getYear() + 1900)));
    }


    /**
     * <p>
     *     this method compares the veterinary object with all of the employee types.
     *     this object's salary is bigger than any type of employee's salary then the function returns 1
     *     this object's salary is equal to any type of employee's salary then the function returns 0
     *     this object's salary is less than any type of employee's salary then the function returns -1
     * </p>
     * @param emp the object to be compared.
     * @return compare result value
     */
    public int compareTo(Employee emp) {
        if(emp instanceof FarmWorker){
            if(this.getSalary() > ((FarmWorker) emp).getSalary()){
                return 1;
            }
            else if(this.getSalary() == ((FarmWorker) emp).getSalary()){
                return 0;
            }
            else{
                return -1;
            }
        }
        else{
            if(this.getSalary() > ((Veterinary) emp).getSalary()){
                return 1;
            }
            else if(this.getSalary() == ((Veterinary) emp).getSalary()){
                return 0;
            }
            else{
                return -1;
            }
        }
    }
}
