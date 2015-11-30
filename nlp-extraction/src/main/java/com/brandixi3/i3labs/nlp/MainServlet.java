package com.brandixi3.i3labs.nlp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class MainServlet extends HttpServlet {

	public void init() throws ServletException {
		  Thread thread = new Thread(new Main());
		  thread.start();
	}
}
