package model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DAO.EstoqueDAO;
import model.entity.Produto;

public class EstoqueBO implements BaseInterBO<Produto>{
	EstoqueDAO dao = new EstoqueDAO();
	
	private boolean ExisteNoBD(Produto produto) {
		ResultSet existe = dao.encontrarPorCampoEspecifico(produto, "id_produto");
		try { 
			return existe != null && existe.next();
			
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
	}
	
	public boolean inserir (Produto produto){
		if (!this.ExisteNoBD(produto)) {
			if (dao.inserir(produto) == true) return true;
			else return false;
		}else return false;
	}
	
	public boolean alterar (Produto produto){
		if (this.ExisteNoBD(produto)) {
			if (dao.alterar(produto))return true;
			else return false;
		}else return false;
	}

	public Produto[] listarPorCampoEspecifico(Produto produto, String campo){
		List<Produto> Produtos = new ArrayList<Produto>();
		ResultSet rs = dao.encontrarPorCampoEspecifico(produto, campo);
		try {
			while(rs.next()) {
				Produto produtoLista = new Produto();
				produtoLista.setId(rs.getInt("id_produto"));
				produtoLista.setQuantidade(rs.getDouble("quantidade"));
				

				Produtos.add(produtoLista);
			}
			return Produtos.toArray(new Produto[Produtos.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Produto[] listarTodos(){
		List<Produto> Produtos = new ArrayList<Produto>();
		ResultSet rs = dao.encontrarTodos();
		try {
			while(rs.next()) {
				Produto produtoLista = new Produto();
				produtoLista.setId(rs.getInt("id_produto"));
				produtoLista.setQuantidade(rs.getDouble("quantidade"));
				Produtos.add(produtoLista);
			}
			return Produtos.toArray(new Produto[Produtos.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deletar(Produto e) {
		// TODO Auto-generated method stub
		return false;
	}



}
