package com.github.ltsopensource.thread;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author xiaowei
 * @date 2021/06/06
 */
public class ThreadSafeTest {

    private static final ConcurrentHashMap<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap();

    public static void main(String[] args) {
        ThreadSafeTest safeTest = new ThreadSafeTest();

    }

    public DataSource getDataSource(String url, String password) {
        DataSource dataSource = DATA_SOURCE_MAP.get(url);
        if (dataSource == null) {
            DataSource source = createDataSource(url, password);
            DATA_SOURCE_MAP.put(url, source);
            dataSource = source;
        }
        return dataSource;
    }

    private DataSource createDataSource(String url, String password) {
        return new DataSource(url, password);
    }

    private static class DataSource {
        private String url;
        private String password;

        public DataSource(String url, String password) {
            this.url = url;
            this.password = password;
        }
    }
}
