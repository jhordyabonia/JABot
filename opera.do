::OPEN
::KILL
[SYSTEM RUN],[WAIT 1000],taskkill /im opera.exe,[ENTER/],[WAIT 5000]
::KILL
[SYSTEM RUN],[WAIT 1000],opera /private,[ENTER/],[WAIT 5000]
::OPEN
::VPN
[WAIT 5000]
[F6/],[F6/],[F6/],[WAIT 500]
[TAB/],[WAIT 400],[WAIT 150], ::150,[WAIT 150], ::150
[ESCAPE/],[WAIT 400],[F5/]
::VPN
::ATRAS
[F5/],[WAIT 4567],[F6/],[WAIT 123], ::100,
::ATRAS
::DESCRIP
Libreria de comandos del navegador Opera
::DESCRIP