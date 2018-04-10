
public interface CumparareVanzareTricou {
	/**
	 * Se adauga tricouri la stoc.
	 * @param tricou Se precizeaza toate atributele tricoului.
	 * @param cantitate
	 */
	void cumparare(Tricou tricou, int cantitate);
	/**
	 *  Se scad tricouri din stoc.
	 * @param tricou Se precizeaza doar codul.
	 * @param cantitate
	 */
	void vanzare(Tricou tricou, int cantitate);
}
