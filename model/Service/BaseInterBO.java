package model.Service;

import model.entity.Produto;

public interface BaseInterBO<entity> {
	public boolean inserir (entity e);
	public boolean deletar (entity e);
	public boolean alterar (entity e);
	public Produto[] listarPorCampoEspecifico (entity e, String campo);
	public Produto[] listarTodos();
}
