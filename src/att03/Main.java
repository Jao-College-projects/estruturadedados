package att03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        executarCenarioPacientes();
        executarCenarioTarefas();
    }


    public static void executarCenarioPacientes() {
        imprimirCabecalho("Cenário 1: Atendimento de Pacientes (Ordem por Urgência - Comparable)");
        FilaDePrioridade<Paciente> filaAtendimento = new FilaDePrioridade<>();
        List<Paciente> pacientes = List.of(
                new Paciente("Carlos", 3),
                new Paciente("Maria", 5),
                new Paciente("Ana", 8),
                new Paciente("Sofia", 10) // Maior urgência
        );

        long inicio = System.nanoTime();

        System.out.println("Enfileirando pacientes...");
        pacientes.forEach(filaAtendimento::enfileirar);

        System.out.println("\nOrdem de atendimento definida:");
        processarFila(filaAtendimento, "Atendendo", "Próximo paciente");

        long fim = System.nanoTime();
        imprimirTempoDeResposta(inicio, fim);
    }


    public static void executarCenarioTarefas() {
        imprimirCabecalho("Cenário 2: Gerenciador de Tarefas (Ordem por Prazo - Comparator)");
        Comparator<Tarefa> comparadorPorPrazo = (t1, t2) -> t1.getPrazo().compareTo(t2.getPrazo());
        FilaDePrioridade<Tarefa> filaTarefas = new FilaDePrioridade<>(comparadorPorPrazo);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        long inicio = System.nanoTime();

        System.out.println("Adicionando tarefas com prazos...");
        try {
            filaTarefas.enfileirar(new Tarefa("Revisar documento importante", sdf.parse("10/09/2025")));
            filaTarefas.enfileirar(new Tarefa("Entregar relatório final", sdf.parse("08/09/2025")));
            filaTarefas.enfileirar(new Tarefa("Comprar material para o projeto", sdf.parse("15/09/2025")));
            filaTarefas.enfileirar(new Tarefa("Agendar reunião com a equipe", sdf.parse("06/09/2025")));
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + e.getMessage());
        }

        System.out.println("\nOrdem de execução das tarefas:");
        processarFila(filaTarefas, "Executando", "Próxima tarefa");

        long fim = System.nanoTime();
        imprimirTempoDeResposta(inicio, fim);
    }

    private static <T> void processarFila(FilaDePrioridade<T> fila, String acao, String proximoLabel) {
        if (fila.estaVazia()) {
            System.out.println("A fila está vazia. Nenhuma ação a ser realizada.");
            return;
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("  -> " + proximoLabel + ": " + fila.espiar());
        System.out.println("---------------------------------------------------------");
        while (!fila.estaVazia()) {
            System.out.println("  " + acao + ": " + fila.desenfileirar());
        }
    }

    private static void imprimirCabecalho(String titulo) {
        System.out.println("\n=========================================================");
        System.out.println("  " + titulo);
        System.out.println("=========================================================");
    }

    private static void imprimirTempoDeResposta(long startTimeNanos, long endTimeNanos) {
        long duracaoNanos = endTimeNanos - startTimeNanos;
        double duracaoMillis = TimeUnit.NANOSECONDS.toMillis(duracaoNanos);
        System.out.println("---------------------------------------------------------");
        System.out.printf("  Tempo de execução: %.4f ms%n", duracaoMillis);
        System.out.println("=========================================================\n");
    }
}