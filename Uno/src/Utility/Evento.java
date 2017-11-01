package Utility;

public enum Evento {
	turno, //Inviato al client che deve fare la giocata
	aspetta, //Inviato al client che deve attendere la giocata dell'avversario
	butto, //Inviato dal client che ha fatto la giocata
	subisci, //Inviato al client che subisce la giocata
	vittoria, //Inviato al client vincitore
	sconfitta //Inviato al client perdente
}
