package Website.Entities;

public class CreditCard {
    public String cardNumber;
    public String cvv;
    public String sortCode;
    public String accountNumber;

    public CreditCard(String cardNumber, String cvv, String sortCode, String accountNumber){
        this.cardNumber = cardNumber.replaceAll("\\s",""); //Removes any spaces
        this.cvv = cvv;
        //If the user inputs their sort code as xx-xx-xx, it will be trimmed to remove all '-'s
        if(sortCode.indexOf('-') > 0){
            this.sortCode = sortCode.replace("-","");
        }
        else {
            this.sortCode = sortCode;
        }
        this.accountNumber = accountNumber;
    }
    //Checks the cardnumber is the correct length, and is only number
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

    public String getSortCode() {
        return sortCode;
    }

    public String getCardNumber() {
        return cardNumber;
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
    public String getCensoredCardNumber(){
        String out = cardNumber.substring(0,cardNumber.length()-4);
        out +="xxxx";
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
