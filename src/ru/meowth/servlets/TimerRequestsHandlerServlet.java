package ru.meowth.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import ru.meowth.domain.Letter;
import ru.meowth.services.LetterService;
import ru.meowth.services.MailSenderService;

@SuppressWarnings("serial")
public class TimerRequestsHandlerServlet extends HttpServlet {
	/**
	 * Called by cron timer
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
				
		MailSenderService sender = new MailSenderService();
		
		List<Letter> letters = new LetterService().getLettersToSend();		
		for(Letter letter : letters) {
			sender.sendMail(letter);
		}
	}
}
