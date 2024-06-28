package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DatabaseHandler.databaseHandler;

public record Ujian(
        String NIM,
        String MKID,
        String NilaiMK
) {

    public static List<Ujian> getUjian() {
        Connection connection = databaseHandler.getConnection();
        String query = "SELECT * FROM Ujian;";

        List<Ujian> ujian = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
            	String NIM = result.getString("NIM");
                String MKID = result.getString("MKID");
                String NilaiMK = result.getString("NilaiMK");
                ujian.add(new Ujian(NIM, MKID, NilaiMK));
            }
        } catch (SQLException ex) {
            System.out.println("There was an error trying to get all Exams");
            ex.printStackTrace();
        }
        return ujian;
    }
    
    public static void addUjian(Ujian ujian) {
        Connection connection = databaseHandler.getConnection();
        String query = "INSERT INTO ujian (NIM, MKID, NilaiMK) VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ujian.NIM());
            statement.setString(2, ujian.MKID());
            statement.setString(3, ujian.NilaiMK());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to add mahasiswa");
            ex.printStackTrace();
        }
    }

    public static void updateUjian(Ujian ujian) {
        Connection connection = databaseHandler.getConnection();
        String query = "UPDATE ujian SET NilaiMK=? WHERE NIM=? AND MKID=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, ujian.getNilaiMK());
            statement.setString(2, ujian.getNIM());
            statement.setString(3, ujian.getMKID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to update ujian");
            ex.printStackTrace();
        }
    }


    public static void removeUjian(String NIM, String MKID) {
        Connection connection = databaseHandler.getConnection();
        String query = "DELETE FROM ujian WHERE NIM=? AND MKID=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, NIM);
            statement.setString(2, MKID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to delete mahasiswa");
            ex.printStackTrace();
        }
    }

    public static List<Ujian> getUjianByNIM(String NIM) {
        Connection connection = databaseHandler.getConnection();
        String query = "SELECT * FROM Ujian WHERE NIM=?;";
        List<Ujian> ujianList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, NIM);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String MKID = result.getString("mkid");
                    String NilaiMK = result.getString("nilaimk");
                    ujianList.add(new Ujian(NIM, MKID, NilaiMK));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Failed to get ujian data for nim " + NIM);
            ex.printStackTrace();
        }
        return ujianList;
    }
    
    public String getNIM() {
        return NIM;
    }
    
    public String getMKID() {
        return MKID;
    }
    
    public String getNilaiMK() {
        return NilaiMK;
    }


}
