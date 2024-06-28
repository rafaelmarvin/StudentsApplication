package Objects;

import DatabaseHandler.databaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record Mahasiswa(
        String NIM,
        String Jenis_Kelamin,
        String Nama,
        String Alamat,
        String TempatLahir,
        Date TanggalLahir) {

    public static List<Mahasiswa> getMahasiswa() {
        Connection connection = databaseHandler.getConnection();
        String query = "SELECT * FROM mahasiswa;";

        List<Mahasiswa> mahasiswas = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                String NIM = result.getString("NIM");
                String Jenis_Kelamin = result.getString("Jenis_Kelamin");
                String Nama = result.getString("Nama");
                String Alamat = result.getString("Alamat");
                String TempatLahir = result.getString("TempatLahir");
                java.sql.Date TanggalLahir = result.getDate("TanggalLahir");
                mahasiswas.add(new Mahasiswa(NIM, Jenis_Kelamin, Nama, Alamat, TempatLahir, TanggalLahir));
            }
        } catch (SQLException ex) {
            System.out.println("There was an error trying to get all mahasiswa");
            ex.printStackTrace();
        }
        return mahasiswas;
    }

    public static void addMahasiswa(Mahasiswa mahasiswa) {
        String query = "INSERT INTO mahasiswa (NIM, Jenis_Kelamin, Nama, Alamat, TempatLahir, TanggalLahir) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = databaseHandler.getConnection().prepareStatement(query)) {
            statement.setString(1, mahasiswa.NIM());
            statement.setString(2, mahasiswa.Jenis_Kelamin());
            statement.setString(3, mahasiswa.Nama());
            statement.setString(4, mahasiswa.Alamat());
            statement.setString(5, mahasiswa.TempatLahir());
            statement.setDate(6, mahasiswa.getTanggalLahir());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateMahasiswa(Mahasiswa mahasiswa) {
        Connection connection = databaseHandler.getConnection();
        String query = "UPDATE mahasiswa SET Jenis_Kelamin=?, Nama=?, Alamat=?, TempatLahir=?, TanggalLahir=? WHERE NIM=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, mahasiswa.Jenis_Kelamin());
            statement.setString(2, mahasiswa.Nama());
            statement.setString(3, mahasiswa.Alamat());
            statement.setString(4, mahasiswa.TempatLahir());
            statement.setDate(5, new java.sql.Date(mahasiswa.TanggalLahir().getTime()));
            statement.setString(6, mahasiswa.NIM());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to update mahasiswa");
            ex.printStackTrace();
        }
    }

    public static void deleteMahasiswa(String NIM) {
        Connection connection = databaseHandler.getConnection();
        String query = "DELETE FROM mahasiswa WHERE NIM=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, NIM);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("There was an error trying to delete mahasiswa");
            ex.printStackTrace();
        }
    }
    
    public String getNIM() {
    	return NIM;
    }
    
    public String getJenis_Kelamin() {
    	return Jenis_Kelamin;
    }
    
    public String getNama() {
    	return Nama;
    }
    
    public String getAlamat() {
    	return Alamat;
    }
    
    public String getTempatLahir() {
    	return TempatLahir;
    }
    
    public java.sql.Date getTanggalLahir() {
    	return (java.sql.Date) TanggalLahir;
    }
}

