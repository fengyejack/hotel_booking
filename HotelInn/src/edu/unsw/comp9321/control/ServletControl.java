package edu.unsw.comp9321.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerletControl
 */
@WebServlet("/ServletControl")
public final class ServletControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> commands;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("YOU ARE AWESOME!");
		super.init(config);
		commands = new HashMap<String, Command>();
		
		commands.put("PAGE_NOT_FOUND", new Error());
		commands.put("search", new Search());
		commands.put("confirm", new Confirm());
		commands.put("book", new Book());
		commands.put("URL", new URL());
		commands.put("validate", new Validate());
		commands.put("delete", new Delete());
		commands.put("add", new Add());
		commands.put("loginin", new Loginin());
		commands.put("assign", new AssignRooms());
		commands.put("checksout", new Checksout());
		commands.put("decideroom", new Decideroom());
		commands.put("Ownerloginin", new logininOwner());
		commands.put("discount", new discount());	
	}
	
	private final void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Command cmd = resolveCommand(request);
		String next = cmd.execute(request, response);
		System.out.println("Next = "+ next);
		while (next.indexOf('.') < 0) {
			cmd = (Command) commands.get(next);
			next = cmd.execute(request, response);
		}	
		System.out.println("Next = "+next);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(next);
		dispatcher.forward(request, response);
	}
			
	private Command resolveCommand(HttpServletRequest request) {
		Command cmd = (Command) commands.get(request.getParameter("control"));
		if (cmd == null) {
			cmd = (Command) commands.get("PAGE_NOT_FOUND");
		}
		return cmd;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
