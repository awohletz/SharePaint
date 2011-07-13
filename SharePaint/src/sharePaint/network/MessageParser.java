package sharePaint.network;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

/**
 * Parses messages.
 * @author AJW
 *
 * Bibliography:
 * http://www.developertutorials.com/tutorials/java/read-xml-file-in-java-050611-1112/
 */
public class MessageParser
{
    /**
	* Parse a message
	* @param msg the raw text of the message
	* @return a Message
	* @throws Exception if any parsing errors occur
	*/
    public Message parse(String msg) throws Exception
    {
	   InputStream XML = new ByteArrayInputStream(msg.getBytes());

	   DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	   DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	   Document doc = docBuilder.parse(XML);
	   doc.getDocumentElement().normalize();
	   NodeList events = doc.getElementsByTagName("event");

	   for (int s = 0; s < events.getLength(); s++)
	   {

		  Node eventNode = events.item(s);
		  if (eventNode.getNodeType() == Node.ELEMENT_NODE)
		  {

			 Element eventElement = (Element) eventNode;
			 String client = getElementValue(eventElement, "client");
			 String action = getElementValue(eventElement, "action");
			 String message = getElementValue(eventElement, "message");

			 return new Message(client, action, message);
		  }
		  else
		  {
			 throw new Exception("Invalid XML");
		  }
	   }

	   return new Message("Unknown", "Unknown", "Unknown");
    }

    private String getElementValue(Element element, String tag)
    {
	   NodeList clientList = element.getElementsByTagName(tag);
	   Element clientElement = (Element) clientList.item(0);
	   NodeList textClientList = clientElement.getChildNodes();
	   return (textClientList.item(0)).getNodeValue().trim();
    }
}
