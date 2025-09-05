package att03;

import java.util.*;

public class FilaDePrioridade<T> {

    private ArrayList<T> fila;
    private Comparator<T> comparator;

    public FilaDePrioridade() {
        this.fila = new ArrayList<>();
        this.comparator = null;
    }

    public FilaDePrioridade(Comparator<T> comparator) {
        this.fila = new ArrayList<>();
        this.comparator = comparator;
    }

    public void enfileirar(T elemento) {
        if (fila.isEmpty()) {
            fila.add(elemento);
            return;
        }

        for (int i = 0; i < fila.size(); i++) {
            if (comparar(elemento, fila.get(i)) < 0) {
                fila.add(i, elemento);
                return;
            }
        }
        fila.add(elemento);
    }
   

    public T desenfileirar() {
        return (fila.isEmpty())
                ? null
                : fila.remove(0);
    }

    public T espiar() {
        return (fila.isEmpty())
                ? null
                : fila.get(0);
    }

    private int comparar(T a, T b) {
        if (this.comparator != null) {
            return this.comparator.compare(a, b);
        } else {

            return ((Comparable<T>) a).compareTo(b);
        }
    }


    public boolean estaVazia() {
        return fila.isEmpty();
    }

    public int tamanho() {
        return fila.size();
    }
}
