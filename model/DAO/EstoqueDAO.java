package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Produto;

public class EstoqueDAO extends BaseDAO<Produto>{
	public boolean inserir (Produto produto) {
		String sql = "INSERT INTO estoque (quantidade, id_produto) VALUES (?,?);";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setDouble(1, produto.getQuantidade());
			pst.setInt(2, produto.getId() );
			pst.execute();
			return true;		
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}				
	}
   
    public boolean alterar(Produto produto) {
		String sql = "UPDATE estoque SET quantidade=? WHERE id_produto=? ";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setDouble(1, produto.getQuantidade());
			pst.setInt(2, produto.getId() );
			pst.executeUpdate();
			return true;		
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		
	}

	@Override
	public ResultSet encontrarPorCampoEspecifico(Produto produto, String field) {
		String sql = "SELECT * FROM estoque WHERE " + field +"=? ;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			switch (field) {
			case "id_produto":
				pst.setInt(1, produto.getId());
				break;
			}
			
			ResultSet rs = pst.executeQuery();
			return rs;
		
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ResultSet encontrarTodos() {
		String sql = "SELECT * FROM estoque;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			return rs;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
