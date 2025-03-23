package domain;
import java.awt.Color;

/*No olviden adicionar la documentacion*/
public interface Item{
  public static final int ROUND = 1;
  public static final int SQUARE = 2;
  /**depends of the item will decide the movement and state
     */
  public abstract void decide();
  /**makes the change of the item
     */
  public default void change(){
  };
  /** return the shape of the item (ROUND or SQUARE)
     */
  public default int shape(){
      return ROUND;
  }
  /**return the color of the item
     */
  public default Color getColor(){
      return Color.black;
  };
  /**
   * return if isActive
     */
  public default boolean isActive(){
      return true;
  }
  /**
   * return if isAgent
     */
  public default boolean isAgent(){
      return false;
  }    
     
}
