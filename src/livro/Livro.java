package livro;

public class Livro {

    private int ISBN;
    private String TITULO;
    private String AUTOR;
    private int EDITORA;
    private int QUANTIDADE_ESTOQUE;
    private int QUANTIDADE_DISPONIVEL;

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getAUTOR() {
        return AUTOR;
    }

    public void setAUTOR(String AUTOR) {
        this.AUTOR = AUTOR;
    }

    public int getEDITORA() {
        return EDITORA;
    }

    public void setEDITORA(int EDITORA) {
        this.EDITORA = EDITORA;
    }

    public int getQUANTIDADE_ESTOQUE() {
        return QUANTIDADE_ESTOQUE;
    }

    public void setQUANTIDADE_ESTOQUE(int QUANTIDADE_ESTOQUE) {
        this.QUANTIDADE_ESTOQUE = QUANTIDADE_ESTOQUE;
    }

    public int getQUANTIDADE_DISPONIVEL() {
        return QUANTIDADE_DISPONIVEL;
    }

    public void setQUANTIDADE_DISPONIVEL(int QUANTIDADE_DISPONIVEL) {
        this.QUANTIDADE_DISPONIVEL = QUANTIDADE_DISPONIVEL;
    }
}
