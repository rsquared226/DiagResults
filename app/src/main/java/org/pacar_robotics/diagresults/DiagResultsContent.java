package org.pacar_robotics.diagresults;

/**
 * Created by Rahul on 2/12/2017.
 */

public class DiagResultsContent {

	/**
	 *  Content for each item taken from DiagResults.xml
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
			this.name = name;
			this.shortDesc = shortDesc;
			this.longDesc = longDesc;
			this.result = result;
			this.resultMessage = resultMessage;
			this.resultSeverity = resultSeverity;
			this.recommendation = recommendation;
		}
	}
}
