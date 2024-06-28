package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DatabaseHandler.databaseHandler;

public record MataKuliah(String MKID, String NamaMK, int SKS) {

	
    public static List<MataKuliah> getMataKuliah() {
        Connection connection = databaseHandler.getConnection();
        String query = "SELECT * FROM matakuliah;";

        List<MataKuliah> matakuliah = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                String MKID = result.getString("MKID");
                String NamaMK = result.getString("NamaMK");
                int SKS = result.getInt("SKS");
                matakuliah.add(new MataKuliah(MKID, NamaMK, SKS));
            }
        } catch (SQLException ex) {
            System.out.println("There was an error trying to get all matakuliah");
            ex.printStackTrace();
        }
        return matakuliah;
    }
    
    public static void addMataKuliah(MataKuliah matakuliah) {
        Connection connection = databaseHandler.getConnection();
        String query = "INSERT INTO matakuliah (MKID, NamaMK, SKS) VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, matakuliah.MKID());
            statement.setString(2, matakuliah.NamaMK());
            statement.setInt(3, matakuliah.SKS());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to add mahasiswa");
            ex.printStackTrace();
        }
    }

    public static void updateMataKuliah(MataKuliah matakuliah) {
        Connection connection = databaseHandler.getConnection();
        String query = "UPDATE matakuliah SET NamaMK=?, SKS=? WHERE MKID=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, matakuliah.NamaMK());
            statement.setInt(2, matakuliah.SKS());
            statement.setString(3, matakuliah.MKID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to update mahasiswa");
            ex.printStackTrace();
        }
    }

    public static void removeMataKuliah(String MKID) {
        Connection connection = databaseHandler.getConnection();
        String query = "DELETE FROM matakuliah WHERE MKID=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, MKID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to delete mahasiswa");
            ex.printStackTrace();
        }
    }
    
    public String getMKID() {
        return MKID;
    }

    public String getNamaMK() {
        return NamaMK;
    }

    public int getSKS() {
        return SKS;
    }


	


}

