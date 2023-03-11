import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 *     This class represents a cow in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class Cow extends Animal {
    private double weight;

    /**
     * <p>
     *     cow object constructor
     * </p>
     * @author Oguzhan Alperturk
     * @param tagNo tag number of the cow
     * @param gender gender of the cow
     * @param dateOfBirth date of birth of the cow
     * @param purchased purchased (true) farm raised (false)
     * @param milking milking information of the cow
     * @param treatments treatment list that the cow had
     * @param weight weight of the cow
     */
    public Cow(int tagNo, String gender, Date dateOfBirth, boolean purchased, HashMap<Date, Double> milking, ArrayList<Treatment> treatments, double weight) {
        super(tagNo, gender, dateOfBirth, purchased, milking, treatments);
        this.weight = weight;
    }

    /**
     * <p>
     *     Cow object constructor
     * </p>
     * @author Oguzhan Alperturk
     * @param tagNo tag number of the cow
     * @param gender gender of the cow
     * @param dateOfBirth date of birth of the cow
     * @param purchased purchased (true) farm raised (false)
     * @param weight weight of the cow
     */
    public Cow(int tagNo, String gender, Date dateOfBirth, boolean purchased, double weight) {
        super(tagNo, gender, dateOfBirth, purchased);
        this.weight = weight;
    }

    /**
     * <p>
     *     get the weight of the cow
     * </p>
     * @author Oguzhan Alperturk
     * @return weight of the cow
     */
    public double getWeight() {
        return weight;
    }

    /**
     * <p>
     *     set the weight of the cow
     * </p>
     * @author Oguzhan Alperturk
     * @param weight weight of the cow
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * <p>
     *     this method is for feeding the cow depending on its age and weight
     * </p>
     * @return response of the operation
     */
    public String feeding(){
        String response = "";
        if(this.getAge() < 3){
            response = "only grass";
        }
        else if(this.getAge() >= 5 && this.weight < 500){
            response = "TMR diet";
        }
        else if(this.getAge() > 10){
            response = "grains and oilseed meals";
        }
        else{
            response = "grass and grains";
        }
        return response;
    }

}
