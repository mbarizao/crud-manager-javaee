package com.rh.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rh.manager.model.Funcionario;
import com.rh.manager.model.Setor;

public class FuncionarioDao {

	public void adicionar(Funcionario funcionario) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert funcionario(nome,profissao,idsetor) values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, funcionario.getNome());
		stmt.setString(2, funcionario.getProfissao());
		stmt.setInt(3, funcionario.getSetor().getId());
		stmt.execute();
		stmt.close();
		con.close();
	}

	public void atualizar(Funcionario funcionario) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "UPDATE funcionario SET nome = ?, profissao = ?, idSetor = ? WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, funcionario.getNome());
		stmt.setString(2, funcionario.getProfissao());
		stmt.setInt(3, funcionario.getSetor().getId());
		stmt.setInt(4, funcionario.getId());

		stmt.executeUpdate();
		stmt.close();
		con.close();
	}

	public List<Funcionario> listarTodos() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from funcionario";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		Funcionario funcionario = null;
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Setor setor;

		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String profissao = rs.getString("profissao");
			int idsetor = rs.getInt("idSetor");

			funcionario = new Funcionario();
			funcionario.setId(id);
			funcionario.setNome(nome);
			funcionario.setProfissao(profissao);

			SetorDao setDao = new SetorDao();
			setor = setDao.listarUm(idsetor);
			funcionario.setSetor(setor);

			funcionarios.add(funcionario);
		}
		stmt.close();
		con.close();
		return funcionarios;

	}

	public Funcionario listarUm(int id) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM funcionario WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Funcionario funcionario = null;

		if (rs.next()) {
			String nome = rs.getString("nome");
			String profissao = rs.getString("profissao");
			int idSetor = rs.getInt("idSetor");

			Setor setor = new Setor();

			SetorDao setorDao = new SetorDao();

			setor = setorDao.listarUm(idSetor);

			funcionario = new Funcionario();
			funcionario.setId(id);
			funcionario.setNome(nome);
			funcionario.setProfissao(profissao);
			funcionario.setSetor(setor);
		}

		stmt.close();
		con.close();
		return funcionario;
	}

	public void apagarUm(int id) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "DELETE FROM funcionario WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();

		stmt.close();
		con.close();
	}
}
