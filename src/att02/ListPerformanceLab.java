package att02;

import java.util.*;

public class ListPerformanceLab {

    // --- Códigos de Cor ANSI para o Console ---
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Usando LinkedHashMap para manter a ordem de inserção no menu
        Map<Integer, String> opcoes = new LinkedHashMap<>();
        opcoes.put(1, "ArrayList");
        opcoes.put(2, "LinkedList");
        opcoes.put(3, "Vector");
        opcoes.put(4, "Stack");
        opcoes.put(5, "Executar Todos e Comparar"); // Nova opção

        while (true) {
            System.out.println(ANSI_CYAN + BOLD + "*****************************************************");
            System.out.println("*                                                   *");
            System.out.println("*    LABORATÓRIO DE DESEMPENHO DE LISTAS JAVA       *");
            System.out.println("*                                                   *");
            System.out.println("*****************************************************" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Escolha uma das opções abaixo:" + ANSI_RESET);

            for(Map.Entry<Integer, String> entry : opcoes.entrySet()) {
                System.out.println(ANSI_WHITE + entry.getKey() + ". " + entry.getValue() + ANSI_RESET);
            }

            System.out.println(ANSI_RED + "0. Sair" + ANSI_RESET);
            System.out.print(ANSI_GREEN + "Digite sua opção -> " + ANSI_RESET);

            int escolha;
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "\nErro: Por favor, digite um número válido.\n" + ANSI_RESET);
                continue;
            }

            if (escolha == 0) {
                System.out.println(ANSI_CYAN + "\nEncerrando o laboratório. Até a próxima! 👋" + ANSI_RESET);
                break;
            }

            if (escolha == 5) {
                executarTodosOsTestesEImprimirTabela();
                continue; // Volta para o início do loop do menu
            }

            List<Integer> listaEscolhida = null;
            String nomeDaLista = opcoes.get(escolha);

            if (nomeDaLista == null) {
                System.out.println(ANSI_RED + "\nOpção inválida! Tente novamente.\n" + ANSI_RESET);
                continue;
            }

            switch (escolha) {
                case 1: listaEscolhida = new ArrayList<>(); break;
                case 2: listaEscolhida = new LinkedList<>(); break;
                case 3: listaEscolhida = new Vector<>(); break;
                case 4: listaEscolhida = new Stack<>(); break;
            }

            System.out.println("\n" + ANSI_PURPLE + "--- Iniciando testes com " + BOLD + nomeDaLista + ANSI_RESET + ANSI_PURPLE + " ---" + ANSI_RESET);
            Map<String, Long> resultados = ListTester.executarTodosOsTestes(listaEscolhida);
            imprimirTabelaDeResultados(nomeDaLista, resultados);
            System.out.println(ANSI_PURPLE + "--- Testes finalizados ---" + ANSI_RESET + "\n");
        }

        scanner.close();
    }

    private static void executarTodosOsTestesEImprimirTabela() {
        System.out.println("\n" + ANSI_PURPLE + BOLD + "--- INICIANDO TESTES COMPARATIVOS EM TODAS AS LISTAS ---" + ANSI_RESET);

        Map<String, Map<String, Long>> todosOsResultados = new LinkedHashMap<>();
        List<List<Integer>> listasParaTestar = Arrays.asList(new ArrayList<>(), new LinkedList<>(), new Vector<>(), new Stack<>());

        for (List<Integer> lista : listasParaTestar) {
            String nomeDaLista = lista.getClass().getSimpleName();
            System.out.println("\n" + ANSI_YELLOW + "Processando: " + nomeDaLista + "..." + ANSI_RESET);
            Map<String, Long> resultados = ListTester.executarTodosOsTestes(lista);
            todosOsResultados.put(nomeDaLista, resultados);
        }

        imprimirTabelaComparativa(todosOsResultados);
    }

    private static void imprimirTabelaComparativa(Map<String, Map<String, Long>> todosOsResultados) {
        List<String> nomesDasListas = new ArrayList<>(todosOsResultados.keySet());
        Set<String> nomesDasOperacoes = todosOsResultados.get(nomesDasListas.get(0)).keySet();

        System.out.println("\n" + ANSI_GREEN + BOLD + "====================================== TABELA COMPARATIVA DE DESEMPENHO (ms) ======================================");
        System.out.printf("| %-25s ", "Operação");
        for (String nome : nomesDasListas) {
            System.out.printf("| %-18s ", nome);
        }
        System.out.println("|");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (String operacao : nomesDasOperacoes) {
            System.out.printf("| " + ANSI_YELLOW + "%-25s " + ANSI_RESET, operacao);
            for (String nomeLista : nomesDasListas) {
                long tempo = todosOsResultados.get(nomeLista).get(operacao);
                System.out.printf("| " + ANSI_CYAN + "%-18d " + ANSI_RESET, tempo);
            }
            System.out.println("|");
        }
        System.out.println(ANSI_GREEN + "==================================================================================================================" + ANSI_RESET + "\n");
    }

    private static void imprimirTabelaDeResultados(String nomeLista, Map<String, Long> resultados) {
        System.out.println("\n" + ANSI_GREEN + BOLD + "================== RESULTADOS PARA " + nomeLista.toUpperCase() + " ==================");
        System.out.printf("| %-25s | %-15s |%n", "Operação", "Tempo (ms)");
        System.out.println("---------------------------------------------");

        for (Map.Entry<String, Long> entry : resultados.entrySet()) {
            System.out.printf("| " + ANSI_YELLOW + "%-25s" + ANSI_RESET + " | " + ANSI_CYAN + "%-15d" + ANSI_RESET + " |%n", entry.getKey(), entry.getValue());
        }
        System.out.println(ANSI_GREEN + "=====================================================" + ANSI_RESET);
    }
}