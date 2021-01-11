package Website.Entities;

import java.util.ArrayList;

public class Address {
    public String address;
    public String postcode;

    public Address(String address, String postcode){
        this.address = address;
        if (postcode.length() > 0 && postcode.indexOf(' ') == -1){
            String temp = postcode.substring(0,postcode.length()-3) + " " +postcode.substring(postcode.length()-3);
            this.postcode = temp.toUpperCase();
        }
        else{
            this.postcode = postcode.toUpperCase();
        }

    }

    /*public String getAddress(){
        String lines[] = address.split("\\r?\\n");
    }*/

    // This checks for the correct postcode format - e.g. SW 72AZ is invalid but SW72AZ and SW7 2AZ is valid
    // The space can be either the 3rd, 4th or 5th position of the postcode (e.g. 3rd in L1 1JJ, 4th in SW7 2AZ and 5th in SW1A 1AA)
    // but it will always be 3rd from the end (the final character non-inclusive)
    public boolean validPostcode(){
        if(!isAlphaNumeric(postcode)){
            return false;
        }
        return postcode.indexOf(' ') == postcode.length() - 4;
    }

    public boolean isAlphaNumeric(String input){
        for (char c:input.toCharArray()){
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != ' '){
                return false;
            }
        }
        return true;
    }
}
