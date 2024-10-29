/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.DAO;
import VIEW.Menu_READ;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Despesa implements ClasseInterface {

    private int id;

    private int idFornecedor;
    private Fornecedor fornecedor;

    private double valorTotal;

    private LocalDate dataPrimeiroVencimento;
    private LocalDate dataUltimoVencimento;
    private LocalDate dataAgendamento;
    private LocalDate dataQuitacao;
    private boolean pago;
    private boolean agendado;
    private boolean parcelado;
    private int nParcelas;
    private Parcela vParcelas[];

    private String descricao;
    private String nome;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    private DAO dao;
    private Usuario user;

    private static int total;

    public static String[] getCampos() {
        String[] campos = new String[10];
        campos[0] = "ID: ";
        campos[1] = "ID DO FORNECEDOR: ";

        campos[2] = "NOME DA DESPESA: ";

        campos[3] = "DESCRIÇÃO: ";
        campos[4] = "VALOR TOTAL: ";

        campos[5] = "Nº DE PARCELAS (1 se for à vista): ";
        campos[6] = "DATA DO PRIMEIRO VENCIMENTO (DD/MM/YYYY) (0 SE FOR À VISTA): ";
        return campos;
    }

    public boolean criar(DAO dao, Usuario user, Object vetor[]) {
        this.user = user;
        return criar(dao, vetor);
    }

    public boolean criar(DAO dao, Object vetor[]) {

        this.dao = dao;
        boolean alterado = false;
        if (this.dao != null) {
            
            int idFornecedor = Util.stringToInt((String) vetor[0]);
            this.trocarFornecedor(idFornecedor);

            if (vetor[0] != null && vetor[2] != null && vetor[3] != null && vetor[4] != null && vetor[5] != null) {
                if (vetor[1] != null && vetor[1] instanceof String) {
                    this.setNome(((String) vetor[1]).toUpperCase()); // Nome
                    if (vetor[2] != null && vetor[2] instanceof String) {

                        String descricao = (String) vetor[2];
                        this.setDescricao(descricao);  // descricao

                        double valorTotal = Util.stringToDouble((String) vetor[3]);
                        this.setValorTotal(valorTotal);

                        int nParcelas = Util.stringToInt((String) vetor[4]);
                        this.setnParcelas(nParcelas);
                        //checa se o pagamento não é a vista
                        if (this.getnParcelas() > 1 && !((String) vetor[5]).equals("0")) {
                            this.setParcelado(true);
                            this.setDataPrimeiroVencimento((String) vetor[5]);
                            
                        }else{
                            this.setParcelado(false);
                        }
                        this.id = total + 1;
                    }
                }

                alterado = true;

            }
            if (alterado) {

                // Atribui o ID único e define as datas de criação e modificação
                total++;
                this.dataCriacao = LocalDate.now();
                this.dataModificacao = null; // Nenhuma modificação inicial
            }

        }

        return alterado;
    }

    public void criarParcelas() {
        Double valor = this.getValorTotal() / this.getnParcelas();
        this.vParcelas = new Parcela[this.getnParcelas()];
        for (int i = 0; i < this.getnParcelas(); i++) {
            LocalDate dataVencimento = this.getDataPrimeiroVencimento().plusMonths(i);
            Object infos[] = {this.getId(), dataVencimento, valor, i + 1, this.getnParcelas(), this.getNome()};
            Parcela p = this.dao.cadastrarParcela(13, infos);

            if (p != null) {
                //add no vetor
                this.add(p);
            }

        }
        LocalDate dataVencimento = this.getDataPrimeiroVencimento().plusMonths(this.getnParcelas() - 1);
        this.setDataUltimoVencimento(dataVencimento);

    }

    public boolean add(Parcela p) {
        for (int i = 0; i < this.vParcelas.length; i++) {
            if (this.vParcelas[i] == null) {
                this.vParcelas[i] = p;
                return true;
            }
        }
        return false;
    }

    public boolean trocarFornecedor(int idFornecedor) {
        if (idFornecedor != 0) {
            Fornecedor fornecedor = (Fornecedor) this.dao.getItemByID(4, idFornecedor);

            if (fornecedor != null) {
                //checa se o id é diferente
                if (this.getIdFornecedor() == 0 || this.getIdFornecedor() != idFornecedor
                        ) {
                    this.setIdFornecedor(idFornecedor);
                    this.setFornecedor(fornecedor);
                    this.getFornecedor().atualizarValores();
                    return true;
                }
            }
        }

        return false;
    }

    public boolean deletar() {
        --total;
        return true;
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        resultado.append("\nID: ").append(this.id).append("      ");

        if (this.nome != null && !this.nome.isEmpty()) {
            resultado.append("DESPESA: ").append(this.nome).append("\n");
        }
        if (this.descricao != null && !this.descricao.isEmpty()) {
            resultado.append("Descrição: ").append(this.descricao).append("\n");
        }

        if (this.fornecedor != null && this.fornecedor.getNome() != null && !this.fornecedor.getNome().isEmpty()) {
            resultado.append("Fornecedor: ").append(this.fornecedor.getNome()).append("\n");
        }

        resultado.append("Valor Total: R$").append(String.format("%.2f", this.valorTotal)).append("\n");

        resultado.append("Pago: ").append(this.pago ? "Sim" : "Não").append("\n");
        if (!this.pago) {
            resultado.append("Pagamento Agendado: ").append(this.agendado ? "Sim" : "Não").append("\n");
            if (this.isAgendado()) {
                resultado.append("Data do Agendamento: ").append(this.dataAgendamento.format(formatter)).append("\n");

            }
            if (this.dataPrimeiroVencimento != null && !this.parcelado) {
                resultado.append("Data de Vencimento: ").append(this.dataPrimeiroVencimento.format(formatter)).append("\n");
            }
        } else {
            if (this.dataQuitacao != null) {
                resultado.append("Data de Quitação: ").append(this.dataQuitacao.format(formatter)).append("\n");
            }   
        }

        if (this.parcelado) {
            if (this.dataPrimeiroVencimento != null && !this.pago) {
                resultado.append("Data do Primeiro Vencimento: ").append(this.dataPrimeiroVencimento.format(formatter)).append("\n");
            }

            if (this.dataUltimoVencimento != null) {
                resultado.append("Data do Último Vencimento: ").append(this.dataUltimoVencimento.format(formatter)).append("\n");
            }

            resultado.append("Modo de Pagamento: Parcelado\n");
            resultado.append("Número de Parcelas: ").append(this.nParcelas).append("\n");

        } else {
            resultado.append("Modo de Pagamento: À vista\n");
        }
        if (this.agendado) {
            if (this.dataCriacao != null) {
                resultado.append("Data de Criação: ").append(this.dataCriacao.format(formatter)).append("\n");
            }
        }

        resultado.append("\n");
        return resultado.toString();
    }

    public void update(Object vetor[]) {
        boolean alterou = false;

        // Atualiza a data de modificação caso tenha havido alguma alteração
        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

    public void cancelarAgendamento() {

        this.setAgendado(false);
        this.setDataAgendamento(null);
        if (this.isParcelado()) {
            for (int p = 0; p < this.getnParcelas(); p++) {
                Parcela parcela = this.getvParcelas()[p];
                if (parcela != null && !parcela.isPago()) {
                    parcela.cancelarAgendamento();
                }

            }
        }
    }
    public boolean agendar(LocalDate dataAgendamento, boolean entrandoNoSistema) {

        if (this.isAgendado()) {
            this.cancelarAgendamento();
        } else {

            this.setAgendado(true);
            this.setDataAgendamento(dataAgendamento);
            if (this.isParcelado()) {
                for (int p = 0; p < this.getnParcelas(); p++) {
                    Parcela parcela = this.getvParcelas()[p];
                    if (parcela != null && !parcela.isPago()) {
                        parcela.agendarForce(dataAgendamento);
                    }

                }
            }
        }
        return true;

    }
    public boolean agendar(LocalDate dataAgendamento) {

        if (this.isAgendado()) {
            this.cancelarAgendamento();
        } else {

            this.setAgendado(true);
            this.setDataAgendamento(dataAgendamento);
            if (this.isParcelado()) {
                for (int p = 0; p < this.getnParcelas(); p++) {
                    Parcela parcela = this.getvParcelas()[p];
                    if (parcela != null && !parcela.isPago()) {
                        parcela.agendarForce(dataAgendamento);
                    }

                }
            }
            Util.mostrarMSG("Agendamento feito com sucesso!");
        }
        return true;

    }

    public void pagar() {
        if (!this.isPago()) {
            LocalDate hoje = LocalDate.now();
            this.setPago(true);
            this.setDataQuitacao(hoje);
            this.setAgendado(false);
            if (this.isParcelado()) {
                for (int p = 0; p < this.getnParcelas(); p++) {
                    Parcela parcela = this.getvParcelas()[p];
                    if (parcela != null && !parcela.isPago()) {
                        parcela.pagar(true);
                    }

                }
               
                Menu_READ menuVer = new Menu_READ();
                menuVer.exibir(this.dao, 11);
            } else {
                Object infos[] = {this.getIdFornecedor(), hoje, this.getDescricao(), this.getValorTotal(), 1, this.getId(), 1};
                this.dao.cadastrar(11, infos);
                Menu_READ menuVer = new Menu_READ();
                menuVer.exibir(this.dao, 11);
            }

        }

    }
    public void cancelarPagamento() {
        if (this.isPago()) {
            this.setPago(false);
            this.setDataQuitacao(null); 
            
            if (this.isParcelado()) {
                for (int p = 0; p < this.getnParcelas(); p++) {
                    Parcela parcela = this.getvParcelas()[p];
                    if (parcela != null && parcela.isPago()) {
                        parcela.cancelarPagamento(); // Método em Parcela para cancelar pagamento individual da parcela
                    }
                }
              } 
        }
    }
    

    public void pagar( boolean entrandoNoSistema) {
        if (!this.isPago()) {
            LocalDate hoje = LocalDate.now();
            this.setPago(true);
            this.setDataQuitacao(hoje);
            this.setAgendado(false);
            if (this.isParcelado()) {
                for (int p = 0; p < this.getnParcelas(); p++) {
                    Parcela parcela = this.getvParcelas()[p];
                    if (parcela != null && !parcela.isPago()) {
                        parcela.pagar(true, entrandoNoSistema);
                    }

                }
               
              
            } else {
                Object infos[] = {this.getIdFornecedor(), hoje, this.getDescricao(), this.getValorTotal(), 1, this.getId(), 1};
                this.dao.cadastrar(11, infos);
               
            }

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

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataPrimeiroVencimento() {
        return dataPrimeiroVencimento;
    }

    public void setDataPrimeiroVencimento(String dataPrimeiroVencimento) {
        this.dataPrimeiroVencimento = Util.stringToDate(dataPrimeiroVencimento);
    }

    public LocalDate getDataUltimoVencimento() {
        return dataUltimoVencimento;
    }

    public void setDataUltimoVencimento(LocalDate dataUltimoVencimento) {
        this.dataUltimoVencimento = dataUltimoVencimento;
    }

    public LocalDate getDataQuitacao() {
        return dataQuitacao;
    }

    public void setDataQuitacao(LocalDate dataQuitacao) {
        this.dataQuitacao = dataQuitacao;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public int getnParcelas() {
        return nParcelas;
    }

    public void setnParcelas(int nParcelas) {
        this.nParcelas = nParcelas;
    }

    public Parcela[] getvParcelas() {
        return vParcelas;
    }

    public void setvParcelas(Parcela[] vParcelas) {
        this.vParcelas = vParcelas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        Despesa.total = total;
    }

    public boolean isAgendado() {
        return agendado;
    }

    public void setAgendado(boolean agendado) {
        this.agendado = agendado;
    }

    public boolean isParcelado() {
        return parcelado;
    }

    public void setParcelado(boolean parcelado) {
        this.parcelado = parcelado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

}
