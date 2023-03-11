import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     This class represents an employee in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public abstract class Employee implements Payment, Comparable<Employee>, Serializable {
    private int empID;
    private String gender;
    private Date dateOfBirth;


    /**
     * <p>
     *     constructor of the employee object
     * </p>
     * @author Oguzhan Alperturk
     * @param empID employee ID of the employee
     * @param gender gender of the employee
     * @param dateOfBirth date of birth of the employee
     */
    public Employee(int empID, String gender, Date dateOfBirth) {
        this.empID = empID;
        this.gender = gender.toLowerCase();
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * <p>
     *     constructor of the employee
     * </p>
     * @author Oguzhan Alperturk
     */
    public Employee() {
        this.dateOfBirth = FarmApp.stringToDate("00/00/0000");
        this.empID = -1;
        this.gender = "Not Provided";
    }

    /**
     * <p>
     *     get the employee ID of the employee
     * </p>
     * @author Oguzhan Alperturk
     * @return employee ID
     */
    public int getEmpID() {
        return empID;
    }

    /**
     * <p>
     *     set the employee ID of the employee
     * </p>
     * @author Oguzhan Alperturk
     * @param empID employee ID
     */
    public void setEmpID(int empID) {
        this.empID = empID;
    }

    /**
     * <p>
     *     get the gender of the employee
     * </p>
     * @author Oguzhan Alperturk
     * @return gender of the employee
     */
    public String getGender() {
        return gender;
    }

    /**
     * <p>
     *     set the gender of the employee
     * </p>
     * @author Oguzhan Alperturk
     * @param gender gender of the employee
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * <p>
     *     get the date of birth of the employee
     * </p>
     * @author Oguzhan Alperturk
     * @return date of birth of the employee
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * <p>
     *     set the date of birth of the employee
     * </p>
     * @author Oguzhan Alperturk
     * @param dateOfBirth date of birth of the employee
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
