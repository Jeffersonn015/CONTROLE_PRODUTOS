package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.DatabaseConnection;

// Classe ProdutoVestuario que estende a classe Produto, adicionando atributos específicos
public class ProdutoVestuario extends Produto {
    private String tamanho;  // Tamanho da peça de vestuário (ex.: P, M, G)
    private String cor;      // Cor da peça de vestuário
    private String material; // Material da peça (ex.: algodão, poliéster)

    // Construtor para inicializar os atributos de ProdutoVestuario
    public ProdutoVestuario(String nome, double precoCusto, double precoVenda, String tamanho, String cor, String material) {
        super(nome, precoCusto, precoVenda); // Chama o construtor da classe base Produto
        this.tamanho = tamanho;
        this.cor = cor;
        this.material = material;
    }

    // Métodos para acessar os atributos específicos de ProdutoVestuario
    public String getTamanho() {
        return tamanho;
    }

    public String getCor() {
        return cor;
    }

    public String getMaterial() {
        return material;
    }

    // Sobrescreve o método salvar para incluir as informações específicas de ProdutoVestuario
    @Override
    public void salvar() {
        super.salvar(); // Salva os dados gerais do produto na tabela Produto
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "INSERT INTO ProdutoVestuario (produtoId, tamanho, cor, material) VALUES ((SELECT id FROM Produto WHERE nome = ?), ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setString(1, getNome()); // Usa o nome do produto para obter o ID
                stmt.setString(2, tamanho);   // Define o tamanho
                stmt.setString(3, cor);      // Define a cor
                stmt.setString(4, material); // Define o material
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
    }

    // Sobrescreve o método deletar para remover também os dados específicos de ProdutoVestuario
    @Override
    public void deletar() {
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "DELETE FROM ProdutoVestuario WHERE produtoId = (SELECT id FROM Produto WHERE nome = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setString(1, getNome()); // Usa o nome do produto para identificar o registro
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
        super.deletar(); // Deleta o registro geral na tabela Produto
    }

    // Sobrescreve o método atualizar para incluir as alterações em ProdutoVestuario
    @Override
    public void atualizar() {
        super.atualizar(); // Atualiza os dados gerais do produto na tabela Produto
        try (Connection conn = DatabaseConnection.getConnection()) { // Obtém conexão com o banco de dados
            String sql = "UPDATE ProdutoVestuario SET tamanho = ?, cor = ?, material = ? WHERE produtoId = (SELECT id FROM Produto WHERE nome = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
                stmt.setString(1, tamanho);   // Atualiza o tamanho
                stmt.setString(2, cor);      // Atualiza a cor
                stmt.setString(3, material); // Atualiza o material
                stmt.setString(4, getNome()); // Usa o nome do produto para identificar o registro
                stmt.executeUpdate(); // Executa a instrução SQL
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe a pilha de erro em caso de falha
        }
    }
}
