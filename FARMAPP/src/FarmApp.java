import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * <p>
 *     This class represents a farm
 * </p>
 * @author Oğuzhan Alpertürk
 * @version Java version 19
 */
public class FarmApp {

    /**
     * <p>
     *      the arraylist holds all the animals in the farm
     * </p>
     */
    public ArrayList<Animal> animals;

    /**
     * <p>
     *     the arraylist holds all the employees in the farm
     * </p>
     */
    public ArrayList<Employee> employees;

    /**
     * <p>
     *     constructor for the farm object
     * </p>
     */
    public FarmApp() {
        this.animals = new ArrayList<Animal>();
        this.employees = new ArrayList<Employee>();
    }

    /**
     * <p>
     *     constructor for the farm object
     * </p>
     * @param animals animals in the farm list
     * @param employees employees in the farm list
     */
    public FarmApp(ArrayList<Animal> animals, ArrayList<Employee> employees) {
        this.animals = animals;
        this.employees = employees;
    }

    /**
     * <p>
     *     if the the animal with tagNo in the list, the function returns true, otherwise returns false
     * </p>
     * @param tagNo tag number of the animal
     * @param animals animals in the farm list
     * @return true: if the animal which has given tag no in the list, otherwise returns false
     */
    public static boolean isAnimalInTheList(int tagNo, ArrayList<Animal> animals) {
        int i;
        for (i = 0; i < animals.size(); i++) {
            if (animals.get(i).getTagNo() == tagNo) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     *     if the the employee with empID in the list, the function returns true, otherwise returns false
     * </p>
     * @param empID employee ID
     * @param employees the list which includes all the employees in the farm
     * @return true: if the employee with empID in the list, otherwise returns false
     */
    public static boolean isEmployeeInTheList(int empID, ArrayList<Employee> employees) {
        int i;
        for (i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmpID() == empID) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>
     *     converting string into date format
     * </p>
     * @param date_str date valur in string
     * @return date value in Date format
     */
    public static Date stringToDate(String date_str) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(date_str);
            return date;
        } catch (ParseException e) {
            System.out.println("Enter date in given format!!");
        }
        return date;
    }

    /**
     * <p>
     *     converting date value into string format
     * </p>
     * @param date date value of date
     * @return string value of date
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.format(date);
    }

    /**
     * <p>
     *     This method is for adding an animal
     * </p>
     * @param tagNo tag number of the animal
     * @param gender gender
     * @param purchased purchased or farm raised
     * @param str_date date of birth
     * @param weight weight
     * @param cow_or_sheep 0->cow 1->sheep
     * @return the response (result) of the operation
     */
    public String addAnimal(int tagNo, String gender, boolean purchased, String str_date, double weight, int cow_or_sheep) { // 0->cow 1->sheep

        Animal newanimal = null;
        String response = "";


        if(isAnimalInTheList(tagNo,animals)){
            response = "TagNo you entered already exists in the farm.";
            return response;
        }

        Date dob = FarmApp.stringToDate(str_date);
        if(dob == null){
            response = "Enter date in dd/MM/yyyy format!!";
            return response;
        }

        if (cow_or_sheep == 0) {
            newanimal = new Cow(tagNo, gender, dob, purchased, weight);
            response = "New Cow is added to the farm successfully!";
        }
        else {
            newanimal = new Sheep(tagNo, gender, dob, purchased);
            response = "New Sheep is added to the farm successfully!";
        }

        animals.add(newanimal);
        return response;

    }

    /**
     * <p>
     *     for deleting an animal
     * </p>
     * @param tagNo tag number
     * @return response of the operation
     */
    public String deleteAnimal(int tagNo) {
        int i;
        String response = "";
        if (!isAnimalInTheList(tagNo, this.animals)) {
            response = "There is no animal has tagNo " + tagNo + " in this farm.";
            return response;
        }
        for (i = 0; i < animals.size(); i++) {
            if (animals.get(i).getTagNo() == tagNo) {
                animals.remove(i);
                response = tagNo + " is succesfully deleted.";
            }
        }

        Connection connection = null;
        Statement statement = null;

        try {
            // STEP1 -- Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
            System.out.println("Database connected");

            // STEP 3 -- Create a statement
            statement = connection.createStatement();


            ArrayList<Integer> registered_tag_numbers = new ArrayList<Integer>();
            ResultSet resultSet = statement.executeQuery("select tagNo from Animal");

            while (resultSet.next()) {
                registered_tag_numbers.add(resultSet.getInt(1));
            }

            if(registered_tag_numbers.contains(tagNo)){
                PreparedStatement deleteAnimal = connection.prepareStatement("DELETE FROM `Animal` WHERE tagNo=?");
                deleteAnimal.setInt(1,tagNo);

                deleteAnimal.execute();
            }


        }catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try


        return response;
    }


    /**
     * <p>
     *     For adding an employee
     * </p>
     * @param vet_or_fworker 0->vet, 1->fworker
     * @param empid employee ID
     * @param exp_level expertise level
     * @param wexperience experience
     * @param BScDegree have BScDegree or not
     * @param str_date date of birth
     * @param gender gender
     * @param grad_date_str graduation date
     * @param pfarmname previous farm name
     * @return response of the operation
     */
    public String addEmployee(int vet_or_fworker, int empid, int exp_level, int wexperience, boolean BScDegree, String str_date, String gender, String grad_date_str, String pfarmname ){ // 0->vet, 1->fworker

        Employee newEmployee;
        Date dateofbirth, grad_date;
        String response;

        if(isEmployeeInTheList(empid,employees)){
            response = "ID no you entered already exists in the farm.";
            return response;
        }

        dateofbirth = stringToDate(str_date);
        if(dateofbirth == null){
            response = "Enter date in dd/MM/yyyy format!!";
            return response;
        }

        if(vet_or_fworker == 0){
            grad_date = stringToDate(grad_date_str);
            if(grad_date == null){
                response = "Enter date in dd/MM/yyyy format!!";
                return response;
            }

            newEmployee = new Veterinary(empid, gender, dateofbirth, BScDegree, grad_date, exp_level);
            employees.add(newEmployee);
            response = "New Veterinary is added to the farm successfully!";

        }
        else{
            newEmployee = new FarmWorker(empid, gender, dateofbirth, pfarmname, wexperience);
            employees.add(newEmployee);
            response = "New FarmWorker is added to the farm successfully!";
        }
        return response;
    }

    /**
     * <p>
     *     for deleting an employee
     * </p>
     * @param empID employee ID
     * @return response of the operation
     */
    public String deleteEmployee(int empID){
        int i;
        String response = "";
        if(!isEmployeeInTheList(empID,employees)){
            response = "There is no employee has empID: " + empID + " in this farm.";
            return response;
        }
        for(i=0;i<employees.size();i++){
            if(employees.get(i).getEmpID() == empID){
                employees.remove(i);
                response = empID + " is succesfully deleted.";
            }
        }


        Connection connection = null;
        Statement statement = null;

        try {
            // STEP1 -- Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
            System.out.println("Database connected");

            // STEP 3 -- Create a statement
            statement = connection.createStatement();


            ArrayList<Integer> registered_empids = new ArrayList<Integer>();
            ResultSet resultSet = statement.executeQuery("select empID from Employee");

            while (resultSet.next()) {
                registered_empids.add(resultSet.getInt(1));
            }

            if(registered_empids.contains(empID)){
                PreparedStatement deleteEmployee = connection.prepareStatement("DELETE FROM `Employee` WHERE empID=?");
                deleteEmployee.setInt(1, empID);

                deleteEmployee.execute();
            }


        }catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try




        return response;
    }


    /**
     * <p>
     *     For adding a treatment to an animal
     * </p>
     * @param empID employee id of who gives the treatment
     * @param tagNo tag number of the animal who took the treatment
     * @param clean_or_health 0-> clean 1-> health
     * @param used_materials used materials
     * @param str_date_of_treatment date of the treatment
     * @param emergency emergency of the treatment
     * @param medications medications of the treatment
     * @return the response of the operation
     */
    public String addTreatment(int empID, int tagNo, int clean_or_health, String used_materials, String str_date_of_treatment, boolean emergency, ArrayList<Medication> medications){ // 0-> clean 1-> health

        int i,flagEmp = 0, flagAnimal = 0;
        boolean isvet = false, isfworker=false, stop_flag = false;
        Treatment treatment = null;
        Date date_of_treatment = null;
        String response = "";

        for(i=0;i<animals.size();i++){
            if(animals.get(i).getTagNo() == tagNo){
                flagAnimal = 1;
            }
        }
        for(i=0;i<employees.size();i++){
            if(employees.get(i).getEmpID() == empID){
                flagEmp = 1;
                if(employees.get(i) instanceof Veterinary){
                    isvet = true;
                }
                else{
                    isfworker = true;
                }
            }
        }

        if(flagEmp == 0){
            response += "There is no employee has given empID!\n";
            stop_flag = true;
        }
        if(flagAnimal == 0){
            response += "There is no animal has given tagNo!\n";
            stop_flag = true;
        }

        if(stop_flag){
            return response;
        }


        if(clean_or_health == 0) {
            if(isfworker){
                date_of_treatment = FarmApp.stringToDate(str_date_of_treatment);
                FarmWorker fworker = null;
                for(i=0;i<employees.size();i++){
                    if(empID == employees.get(i).getEmpID()){
                        fworker = (FarmWorker)employees.get(i);
                    }
                }

                treatment = new CleaningTreatment(date_of_treatment,used_materials,fworker);
            }
            else{
                response = "The employee who has the emp ID: " + empID + " is a veterinary. Please choose a Farm Worker for cleaning treatment!";
                return response;
            }
        }
        if(clean_or_health == 1){
            if(isvet){
                Veterinary vet = null;
                date_of_treatment = FarmApp.stringToDate(str_date_of_treatment);

                for(i=0;i<employees.size();i++){
                    if(empID == employees.get(i).getEmpID()){
                        vet = (Veterinary) employees.get(i);
                    }
                }

                treatment = new HealthTreatment(date_of_treatment, emergency, vet, medications);

            }
            else{
                response = "The employee who has the emp ID: " + empID + " is a farm worker. Please choose a veterinary for health treatment!";
                return response;
            }
        }

        for(i=0;i<animals.size();i++){
            if(animals.get(i).getTagNo() == tagNo){
                animals.get(i).treatments.add(treatment);
                break;
            }
        }

        return "Treatment is added successfully!";

    }



    /**
     * <p>
     *     display all the treatments that a specific animal took with tag number and on a specific date
     * </p>
     * @param tagNo the tag number of the animal whose treatment details will be displayed
     * @param dateOfTreatment the date which treatments taken on will be displayed
     */
    public void getAnimalTreatment(int tagNo, Date dateOfTreatment){
        if(!(isAnimalInTheList(tagNo,animals))){
            System.out.println("There is no animal has tagNo " + tagNo + " in this farm.");
        }
        else{
            int i;
            System.out.println("-----TREATMENTS-----");
            System.out.println("");
            for(i=0;i<animals.size();i++){
                if(animals.get(i).getTagNo() == tagNo){
                    animals.get(i).listTreatments(dateOfTreatment);
                }
            }
        }
    }

    /**
     * <p>
     *     display the all cow objects in the farm
     * </p>
     */
    void listCow(){
        int i,count = 1;
        System.out.println("Information of the Cows in the farm");
        System.out.println("--------------------------");
        for(i=0;i<animals.size();i++){
            if(animals.get(i) instanceof Cow){
                System.out.println(count++ + ". Cow");
                System.out.println("TagNo: " + animals.get(i).getTagNo());
                System.out.println("Gender: " + animals.get(i).getGender());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String date_in_str = formatter.format(animals.get(i).getDateOfBirth());
                System.out.println("Date of Birth: " + date_in_str);
                System.out.println("Purchased: " + animals.get(i).isPurchased());
                System.out.println("Weight: " + ((Cow)animals.get(i)).getWeight());
                System.out.println("Milking:");
                System.out.println(animals.get(i).getMilking());
                System.out.println("");
            }
        }
    }

    /**
     * <p>
     *     display the all sheep objects in the farm
     * </p>
     */
    void listSheep(){
        int i,count = 1;
        System.out.println("Information of the Sheep in the farm");
        System.out.println("--------------------------");
        for(i=0;i<animals.size();i++){
            if(animals.get(i) instanceof Sheep){
                System.out.println(count++ + ". Sheep");
                System.out.println("TagNo: " + animals.get(i).getTagNo());
                System.out.println("Gender: " + animals.get(i).getGender());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String date_in_str = formatter.format(animals.get(i).getDateOfBirth());
                System.out.println("Date of Birth: " + date_in_str);
                System.out.println("Purchased: " + animals.get(i).isPurchased());
                System.out.println("Milking:");
                System.out.println(animals.get(i).getMilking());
                System.out.println("");
            }
        }
    }

    /**
     * <p>
     *     display the all veterinary objects in the farm
     * </p>
     */
    public void listVet(){
        int i,count = 1;
        System.out.println("Information of the Vets in the farm");
        System.out.println("--------------------------");
        for(i=0;i<employees.size();i++){
            if(employees.get(i) instanceof Veterinary){
                System.out.println(count++ + ". Vet");
                System.out.println("empID: " + employees.get(i).getEmpID());
                System.out.println("Gender: " + employees.get(i).getGender());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String date_in_str = formatter.format(employees.get(i).getDateOfBirth());
                System.out.println("Date of Birth: " + date_in_str);
                System.out.println("Has BSc Degree: " + ((Veterinary)employees.get(i)).isBScDegree());
                date_in_str = formatter.format(((Veterinary) employees.get(i)).getDateOfGraduation());
                System.out.println("Date of graduation: " + date_in_str);
                System.out.println("Expertise level: " + ((Veterinary)employees.get(i)).getExpertiseLevel());
                System.out.println("Salary: " + employees.get(i).getSalary());
                System.out.println("");
            }
        }
    }

    /**
     * <p>
     *     display the all farmworker objects in the farm
     * </p>
     */
    public void listFarmWorker(){
        int i,count = 1;
        System.out.println("Information of the Farm Workers in the farm");
        System.out.println("--------------------------");
        for(i=0;i<employees.size();i++){
            if(employees.get(i) instanceof FarmWorker){
                System.out.println(count++ + ". Farm Worker");
                System.out.println("empID: " + employees.get(i).getEmpID());
                System.out.println("Gender: " + employees.get(i).getGender());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String date_in_str = formatter.format(employees.get(i).getDateOfBirth());
                System.out.println("Date of Birth: " + date_in_str);
                System.out.println("Previous farm name: " + ((FarmWorker)employees.get(i)).getPreviousFarmName());
                System.out.println("Work experience: " + ((FarmWorker)employees.get(i)).getWorkexperience());
                System.out.println("Salary: " + employees.get(i).getSalary());
                System.out.println("");
            }
        }
    }

    /**
     * <p>
     *     For getting feeding information of the animal
     * </p>
     * @param tagNo tag number
     * @return response of the operation
     */
    public String feedingAnimal(int tagNo) {
        int i;
        String response = "";
        for (i = 0; i < animals.size(); i++) {
            if (animals.get(i).getTagNo() == tagNo) {
                response +=  "Animal with tagNo: " + animals.get(i).getTagNo() + " feeded with:";
                response += animals.get(i).feeding();
            }
        }
        return response;
    }

    /**
     * <p>
     *     get the salary of the employee with given ID
     * </p>
     * @param empId employee ID of the employee whose salary will be calculated
     * @return salary of the employee who has the given id
     */
    public double getEmpSalary(int empId){
        if(isEmployeeInTheList(empId,employees)){
            int i;
            for(i=0;i<employees.size();i++){
                if(employees.get(i).getEmpID() == empId){
                    return employees.get(i).getSalary();
                }
            }
        }

        return -1;
    }

    /**
     * <p>
     *     For adding milking measurement to an animal
     * </p>
     * @param tagNo tag number
     * @param amount amount of milk
     * @return response of the operation
     */
    public String addMilkingMeasurement(int tagNo, double amount){
        String response = "";
        if(!(isAnimalInTheList(tagNo,animals))){
            response = "There is no employee has tagNo " + tagNo + " in this farm.";
            return response;
        }
        int i;
        for (i=0;i<animals.size();i++){
            if(animals.get(i).getTagNo() == tagNo){
                animals.get(i).addInfoToHashmap(amount);
                response = "Milking measurement is added successfully";
                return response;
            }
        }
        return response;
    }


    /**
     * <p>
     *     Writes, saves the objects and terminates the program
     * </p>
     * @throws IOException
     */
    public void exit() throws IOException {

        // Writes animal objects and MD5 animals

        writeAnimals();

        String serialized_animals = FarmApp.readFile("./animals");
        String enc_serialized_animals = null;
        try {
            enc_serialized_animals = FarmApp.encryptString(serialized_animals);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        writeFile("./md5_animals", enc_serialized_animals);


        // Load animals end employees from the database

        Connection connection = null;
        Statement statement = null;

        try {
            // STEP1 -- Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmappdb", "cng443user", "1234");
            System.out.println("Database connected");

            // STEP 3 -- Create a statement
            statement = connection.createStatement();

            // Select all tag numbers to control tag numbers and not add registered animals again

            ArrayList<Integer> registered_tag_numbers = new ArrayList<Integer>();
            ResultSet resultSet = statement.executeQuery("select tagNo from Animal");

            while (resultSet.next()) {
                registered_tag_numbers.add(resultSet.getInt(1));
            }

            for(int i=0;i<this.animals.size();i++){

                if(!registered_tag_numbers.contains(animals.get(i).getTagNo())){
                    int tagNo = animals.get(i).getTagNo();
                    String gender;
                    int purchased;
                    String type;
                    double Weight = 0;
                    java.sql.Date dateOfBirth = new java.sql.Date(animals.get(i).getDateOfBirth().getTime());

                    if(animals.get(i).getGender().toLowerCase().contains("f")){
                        gender = "f";
                    }
                    else{
                        gender = "m";
                    }

                    if(animals.get(i).isPurchased()){
                        purchased = 1;
                    }
                    else{
                        purchased = 0;
                    }
                    if(animals.get(i) instanceof Cow){
                        type = "c";
                        Weight = ((Cow) animals.get(i)).getWeight();
                    }
                    else{
                        type = "s";
                    }


                    PreparedStatement insertAnimal = connection.prepareStatement("INSERT INTO `Animal`(tagNo,gender,dateOfBirth,purchased,type,Weight) VALUES (?, ?, ?, ?, ?,?)");

                    insertAnimal.setInt(1, tagNo );
                    insertAnimal.setString(2, gender);
                    insertAnimal.setDate(3, dateOfBirth);
                    insertAnimal.setInt(4, purchased);
                    insertAnimal.setString(5,type );
                    insertAnimal.setDouble(6,Weight );
                    insertAnimal.executeUpdate();

                }
            }


            ArrayList<Integer> registered_empIDs = new ArrayList<Integer>();
            ResultSet results = statement.executeQuery("select empID from Employee");

            while (results.next()) {
                registered_empIDs.add(results.getInt(1));
            }

            for(int i=0;i<this.employees.size();i++){

                if(!registered_empIDs.contains(employees.get(i).getEmpID())){
                    int empID = employees.get(i).getEmpID();
                    String gender;
                    java.sql.Date dateOfBirth = new java.sql.Date(employees.get(i).getDateOfBirth().getTime());
                    int BScDegree = 0;
                    java.sql.Date date_of_graduation = new java.sql.Date(employees.get(i).getDateOfBirth().getTime());
                    int expertise_level = 0;
                    String previous_farm_name = "no data";
                    int work_experience = 0;
                    String type = "no data";

                    if(employees.get(i).getGender().toLowerCase().contains("f")){
                        gender = "f";
                    }
                    else{
                        gender = "m";
                    }

                    if(employees.get(i) instanceof Veterinary){
                        if(((Veterinary) employees.get(i)).isBScDegree()){
                            BScDegree = 1;
                        }
                        type = "v";
                        date_of_graduation = new java.sql.Date(((Veterinary) employees.get(i)).getDateOfGraduation().getTime());
                        expertise_level = ((Veterinary) employees.get(i)).getExpertiseLevel();
                    }
                    else{
                        previous_farm_name = ((FarmWorker) employees.get(i)).getPreviousFarmName();
                        work_experience = ((FarmWorker) employees.get(i)).getWorkexperience();
                        type = "f";
                    }


                    PreparedStatement insertEmployee = connection.prepareStatement("INSERT INTO `Employee`(empID,gender,dateOfBirth,type,BScDegree,dateOfGraduation,expertiseLevel,previousFarmName,workExperience) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

                    insertEmployee.setInt(1, empID );
                    insertEmployee.setString(2, gender);
                    insertEmployee.setDate(3, dateOfBirth);
                    insertEmployee.setString(4, type);
                    insertEmployee.setInt(5,BScDegree );
                    insertEmployee.setDate(6,date_of_graduation );
                    insertEmployee.setInt(7,expertise_level );
                    insertEmployee.setString(8,previous_farm_name );
                    insertEmployee.setInt(9,work_experience );

                    insertEmployee.executeUpdate();

                }
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try


        System.exit(0);
    }

    /**
     * <p>
     *     Writes the animals arraylist into a file
     * </p>
     * @throws IOException
     */
    public void writeAnimals() throws IOException {
        File f = new File("./animals");

        if(!f.exists()){
            f.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for(int i=0;i<this.animals.size();i++){
            oos.writeObject(this.animals.get(i));
        }

        oos.close();
        fos.close();

    }

    /**
     * read the given file contents
     * @param filename file name
     * @return content of the file
     */
    public static String readFile(String filename){

        BufferedReader reader = null;
        String line, lines = "";

        try {
            reader = new BufferedReader(new FileReader(filename));
            while((line = reader.readLine()) != null){
                lines += line;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("File is not closed");
        }

        return lines;
    }

    /**
     * writes string into a file
     * @param filename file name
     * @param content string that will be written into the file
     */
    public void writeFile(String filename, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write(content);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     *     Encryptes given string with MD5 Algorithm
     * </p>
     * @param input string input
     * @return encrypted string
     * @throws NoSuchAlgorithmException
     */
    public static String encryptString(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1,messageDigest);

        return bigInt.toString(16);

    }

    /**
     * <p>
     *     It loads animals and employees from datatbase
     * </p>
     */
    public void loadDataFromDatabase(){

        Connection connection = null;
        Statement statement = null;

        try {
            // STEP1 -- Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // STEP 2 -- Establish a connection
            System.out.println("Establishing a connection");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FarmAppDB", "cng443user", "1234");
            System.out.println("Database connected");

            // STEP 3 -- Create a statement
            statement = connection.createStatement();


            // Load Animals

            ResultSet resultSet = statement.executeQuery("select * from Animal");

            int tagNo;
            String gender;
            boolean purchased = false;
            int purch;
            String type;
            double Weight = 0;
            java.util.Date dateOfBirth;
            Animal animal = null;


            while (resultSet.next()) {
                tagNo = resultSet.getInt(1);
                gender = resultSet.getString(2);
                dateOfBirth = new java.sql.Date(resultSet.getDate(3).getTime());
                purch = resultSet.getInt(4);
                type = resultSet.getString(5);
                if(purch == 1){
                    purchased = true;
                }
                else{
                    purchased = false;
                }
                Weight = resultSet.getDouble(6);

                if(type.equals("c")){
                    animal = new Cow(tagNo, gender, dateOfBirth, purchased, Weight);
                }
                else{
                    animal = new Sheep(tagNo, gender, dateOfBirth, purchased);
                }

                this.animals.add(animal);

            }


            // Load Employees

            ResultSet results = statement.executeQuery("select * from Employee");

            int empID;
            String gender_emp;
            java.util.Date dateOfBirth_emp;
            int BScDegree;
            boolean bsc_degree;
            java.util.Date date_of_graduation;
            int expertise_level;
            String previous_farm_name;
            int work_experience;
            String type_emp;
            Employee employee;

            while (results.next()) {
                empID = results.getInt(1);
                gender_emp = results.getString(2);
                dateOfBirth_emp = new java.sql.Date(results.getDate(3).getTime());
                type_emp = results.getString(4);
                BScDegree = results.getInt(5);
                if(BScDegree == 1){
                    bsc_degree = true;
                }
                else{
                    bsc_degree = false;
                }
                date_of_graduation = new java.sql.Date(results.getDate(6).getTime());
                expertise_level = results.getInt(7);
                previous_farm_name = results.getString(8);
                work_experience = results.getInt(9);

                if(type_emp.equals("v")){
                    employee = new Veterinary(empID, gender_emp, dateOfBirth_emp, bsc_degree, date_of_graduation, expertise_level);
                }
                else{
                    employee = new FarmWorker(empID, gender_emp, dateOfBirth_emp, previous_farm_name, work_experience);
                }

                this.employees.add(employee);
            }


        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }


    /**
     * <p>
     *     This is the main class of the program. I did command line interface with this class and make the user can try all the functions.
     * </p>
     * @param args string array used for taking inputs from the command line
     */
    public static void main(String[] args) throws IOException {

        FarmApp farm = new FarmApp();

        File f1 = new File("./md5_animals");
        File f2 = new File("./animals");

        if(f1.exists() && f2.exists()){
            CheckStoredAnimals t1 = new CheckStoredAnimals();
            Thread checkAnimals = new Thread(t1);
            checkAnimals.start();
        }

        GUIThread t2 = new GUIThread(farm);
        Thread runGUI = new Thread(t2);



        runGUI.start();

    }
}