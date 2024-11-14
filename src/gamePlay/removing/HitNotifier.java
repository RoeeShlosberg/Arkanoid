// Roee Shlosberg 211600812
package gamePlay.removing;

/**
 * The interface Hit notifier - is an object that indicates a listener every time that it being hit.
 */
public interface HitNotifier {
    /**
     * Add hit listener - adding a listener to this object.
     *
     * @param hl the hitting listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener - removing a listener from this object.
     *
     * @param hl the hitting listener.
     */
    void removeHitListener(HitListener hl);
}