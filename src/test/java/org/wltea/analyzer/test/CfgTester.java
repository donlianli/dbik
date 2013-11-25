/**
 * 
 */
package org.wltea.analyzer.test;

import junit.framework.TestCase;

import org.wltea.analyzer.cfg.DefaultConfig;

/**
 * @author Administrator
 *
 */
public class CfgTester extends TestCase{
	
	public void testCfgLoading(){
		System.out.println(DefaultConfig.getInstance().getExtDictionarys().size());
		System.out.println(DefaultConfig.getInstance().getExtStopWordDictionarys().size());
	}

}
