package sharePaint.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ConnectionListener
{
	public void clientConnected(ObjectInputStream in, ObjectOutputStream out);
}
