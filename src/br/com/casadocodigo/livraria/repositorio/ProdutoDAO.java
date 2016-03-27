package br.com.casadocodigo.livraria.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.livraria.produtos.LivroFisico;
import br.com.casadocodigo.livraria.produtos.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProdutoDAO {

	public ObservableList<Produto> lista() {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		
		try (Connection connection = ConnectionFactory.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM produtos");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				LivroFisico livro = new LivroFisico(new Autor());
				livro.setNome(rs.getString("nome"));
				livro.setDescricao(rs.getString("descricao"));
				livro.setValor(rs.getDouble("valor"));
				livro.setIsbn(rs.getString("isbn"));
				produtos.add(livro);
			}
			
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		
		return produtos;
	}
	
	public void adiciona(Produto produto) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO produtos "
					+ "(nome, descricao, valor, isbn) values (?, ?, ?, ?)");
			
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.setDouble(3, produto.getValor());
			ps.setString(4, produto.getIsbn());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
