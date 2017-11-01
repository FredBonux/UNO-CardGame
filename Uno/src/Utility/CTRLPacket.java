package Utility;

public abstract class CTRLPacket {
	private Packet pacchetto;
	
	public CTRLPacket(Packet p) {
		this.pacchetto = p;
		this.callToAction();
	}	
	
	//Funzione che richiama la funzione astratta dell'evento
	public void callToAction() {
		switch (this.pacchetto.getEvento()) {
		case turno: //E' il turno del client che riceve il pacchetto
			this.myTurn(); 
			break;
		case aspetta:
			this.otherTurn();
			break;
		case sconfitta:
			this.hoPerso();
			break;
		case subisci:
			this.callSubisci();
			break;
		case vittoria:
			this.hoVinto();
			break;
		
		default:
			System.out.println("EVENTO NON VALIDO!");
			break;
		}
	}
	
	public void callSubisci() {
		//Gestisci gli eventi delle tipologie delle carte
		//if( NORMALE ) this.subisciNormale( CARTA );
		//else if( PIU2 ) this.subisciPIU2( CARTA );
		//else if( PIU4 ) this.subisciPIU4( CARTA );
		//else if( COLORE ) this.subisciCambiaColore( CARTA );
		//else if( STOP ) this.subisciStop( CARTA );
		//else if( CAMBIOGIRO ) this.subisciCambioGiro();
		
		return;
	}
	
	public abstract void myTurn();
	public abstract void otherTurn();
	public abstract void hoPerso();
	public abstract void hoVinto();
	
	//Subisci
	public abstract void subisciNormale();
	public abstract void subisciPIU2();
	public abstract void subisciPIU4();
	public abstract void subisciCambiaColore();
	public abstract void subisciStop();
	public abstract void subisciCambioGiro();
	
}
