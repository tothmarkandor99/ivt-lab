package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private ITorpedoStore primaryTorpedoStore;
  private ITorpedoStore secondaryTorpedoStore;
  @BeforeEach
  public void init(){
    primaryTorpedoStore = mock(TorpedoStore.class);
    secondaryTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success_First(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Failure_First(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success_First_Empty(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Failure_Both_Empty(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success_All(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success_Second_Only(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success_First_Only(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Failure(){
    // Arrange
    when(primaryTorpedoStore.fire(1)).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

}
