package ru.meowth.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.meowth.domain.DeliverySpan;
import ru.meowth.domain.Letter;
import ru.meowth.services.LetterService;

@SuppressWarnings("serial")
public class PostMyLetterServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("/post.get()");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {			
		final String body = req.getParameter("message");
		final String email = req.getParameter("email");
		final DeliverySpan span = convertToSpan(req.getParameter("timespan"));
		
		new LetterService().saveLetter(new Letter(body, email, span));
		
		req.getSession().setAttribute("#marker", "a");
		resp.sendRedirect("/");
	}

	
	private DeliverySpan convertToSpan(String parameter) {
		if(parameter.endsWith("1m")) {
			return DeliverySpan.VerySoon;
		}
		
		if(parameter.endsWith("3m")) {
			return DeliverySpan.Soon;
		}
		
		if(parameter.endsWith("6m")) {
			return DeliverySpan.RelativelySoon;
		}
		
		if(parameter.endsWith("1y")) {
			return DeliverySpan.NotSoSoon;
		}
		
		if(parameter.endsWith("3y")) {
			return DeliverySpan.AlmostNever;
		}

		return DeliverySpan.VerySoon;
	}
}
