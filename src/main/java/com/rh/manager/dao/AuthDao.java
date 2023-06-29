package com.rh.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rh.manager.model.Usuario;

// Conexão com o banco para fazer login e registrar usuários
public class AuthDao {
	public void register(Usuario user) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "INSERT usuario(nome, endereco, email, password) VALUES (?,?,?,?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user.getNome());
		stmt.setString(2, user.getEndereco());
		stmt.setString(3, user.getEmail());
		stmt.setString(4, user.getPassword());

		stmt.execute();
		stmt.close();
		con.close();
	}

	public Usuario login(String email, String password) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT id, nome, endereco, email, password FROM usuario WHERE email = ? AND password = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, password);

		ResultSet rs = stmt.executeQuery();

		Usuario user = null;

		if (rs.next()) {
			int id = rs.getInt("id");
			String nome = rs.getString("nome");
			String endereco = rs.getString("endereco");
			user = new Usuario();
			user.setId(id);
			user.setNome(nome);
			user.setPassword(password);
			user.setEmail(email);
			user.setEndereco(endereco);
		}

		stmt.close();
		con.close();
		return user;
	}
}
