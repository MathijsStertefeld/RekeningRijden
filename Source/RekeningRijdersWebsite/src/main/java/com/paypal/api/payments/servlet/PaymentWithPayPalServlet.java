// #Create Payment Using PayPal Sample
// This sample code demonstrates how you can process a 
// PayPal Account based Payment.
// API used: /v1/payments/payment
package com.paypal.api.payments.servlet;

import com.marbl.administration.domain.Bill;
import com.paypal.api.payments.*;
import com.paypal.api.payments.util.*;
import com.paypal.core.rest.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import com.marbl.rekeningrijders.website.service.RekeningRijdersService;
import com.marbl.rekeningrijders.util.GoogleConverter;

/**
 * @author lvairamani
 *
 */
@Stateless
public class PaymentWithPayPalServlet extends HttpServlet {
    
    @Inject
    private RekeningRijdersService service;

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger
            .getLogger(PaymentWithPayPalServlet.class);
    Map<String, String> map = new HashMap<String, String>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // ##Load Configuration
        // Load SDK configuration for
        // the resource. This intialization code can be
        // done as Init Servlet.
        InputStream is = PaymentWithPayPalServlet.class
                .getResourceAsStream("/sdk_config.properties");
        try {
            PayPalResource.initConfig(is);
        } catch (PayPalRESTException e) {
            LOGGER.fatal(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    // ##Create
    // Sample showing to create a Payment using PayPal
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // ###AccessToken
        // Retrieve the access token from
        // OAuthTokenCredential by passing in
        // ClientID and ClientSecret
        APIContext apiContext = null;
        String accessToken;
        try {
            accessToken = GenerateAccessToken.getAccessToken();

            // ### Api Context
            // Pass in a `ApiContext` object to authenticate 
            // the call and to send a unique request id 
            // (that ensures idempotency). The SDK generates
            // a request id if you do not pass one explicitly. 
            apiContext = new APIContext(accessToken);
            // Use this variant if you want to pass in a request id  
            // that is meaningful in your application, ideally 
            // a order id.
			/* 
             * String requestId = Long.toString(System.nanoTime();
             * APIContext apiContext = new APIContext(accessToken, requestId ));
             */
        } catch (PayPalRESTException e) {
            req.setAttribute("error", e.getMessage());
        }
        if (req.getParameter("PayerID") != null) {
            Payment payment = new Payment();
            if (req.getParameter("guid") != null) {
                payment.setId(map.get(req.getParameter("guid")));
            }

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(req.getParameter("PayerID"));
            try {
                payment.execute(apiContext, paymentExecution);
                req.setAttribute("response", Payment.getLastResponse());
            } catch (PayPalRESTException e) {
                req.setAttribute("error", e.getMessage());
            }
        } else {
            Long billId = Long.parseLong(req.getParameter("billID"));
            Bill bill = service.findBill(billId);
            
            //bill.getPaymentAmount();
            String convertedAmountString = GoogleConverter.convertEURtoUSD(Double.toString(bill.getPaymentAmount()));
            Double convertedAmount = Double.parseDouble(convertedAmountString);        
            DecimalFormat df = new DecimalFormat("#0.00");
            String total = df.format(convertedAmount);
            //System.out.println(total);
            
            // ###AmountDetails
            // Let's you specify details of a payment amount.
            AmountDetails amountDetails = new AmountDetails();
            amountDetails.setShipping("0");
            amountDetails.setSubtotal(total);
            amountDetails.setTax("0");

            // ###Amount
            // Let's you specify a payment amount.
            Amount amount = new Amount();
            amount.setCurrency("USD");
            // Total must be equal to sum of shipping, tax and subtotal.
            amount.setTotal(total);
            amount.setDetails(amountDetails);

            // ###Transaction
            // A transaction defines the contract of a
            // payment - what is the payment for and who
            // is fulfilling it. Transaction is created with
            // a `Payee` and `Amount` types
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction
                    .setDescription("This is the payment transaction description.");

            // The Payment creation API requires a list of
            // Transaction; add the created `Transaction`
            // to a List
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.add(transaction);

            // ###Payer
            // A resource representing a Payer that funds a payment
            // Payment Method
            // as 'paypal'
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            // ###Payment
            // A Payment Resource; create one using
            // the above types and intent as 'sale'
            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            // ###Redirect URLs
            RedirectUrls redirectUrls = new RedirectUrls();
            String guid = UUID.randomUUID().toString().replaceAll("-", "");
            redirectUrls.setCancelUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
            redirectUrls.setReturnUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
            payment.setRedirectUrls(redirectUrls);

            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            try {
                Payment createdPayment = payment.create(apiContext);
                LOGGER.info("Created payment with id = "
                        + createdPayment.getId() + " and status = "
                        + createdPayment.getState());
                // ###Payment Approval Url
                Iterator<Link> links = createdPayment.getLinks().iterator();
                while (links.hasNext()) {
                    Link link = links.next();
                    if (link.getRel().equalsIgnoreCase("approval_url")) {
                        req.setAttribute("redirectURL", link.getHref());
                    }
                }
                req.setAttribute("response", Payment.getLastResponse());
                map.put(guid, createdPayment.getId());
            } catch (PayPalRESTException e) {
                req.setAttribute("error", e.getMessage());
            }
        }
        req.setAttribute("request", Payment.getLastRequest());
        req.getRequestDispatcher("response.jsp").forward(req, resp);
    }
}
