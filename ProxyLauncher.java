package Architecture;

import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;

// Интерфейс HTTP-сервиса
interface HttpService {
    String get(String url);
}

// Реальный HTTP-сервис
class RealHttpService implements HttpService {
    @Override
    public String get(String url) {
        try {
            System.out.println("Выполняем реальный HTTP-запрос к " + url);
            TimeUnit.MILLISECONDS.sleep(1000); // Имитация задержки
            return "Данные с " + url;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// Прокси-класс с кэшированием
class CachingProxy implements HttpService {
    private final HttpService realService;
    private final Map<String, String> cache = new HashMap<>();

    public CachingProxy(HttpService realService) {
        this.realService = realService;
    }

    @Override
    public String get(String url) {
        // Проверяем наличие в кэше
        if (cache.containsKey(url)) {
            System.out.println("Возвращаем данные из кэша для " + url);
            return cache.get(url);
        }

        // Если нет в кэше - запрашиваем у реального сервиса
        String result = realService.get(url);
        cache.put(url, result);
        return result;
    }
}

public class ProxyLauncher {
    public static void main(String[] args) {
        // Создаем реальный сервис и прокси
        HttpService realService = new RealHttpService();
        HttpService service = new CachingProxy(realService);

        // Первый запрос (долгий)
        System.out.println("Первый запрос:");
        long start = System.currentTimeMillis();
        String result1 = service.get("https://example.com");
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + " мс");
        System.out.println("Результат: " + result1);
        System.out.println();

        // Второй запрос (быстрый)
        System.out.println("Второй запрос:");
        start = System.currentTimeMillis();
        String result2 = service.get("https://example.com");
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + " мс");
        System.out.println("Результат: " + result2);
    }
}

