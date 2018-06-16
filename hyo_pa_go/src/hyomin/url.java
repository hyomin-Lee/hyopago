package hyomin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class url extends GoogleVisionApiTester {
    
    public void connection(){
    	String clientId = "jmBmp4U9s6g_nFSUREcv";
        String clientSecret = "pwTp1Ew6H9";
        String value = super.OCR_STRING; // use extends
        String src_target;
        
        if (super.num == 1) {
        	src_target = "source=en&target=ko&text=";
        }
        else {
        	src_target = "source=ko&target=en&text=";
        }
        
        try {
            String text = URLEncoder.encode(value, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = src_target + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            
            int idx = (response.toString()).indexOf("\"translatedText\":\"");
            String slice = (response.toString()).substring(idx+18);
            idx = (slice).indexOf("\"}}}");
            slice = (slice).substring(0,idx);
            
            System.out.print("[*] Translation result : ");
            System.out.println(new String(slice.getBytes("ksc5601"), "euc-kr"));
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
    

