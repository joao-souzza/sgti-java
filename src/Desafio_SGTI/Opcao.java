package Desafio_SGTI;

public enum Opcao {

    CADASTRAR_FUNCIONARIO(1),
    LISTAR_FUNCIONARIO(2),
    EXIBIR_SALARIOS(3),
    FUNCIONARIO_COM_MAIOR_SALARIO(4),
    QUANTIDADE_FUNCIONARIO_POR_CARGO(5),
    PESQUISAR_FUNCIONARIO_POR_NOME(6),
    SAIR(7);

    private final int codigo;

    Opcao(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static Opcao porCodigo(int codigo){
        for (Opcao opcao : values()){
            if(opcao.codigo == codigo) {
                return opcao;
            }
        }

        throw new IllegalArgumentException("Opção inválida: " + codigo);
    }
}
