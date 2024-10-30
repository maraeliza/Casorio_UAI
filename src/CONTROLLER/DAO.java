/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CONTROLLER;

import MODEL.Cartorio;
import MODEL.Cerimonial;
import MODEL.ClasseInterface;
import MODEL.ConvidadoFamilia;
import MODEL.ConvidadoIndividual;
import MODEL.Despesa;
import MODEL.Evento;
import MODEL.Fornecedor;
import MODEL.Igreja;
import MODEL.Pagamento;
import MODEL.Parcela;
import MODEL.Pessoa;
import MODEL.Presente;
import MODEL.Recado;
import MODEL.Relatorio;
import MODEL.Usuario;
import VIEW.MenuInicial;
import VIEW.TelaInicial;
import VIEW.Util;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Mara
 */
public class DAO {

    private Recado[] recados;
    private Presente[] presentes;
    private Pessoa[] pessoas;
    private Usuario[] usuarios;
    private Fornecedor[] fornecedores;
    private Evento[] eventos;
    private Cerimonial[] cerimoniais;
    private Igreja[] igrejas;
    private Cartorio[] cartorios;
    private ConvidadoIndividual[] convidadosIndividuais;
    private ConvidadoFamilia[] convidadosFamilia;
    private Pagamento[] pagamentos;
    private Relatorio[] relatorios;
    private Parcela[] parcelas;
    private Despesa[] despesas;
    private Despesa[] despesasAgendadas;

    private Parcela[] parcelaAgendadas;
    private Object[][] todosOsVetores;
    private Class<?> listaClasses[];
    private String listaNomesClasses[];
    private LocalDate dataHoje;
    private Usuario userLogado;

    public DAO() {
        this.listaNomesClasses = new String[20];
        this.listaNomesClasses[0] = "RECADOS";
        this.listaNomesClasses[1] = "PRESENTES";
        this.listaNomesClasses[2] = "PESSOA";
        this.listaNomesClasses[3] = "USUÁRIOS";
        this.listaNomesClasses[4] = "FORNECEDOR";
        this.listaNomesClasses[5] = "EVENTO";

        this.listaNomesClasses[6] = "CERIMONIAL";
        this.listaNomesClasses[7] = "IGREJA";
        this.listaNomesClasses[8] = "CARTÓRIO";
        this.listaNomesClasses[9] = "CONVIDADO INDIVIDUAL";
        this.listaNomesClasses[10] = "CONVIDADO FAMÍLIA";

        this.listaNomesClasses[11] = "PAGAMENTOS";
        this.listaNomesClasses[12] = "DESPESAS";
        this.listaNomesClasses[13] = "PARCELAS";

        this.listaClasses = new Class<?>[]{
            Recado.class, // RECADOS        0
            Presente.class, // PRESENTES    1
            Pessoa.class, // PESSOA         2
            Usuario.class, // USUÁRIOS      3
            Fornecedor.class, // FORNECEDOR 4
            Evento.class, // EVENTO         5
            Cerimonial.class, // CERIMONIAL 6
            Igreja.class, // IGREJA         7
            Cartorio.class, // CARTÓRIO     8
            ConvidadoIndividual.class, // CONVIDADO INDIVIDUAL 9
            ConvidadoFamilia.class, // CONVIDADO FAMÍLIA 10
            Pagamento.class, // PAGAMENTO 11
            Despesa.class, // DESPESAS 12
            Parcela.class // PARCELAS  13
        };

        recados = new Recado[100];            // Por exemplo, vetor com 10 elementos
        presentes = new Presente[100];
        pessoas = new Pessoa[100];
        usuarios = new Usuario[100];
        fornecedores = new Fornecedor[100];
        eventos = new Evento[100];
        cerimoniais = new Cerimonial[100];
        igrejas = new Igreja[100];
        cartorios = new Cartorio[100];
        convidadosIndividuais = new ConvidadoIndividual[100];
        convidadosFamilia = new ConvidadoFamilia[100];
        pagamentos = new Pagamento[100];
        despesas = new Despesa[100];
        parcelas = new Parcela[100];

        this.todosOsVetores = new Object[][]{
            recados,//0
            presentes,//1
            pessoas,//2
            usuarios,//3
            fornecedores,//4
            eventos,//5
            cerimoniais,//6
            igrejas,//7
            cartorios,//8
            convidadosIndividuais,//9
            convidadosFamilia,//10
            pagamentos,//11
            despesas,//12
            parcelas //13
        };
        this.dataHoje = LocalDate.now();
    }

    public void criar() {
        this.addInfosIniciais();
    }

