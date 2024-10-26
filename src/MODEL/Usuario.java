/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.DAO;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author CAUPT - ALUNOS
 */
public class Usuario implements ClasseInterface {

    private int id;
    private int idPessoa;
    private Pessoa pessoa;
    private int tipo;
    private String login;
    private String senha;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private DAO dao;
    public static int total;

    public Usuario() {

        this.idPessoa = 0;
        this.setTipo(1);
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Usuario.total = total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPessoa(Pessoa pessoa) {
        if (pessoa != null) {
            this.pessoa = pessoa;
            this.pessoa.setUserVinculado(true);
        
            this.setIdPessoa(this.pessoa.getId());
        }
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public void update(Object vetor[]) {
        System.out.println("CHAMOU A FUNCAO UPDATE");
        boolean alterou = false;
        if (vetor[1] != null && !((String) vetor[1]).equals("") && ((String) vetor[1]).length() > 0) {
            int idPessoa = Util.stringToInt((String) vetor[1]);
            System.out.println("ATUALIZANDO ITEM DE ID " + idPessoa);
            if (idPessoa != 0 && this.getId() != idPessoa) {
                this.idPessoa = idPessoa;
                alterou = true;

            }
        }

        System.out.println(vetor[2] + " " + vetor[3] + " " + vetor[4]);

        if (vetor[2] != null && !((String) vetor[2]).equals("") && ((String) vetor[2]).length() > 0) {
            String login = (String) vetor[2];
            if (login.length() > 0) {
                this.login = login;
                alterou = true;

            }
        }
        if (vetor[3] != null && !((String) vetor[3]).equals("") && ((String) vetor[3]).length() > 0) {
            String senha = (String) vetor[3];
            if (senha.length() > 0) {
                this.senha = senha;
                alterou = true;

            }
        }
        if (vetor[4] != null && !((String) vetor[4]).equals("") && ((String) vetor[4]).length() > 0) {
            int tipo = Util.stringToInt((String) vetor[4]);
            if (tipo != 0) {
                this.tipo = tipo;
                alterou = true;

            }
        }
        if (alterou) {
            this.atualizarDataModificacao();
        }

    }

    public void update(Usuario user, Object vetor[]) {

    }

    public void update(DAO dao, Object vetor[]) {
        boolean alterou = false;
        int idPessoa = Util.stringToInt((String) vetor[0]);
        if (idPessoa != 0) {
            Pessoa pessoa = (Pessoa) dao.getItemByID(2, idPessoa);
            if (!pessoa.isUserVinculado() || this.getIdPessoa() != pessoa.getId()) {
                this.trocarPessoa(idPessoa, pessoa);
            }
        }
        if (vetor[1] != null) {
            int tipo = (int) vetor[1];
            if (tipo != 0) {
                this.tipo = tipo;
                alterou = true;

            }
        }
        if (vetor[2] != null) {
            String login = (String) vetor[2];
            if (login.length() > 0) {
                this.login = login;
                alterou = true;

            }
        }
        if (vetor[3] != null) {
            String senha = (String) vetor[3];
            if (senha.length() > 0) {
                this.senha = senha;
                alterou = true;

            }
        }
        if (alterou) {
            this.atualizarDataModificacao();
        }

    }

    public boolean criar(DAO dao, Object vetor[]) {
        this.dao = dao;
        boolean criou = false;
        if(this.dao != null){
            System.out.println("usuario detectado");
          
            int idP = Util.stringToInt((String) vetor[0]);
            System.out.println("ID DA PESSOA " + idP);
            Pessoa pessoa = (Pessoa) dao.getItemByID(2, idP);
            System.out.println("NOME: " + pessoa.getNome());
            if (pessoa != null) {
    
                if (!pessoa.isUserVinculado() ) {
                    System.out.println("Pessoa não tem usuario vinculado");
    
                    System.out.println("Trocando pessoa do usuario");
                    this.trocarPessoa(idP, pessoa);
                    System.out.println("criando usuario");
    
                    System.out.println("Metodo para criar usuario");
                    System.out.println("VETOR " + vetor[0] + " " + vetor[1] + " " + vetor[2]);
                    //Pessoa pessoa, String login, String senha, int tipo
                    if (vetor[0] != null && vetor[1] != null && vetor[2] != null) {
                        System.out.println("VETOR NAO NULO");
                        String login = (String) vetor[1];
                        String senha = (String) vetor[2];
    
                        if (login.length() > 0 && senha.length() > 0) {
    
                            this.id = ++total;
                            this.login = login;
                            this.senha = senha;
                            this.pessoa.setUserVinculado(true);
                            this.dataCriacao = LocalDate.now();
                            this.dataModificacao = null;
                            System.out.println("USUARIO: " + this);
                            System.out.println("TIPO: " + this.pessoa.getTipo());
                            if (this.pessoa.getTipo().toUpperCase().equals("ADMIN")
                                    || this.pessoa.getTipo().toUpperCase().equals("NOIVO")
                                    || this.pessoa.getTipo().toUpperCase().equals("NOIVA")
                                    || this.pessoa.getTipo().toUpperCase().equals("CERIMONIAL")) {
    
                                this.tipo = 1;
                            } else {
                                this.tipo = 2;
                            }
    
                            criou = true;
    
                        }
    
                    }
                } else if (pessoa.isUserVinculado()) {
                    Util.mostrarErro("A conta de usuário de " + pessoa.getNome() + " já existe!");
                }
    
            } else {
                Util.mostrarErro("Pessoa de id " + vetor[0] + " não encontrada");
    
            }
        }
        

        return criou;
    }

    public boolean criar(DAO dao, Usuario user, Object vetor[]) {

        System.out.println("Chamando o metodo criar com o user");
        return criar(dao, vetor);
    }

    public boolean checarTipo(int tipo) {
        return (tipo >= 0 && tipo <= 2);
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Adiciona informações do usuário
        resultado.append("\n\nUsuário ").append(this.id);
        resultado.append("\n Nome: ").append(this.getNome());
        resultado.append("\n Login: ").append(this.login);
        resultado.append("\n Senha: ").append(this.senha);
        resultado.append("\n Tipo: ").append(this.pessoa.getTipo());

        // Verifica e formata a data de criação
        if (this.dataCriacao != null) {
            resultado.append("\n Data de Criação: ").append(this.dataCriacao.format(formatter));
        }

        // Verifica e formata a data de modificação
        if (this.dataModificacao != null) {
            resultado.append("\n Data da Última Modificação: ").append(this.dataModificacao.format(formatter));
        }

        return resultado.toString();
    }

    public static String[] getCampos() {

        String[] campos = new String[5];
        campos[0] = "ID: ";
        campos[1] = "ID DA PESSOA: ";
        campos[2] = "Login: ";
        campos[3] = "Senha: ";
        return campos;
    }

    public void atualizar() {
        this.dataModificacao = LocalDate.now();
    }

    public boolean deletar() {
        --total;
        this.pessoa.setUserVinculado(false);
        return true;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getNome() {
        return this.pessoa.getNome();
    }

    public int getId() {
        return this.id;
    }

    public int getTipo() {
        return this.tipo;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void atualizarDataModificacao() {

        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public static int getTotalUsuario() {
        return total;
    }

    public static void setTotalUsuario(int totalUsuario) {
        Usuario.total = totalUsuario;
    }

    public boolean trocarPessoa(int idPessoa, Pessoa p) {

        //checa se o id é diferente e se pessoa já não tem user vinculado
        if ((this.getIdPessoa() == 0 || this.getIdPessoa() != idPessoa)
                && p != null && !p.isUserVinculado()) {

            if (this.getIdPessoa() > 0 && this.getPessoa() != null) {
                this.getPessoa().setUserVinculado(false);
            }

            this.setPessoa(p);

            return true;
        }
        return false;
    }
}
