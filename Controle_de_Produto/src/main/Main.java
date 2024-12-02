package main;

import java.time.LocalDate;
import java.util.Scanner;
import produto.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados pelo usuário

        while (true) { // Loop infinito até o usuário escolher sair
            // Exibe o menu de opções
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar Produto Alimentício");
            System.out.println("2. Adicionar Produto Vestuário");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Deletar Produto");
            System.out.println("5. Calcular Lucro");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt(); // Lê a opção escolhida
            scanner.nextLine(); // Consumir a nova linha para evitar problemas na próxima entrada

            if (opcao == 6) { // Encerra o programa se o usuário escolher a opção 6
                break;
            }

            // Switch para tratar cada opção do menu
            switch (opcao) {
                case 1: // Adicionar Produto Alimentício
                    System.out.print("Nome do Produto Alimentício: ");
                    String nomeAlimenticio = scanner.nextLine();
                    System.out.print("Preço de Custo: ");
                    double precoCustoAlimenticio = scanner.nextDouble();
                    System.out.print("Preço de Venda: ");
                    double precoVendaAlimenticio = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.print("Data de Validade (AAAA-MM-DD): ");
                    LocalDate dataValidade = LocalDate.parse(scanner.nextLine());
                    System.out.print("Informações Nutricionais: ");
                    String informacoesNutricionais = scanner.nextLine();

                    // Cria e salva o produto alimentício
                    ProdutoAlimenticio produtoAlimenticio = new ProdutoAlimenticio(nomeAlimenticio, precoCustoAlimenticio, precoVendaAlimenticio, dataValidade, informacoesNutricionais);
                    produtoAlimenticio.salvar();
                    System.out.println("Produto Alimentício salvo: " + produtoAlimenticio.getNome());
                    break;

                case 2: // Adicionar Produto Vestuário
                    System.out.print("Nome do Produto Vestuário: ");
                    String nomeVestuario = scanner.nextLine();
                    System.out.print("Preço de Custo: ");
                    double precoCustoVestuario = scanner.nextDouble();
                    System.out.print("Preço de Venda: ");
                    double precoVendaVestuario = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.print("Tamanho: ");
                    String tamanho = scanner.nextLine();
                    System.out.print("Cor: ");
                    String cor = scanner.nextLine();
                    System.out.print("Material: ");
                    String material = scanner.nextLine();

                    // Cria e salva o produto vestuário
                    ProdutoVestuario produtoVestuario = new ProdutoVestuario(nomeVestuario, precoCustoVestuario, precoVendaVestuario, tamanho, cor, material);
                    produtoVestuario.salvar();
                    System.out.println("Produto Vestuário salvo: " + produtoVestuario.getNome());
                    break;

                case 3: // Atualizar Produto
                    System.out.print("Nome do Produto a ser atualizado: ");
                    String nomeAtualizar = scanner.nextLine();
                    System.out.print("Novo Preço de Custo: ");
                    double novoPrecoCusto = scanner.nextDouble();
                    System.out.print("Novo Preço de Venda: ");
                    double novoPrecoVenda = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a nova linha

                    // Cria um produto com os novos valores e atualiza
                    Produto produtoAtualizar = new Produto(nomeAtualizar, novoPrecoCusto, novoPrecoVenda);
                    produtoAtualizar.atualizar();
                    System.out.println("Produto atualizado: " + produtoAtualizar.getNome());
                    break;

                case 4: // Deletar Produto
                    System.out.print("Nome do Produto a ser deletado: ");
                    String nomeDeletar = scanner.nextLine();

                    // Cria um produto com o nome e deleta
                    Produto produtoDeletar = new Produto(nomeDeletar, 0, 0);
                    produtoDeletar.deletar();
                    System.out.println("Produto deletado: " + produtoDeletar.getNome());
                    break;

                case 5: // Calcular Lucro
                    System.out.print("Nome do Produto para calcular o lucro: ");
                    String nomeCalcularLucro = scanner.nextLine();
                    System.out.print("Preço de Custo: ");
                    double precoCustoLucro = scanner.nextDouble();
                    System.out.print("Preço de Venda: ");
                    double precoVendaLucro = scanner.nextDouble();
                    scanner.nextLine(); // Consumir a nova linha

                    // Cria um produto e calcula o lucro
                    Produto produtoCalcularLucro = new Produto(nomeCalcularLucro, precoCustoLucro, precoVendaLucro);
                    double lucro = produtoCalcularLucro.calcularLucro();
                    System.out.println("Lucro do produto " + produtoCalcularLucro.getNome() + ": R$ " + lucro);
                    break;

                default: // Opção inválida
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close(); // Fecha o scanner ao sair do loop
    }
}
