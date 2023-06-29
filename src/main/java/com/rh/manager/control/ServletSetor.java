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

import com.rh.manager.dao.SetorDao;
import com.rh.manager.model.Setor;

@WebServlet("/ServletSetor")
public class ServletSetor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletSetor() {
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
			SetorDao setDao = new SetorDao();
			Setor setor = new Setor();

			try {
				int id = Integer.parseInt(request.getParameter("id"));
				setor = setDao.listarUm(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			request.setAttribute("descricao", setor.getDescricao());

			RequestDispatcher rd = request.getRequestDispatcher("/setores/registrar.jsp");
			rd.forward(request, response);

			return;
		}

		// Update list with new data
		updateTable(request);

		String nextPage = request.getParameter("nextPage");

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

	// Post
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		String editId = request.getParameter("editId");

		// Check if editMode is true
		if (editId != null) {
			doPut(request, response);
			return;
		}

		Setor setor = new Setor();
		setor.setDescricao(descricao);
		String alert = null;

		SetorDao setDao = new SetorDao();

		try {
			setDao.adicionar(setor);
			alert = "Setor adicionado com sucesso";
		} catch (SQLException e) {
			alert = "Erro ao adicionar setor";
			e.printStackTrace();
		}

		request.setAttribute("success", alert);
		RequestDispatcher rd = request.getRequestDispatcher("/setores/registrar.jsp");
		rd.forward(request, response);
	}

	// Update
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		String editId = request.getParameter("editId");
		String alert = null;

		int idParsed = Integer.parseInt(editId);

		Setor setor = new Setor();

		setor.setDescricao(descricao);
		setor.setId(idParsed);

		SetorDao setDao = new SetorDao();

		try {
			setDao.editar(setor);
			alert = "Setor editado com sucesso";
		} catch (SQLException e) {
			alert = "Erro ao editar setor";
			e.printStackTrace();
		}

		updateTable(request);

		request.setAttribute("success", alert);
		RequestDispatcher rd = request.getRequestDispatcher("/setores/listar.jsp");
		rd.forward(request, response);
	}

	// Delete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String alert = null;

		SetorDao setDao = new SetorDao();

		try {
			int idParsedInt = Integer.parseInt(id);
			setDao.apagarUm(idParsedInt);
			alert = "Item removido com sucesso";
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
		RequestDispatcher rd = request.getRequestDispatcher("/setores/listar.jsp");
		rd.forward(request, response);
	}

	// Functions
	protected void updateTable(HttpServletRequest request) {
		SetorDao setDao = new SetorDao();
		List<Setor> setores = null;

		try {
			setores = setDao.listarTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("setores", setores);
	}
}