    public void addInfosIniciais() {
        Object[] comentario = {"Felicidades para o casal!"};
        this.cadastrar(0, comentario);

        Object[] comentario1 = {"Mal posso esperar pela festa!"};
        this.cadastrar(0, comentario1);

        Object[] comentario2 = {"Shippo demais! Meu casal favorito!!"};
        this.cadastrar(0, comentario2);

        Object[] presente1 = {"Fogão", "Eletrodomésticos", "https://www.casasbahia.com.br/fogao/b"};
        this.cadastrar(1, presente1);
        Object[] presente2 = {"Cama", "Móveis", "https://www.casasbahia.com.br/cama/b"};
        this.cadastrar(1, presente2);
        Object[] presente3 = {"Sofá", "Móveis", "https://www.casasbahia.com.br/sofa/b"};
        this.cadastrar(1, presente3);

        Object[] dados0 = {"Pagamento agendado", "00000000", "sys", "01/01/2000"};
        this.cadastrar(2, dados0);

        Object[] dados = {"ADMINISTRADOR", "7777 5555", "ADMIN", "01/01/2001"};
        this.cadastrar(2, dados);

        Object[] pessoa2Dados = {"José", "3432 2556", "NOIVO", "01/01/2001"};
        this.cadastrar(2, pessoa2Dados);

        Object[] pessoa3Dados = {"Maria", "3431 1335", "NOIVA", "01/01/2001"};
        this.cadastrar(2, pessoa3Dados);

        Object[] pessoa4Dados = {"Ana", "3431 1335", "convidado", "01/01/2001"};
        this.cadastrar(2, pessoa4Dados);

        Object[] pessoa5Dados = {"Ricardo", "3431 1335", "cerimonial", "31/01/1989"};
        this.cadastrar(2, pessoa5Dados);

        Object[] pessoa6Dados = {"Fábio", "3431 1335", "cerimonial", "15/05/1989"};
        this.cadastrar(2, pessoa6Dados);

        Object[] pessoa7Dados = {"Marisvalda", "3431 1335", "convidado", "15/05/1989"};
        this.cadastrar(2, pessoa7Dados);

        Object[] userDados1 = {"2", "admin", "1234"};
        this.cadastrar(3, userDados1);

        Object[] userDados3 = {"3", "loginNoivo", "senha"};
        this.cadastrar(3, userDados3);

        Object[] userDados4 = {"4", "loginNoiva", "senha"};
        this.cadastrar(3, userDados4);

        Object[] cerDados = {"6"};
        this.cadastrar(6, cerDados);

        Object[] fornecedorBuffet = {"Buffet Delicioso", "12.345.678/0001-99", "(34) 1234-5678", 15000.0, 5, "em aberto"};
        this.cadastrar(4, fornecedorBuffet);

        Object[] fornecedorDecoracao = {"Flores e Cores Decoração", "98.765.432/0001-11", "(34) 9876-5432", 8000.0, 3, "pago"};
        this.cadastrar(4, fornecedorDecoracao);

        Object[] fornecedorFotografia = {"Momentos Eternos Fotografia", "11.223.344/0001-22", "(34) 1122-3344", 5000.0, 2, "em aberto"};
        this.cadastrar(4, fornecedorFotografia);

        Object[] fornecedorMusica = {"Som & Luz Banda", "22.334.556/0001-33", "(34) 2233-4455", 7000.0, 4, "pago"};
        this.cadastrar(4, fornecedorMusica);

        Object[] fornecedorConvites = {"Convites Perfeitos", "33.445.667/0001-44", "(34) 3344-5566", 2000.0, 1, "em aberto"};
        this.cadastrar(4, fornecedorConvites);

        // Exemplo de igrejas para o casamento
        Object[] igrejaDados1 = {"Igreja Matriz", "Rua das Flores, 123"};
        this.cadastrar(7, igrejaDados1);

        Object[] igrejaDados2 = {"Capela São José", "Avenida Central, 456"};
        this.cadastrar(7, igrejaDados2);

        Object[] igrejaDados3 = {"Igreja Nossa Senhora das Graças", "Praça das Palmeiras, 789"};
        this.cadastrar(7, igrejaDados3);

        // Exemplo de cartórios para o casamento
        Object[] cartorioDados1 = {"Cartório Central", "(34) 1234-5678", "Avenida Brasil, 100"};
        this.cadastrar(8, cartorioDados1);

        Object[] cartorioDados2 = {"Cartório do Povo", "(34) 8765-4321", "Rua da Independência, 200"};
        this.cadastrar(8, cartorioDados2);

        Object[] cartorioDados3 = {"Cartório e Registro São José", "(34) 5678-1234", "Praça da República, 300"};
        this.cadastrar(8, cartorioDados3);

        Object[] eventoIgreja = {"15/12/2024", "1", "0", "1", "❤ Casorio na Igreja ⛪❤"};
        this.cadastrar(5, eventoIgreja);

        Object[] eventoCartorio = {"10/12/2024", "0", "1", "1", "❤ Casorio no Civil ❤"};
        this.cadastrar(5, eventoCartorio);

        String date = Util.dateToString(this.dataHoje);
        Object[] evento = {date, "0", "0", "0", "Apresentação do Casório UAI❤"};
        this.cadastrar(5, evento);

        Object[] famDados = {"Lopes"};
        this.cadastrar(10, famDados);

        Object[] famDados1 = {"Silva"};
        this.cadastrar(10, famDados1);

        Object[] famDados2 = {"Sales"};
        this.cadastrar(10, famDados2);

        Object[] famDados3 = {"Genesio"};
        this.cadastrar(10, famDados3);

        Object[] famDados4 = {"Sampaio"};
        this.cadastrar(10, famDados4);

        Object[] conDados = {"5", "1", "Filha"};
        this.cadastrar(9, conDados);

        Object[] despesaDados = {"1", "Comidas", "Bolo, janta, etc.", "1800.0", "2", "31/11/2024", ""};
        this.cadastrar(12, despesaDados);
        LocalDate data = Util.stringToDate("15/12/2024");
        this.getDespesas()[0].agendar(data, true);

        Object[] despesaDados1 = {"1", "Bebidas", "Sucos, refris, etc.", "100.0", "1", "31/11/2024", ""};
        this.cadastrar(12, despesaDados1);
        this.getDespesas()[1].agendar(this.dataHoje, true);

        Object[] despesaDados2 = {"3", "Album", "Fotos, fotográfo, etc.", "2500.0", "2", "15/12/2024", ""};
        this.cadastrar(12, despesaDados2);
        this.getDespesas()[2].agendar(this.dataHoje, true);

        Object[] despesaDados3 = {"2", "Decoração", "Flores, adornos, etc.", "300.0", "1", "10/11/2024", ""};
        this.cadastrar(12, despesaDados3);

        this.pagarAgendados();
    }

