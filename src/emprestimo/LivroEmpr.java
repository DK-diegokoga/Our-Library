package emprestimo;

public class LivroEmpr {

    private int ISBN;
    private String Titulo;

    public int getISBN() { return ISBN; }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    @Override
    public String toString() {
        return "ISBN=" + ISBN +
                ", Titulo='" + Titulo ;
    }
}
