package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Todo;
import repository.TodoRepository;

@WebServlet("/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TodoRepository repository = TodoRepository.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * HTTP Message
	 * POST /todo-app/TodoControllerServlet HTTP/1.1
	 * Content-Length: 25
	 * Content-Type: application/x-www-form-urlencoded
	 * 
	 * whattodo=do nothing&myname=yoonji je
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String whatTodo = request.getParameter("whattodo"); // do something
		 String myName = request.getParameter("myname");
		 final Todo todo =  new Todo(whatTodo, myName);
		 repository.saveTodo(todo); //todo 저장 해줘!!
		 /**
		  * HTTP/1.1 302 Found
		  * Location: index.jsp
		  * 
		  * Location에 명시된 곳으로 이동하라고 브라우저에게 명령
		  */
		 response.sendRedirect("index.jsp");
		 
	}

}
