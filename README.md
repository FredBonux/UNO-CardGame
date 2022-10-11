# Uno-CardGame 

## Struttura generale
L'applicativo è formato da un server e da due client, i due client si collegano al server tramite indirizzo ip locale e porta.

## Compilare il server
La funzione `main` del server è contenuta nel file `ServerView.java` all'interno della cartella `src/Server`\
È sufficiente compilare questo file per ottenere un eseguibile per l'app del server.

## Compilare il client
La funzione `main` del client è contenuta nel file `Client.java` all'interno della cartella `src/Main`\
È sufficiente compilare questo file per ottenere un eseguibile per l'app del client.

## Iniziare una partita
Dopo aver avviato il server e aver ottenuto la combinazione IP + PORTA, avviare i due client e inserire i dati necessari per procedere al collegamento con il server.\
Il server accoppia automaticamente i client e genera le varie partita. 