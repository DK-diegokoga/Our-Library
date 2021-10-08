package aluno;

import java.sql.*;

public class AlunoDAOImpl implements AlunoDAO {

    public static final String URL = "jdbc:mariadb://localhost:3306/bdbiblioteca";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public void adicionar(Aluno Al) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO TBALUNO" +
                    "(RA, NOME, EMAIL, CELULAR, SITUACAO, PENALIDADE, DESCRICAO) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtADD = con.prepareStatement(sqlADD);
            stmtADD.setLong(1, Al.getRA());
            stmtADD.setString(2, Al.getNome());
            stmtADD.setString(3, Al.getEmail());
            stmtADD.setLong(4, Al.getCelular());
            stmtADD.setString(5, Al.getSituacao());
            stmtADD.setString(6, Al.getPenalidade());
            stmtADD.setString(7, Al.getDescricao());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Aluno pesquisarPorCodigo(long RA) {
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM tbaluno WHERE RA LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, RA);
            ResultSet rs = stmtPQ.executeQuery();
            while (rs.next()) {
                Aluno Al = new Aluno();
                Al.setRA(rs.getLong("RA"));
                Al.setNome(rs.getString("nome"));
                Al.setEmail(rs.getString("email"));
                Al.setCelular(rs.getLong("celular"));
                Al.setSituacao(rs.getString("situacao"));
                Al.setPenalidade(rs.getString("penalidade"));
                Al.setDescricao(rs.getString("descricao"));
                return Al;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Aluno Al) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM tbaluno WHERE RA LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setLong(1, Al.getRA());
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Aluno Al) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE TBALUNO SET " +
                    "NOME = ?, " +
                    "EMAIL = ?, " +
                    "CELULAR = ?, " +
                    "SITUACAO = ?, " +
                    "PENALIDADE = ?," +
                    "DESCRICAO = ? where RA = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, Al.getNome());
            stmtAL.setString(2, Al.getEmail());
            stmtAL.setLong(3, Al.getCelular());
            stmtAL.setString(4, Al.getSituacao());
            stmtAL.setString(5, Al.getPenalidade());
            stmtAL.setString(6, Al.getDescricao());
            stmtAL.setLong(7, Al.getRA());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void admin() {
        try (Connection conAD = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM tbfuncionario WHERE codigo LIKE ?";
            PreparedStatement stmt = conAD.prepareStatement(sql);
            stmt.setLong(1, 0);
            ResultSet rs = stmt.executeQuery();
            if (!rs.first()) {
                Aluno Fn = new Aluno();
                Fn.setNome("ADMIN");
                Fn.setEmail("ADMIN@ADMIIN.COM");
                Fn.setConfEmail("ADMIN@ADMIIN.COM");
                Fn.setSenha("ADMIN1234");
                Fn.setConfSenha("ADMIN1234");
                Fn.setCodigo(0);
                Fn.setPermissao("MASTER");
                adicionar(Fn);
            }
        } catch (SQLException e) {

        }
    }*/
}
