package com.qgo.profiler.core.callMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qgo.profiler.connector.annotation.PrintLevel;
/**
 * 访问信息转化为日志打印出来
 * @author van
 *
 */
public class CallMarkManagerImpl implements CallMarkManager {

	private static Logger log = LoggerFactory.getLogger("profiler.callMark.log");



	public void showCallMark(CallMark callMark) {
		log.info(callMark.toString());
	}

	public void showCallMark(CallMark callMark, PrintLevel printLevel) {
		if(printLevel==PrintLevel.DEBUG){
			log.debug(callMark.toString());
			
		}else if(printLevel==PrintLevel.INFO){
			log.info(callMark.toString());
			
		}else if(printLevel==PrintLevel.WARN){
			log.warn(callMark.toString());
			
		}else if(printLevel==PrintLevel.ERROR){
			log.error(callMark.toString());
		}
		
	}

}
