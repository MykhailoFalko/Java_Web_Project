package com.example.FinalProject.web;

import com.example.FinalProject.db.DBException;
import com.example.FinalProject.web.command.Command;
import com.example.FinalProject.web.command.CommandContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final Logger log= LogManager.getLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("Controller#doGet");

		String address = "error.jsp";

		String commandName = req.getParameter("command");
		log.trace("commandName ==> " + commandName);
		
		Command command = CommandContainer.getCommand(commandName);
		log.trace("command ==> " + command);
		
		try {
			address = command.execute(req, resp);
		} catch (DBException ex) {
			req.setAttribute("error", ex);
		}

		log.debug("address == > " + address);
		req.getRequestDispatcher(address).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.debug("Controller#doPost");

		String address = "error.jsp";
		String commandName = req.getParameter("command");
		log.trace("commandName ==> " + commandName);
		Command command = CommandContainer.getCommand(commandName);
		log.trace("command ==> " + command);
		try {
			address = command.execute(req, resp);
		} catch (DBException ex) {
			req.getSession().setAttribute("error", ex);
		}
		log.debug("address == > " + address);
		resp.sendRedirect(address);
	}

}
