/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VIEW;
import CONTROLLER.DAO;
import javax.swing.JOptionPane;
import MODEL.Usuario;

/**
 *
 * @author Mara
 */
public class MenuCalendario {

    private String texto;
    private String op;
    private  boolean logou;
    private String opcoes[];
    private Usuario userLogado;
    private String listaNomeClasses[] = new String[20];

    private int nOps;
    private DAO dao;

    public MenuCalendario() {

        setLista();

    }

    private void setLista() {
        String listaClasses[] = new String[20];
        listaClasses[0] = "EVENTOS PARA HOJE";
        listaClasses[1] = "PESQUISAR EVENTO POR DATA";
        listaClasses[2] = "DESPESAS QUE VENCEM HOJE";
        listaClasses[3] = "PESQUISAR DESPESA POR DATA DE VENCIMENTO";
        listaClasses[4] = "PAGAMENTOS AGENDADOS (EM ABERTO)";
        this.listaNomeClasses = listaClasses;
    }

    private String[] getLista() {
        return this.listaNomeClasses;
    }

    private void definirTexto() {
        this.texto = "";
        this.texto += "\n\nCALENDÁRIO";
        this.texto += "\n\nEscolha a opção a seguir ";

        this.texto += this.definirOpcoes();

        this.texto += "\n\nDigite aqui o número da sua opção: ";

    }

    private String definirOpcoes() {
        String Opcoes = "";
        this.nOps = 1;
        System.out.println("definindo opcoes");

        for (int n = 0; n < this.listaNomeClasses.length; n++) {
            if (this.listaNomeClasses[n] != null) {
                Opcoes += "\n" + Util.intToString(this.nOps) + ". " + this.listaNomeClasses[n];
                this.nOps++;
            }

        }

        Opcoes += "\n" + Util.intToString(this.nOps) + ". SAIR";
        return Opcoes;
    }

    public void exibir(DAO dao, Usuario user, boolean logou) {
        this.userLogado = user;
        this.logou = logou;
        this.dao = dao;
        int o = -1;
        do {
            this.definirTexto();

            this.op = JOptionPane.showInputDialog(null, this.texto, "UaiCasórioPro", JOptionPane.QUESTION_MESSAGE);
            System.out.println(this.op);

            if (this.op != null) {
                o = Util.stringToInt(this.op);
                this.lidarEscolha(o);
            } else {
                TelaInicial menu = new TelaInicial();
                op = menu.exibir(this.dao);
            }
        } while (o != 0 && this.op != null);

    }

    private void lidarEscolha(int o) {

        if (o >= this.nOps) {
            MenuInicial menu = new MenuInicial();
            menu.exibir(this.dao, this.logou, this.userLogado);
        }

    }
}
