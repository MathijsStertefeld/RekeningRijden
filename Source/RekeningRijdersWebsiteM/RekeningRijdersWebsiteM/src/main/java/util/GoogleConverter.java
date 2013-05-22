package util;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleConverter
{
    static class Result
    {
        private String lhs;
        private String rhs;
        public String getLhs()
        {
            return lhs;
        }
        public String getRhs()
        {
            return rhs;
        }
        public void setLhs(String lhs)
        {
            this.lhs = lhs;
        }
        public void setRhs(String rhs)
        {
            this.rhs = rhs;
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        String s = convertCurrency("EUR", "USD", "15,50");
        System.out.println("Result: " + s);
    }
    
    public static String convertCurrency(String baseCurrency, String termCurrency, String amount) throws MalformedURLException, IOException
    {
        //Correct link: 
        //http://www.google.com/ig/calculator?hl=en&q=1GBP=?USD
        
        String _google = "http://www.google.com/ig/calculator?hl=en&q=";
        String _charset = "UTF-8";
             
        URL _url = new URL(_google + amount + baseCurrency + "=?" + termCurrency);
        Reader _reader = new InputStreamReader(_url.openStream(), _charset);
        Result _result = new Gson().fromJson(_reader, Result.class);
        
        String _convertedAmount = _result.getRhs().split("\\s+")[0];
        
        return _convertedAmount;
    }
    
    public static String convertEURtoUSD(String amount) throws MalformedURLException, IOException{
        return convertCurrency("EUR", "USD", amount);
    }
    
    public static String convertUSDtoEUR(String amount) throws MalformedURLException, IOException{
        return convertCurrency("USD", "EUR", amount);
    }
}