    public void getAgendados() {
        int c = 0;
        /*------------------------    DESPESAS AGENDADAS ---------------------------------- */
        Despesa vDespesa[] = (Despesa[]) this.todosOsVetores[12];
        Despesa vDespesaAgendadas[] = new Despesa[100];

        for (int i = 0; i < vDespesa.length; i++) {
            if (vDespesa[i] != null) {
                if (vDespesa[i].isAgendado() && !vDespesa[i].isPago()) {
                    for (int n = 0; n < vDespesaAgendadas.length; n++) {
                        if (vDespesaAgendadas[n] == null) {
                            vDespesaAgendadas[n] = vDespesa[i];
                            break;
                        }
                    }
                    c++;
                }

            }
        }
        this.setDespesasAgendadas(vDespesaAgendadas);

        /*------------------------    PARCELAS AGENDADAS ---------------------------------- */
        Parcela vParcela[] = (Parcela[]) this.todosOsVetores[13];
        Parcela vParcelaAgendadas[] = new Parcela[100];
        /* percorre todas as parcelas cadastradas */
        for (int i = 0; i < vParcela.length; i++) {
            if (vParcela[i] != null) {
                /* checa se está agendada */
                if (vParcela[i].isAgendado() && !vParcela[i].isPago()) {
                    for (int n = 0; n < vParcelaAgendadas.length; n++) {
                        if (vParcelaAgendadas[n] == null) {
                            vParcelaAgendadas[n] = vParcela[i];
                            break;
                        }
                    }
                    c++;
                }

            }
        }
        /* salva no vetor */
        this.setParcelaAgendadas(vParcelaAgendadas);
    }

    public void pagarAgendados() {
        this.getAgendados();
        for (int n = 0; n < this.getDespesasAgendadas().length; n++) {
            Despesa despesa = this.getDespesasAgendadas()[n];
            if (despesa != null && despesa.isAgendado()
                    && (despesa.getDataAgendamento().isBefore(this.dataHoje)
                    || despesa.getDataAgendamento().isEqual(this.dataHoje))) {
                despesa.pagar(true);
            }
        }
        // Percorre o vetor de parcelas agendadas e verifica se alguma parcela tem uma data anterior ou igual ao dia de hoje
        for (int n = 0; n < this.getParcelaAgendadas().length; n++) {
            Parcela parcela = this.getParcelaAgendadas()[n];
            if (parcela != null && parcela.isAgendado()
                    && (parcela.getDataAgendamento().isBefore(this.dataHoje)
                    || parcela.getDataAgendamento().isEqual(this.dataHoje))) {
                parcela.pagar(false);
            }
        }

    }

    public int getTotalClasse(int idClasse) {
        int n = 0;

        for (int i = 0; i < this.getVetorById(idClasse).length; i++) {
            if (this.getVetorById(idClasse)[i] != null) {
                n++;
            }
        }

        return n;
    }

