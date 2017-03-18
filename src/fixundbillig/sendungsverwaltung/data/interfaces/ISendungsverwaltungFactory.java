package fixundbillig.sendungsverwaltung.data.interfaces;

/**
 * Created by Don on 21.12.2016.
 *
 * @author Don
 * @since 21.21.2016
 */
@SuppressWarnings("ALL")
public interface ISendungsverwaltungFactory {
	IPackstueckAnlegen getPackstueckAnlegen();
	ISendungAnlegen getSendungAnlegen();

	void destroy();
}
