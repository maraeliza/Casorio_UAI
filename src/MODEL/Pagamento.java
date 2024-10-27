/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.DAO;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pagamento implements ClasseInterface {

    private int id;
    private LocalDate data;
    private int idPessoa;
    private Pessoa pessoa;

    private int idDespesa;
    private Despesa despesa;

    private int idParcela;
    private Parcela parcela;

    private String descricao;
    private String nome;

    private int idFornecedor;
    private Fornecedor fornecedor;

    private double valor;

    private int nParcela;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    private DAO dao;
    private static int total;


    private int idUser;
    private Usuario user;

    public static String[] getCampos() {
        String[] campos = new String[10];
        campos[0] = "ID: ";
        campos[1] = "ID DO FORNECEDOR (0 PARA NENHUM FORNECEDOR): ";
        campos[2] = "DATA DO PAGAMENTO (DD/MM/YYYY): ";
        campos[3] = "DESCRIÇÃO: ";
        campos[4] = "VALOR: ";

        return campos;
    }

    public boolean trocarDespesa(int id, Despesa despesa) {

        //checa se o id é diferente
        if ((this.getIdDespesa() == 0 || this.getIdDespesa() != id)
                && despesa != null) {
            this.setIdDespesa(id);
            this.setDespesa(despesa);

            return true;
        }
        return false;
    }

    public boolean trocarParcela(int id, Parcela parcela) {

        //checa se o id é diferente
        if ((this.getIdParcela() == 0 || this.getIdParcela() != id)
                && parcela != null) {

            this.setIdParcela(id);
            this.setParcela(parcela);

            return true;
        }
        return false;
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

    public boolean criar(DAO dao, Object vetor[]) {
        this.dao = dao;
        boolean alterado = false;
        if (this.dao != null) {
            System.out.println("CRIANDO UM NOVO PAGAMENTO!");
            System.out.println("Dados: " + vetor[0] + " " + vetor[1] + " " + vetor[2] + " " + vetor[3] + " " + vetor[4]);

            System.out.println("Dados: " + vetor[4] + " " + vetor[5] + " " + vetor[6]);

            Pessoa pessoa = this.user.getPessoa();

            if (pessoa != null) {
                System.out.println("Pessoa encontrada " + pessoa.getNome());
                System.out.println("Definindo a pessoa");
                this.trocarPessoa(pessoa.getId(), pessoa);

                int idFornecedor = 0;

                if (vetor[0] instanceof String) {
                    // Converte o elemento para String e então para int
                    idFornecedor = Util.stringToInt((String) vetor[0]);
                } else if (vetor[0] instanceof Integer) {
                    // Faz o cast direto para int se o elemento já for Integer
                    idFornecedor = (Integer) vetor[0];
                } else {
                    throw new IllegalArgumentException("Tipo não suportado no vetor[0]");
                }

                System.out.println("pagamento detectado, encontrando fornecedor de id " + idFornecedor);
                if (idFornecedor != 0) {
                    Fornecedor fornecedor = (Fornecedor) dao.getItemByID(4, idFornecedor);

                    if (fornecedor != null) {
                        System.out.println("fornecedor encontrado " + fornecedor.getNome());
                        this.trocarFornecedor(idFornecedor, fornecedor);

                    }
                }
                if (vetor[0] != null && vetor[1] != null && vetor[2] != null && vetor[3] != null) {
                    System.out.println("Definindo os dados do pagamento: DATA " + vetor[1]);

                    if (vetor[1] instanceof String) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String dateStr = (String) vetor[1];
                        this.data = LocalDate.parse(dateStr, formatter);// Data do pagamento
                    } else if (vetor[1] instanceof LocalDate) {
                        // Faz o cast direto para int se o elemento já for Integer
                        this.data = (LocalDate) vetor[1];
                    } else {
                        throw new IllegalArgumentException("Tipo não suportado no vetor[0]");
                    }

                    this.descricao = (String) vetor[2]; // Descrição

                    double valor;

                    if (vetor[3] instanceof String) {
                        valor = Util.stringToDouble((String) vetor[3]);
                    } else if (vetor[3] instanceof Double) {
                        valor = (Double) vetor[3];
                    } else if (vetor[3] instanceof Integer) {
                        valor = ((Integer) vetor[3]).doubleValue();
                    } else {
                        throw new IllegalArgumentException("Tipo não suportado no vetor[3]");
                    }
                    if (valor > 0) {
                        this.setValor(valor);
                    }

                    if (vetor[5] != null) {
                        System.out.println("Pesquisando pela despesa de id " + vetor[5]);
                        int idDespesa;
                        if (vetor[5] instanceof String) {
                            idDespesa = Util.stringToInt((String) vetor[5]);
                        } else if (vetor[5] instanceof Integer) {
                            idDespesa = (Integer) vetor[5];
                        } else {
                            throw new IllegalArgumentException("Tipo não suportado no vetor[5]");
                        }
                        Despesa despesa = (Despesa) dao.getItemByID(12, idDespesa);

                        if (despesa != null) {

                            System.out.println("Despesa encontrada " + despesa.getNome());
                            this.trocarDespesa(despesa.getId(), despesa);
                        }
                    }
                    if (vetor[6] != null) {
                        System.out.println("Pesquisando pela parcela de id " + vetor[6]);

                        int idParcela;

                        if (vetor[6] instanceof String) {
                            idParcela = Util.stringToInt((String) vetor[6]);
                        } else if (vetor[6] instanceof Integer) {
                            idParcela = (Integer) vetor[6];
                        } else {
                            throw new IllegalArgumentException("Tipo não suportado no vetor[6]");
                        }

                        Parcela parcela = (Parcela) dao.getItemByID(13, idParcela);
                        System.out.println("Pesquisando pela parcela de id " + vetor[6]);
                        if (parcela != null) {
                            System.out.println("Parcela encontrada " + parcela.getNome());
                            this.trocarParcela(parcela.getId(), parcela);
                            System.out.println("Numero da parcela " + vetor[4]);
                            int nParcela = 0;
                            if (vetor[4] instanceof String) {
                                nParcela = Util.stringToInt((String) vetor[4]);
                            } else if (vetor[4] instanceof Integer) {
                                nParcela = (Integer) vetor[4];
                            } else {
                                throw new IllegalArgumentException("Tipo não suportado no vetor[0]");
                            }
                            if (nParcela != 0) {
                                this.setnParcela(nParcela);
                            }

                        }
                    }

                    alterado = true;

                }
                if (alterado) {
                    System.out.println("Pagamento criado com sucesso !");
                    this.id = ++total;
                    this.dataCriacao = LocalDate.now();
                    this.dataModificacao = null;
                }

            }

        }

        return alterado;
    }

    public boolean deletar() {
        --total;
        return true;
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        resultado.append("\nID: ").append(this.id).append("\n");
        resultado.append("Valor Pago: ").append(this.valor).append("\n");
        if (this.descricao != null && !this.descricao.isEmpty()) {
           
            if (this.despesa != null) {
                resultado.append("DESPESA: ").append(this.despesa.getNome()).append("            ");    
                resultado.append("Valor Total: ").append(this.despesa.getValorTotal()).append("\n"); 
            }
            resultado.append("Descrição: ").append(this.descricao).append("\n");
        }

        if (this.fornecedor != null && this.fornecedor.getNome() != null && !this.fornecedor.getNome().isEmpty()) {
            resultado.append("Fornecedor: ").append(this.fornecedor.getNome()).append("\n");
        }
        if (this.data != null) {
            resultado.append("Data do Pagamento: ").append(this.data.format(formatter)).append("\n");
            if (this.parcela != null) {
                if (this.parcela.getDataVencimento() != null) {
                    resultado.append("Data de Vencimento: ")
                            .append(this.parcela.getDataVencimento().format(formatter)).append("\n");
                }
            }
        }

        if (this.pessoa != null && this.pessoa.getNome() != null && !this.pessoa.getNome().isEmpty()) {
            resultado.append("Pagador: ").append(this.pessoa.getNome()).append("\n");
        }
        
        if (this.despesa.isAgendado() || this.parcela.isAgendado()) {
            if (this.dataCriacao != null) {
                resultado.append("Data de Criação: ").append(this.dataCriacao.format(formatter)).append("\n");
            }
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
            if (this.nParcela == 1 || valor <= 0) {
                // Atualizar o estado do fornecedor
                this.fornecedor.setEstado(1);
            }
        }
    }

    public void update(Object vetor[]) {
        System.out.println("CHAMOU A FUNÇÃO UPDATE PARA PAGAMENTO");
        boolean alterou = false;
        if (vetor[1] != null || !vetor[1].equals('0')) {
            System.out.println("ATUALIZANDO FORNECEDOR");
            int idFornecedor = Util.stringToInt((String) vetor[1]);

            // Obtém o fornecedor pelo ID
            Fornecedor fornecedor = (Fornecedor) this.dao.getItemByID(4, idFornecedor);
            if (fornecedor != null) {
                this.trocarFornecedor(idFornecedor, fornecedor);
                System.out.println("Fornecedor associado com sucesso.");
            } else {
                System.out.println("Fornecedor não encontrado para o ID: " + idFornecedor);
            }
        }

        // Verifica e atualiza data do pagamento (vetor[3])
        if (vetor[2] != null && !((String) vetor[2]).isEmpty()) {
            this.data = Util.stringToDate((String) vetor[2]);
            System.out.println("Data de pagamento atualizada para: " + this.data);
            alterou = true;
        }

        // Verifica e atualiza descrição (vetor[4])
        if (vetor[3] != null && !((String) vetor[3]).isEmpty()) {
            this.descricao = (String) vetor[3];
            System.out.println("Descrição atualizada para: " + this.descricao);
            alterou = true;
        }

        // Verifica e atualiza valor (vetor[5])
        if (vetor[4] != null && !((String) vetor[4]).isEmpty()) {
            this.valor = Util.stringToDouble((String) vetor[4]);
            System.out.println("Valor atualizado para: " + this.valor);
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

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesa despesa) {
        this.despesa = despesa;
    }

    public int getIdParcela() {
        return idParcela;
    }

    public void setIdParcela(int idParcela) {
        this.idParcela = idParcela;
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public int getnParcela() {
        return nParcela;
    }

    public void setnParcela(int nParcela) {
        this.nParcela = nParcela;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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

    public int getNParcela() {
        return nParcela;
    }

    public void setParcela(int parcela) {
        this.nParcela = parcela;
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

    public boolean criar(DAO dao, Usuario user, Object vetor[]) {
        if (user != null) {
            this.idUser = user.getId();
            this.user = user;
            return criar(dao, vetor);
        }
        return criar(dao, vetor);
    }

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
}
