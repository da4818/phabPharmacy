package Website.Functions;

import com.sendgrid.*;
import Website.Entities.Product;
import java.io.IOException;
import java.util.List;
//This class is used to send an Email to the wholesaler containing a list of products which the pharmacy wishes to purchase
public class MailSender {
    public MailSender(List<Product> products){
        Email from = new Email("phabpharmacy@gmail.com");
        String message = new String();
        //Creating a message containing a formatted list of products
        for (int i = 0; i<products.size(); i++){
            message = message + "name: " + products.get(i).name + " brand: " + products.get(i).brand + " quantity: " + products.get(i).change + "\n";
        }
        //Setting up email subject and recipient
        String subject = "PhabPharmacy Order";
        Email to = new Email("thewholesalerpharmacy@gmail.com");
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("phabpharmacy"));
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println(response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            try {
                throw ex;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

