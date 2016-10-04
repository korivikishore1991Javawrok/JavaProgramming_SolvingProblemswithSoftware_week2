/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

public class URLFinder {
	public StorageResource findURLs(String url) {
		URLResource page = new URLResource(url);
		String source = page.asString();
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {
			int index = source.indexOf("href=", start);
			if (index == -1) {
				break;
			}
			int firstQuote = index+6; // after href="
			int endQuote = source.indexOf("\"", firstQuote);
			String sub = source.substring(firstQuote, endQuote);
			if (sub.startsWith("http")) {
				store.add(sub);
			}
			start = endQuote + 1;
		}
		return store;
	}

	public void testURL() {
		StorageResource url = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        int count = 0;
		for (String link : url.data()) {
           int start = 0; 
           while (true) {
			int index = link.indexOf(".", start);
			if (index == -1) {
				break;
			}
            else {
                count = count + 1;
            }
			start = start + 2;
		}			
    }
     System.out.println(count);
  	}
}
