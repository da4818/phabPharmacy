package Website.Entities;

import java.util.ArrayList;

public class Address {
    public String address;
    public String postcode;

    public Address(String address, String postcode){
        this.address = address;
        if (postcode.indexOf(' ') == -1){
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
    public boolean validPostcode(){
        if(!isAlphaNumeric(postcode)){
            return false;
        }
        if (postcode.indexOf(' ') == postcode.length()-4){
            return true;
        }
        else {
            return false;
        }
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
