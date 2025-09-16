import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

/**
 * Avalia expressões matemáticas escritas em Notação Polonesa Reversa (RPN).
 */
public class CalculadoraRPN {

    private static final Set<String> OPERADORES = Set.of("+", "-", "*", "/");

    // ... (Os métodos calcular, processarComponente e realizarOperacao continuam os mesmos)
    public double calcular(String expressao) {
        if (expressao == null || expressao.trim().isEmpty()) {
            throw new IllegalArgumentException("A expressão não pode ser nula ou vazia.");
        }

        Deque<Double> pilhaDeOperandos = new ArrayDeque<>();
        String[] componentes = expressao.trim().split("\\s+");

        for (String componente : componentes) {
            processarComponente(componente, pilhaDeOperandos);
        }

        if (pilhaDeOperandos.size() == 1) {
            return pilhaDeOperandos.pop();
        } else {
            throw new IllegalArgumentException("Erro de sintaxe: A expressão tem operandos sobrando. Faltam operadores?");
        }
    }

    private void processarComponente(String componente, Deque<Double> pilha) {
        if (OPERADORES.contains(componente)) {
            realizarOperacao(componente, pilha);
        } else {
            try {
                pilha.push(Double.parseDouble(componente));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Componente inválido na expressão: '" + componente + "'");
            }
        }
    }

    private void realizarOperacao(String operador, Deque<Double> pilha) {
        if (pilha.size() < 2) {
            throw new IllegalArgumentException("Erro de sintaxe: Operandos insuficientes para a operação '" + operador + "'.");
        }

        double operando2 = pilha.pop();
        double operando1 = pilha.pop();
        double resultado;

        switch (operador) {
            case "+": resultado = operando1 + operando2; break;
            case "-": resultado = operando1 - operando2; break;
            case "*": resultado = operando1 * operando2; break;
            case "/":
                if (operando2 == 0) {
                    throw new ArithmeticException("Erro matemático: Tentativa de divisão por zero.");
                }
                resultado = operando1 / operando2;
                break;
            default:
                throw new IllegalStateException("Operador desconhecido: " + operador);
        }
        
        pilha.push(resultado);
    }
    // --- Fim dos métodos da calculadora ---


    /**
     * Método principal com uma apresentação aprimorada para demonstrar
     * o funcionamento da calculadora com uma lista de testes.
     */
    public static void main(String[] args) {
        CalculadoraRPN calculadora = new CalculadoraRPN();

        System.out.println("==============================================");
        System.out.println("---   Demonstração da Calculadora RPN    ---");
        System.out.println("--- Executando testes pré-definidos...   ---");
        System.out.println("==============================================");

        String[] expressoesParaTestar = {
            "5 1 2 + 4 * +",
            "10 2 * 5 /",
            "15 7 1 1 + - / 3 * 2 1 1 + + -",
            "3.5 1.5 +",
            "5 3 2 + *",
            "5 3 2 +",
            "10 0 /",
            "5 maça 2 +",
            "10 4 - 2 /"
        };

        for (String expressao : expressoesParaTestar) {
            System.out.println("\nAnalisando a expressão: \"" + expressao + "\"");
            try {
                double resultado = calculadora.calcular(expressao);
                System.out.printf("  Resultado: %.4f\n", resultado);
            } catch (Exception e) {
                System.out.println("  Erro: " + e.getMessage());
            }
        }

        System.out.println("\n==============================================");
        System.out.println("---       Fim da Demonstração          ---");
        System.out.println("==============================================");
    }
}