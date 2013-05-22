package util;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
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
    
    //public static void main(String[] args) throws Exception
    //{
    //    String s = convertCurrency("EUR", "USD", "15,50");
    //    System.out.println("Result: " + s);
    //}
      
    public static String convert(String from, String to, String amount) throws MalformedURLException, IOException{
        //Correct link: 
        //http://www.google.com/ig/calculator?hl=en&q=1GBP=?USD
        
        String google = "http://www.google.com/ig/calculator?hl=en&q=";
        String charset = "UTF-8";
        
        URL url = new URL(google + amount + from + "=?" + to);
        Reader reader = new InputStreamReader(url.openStream(), charset);
        Result result = new Gson().fromJson(reader, Result.class);
        
        String convertedResult = result.getRhs().split("\\s+")[0];
        return convertedResult;
        
    }
    
    public static String convertCurrency(String baseCurrency, String termCurrency, String amount) throws MalformedURLException, IOException
    {
        return convert(baseCurrency, termCurrency, amount);
    }
    
    public static String convertEURtoUSD(String amount) throws MalformedURLException, IOException{
        return convertCurrency("EUR", "USD", amount);
    }
    
    public static String convertUSDtoEUR(String amount) throws MalformedURLException, IOException{
        return convertCurrency("USD", "EUR", amount);
    }
}
