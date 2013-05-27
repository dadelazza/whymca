package org.whymca.carmood;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Servlet implementation class MusicServlet
 */
@WebServlet("/MusicServlet")
public class MusicServlet extends WeatherServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MusicServlet() {
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
		
		String weather = request.getParameter("weather");
		String mood = WeatherMoodMatcher.getMoodByWeather(weather);
		JSONObject ret = runStereomoodReq(mood);
		try {
			ret.write(response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JSONObject runStereomoodReq(String mood) {
		OAuthService service = new ServiceBuilder().signatureType(SignatureType.QueryString)
			.provider(StereoMoodOAuthApi.class).apiKey("8e8ce6853c6c404148ffb3c5b21843a1051a0a1a7").apiSecret("98b35e0ae14ef462a0ad411cd4187430").build();
		
		Token access = new Token("c02294106955dab13e762b8442b8a541051a0b017", "05a29c608503c9136a759fee43b630f1");
		
		try {
			OAuthRequest requesto = new OAuthRequest(Verb.GET, "http://www.stereomood.com/api/search.json?q=" + URLEncoder.encode(mood, "UTF-8") + "&type=mood");
			service.signRequest(access, requesto);
			Response resp = requesto.send();
			JSONObject ret = new JSONObject(resp.getBody());
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void doOptions(HttpServletRequest request, HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
	}
}
