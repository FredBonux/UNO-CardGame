package Utility;

public enum Evento {
	turno, //Inviato al client che deve fare la giocata
	aspetta, //Inviato al client che deve attendere la giocata dell'avversario
	butto, //Inviato dal client che ha fatto la giocata
	pesco, //Inviato dal client che ha fatto la giocata ma ha pescato
	subisci, //Inviato al client che subisce la giocata
	vittoria, //Inviato al client vincitore
	sconfitta, //Inviato al client perdente
	mano, //Inviato insiame alla mano iniziale
	badPlay, //Inviato al client che ha fatto una giocata scorretta
	okPlay, //Inviato al client per confermare la giocata
	connessione, //Inviato al client alla connessione al server
	penalita //Inviato al client che commette un errore con penalita'
}