    public String getTexto(int idClasse, ClasseInterface vetor[]) {

        String texto = this.listaNomesClasses[idClasse] + " ENCONTRADOS!";
        int c = 0;

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] != null) {
                if (vetor[i] instanceof ClasseInterface) {
                    texto += ((ClasseInterface) vetor[i]).ler();
                    c++;
                }

            }
        }
        if (c > 1) {
            texto += "\n\nTotal: " + c + " itens\n";
        } else if (c == 1) {
            texto += "\n\nTotal: " + c + " item\n";
        } else {
            texto = "\nNenhum item encontrado!\n";
        }

        return texto;
    }

    public String getTextoParcelas() {

        String texto = "PARCELAS CADASTRADAS";
        int c = 0;
        Parcela[] vP = (Parcela[]) this.getVetorById(13);
        for (int i = 0; i < vP.length; i++) {
            if (vP[i] != null) {
                texto += vP[i].lerParcelaAgendada();
                c++;

            }
        }
        if (c > 1) {
            texto += "\n\nTotal: " + c + " itens\n";
        } else if (c == 1) {
            texto += "\n\nTotal: " + c + " item\n";
        } else {
            texto = "\nNenhum item encontrado!\n";
        }

        return texto;
    }

    public String getTexto(int idClasse) {

        String texto = this.listaNomesClasses[idClasse] + " JÁ CADASTRADOS";
        if (this.getTotalClasse(idClasse) > 1) {
            texto += "\nTotal: " + this.getTotalClasse(idClasse) + " itens\n";
        } else if (this.getTotalClasse(idClasse) == 1) {
            texto += "\nTotal: " + this.getTotalClasse(idClasse) + " item\n";
        }

        if (this.getTotalClasse(idClasse) > 0 && this.getTotalClasse(idClasse) <= 7) {
            Object[] vetor = this.getVetorById(idClasse);
            for (int i = 0; i < vetor.length; i++) {
                if (vetor[i] != null) {
                    if (vetor[i] instanceof ClasseInterface) {
                        texto += ((ClasseInterface) vetor[i]).ler();
                    }

                }
            }
        } else if (this.getTotalClasse(idClasse) > 7) {
            texto += this.getNomes(idClasse);
        } else {
            texto += "\n\nNENHUM ITEM ENCONTRADO!\n";
        }

        return texto;
    }

    public String getPagamentosNoivos(int idClasse) {
        double valorPago = 0.0;
        double valorNoiva = 0.0;
        double valorNoivo = 0.0;
        int totalPgs = 0;
        String texto = "💲 PAGAMENTOS FEITOS PELOS NOIVOS 💲 ";

        for (int i = 0; i < this.getPagamentos().length; i++) {

            Pagamento pg = this.getPagamentos()[i];
            if (pg != null && pg.getPessoa() != null) {
                if (pg.getPessoa().getTipo().toUpperCase().equals("NOIVO")
                        || pg.getPessoa().getTipo().toUpperCase().equals("NOIVA")) {
                    texto += "\nValor pago: " + pg.getValor() + " data do pagamento: " + pg.getData();
                    texto += "\n Pagante: " + pg.getPessoa().getNome();
                    valorPago += pg.getValor();
                    totalPgs++;
                }
                if (pg.getPessoa().getTipo().toUpperCase().equals("NOIVA")) {

                    valorNoiva += pg.getValor();
                }
                if (pg.getPessoa().getTipo().toUpperCase().equals("NOIVO")) {

                    valorNoivo += pg.getValor();
                }
            }

        }
        if (totalPgs > 1) {
            texto += "\n\nTotal: " + totalPgs + " pagamentos";
        } else {
            texto += "\n\nTotal: " + totalPgs + " pagamento";
        }
        texto += "\nVALOR TOTAL GASTO PELOS NOIVOS R$" + String.format("%.2f", valorPago);
        texto += "\nGASTOS DA NOIVA:  R$" + String.format("%.2f", valorNoiva);
        texto += "\nGASTOS DO NOIVO:  R$" + String.format("%.2f", valorNoivo);
        return texto;
    }

    public Class<?> getClasseByID(int idClasse) {
        ;
        return this.listaClasses[idClasse];
    }

    public boolean cadastrar(int idClasse, Object infos[]) {

        boolean criado = false;

        try {
            // Obtém a classe correspondente ao idClasse
            Class<?> classe = this.listaClasses[idClasse];

            // Cria uma nova instância da classe
            ClasseInterface objeto = (ClasseInterface) classe.getDeclaredConstructor().newInstance();
            // Chama o método criar com as informações fornecidas

            criado = objeto.criar(this, infos);

            if (criado) {

                // Adiciona o objeto ao vetor correspondente
                boolean adicionado = this.addVetor(idClasse, objeto);

                if (idClasse == 12) {
                    if (((Despesa) objeto).isParcelado()) {
                        ((Despesa) objeto).criarParcelas();
                    }

                }

                return adicionado;

            } else {

                return criado;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public Parcela cadastrarParcela(int idClasse, Object infos[]) {

        boolean criado = false;
        try {
            // Cria uma nova instância
            Parcela objeto = new Parcela();
            // Chama o método criar com as informações fornecidas
            criado = objeto.criar(this, infos);
            if (criado) {
                this.addVetor(13, objeto);
                return objeto;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public void atualizar(int idClasse, Object infos[]) {
        int id = Util.stringToInt((String) infos[0]);
        if (id != 0) {
            if (this.find(idClasse, id)) {
                ClasseInterface objeto = this.getItemByID(idClasse, id);

                objeto.update(infos);

            } else {
                Util.mostrarErro("NÃO ENCONTRADO");
            }
        }

    }

    public boolean addVetor(int idClasse, Object ob) {
        Object[] vetor = this.getVetorById(idClasse);
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == null) {
                vetor[i] = ob;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int idClasse, Object ob) {
        Object[] vetor = this.getVetorById(idClasse);
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == ob) {
                vetor[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean find(int idClasse, int id) {
        Object[] vetor = this.getVetorById(idClasse);
        for (int i = 0; i < vetor.length; i++) {
            // Verifica se o item não é nulo e se implementa a interface
            if (vetor[i] != null && vetor[i] instanceof ClasseInterface) {
                ClasseInterface item = (ClasseInterface) vetor[i]; // Faz o cast
                if (item.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public ClasseInterface getItemByID(int idClasse, int id) {
        Object[] vetor = this.getVetorById(idClasse);
        for (int i = 0; i < vetor.length; i++) {
            // Verifica se o item não é nulo e se implementa a interface
            if (vetor[i] != null && vetor[i] instanceof ClasseInterface) {
                ClasseInterface item = (ClasseInterface) vetor[i]; // Faz o cast
                if (item.getId() == id) {
                    return item; // Retorna como ClasseInterface    
                }
            }
        }
        return null;
    }

    public boolean delItemByID(int idClasse, int id) {

        Object[] vetor = this.getVetorById(idClasse);
        for (int i = 0; i < vetor.length; i++) {
            // Verifica se o item não é nulo e se implementa a interface
            if (vetor[i] != null && vetor[i] instanceof ClasseInterface) {
                ClasseInterface item = (ClasseInterface) vetor[i];

                if (item.getId() == id) {

                    boolean podeApagar = item.deletar();
                    if (podeApagar) {
                        vetor[i] = null; // Remove o item
                    }

                    return podeApagar;
                }
            }
        }
        return false;
    }

    public Pessoa getNoivos(int noiva) {
        Pessoa p = null;
        int n = 0;
        Pessoa vPessoas[] = (Pessoa[]) this.todosOsVetores[2];
        for (int i = 0; i < vPessoas.length; i++) {
            if (vPessoas[i] != null) {
                if ((noiva == 1 && vPessoas[i].getTipo().equals("NOIVA"))
                        || (noiva == 0 && vPessoas[i].getTipo().equals("NOIVO"))) {
                    p = vPessoas[i];
                    n++;
                }
            }
        }
        if (n == 0) {
            return null;
        }
        return p;
    }

    public String getCerimoniaisIdNomeDisponiveis() {
        String texto = "";
        int n = 0;
        Pessoa vPessoas[] = (Pessoa[]) this.todosOsVetores[2];
        for (int i = 0; i < vPessoas.length; i++) {
            if (vPessoas[i] != null) {
                if (vPessoas[i].getTipo().equals("CERIMONIAL")
                        && !vPessoas[i].isCerimonialVinculado()
                        && !vPessoas[i].isUserVinculado()) {
                    texto += "\nID: " + vPessoas[i].getId() + "\nNome: " + vPessoas[i].getNome();
                    texto += "     tipo: " + vPessoas[i].getTipo();
                    texto += "\n";
                    n++;
                }
            }
        }
        if (n == 0) {
            texto = "\nNenhum cerimonial encontrado!";
        }
        return texto;
    }

    public String getNoivo(int noiva) {
        String texto = "";
        int n = 0;
        Pessoa vPessoas[] = (Pessoa[]) this.todosOsVetores[2];
        for (int i = 0; i < vPessoas.length; i++) {
            if (vPessoas[i] != null) {
                if ((noiva == 1 && vPessoas[i].getTipo().equals("NOIVA"))
                        || (noiva == 0 && vPessoas[i].getTipo().equals("NOIVO"))) {
                    texto += "\nID: " + vPessoas[i].getId() + "\nNome: " + vPessoas[i].getNome();
                    texto += "\n";
                    n++;
                }
            }
        }
        if (n == 0) {
            texto = "\nNenhum(a) noivo(a) encontrado!";
        }
        return texto;
    }

    public String getTextoNoivos() {
        String texto = "\n                    ";
        int n = 0;
        Pessoa vPessoas[] = (Pessoa[]) this.todosOsVetores[2];
        for (int i = 0; i < vPessoas.length; i++) {
            if (vPessoas[i] != null && (vPessoas[i].getTipo().equals("NOIVO")
                    || vPessoas[i].getTipo().equals("NOIVA"))) {
                texto += vPessoas[i].getNome();
                if (n == 0) {
                    texto += " ❤ ";
                }
                n++;
            }
        }
        return texto;
    }

    public String getDespesasParceladasPendentes() {
        String texto = "\n                    ";

        Despesa[] vObj = (Despesa[]) this.todosOsVetores[12];
        int c = 0;
        for (int i = 0; i < vObj.length; i++) {
            if (vObj[i] != null && !vObj[i].isPago() && vObj[i].isParcelado()) {
                texto += "\nID: " + vObj[i].getId() + "\nNome: " + vObj[i].getNome();
                texto += "\n";
                c++;
            }
        }

        if (c == 0) {
            texto = "\n\nNenhuma despesa encontrada!\n\n";
        }
        return texto;
    }
    public String getDespesasPendentes() {
        String texto = "\n                    ";

        Despesa[] vObj = (Despesa[]) this.todosOsVetores[12];
        int c = 0;
        for (int i = 0; i < vObj.length; i++) {
            if (vObj[i] != null && !vObj[i].isPago()) {
                texto += "\nID: " + vObj[i].getId() + "\nNome: " + vObj[i].getNome();
                texto += "\n";
                c++;
            }
        }

        if (c == 0) {
            texto = "\n\nNenhuma despesa encontrada!\n\n";
        }
        return texto;
    }

    public String getDespesasPendentesAgendada() {
        String texto = "\n DESPESAS COM PAGAMENTO AGENDADO \n";

        Despesa[] vObj = (Despesa[]) this.todosOsVetores[12];
        int c = 0;
        for (int i = 0; i < vObj.length; i++) {
            if (vObj[i] != null && !vObj[i].isPago() && vObj[i].isAgendado()) {
                texto += "\nID: " + vObj[i].getId() + "           NOME: " + vObj[i].getNome();
                texto += "\nVALOR: " + vObj[i].getValorTotal();
                texto += "\nDATA DO PAGAMENTO AGENDADO: " + vObj[i].getDataAgendamento() + "\n";
                c++;

            } else {
                if (vObj[i] != null && !vObj[i].isPago() && !vObj[i].isAgendado() && vObj[i].isParcelado()) {
                    for (int p = 0; p < vObj[i].getnParcelas(); p++) {
                        Parcela parcela = vObj[i].getvParcelas()[p];
                        if (parcela != null && !parcela.isPago() && parcela.isAgendado()) {
                            texto += parcela.lerParcelaAgendada();
                        }

                    }

                }

            }

        }

        if (c == 0) {
            texto = "\n\nNenhuma despesa encontrada!\n\n";
        }
        return texto;
    }

    public String getParcelasPendentes(int idDespesa) {
        String texto = "\n";

        Despesa despesa = (Despesa) this.getItemByID(12, idDespesa);

        Parcela[] vDespesa = despesa.getvParcelas();
        int c = 0;
        if (vDespesa != null) {
          

            for (int i = 0; i < vDespesa.length; i++) {
                if (vDespesa[i] != null && !vDespesa[i].isPago()) {
                    texto += vDespesa[i].ler();
                    c++;
                }
            }
    
          
        }
        if (c == 0) {
            texto = "\n\nNenhuma parcela pendente de pagamento encontrada!\n\n";
        }
      
        return texto;
    }

    public String getNomes(int idClasse) {
        String texto = "\n                    ";

        ClasseInterface[] vObj = (ClasseInterface[]) this.todosOsVetores[idClasse];
        int c = 0;
        for (int i = 0; i < vObj.length; i++) {
            if (vObj[i] != null) {
                texto += "\nID: " + vObj[i].getId() + "      NOME: " + vObj[i].getNome().toUpperCase();
                texto += "\n";
                c++;
            }
        }

        if (c == 0) {
            texto = "\n\nNenhum cadastrado encontrado!\n\n";
        }
        return texto;
    }

    public void mostrarPagamentosAgendados() {
        // Tenta obter os nomes dos convidados
        String texto = this.getDespesasPendentesAgendada();

        // Verifica se a lista está vazia ou nula
        if (texto == null || texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum pagamento agendado encontrado.", "Lista de Pagamentos Agendados", JOptionPane.INFORMATION_MESSAGE);
        } else {

            JOptionPane.showMessageDialog(null, texto, "Lista de Convidados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getNomesPessoasParaCriarUsers() {
        String texto = "\n                    ";
        Pessoa vPessoas[] = (Pessoa[]) this.todosOsVetores[2];
        Usuario vUsers[] = (Usuario[]) this.todosOsVetores[3];
        boolean userVinculado = false;
        int c = 0;
        for (int i = 0; i < vPessoas.length; i++) {
            if (vPessoas[i] != null) {
                if (!vPessoas[i].isUserVinculado()
                        && !vPessoas[i].getTipo().toUpperCase().equals("CONVIDADO")
                        && !vPessoas[i].getTipo().toUpperCase().equals("CERIMONIAL")) {
                    texto += "\nID: " + vPessoas[i].getId() + "\nNome: " + vPessoas[i].getNome() + "\nTipo: " + vPessoas[i].getTipo();
                    c++;
                    texto += "\n";
                }
            }
        }

        if (c == 0) {
            texto = "\n\nNENHUMA PESSOA CADASTRADA SEM USUÁRIO VINCULADO!\n\n";
        }
        return texto;
    }

    public String getNomesPessoasSemUsers() {
        String texto = "\n                    ";
        Pessoa vPessoas[] = (Pessoa[]) this.todosOsVetores[2];
        Usuario vUsers[] = (Usuario[]) this.todosOsVetores[3];
        boolean userVinculado = false;
        int c = 0;
        for (int i = 0; i < vPessoas.length; i++) {
            if (vPessoas[i] != null) {
                if (!vPessoas[i].isUserVinculado() && !vPessoas[i].isCerimonialVinculado() && !vPessoas[i].isConvidadoVinculado()) {
                    texto += "\nID: " + vPessoas[i].getId() + "\nNome: " + vPessoas[i].getNome() + "\nTipo: " + vPessoas[i].getTipo();
                    c++;
                    texto += "\n";
                }
            }
        }

        if (c == 0) {
            texto = "\n\nNENHUMA PESSOA CADASTRADA SEM USUÁRIO VINCULADO!\n\n";
        }
        return texto;
    }

    public String getNomesPessoasSemConvidado() {
        String texto = "\n                    ";
        Pessoa vPessoas[] = (Pessoa[]) this.todosOsVetores[2];
        Usuario vUsers[] = (Usuario[]) this.todosOsVetores[3];
        boolean userVinculado = false;
        int c = 0;
        for (int i = 0; i < vPessoas.length; i++) {
            if (vPessoas[i] != null) {
                if (!vPessoas[i].isUserVinculado()
                        && !vPessoas[i].isConvidadoVinculado()
                        && vPessoas[i].getTipo().toUpperCase().equals("CONVIDADO")) {
                    texto += "\nID: " + vPessoas[i].getId() + "\nNome: " + vPessoas[i].getNome() + "\nTipo: " + vPessoas[i].getTipo();
                    c++;
                    texto += "\n";
                }
            }
        }

        if (c == 0) {
            texto = "\n\nNENHUMA PESSOA CADASTRADA SEM USUÁRIO VINCULADO!\n\n";
        }
        return texto;
    }

    public Usuario getUserByIdPessoa(int id) {
        Usuario vUsers[] = (Usuario[]) this.todosOsVetores[3];
        for (int u = 0; u < vUsers.length; u++) {
            if (vUsers[u].getIdPessoa() == id) {
                return vUsers[u];
            }

        }
        return null;
    }

    public String getPresentesEscolhidos(Usuario user) {
        String texto = "\n                    ";
        Presente vPresente[] = (Presente[]) this.todosOsVetores[1];

        int c = 0;
        for (int i = 0; i < vPresente.length; i++) {
            if (vPresente[i] != null) {
                if (vPresente[i].getIdPessoa() == user.getIdPessoa()) {
                    texto += "\nID: " + vPresente[i].getId() + "\nNome: " + vPresente[i].getNome() + "\nLink: " + vPresente[i].getLink();
                    if (vPresente[i].isComprado()) {
                        texto += "\nComprado: SIM";

                    } else {
                        texto += "\nComprado: NÃO";
                    }
                    c++;
                    texto += "\n";
                }
            }
        }

        if (c == 0) {
            texto = "\n\nNenhum presente escolhido por você!\n\n";
        }
        return texto;
    }

    public void logar(String user, String senha) {
        Usuario usuario = this.getUserByLogin(user);
        if (usuario != null) {
            if (usuario.getSenha().equals(senha)) {
                this.setUserLogado(usuario);
                if (usuario.getPessoa().getTipo().toUpperCase().equals("CONVIDADO")) {
                    String texto = "\nCONFIRMAR PRESENÇA\n\nVOCÊ GOSTARIA DE CONFIRMAR SUA PRESENÇA NO CASAMENTO?\nDIGITE SIM OU NÃO PARA CONFIRMAR";
                    String resposta = JOptionPane.showInputDialog(null, texto, "UaiCasórioPro", JOptionPane.QUESTION_MESSAGE);

                    ConvidadoIndividual conv = this.findConvidado(this.userLogado.getId());
                    if (conv != null) {
                        if (resposta.toUpperCase().equals("SIM")) {
                            conv.setConfirmacao(true);
                            Util.mostrarMSG("PRESENÇA CONFIRMADA!");
                        } else {
                            conv.setConfirmacao(false);
                            Util.mostrarMSG("Obrigado pela resposta ❤! \nAté mais!");
                        }

                        TelaInicial menu = new TelaInicial();
                        menu.exibir(this);
                    }
                } else {

                    MenuInicial menu = new MenuInicial();
                    menu.exibir(this, true, this.getUserLogado());
                }

            } else {

                Util.mostrarErro("Credenciais incorretas!");
                this.deslogar();
            }
        } else {

            Util.mostrarErro("Credenciais incorretas!");
            this.deslogar();
        }
    }

    public ConvidadoIndividual findConvidado(int idUser) {
        ConvidadoIndividual[] vObj = (ConvidadoIndividual[]) this.todosOsVetores[9];

        for (int i = 0; i < vObj.length; i++) {
            if (vObj[i] != null) {
                if (vObj[i].getIdUser() == idUser) {
                    return vObj[i];
                }

            }
        }
        return null;
    }

    public void deslogar() {
        this.userLogado = null;
        TelaInicial menu = new TelaInicial();
        menu.exibir(this);
    }

    public Usuario getUserByLogin(String user) {
        Usuario vUsers[] = (Usuario[]) this.todosOsVetores[3];
        for (int i = 0; i < vUsers.length; i++) {
            if (vUsers[i] != null && vUsers[i].getLogin().equals(user)) {
                return vUsers[i];
            }
        }
        return null;
    }

    public Evento[] getEventosByData(LocalDate data) {
        Evento vEventoConsulta[] = new Evento[100];
        Evento vEvento[] = this.getEventos();
        for (int i = 0; i < vEvento.length; i++) {
            if (vEvento[i] != null && vEvento[i].getData().equals(data)) {
                for (int n = 0; n < vEventoConsulta.length; n++) {
                    if (vEventoConsulta[n] == null) {
                        vEventoConsulta[n] = vEvento[i];
                        break;
                    }
                }
            }
        }
        return vEventoConsulta;
    }

    public Pagamento[] getPagamentosByData(LocalDate dataPagamento) {
        Pagamento[] vPagamentoConsulta = new Pagamento[100];
        Pagamento[] vPagamento = this.getPagamentos();

        for (int i = 0; i < vPagamento.length; i++) {
            if (vPagamento[i] != null && vPagamento[i].getData().equals(dataPagamento)) {
                for (int n = 0; n < vPagamentoConsulta.length; n++) {
                    if (vPagamentoConsulta[n] == null) {
                        vPagamentoConsulta[n] = vPagamento[i];
                        break;
                    }
                }
            }
        }
        return vPagamentoConsulta;
    }

   
    public Parcela[] getParcelasByDataVencimento(LocalDate dataVencimento) {
        Parcela[] vParcelaConsulta = new Parcela[100];
        Parcela[] vParcela = this.getParcelas();

        for (int i = 0; i < vParcela.length; i++) {
            if (vParcela[i] != null && vParcela[i].getDataVencimento().equals(dataVencimento)
                    && !vParcela[i].isPago()) {
                for (int n = 0; n < vParcelaConsulta.length; n++) {
                    if (vParcelaConsulta[n] == null) {
                        vParcelaConsulta[n] = vParcela[i];
                        break;
                    }
                }
            }
        }
        return vParcelaConsulta;
    }

    public Despesa[] getDespesasByDataAgendamento(int idClasse, LocalDate dataAgendamento) {
        Despesa[] vDespesaConsulta = new Despesa[100];
        Despesa[] vDespesa = this.getDespesas(); // Supõe que exista um método getDespesas() que retorna todas as despesas

        for (int i = 0; i < vDespesa.length; i++) {
            if (vDespesa[i] != null && vDespesa[i].getDataAgendamento().equals(dataAgendamento)) {
                for (int n = 0; n < vDespesaConsulta.length; n++) {
                    if (vDespesaConsulta[n] == null) {
                        vDespesaConsulta[n] = vDespesa[i];
                        break;
                    }
                }
            }
        }
        return vDespesaConsulta;
    }

    public String getIprimirConviteINdividual(int idConvidado, int idEvento) {
        Evento evento = (Evento) this.getItemByID(5, idEvento);
        ConvidadoIndividual conv = (ConvidadoIndividual) this.getItemByID(9, idConvidado);
        String texto = "\n                    ";
        if (conv != null) {
            if (evento != null) {
                texto += "\n Convite Para o Casamento de " + this.getNoivos(0).getNome() + " e " + this.getNoivos(1).getNome() + "\n";

                texto += "\nCom muito prazer, gostaríamos de convidar você " + conv.getNome() + " para o nosso casamento!\n";
                texto += "Evento: " + evento.getNome() + " \n";
                texto += "Data: " + evento.getData() + " \n";
                texto += "Local: " + evento.getEndereco() + " \n";
                texto += "\nPor favor, confirme sua presença\n";
                texto += "\nPara isso, basta logar com as credenciais a seguir: \n";
                texto += "Login: " + conv.getUser().getLogin() + " \n";
                texto += "Senha: " + conv.getUser().getSenha() + " \n";
            } else {
                texto = "\n\nNenhum evento com id " + idEvento + " foi encontrado!\n\n";
            }
        } else {
            texto = "\n\nNenhum convidado com id " + idConvidado + " foi encontrado!\n\n";
        }

        return texto;

    }

    public String gerarConviteFamilia(int idEvento, int idConvidadoFamilia) {
        Evento evento = (Evento) this.getItemByID(5, idEvento);
        ConvidadoFamilia convFamilia = (ConvidadoFamilia) this.getItemByID(10, idConvidadoFamilia); // Classe ConvidadoFamilia
        String texto = "\n                    ";

        if (convFamilia != null) {
            if (evento != null) {
                texto += "\n Convite Para o Casamento de " + this.getNoivos(0).getNome() + " e " + this.getNoivos(1).getNome() + "\n";
                texto += "\nCom muito prazer, gostaríamos de convidar a família " + convFamilia.getNome() + " para o nosso casamento!\n";
                texto += "Evento: " + evento.getNome() + " \n";
                texto += "Data: " + evento.getData() + " \n";
                texto += "Local: " + evento.getEndereco() + " \n";
                texto += "\nPor favor, confirme a presença de sua família\n";
                texto += "\nPara isso, peça para o titular da sua família logar com o acesso a seguir: \n";
                texto += "Acesso: " + convFamilia.getAcesso() + " \n"; // Acesso específico da família

            } else {
                texto = "\nNenhum evento com id " + idEvento + " foi encontrado!\n\n";
            }
        } else {
            texto = "\nNenhuma família com id " + idConvidadoFamilia + " foi encontrada!\n\n";
        }

        return texto;
    }

    public String getNomesConfirmados(int idClasse) {
        String texto = "\n                    ";

        ConvidadoIndividual[] vObj = (ConvidadoIndividual[]) this.todosOsVetores[idClasse];
        int c = 0;

        for (int i = 0; i < vObj.length; i++) {
            if (vObj[i] != null && vObj[i].isConfirmacao() == true) {
                texto += "\nID: " + vObj[i].getId() + "\nNome: " + vObj[i].getNome();
                texto += "\n";
                c++;
            }
        }

        if (c == 0) {
            texto = "\n\nNenhum Convidado Confirmado!\n\n";
        }

        return texto;

    }

    public Recado[] getRecados() {
        return recados;
    }

    public void setRecados(Recado[] recados) {
        this.recados = recados;
    }

    public Presente[] getPresentes() {
        return presentes;
    }

    public void setPresentes(Presente[] presentes) {
        this.presentes = presentes;
    }

    public Pessoa[] getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoa[] pessoas) {
        this.pessoas = pessoas;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public Fornecedor[] getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Fornecedor[] fornecedores) {
        this.fornecedores = fornecedores;
    }

    public Evento[] getEventos() {
        return eventos;
    }

    public void setEventos(Evento[] eventos) {
        this.eventos = eventos;
    }

    public Cerimonial[] getCerimoniais() {
        return cerimoniais;
    }

    public void setCerimoniais(Cerimonial[] cerimoniais) {
        this.cerimoniais = cerimoniais;
    }

    public Igreja[] getIgrejas() {
        return igrejas;
    }

    public void setIgrejas(Igreja[] igrejas) {
        this.igrejas = igrejas;
    }

    public Cartorio[] getCartorios() {
        return cartorios;
    }

    public void setCartorios(Cartorio[] cartorios) {
        this.cartorios = cartorios;
    }

    public ConvidadoIndividual[] getConvidadosIndividuais() {
        return convidadosIndividuais;
    }

    public void setConvidadosIndividuais(ConvidadoIndividual[] convidadosIndividuais) {
        this.convidadosIndividuais = convidadosIndividuais;
    }

    public ConvidadoFamilia[] getConvidadosFamilia() {
        return convidadosFamilia;
    }

    public void setConvidadosFamilia(ConvidadoFamilia[] convidadosFamilia) {
        this.convidadosFamilia = convidadosFamilia;
    }

    public Pagamento[] getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Pagamento[] pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Relatorio[] getRelatorios() {
        return relatorios;
    }

    public void setRelatorios(Relatorio[] relatorios) {
        this.relatorios = relatorios;
    }

    public Object[][] getTodosOsVetores() {
        return this.todosOsVetores;
    }

    public void setTodosOsVetores(Object[][] todosOsVetores) {
        this.todosOsVetores = todosOsVetores;
    }

    public Class<?>[] getListaClasses() {
        return listaClasses;
    }

    public void setListaClasses(Class<?>[] listaClasses) {
        this.listaClasses = listaClasses;
    }

    public Object[] getVetorById(int id) {
        return this.todosOsVetores[id];
    }

    public String getNameClasseById(int idClasse) {
        return this.listaNomesClasses[idClasse];
    }

    public Despesa[] getDespesasAgendadas() {
        return this.despesasAgendadas;
    }

    public void setDespesasAgendadas(Despesa[] despesasAgendadas) {
        this.despesasAgendadas = despesasAgendadas;
    }

    public Parcela[] getParcelas() {
        return parcelas;
    }

    public void setParcelas(Parcela[] parcelas) {
        this.parcelas = parcelas;
    }

    public Despesa[] getDespesas() {
        return despesas;
    }

    public void setDespesas(Despesa[] despesas) {
        this.despesas = despesas;
    }

    public Parcela[] getParcelaAgendadas() {
        return parcelaAgendadas;
    }

    public void setParcelaAgendadas(Parcela[] parcelaAgendadas) {
        this.parcelaAgendadas = parcelaAgendadas;
    }

    public String[] getListaNomesClasses() {
        return listaNomesClasses;
    }

    public void setListaNomesClasses(String[] listaNomesClasses) {
        this.listaNomesClasses = listaNomesClasses;
    }

    public LocalDate getDataHoje() {
        return dataHoje;
    }

    public void setDataHoje(LocalDate dataHoje) {
        this.dataHoje = dataHoje;
    }

    public Usuario getUserLogado() {
        return userLogado;
    }

    public void setUserLogado(Usuario userLogado) {
        this.userLogado = userLogado;
    }

}
