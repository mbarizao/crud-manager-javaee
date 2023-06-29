package com.rh.manager.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rh.manager.dao.AccessLogDao;
import com.rh.manager.model.AccessLog;
import com.rh.manager.model.Usuario;

@WebFilter("/*")
public class SessionFilter implements Filter {
	public SessionFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		Usuario user = null;
		user = (Usuario) session.getAttribute("user");

		String url = req.getRequestURI();
		String enderecoIp = req.getRemoteAddr();

		// Adiciona log no banco
		try {
			// Não registra o log caso o final da url for um .css
			if (url.endsWith(".css"))
				return;

			AccessLog accessLog = new AccessLog();

			accessLog.setPagina(url);
			accessLog.setEnderecoIP(enderecoIp);
			
			if (user != null) {
				accessLog.setUsuarioID(user.getId());
			}

			AccessLogDao accessLogDao = new AccessLogDao();

			accessLogDao.add(accessLog);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Controle de acesso
		if (url.endsWith("login.jsp") || url.endsWith("ServletAuth")) {

			// Se o usuário estiver logado ele será redirecionado para index caso tente
			// acessar a página de login
			if (user != null && url.endsWith("login.jsp")) {
				resp.sendRedirect("/manager/index.jsp");
				return;
			}

			chain.doFilter(request, response);
		} else {
			if (user == null) {
				resp.sendRedirect("/manager/login.jsp");
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
