program showMenu;
uses crt;

var
    id    :integer;
    nama  :string;
    harga :integer;


begin
    writeln('===================');
    writeln('==== VIEW MENU ====');
    writeln('===================');
    
    for (i:=1) to n do 
    begin
    id:=idMenu[i];
    nama:=namaMenu[i];
    hargaMenu:=hargaMenu[i];
    write(nama);
    writeln(harga);
    end;
end.
