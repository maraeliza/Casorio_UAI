/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;
import CONTROLLER.DAO;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Cartorio implements ClasseInterface {

    private int id;
    private String nome;
    private String telefone;
    private String endereco;
    private boolean eventoVinculado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private DAO dao;
    public static int total;

    public boolean criar(DAO dao,Object vetor[]) {
        boolean alterado = false;

        if (vetor[0] != null) {
            this.nome = (String) vetor[0];
            alterado = true;
        }

        if (vetor[1] != null) {
            this.telefone = (String) vetor[1];
            alterado = true;
        }

        if (vetor[2] != null) {
            this.endereco = (String) vetor[2];
            alterado = true;
        }

        if (alterado) {
            this.dataCriacao = LocalDate.now();
            this.dataModificacao = null;
            this.id = ++total; // Supondo que 'total' é um contador de IDs
        }

        return alterado;
    }
    
    @Override
    public void update(Object vetor[]) {
        boolean alterou = false;

        if (vetor[0] != null) {
            String novoNome = (String) vetor[0];
            if (novoNome.length() > 0 && !this.nome.equals(novoNome)) {
                this.nome = novoNome;
                alterou = true;
            }
        }

        if (vetor[1] != null) {
            String novoTelefone = (String) vetor[1];
            if (novoTelefone.length() > 0 && !this.telefone.equals(novoTelefone)) {
                this.telefone = novoTelefone;
                alterou = true;
            }
        }

        if (vetor[2] != null) {
            String novoEndereco = (String) vetor[2];
            if (novoEndereco.length() > 0 && !this.endereco.equals(novoEndereco)) {
                this.endereco = novoEndereco;
                alterou = true;
            }
        }

        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

    public static String[] getCampos() {
        String[] campos = new String[10];
        campos[0] = "ID: ";
        campos[1] = "Nome: ";
        campos[2] = "Telefone: ";
        campos[3] = "Endereço: ";
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
        return Cartorio.total;
    }

    public static void setTotal(int total) {
        Cartorio.total = total;
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

    // Método para criar um novo cartório
    public void criar(DAO dao, String nome, String telefone, String endereco) {
        this.id = ++total;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = null;
    }

    // Método para atualizar cartório
    public void update(String nome, String telefone, String endereco) {
        boolean alterou = false;

        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
            alterou = true;
        }

        if (telefone != null && !telefone.isEmpty()) {
            this.telefone = telefone;
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

    // Método para deletar cartório
    public boolean deletar() {
        if (this.isEventoVinculado()) {
            Util.mostrarErro("Não é possível excluir o cartório " + this.getNome() + ", pois ele está vinculado a um evento");
            return false;
        } else {

            --Igreja.total;
            return true;
        }
    }
    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Adiciona informações do cartório
        resultado.append("Cartório ").append(this.id);
        resultado.append("\nNome: ").append(this.nome);

        // Verifica e adiciona o telefone
        if (this.telefone != null && !this.telefone.isEmpty()) {
            resultado.append("\nTelefone: ").append(this.telefone);
        }

        // Verifica e adiciona o endereço
        if (this.endereco != null && !this.endereco.isEmpty()) {
            resultado.append("\nEndereço: ").append(this.endereco);
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
    public boolean isEventoVinculado() {
        return eventoVinculado;
    }

    public void setEventoVinculado(boolean eventoVinculado) {
        this.eventoVinculado = eventoVinculado;
    }
    
}
