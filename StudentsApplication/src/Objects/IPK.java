package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DatabaseHandler.databaseHandler;

public class IPK {
    private String NIM;
    private String Nama;
    private double TotalIPK;

    public IPK(String NIM, String Nama, double TotalIPK) {
        this.NIM = NIM;
        this.Nama = Nama;
        this.TotalIPK = TotalIPK;
    }

    public String getNIM() {
        return NIM;
    }

    public String getNama() {
        return Nama;
    }

    public double getTotalIPK() {
        return TotalIPK;
    }

    public static double convertGrade(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            default:
                return 0.0; // Jika dimasukan huruf diluar A B C D maka akan menjadi nilai 0
        }
    }
    
    public static List<IPK> getIPKList() {
        List<IPK> ipkList = new ArrayList<>();
        String query = "SELECT m.NIM, m.Nama, u.NilaiMK " +
                "FROM Mahasiswa m " +
                "JOIN Ujian u ON m.NIM = u.NIM;";

        Connection connection = databaseHandler.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {

            Map<String, List<Double>> gradesMap = new HashMap<>();

            while (result.next()) {
                String NIM = result.getString("NIM");
                String Nama = result.getString("Nama");
                String NilaiMK = result.getString("NilaiMK");

                double ipkAkhir = convertGrade(NilaiMK);
                gradesMap.computeIfAbsent(NIM, k -> new ArrayList<>()).add(ipkAkhir);

                if (gradesMap.get(NIM).size() == 1) {
                    ipkList.add(new IPK(NIM, Nama, 0.0));
                }
            }

            // Menghitung rata-rata IPK
            for (IPK ipk : ipkList) {
                List<Double> grades = gradesMap.get(ipk.getNIM());
                double totalGrades = grades.stream().mapToDouble(Double::doubleValue).sum();
                double averageGrade = totalGrades / grades.size();
                ipk.TotalIPK = averageGrade;
            }

        } catch (SQLException ex) {
            System.out.println("There was an error trying to get IPK list");
            ex.printStackTrace();
        }
        return ipkList;
    }
}
