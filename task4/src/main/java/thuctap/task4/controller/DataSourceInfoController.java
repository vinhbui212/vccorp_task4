package thuctap.task4.controller;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;

@RestController
public class DataSourceInfoController {

    private final DataSource dataSource;

    @Autowired
    public DataSourceInfoController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/connection-info")
    public String getConnectionInfo() {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
            int totalConnections = hikariDataSource.getHikariPoolMXBean().getTotalConnections();
            int idleConnections = hikariDataSource.getHikariPoolMXBean().getIdleConnections();
            int activeConnections = hikariDataSource.getHikariPoolMXBean().getActiveConnections();
            int pendingThreads = hikariDataSource.getHikariPoolMXBean().getThreadsAwaitingConnection();

            StringBuilder sb = new StringBuilder();
            sb.append("Total connections: ").append(totalConnections).append("\n");
            sb.append("Idle connections: ").append(idleConnections).append("\n");
            sb.append("Active connections: ").append(activeConnections).append("\n");
            sb.append("Threads awaiting connection: ").append(pendingThreads).append("\n");

            return sb.toString();
        } else {
            return "DataSource is not an instance of HikariDataSource";
        }
    }
}

