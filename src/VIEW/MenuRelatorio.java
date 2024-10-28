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
public class MenuRelatorio {

    private String texto;
    private String op;
    private boolean logou;
    private String opcoes[];
    private Usuario userLogado;
    private String listaNomeClasses[] = new String[20];

    private int nOps;
    private DAO dao;

    public MenuRelatorio() {

        setLista();

    }

    private void setLista() {
        String listaClasses[] = new String[20];
        listaClasses[0] = "RECADOS";
        listaClasses[1] = "IMPRIMIR CONVITE INDIVIDUAL";
        listaClasses[2] = "IMPRIMIR CONVITE FAMÍLIA";
        listaClasses[3] = "PAGAMENTOS DOS NOIVOS";
        listaClasses[4] = "CONVIDADOS";
        listaClasses[5] = "CONVIDADOS CONFIRMADOS";
        this.listaNomeClasses = listaClasses;
    }

    private String[] getLista() {
        return this.listaNomeClasses;
    }

    private void definirTexto() {
        this.texto = "";
        this.texto += "\n\nRELATÓRIOS";
        this.texto += "\n\nEscolha a opção a seguir ";

        this.texto += this.definirOpcoes();

        this.texto += "\n\nDigite aqui o número da sua opção: ";

    }

    private String definirOpcoes() {
        String Opcoes = "";
        this.nOps = 1;

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

        if (o == 1) {
            Menu_READ menuVer = new Menu_READ();
            menuVer.exibir(this.dao, 0);
        }else if (o == 4) {
            Menu_READ menuVer = new Menu_READ();
            menuVer.exibir(this.dao, 11, true);
        }else if(o == 2){
            imprimirConviteIndividual();
        }else if (o==3){
            imprimirConviteFamilia();
        } else if (o == 5) {
            mostrarConvidados();
        } else if (o == 6) {  
            mostrarConvidadosConfirmados();
        }

        if (o >= this.nOps) {
            MenuInicial menu = new MenuInicial();
            menu.exibir(this.dao, this.logou, this.userLogado);
        }

    }
    private void imprimirConviteIndividual(){
        String idNomeConvidado = JOptionPane.showInputDialog(null, "Digite o id do nome do convidado:", "Imprimir Convite Individual", JOptionPane.QUESTION_MESSAGE);
        
        
        if (idNomeConvidado != null && !idNomeConvidado.trim().isEmpty()){
            int idConvidado  = Integer.parseInt(idNomeConvidado);
            
            String gerandoConvite = this.dao.getIprimirConviteINdividual(idConvidado, 9);
            
            String mensagem = "Emissão de Convite individual:\n" + gerandoConvite + "\n\nClique em OK para voltar.";
            JOptionPane.showMessageDialog(null, mensagem, "Convite", JOptionPane.INFORMATION_MESSAGE);
        }else{
        
             JOptionPane.showMessageDialog(null, "Id do convidado não inserido.", "Erro", JOptionPane.WARNING_MESSAGE);
    
        }    
    }
    
    private void imprimirConviteFamilia(){
        String idNomeFamilia = JOptionPane.showInputDialog(null, "Digite o id da familia:", "Imprimir Convite Familia", JOptionPane.QUESTION_MESSAGE);
        
        if (idNomeFamilia != null && !idNomeFamilia.trim().isEmpty()){
            int idConvidadoFamilia  = Integer.parseInt(idNomeFamilia);
            
            String gerandoConviteFamilia = this.dao.getIprimirConviteFamilia(idConvidadoFamilia, 10);
            
            String mensagem = "Emissão de Convite Familia:\n" + gerandoConviteFamilia + "\n\nClique em OK para voltar.";
            JOptionPane.showMessageDialog(null, mensagem, "Convite", JOptionPane.INFORMATION_MESSAGE);
        }else{
        
             JOptionPane.showMessageDialog(null, "Id da familia não inserido.", "Erro", JOptionPane.WARNING_MESSAGE);
    
        }    
    
    }
    
    private void mostrarConvidados() {
        // Tenta obter os nomes dos convidados
        String nomeConvidados = this.dao.getNomes(9);

        // Verifica se a lista está vazia ou nula
        if (nomeConvidados == null || nomeConvidados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum convidado encontrado.", "Lista de Convidados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Exibe os nomes dos convidados em um JOptionPane
            String mensagem = "Lista de Convidados:\n" + nomeConvidados + "\n\nClique em OK para voltar.";
            JOptionPane.showMessageDialog(null, mensagem, "Lista de Convidados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void mostrarConvidadosConfirmados() {
        
        String NomeConvidadosConfirmados = this.dao.getNomesConfirmados(9);

        // Verifica se há convidados confirmados 
        // Esse if é reddundante porque o metodo ja retorna senão houver Convidados Confirmados
        if (NomeConvidadosConfirmados == null || NomeConvidadosConfirmados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum convidado confirmou presença.", "Convidados Confirmados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Exibe os nomes dos convidados confirmados
            String mensagem = "Lista de Convidados Confirmados:\n" + NomeConvidadosConfirmados + "\n\nClique em OK para voltar.";
            JOptionPane.showMessageDialog(null, mensagem, "Convidados Confirmados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
