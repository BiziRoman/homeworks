package Architecture;

import java.util.UUID;

// Singleton класс с Holder-реализацией
public class ConnectionPool {
    // Приватный конструктор
    private ConnectionPool() {}

    // Holder класс для ленивой инициализации
    private static class ConnectionPoolHolder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    // Метод получения экземпляра
    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }

    // Метод получения соединения
    public String getConnection() {
        return "conn-" + UUID.randomUUID();
    }
}
