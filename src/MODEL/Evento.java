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
    private DAO dao;

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

    public void setCartorio(Cartorio cartorio) {
        if (cartorio != null) {
            this.cartorio = cartorio;
            this.idCartorio = this.cartorio.getId();
            this.dataModificacao = LocalDate.now();
        }
    }

    public void setCerimonial(Cerimonial cerimonial) {
        if (this.cerimonial != null) {
            this.cerimonial.setEventoVinculado(false);
        }
        if (cerimonial != null) {
            this.cerimonial = cerimonial;
            this.cerimonial.setEventoVinculado(true);
            this.idCerimonial = this.cerimonial.getId();
            this.dataModificacao = LocalDate.now();
        }
    }

    public Igreja getIgreja() {
        return this.igreja;
    }

    public void setIgreja(Igreja igreja) {
        if (igreja != null) {
            this.igreja = igreja;
            this.idIgreja = this.igreja.getId();
            this.dataModificacao = LocalDate.now();
        }

    }

    public Cartorio getCartorio() {
        return this.cartorio;
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

    public boolean criar(DAO dao, Usuario user, Object[] vetor) {
        return criar(dao, vetor);

    }

    public boolean criar(DAO dao, Object[] vetor) {
        boolean alterado = false;
        if (dao != null) {
            this.dao = dao;
            System.out.println("novo evento detectado");
            System.out.println(" " + vetor[0] + " " + vetor[1] + " " + vetor[2] + " " + vetor[3] + " " + vetor[4]);

            Pessoa noiva = dao.getNoivos(1);
            Pessoa noivo = dao.getNoivos(0);
            System.out.println("noivo encontrado " + noivo.getNome());
            System.out.println("noiva encontrada " + noiva.getNome());

            int idIgreja = Util.stringToInt((String) vetor[1]);
            if (idIgreja != 0) {

                Igreja igreja = (Igreja) dao.getItemByID(7, idIgreja);
                System.out.println("Igreja encontrada " + igreja.getNome());

                int idCartorio = Util.stringToInt((String) vetor[2]);
                if (idCartorio != 0) {
                    Cartorio cartorio = (Cartorio) dao.getItemByID(8, idCartorio);
                    System.out.println("cartorio encontradao" + cartorio.getNome());

                    int idCerimonial = Util.stringToInt((String) vetor[3]);
                    if (idCerimonial != 0) {
                        Cerimonial cerimonial = (Cerimonial) dao.getItemByID(6, idCerimonial);
                        System.out.println("cerimonial encontrado " + cerimonial.getNome());
                        if (noiva != null && noivo != null && igreja != null && cartorio != null && cerimonial != null) {

                            this.setNoiva(noiva);
                            this.setNoivo(noivo);
                            this.setIgreja(igreja);
                            this.setCartorio(cartorio);
                            this.setCerimonial(cerimonial);
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

                        }
                    }
                }

            }
        }

        return alterado;
    }

    // Método para atualizar a data de modificação
    public void atualizarDataModificacao() {
        this.dataModificacao = LocalDate.now();
    }

    @Override
    public boolean deletar() {
        if (this.getId() > 1) {
            --total;
            return true;
        } else {
            Util.mostrarErro(this.getNome() + " não pode ser deletado, pois é o evento principal!");
            return false;
        }

    }


    public String ler() {
        StringBuilder resultado = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (this.nome != null && this.nome.length() > 0) {
            resultado.append("\n\nID: ").append(this.id);
            resultado.append("  Evento: ").append(this.nome != null ? this.nome : "N/A");
            if (this.igreja != null) {
                resultado.append("\n\nIgreja: ").append(this.igreja != null ? this.igreja.getNome() : "N/A");
                resultado.append("\nEndereço da Igreja: ").append(this.igreja != null ? this.igreja.getEndereco() : "N/A");
            }
            if (this.cartorio != null) {
                resultado.append("\n\nCartório: ").append(this.cartorio != null ? this.cartorio.getNome() : "N/A");
                resultado.append("\nEndereço do Cartório: ").append(this.cartorio != null ? this.cartorio.getEndereco() : "N/A");
            }
            if (this.cerimonial != null) {
                resultado.append("\n\nCerimonial: ").append(this.cerimonial != null ? this.cerimonial.getNome() : "N/A");
            }
            if (this.noiva != null) {
                resultado.append("\nNoiva: ").append(this.noiva != null ? this.noiva.getNome() : "N/A");
            }
            if (this.noivo != null) {
                resultado.append("\nNoivo: ").append(this.noivo != null ? this.noivo.getNome() : "N/A");
            }
            resultado.append("\n");
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
        }

        return resultado.toString();
    }

    public void update(Object vetor[]) {
        /*
         *  campos[0] = "ID: ";
         *  campos[1] = "Data: ";
         *  campos[2] = "ID da Igreja: ";
         *  campos[3] = "ID do Cartório: ";
         *  campos[4] = "ID do Cerimonial: ";
         *  campos[5] = "Nome: ";
         */
        boolean alterou = false;
        // Atualiza a data de nascimento (recebe como String e converte para LocalDate)
        if (vetor[1] != null && vetor[1] instanceof String) {
            String dataStr = (String) vetor[1];
            if (dataStr.length() > 0) {
                try {
                    // Define o formato da data esperado (por exemplo, "dd/MM/yyyy")
                    this.data = Util.stringToDate(dataStr);
                    alterou = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido: " + dataStr);
                }
            }
        }
        if (vetor[5] != null && vetor[5] instanceof String) {
            String nome = (String) vetor[5];
            if (nome.length() > 0) {
                this.nome = nome;
                alterou = true;

            }

        }

        if (vetor[2] != null && vetor[2] instanceof String) {
            int idIgreja = Util.stringToInt((String) vetor[2]);
            if (idIgreja != 0) {
                Igreja igreja = (Igreja) this.dao.getItemByID(7, idIgreja); // 7 representa a classe Igreja
                this.setIgreja(igreja);
                alterou = true;
            }
        }

        if (vetor[3] != null && vetor[3] instanceof String && !vetor[3].equals("0")) {
            int idCartorio = Util.stringToInt((String) vetor[3]);
            if (idCartorio != 0) {
                Cartorio cartorio = (Cartorio) this.dao.getItemByID(8, idCartorio);
                this.setCartorio(cartorio);
                alterou = true;
            }
        }

        if (vetor[4] != null && vetor[4] instanceof String && !vetor[4].equals("0")) {
            int idCerimonial = Util.stringToInt((String) vetor[4]);
            if (idCerimonial != 0) {
                Cerimonial cerimonial = (Cerimonial) this.dao.getItemByID(6, idCerimonial);
                this.setCerimonial(cerimonial);
                alterou = true;
            }
        }

        if (alterou) {
            this.atualizarDataModificacao();
        }
    }

}
