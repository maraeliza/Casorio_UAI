/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;

import CONTROLLER.DAO;
import MODEL.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author Mara
 */
public class Menu_CREATE {

    private String nomeClasse;
    private String texto;
    private String vetor[];
    private Object valores[];
    private int nColetados;
    private Usuario userLogado;
    private DAO dao;
    private int idClasse;

    public void exibir(DAO dao, int idClasse, Usuario user) {
        this.dao = dao;
        this.vetor = new String[10];
        this.nomeClasse = this.dao.getNameClasseById(idClasse);
        this.userLogado = user;
        this.idClasse = idClasse;
        try {
            this.getTexto();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getTexto() {
        this.texto = "";
        this.cleanVetor();
        System.out.println("MENU DA CLASSE " + this.nomeClasse);

        Class<?> classe = this.dao.getClasseByID(this.idClasse);
        try {
            java.lang.reflect.Method metodo = classe.getMethod("getCampos");
            this.vetor = (String[]) metodo.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        montarPainel();
        return "";
    }

    public void montarPainel() {

        String conteudo = "\nCADASTRAR " + this.nomeClasse.toUpperCase();
        conteudo += "\n";
        this.valores = new Object[10];
        String result = "";
        this.nColetados = 0;
        for (int i = 1; i < this.vetor.length; i++) {
            if (this.vetor[i] != null && result != null) {
                conteudo = "\nCADASTRAR " + this.nomeClasse.toUpperCase();
                conteudo += "\n" + this.montarOpcoes(this.nomeClasse, i);

                conteudo += "\n\nINSIRA " + this.vetor[i].toUpperCase();
                do {
                    result = JOptionPane.showInputDialog(null, conteudo, "UaiCasórioPro", JOptionPane.QUESTION_MESSAGE);
                    if (result != null) {
                        if (result.length() > 0) {
                            this.valores[this.nColetados] = result;
                            this.nColetados++;
                        } else {
                            Util.mostrarErro("Preencha o campo!");
                        }
                    } else {
                        break;
                    }

                } while (result.length() <= 0 && result != null);

                System.out.println(this.valores[this.nColetados]);
            }
        }
        if (result != null) {
            this.dao.cadastrar(this.idClasse, this.valores, this.userLogado);
            Menu_READ menuVer = new Menu_READ();
            menuVer.exibir(this.dao, this.idClasse);
        }

    }

    public String montarPainelUsuarios() {
        String conteudo = "";
        conteudo += "\nNOMES DE PESSOAS SEM USUÁRIOS VINCULADOS:";
        conteudo += this.dao.getNomesPessoasSemUsers();
        return conteudo;
    }

    public String montarOpcoes(String nomeClasse, int i) {
        nomeClasse = nomeClasse.toUpperCase();
        String conteudo = "";
        switch (nomeClasse) {
            case "USUÁRIOS" -> {
                switch (i) {
                    case 1 -> {
                        if (this.dao.getTotalClasse(2) > 1) {
                            conteudo += "\nID E NOME DAS PESSOAS:";
                        } else {
                            conteudo += "\nID E NOME DA PESSOA:";
                        }
                        conteudo += this.dao.getNomesPessoasSemUsers();
                        return conteudo;
                    }
                    default -> {
                        break;
                    }
                }
            }
            case "PAGAMENTO" -> {
                switch (i) {
                    case 1 -> {
                        if (this.dao.getTotalClasse(2) > 1) {
                            conteudo += "\nID E NOME DAS PESSOAS:";
                        } else {
                            conteudo += "\nID E NOME DA PESSOA:";
                        }
                        conteudo += this.dao.getNomes(2);
                        return conteudo;
                    }
                    case 2 -> {
                        if (this.dao.getTotalClasse(4) > 1) {
                            conteudo += "\nID E NOME DOS FORNECEDORES:";
                        } else {
                            conteudo += "\nID E NOME DO FORNECEDOR:";
                        }

                        conteudo += this.dao.getNomes(4);
                        return conteudo;

                    }

                    default -> {
                        break;
                    }
                }
            }

            case "EVENTO" -> {
                switch (i) {
                    case 2 -> {
                        if (this.dao.getTotalClasse(7) > 1) {
                            conteudo += "\nID E NOME DAS IGREJAS:";
                        } else {
                            conteudo += "\nID E NOME DA IGREJA:";
                        }
                        conteudo += this.dao.getNomes(7);
                    }
                    case 3 -> {
                        if (this.dao.getTotalClasse(8) > 1) {
                            conteudo += "\nID E NOME DOS CARTÓRIOS:";
                        } else {
                            conteudo += "\nID E NOME DO CARTÓRIO:";
                        }

                        conteudo += this.dao.getNomes(8);
                    }
                    case 4 -> {
                        if (this.dao.getTotalClasse(6) > 1) {
                            conteudo += "\nID E NOME DOS CERIMONIAIS:";
                        } else {
                            conteudo += "\nID E NOME DO CERIMONIAL:";
                        }
                        conteudo += this.dao.getNomes(6);
                    }
                    default -> {
                        break;
                    }
                }
            }

            default -> {
                break;
            }

        }

        return conteudo;

    }

    public boolean add(String p) {
        for (int i = 0; i < this.vetor.length; i++) {
            if (this.vetor[i] == null) {
                this.vetor[i] = p;
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
