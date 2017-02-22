package org.pacar_robotics.diagresults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul on 2/12/2017.
 */

public class DiagResultsContent {

	public static final List<DiagResultItem> ITEMS = new ArrayList<>();

	static {
		XmlReader xmlReader = new XmlReader();
		ITEMS.addAll(xmlReader.parseXml());
	}

	/**
	 * Content for each item taken from DiagResults.xml
	 */
	public static class DiagResultItem {
		public final String id;
		public final String name;
		public final String shortDesc;
		public final String longDesc;
		public final String result;
		public final String resultMessage;
		public final String resultSeverity;
		public final String recommendation;

		public DiagResultItem(String id, String name, String shortDesc, String longDesc,
		                      String result, String resultMessage, String resultSeverity,
		                      String recommendation) {
			this.id = id;
			this.name = name.replace("test", "").replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");  // "testFrontLeftMotor" -> "Front Left Motor"
			this.shortDesc = shortDesc;
			this.longDesc = longDesc;
			this.result = result;
			this.resultMessage = resultMessage;
			this.resultSeverity = resultSeverity;
			this.recommendation = recommendation;
		}
	}
}
