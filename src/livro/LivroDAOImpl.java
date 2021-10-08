package livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImpl implements LivroDAO {
    public static final String URL = "jdbc:mariadb://localhost:3306/bdBiblioteca";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public List<String> carregarEditora() {
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT NOME_EDITORA FROM tbEDITORA";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            ResultSet rsLg = stmtLg.executeQuery();
            int i = 0;
            List<String> lista = new ArrayList<>();
            while (rsLg.next()) {
                lista.add(i, rsLg.getString(1));
                i++;
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int buscarPorNomeEditora(String cbEditoraValue){
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT CODIGO_EDITORA FROM tbEDITORA WHERE NOME_EDITORA LIKE ?";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            stmtLg.setString(1, cbEditoraValue);
            ResultSet rsLg = stmtLg.executeQuery();
            rsLg.next();
            return Integer.parseInt(rsLg.getString("CODIGO_EDITORA"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void adicionar(Livro Lv) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO tbLIVRO" +
                    "(ISBN, TITULO, AUTOR, EDITORA, QUANTIDADE_ESTOQUE, QUANTIDADE_DISPONIVEL" +
                    ") VALUES" +
                    "(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtADD = con.prepareStatement(sqlADD);
            stmtADD.setInt(1, Lv.getISBN());
            stmtADD.setString(2, Lv.getTITULO());
            stmtADD.setString(3, Lv.getAUTOR());
            stmtADD.setInt(4, Lv.getEDITORA());
            stmtADD.setInt(5, Lv.getQUANTIDADE_ESTOQUE());
            stmtADD.setInt(6, Lv.getQUANTIDADE_DISPONIVEL());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void excluir(int cod) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM tbLIVRO WHERE ISBN LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setLong(1, cod);
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Livro Lv) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE tbLIVRO SET " +
                    "TITULO = ?, " +
                    "AUTOR = ?, " +
                    "EDITORA = ?, " +
                    "QUANTIDADE_ESTOQUE = ?, " +
                    "QUANTIDADE_DISPONIVEL = ? where ISBN = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, Lv.getTITULO());
            stmtAL.setString(2, Lv.getAUTOR());
            stmtAL.setInt(3, Lv.getEDITORA());
            stmtAL.setInt(4, Lv.getQUANTIDADE_ESTOQUE());
            stmtAL.setInt(5, Lv.getQUANTIDADE_DISPONIVEL());
            stmtAL.setInt(6, Lv.getISBN());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro pesquisarPorCodigo(int cod) {
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM tbLIVRO WHERE ISBN LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, cod);
            ResultSet rs = stmtPQ.executeQuery();
            while (rs.next()) {
                Livro Lv = new Livro();
                Lv.setISBN(rs.getInt("ISBN"));
                Lv.setQUANTIDADE_ESTOQUE(rs.getInt("QUANTIDADE_ESTOQUE"));
                Lv.setQUANTIDADE_DISPONIVEL(rs.getInt("QUANTIDADE_DISPONIVEL"));
                Lv.setEDITORA(rs.getInt("EDITORA"));
                Lv.setTITULO(rs.getString("TITULO"));
                Lv.setAUTOR(rs.getString("AUTOR"));
                return Lv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
