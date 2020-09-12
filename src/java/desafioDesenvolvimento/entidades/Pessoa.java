package desafioDesenvolvimento.entidades;


import java.util.Date;

/**
 *
 * @author nammur
 */

public class Pessoa {
    private String nome;
    private Date entrada;
    private Date saida;
    private String funcao;
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada= entrada;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Pessoa() {
    }

    public Pessoa(String nome, Date entrada, String funcao) {
        this.nome = nome;
        this.entrada = entrada;
        this.funcao = funcao;
    }
    
    
    
}
