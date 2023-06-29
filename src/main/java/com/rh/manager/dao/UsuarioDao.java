package com.rh.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rh.manager.model.Usuario;

public class UsuarioDao {
	public void update(Usuario usuario) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "UPDATE usuario SET nome = ?, endereco = ?, email = ?, password = ? WHERE id = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getEndereco());
		stmt.setString(3, usuario.getEmail());
		stmt.setString(4, usuario.getPassword());
		stmt.setInt(5, usuario.getId());

		stmt.executeUpdate();
		stmt.close();
		con.close();
	}

	public List<Usuario> listarTodos() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM usuario";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		Usuario usuario = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String endereco = rs.getString("endereco");
			String email = rs.getString("email");

			usuario = new Usuario();
			usuario.setId(id);
			usuario.setNome(nome);
			usuario.setEndereco(endereco);
			usuario.setEmail(email);

			usuarios.add(usuario);
		}

		stmt.close();
		con.close();

		return usuarios;
	}

	public Usuario listarUm(int id) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM usuario WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		Usuario usuario = null;

		if (rs.next()) {
			String nome = rs.getString("nome");
			String endereco = rs.getString("endereco");
			String email = rs.getString("email");
			String password = rs.getString("password");

			usuario = new Usuario();
			usuario.setId(id);
			usuario.setNome(nome);
			usuario.setEndereco(endereco);
			usuario.setEmail(email);
			usuario.setPassword(password);
		}

		stmt.close();
		con.close();
		return usuario;
	}

	public void apagarUm(int id) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "DELETE FROM usuario WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();

		stmt.close();
		con.close();
	}
}
