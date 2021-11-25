package webserver;

import java.net.*;
import java.io.*;

public class WebServer extends Thread {
	protected Socket clientSocket;
	static final String DEFAULT_FILE = "/index.html";
	static final String MAINTENANCE_FILE = "/maintenance.html";
	private volatile boolean maintenanceMode;

	public WebServer() {

	}

	public WebServer(Socket clientSoc, boolean maintenanceMode) {
		clientSocket = clientSoc;
		this.maintenanceMode = maintenanceMode;
		start();
	}

    public void run() {
		String fileRequested = null;
		PrintWriter out = null;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedOutputStream dataOut = null;
		try {
			dataOut = new BufferedOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {

			String inputLine;

			inputLine = in.readLine();
			String[] requestArray = inputLine.split(" ");
			System.out.println(requestArray[1]);

			fileRequested = determineServerServingPage(requestArray[1]);

			File file = new File("src/TestSite" + fileRequested);
			int fileLength = (int) file.length();

			byte[] fileData = readFile(file, fileLength);

			out.println("HTTP/1.0 200 OK\n");

			dataOut.write(fileData, 0, fileLength);
			dataOut.flush();

			out.close();
			in.close();
			clientSocket.close();
		} catch (FileNotFoundException fileNotFoundException) {
			try {
				fileNotFound(out, dataOut, fileRequested);
			} catch (IOException ioe) {
				System.err.println("Error with file not found exception : " + ioe.getMessage());
			}
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			throw new RuntimeException();
		}
	}

	public byte[] readFile(File file, int fileLength) throws IOException {
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

	public String determineServerServingPage(String s) {
		if (maintenanceMode){
			return MAINTENANCE_FILE;
		}
		if (s.equals("/")){
			return DEFAULT_FILE;
		}
		if(s.endsWith(".html") || s.endsWith(".jpg") || s.endsWith(".txt")) {
			return s;
		}
		return s + ".html";
	}

	public void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
		File file = new File(fileRequested);
		int fileLength = (int) file.length();
		byte[] fileData = readFile(file, fileLength);

		out.println("HTTP/1.0 404 File Not Found\n");

		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();
	}

	public boolean isMaintenanceMode() {
		return maintenanceMode;
	}

	public void setMaintenanceMode(boolean maintenanceMode) {
		this.maintenanceMode = maintenanceMode;
	}
}