package com.zaomeng.zaomeng.support;

import com.tmall.wireless.tangram.support.InternalErrorSupport;

import java.util.Map;

public class SampleErrorSupport extends InternalErrorSupport {
    @Override
    public void onError(int code, String msg, Map<String, Object> relativeInfoMap) {


        super.onError(code, msg, relativeInfoMap);
    }
}
