package sharePaint.network;

/**
 * Holds a message
 * @author AJW
 */
public class Message
{
    private String client;
    private String action;
    private String message;

    public Message(String client, String action, String message)
    {
	   this.client = client;
	   this.action = action;
	   this.message = message;
    }

    public String getClient()
    {
	   return client;
    }

    public String getAction()
    {
	   return action;
    }

    public String getMessage()
    {
	   return message;
    }
}
