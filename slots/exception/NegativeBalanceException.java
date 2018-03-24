package slots.exception;

/**
  * @author  Erin Brintnell
  *  
  * @version     1.0
  *       
  * The NegativeBalanceException class inherits the exception class and is thrown whenever the balance of the
  * slot machine is negative. Since no one should be able to play the game after they have reached a balance of
  * zero dollars the balance of the slot machine should never be negative, if this is the case this exception
  * is thrown
  *
  */

public class NegativeBalanceException extends Exception{
  
  /** Default Constructor
    *
    * No parameters are given when the exception is thrown. Thus there is no detailed message about the 
    * exception, thus the super class is called with no exception message.
    */
  public NegativeBalanceException(){
    super();
  }
  
  /** Constructor
    *
    * A string with a message is given when the exception is thrown making the message about the exception
    * slightly more detailed. The super class is then called with this message.
    *
    * @param      message - the detailed message about the exception.
    *
    */
  public NegativeBalanceException(String message){
    super(message);
  }

}
