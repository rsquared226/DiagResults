package org.pacar_robotics.diagresults;

import android.os.Environment;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Rahul on 2/19/2017.
 */

public class XmlReader {

	// File Reading Constants
	private static final String FILE_NAME = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
			"/PACAR/DiagResults.xml";
	private static final String ID = "TestId";
	private static final String TEST_NAME = "TestName";
	private static final String SHORT_DESC = "TestShortDescription";
	private static final String LONG_DESC = "TestLongDescription";
	private static final String RESULT = "TestResult";
	private static final String RESULT_MESSAGE = "TestResultMessage";
	private static final String RESULT_SEVERITY = "TestResultSeverity";
	private static final String RECOMMENDATION = "TestRecommendation";

	public ArrayList<DiagResultsContent.DiagResultItem> parseXml() {
		ArrayList<DiagResultsContent.DiagResultItem> robotTests = new ArrayList<>();

		try {
			File file = new File(FILE_NAME);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			// Parse XML into DOM structure, initializing document
			Document doc = builder.parse(file);

			doc.getDocumentElement().normalize();

			NodeList listofRobotTests = doc.getElementsByTagName("RobotTest");

			for (int i = 0; i < listofRobotTests.getLength(); i++) {
				Node node = listofRobotTests.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					robotTests.add(new DiagResultsContent.DiagResultItem(
							getTextContent(element, ID),
							getTextContent(element, TEST_NAME),
							getTextContent(element, SHORT_DESC),
							getTextContent(element, LONG_DESC),
							getTextContent(element, RESULT),
							getTextContent(element, RESULT_MESSAGE),
							getTextContent(element, RESULT_SEVERITY),
							getTextContent(element, RECOMMENDATION)));
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			Log.e("XmlReader", e.getMessage());
			// Attempt to tell user that something is wrong with the XML file
			robotTests.add(new DiagResultsContent.DiagResultItem("0", "CannotReadFile",
					"", "", "Failed", e.getMessage(), "DEADLY", "See if file exists"));
		}
		return robotTests;
	}

	private String getTextContent(Element element, String name) {
		return element.getElementsByTagName(name).item(0).getTextContent().trim();
	}
}
