package com.rh.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rh.manager.model.Setor;

public class SetorDao {

	public void adicionar(Setor setor) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert setor(descricao) values (?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, setor.getDescricao());
		stmt.execute();
		stmt.close();
		con.close();
	}

	public List<Setor> listarTodos() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select id, descricao from setor";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		Setor setor = null;
		List<Setor> sets = new ArrayList<Setor>();

		while (rs.next()) {
			int id = rs.getInt("id");
			String descricao = rs.getString("descricao");
			setor = new Setor();
			setor.setId(id);
			setor.setDescricao(descricao);
			sets.add(setor);
		}
		stmt.close();
		con.close();
		return sets;

	}

	public Setor listarUm(int id) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select descricao from setor where id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Setor setor = null;
		if (rs.next()) {
			String descricao = rs.getString("descricao");
			setor = new Setor();
			setor.setId(id);
			setor.setDescricao(descricao);
		}
		stmt.close();
		con.close();
		return setor;

	}

	public void apagarUm(int id) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "DELETE FROM setor WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();

		stmt.close();
		con.close();
	}

	public void editar(Setor setor) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "UPDATE setor SET descricao = ? WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, setor.getDescricao());
		stmt.setInt(2, setor.getId());
		stmt.executeUpdate();

		stmt.close();
		con.close();
	}
}
