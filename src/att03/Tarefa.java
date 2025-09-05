package att03;
import java.text.*;
import java.util.*;

public class Tarefa {
    private String descricao;
    private Date prazo;

    public Tarefa(String descricao, Date prazo) {
        this.descricao = descricao;
        this.prazo = prazo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getPrazo() {
        return prazo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Tarefa{descricao='" + descricao + "', prazo=" + sdf.format(prazo) + "}";
    }
}
