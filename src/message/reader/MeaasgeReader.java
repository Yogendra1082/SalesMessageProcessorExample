package message.reader;

/**
* Created by Yogendra N
* Date: 27-03-2018
* Class Name: MeaasgeReader
* This class contains the main program to run the application.
* Requires a test input file containing sales message details.
*
*/


import java.io.BufferedReader;
import java.io.FileReader;

import message.processor.MessageProcessor;

public class MeaasgeReader {

	public static void main(String[] args) {

		// Read inputs from test file
		try {
			String line;
			MessageProcessor process = new MessageProcessor();
			BufferedReader inputFile = new BufferedReader(new FileReader("src/Input.txt"));

			while ((line = inputFile.readLine()) != null) {

				process.processMessage(line);
			}

		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

}
