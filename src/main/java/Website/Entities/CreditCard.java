package Website.Entities;

public class CreditCard {
    public String cardNumber;
    public String cvv;
    public String sortCode;
    public String accountNumber;

    public CreditCard(){
    }
    public CreditCard(String cardNumber, String cvv, String sortCode, String accountNumber){
        if (cardNumber.indexOf(' ') > 0){ // People input their card number in the xxxx xxxx xxxx xxxx or xxxxxxxxxxxxxxxx format - we want the data to be stored in a consistent format, so will we store the data into the DB in latter format
            String temp = cardNumber.substring(0,4) + cardNumber.substring(5,9) + cardNumber.substring(10,14) + cardNumber.substring(cardNumber.length()-4);
            this.cardNumber = temp;
        }
        else{
            this.cardNumber = cardNumber;
        }
        this.cvv = cvv;
        this.accountNumber = accountNumber;

        if (sortCode.indexOf('-') > 0){ // People input their sortcode in the xx-xx-xx or xxxxxx format - we want the data to be stored in a consistent format, so will we store the data into the DB in  latter format
            String temp = sortCode.substring(0,2) + sortCode.substring(3,5) +sortCode.substring(sortCode.length()-2);
            this.sortCode = temp;
        }
        else{
            this.sortCode = sortCode;
        }


    }

    public boolean validCardNumber(){
        if (cardNumber.length() == 16){
            if (!isNumeric(cardNumber)){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    public boolean validAccountNumber(){
        if (accountNumber.length() == 8){
            if (!isNumeric(accountNumber)){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    public boolean validSortCode(){
        if (sortCode.length() == 6){
            if (!isNumeric(sortCode)){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    public boolean validCvv(){
        if (cvv.length() == 3){
            if (!isNumeric(cvv)){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }

    public String getCensoredCardNumber(){
        String out = cardNumber.substring(0,cardNumber.length()-4);
        out += "xxxx";
        return out;
    }
    public String getSortCode(){
        String out = sortCode.substring(0,2);
        out += "-";
        out += sortCode.substring(2,4);
        out += "-";
        out += sortCode.substring(sortCode.length()-2);
        return out;
    }

    public boolean isNumeric(String input){
        for (char c:input.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
