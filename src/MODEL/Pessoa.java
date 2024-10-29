/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.DAO;
import VIEW.Util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author CAUPT - ALUNOS
 */
public class Pessoa implements ClasseInterface {

    private int id;
    private int idade;
    private String nome;
    private String telefone;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private LocalDate nascimento;
    private boolean userVinculado;
    private boolean cerimonialVinculado;
    private boolean convidadoVinculado;
    
    private String tipo;
    public static int total;
    private DAO dao;

    public Pessoa() {
        this.userVinculado = false;
        this.cerimonialVinculado = false;
        this.convidadoVinculado = false;
    }

    public boolean isConvidadoVinculado() {
        return convidadoVinculado;
    }

    public void setConvidadoVinculado(boolean convidadoVinculado) {
        this.convidadoVinculado = convidadoVinculado;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }
        
    
    
    public boolean isCerimonialVinculado() {
        return cerimonialVinculado;
    }

    public void setCerimonialVinculado(boolean cerimonialVinculado) {
        this.cerimonialVinculado = cerimonialVinculado;
    }

    public boolean isUserVinculado() {
        return userVinculado;
    }

    public void setUserVinculado(boolean userVinculado) {
        this.userVinculado = userVinculado;
    }

    public static String[] getCampos() {
        String[] campos = new String[5];
        campos[0] = "ID: ";
        campos[1] = "NOME: ";
        campos[2] = "TELEFONE: ";
        campos[3] = "TIPO (noivo/noiva, cerimonial, etc): ";
        campos[4] = "DATA DE NASCIMENTO: ";
        return campos;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public static int getTotal() {
        return Pessoa.total;
    }

    public static void setTotal(int total) {
        Pessoa.total = total;
    }
    public void calcularIdade() {
        LocalDate hoje = LocalDate.now();
        int idade = hoje.getYear() - this.nascimento.getYear();

        // Verifica se a data de nascimento já ocorreu este ano
        if (hoje.getMonthValue() < this.nascimento.getMonthValue() || 
            (hoje.getMonthValue() == this.nascimento.getMonthValue() && hoje.getDayOfMonth() < this.nascimento.getDayOfMonth())) {
            idade--;
        }
        this.setIdade(idade);
     
    }

    public void update(Object vetor[]) {
        boolean alterou = false;

        // Atualiza o nome
        if (vetor[1] != null && vetor[1] instanceof String) {
            String nome = (String) vetor[1];
            if (!nome.isEmpty()) {
                this.nome = nome;
                alterou = true;
            }
        }

        // Atualiza o telefone
        if (vetor[2] != null && vetor[2] instanceof String) {
            String telefone = (String) vetor[2];
            if (!telefone.isEmpty()) {
                this.telefone = telefone;
                alterou = true;
            }
        }

        // Atualiza a data de nascimento (recebe como String e converte para LocalDate)
        if (vetor[3] != null && vetor[3] instanceof String) {
            String nascimentoStr = (String) vetor[3];
            try {
                // Define o formato da data esperado (por exemplo, "dd/MM/yyyy")

                this.nascimento = Util.stringToDate(nascimentoStr);
                this.calcularIdade();
                alterou = true;
            } catch (DateTimeParseException e) {
            }
        }

        // Atualiza o tipo
        if (vetor[4] != null && vetor[4] instanceof String) {
            String tipo = (String) vetor[4];
            if (!tipo.isEmpty()) {
                this.tipo = tipo;
                alterou = true;
            }
        }

        // Se alguma alteração foi feita, atualiza a data de modificação
        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

    public boolean criar(DAO dao, Usuario user, Object[] vetor) {
         return criar(dao, vetor);

    }

    public boolean criar(DAO dao, Object[] vetor) {
       boolean criado = false;
        this.dao = dao;
        if (this.dao != null) {
            if (vetor[0] != null && vetor[0] instanceof String) {
                this.nome = (String) vetor[0]; // Nome
                if (vetor[1] != null && vetor[1] instanceof String) {
                    this.telefone = (String) vetor[1]; // Telefone
                    if (vetor[2] != null && vetor[2] instanceof String) {
                        this.tipo = (String) vetor[2]; // Tipo
                        if (vetor[3] != null && vetor[3] instanceof String) {
                            String nascimentoStr = (String) vetor[3];
                            try {
                                this.nascimento = Util.stringToDate(nascimentoStr);
                                if (this.nascimento != null) {
                                    this.calcularIdade();
                                    criado = true;
                                }

                            } catch (DateTimeParseException e) {
                                Util.mostrarErro("Formato de data inválido: " + nascimentoStr);
                            }
                        }
                    }

                }

            }
            if (criado) {
                // Atribui o ID único e define as datas de criação e modificação
                this.id = ++total;
                this.dataCriacao = LocalDate.now();
                this.dataModificacao = null; // Nenhuma modificação inicial

            }
        }

        return criado;
    }

    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Adiciona informações da pessoa
        resultado.append("\nID ").append(this.id);
        resultado.append("            NOME: ").append(this.nome.toUpperCase());

        // Verifica e formata a data de nascimento
        if (this.nascimento != null) {
            resultado.append("\nData de Nascimento: ").append(this.nascimento.format(formatter));
        }
        if (this.idade != 0) {
            resultado.append("\nIdade: ").append(this.idade);
        }
        // Verifica e adiciona o telefone
        if (this.telefone != null && !this.telefone.isEmpty()) {
            resultado.append("      Telefone: ").append(this.telefone);
        }
        // Verifica e adiciona o tipo
        if (this.tipo != null && !this.tipo.isEmpty()) {
            resultado.append("\nTIPO: ").append(this.tipo.toUpperCase());
        }
        resultado.append("\nUsuário Cadastrado: ");
        // Verifica e adiciona o usuario
        if (this.isUserVinculado()) {
            resultado.append("SIM");
        } else {
            resultado.append("NÃO");
        }
        

        // Verifica e formata a data de modificação
        if (this.dataModificacao != null) {
            resultado.append("\nData da Última Modificação: ").append(this.dataModificacao.format(formatter));
        }
        resultado.append("\n");

        return resultado.toString();
    }

    public boolean deletar() {
        if (this.userVinculado) {
            Util.mostrarErro(this.getNome() + " não pode ser deletado, tem usuário vinculado!");

            return false;
        } else {
            --Pessoa.total;
            return true;
        }

    }

    public String getNome() {
        return this.nome;

    }

    public String getTipo() {
        return this.tipo.toUpperCase();

    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return this.dataModificacao;
    }

    public void atualizarDataModificacao() {

        this.dataModificacao = LocalDate.now();
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
    
}
