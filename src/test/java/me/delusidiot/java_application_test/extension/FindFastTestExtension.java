package me.delusidiot.java_application_test.extension;

import me.delusidiot.java_application_test.FastTest;
import me.delusidiot.java_application_test.SlowTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindFastTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private long THRESHOLD;

    public FindFastTestExtension(long THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method requiredTestMethod = context.getRequiredTestMethod();
        FastTest annotation = requiredTestMethod.getAnnotation(FastTest.class);
        String testMethodName = requiredTestMethod.getName();
        ExtensionContext.Store store = getStore(context);
        Long start_time = store.remove("START_TIME", Long.class);
        long duration = System.currentTimeMillis() - start_time;
        if (duration < THRESHOLD && annotation == null)
            System.out.printf("Please consider mark method [%s] with @FastTest.\n", testMethodName);
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        return store;
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }
}
