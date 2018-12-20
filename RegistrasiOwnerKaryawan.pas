Program RegisterOwnerKaryawan;
uses crt;
var
username,password:string;
begin
writeln('=============================');
writeln('= Registrasi Owner/Karyawan =');
writeln('=============================');

//input data
write('Username : ');
readln(username);
write('Password : ');
readln(password);

//check input
if(username<>null) and (password<>null) then
    writeln('Registrasi Berhasil')
else
    writeln('Username atau Password tidak boleh kosong');
end.