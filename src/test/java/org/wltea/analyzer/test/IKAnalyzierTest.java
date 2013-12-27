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
//		TokenStream ts = analyzer.tokenStream("myfield", new StringReader(
//				"美的（Midea） W13PCS503E 易拆洗一手开系列 5L电压力锅"));
		TokenStream ts = analyzer.tokenStream("myfield", new StringReader(
				"中华人民共和国"));
		OffsetAttribute offsetAtt = ts.addAttribute(OffsetAttribute.class);
		CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);
		PositionIncrementAttribute posIncrAtt = ts.addAttribute(PositionIncrementAttribute.class); 
		TypeAttribute typeAtt = ts.addAttribute(TypeAttribute.class);
		try {
			ts.reset(); // Resets this stream to the beginning. (Required)
			int pos=0;
			while (ts.incrementToken()) {
				// Use AttributeSource.reflectAsString(boolean)
				// for token stream debugging.
//				System.out.println("token: " + ts.reflectAsString(true));
				StringBuilder sb = new StringBuilder();
				sb.append("term:\"" +termAtt.toString()+"\"").append(",\n");
				sb.append("termType:\"" +typeAtt.type()+"\"").append(",\n");
				int increment = posIncrAtt.getPositionIncrement();
				if(increment>0){
					pos = pos+increment;
				}
				sb.append("positionIncrement:" +increment).append(",\n");
				sb.append("position:" +pos).append(",\n");
				sb.append("StartOffset: "
						+ offsetAtt.startOffset()).append(",\n");
				sb.append("EndOffset: "
						+ offsetAtt.endOffset()).append("");
				System.out.println(sb.toString());
			}
			ts.end(); // Perform end-of-stream operations, e.g. set the final
						// offset.
		} finally {
			ts.close(); // Release resources associated with this stream.
			analyzer.close();
		}
	}
}
