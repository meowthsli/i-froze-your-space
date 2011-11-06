package ru.meowth.servlets;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		resp.sendError(404);		
	}
}
