/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pagamento implements ClasseInterface {

    private int id;
    private LocalDate data;
    private int idPessoa;
    private Pessoa pessoa;
    private String descricao;
    private String nome;
    private int idFornecedor;
    private Fornecedor fornecedor;
    private double valor;
    private int parcela;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private static int total;

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Pagamento.total = total;
    }

    public static String[] getCampos() {
        String[] campos = new String[10];
        campos[0] = "ID: ";
        campos[1] = "ID DA PESSOA: ";
        campos[2] = "ID DO FORNECEDOR (0 PARA NENHUM FORNECEDOR): ";
        campos[3] = "DATA (DD/MM/YYYY): ";
        campos[4] = "DESCRIÇÃO: ";
        campos[5] = "VALOR: ";
        campos[6] = "PARCELA: ";
        return campos;
    }

    public boolean trocarPessoa(int idPessoa, Pessoa p) {

        //checa se o id é diferente
        if ((this.getIdPessoa() == 0 || this.getIdPessoa() != idPessoa)
                && p != null) {

            this.setPessoa(p);

            return true;
        }
        return false;
    }

    public boolean trocarFornecedor(int idFornecedor, Fornecedor fornecedor) {

        //checa se o id é diferente
        if ((this.getIdFornecedor() == 0 || this.getIdFornecedor() != idFornecedor)
                && fornecedor != null) {

            this.setFornecedor(fornecedor);

            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor forn) {
        if (forn != null) {
            this.fornecedor = forn;
            this.setIdFornecedor(this.fornecedor.getId());
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public boolean criar(Usuario user, Object vetor[]) {
        return criar(vetor);
    }

    public boolean criar(Object vetor[]) {
        System.out.println("CRIANDO UM NOVO PAGAMENTO!");
        System.out.println("Dados: " + vetor[0] + " " + vetor[1] + " " + vetor[2] + " " + vetor[3] + " " + vetor[4]);

        boolean alterado = false;

        if (vetor[0] != null && vetor[2] != null && vetor[3] != null && vetor[4] != null && vetor[5] != null) {
            System.out.println("DATA VALIDA " + (String) vetor[2]);
            this.data = Util.stringToDate((String) vetor[2]); // Data do pagamento

            this.descricao = (String) vetor[3]; // Descrição
            this.valor = Util.stringToDouble((String) vetor[4]);
            this.parcela = Util.stringToInt((String) vetor[5]);
            alterado = true;

        }
        if (alterado) {
            // Atribui o ID único e define as datas de criação e modificação
            this.id = ++total;
            this.dataCriacao = LocalDate.now();
            this.dataModificacao = null; // Nenhuma modificação inicial
        }

        return alterado;
    }

    public void atualizar() {
        // Lógica para atualizar um pagamento no banco de dados
        this.dataModificacao = LocalDate.now();
        // Atualizar informações no banco de dados
    }

    public void deletar() {
        // Lógica para deletar um pagamento do banco de dados
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        resultado.append("ID: ").append(this.id).append("\n");

        if (this.data != null) {
            resultado.append("Data: ").append(this.data.format(formatter)).append("\n");
        }

        if (this.pessoa != null && this.pessoa.getNome() != null && !this.pessoa.getNome().isEmpty()) {
            resultado.append("Pessoa: ").append(this.pessoa.getNome()).append("\n");
        }

        if (this.descricao != null && !this.descricao.isEmpty()) {
            resultado.append("Descrição: ").append(this.descricao).append("\n");
        }

        if (this.fornecedor != null && this.fornecedor.getNome() != null && !this.fornecedor.getNome().isEmpty()) {
            resultado.append("Fornecedor: ").append(this.fornecedor.getNome()).append("\n");
        }

        resultado.append("Valor: ").append(this.valor).append("\n");

        if (this.parcela > 0) {
            resultado.append("Parcela: ").append(this.parcela).append("\n");
        }

        if (this.dataCriacao != null) {
            resultado.append("Data de Criação: ").append(this.dataCriacao.format(formatter)).append("\n");
        }

        if (this.dataModificacao != null) {
            resultado.append("Data da Última Modificação: ").append(this.dataModificacao.format(formatter)).append("\n");
        }

        return resultado.toString();
    }

    // Método para verificar e atualizar o estado do pagamento
    public void verificarPagamentoAgendado() {
        LocalDate hoje = LocalDate.now();
        if (data.isEqual(hoje) && valor > 0) {
            // Lógica para atualizar o estado do pagamento
            // Se for uma parcela única ou se todas as parcelas foram pagas
            if (parcela == 1 || valor <= 0) {
                // Atualizar o estado do fornecedor
                this.fornecedor.setEstado(1);
            }
        }
    }

    public void update(Object vetor[]) {
        System.out.println("CHAMOU A FUNÇÃO UPDATE PARA PAGAMENTO");
        boolean alterou = false;

        // Verifica e atualiza data do pagamento (vetor[3])
        if (vetor[3] != null && !((String) vetor[3]).isEmpty()) {
            this.data = Util.stringToDate((String) vetor[3]);
            System.out.println("Data de pagamento atualizada para: " + this.data);
            alterou = true;
        }

        // Verifica e atualiza descrição (vetor[4])
        if (vetor[4] != null && !((String) vetor[4]).isEmpty()) {
            this.descricao = (String) vetor[4];
            System.out.println("Descrição atualizada para: " + this.descricao);
            alterou = true;
        }

        // Verifica e atualiza valor (vetor[5])
        if (vetor[5] != null && !((String) vetor[5]).isEmpty()) {
            this.valor = Util.stringToDouble((String) vetor[5]);
            System.out.println("Valor atualizado para: " + this.valor);
            alterou = true;
        }

        // Verifica e atualiza parcela (vetor[6])
        if (vetor[6] != null && !((String) vetor[6]).isEmpty()) {
            this.parcela = Util.stringToInt((String) vetor[6]);
            System.out.println("Parcela atualizada para: " + this.parcela);
            alterou = true;
        }

        // Atualiza a data de modificação caso tenha havido alguma alteração
        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

    public void atualizarDataModificacao() {

        this.dataModificacao = LocalDate.now();
    }

}
