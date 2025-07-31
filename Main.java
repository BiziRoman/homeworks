package Architecture;

import java.util.UUID;

// DAO для работы с пользователями
class UserDao {
    final ConnectionPool pool = ConnectionPool.getInstance();

    public void fetchUser() {
        String connection = pool.getConnection();
        System.out.println("UserDao использует соединение: " + connection);
    }
}

// DAO для работы с заказами
class OrderDao {
    final ConnectionPool pool = ConnectionPool.getInstance();

    public void fetchOrder() {
        String connection = pool.getConnection();
        System.out.println("OrderDao использует соединение: " + connection);
    }
}

public class Main {
    public static void main(String[] args) {
        // Получаем пул соединений
        ConnectionPool pool = ConnectionPool.getInstance();

        // Выводим идентификатор пула
        System.out.println("Идентификатор пула соединений: " +
                System.identityHashCode(pool));

        // Создаем DAO объекты
        UserDao userDao = new UserDao();
        OrderDao orderDao = new OrderDao();

        // Вызываем методы DAO
        userDao.fetchUser();
        orderDao.fetchOrder();

        // Проверяем, что оба DAO используют один и тот же пул
        System.out.println("\nПроверка использования одного пула:");
        System.out.println("UserDao пул: " +
                System.identityHashCode(userDao.pool));
        System.out.println("OrderDao пул: " +
                System.identityHashCode(orderDao.pool));
    }
}

