/**
 * 
 */
package org.wltea.analyzer.test;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.wltea.analyzer.lucene.IKTokenizer;

/**
 * @author 林良益
 *
 */
public class IKTokenizerTest extends TestCase {
	
	public void testLucene4Tokenizer(){
		String t = "IK分词器Lucene Analyzer接口实现类 民生银行";
		IKTokenizer tokenizer = new IKTokenizer(new StringReader(t) , false);
		CharTermAttribute termAtt = tokenizer.getAttribute(CharTermAttribute.class);
	    PositionIncrementAttribute posIncrAtt = tokenizer.getAttribute(PositionIncrementAttribute.class);
		try {
			while(tokenizer.incrementToken()){
				System.out.println(termAtt.toString());				
				System.out.println(posIncrAtt);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
