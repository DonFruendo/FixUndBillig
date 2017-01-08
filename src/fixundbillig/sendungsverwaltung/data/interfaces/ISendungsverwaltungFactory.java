package fixundbillig.sendungsverwaltung.data.interfaces;

@SuppressWarnings("ALL")
public interface ISendungsverwaltungFactory {
	IPackstueckAnlegen getPackstueckAnlegen();
	ISendungAnlegen getSendungAnlegen();

	void destroy();
}
