package hu.bme.mit.spaceship;

/**
* TorpedoStore interface. A TorpedoStore should be able to tell the torpedo count, and if the store is empty.
* A TorpedoStore also should be able to fire torpedos.
*
*/
public interface ITorpedoStore {

  public boolean fire(int numberOfTorpedos);

  public boolean isEmpty();

  public int getTorpedoCount();
}
