//Refined abstraction
public class ConcreteRemote extends RemoteControl
{
   private int currentChannel; 
   
   public void nextChannel()
   {
       currentChannel++;
	   setChannel(currentChannel);
   }
   
   public void prevChannel()
   {
       currentChannel--;
	   setChannel(currentChannel);
   }
   
   
}