package Website.Entities;

public class CreditCard {
    public String cardNumber;
    public String cvv;
    public String sortCode;
    public String accountNumber;

    public CreditCard(String cardNumber, String cvv, String sortCode, String accountNumber){
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
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
