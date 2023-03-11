
/**
 * <p>
 *     This interface is for calculating the payment of the employees
 * </p>
 */
public interface Payment {
    /**
     * <p>
     *     I assumed gross salary is 10000
     * </p>
     */
    public double grossSalary = 10000;

    /**
     * <p>
     *     get the salary of the employee
     * </p>
     * @return salary of the employee
     */
    public double getSalary();

}
