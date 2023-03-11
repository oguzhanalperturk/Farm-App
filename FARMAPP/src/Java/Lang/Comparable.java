package Java.Lang;

/**
 * <p>
 *     This interface is for comparing two employee object out of their salary
 * </p>
 */
public interface Comparable {

    /**
     * <p>
     *     used for comparing two objects generally, but in this code it is comparing two employee objects out of their salary
     * </p>
     * @return returns -1,0,1 according to the result of the comparison
     */
    public int compareTo();
}
