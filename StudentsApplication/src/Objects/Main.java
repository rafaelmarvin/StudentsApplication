package Objects;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import DatabaseHandler.databaseHandler;

public class Main extends Application {

    private TableView<Mahasiswa> mahasiswaTable;
    private TableView<MataKuliah> mataKuliahTable;
    private TableView<Ujian>ujianTable;

    private TextField nimInput, genderInput, nameInput, addressInput, placeInput, dateInput;
    private TextField mkidInput, namamkInput, sksInput;
    private TextField nimUjianInput, mkidUjianInput, nilaiInput;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("rekapMahasiswa Apps");

        TabPane tabPane = new TabPane();
        
        Tab tabMahasiswa = new Tab("Mahasiswa", detailMahasiswa());
        Tab tabMatkul = new Tab("Mata Kuliah", detailMatkul());
        Tab tabUjian = new Tab("Ujian", detailUjian());
        Tab tabIPK = new Tab("IPK", detailIPK());
        
        tabPane.getTabs().addAll(tabMahasiswa, tabMatkul, tabUjian, tabIPK);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        Scene scene = new Scene(tabPane, 1052,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    private VBox detailMahasiswa() {
        TableColumn<Mahasiswa, String> nimColumn = new TableColumn<>("NIM");
        nimColumn.setMinWidth(150);
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("NIM"));

        TableColumn<Mahasiswa, String> genderColumn = new TableColumn<>("Jenis Kelamin");
        genderColumn.setMinWidth(100);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("Jenis_Kelamin"));

        TableColumn<Mahasiswa, String> nameColumn = new TableColumn<>("Nama");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Nama"));

        TableColumn<Mahasiswa, String> addressColumn = new TableColumn<>("Alamat");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Alamat"));

