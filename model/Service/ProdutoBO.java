package model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.Produto;
import model.DAO.ProdutoDAO;

public class ProdutoBO implements BaseInterBO<Produto>{
	ProdutoDAO dao = new ProdutoDAO();
	
	private boolean ExisteNoBD(Produto produto) {
		ResultSet existe = dao.encontrarPorCampoEspecifico(produto, "cod_de_barras");
		try { 
			return existe != null && existe.next();
			
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
	}
	
	private boolean NaoExisteNoBD(Produto produto) {
		ResultSet existe = dao.encontrarPorCampoEspecifico(produto, "cod_de_barras");
		try { 
			return existe == null || !(existe.next());
			
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
	}
	
	public boolean inserir (Produto produto){
		if (this.NaoExisteNoBD(produto)) {
			if (dao.inserir(produto) == true) return true;
			else return false;
		}else return false;
	}
	
	public boolean deletar (Produto produto) {
		if(this.ExisteNoBD(produto)) {
			if (dao.deletar(produto) == true)return true;
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
				produtoLista.setNome(rs.getString("nome"));
				produtoLista.setMarca(rs.getString("marca"));
				produtoLista.setCodBarras(rs.getString("cod_de_barras"));
				produtoLista.setPreco(rs.getDouble("preco"));
				

				Produtos.add(produtoLista);
			}
			return Produtos.toArray(new Produto[Produtos.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Produto> listarPorCampoEspecificoIncompleto(Produto produto, String campo){
		List<Produto> Produtos = new ArrayList<Produto>();
		ResultSet rs = dao.encontrarPorCampoEspecificoIncompleto(produto, campo);
		try {
			while(rs.next()) {
				Produto produtoLista = new Produto();
				produtoLista.setId(rs.getInt("id_produto"));
				produtoLista.setNome(rs.getString("nome"));
				produtoLista.setMarca(rs.getString("marca"));
				produtoLista.setCodBarras(rs.getString("cod_de_barras"));
				produtoLista.setPreco(rs.getDouble("preco"));

				Produtos.add(produtoLista);
			}
			return Produtos;
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
				produtoLista.setNome(rs.getString("nome"));
				produtoLista.setMarca(rs.getString("marca"));
				produtoLista.setCodBarras(rs.getString("cod_de_barras"));
				produtoLista.setPreco(rs.getDouble("preco"));

				Produtos.add(produtoLista);
			}
			return Produtos.toArray(new Produto[Produtos.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}