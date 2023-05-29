package tn.arabsoft.spring.controllers;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.arabsoft.spring.entities.*;
import com.stripe.model.checkout.Session;
import tn.arabsoft.spring.repositories.DeclarationIRPPRepo;
import tn.arabsoft.spring.repositories.DeclarationTFRepo;
import tn.arabsoft.spring.repositories.DeclarationTVARepo;
import tn.arabsoft.spring.services.DeclarationIRPPService;
import tn.arabsoft.spring.services.DeclarationTFService;
import tn.arabsoft.spring.services.DeclarationTVAService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
//@RequestMapping(value = "/api")
public class StripeController {

    @Autowired
    DeclarationIRPPService declarationIRPPService ;
    @Autowired
    DeclarationIRPPRepo declarationIRPPRepo ;

    @Autowired
    DeclarationTVAService declarationTVAService ;
    @Autowired
    DeclarationTVARepo declarationTVARepo ;
    @Autowired
    DeclarationTFService declarationTFService ;
    @Autowired
    DeclarationTFRepo declarationTFRepo ;

    private static Gson gson = new Gson();
    @PostMapping("/payment")
    /**
     * Payment with Stripe checkout page
     *
     * @param payment
     * @return
     * @throws StripeException
     */
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        // We initilize stripe object with the api key
        init();
        System.out.println();
        // We create a stripe session
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
System.out.println(params);


        System.out.println("wael haw payment" +payment );
        if(payment.getTypedec().equals("IRPP")){
            DeclarationIRPP declarationIRPP = declarationIRPPService.getDeclarationIRPPById(payment.getDecId()) ;
            declarationIRPP.setSituationFiscale("payee");
            declarationIRPPRepo.save(declarationIRPP) ;
        }
        else if (payment.getTypedec().equals("TVA")) {
            DeclarationTVA declarationTVA = declarationTVAService.getDeclarationTVAById(payment.getDecId()) ;
            declarationTVA.setSituationFiscale("payee");
            declarationTVARepo.save(declarationTVA) ;
        }
        else if (payment.getTypedec().equals("TF")) {
            DeclarationTF declarationTF = declarationTFService.getDeclarationTFById(payment.getDecId()) ;
            declarationTF.setSituationFiscale("payee");
            declarationTFRepo.save(declarationTF) ;
        }


        Session session = Session.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());

        return gson.toJson(responseData);
    }

    @PostMapping("/subscription")
    /**
     * Used to create a subscription with strpe checkout page
     *
     * @param checkout
     * @return the subscription id
     * @throws StripeException
     */
    public String subscriptionWithCheckoutPage(@RequestBody Checkout checkout) throws StripeException {
        SessionCreateParams params = new SessionCreateParams.Builder().setSuccessUrl(checkout.getSuccessUrl())
                .setCancelUrl(checkout.getCancelUrl()).addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION).addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1L).setPrice(checkout.getPriceId()).build())
                .build();

        try {
            Session session = Session.create(params);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            return gson.toJson(responseData);
        } catch (Exception e) {
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("message", e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("error", messageData);
            return gson.toJson(responseData);
        }
    }


    private static void init() {
        Stripe.apiKey = "sk_test_51N8vbeD7kz8pNZUfgUdECsRytn9qGsUqMVMD0MqIxQekJkLV5oe1yiBhEcjNBarw1FwYxqK0GcyCzOovvtNbAYjc00OUIbXwGL";
    }


}
