/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import CONTROLLER.DAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 *
 * @author CAUPT - ALUNOS
 */

    
public class ConvidadoFamilia implements ClasseInterface {
    
    private int id;
    private String nome;
    private String acesso;  
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private DAO dao;
    
    public static int total;
    
    public static String[] getCampos() {
        String[] campos = new String[10]; // Somente 3 campos necessários
        campos[0] = "ID: ";
        campos[1] = "Nome da Familia: ";
        

        return campos;
    }
    private String gerarAcesso() {
        String primeiroNomeNoivo = this.dao.getNoivos(0).getNome();
        String primeiroNomeNoiva = this.dao.getNoivos(1).getNome();
        LocalDate dataCasamento = ((Evento) this.dao.getItemByID(5,1)).getData();
        String diaMesAno = String.format("%02d%02d%d", dataCasamento.getDayOfMonth(), dataCasamento.getMonthValue(), dataCasamento.getYear());
        String letrasAleatorias = gerarLetrasAleatorias(4);
        return primeiroNomeNoivo + primeiroNomeNoiva + diaMesAno + letrasAleatorias;
    }

   
    private String gerarLetrasAleatorias(int tamanho) {
        String letras = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(tamanho);
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
            
            
        }
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nomeFamilia) {
        this.nome = nomeFamilia;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
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


    public boolean criar(DAO dao, Object[] vetor) {
        System.out.println("ADICIONANDO FAMILIA "+vetor[0]);
        this.dao = dao;
       
        this.nome = (String) vetor[0];
        this.acesso = this.gerarAcesso();
        
        this.dataCriacao = LocalDate.now();
        this.dataModificacao = null;
        this.id = ++total; // Aumenta o contador de IDs
        return true;
 
    }

    public void update(Object vetor[]) {
        boolean alterou = false;

        // Atualiza o nome da familia
        if (vetor[1] != null && vetor[1] instanceof String) {
            String nmFamilia = (String) vetor[1];
            if (!nmFamilia.isEmpty()) {
                this.nome = nmFamilia;
                alterou = true;
            }
        }

        // Atualiza o acesso
        if (vetor[2] != null && vetor[2] instanceof String) {
            String acesso = (String) vetor[2];
            if (!acesso.isEmpty()) {
                this.acesso = acesso;
                alterou = true;
            }
        }
        if(alterou){
            atualizarDataModificacao();
        }
        }
    
    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Adiciona informações da pessoa
        resultado.append("\n\nFamilia ").append(this.id);
        resultado.append("\nNome da Familia: ").append(this.nome);


        // Verifica e adiciona o Acesso
        if (this.acesso != null && !this.acesso.isEmpty()) {
            resultado.append("\nAcesso da Familia: ").append(this.acesso);
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
    
    public boolean deletar() {
        --ConvidadoFamilia.total;
        return true;
    }

}
