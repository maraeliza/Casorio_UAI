package main;

import CONTROLLER.DAO;
import VIEW.TelaInicial;

public class TrabalhoPOO {

    public static void main(String[] args) {
     
        DAO dao = new DAO();  
        dao.criar();
        TelaInicial menu = new TelaInicial();
        menu.exibir(dao);

    }
    
}


//JoséMaria15122024iyai

