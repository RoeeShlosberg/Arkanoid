// Roee Shlosberg 211600812
package generators;

/**
 * The type Counter - responsible for value keeping and operations.
 */
public class Counter {
    private int counting;

    /**
     * Instantiates a new Counter.
     *
     * @param number the first value of the counter.
     */
    public Counter(int number) {
        this.counting = number;
    }

    /**
     * Increase - count + x.
     *
     * @param number the number adding.
     */
    public void increase(int number) {
        this.counting += number;
    }

    /**
     * Decrease - count - x.
     *
     * @param number the number subtracting
     */
    public void decrease(int number) {
        this.counting -= number;
    }

    /**
     * Gets value - return the current value of the counter.
     *
     * @return the value.
     */
    public int getValue() {
        return this.counting;
    }
}