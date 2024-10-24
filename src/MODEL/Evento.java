/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Mara
 */
import java.time.LocalDate;

public class Evento implements ClasseInterface {

    // Atributos da classe
    private int id;

    private String nome;
    private Cerimonial cerimonial;
    private int idIgreja;
    private int idCerimonial;
    private int idCartorio;
    private Igreja igreja;
    private Cartorio cartorio;
    private int idNoiva;
    private Pessoa noiva;
    private int idNoivo;
    private Pessoa noivo;
    private LocalDate data;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static int total;

    public int getIdIgreja() {
        return idIgreja;
    }

    public void setIdIgreja(int idIgreja) {
        this.idIgreja = idIgreja;
    }

    public int getIdCerimonial() {
        return idCerimonial;
    }

    public void setIdCerimonial(int idCerimonial) {
        this.idCerimonial = idCerimonial;
    }

    public int getIdCartorio() {
        return idCartorio;
    }

    public void setIdCartorio(int idCartorio) {
        this.idCartorio = idCartorio;
    }

    public int getIdNoiva() {
        return idNoiva;
    }

    public void setIdNoiva(int idNoiva) {
        this.idNoiva = idNoiva;
    }

    public int getIdNoivo() {
        return idNoivo;
    }

    public void setIdNoivo(int idNoivo) {
        this.idNoivo = idNoivo;
    }

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Evento.total = total;
    }

    public Cerimonial getCerimonial() {
        return this.cerimonial;
    }

    public void setCerimonial(Cerimonial cerimonial) {
        this.cerimonial = cerimonial;
        this.dataModificacao = LocalDate.now();
    }

    public Igreja getIgreja() {
        return this.igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
        this.dataModificacao = LocalDate.now();
    }

    public Cartorio getCartorio() {
        return this.cartorio;
    }

    public void setCartorio(Cartorio cartorio) {
        this.cartorio = cartorio;
        this.dataModificacao = LocalDate.now();
    }

    public Pessoa getNoiva() {
        return this.noiva;
    }

    public void setNoiva(Pessoa noiva) {
        if (noiva != null
                && noiva.getTipo().toUpperCase().equals("NOIVA")) {
            this.noiva = noiva;
            this.idNoiva = this.noiva.getId();
            this.dataModificacao = LocalDate.now();
        }

    }

    public Pessoa getNoivo() {
        return this.noivo;
    }

    public void setNoivo(Pessoa noivo) {
        if (noivo != null
                && noivo.getTipo().toUpperCase().equals("NOIVO")) {
            this.noivo = noivo;
            this.idNoivo = this.noivo.getId();
            this.dataModificacao = LocalDate.now();
        }
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
        this.dataModificacao = LocalDate.now();
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return this.dataModificacao;
    }

    public static String[] getCampos() {
        String[] campos = new String[11]; // Aumentando o tamanho do array para 11
        campos[0] = "ID: ";
        campos[1] = "Data: ";
        campos[2] = "ID da Igreja: ";
        campos[3] = "ID do Cartório: ";
        campos[4] = "ID do Cerimonial: ";
        campos[5] = "Nome: ";
        return campos;
    }

    public boolean criar(Usuario user, Object[] vetor) {
        return criar(vetor);

    }

    public boolean criar(Object[] vetor) {
        boolean alterado = false;

        if (vetor[0] != null && vetor[0] instanceof String) {
            String data = (String) vetor[0]; // Data de nascimento como string
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            try {
                // Converte a string de data para LocalDate
                this.data = LocalDate.parse(data, formato);
                alterado = true; // Indica que o campo de data foi alterado
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido: " + e.getMessage());
            }
        }
        if (vetor[4] != null && vetor[4] instanceof String) {
            this.nome = (String) vetor[4]; // Nome

        }

        if (alterado) {
            this.id = ++total;
            this.dataCriacao = LocalDate.now();
            this.dataModificacao = null;
        }

        return alterado;
    }

    // Método para atualizar o evento
    public void update(Cerimonial cerimonial, Igreja igreja, Cartorio cartorio, Pessoa noiva, Pessoa noivo, LocalDate data) {
        boolean alterou = false;

        if (cerimonial != null) {
            this.cerimonial = cerimonial;
            alterou = true;
        }

        if (igreja != null) {
            this.igreja = igreja;
            alterou = true;
        }

        if (cartorio != null) {
            this.cartorio = cartorio;
            alterou = true;
        }

        if (noiva != null) {
            this.noiva = noiva;
            alterou = true;
        }

        if (noivo != null) {
            this.noivo = noivo;
            alterou = true;
        }

        if (data != null) {
            this.data = data;
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

    // Método para deletar um evento
    public void deletar() {
        --total;
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        resultado.append("\n\nID: ").append(this.id);
        resultado.append("\nNome: ").append(this.nome != null ? this.nome : "N/A");
        resultado.append("\nCerimonial: ").append(this.cerimonial != null ? this.cerimonial.getNome() : "N/A");
        resultado.append("\nIgreja: ").append(this.igreja != null ? this.igreja.getNome() : "N/A");
        resultado.append("\nCartório: ").append(this.cartorio != null ? this.cartorio.getNome() : "N/A");
        resultado.append("\nNoiva: ").append(this.noiva != null ? this.noiva.getNome() : "N/A");
        resultado.append("\nNoivo: ").append(this.noivo != null ? this.noivo.getNome() : "N/A");

        // Formatação da data do evento
        if (this.data != null) {
            resultado.append("\nData do Evento: ").append(this.data.format(formatter));
        } 
        // Formatação da data de criação
        if (this.dataCriacao != null) {
            resultado.append("\nData de Criação: ").append(this.dataCriacao.format(formatter));
        } 
        // Formatação da data de modificação
        if (this.dataModificacao != null) {
            resultado.append("\nData da Última Modificação: ").append(this.dataModificacao.format(formatter));
        } 

        return resultado.toString();
    }

    public void update(Object vetor[]) {
        boolean alterou = false;

        if (vetor[0] != null && vetor[0] instanceof LocalDate) {
            LocalDate novaData = (LocalDate) vetor[0];
            if (!novaData.equals(this.data)) {
                this.data = novaData;
                alterou = true;
            }
        }

        if (vetor[1] != null) {
            Igreja novaIgreja = (Igreja) vetor[1];
            if (novaIgreja != null && !novaIgreja.equals(this.igreja)) {
                this.igreja = novaIgreja;
                alterou = true;
            }
        }

        if (vetor[2] != null) {
            Cartorio novoCartorio = (Cartorio) vetor[2];
            if (novoCartorio != null && !novoCartorio.equals(this.cartorio)) {
                this.cartorio = novoCartorio;
                alterou = true;
            }
        }

        if (vetor[3] != null) {
            Pessoa novaNoiva = (Pessoa) vetor[3];
            if (novaNoiva != null && !novaNoiva.equals(this.noiva)) {
                this.noiva = novaNoiva;
                alterou = true;
            }
        }

        if (vetor[4] != null) {
            Pessoa novoNoivo = (Pessoa) vetor[4];
            if (novoNoivo != null && !novoNoivo.equals(this.noivo)) {
                this.noivo = novoNoivo;
                alterou = true;
            }
        }

        if (vetor[5] != null) {
            int novoIdIgreja = (int) vetor[5];
            if (novoIdIgreja != this.idIgreja) {
                this.idIgreja = novoIdIgreja;
                alterou = true;
            }
        }

        if (vetor[6] != null) {
            int novoIdCerimonial = (int) vetor[6];
            if (novoIdCerimonial != this.idCerimonial) {
                this.idCerimonial = novoIdCerimonial;
                alterou = true;
            }
        }

        if (vetor[7] != null) {
            int novoIdCartorio = (int) vetor[7];
            if (novoIdCartorio != this.idCartorio) {
                this.idCartorio = novoIdCartorio;
                alterou = true;
            }
        }

        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

}
