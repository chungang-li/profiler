package com.qgo.profiler.core.callMonitor;

import com.qgo.profiler.connector.annotation.PrintLevel;

public interface CallMarkManager {
	public void showCallMark(CallMark callMark);
	public void showCallMark(CallMark callMark, PrintLevel printLevel);
}
