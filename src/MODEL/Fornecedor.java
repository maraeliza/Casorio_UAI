/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

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
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public static int total;

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

    public boolean criar(Usuario user, Object vetor[]) {
        return criar(vetor);
    }

    @Override
    public boolean criar(Object vetor[]) {
        if (vetor.length < 6) {
            return false; // Verifica se há informações suficientes
        }
        this.nome = (String) vetor[0];
        this.cnpj = (String) vetor[1];
        this.telefone = (String) vetor[2];

        if (vetor[4] instanceof String) {
            this.valorAPagar = Double.parseDouble((String) vetor[3]);
        } else {
            this.valorAPagar = (Double) vetor[3]; // Se já for Double
        }

        // Conversão de String para Integer
        if (vetor[4] instanceof String) {
            this.parcelas = Integer.parseInt((String) vetor[4]);
        } else {
            this.parcelas = (Integer) vetor[4]; // Se já for Integer
        }

        String estadoTemp = ((String) vetor[5]).toUpperCase();
        if ("PAGO".equals(estadoTemp)) {
            this.estado = 1; // Estado pago
        } else {
            this.estado = 0; // Estado em pagamento ou outro
        }

        this.dataCriacao = LocalDate.now();
        this.dataModificacao = null;
        this.id = ++total; // Aumenta o contador de IDs
        return true;
    }

    public static String[] getCampos() {

        String[] campos = new String[10];
        campos[0] = "ID: ";
        campos[1] = "nome: ";
        campos[2] = "CNPJ: ";
        campos[3] = "telefone: ";

        campos[4] = "Valor A Pagar: ";
        campos[5] = "Parcelas: ";

        campos[6] = "Estado ('pago' ou 'em aberto'): ";

        return campos;
    }

    public void update(Object vetor[]) {
        System.out.println("CHAMOU A FUNCAO UPDATE");
        boolean alterado = false;

        if (vetor.length < 6) {
            System.out.println("INFORMAÇÕES INSUFICIENTES");
            return; // Verifica se há informações suficientes
        }

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

        // Verifica e atualiza o valor a pagar
        if (vetor[4] != null) {
            try {
                this.valorAPagar = Util.stringToDouble((String) vetor[4]); // Conversão usando stringToDouble
                alterado = true;
            } catch (NumberFormatException e) {
                System.out.println("VALOR A PAGAR INVÁLIDO");
            }
        }

        // Verifica e atualiza o número de parcelas
        if (vetor[5] != null) {
            this.setParcelas(
                    Util.stringToInt((String) vetor[5])
            );
            alterado = true;
        }

        // Verifica e atualiza o estado
        String estadoTemp = ((String) vetor[6]).toUpperCase();
        if ("PAGO".equals(estadoTemp)) {
            this.estado = 1; // Estado pago
        } else {
            this.estado = 0; // Estado em pagamento ou outro
        }

        if (alterado) {
            this.atualizarDataModificacao(); // Atualiza a data de modificação
        }
    }

    @Override
    public void deletar() {
        --total; // Decrementa o total
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

        resultado.append("Valor a Pagar: ").append(this.valorAPagar).append("\n");

        if (this.parcelas > 0) {
            resultado.append("Parcelas: ").append(this.parcelas).append("\n");
        }

        String estadoDescricao = (this.estado == 1) ? "Pago" : "Não pago";
        resultado.append("Estado: ").append(estadoDescricao).append("\n");

        if (this.dataCriacao != null) {
            resultado.append("Data de Criação: ").append(this.dataCriacao.format(formatter)).append("\n");
        }

        if (this.dataModificacao != null) {
            resultado.append("Data de Modificação: ").append(this.dataModificacao.format(formatter));
        }

        return resultado.toString();
    }

}
