import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 *     This class represents a sheep in the farm
 * </p>
 * @author Oguzhan Alperturk
 * @version java version 19
 */
public class Sheep extends Animal {

    /**
     * <p>
     *     constructor for a sheep object
     * </p>
     * @author Oguzhan Alperturk
     * @param tagNo tag number of the sheep
     * @param gender gender of the sheep
     * @param dateOfBirth date of birth of the sheep
     * @param purchased purchased (true) farm raised(false)
     * @param milking milking information of the sheep
     * @param treatments treatments list that the sheep has
     */
    public Sheep(int tagNo, String gender, Date dateOfBirth, boolean purchased, HashMap<Date, Double> milking, ArrayList<Treatment> treatments) {
        super(tagNo, gender, dateOfBirth, purchased, milking, treatments);
    }

    /**
     * <p>
     *     constructor for a sheep object
     * </p>
     * @author Oguzhan Alperturk
     * @param tagNo tag number of the sheep
     * @param gender gender of the sheep
     * @param dateOfBirth date of birth of the sheep
     * @param purchased purchased (true) farm raised(false)
     */
    public Sheep(int tagNo, String gender, Date dateOfBirth, boolean purchased) {
        super(tagNo, gender, dateOfBirth, purchased);
    }

    /**
     * <p>
     *     this method is for feeding the sheep
     * </p>
     * @author Oguzhan Alperturk
     * @return response of the operation
     */
    public String feeding(){
        String response = "";
        if(this.getAge() < 5 && this.getGender().equals("male")){
            response = "only grass";
        }
        else if(this.getAge() < 8 && this.getGender().equals("female")){
            response = "only grass";
        }
        else if(this.getAge() >= 5 && this.getGender().equals("male")){
            response = "TMR diet";
        }
        else if(this.getAge() >= 8 && this.getGender().equals("female")){
            response = "TMR diet";
        }
        return response;
    }

}
