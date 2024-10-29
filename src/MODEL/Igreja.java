/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Jussie
 */
import CONTROLLER.DAO;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Igreja implements ClasseInterface {

    private int id;
    private String nome;
    private String endereco;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    private boolean eventoVinculado;

    public static int total;
    private DAO dao;

    public static String[] getCampos() {
        String[] campos = new String[3]; // Somente 3 campos necessários
        campos[0] = "ID: ";
        campos[1] = "Nome: ";
        campos[2] = "Endereço: ";

        return campos;
    }

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getTotal() {
        return Igreja.total;
    }

    public static void setTotal(int total) {
        Igreja.total = total;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.dataModificacao = LocalDate.now();
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return this.dataModificacao;
    }

    public boolean criar(DAO dao, Usuario user, Object vetor[]) {

        return criar(dao, vetor);
    }

    public boolean criar(DAO dao, Object vetor[]) {
        boolean alterado = false;

        if (vetor[0] != null) {
            this.nome = (String) vetor[0];
            alterado = true;
        }

        if (vetor[1] != null) {
            this.endereco = (String) vetor[1];
            alterado = true;
        }

        if (alterado) {
            this.dataCriacao = LocalDate.now();
            this.dataModificacao = null;
            this.id = ++total; // Supondo que 'total' é um contador de IDs
        }

        return alterado;
    }

    public void update(Object vetor[]) {
        boolean alterou = false;

        if (vetor[1] != null) {
            String novoNome = (String) vetor[1];
            if (novoNome.length() > 0) {
                this.nome = novoNome;
                alterou = true;
            }
        }

        if (vetor[2] != null) {
            String novoEndereco = (String) vetor[2];
            if (novoEndereco.length() > 0) {
                this.endereco = novoEndereco;
                alterou = true;
            }
        }

        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

    // Método para criar uma nova igreja
    public void criar(String nome, String endereco) {
        this.id = ++total;
        this.nome = nome;
        this.endereco = endereco;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = null;
    }

    // Método para atualizar igreja
    public void update(String nome, String endereco) {
        boolean alterou = false;

        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
            alterou = true;
        }

        if (endereco != null && !endereco.isEmpty()) {
            this.endereco = endereco;
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
    @Override
    // Método para deletar igreja
    public boolean deletar() {
        if (this.isEventoVinculado()) {
            Util.mostrarErro("Não é possível excluir a Igreja " + this.getNome() + ", pois está vinculada a um evento");
            return false;
        } else {

            --Igreja.total;
            return true;
        }
    }

    // Método para ler os dados da igreja
    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Adiciona informações da igreja
        resultado.append("Igreja ").append(this.id);
        resultado.append("\nNome: ").append(this.nome);
        resultado.append("\nEndereço: ").append(this.endereco);

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

    public boolean isEventoVinculado() {
        return eventoVinculado;
    }

    public void setEventoVinculado(boolean eventoVinculado) {
        this.eventoVinculado = eventoVinculado;
    }

}
