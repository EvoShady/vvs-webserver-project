package webserver;

import java.net.*;
import java.io.*;

public class WebServer extends Thread {
	protected Socket clientSocket;
	static final String DEFAULT_FILE = "/a";
	static final String MAINTENANCE_FILE = "/maintenance";
	private volatile boolean maintenanceMode = true;

	public WebServer(Socket clientSoc, boolean maintenanceMode) {
		clientSocket = clientSoc;
		this.maintenanceMode = maintenanceMode;
		start();
	}

	public void run() {
		String fileRequested;

		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream());

			String inputLine;

			inputLine = in.readLine();
			String[] requestArray = inputLine.split(" ");

			fileRequested = determineServerServingPage(requestArray[1]);

			File file = new File("src/TestSite" + fileRequested + ".html");
			int fileLength = (int) file.length();

			byte[] fileData = readFile(file, fileLength);

			out.println("HTTP/1.0 200 OK\n");

			dataOut.write(fileData, 0, fileLength);
			dataOut.flush();

			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}

	private byte[] readFile(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];

		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null)
				fileIn.close();
		}

		return fileData;
	}

	private String determineServerServingPage(String s) {
		if (maintenanceMode == true){
			return MAINTENANCE_FILE;
		}
		if (s.equals("/")){
			return DEFAULT_FILE;
		}
		return s;
	}

	synchronized public void setMaintenanceMode(boolean maintenanceMode) {
		this.maintenanceMode = maintenanceMode;
	}
}