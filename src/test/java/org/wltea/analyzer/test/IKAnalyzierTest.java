package org.wltea.analyzer.test;

import java.io.StringReader;

import junit.framework.TestCase;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IKAnalyzierTest extends TestCase {
	public void testToken() throws Exception {
		Analyzer analyzer = new IKAnalyzer(); 
		TokenStream ts = analyzer.tokenStream("myfield", new StringReader(
				"【3店通用】仅99元，享价值337元『东来顺』双人套餐！中华老字号，传统老北京涮肉！"));
		OffsetAttribute offsetAtt = ts.addAttribute(OffsetAttribute.class);
		CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);
		PositionIncrementAttribute posIncrAtt = ts.addAttribute(PositionIncrementAttribute.class); 
		TypeAttribute typeAtt = ts.addAttribute(TypeAttribute.class);
		try {
			ts.reset(); // Resets this stream to the beginning. (Required)
			while (ts.incrementToken()) {
				// Use AttributeSource.reflectAsString(boolean)
				// for token stream debugging.
//				System.out.println("token: " + ts.reflectAsString(true));
				System.out.println("term:[" +termAtt.toString()+"]");
				System.out.println("termType:[" +typeAtt.type()+"]");
				System.out.println("positionIncrement:" +posIncrAtt.getPositionIncrement());
				System.out.println("token start offset: "
						+ offsetAtt.startOffset());
				System.out.println("  token end offset: "
						+ offsetAtt.endOffset());
			}
			ts.end(); // Perform end-of-stream operations, e.g. set the final
						// offset.
		} finally {
			ts.close(); // Release resources associated with this stream.
			analyzer.close();
		}
	}
}
