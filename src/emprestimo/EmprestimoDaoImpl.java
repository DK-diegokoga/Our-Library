package emprestimo;

import aluno.Aluno;

import java.sql.*;

public class EmprestimoDaoImpl implements EmprestimoDao{

    public static final String URL = "jdbc:mariadb://localhost:3306/bdbiblioteca";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public Emprestimo pesquisarPorCodigo(long RA) {
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM tbaluno WHERE RA LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, RA);
            ResultSet rs = stmtPQ.executeQuery();
            while (rs.next()) {
                Emprestimo Al = new Emprestimo();
                Al.setRA(rs.getLong("RA"));
                Al.setNome(rs.getString("nome"));
                Al.setCelular(rs.getLong("celular"));
                Al.setSituacao(rs.getString("situacao"));
                return Al;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Emprestimo pesquisarPorCodigoEMPRESTIMO(long CODIGO) {
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM tbEMPRESTIMO WHERE CODIGO_EMPRESTIMO LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, CODIGO);
            ResultSet rs = stmtPQ.executeQuery();
            while (rs.next()) {
                Emprestimo Al = new Emprestimo();
                Al.setRA(rs.getLong("RA_ALUNO"));
                Al.setNome(rs.getString("nome_ALUNO"));
                Al.setCelular(rs.getLong("celular_ALUNO"));
                Al.setSituacao(rs.getString("situacao_emprestimo"));

                Al.setDataEmprestimo(rs.getString("data_emprestimo"));
                Al.setISBN(rs.getInt("CODIGO_LIVRO"));
                Al.setDataEntrega(rs.getString("data_entrega"));
                Al.setTitulo(rs.getString("titulo_LIVRO"));
                return Al;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
