/**
 * Edit by Tikhonov Sergey
 */
public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return this.description;
    }
    public abstract void go(Car c);
}
