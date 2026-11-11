package Desafio_SGTI;

public class Funcionario {

    private String nome;
    private double salario;
    private Cargo cargo;

    public Funcionario(String nome,
                       Cargo cargo,
                       double salario) {
        this.nome = nome;
        this.salario = salario;
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public String getNome() {
        return nome;
    }

    public double obterSalarioFinal() {
        return salario + (cargo.calcularBonus(salario));
    }
}
