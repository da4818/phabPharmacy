package Website.Entities;
import java.util.Arrays;
import java.util.List;

public class Email {
    public String email;
    public String prefix;
    public String domain;
    public String subDomain;
    List<String> validDomains = Arrays.asList("gmail", "googlemail","yahoo","hotmail","outlook","btinternet");
    List<String> validSubDomains = Arrays.asList("com", "co.uk","fr","net","org");
    List<Character> invalidChars = Arrays.asList('!','#','$','%','&','\'','*','+','/','=','?','^','`','{','|','(',')');
    List<Character> specialChars = Arrays.asList('_','.','-');
    public Email(String email){
        this.email = email;
        //check that the '@' sign if present in the user's input
        if (email.indexOf('@') > 0){
            this.prefix = email.substring(0,email.indexOf("@"));
            String temp = email.substring(email.indexOf('@')+1);
            this.domain = temp.substring(0, temp.indexOf("."));
            this.subDomain = temp.substring(temp.indexOf(".")+1);
        }
        else{
            this.prefix = null;
            this.domain = null;
            this.subDomain = null;
        }
    }
    public String getEmailInfo(){
        return "Email: "+ prefix+ " & " +domain+ " & " +subDomain;
    }
    public boolean validEmail(){
        // Values become null when no '@' is entered,
        if (prefix == null || domain == null || subDomain == null){
            return false;
        }
        else if (email.length() > 64){
            return false;
        }
        else if(prefix.length() > 1){
            if(validPrefix() && validDomain() && validSubDomain()){
                return true;
            }
            else{
                //String chars = getSpecialChars();
                return false;//\nValid emails must:\n- not contain consecutive special characters\n- not begin with a special character\n- not contain special characters:" +chars;
            }
        }
        else{
            return false;//\nEmails must:\n- not be longer than 64 characters\n- have a prefix of 2+ characters";
        }
    }
    public boolean validDomain(){
        for (String s:validDomains){
            if(domain.equals(s)){
                return true;
            }
        }
        return false;
    }
    public boolean validSubDomain(){
        for (String s:validSubDomains){
            if(subDomain.equals(s)){
                return true;
            }
        }
        return false;
    }

    public boolean validPrefix(){
        //check: special characters are not allowed
        if(hasInvalidChars()){
            return false;
        }
        else{
            // check: prefixes can only start with letters or numbers
            if(!Character.isLetterOrDigit(prefix.charAt(0))){
                return false;
            }
            else{ // check for: special character must be followed by letter or number //
                for (char c:specialChars){
                    int n = prefix.indexOf(c);
                    while(n >= 0){
                        // if special char is in the final string position i.e. - the special char is followed by the @ sign
                        if (n == prefix.length()-1){
                            return false;
                        }
                        // If a special char is present, but not the final char-if a letter or number doesn't follow the special char
                        else if (!Character.isLetterOrDigit(prefix.charAt(n+1))){
                            return false;
                        }
                        n = prefix.indexOf(c,n+1);
                    }
                }
                return true;
            }
        }



    }
    public String getSpecialChars(){
        String out  = "";
        for(char c:invalidChars){
            out+= c + " ";
        }
        return out;
    }

    public boolean hasInvalidChars(){
        for(char c:prefix.toCharArray()){
            if(!Character.isLetterOrDigit(c) && c !='.' && c!='-' && c !='_'){
                return true;
            }
        }
        return false;
    }
    public String getErrorMessage(){
        String out = "<p>Valid emails must: <br>- not contain consecutive special characters<br>- not begin with a special character '_', '.' or '-'<br>- not contain any of the following characters: " +getSpecialChars() +"</p></h2>";
            return out;
    }
}
