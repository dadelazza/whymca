package org.whymca.carmood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet implementation class WeatherServlet
 */
@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String clientId = "129380043";
	private static final String clientSecret = "-umApL-OnW1TmAonPtV8Awl-XlLvgb8NMuJh9jOJ";
	private static final String token = "MTI5MzgwMDQzOi11bUFwTC1PblcxVG1Bb25QdFY4QXdsLVhsTHZnYjhOTXVKaDlqT0o=";
	
	static {
		TrustManager[] trustAllCerts = new TrustManager[]{
	        new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	            public void checkClientTrusted(
	                java.security.cert.X509Certificate[] certs, String authType) {
	            }
	            public void checkServerTrusted(
	                java.security.cert.X509Certificate[] certs, String authType) {
	            }
	        }
	    };

	    // Install the all-trusting trust manager
	    try {
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	    } catch (Exception e) {
	    }
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeatherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Content-Type", "application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
		JSONObject weatherData = runMetwitReq(
				Double.parseDouble(request.getParameter("lat")), 
				Double.parseDouble(request.getParameter("lon")));	
		try {
			JSONArray objects = weatherData.getJSONArray("objects");
			JSONObject weather = objects.getJSONObject(0);
			weather.write(response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected WeatherObject getCurrentWeather(Double lat, Double lon) {
		JSONObject weatherData = runMetwitReq(lat, lon);
					
		try {
			JSONArray objects = weatherData.getJSONArray("objects");
			JSONObject weather = objects.getJSONObject(0);
			return new WeatherObject(weather);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected JSONObject runMetwitReq(Double lat, Double lon) {		
		try {
			URL url = new URL("https://api.metwit.com/v2/weather/?location_lat=" +  lat + "&location_lng=" + lon);
			HttpsURLConnection conn  = (HttpsURLConnection)url.openConnection();	
			conn.addRequestProperty("Authorization", "Basic " + token);
			conn.setHostnameVerifier(new TrustAllHostNameVerifier ());
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String resp = "";
			while((line = br.readLine()) != null) {
				resp += line;
			}
			
			System.out.println(resp);
			
			return new JSONObject(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
	}
	
	public class TrustAllHostNameVerifier implements HostnameVerifier {

	    public boolean verify(String hostname, SSLSession session) {
	        return true;
	    }

	}
}
