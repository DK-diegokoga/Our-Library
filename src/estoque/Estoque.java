package estoque;

public class Estoque {

    private int ISBN;
    private String Titulo;
    private String Autor;
    private int QuantidadeEstoque;
    private int QuantidadeDisponivel;

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public int getQuantidadeEstoque() {
        return QuantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        QuantidadeEstoque = quantidadeEstoque;
    }

    public int getQuantidadeDisponivel() {
        return QuantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        QuantidadeDisponivel = quantidadeDisponivel;
    }


    @Override
    public String toString() {
        return "Estoque{" +
                "ISBN=" + ISBN +
                ", Titulo='" + Titulo + '\'' +
                ", Autor='" + Autor + '\'' +
                ", QuantidadeEstoque=" + QuantidadeEstoque +
                ", QuantidadeDisponivel=" + QuantidadeDisponivel +
                '}';
    }
}
