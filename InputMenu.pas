Program InputMenu;
uses crt;

var
nama : string;
harga : integer;

begin
clrscr;
writeln('==================');
writeln('= INPUT MENU =');
writeln('==================');

write('Nama Menu : ');
readln(nama);
write('Harga Menu : ');
readln(harga);

if(nama<>null) and (harga<>null) then
    writeln('Input Berhasil')
else
    writeln('Nama atau Harga tidak boleh kosong');
end.