package model.DAO;

import model.entity.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO extends BaseDAO<Produto>{
	
	public boolean inserir (Produto produto) {
		String sql = "INSERT INTO produtos  (nome,marca,cod_de_barras,preco) VALUES (?,?,?,?);";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getMarca() );
			pst.setString(3, produto.getCodBarras());
			pst.setDouble(4, produto.getPreco());
			pst.execute();
			return true;		
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}				
	}
	
    public boolean deletar(Produto produto) {
		String sql = "DELETE FROM produtos WHERE cod_de_barras=?;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setString(1, produto.getCodBarras());
			pst.execute();
			
			return true;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
   
    public boolean alterar(Produto produto) {
		String sql = "UPDATE produtos SET nome=?,marca=?,preco=? WHERE cod_de_barras=? ";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getMarca() );
			pst.setDouble(3, produto.getPreco());
			pst.setString(4, produto.getCodBarras());
			pst.executeUpdate();
			return true;		
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		
	}

	@Override
	public ResultSet encontrarPorCampoEspecifico(Produto produto, String field) {
		String sql = "SELECT * FROM produtos WHERE " + field +"=? ;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			switch (field) {
			case "nome":
				pst.setString(1, produto.getNome());
				break;
				
			case "marca":
				pst.setString(1, produto.getMarca());
				break;
				
			case "cod_de_barras":
				pst.setString(1, produto.getCodBarras());
				break;
				
			case "preco":
				pst.setDouble(1, produto.getPreco());
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
		String sql = "SELECT * FROM produtos;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			return rs;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ResultSet encontrarPorCampoEspecificoIncompleto(Produto produto, String field) {
		String sql = "SELECT * FROM produtos WHERE " + field +" LIKE ? ;";
		try {
			PreparedStatement pst = getConnection().prepareStatement(sql);
			switch (field) {
			case "nome":
				pst.setString(1, "%" + produto.getNome() + "%");
				break;
				
			case "marca":
				pst.setString(1, "%" + produto.getMarca() + "%");
				break;
				
			case "cod_de_barras":
				pst.setString(1, "%" + produto.getCodBarras() + "%");
				break;
				
			
			}
			
			ResultSet rs = pst.executeQuery();
			return rs;
		
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
