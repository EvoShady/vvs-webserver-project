package webserver;

import java.net.*;
import java.io.*;

public class WebServer extends Thread {
	protected Socket clientSocket;

	public static String ROOT_DIRECTORY = "C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\";
	public static String MAINTENANCE_ROOT_DIRECTORY = "C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\";
	public static final String DEFAULT_FILE = "index.html";
	public static final String MAINTENANCE_FILE = "maintenance.html";
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

			String inputLine = "";

			assert in != null;
			inputLine = in.readLine();
			String[] requestArray = inputLine.split(" ");
			System.out.println(requestArray[1]);

			fileRequested = determineServerServingPage(requestArray[1]);

			File file = new File(fileRequested);
			int fileLength = (int) file.length();

			byte[] fileData = readFile(file, fileLength);

			assert out != null;
			out.println("HTTP/1.0 200 OK\n");

			assert dataOut != null;
			dataOut.write(fileData, 0, fileLength);
			dataOut.flush();

			out.close();
			in.close();
			clientSocket.close();
		} catch (FileNotFoundException fileNotFoundException) {
			out.println("HTTP/1.0 404 Not Found\r\n"+
					"Content-type: text/html\r\n\r\n"+
					"<html><head></head><body> 404 File Not Found</body></html>\n");
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
			return MAINTENANCE_ROOT_DIRECTORY + MAINTENANCE_FILE;
		}
		if (s.equals("/")){
			return ROOT_DIRECTORY + DEFAULT_FILE;
		}
		if(s.endsWith(".html") || s.endsWith(".jpg") || s.endsWith(".txt")) {
			return ROOT_DIRECTORY + s;
		}
		return ROOT_DIRECTORY + s + ".html";
	}

	public void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
		File file = new File(fileRequested);
		int fileLength = (int) file.length();
		byte[] fileData = readFile(file, fileLength);

		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();
	}

	public boolean isMaintenanceMode() {
		return maintenanceMode;
	}

	public void setMaintenanceMode(boolean maintenanceMode) {
		this.maintenanceMode = maintenanceMode;
	}

	public String getROOT_DIRECTORY() {
		return ROOT_DIRECTORY;
	}

	public void setROOT_DIRECTORY(String ROOT_DIRECTORY) {
		this.ROOT_DIRECTORY = ROOT_DIRECTORY;
	}

	public String getMAINTENANCE_ROOT_DIRECTORY() {
		return MAINTENANCE_ROOT_DIRECTORY;
	}

	public void setMAINTENANCE_ROOT_DIRECTORY(String MAINTENANCE_ROOT_DIRECTORY) {
		this.MAINTENANCE_ROOT_DIRECTORY = MAINTENANCE_ROOT_DIRECTORY;
	}

}