package com.rh.manager.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rh.manager.dao.AuthDao;
import com.rh.manager.dao.UsuarioDao;
import com.rh.manager.model.Usuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletUsuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Get
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		// Delete button trigger
		if (action != null && action.equals("delete")) {
			doDelete(request, response);
			return;
		}

		// Edit button trigger
		if (action != null && action.equals("edit")) {
			UsuarioDao usuarioDao = new UsuarioDao();
			Usuario usuario = new Usuario();

			try {
				int id = Integer.parseInt(request.getParameter("id"));
				usuario = usuarioDao.listarUm(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("nome", usuario.getNome());
			request.setAttribute("endereco", usuario.getEndereco());
			request.setAttribute("email", usuario.getEmail());
			request.setAttribute("password", usuario.getPassword());

			RequestDispatcher rd = request.getRequestDispatcher("/usuarios/registrar.jsp");
			rd.forward(request, response);

			return;
		}

		updateTable(request);

		String nextPage = request.getParameter("nextPage");
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String alert = null;

		String editId = request.getParameter("editId");

		// Check if editMode is true
		if (editId != null) {
			doPut(request, response);
			return;
		}

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEndereco(endereco);
		usuario.setEmail(email);
		usuario.setPassword(password);

		AuthDao authDao = new AuthDao();

		try {
			authDao.register(usuario);
			alert = "Usuario registrado com sucesso!";
		} catch (SQLException e) {
			alert = "Houve um erro interno e não foi possível adicionar o registro";
			e.printStackTrace();
		}

		request.setAttribute("success", alert);
		RequestDispatcher rd = request.getRequestDispatcher("/usuarios/registrar.jsp");
		rd.forward(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String alert = null;

		UsuarioDao usuarioDao = new UsuarioDao();

		try {
			int idParsedInt = Integer.parseInt(id);
			usuarioDao.apagarUm(idParsedInt);
			alert = "Usuario removido com sucesso";
		} catch (Exception e) {
			if (e.getMessage().contains("foreign key constraint fails")) {
				alert = "Você não pode remover este item pois ele está sendo utilizado por outra tabela";
			} else {
				alert = "Erro ao remover item" + id;
			}
			e.printStackTrace();
		}

		updateTable(request);

		request.setAttribute("alert", alert);
		RequestDispatcher rd = request.getRequestDispatcher("/usuarios/listar.jsp");
		rd.forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int editId = Integer.parseInt(request.getParameter("editId"));

		String alert = null;

		Usuario usuario = new Usuario();
		usuario.setId(editId);
		usuario.setNome(nome);
		usuario.setEndereco(endereco);
		usuario.setEmail(email);
		usuario.setPassword(password);

		UsuarioDao usuarioDao = new UsuarioDao();

		try {
			usuarioDao.update(usuario);
			alert = "Usuário atualizado com sucesso!";
		} catch (SQLException e) {
			alert = "Houve um erro interno e não foi possível atualizar o registro";
			e.printStackTrace();
		}

		updateTable(request);

		request.setAttribute("success", alert);
		RequestDispatcher rd = request.getRequestDispatcher("/usuarios/listar.jsp");
		rd.forward(request, response);
	}

	// Functions
	protected void updateTable(HttpServletRequest request) {
		UsuarioDao setDao = new UsuarioDao();
		List<Usuario> usuarios = null;

		try {
			usuarios = setDao.listarTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("usuarios", usuarios);
	}

}
