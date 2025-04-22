package com.cdp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "features")
public class FeatureConfig {
    private Authentication authentication = new Authentication();
    private Monitoring monitoring = new Monitoring();
    private Database database = new Database();
    private RateLimiting rateLimiting = new RateLimiting();
    private Security security = new Security();
    private MonitoringDashboards monitoringDashboards = new MonitoringDashboards();

    @Data
    public static class Authentication {
        private boolean enabled = false;
    }

    @Data
    public static class Monitoring {
        private Prometheus prometheus = new Prometheus();
        private Grafana grafana = new Grafana();

        @Data
        public static class Prometheus {
            private boolean enabled = false;
        }

        @Data
        public static class Grafana {
            private boolean enabled = false;
        }
    }

    @Data
    public static class Database {
        private MariaDb mariaDb = new MariaDb();

        @Data
        public static class MariaDb {
            private boolean enabled = false;
        }
    }

    @Data
    public static class RateLimiting {
        private boolean enabled = false;
    }

    @Data
    public static class Security {
        private boolean enabled = false;
    }

    @Data
    public static class MonitoringDashboards {
        private boolean enabled = false;
    }
} 