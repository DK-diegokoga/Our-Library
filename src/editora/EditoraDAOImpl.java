package editora;

import java.sql.*;

public class EditoraDAOImpl implements EditoraDAO{

    public static final String URL = "jdbc:mariadb://localhost:3306/bdBiblioteca";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public void adicionar(Editora Ed) {
        try (Connection conMC= DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO tbEDITORA" +
                    "(CODIGO_EDITORA, NOME_EDITORA) VALUES" +
                    "(?, ?)";
            PreparedStatement stmtADD = conMC.prepareStatement(sqlADD);
            stmtADD.setInt(1, Ed.getCODIGO_EDITORA());
            stmtADD.setString(2, Ed.getNOME_EDITORA());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Editora pesquisarPorCodigo(int txtPesquisar) {
        try (Connection conPQM = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQM = "SELECT * FROM tbEDITORA WHERE CODIGO_EDITORA LIKE ?";
            PreparedStatement stmtPQM = conPQM.prepareStatement(sqlPQM);
            stmtPQM.setInt(1, txtPesquisar);
            ResultSet rsM = stmtPQM.executeQuery();
            while (rsM.next()) {
                Editora Ed = new Editora();
                Ed.setCODIGO_EDITORA(rsM.getInt("CODIGO_EDITORA"));
                Ed.setNOME_EDITORA(rsM.getString("NOME_EDITORA"));
                return Ed;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Editora Ed) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM tbEDITORA WHERE CODIGO_EDITORA LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setInt(1, Ed.getCODIGO_EDITORA());
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Editora Ed) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE tbEDITORA SET " +
                    "NOME_EDITORA = ? where CODIGO_EDITORA = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, Ed.getNOME_EDITORA());
            stmtAL.setInt(2, Ed.getCODIGO_EDITORA());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
