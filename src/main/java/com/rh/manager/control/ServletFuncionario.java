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

import com.rh.manager.dao.FuncionarioDao;
import com.rh.manager.dao.SetorDao;
import com.rh.manager.model.Funcionario;
import com.rh.manager.model.Setor;

@WebServlet("/ServletFuncionario")
public class ServletFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletFuncionario() {
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
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			Funcionario funcionario = new Funcionario();

			try {
				int id = Integer.parseInt(request.getParameter("id"));
				funcionario = funcionarioDao.listarUm(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			SetorDao setDao = new SetorDao();
			List<Setor> setores = null;

			try {
				setores = setDao.listarTodos();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("setores", setores);

			request.setAttribute("nome", funcionario.getNome());
			request.setAttribute("profissao", funcionario.getProfissao());
			request.setAttribute("idSetor", funcionario.getSetor().getId());

			RequestDispatcher rd = request.getRequestDispatcher("/funcionarios/registrar.jsp");
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
		String profissao = request.getParameter("profissao");
		int idSetor = Integer.valueOf(request.getParameter("idSetor"));

		String alert = null;

		String editId = request.getParameter("editId");

		// Check if editMode is true
		if (editId != null) {
			doPut(request, response);
			return;
		}

		SetorDao setDao = new SetorDao();
		Setor setor = null;

		try {
			setor = setDao.listarUm(idSetor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			alert = "Houve um erro interno e não foi possível adicionar o registro";
			e.printStackTrace();
		}

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setProfissao(profissao);
		funcionario.setSetor(setor);

		FuncionarioDao funcionarioDao = new FuncionarioDao();

		try {
			funcionarioDao.adicionar(funcionario);
			alert = "Funcionário adicionado com sucesso!";
		} catch (SQLException e) {
			alert = "Houve um erro interno e não foi possível adicionar o registro";
			e.printStackTrace();
		}

		request.setAttribute("success", alert);
		RequestDispatcher rd = request.getRequestDispatcher("/funcionarios/registrar.jsp");
		rd.forward(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String alert = null;

		FuncionarioDao funcionario = new FuncionarioDao();

		try {
			int idParsedInt = Integer.parseInt(id);
			funcionario.apagarUm(idParsedInt);
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
		RequestDispatcher rd = request.getRequestDispatcher("/funcionarios/listar.jsp");
		rd.forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String profissao = request.getParameter("profissao");
		int idSetor = Integer.parseInt(request.getParameter("idSetor"));
		int editId = Integer.parseInt(request.getParameter("editId"));

		String alert = null;

		SetorDao setDao = new SetorDao();
		Setor setor = null;

		try {
			setor = setDao.listarUm(idSetor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			alert = "Houve um erro interno e não foi possível atualizar o registro";
			e.printStackTrace();
		}

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setProfissao(profissao);
		funcionario.setSetor(setor);
		funcionario.setId(editId);

		FuncionarioDao funcionarioDao = new FuncionarioDao();

		try {
			funcionarioDao.atualizar(funcionario);
			alert = "Funcionário atualizado com sucesso!";
		} catch (SQLException e) {
			alert = "Houve um erro interno e não foi possível atualizar o registro";
			e.printStackTrace();
		}

		updateTable(request);

		request.setAttribute("success", alert);
		RequestDispatcher rd = request.getRequestDispatcher("/funcionarios/listar.jsp");
		rd.forward(request, response);
	}

	// Functions

	protected void updateTable(HttpServletRequest request) {
		FuncionarioDao setDao = new FuncionarioDao();
		List<Funcionario> funcionarios = null;

		try {
			funcionarios = setDao.listarTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("funcionarios", funcionarios);
	}

}
