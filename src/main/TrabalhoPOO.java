package main;

import CONTROLLER.DAO;
import VIEW.TelaInicial;

public class TrabalhoPOO {

    public static void main(String[] args) {
        
        /*
        Não deixar deletar pessoa, se estiver vinculado a usuário
        Adicionar opção de a vista ou parcelado


        visualizar o calendário -> mostra todos os pagamentos agendados e as datas
        pesquisar pagamentos  ver por data
        mostrar eventos



        Cria convite para pessoa
        criar convidado para pessoa
        */
        DAO dao = new DAO();
        TelaInicial menu = new TelaInicial();
        menu.exibir(dao);

    }

}
