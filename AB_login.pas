program Login_AB;
uses crt;

var
username,password:string;

begin
writeln('===========================');
writeln('= Selamat Datang Di ABWAR =');
writeln('===========================');

write('Username : ');
readln(username);
write('Password : ');
readln(password);

if (username='admin') and (password='admin') then
        writeln('Login Sukses')
else
        writeln('Login Gagal');
readln;
end.
