import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     This class represents a farmworker in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class FarmWorker extends Employee {
    private String previousFarmName;
    private int workexperience;

    /**
     * <p>
     *     constructor for a farm worker
     * </p>
     * @author Oguzhan Alperturk
     * @param empID employee ID of a farmworker
     * @param gender gender
     * @param dateOfBirth date of birth
     * @param previousFarmName previous farm name
     * @param workexperience work experience
     */
    public FarmWorker(int empID, String gender, Date dateOfBirth, String previousFarmName, int workexperience) {
        super(empID, gender, dateOfBirth);
        this.previousFarmName = previousFarmName;
        this.workexperience = workexperience;
    }

    /**
     * <p>
     *     constructor for a farmworker
     * </p>
     * @author Oguzhan Alperturk
     */
    public FarmWorker() {
        this.previousFarmName = "Not Provided";
        this.workexperience = 0;
    }

    /**
     * <p>
     *     get previous farm name of a farmworker
     * </p>
     * @author Oguzhan Alperturk
     * @return previous farm name of a farmworker
     */
    public String getPreviousFarmName() {
        return previousFarmName;
    }

    /**
     * <p>
     *     set previous farm name of a farmworker
     * </p>
     * @author Oguzhan Alperturk
     * @param previousFarmName previous farm name of a farmworker
     */
    public void setPreviousFarmName(String previousFarmName) {
        this.previousFarmName = previousFarmName;
    }

    /**
     * <p>
     *     get the work experience of the farm worker
     * </p>
     * @author Oguzhan Alperturk
     * @return work experience of the farmworker
     */
    public int getWorkexperience() {
        return workexperience;
    }

    /**
     * <p>
     *     set the work experience of the farmworker
     * </p>
     * @author Oguzhan Alperturk
     * @param workexperience work experience of the farmworker
     */
    public void setWorkexperience(int workexperience) {
        this.workexperience = workexperience;
    }

    /**
     * <p>
     *     calculate the salary of the farm worker
     * </p>
     * @author Oguzhan Alperturk
     * @return salary of the farmworker
     */
    public double getSalary(){
        return grossSalary + (grossSalary * 0.02 * workexperience);
    }

    /**
     * <p>
     *      this method compares the farmworker object with all of the employee types.
     *      this object's salary is bigger than any type of employee's salary then the function returns 1
     *      this object's salary is equal to any type of employee's salary then the function returns 0
     *      this object's salary is lower than any type of employee's salary then the function returns -1
     * </p>
     * @param emp the object to be compared.
     * @return the result of the comparison
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