        TableColumn<Mahasiswa, String> placeColumn = new TableColumn<>("Tempat Lahir");
        placeColumn.setMinWidth(200);
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("TempatLahir"));

        TableColumn<Mahasiswa, Date> dateColumn = new TableColumn<>("Tanggal Lahir");
        dateColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("TanggalLahir"));

        mahasiswaTable = new TableView<>();
        mahasiswaTable.getColumns().addAll(nimColumn, genderColumn, nameColumn, addressColumn, placeColumn, dateColumn);
        
        // Inputs
        nimInput = new TextField();
        nimInput.setPromptText("NIM");
        nimInput.setMinWidth(100);

        genderInput = new TextField();
        genderInput.setPromptText("Gender");
        genderInput.setMinWidth(100);

        nameInput = new TextField();
        nameInput.setPromptText("Nama");
        nameInput.setMinWidth(100);

        addressInput = new TextField();
        addressInput.setPromptText("Alamat");
        addressInput.setMinWidth(100);

        placeInput = new TextField();
        placeInput.setPromptText("Tempat Lahir");
        placeInput.setMinWidth(100);

        dateInput = new TextField();
        dateInput.setPromptText("Tanggal Lahir (yyyy-mm-dd)");
        dateInput.setMinWidth(100);

        Button addStudentBtn = new Button("Tambahkan Mahasiswa");
        addStudentBtn.setOnAction(e -> addMahasiswa());
        Button removeStudentBtn = new Button("Hapus Mahasiswa");
        removeStudentBtn.setOnAction(e -> removeMahasiswa());
        Button updateStudentBtn = new Button("Update Mahasiswa");
        updateStudentBtn.setOnAction(e -> updateMahasiswa());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nimInput, genderInput, nameInput, addressInput, placeInput, dateInput, addStudentBtn, removeStudentBtn, updateStudentBtn);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(mahasiswaTable, hBox);

        tampilkanMahasiswa();
        return vbox;
    }

    	
    	private VBox detailMatkul() {
    		mataKuliahTable = new TableView<>();
    		
    		TableColumn<MataKuliah, String> mkidColumn = new TableColumn<>("Kode");
		    mkidColumn.setMinWidth(100);
		    mkidColumn.setCellValueFactory(new PropertyValueFactory<>("MKID"));

		    TableColumn<MataKuliah, String> namamkColumn = new TableColumn<>("Mata Kuliah");
		    namamkColumn.setMinWidth(250);
		    namamkColumn.setCellValueFactory(new PropertyValueFactory<>("NamaMK"));

		    TableColumn<MataKuliah, Integer> sksColumn = new TableColumn<>("SKS");
		    sksColumn.setMinWidth(100);
		    sksColumn.setCellValueFactory(new PropertyValueFactory<>("SKS"));

		    mataKuliahTable.getColumns().addAll(mkidColumn, namamkColumn, sksColumn);
		    
		    // Inputs
		    mkidInput = new TextField();
		    mkidInput.setPromptText("MKID");
		    mkidInput.setMinWidth(100);

		    namamkInput = new TextField();
		    namamkInput.setPromptText("Mata Kuliah");
		    namamkInput.setMinWidth(100);

		    sksInput = new TextField();
		    sksInput.setPromptText("SKS");
		    sksInput.setMinWidth(100);


		    Button addMKBtn = new Button("Tambah");
		    addMKBtn.setOnAction(e -> addMataKuliah());
		    Button removeMKBtn = new Button("Hapus");
		    removeMKBtn.setOnAction(e -> removeMataKuliah());
	        Button updateMKBtn = new Button("Update");
	        updateMKBtn.setOnAction(e -> updateMataKuliah());

		    HBox hBox = new HBox();
		    hBox.setPadding(new Insets(10, 10, 10, 10));
		    hBox.setSpacing(10);
		    hBox.getChildren().addAll(mkidInput, namamkInput, sksInput, addMKBtn, removeMKBtn, updateMKBtn);

		    VBox vbox = new VBox(10);
		    vbox.getChildren().addAll(mataKuliahTable, hBox);

		    tampilkanMatakuliah();
		    return vbox;
    		
    	}
    	
    	private VBox detailUjian() {
    		TableColumn<Ujian, String> nimColumn = new TableColumn<>("NIM");
		    nimColumn.setMinWidth(150);
		    nimColumn.setCellValueFactory(new PropertyValueFactory<>("NIM"));

		    TableColumn<Ujian, String> mkidColumn = new TableColumn<>("Kode Mata Kuliah");
		    mkidColumn.setMinWidth(100);
		    mkidColumn.setCellValueFactory(new PropertyValueFactory<>("MKID"));

		    TableColumn<Ujian, String> nilaiColumn = new TableColumn<>("Nilai");
		    nilaiColumn.setMinWidth(250);
		    nilaiColumn.setCellValueFactory(new PropertyValueFactory<>("NilaiMK"));

		    ujianTable = new TableView<>();
		    ujianTable.getColumns().addAll(nimColumn, mkidColumn, nilaiColumn);
		    
		    // Inputs
		    nimUjianInput = new TextField();
		    nimUjianInput.setPromptText("NIM");
		    nimUjianInput.setMinWidth(100);

		    mkidUjianInput = new TextField();
		    mkidUjianInput.setPromptText("MKID");
		    mkidUjianInput.setMinWidth(100);

		    nilaiInput = new TextField();
		    nilaiInput.setPromptText("Nilai");
		    nilaiInput.setMinWidth(100);

		    Button searchExamBtn = new Button("Cari Ujian");
		    searchExamBtn.setOnAction(e -> searchExam());
		    Button addExamBtn = new Button("Tambah");
		    addExamBtn.setOnAction(e -> tambahUjian());
		    Button removeExamBtn = new Button("Hapus");
		    removeExamBtn.setOnAction(e -> hapusUjian());
		    Button updateExamBtn = new Button("Update");
	        updateExamBtn.setOnAction(e -> updateUjian());

		    HBox hBox = new HBox();
		    hBox.setPadding(new Insets(10, 10, 10, 10));
		    hBox.setSpacing(10);
		    hBox.getChildren().addAll(nimUjianInput, mkidUjianInput, nilaiInput, searchExamBtn, addExamBtn, removeExamBtn, updateExamBtn);

		    VBox vbox = new VBox(10);
		    vbox.getChildren().addAll(ujianTable, hBox);

		    tampilkanUjian();
		    return vbox;
    	}
    	
    	private VBox detailIPK() {
    	    TableColumn<IPK, String> nimColumn = new TableColumn<>("NIM");
    	    nimColumn.setMinWidth(150);
    	    nimColumn.setCellValueFactory(new PropertyValueFactory<>("NIM"));

    	    TableColumn<IPK, String> nameColumn = new TableColumn<>("Nama");
    	    nameColumn.setMinWidth(250);
    	    nameColumn.setCellValueFactory(new PropertyValueFactory<>("Nama"));

    	    TableColumn<IPK, Double> ipkColumn = new TableColumn<>("Total IPK");
    	    ipkColumn.setMinWidth(100);
    	    ipkColumn.setCellValueFactory(new PropertyValueFactory<>("TotalIPK"));

    	    TableView<IPK> ipkTable = new TableView<>();
    	    ipkTable.getColumns().addAll(nimColumn, nameColumn, ipkColumn);

    	    VBox vbox = new VBox(10);
    	    vbox.getChildren().addAll(ipkTable);

    	    tampilkanIPK(ipkTable);
    	    return vbox;
    	}

    	private void tampilkanIPK(TableView<IPK> ipkTable) {
    	    ObservableList<IPK> list = FXCollections.observableArrayList();
    	    list.addAll(IPK.getIPKList());
    	    ipkTable.setItems(list);
    	}

	
    	
    	

    private void tampilkanMahasiswa() {
    	ObservableList<Mahasiswa> list = FXCollections.observableArrayList();
    	list.addAll(Mahasiswa.getMahasiswa());
        mahasiswaTable.setItems(list);
    }
    
    private void addMahasiswa() {
        try {
            String nim = nimInput.getText();
            String gender = genderInput.getText();
            String name = nameInput.getText();
            String address = addressInput.getText();
            String place = placeInput.getText();
            Date date = Date.valueOf(dateInput.getText());

            Mahasiswa mahasiswa = new Mahasiswa(nim, gender, name, address, place, date);
            Mahasiswa.addMahasiswa(mahasiswa);
            mahasiswaTable.getItems().add(mahasiswa);

            clearFields();
            tampilkanMahasiswa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeMahasiswa() {
    	String nim = nimInput.getText();
    	Mahasiswa.deleteMahasiswa(nim);
    	
    	clearFields();
    	tampilkanMahasiswa();
    }
    
    private void updateMahasiswa() {
    	String nim = nimInput.getText();
        String gender = genderInput.getText();
        String name = nameInput.getText();
        String address = addressInput.getText();
        String place = placeInput.getText();
        Date date = Date.valueOf(dateInput.getText());

        Mahasiswa mahasiswa = new Mahasiswa(nim, gender, name, address, place, date);
        Mahasiswa.updateMahasiswa(mahasiswa);

        // Update table view
        int index = mahasiswaTable.getItems().indexOf(mahasiswa);
        if (index != -1) {
            mahasiswaTable.getItems().set(index, mahasiswa);
        }

        clearFields();
        nimInput.clear();
        tampilkanMahasiswa();
    }
    
    private void clearFields() {
        nimInput.clear();
        genderInput.clear();
        nameInput.clear();
        addressInput.clear();
        placeInput.clear();
        dateInput.clear();
        mkidInput.clear();
        namamkInput.clear();
        sksInput.clear();
        nimUjianInput.clear();
        mkidUjianInput.clear();
        nilaiInput.clear();
    }
    


    private void tampilkanMatakuliah() {
        ObservableList<MataKuliah> list = FXCollections.observableArrayList();
        list.addAll(MataKuliah.getMataKuliah());
        mataKuliahTable.setItems(list);
    }

    
    private void addMataKuliah() {
    	try {
            String mkid = mkidInput.getText();
            String namamk = namamkInput.getText();
            int sks = Integer.parseInt(sksInput.getText());

            MataKuliah matakuliah = new MataKuliah(mkid, namamk, sks);
            MataKuliah.addMataKuliah(matakuliah);
            mataKuliahTable.getItems().add(matakuliah);

            clearFields();
            tampilkanMatakuliah();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void removeMataKuliah() {
    	String mkid = mkidInput.getText();
    	MataKuliah.removeMataKuliah(mkid);
    	
    	clearFields();
    	tampilkanMatakuliah();
    }
    
    private void updateMataKuliah() {
        String mkid = mkidInput.getText();
        String namamk = namamkInput.getText();
        int sks = Integer.parseInt(sksInput.getText());

        MataKuliah matakuliah = new MataKuliah(mkid, namamk, sks);
        MataKuliah.updateMataKuliah(matakuliah);

        // Update table view
        int index = mataKuliahTable.getItems().indexOf(matakuliah);
        if (index != -1) {
            mataKuliahTable.getItems().set(index, matakuliah);
        }

        clearFields();
        mkidInput.clear();
        tampilkanMatakuliah();
    }

    private void tampilkanUjian() {
    	ObservableList<Ujian> list = FXCollections.observableArrayList();
        list.addAll(Ujian.getUjian());
        ujianTable.setItems(list);
    }
    
    private void tambahUjian() {
    	try {
            String nimUjian = nimUjianInput.getText();
            String mkidUjian= mkidUjianInput.getText();
            String nilaimkUjian = nilaiInput.getText();


            Ujian ujian = new Ujian(nimUjian, mkidUjian, nilaimkUjian);
            Ujian.addUjian(ujian);
            ujianTable.getItems().add(ujian);

            clearFields();
            tampilkanUjian();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void hapusUjian() {
    	String nim = nimUjianInput.getText();
    	String mkid = mkidUjianInput.getText();
    	Ujian.removeUjian(nim, mkid);
    	
    	clearFields();
    	tampilkanUjian();
    }

    private void updateUjian() {
    	String nimUjian = nimUjianInput.getText();
    	String mkidUjian = mkidUjianInput.getText();
        String nilaiUjian = nilaiInput.getText();

        Ujian ujian = new Ujian(nimUjian, mkidUjian, nilaiUjian);
        Ujian.updateUjian(ujian);

        // Update table view
        int index = ujianTable.getItems().indexOf(ujian);
        if (index != -1) {
            ujianTable.getItems().set(index, ujian);
        }

        clearFields();
        mkidUjianInput.clear();
        nimUjianInput.clear();
        nilaiInput.clear();
        tampilkanUjian();
    }
    
    private void searchExam() {
        String nim = nimUjianInput.getText();
        List<Ujian> ujianList = Ujian.getUjianByNIM(nim);

        if (ujianList != null && !ujianList.isEmpty()) {
            ObservableList<Ujian> list = FXCollections.observableArrayList(ujianList);
            ujianTable.setItems(list);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText(null);
            alert.setContentText("Data ujian tidak ditemukan untuk NIM: " + nim);
            alert.showAndWait();
        }
    }


    public static void main(String[] args) {
        databaseHandler.connect();
        launch(args);
    }
}
