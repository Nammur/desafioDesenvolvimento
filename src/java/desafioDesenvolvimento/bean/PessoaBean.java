package desafioDesenvolvimento.bean;


import desafioDesenvolvimento.entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author nammur
 */

@ManagedBean
@SessionScoped
public class PessoaBean {
    
    private Pessoa pessoa = new Pessoa();
    private Pessoa person = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList<>();
    private String estadoTela = "edita";
    //primeiro representa Visitantes e segundo representa Funcionarios
    int[] visFunc = {0,0};    
    int contID = 1;
    
    public void salvar(){
        //se a pessoa que esta sendo cadastrada for visitante, for um novo cadastro e o numero de 
        //visitantes estiver em 50, a entrada para essa pessoa é proibida e não será realizado cadastro
        if("Visitante".equals(pessoa.getFuncao()) && visFunc[0] == 50 && pessoa.getId() == 0){
            adicionarMsg("O estabelecimento atingiu seu limite de visitantes, espere algúem deixar o local.", FacesMessage.SEVERITY_INFO);
        }
        //realização do cadastro
        else{
            //se for um novo cadastro
            if(pessoa.getId() == 0){
                //verificação para o preenchimento dos campos
                if("".equals(pessoa.getFuncao()) || pessoa.getEntrada() == null || "".equals(pessoa.getNome())){
                    adicionarMsg("Preencha todos os campos", FacesMessage.SEVERITY_INFO);
                }
                else{
                    //setando o ID
                    pessoa.setId(contID);
                    //incrementando o ID para o proximo registro
                    contID++;
                    //adicionando o novo registro na lista
                    pessoas.add(pessoa);
                    //se for um visitante, incrementa no contador de visitantes
                    if("Visitante".equals(pessoa.getFuncao()) ){
                        visFunc[0]++;
                    }
                    //se for um funcionario, incrementa no contador de funcionario
                    else if("Funcionario".equals(pessoa.getFuncao())){
                        visFunc[1]++;
                    }
                    //reseta o registro para receber um novo
                    pessoa = new Pessoa();
                }
            }
            //se o ID for > 0, ou seja, se já existir um registro com esse ID
            else{
                //percorre a lista até achar onde está o registro com esse ID
                for(int i=0;i<pessoas.size();i++){
                    person = pessoas.get(i);
                    if(person.getId() == pessoa.getId()){
                        //se estiver no estado de tela de saida
                        if(isSaida()){
                            //verificação de preenchimento do campo
                            if(pessoa.getSaida() == null){
                                adicionarMsg("Preencha o campo soliciado", FacesMessage.SEVERITY_INFO);
                            }
                            //decremento na quantidade de funcionarios ou visitantes
                            else{
                                if("Visitante".equals(pessoa.getFuncao())){
                                    visFunc[0]-- ;
                                }
                                else if("Funcionario".equals(pessoa.getFuncao())){
                                    visFunc[1]--;
                                }
                                person = pessoa;
                                pessoa = new Pessoa();
                            }
                        }
                        else{
                            person = pessoa;
                            pessoa = new Pessoa();
                        }
                    } 
                }
                
            }
        }
        //muda a tela de volta para a inserção ou edição de registros
        mudarParaEdita();
    }
    
    //função para editar um registro já existente
    public void editar(Pessoa pessoa){
        this.pessoa = pessoa;
        mudarParaEdita();
    }
    
    //função para adicionar o horario de saida de um registro existente
    public void adicionarSaida(Pessoa pessoa){
        this.pessoa = pessoa;
        mudarParaSaida();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void adicionarMsg(String mensagem, FacesMessage.Severity tipoErro){
        FacesMessage fmsg = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fmsg);
    }
    
    
    //funções para controle de estado de tela
    public boolean isEdita(){
        return "edita".equals(estadoTela);
    }
    public boolean isSaida(){
        return "saida".equals(estadoTela);
    }
    public void mudarParaEdita(){
        estadoTela = "edita";
    }
    public void mudarParaSaida(){
        estadoTela = "saida";   
    }
    
    public int getVisitantes(){
        return visFunc[0];
    }
    public int getFuncionarios(){
        return visFunc[1];
    }
    
}
