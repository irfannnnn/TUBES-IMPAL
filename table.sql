create table Pemilik (
	idAkun int,
	posisi varchar(255),
	constraint PK_Pemilik primary key (idAkun),
	constraint FK_Pemilik foreign key (idAkun) references Kasir(idAkun)
);

create table Karyawan (
	idAkun int,
	gaji int,
	constraint PK_Karyawan primary key (idAkun),
	constraint FK_Karyawan foreign key (idAkun) references Kasir(idAkun)
);

create table Menu (
	idMenu int,
	namaMenu varchar(255),
	hargaMenu int,
	constraint PK_Menu primary key (idMenu)
);

create table Kasir (
	idAkun int,
	username varchar(255),
	password varchar(8),
	nama varchar(255),
	noTelp int,
	alamat varchar(255),
	constraint PK_Kasir primary key (idAkun)
);

create table Belanjaan (
	idBelanjaan int,
	idAkun int,
	namaBelanjaan varchar(255),
	hargaBelanjaan int,
	totalPengeluaran int,
	constraint PK_Belanjaan primary key (idBelanjaan),
	constraint FK_Belanjaan foreign key (idAkun) references Kasir(idAkun)
);

create table Transaksi (
	idTransaksi int,
	idAkun int,
	idMenu int,
	constraint PK_Transaksi primary key (idTransaksi),
	constraint FK_Transaksi1 foreign key (idAkun) references Kasir(idAkun),
	constraint FK_Transaksi2 foreign key (idMenu) references Menu(idMenu)
);

create table DetilTransaksi (
	idDetil int,
	idTransaksi int,
	totalHarga int,
	tglTransaksi date,
	constraint PK_DetilTransaksi primary key (idDetil),
	constraint FK_DetilTransaksi foreign key (idTransaksi) references Transaksi(idTransaksi)
);

create table Recap (
	idRecap int,
	idDetil int,
	idBelanjaan int,
	labaBersih int,
	labaKotor int,
	tglRecap date,
	constraint PK_Recap primary key (idRecap),
	constraint FK_Recap1 foreign key (idDetil) references DetilTransaksi(idDetil),
	constraint FK_Recap2 foreign key (idBelanjaan) references Belanjaan(idBelanjaan)
);


insert into Pemilik values (1,Pemilik);
insert into Pemilik valies (2,Admin);

insert into Karyawan values (3,1000000);

insert into Menu values (0,Nasi,4000);
insert into Menu values (1,Es teh,2000);

insert into Kasir values (3,parjo,iniparjo,Suparjo,089665432,Sukabirus);

insert into Belanjaan values (0,3,Sawi,20000,20000);

insert into Transaksi values (0,3,1);
insert into Transaksi values (0,3,2);

insert into DetilTransaksi values (0,0,7000,18-1-2018);

insert into Recap values (0,0,0,2000,5000,30-1-2018);

select * from Pemilik;