package estoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAOImpl implements EstoqueDAO{
    public static final String URL = "jdbc:mariadb://localhost:3306/bdbiblioteca";
    public static final String USER = "root";
    public static final String PASSWORD = "";


    public List<Estoque> colocarValores(){
        List<Estoque> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT ISBN,TITULO,AUTOR,QUANTIDADE_ESTOQUE,QUANTIDADE_DISPONIVEL FROM tbLIVRO";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque est = new Estoque();
                est.setISBN(rs.getInt("ISBN"));
                est.setTitulo(rs.getString("TITULO"));
                est.setAutor(rs.getString("AUTOR"));
                est.setQuantidadeEstoque(rs.getInt("QUANTIDADE_ESTOQUE"));
                est.setQuantidadeDisponivel(rs.getInt("QUANTIDADE_DISPONIVEL"));
                lista.add(est);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
