package com.rh.manager.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rh.manager.dao.AccessLogDao;
import com.rh.manager.dao.AuthDao;
import com.rh.manager.model.Usuario;

@WebServlet("/ServletAuth")
public class ServletAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletAuth() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
			
		if (action.equals("logout")) {
			logout(request, response);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null)
			return;

		switch (action) {
		case "login":
			login(request, response);
			return;
		case "logout":
			logout(request, response);
			return;
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String erro = null;
		String nextPage = null;

		// Validate email
		if (email == null || email.trim().isEmpty()) {
			erro = "Você precisa digitar um email";
			nextPage = "/login.jsp";
			return;
		}

		// Validate password
		if (password == null || password.trim().isEmpty()) {
			erro = "Você precisa digitar uma senha";
			nextPage = "/login.jsp";
			return;
		}

		AuthDao authDao = new AuthDao();
		Usuario user = null;

		try {
			// Tenta realizar login do usuário
			user = authDao.login(email, password);
		} catch (SQLException e) {
			erro = "Erro ao tentar realizar login";
			e.printStackTrace();
		}

		if (user == null) {
			erro = "Usuário ou senha estão incorretos";
			nextPage = "/login.jsp";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// Cria um cookie para armazenar o ID da sessão
			Cookie sessionCookie = new Cookie("session", session.getId());
			response.addCookie(sessionCookie);

			AccessLogDao accessLogDao = new AccessLogDao();

			// Pega a quantidade de accessos a página do menu principal e salva em um cookie
			try {
				String menuQuantityAccess = Integer
						.toString(accessLogDao.getAccessQuantity("/manager/ServletAuth", user.getId()));
				
				System.out.println(menuQuantityAccess);

				Cookie menuQuantityAccessCookie = new Cookie("menuQuantityAccess", menuQuantityAccess);
				response.addCookie(menuQuantityAccessCookie);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			nextPage = "/index.jsp";
		}

		request.setAttribute("erro", erro);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		// Invalidar sessão
		session.invalidate();

		// Remover cookie da sessão
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("session")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					break;
				}
			}
		}

		// Redirect to login
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
}
