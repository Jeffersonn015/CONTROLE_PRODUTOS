package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.DatabaseConnection;

public class Produto {
    private String nome; // Nome do produto
    private double precoCusto; // Preço de custo do produto
    private double precoVenda; // Preço de venda do produto

    // Construtor para inicializar os atributos do produto
    public Produto(String nome, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    // Método para obter o nome do produto
    public String getNome() {
        return nome;
    }

    // Método para obter o preço de custo do produto
    public double getPrecoCusto() {
        return precoCusto;
    }

    // Método para obter o preço de venda do produto
    public double getPrecoVenda() {
        return precoVenda;
    }

    // Método para alterar o preço de venda do produto
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    // Método para calcular o lucro do produto (preço de venda - preço de custo)
    public double calcularLucro() {
        return precoVenda - precoCusto;
    }

    // Método para salvar o produto no banco de dados
    public void salvar() {
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "INSERT INTO Produto (nome, precoCusto, precoVenda) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setString(1, nome); // Define o nome do produto
                stmt.setDouble(2, precoCusto); // Define o preço de custo
                stmt.setDouble(3, precoVenda); // Define o preço de venda
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
    }

    // Método para deletar o produto do banco de dados pelo nome
    public void deletar() {
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "DELETE FROM Produto WHERE nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setString(1, nome); // Define o nome do produto a ser deletado
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
    }

    // Método para atualizar os preços do produto no banco de dados
    public void atualizar() {
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "UPDATE Produto SET precoCusto = ?, precoVenda = ? WHERE nome = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setDouble(1, precoCusto); // Define o novo preço de custo
                stmt.setDouble(2, precoVenda); // Define o novo preço de venda
                stmt.setString(3, nome); // Define o nome do produto a ser atualizado
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
    }
}
