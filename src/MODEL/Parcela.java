/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.DAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parcela implements ClasseInterface {

    private int id;

    private int idDespesa;
    private String nome;
    private double valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    private boolean pago;
    private boolean agendado;
    private String status;

    private int n;
    private int nTotal;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    private DAO dao;

    private static int total;

    public int getNTotal() {
        return this.nTotal;
    }

    public void setNTotal(int nTotal) {
        this.nTotal = nTotal;
    }
    public static String[] getCampos() {
        String[] campos = new String[10];
        campos[0] = "ID: ";
        campos[1] = "ID DA DESPESA: ";
        campos[2] = "DATA DE VENCIMENTO (DD/MM/YYYY): ";
        campos[3] = "VALOR: ";
        return campos;
    }

    public boolean criar(DAO dao, Usuario user, Object vetor[]) {
        if (user != null) {
            return criar(dao, vetor);
        }
        return criar(dao, vetor);
    }

    public boolean criar(DAO dao, Object vetor[]) {
        this.dao = dao;
        boolean alterado = false;

        if (vetor[0] != null) {
            this.idDespesa = (int) vetor[0];
            if (vetor[1] != null) {
                this.dataVencimento = (LocalDate) vetor[1];
                if (vetor[2] != null) {
                    this.valor = (double) vetor[2];
                    if (vetor[3] != null) {
                        this.n = (int) vetor[3];
                    }
                    alterado = true;

                    // Atribui o ID único e define as datas de criação e modificação
                    this.id = ++total;
                    this.dataCriacao = LocalDate.now();
                    this.dataModificacao = null; // Nenhuma modificação inicial
                    this.pago = false;
                    this.agendado = false;
                    this.status = this.isVencida() ? "VENCIDA" : "PENDENTE";
                }
            }
        }

        return alterado;
    }
    public boolean isVencida() {
        return this.dataVencimento != null && this.dataVencimento.isBefore(LocalDate.now());
    }
    public boolean deletar() {
        --total;
        return true;
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        resultado.append("ID: ").append(this.id).append("\n");

        if (this.nome != null && !this.nome.isEmpty()) {
            resultado.append("Nome: ").append(this.nome).append("\n");
        }

        resultado.append("Valor: ").append(this.valor).append("\n");

        if (this.dataVencimento != null) {
            resultado.append("Data de Vencimento: ").append(this.dataVencimento.format(formatter)).append("\n");
        }

        if (this.dataPagamento != null) {
            resultado.append("Data de Pagamento: ").append(this.dataPagamento.format(formatter)).append("\n");
        }

        resultado.append("Pago: ").append(this.pago ? "Sim" : "Não").append("\n");
        resultado.append("Agendado: ").append(this.agendado ? "Sim" : "Não").append("\n");

        if (this.status != null && !this.status.isEmpty()) {
            resultado.append("Status: ").append(this.status).append("\n");
        }

        resultado.append("Parcela: ").append(this.n).append(" de ").append(this.getNTotal()).append("\n");

        resultado.append("\n");
        return resultado.toString();
    }
    public void pagar(){
        LocalDate hoje = LocalDate.now();
        this.setPago(true);
        this.setDataPagamento(hoje);
        this.setStatus("PAGA");
    }
    public void update(Object vetor[]) {
        System.out.println("CHAMOU A FUNÇÃO UPDATE PARA PAGAMENTO");
        boolean alterou = false;

        // Atualiza a data de modificação caso tenha havido alguma alteração
        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

    public void atualizarDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean isAgendado() {
        return agendado;
    }

    public void setAgendado(boolean agendado) {
        this.agendado = agendado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
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

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Parcela.total = total;
    }

}
