package com.campusconnect.neo4j.logging;

import com.jamonapi.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PerfLogHolder {


    private String apiName;
    private MonitorFactoryInterface factory;
    private Long startTime;
    private Long endTime;

    public boolean isRunning() {
        return running;
    }

    private boolean running;
    public static final String REG_EX_JAMON_LABEL = "JAMon Label=(.*), Units=.*";

    private PerfLogHolder() {

    }

    public String getApiName() {
        return apiName;
    }

    public MonitorFactoryInterface getFactory() {
        if (!running && factory == null)
            throw new IllegalStateException("Perf logger not started.");
        return factory;
    }

    public void start(String apiName) {
        running = true;
        this.apiName = apiName;
        factory = new FactoryEnabled();
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        if (!running) {
            throw new IllegalStateException("Perf logger not started.");
        }
        running = false;
        endTime = System.currentTimeMillis();
    }

    public Long getTotalExecutionTime() {
        if (running) {
            throw new IllegalStateException("Perf logger not yet stopped.");
        }
        return endTime - startTime;
    }

    public void reset() {
        this.apiName = null;
        startTime = endTime = 0L;
        running = false;
        factory = null;
    }

    public String getSummaryString() {
        if (running) {
            throw new IllegalStateException("Perf logger no yet stopped.");
        }

        MonitorComposite monitorComposite = factory.getRootMonitor();
        Monitor[] monitors = monitorComposite.getMonitors();

        StringBuilder stringBuilder = new StringBuilder();

        if (monitors != null) {
            for (Monitor monitor : monitors) {
                MonKey monKey = monitor.getMonKey();
                String api = null;
                String jamonKeyString = monKey.toString();

                Pattern pattern = Pattern.compile(REG_EX_JAMON_LABEL);
                Matcher matcher = pattern.matcher(jamonKeyString);

                if (matcher.matches())
                    api = matcher.group(1);

                stringBuilder.append(api);
                stringBuilder.append(":").append((int) Math.ceil(monitor.getTotal()));
                stringBuilder.append(":").append((int) Math.ceil(monitor.getAvg()));
                stringBuilder.append(":").append((int) Math.ceil(monitor.getHits()));
                stringBuilder.append(":").append((int) Math.ceil(monitor.getMin()));
                stringBuilder.append(":").append((int) Math.ceil(monitor.getMax()));
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }


    public static PerfLogHolder getPerfLogHolder() {
        return PERF_LOG_HOLDER_THREAD_LOCAL.get();
    }

    private static final ThreadLocal<PerfLogHolder> PERF_LOG_HOLDER_THREAD_LOCAL = new ThreadLocal<PerfLogHolder>() {
        @Override
        protected PerfLogHolder initialValue() {
            return new PerfLogHolder();
        }
    };
}
