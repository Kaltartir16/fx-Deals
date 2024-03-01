package com.bloomberg.fxdealanalyzer.flogger;

import com.google.common.flogger.FluentLogger;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class FluentLoggerAnswer implements Answer<Object> {
    private final FluentLogger.Api api = Mockito.mock(FluentLogger.Api.class);

    @Override
    public Object answer(InvocationOnMock invocation) {
        return api;
    }
}
