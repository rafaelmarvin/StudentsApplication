# StudentsApplication
Create an application to recap student data such as CRUD in student data, courses taken, grades obtained and GPA through calculations displayed for all students after taking the exam.

# SQL Code to Access Database for this Java App
CREATE DATABASE rekapMahasiswa;


CREATE TABLE Mahasiswa (
NIM VARCHAR (10) PRIMARY KEY,
Jenis_Kelamin VARCHAR(10),
Nama VARCHAR (30),
Alamat VARCHAR(100),
TempatLahir VARCHAR (30),
TanggalLahir DATE NOT NULL);

INSERT INTO Mahasiswa (NIM, Jenis_Kelamin, Nama, Alamat, TempatLahir, TanggalLahir) 
VALUES
('2602080176','Pria','Rafael Marvin', 'Jl. Merdeka No.4','Bandung','2004-10-15'),
('2602153436','Pria','Ilham Nurdiana', 'Jl. Jendral Sudirman No.5','Jakarta','2003-06-01'),
('2602107991','Pria','Donald Nathaniel Gunawan', 'Jl. Kopo No.7','Semarang','2004-01-14'),
('2602096205','Pria','Eugenia Phoebe', 'Jl. Cibaduyut No.6','Jogja','2004-10-10');

CREATE TABLE MataKuliah (
MKID VARCHAR(5) PRIMARY KEY,
NamaMK VARCHAR (50),
SKS INT(1) NOT NULL);

INSERT INTO MataKuliah (MKID, NamaMK, SKS)
VALUES
('MK001','Business Application Development',4),
('MK002','Business Information System Research',4),
('MK003','Business Law and Ethics',2),
('MK004','Business Plan',6),
('MK005','Information System Analysis and Design',6),
('MK006','Information Technology Infrastructure',2);

CREATE TABLE Ujian (
NIM VARCHAR(10),
MKID VARCHAR(5),
NilaiMK CHAR(1) NOT NULL);

INSERT INTO Ujian (NIM, MKID, NilaiMK)
VALUES
('2602080176','MK001','A'),
('2602080176','MK002','A'),
('2602080176','MK003','B'),
('2602080176','MK004','B'),
('2602080176','MK005','A'),
('2602080176','MK006','A'),
('2602153436','MK001','B'),
('2602153436','MK002','B'),
('2602153436','MK003','A'),
('2602153436','MK004','A'),
('2602153436','MK005','B'),
('2602153436','MK006','A'),
('2602107991','MK001','A'),
('2602107991','MK002','A'),
('2602107991','MK003','A'),
('2602107991','MK004','B'),
('2602107991','MK005','A'),
('2602107991','MK006','B'),
('2602096205','MK001','B'),
('2602096205','MK002','A'),
('2602096205','MK003','A'),
('2602096205','MK004','B'),
('2602096205','MK005','B'),
('2602096205','MK006','A');

