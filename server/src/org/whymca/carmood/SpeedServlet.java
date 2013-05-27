package org.whymca.carmood;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class SpeedServlet
 */
@WebServlet("/SpeedServlet")
public class SpeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init() throws ServletException {		
		super.init();
		new Thread(new SpeedPoller()).start();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Content-Type", "application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
		CarData cd = new CarData();
		cd.load();
		JSONObject json = new JSONObject(cd);
		try  {
			json.write(response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
	}
}
