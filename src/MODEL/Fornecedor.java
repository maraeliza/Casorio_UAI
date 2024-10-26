/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.DAO;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fornecedor implements ClasseInterface {

    private int id;
    private String nome;
    private String cnpj;
    private String telefone;
    private double valorAPagar;
    private int parcelas;
    private int estado; // "pago" ou "em pagamento"
    private boolean quitado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public static int total;
    private DAO dao;

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        atualizarDataModificacao();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
        atualizarDataModificacao();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        atualizarDataModificacao();
    }

    public double getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
        atualizarDataModificacao();
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        if (parcelas != this.parcelas) {
            this.parcelas = parcelas;
        }

        atualizarDataModificacao();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
        atualizarDataModificacao();
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return this.dataModificacao;
    }

    @Override
    public void atualizarDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    public boolean criar(DAO dao, Usuario user, Object vetor[]) {
        return criar(dao, vetor);
    }

    @Override
    public boolean criar(DAO dao, Object vetor[]) {
        this.dao = dao;
        if (this.dao != null) {
            this.nome = (String) vetor[0];
            this.cnpj = (String) vetor[1];
            this.telefone = (String) vetor[2];
            this.dataCriacao = LocalDate.now();
            this.dataModificacao = null;
            this.id = ++total; // Aumenta o contador de IDs
        }

        return true;
    }

    public static String[] getCampos() {
        String[] campos = new String[10];
        campos[0] = "ID: ";
        campos[1] = "nome: ";
        campos[2] = "CNPJ: ";
        campos[3] = "telefone: ";
        return campos;
    }

    public void update(Object vetor[]) {
        System.out.println("CHAMOU A FUNCAO UPDATE");
        boolean alterado = false;

        // Verifica e atualiza o nome
        if (vetor[1] != null && !((String) vetor[1]).trim().isEmpty()) {
            this.nome = (String) vetor[1];
            alterado = true;
        }

        // Verifica e atualiza o CNPJ
        if (vetor[2] != null && !((String) vetor[2]).trim().isEmpty()) {
            this.cnpj = (String) vetor[2];
            alterado = true;
        }

        // Verifica e atualiza o telefone
        if (vetor[3] != null && !((String) vetor[3]).trim().isEmpty()) {
            this.telefone = (String) vetor[3];
            alterado = true;
        }

        if (alterado) {
            this.atualizarDataModificacao(); // Atualiza a data de modificação
        }
    }

    @Override
    public boolean deletar() {
        --total;
        return true;
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        resultado.append("\nID: ").append(this.id).append("\n");

        if (this.nome != null && !this.nome.isEmpty()) {
            resultado.append("Nome: ").append(this.nome).append("\n");
        }

        if (this.cnpj != null && !this.cnpj.isEmpty()) {
            resultado.append("CNPJ: ").append(this.cnpj).append("\n");
        }

        if (this.telefone != null && !this.telefone.isEmpty()) {
            resultado.append("Telefone: ").append(this.telefone).append("\n");
        }
        if (this.valorAPagar > 0) {
            resultado.append("Valor a Pagar: ").append(this.valorAPagar).append("\n");

            if (this.parcelas > 0) {
                resultado.append("Parcelas: ").append(this.parcelas).append("\n");
            }

            String estadoDescricao = this.quitado ? "Pago" : "Não pago";
            resultado.append("Estado: ").append(estadoDescricao).append("\n");

        }

        if (this.dataCriacao != null) {
            resultado.append("Data de Criação: ").append(this.dataCriacao.format(formatter)).append("\n");
        }

        if (this.dataModificacao != null) {
            resultado.append("Data de Modificação: ").append(this.dataModificacao.format(formatter));
        }
        resultado.append("\n");
        return resultado.toString();
    }

    public boolean isQuitado() {
        return quitado;
    }

    public void setQuitado(boolean quitado) {
        this.quitado = quitado;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Fornecedor.total = total;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

}
