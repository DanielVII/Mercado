package model.entity;

public class Produto {
	int id;
    private String nome, marca, codDeBarras;
    private Double preco, quantidade;

    /*Se espera aqui que seja escolhido anteriormente um produto por meio da pesquisa do site
     * e então seja chamada essa função, a qual vai receber o valor preço do produto em 
     * questão e, após verificação, ele será trocado pelo novo valor
    */

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public void setNome(String nome) {
    	if (!nome.isEmpty()){
    		this.nome = nome;
    	}
    }
    
    public String getNome(){
    	return this.nome;
    }

    public void setMarca(String marca) {
    	if (!marca.isEmpty()){
    		this.marca = marca;
    	}
    }
    
    public String getMarca(){
    	return this.marca;
    }
    
    public void setCodBarras(String cod) {
    	if (!cod.isEmpty()){
    		this.codDeBarras = cod;
    	}
    }
    
    public String getCodBarras(){
    	return this.codDeBarras;
    }
    
    public void setPreco(Double valor) {
        if (valor > 0){
            this.preco = valor;
        }
    }

    public Double getPreco(){
        return this.preco;
    }

    public Double getQuantidade() {
    	return this.quantidade;
    }
    
    public void setQuantidade(Double quantidade) {
    	if(quantidade>=0) this.quantidade = quantidade;
    }
    

}
