import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SerialCommunication  {

	public static SerialPort serialPort;
	public static OutputStream sender;
	
	public SerialCommunication() {
		try {
			@SuppressWarnings("rawtypes")
			Enumeration ports = CommPortIdentifier.getPortIdentifiers();
			CommPortIdentifier port = null;
			String nomPort = "/dev/cu.usbserial-A5025UQ7"; // Revoir ...
			while (ports.hasMoreElements()) {
				CommPortIdentifier cPort = (CommPortIdentifier) ports.nextElement();
				System.out.println(cPort.getName());
				if (cPort.getName().equals(nomPort)) {
					port = cPort;
					break;
				}
			}
			
			serialPort = (SerialPort) port.open("port", 2000);
			
			serialPort.setSerialPortParams( 
				9600, 
				SerialPort.DATABITS_8, 
				SerialPort.STOPBITS_1, 
				SerialPort.PARITY_NONE 
			);
			serialPort.notifyOnOutputEmpty(true);
			
			sender = new PrintStream(serialPort.getOutputStream());
			
			System.out.println("Port serie pret.");
		}
		catch(Exception e) {}
	}
	
	public static void envoyer(char c) {
		try {
			if(sender!=null)
				sender.write(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
