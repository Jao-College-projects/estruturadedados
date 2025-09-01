package att02;

import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.LinkedHashMap;

public class ListTester {

    private static final int NUM_OPERACOES = 100000;
    private static final Random random = new Random();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static Map<String, Long> executarTodosOsTestes(List<Integer> list) {
        Map<String, Long> resultados = new LinkedHashMap<>();

        System.out.println(ANSI_BLUE + "Testando " + list.getClass().getSimpleName() + " com " + NUM_OPERACOES + " operações." + ANSI_RESET);

        resultados.put("Adicionar no FINAL", testarAdicaoNoFinal(list));
        resultados.put("Adicionar no INÍCIO", testarAdicaoNoInicio(list));
        resultados.put("Acesso Aleatório", testarAcessoAleatorio(list));
        resultados.put("Remover do INÍCIO", testarRemocaoDoInicio(list));
        resultados.put("Remover do FINAL", testarRemocaoDoFinal(list));

        return resultados;
    }

    private static long testarAdicaoNoFinal(List<Integer> list) {
        System.out.println("  -> Executando teste: Adicionar no FINAL...");
        list.clear();
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }

    private static long testarAdicaoNoInicio(List<Integer> list) {
        System.out.println("  -> Executando teste: Adicionar no INÍCIO...");
        list.clear();
        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.add(0, i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }

    private static long testarAcessoAleatorio(List<Integer> list) {
        System.out.println("  -> Executando teste: Acesso Aleatório...");
        list.clear();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.get(random.nextInt(NUM_OPERACOES));
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }

    private static long testarRemocaoDoInicio(List<Integer> list) {
        System.out.println("  -> Executando teste: Remover do INÍCIO...");
        list.clear();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.remove(0);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }

    private static long testarRemocaoDoFinal(List<Integer> list) {
        System.out.println("  -> Executando teste: Remover do FINAL...");
        list.clear();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < NUM_OPERACOES; i++) {
            list.remove(list.size() - 1);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }
}