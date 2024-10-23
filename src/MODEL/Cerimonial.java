/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

/**
 *
 * @author Jussie
 */
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Cerimonial implements ClasseInterface {

    public int id;
    private String nome;
    private String telefone;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public static int total;

    public static String[] getCampos() {
        String[] campos = new String[2];
        campos[0] = "ID: ";
        campos[1] = "Nome: ";
        campos[2] = "Telefone: ";

        return campos;
    }

    public boolean criar(Usuario user, Object vetor[]) {
        boolean alterado = false;

        if (vetor[0] != null) {
            this.nome = (String) vetor[0];
            alterado = true;
        }

        if (vetor[1] != null) {
            this.telefone = (String) vetor[1];
            alterado = true;
        }

        if (alterado) {
            this.dataCriacao = LocalDate.now();
            this.dataModificacao = null;
            this.id = ++total; // Supondo que 'total' é um contador de IDs
        }

        return alterado;
    }

    public boolean criar(Object vetor[]) {
        boolean alterado = false;

        if (vetor[0] != null) {
            this.nome = (String) vetor[0];
            alterado = true;
        }

        if (vetor[1] != null) {
            this.telefone = (String) vetor[1];
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

        if (vetor[0] != null) {
            String novoNome = (String) vetor[0];
            if (novoNome.length() > 0) {
                this.nome = novoNome;
                alterou = true;
            }
        }

        if (vetor[1] != null) {
            String novoTelefone = (String) vetor[1];
            if (novoTelefone.length() > 0) {
                this.telefone = novoTelefone;
                alterou = true;
            }
        }

        if (alterou) {
            this.atualizarDataModificacao();
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

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        this.telefone = telefone;
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

        if (telefone != null && !telefone.isEmpty()) {
            this.telefone = telefone;
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
    public void deletar() {
        --total;
    }

    // Método para ler os dados do cerimonial
    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Adiciona informações do cerimonial
        resultado.append("Cerimonial ").append(this.id);
        resultado.append("\nNome: ").append(this.nome);

        // Verifica e adiciona o telefone
        if (this.telefone != null && !this.telefone.isEmpty()) {
            resultado.append("\nTelefone: ").append(this.telefone);
        }

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

}
