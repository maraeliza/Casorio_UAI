/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.*;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cerimonial implements ClasseInterface {

    private int id;
    private int idUsuario;
    private int idPessoa;
    private Pessoa pessoa;
    private Usuario user;
    private String nome;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private boolean eventoVinculado;
    private DAO dao;

    public static int total;

    public Cerimonial() {
        this.eventoVinculado = false;
    }

    public boolean isEventoVinculado() {
        return eventoVinculado;
    }

    public void setEventoVinculado(boolean eventoVinculado) {
        this.eventoVinculado = eventoVinculado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public static String[] getCampos() {
        String[] campos = new String[2];
        campos[0] = "ID: ";
        campos[1] = "ID da pessoa: ";

        return campos;
    }

    public boolean criar(DAO dao, Usuario user, Object vetor[]) {
        return criar(dao, vetor);
    }

    public boolean criar(DAO dao, Object vetor[]) {
        this.dao = dao;
        boolean alterado = false;
        if (this.dao != null) {
            System.out.println("Criando cerimonial");

            int idPessoaC = Util.stringToInt((String) vetor[0]);
            System.out.println("ID DA PESSOA " + idPessoaC);
            Pessoa p = (Pessoa) this.dao.getItemByID(2, idPessoaC);
            System.out.println("NOME: " + p.getNome());
            if (p != null) {

                if (!p.isCerimonialVinculado() && p.getTipo().toUpperCase().equals("CERIMONIAL")) {
                    System.out.println("Pessoa não tem cerimonial vinculado");
                    System.out.println("Checando se a pessoa tem usuario vinculado");
                    if (p.isUserVinculado()) {

                        System.out.println("Trocando pessoa de cerimonial");
                        this.trocarPessoa(idPessoaC, p);
                        Usuario user = this.dao.getUserByIdPessoa(idPessoaC);
                        this.trocarUser(user);
                        System.out.println("criando cerimonial");
                        if (this.getPessoa() != null && this.getUser() != null) {
                            alterado = true;
                        }

                        if (alterado) {
                            this.dataCriacao = LocalDate.now();
                            this.dataModificacao = null;
                            this.id = ++total; // Supondo que 'total' é um contador de IDs
                        }
                    }

                } else {
                    Util.mostrarErro("A conta de cerimonial de " + p.getNome() + " já existe!");
                }

            } else {
                Util.mostrarErro("Pessoa de id " + idPessoaC + " não encontrada");

            }
        }

        return alterado;
    }

    /*
    public boolean criar(DAO dao,Object vetor[]) {
        boolean alterado = false;
        if (this.getPessoa() != null && this.getUser() != null) {
            alterado = true;
        }

        if (alterado) {
            this.dataCriacao = LocalDate.now();
            this.dataModificacao = null;
            this.id = ++total; // Supondo que 'total' é um contador de IDs
        }

        return alterado;
    }
     */
    public void update(Object vetor[]) {
        boolean alterou = false;
        System.out.println("Cerimonial detectado");

        int idPessoaC = Util.stringToInt((String) vetor[0]);
        System.out.println("ID DA PESSOA " + idPessoaC);
        Pessoa p = (Pessoa) this.dao.getItemByID(2, idPessoaC);
        System.out.println("NOME: " + p.getNome());
        if (p != null) {

            if (!p.isCerimonialVinculado()) {
                System.out.println("Pessoa não tem cerimonial vinculado");
                System.out.println("Checando se a pessoa tem usuario vinculado");
                if (p.isUserVinculado()) {

                    System.out.println("Trocando pessoa de cerimonial");
                    this.trocarPessoa(idPessoaC, p);
                    Usuario user = this.dao.getUserByIdPessoa(idPessoaC);
                    this.trocarUser(user);
                    System.out.println("atualizando cerimonial");
                    if (this.getPessoa() != null && this.getUser() != null) {
                        alterou = true;
                    }

                    if (alterou) {
                        this.atualizarDataModificacao();
                    }
                }

            } else {
                Util.mostrarErro("A conta de cerimonial de " + p.getNome() + " já existe!");
            }

        } else {
            Util.mostrarErro("Pessoa de id " + vetor[0] + " não encontrada");

        }

    }

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getTotal() {
        return Cerimonial.total;
    }

    public static void setTotal(int total) {
        Cerimonial.total = total;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return this.dataModificacao;
    }

    // Método para criar um novo cerimonial
    public void criar(String nome, String telefone) {
        this.id = ++total;
        this.nome = nome;

        this.dataCriacao = LocalDate.now();
        this.dataModificacao = null;
    }

    // Método para atualizar cerimonial
    public void update(String nome, String telefone) {
        boolean alterou = false;

        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
            alterou = true;
        }

        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

    // Método para atualizar a data de modificação
    public void atualizarDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    // Método para deletar cerimonial
    public boolean deletar() {
        if (this.isEventoVinculado()) {
            Util.mostrarErro("Não é possível excluir o cerimonial " + this.getNome() + ", pois ele está vinculado a um evento");
            return false;
        } else {
            --Cerimonial.total;
            return true;
        }

    }

    // Método para ler os dados do cerimonial
    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Adiciona informações do cerimonial
        resultado.append("Cerimonial ").append(this.id);
        resultado.append("\nNome: ").append(this.nome);

        // Verifica e formata a data de criação
        if (this.dataCriacao != null) {
            resultado.append("\nData de Criação: ").append(this.dataCriacao.format(formatter));
        }

        // Verifica e formata a data de modificação
        if (this.dataModificacao != null) {
            resultado.append("\nData da Última Modificação: ").append(this.dataModificacao.format(formatter));
        }

        resultado.append("\n\n");
        return resultado.toString();
    }

    public boolean trocarUser(Usuario user) {
        if (user != null) {

            if (this.getIdUsuario() == 0 && user.getId() != 0
                    || this.getIdUsuario() != user.getId() && user.getId() != 0) {

                this.setIdUsuario(user.getId());
                this.setUser(user);
                return true;
            }
        }
        return false;
    }

    public boolean trocarPessoa(int idPessoa, Pessoa p) {

        //checa se o id é diferente e se pessoa já não tem cerimonial vinculado
        if ((this.getIdPessoa() == 0 || this.getIdPessoa() != idPessoa)
                && p != null && !p.isCerimonialVinculado()) {

            if (this.getIdPessoa() > 0 && this.getPessoa() != null) {
                this.getPessoa().setCerimonialVinculado(false);
            }
            this.setIdPessoa(idPessoa);
            this.setPessoa(p);
            this.setNome(this.pessoa.getNome());

            return true;
        }
        return false;
    }
}
