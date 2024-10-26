/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;

import CONTROLLER.DAO;
import MODEL.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Mara
 */
public class MenuFazerPagamento {

    private String texto;
    private String vetor[];
    private DAO dao;
    private Usuario user;

    public void exibir(DAO dao, int idClasse, Usuario user) {
        this.user = user;
        this.dao = dao;
        try {
            this.definirTexto();
            this.montarPainel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void definirTexto() {
        this.texto = "\n\n FAZER PAGAMENTO ";
        this.texto += "\nEscolha a opção a seguir ";

        this.texto += "\n1. Pagar despesa";
        this.texto += "\n2. Pagar parcela de despesa";
        this.texto += "\n3. Lançar pagamento avulso";

        this.texto += "\n0. Para voltar";
        this.texto += "\n\nDigite aqui o número da sua opção: ";

    }

    public void montarPainel() {
        String result = JOptionPane.showInputDialog(null, this.texto, "UaiCasórioPro", JOptionPane.QUESTION_MESSAGE);
        if (result != null) {
            int idInserido = Util.stringToInt(result);
            if (idInserido != 0) {
                switch (idInserido) {
                    case 1:
                        this.pagarDespesa();
                        break;
                    case 2:
                        this.pagarParcela();
                        break;
                    case 3:
                        this.pagamentoAvulso();
                        break;

                    default:
                        break;
                }
            }
        } else {
            Util.mostrarErro("Pagamento cancelado!");
        }

    }

    public void pagarParcela() {
        //mostrar o painel com as despesas e pede para escolher uma despesa
        String conteudo = "\nDESPESAS PENDENTES DE PAGAMENTO\n\n";
        conteudo += this.dao.getDespesasPendentes();
        String result = JOptionPane.showInputDialog(null, conteudo, "UaiCasórioPro", JOptionPane.QUESTION_MESSAGE);
        if (result != null) {
            int idInserido = Util.stringToInt(result);
            if (idInserido != 0) {
                conteudo = "\nPARCELAS PENDENTES DE PAGAMENTO\n\n";
                conteudo += this.dao.getParcelasPendentes(idInserido);
                result = JOptionPane.showInputDialog(null, conteudo, "UaiCasórioPro", JOptionPane.QUESTION_MESSAGE);
                if (result != null) {
                    idInserido = Util.stringToInt(result);
                    if (idInserido != 0) {
                        System.out.println("parcela selecionada: " + idInserido);
                        System.out.println("realizar o pagamento!");
                        Parcela parcela = (Parcela) this.dao.getItemByID(13, idInserido);
                        if (parcela != null) {
                            parcela.pagar();
                            Util.mostrarMSG("Pagamento da parcela feito com sucesso!");
                        } else {
                            Util.mostrarMSG("Parcela não encontrada!");
                        }
                    }
                }
            }
        }

    }

    public void pagamentoAvulso() {
        Menu_CREATE menu = new Menu_CREATE();
        menu.exibir(this.dao, 11, this.user);
    }

    public void pagarDespesa() {
        String conteudo = "\nDESPESAS PENDENTES DE PAGAMENTO\n\n";
        conteudo += this.dao.getDespesasPendentes();
        String result = JOptionPane.showInputDialog(null, conteudo, "UaiCasórioPro", JOptionPane.QUESTION_MESSAGE);
        if (result != null) {
            int idInserido = Util.stringToInt(result);
            if (idInserido != 0) {
                LocalDate hoje = LocalDate.now();
                System.out.println("despesa selecionada: " + idInserido);
                System.out.println("realizar o pagamento!");
                Despesa despesa = (Despesa) this.dao.getItemByID(11, idInserido);
                if (despesa != null) {
                    despesa.pagar();
                    Util.mostrarMSG("Pagamento da despesa feito com sucesso!");
                } else {
                    Util.mostrarMSG("Despesa não encontrada!");
                }
               

            }
        }
    }

    public boolean add(String atributo) {
        for (int i = 0; i < this.vetor.length; i++) {
            if (this.vetor[i] == null) {
                this.vetor[i] = atributo;
                return true;
            }
        }
        return false;
    }

    public boolean cleanVetor() {
        for (int i = 0; i < this.vetor.length; i++) {
            if (this.vetor[i] != null) {
                this.vetor[i] = null;
                return true;
            }
        }
        return false;
    }

}
