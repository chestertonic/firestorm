package firestorm.utils.managers;

/**
 * Created by slinkee on 10/10/2016.
 */
public abstract class ResourceManager {
    protected int count = 1;

    public void addReference() {
        count++;
    }

    public boolean removeReference() {
        count--;
        return (count == 0);
    }
}
