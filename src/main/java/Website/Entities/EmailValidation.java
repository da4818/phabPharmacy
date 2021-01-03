package Website.Entities;
import java.util.Arrays;
import java.util.List;

public class EmailValidation extends Email{
    public String password;
    public String passwordCheck;

    public EmailValidation(String email, String password, String passwordCheck){
        super(email);
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
    public String validPassword(){
        if (password.equals(passwordCheck)){
            return "Valid password";
        }
        else{
            return "Invalid password";
        }
    }

    public boolean validLogin(){
        if(validEmail()){
            return true;
        }
        else{
            return false;
        }
    }
}
