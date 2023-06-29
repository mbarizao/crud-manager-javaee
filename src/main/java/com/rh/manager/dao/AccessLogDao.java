package com.rh.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rh.manager.model.AccessLog;

public class AccessLogDao {
	public void add(AccessLog accessLog) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "INSERT logs_de_acesso(pagina, enderecoIp, usuarioId) VALUES (?,?,?)";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, accessLog.getPagina());
		stmt.setString(2, accessLog.getEnderecoIP());

		if (accessLog.getUsuarioID() > 0) {
			stmt.setInt(3, accessLog.getUsuarioID());
		} else {
			stmt.setInt(3, 0);
		}

		stmt.execute();
		stmt.close();
		con.close();
	}

	public int getAccessQuantity(String pageName, int userId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT count(*) as qtd FROM logs_de_acesso WHERE usuarioId = ? AND pagina = ?";

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, userId);
		stmt.setString(2, pageName);

		ResultSet rs = stmt.executeQuery();
		System.out.println(stmt);

		int accessQuantity = 0;

		if (rs.next()) {
			accessQuantity = rs.getInt("qtd");
		}

		stmt.close();
		con.close();
		return accessQuantity;
	}
}
