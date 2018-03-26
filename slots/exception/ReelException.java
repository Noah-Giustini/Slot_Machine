
package slots.exception;

/**
  * @author  Erin Brintnell
  *  
  * @version     1.0
  *       
  * The ReelException class inherits the exception class and is thrown whenever the reels are not set up 
  * properly on the gameboard.
  *
  */
public class ReelException extends Exception{
 
    /** Default Constructor
      *
      * No parameters are given when the exception is thrown. Thus there is no detailed message about the 
      * exception, thus the super class is called with no exception message.
      */
    public ReelException(){
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
    public ReelException(String message){
        super(message);
    }
  


}

