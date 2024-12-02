package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import dao.DatabaseConnection;

// Classe ProdutoAlimenticio que estende a classe Produto, adicionando atributos específicos
public class ProdutoAlimenticio extends Produto {
    private LocalDate dataValidade; // Data de validade do produto alimentício
    private String informacoesNutricionais; // Informações nutricionais do produto

    // Construtor para inicializar os atributos do produto alimentício
    public ProdutoAlimenticio(String nome, double precoCusto, double precoVenda, LocalDate dataValidade, String informacoesNutricionais) {
        super(nome, precoCusto, precoVenda); // Chama o construtor da classe base (Produto)
        this.dataValidade = dataValidade;
        this.informacoesNutricionais = informacoesNutricionais;
    }

    // Método para obter a data de validade do produto
    public LocalDate getDataValidade() {
        return dataValidade;
    }

    // Método para obter as informações nutricionais do produto
    public String getInformacoesNutricionais() {
        return informacoesNutricionais;
    }

    // Sobrescreve o método salvar para incluir as informações específicas de ProdutoAlimenticio
    @Override
    public void salvar() {
        super.salvar(); // Salva os dados gerais do produto na tabela Produto
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "INSERT INTO ProdutoAlimenticio (produtoId, dataValidade, informacoesNutricionais) VALUES ((SELECT id FROM Produto WHERE nome = ?), ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setString(1, getNome()); // Usa o nome do produto para obter seu ID
                stmt.setDate(2, java.sql.Date.valueOf(dataValidade)); // Define a data de validade
                stmt.setString(3, informacoesNutricionais); // Define as informações nutricionais
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
    }

    // Sobrescreve o método deletar para remover também as informações específicas de ProdutoAlimenticio
    @Override
    public void deletar() {
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "DELETE FROM ProdutoAlimenticio WHERE produtoId = (SELECT id FROM Produto WHERE nome = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setString(1, getNome()); // Usa o nome do produto para identificar o registro
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
        super.deletar(); // Deleta o registro geral na tabela Produto
    }

    // Sobrescreve o método atualizar para incluir as alterações em ProdutoAlimenticio
    @Override
    public void atualizar() {
        super.atualizar(); // Atualiza os dados gerais do produto na tabela Produto
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "UPDATE ProdutoAlimenticio SET dataValidade = ?, informacoesNutricionais = ? WHERE produtoId = (SELECT id FROM Produto WHERE nome = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setDate(1, java.sql.Date.valueOf(dataValidade)); // Atualiza a data de validade
                stmt.setString(2, informacoesNutricionais); // Atualiza as informações nutricionais
                stmt.setString(3, getNome()); // Usa o nome do produto para identificar o registro
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
    }
}
