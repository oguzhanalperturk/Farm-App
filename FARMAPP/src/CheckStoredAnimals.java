import java.security.NoSuchAlgorithmException;

public class CheckStoredAnimals implements Runnable{

    @Override
    public void run() {
        String old_enc_serialized_animals = FarmApp.readFile("./md5_animals");
        String serialized_animals =  FarmApp.readFile("./animals");
        String new_enc_serialized_animals = "";
        String response = "";
        try {
            new_enc_serialized_animals = FarmApp.encryptString(serialized_animals);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        if(old_enc_serialized_animals.equals(new_enc_serialized_animals)){
            response = "Serialized objects are not modified !!";
        }
        else{
            response = "Serialized objects has been changed !!";
        }
        new MessageBoxGUI(response);
    }
}
