package Desafio_SGTI;

import java.util.*;

public class Main {

    private static final Scanner leitor = new Scanner(System.in);
    private static final List<Funcionario> funcionarios = new ArrayList<>();

    static void main() {
        Opcao opcao = null;
        do{
        exibirMenu();

        try {
            opcao = Opcao.porCodigo(Integer.parseInt(leitor.nextLine()));
        } catch (IllegalArgumentException e){
            System.out.println("Opção inválida");
            continue;
        }


            switch (opcao) {
                case CADASTRAR_FUNCIONARIO -> cadastrarFuncionario();
                case LISTAR_FUNCIONARIO -> listarFuncionarios();
                case EXIBIR_SALARIOS -> exibirSalariosFinais();
                case FUNCIONARIO_COM_MAIOR_SALARIO -> exibirFuncionarioComMaiorSalario();
                case QUANTIDADE_FUNCIONARIO_POR_CARGO -> exibirQuantidadeFuncionariosPorCargo();
                case PESQUISAR_FUNCIONARIO_POR_NOME -> pesquisarFuncionarioPorNome();
                case SAIR -> System.out.println("ENCERRANDO O SISTEMA!!!");
            }
        } while (opcao !=Opcao.SAIR);
    }

    private static void exibirMenu(){
        System.out.println("""
                \n1 - Cadastrar funcionário
                2 - Listar funcionários
                3 - Exibir salários finais
                4 - Funcionário com maior salário
                5 - Quantidade de funcionários por cargo
                6 - Buscar funcionário por nome
                7 - Sair
                """);
    }

    private static void cadastrarFuncionario(){
        System.out.println("Informe o nome: ");
        String nome = leitor.nextLine();

        Cargo cargo = null;
        while(cargo == null){
            System.out.println("Informe o cargo: ");

            try {
                cargo = Cargo.valueOf(leitor.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Cargo inválido! Tente novamente");
            }
        }

        double salario = 0;

        while(salario <= 0) {
            System.out.println("Informe o salario: ");
            salario = Double.parseDouble(leitor.nextLine());

            if(salario <= 0){
                System.out.println("Salário inválido! Informe um valor maior que zero.");
            }
        }
        Funcionario funcionario = new Funcionario(nome, cargo, salario);
        funcionarios.add(funcionario);
    }

    private static void listarFuncionarios(){
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado!");
            return;
        }

        for (Funcionario funcionario : funcionarios){
            System.out.println("\nNome: " + funcionario.getNome());
            System.out.println("Cargo: " + funcionario.getCargo().getDescricao());
        }
    }

    private static void exibirSalariosFinais(){
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum salário final encontrado!");
            return;
        }
        for(Funcionario funcionario : funcionarios){

            String nome = funcionario.getNome();
            double salarioFinal = funcionario.obterSalarioFinal();

            System.out.println("\n" + nome + " -> "+salarioFinal);
        }
    }

    private static void exibirFuncionarioComMaiorSalario() {
        Funcionario funcionarioComMaiorSalario = null;
        for (Funcionario funcionario : funcionarios){
            if (funcionarioComMaiorSalario == null){
                funcionarioComMaiorSalario = funcionario;
            } else {
                if (funcionario.obterSalarioFinal() > funcionarioComMaiorSalario.obterSalarioFinal()){
                    funcionarioComMaiorSalario = funcionario;
                }
            }
        }
        if (funcionarioComMaiorSalario != null) {
            System.out.println("\nFuncionário: " + funcionarioComMaiorSalario.getNome());
            System.out.println("Salário: " + funcionarioComMaiorSalario.obterSalarioFinal());
        } else {
            System.out.println("Sem funcionários cadastrados no sistema!");
        }
    }

    private static void exibirQuantidadeFuncionariosPorCargo(){
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado!");
            return;
        }
        Map<Cargo, Integer> mapQuantidadeFuncionariosPorCargo = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            int quantidadeAtual = mapQuantidadeFuncionariosPorCargo.getOrDefault(funcionario.getCargo(), 0);
            mapQuantidadeFuncionariosPorCargo.put(funcionario.getCargo(), quantidadeAtual + 1);
        }

        for (Map.Entry<Cargo, Integer> map : mapQuantidadeFuncionariosPorCargo.entrySet()) {
            Cargo key = map.getKey();
            Integer valor = map.getValue();
            System.out.println("\n" + key + " -> " + valor);
        }
    }

    private static void pesquisarFuncionarioPorNome() {
        System.out.println("\n Digite o nome: ");
        String nome = leitor.nextLine();


        boolean encontrado = false;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().equalsIgnoreCase(nome)){
                encontrado = true;

                System.out.println("FUNCIONÁRIO ENCONTRADO!!!");
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Cargo: " + funcionario.getCargo().getDescricao());
                System.out.println("Salário: " + funcionario.obterSalarioFinal());
            }
        } if (!encontrado) {
            System.out.println("Funcionário não encontrado!");
        }
    }
}
