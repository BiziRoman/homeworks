package Architecture;

// Абстрактная фабрика уведомлений
abstract class Notifier {
    // Фабричный метод
    abstract Notification createNotification();

    // Метод для отправки уведомления
    public void notifyUser(String text) {
        Notification notification = createNotification();
        notification.send(text);
    }
}

// Конкретные фабрики
class EmailNotifier extends Notifier {
    @Override
    Notification createNotification() {
        return new EmailNotification();
    }
}

class SmsNotifier extends Notifier {
    @Override
    Notification createNotification() {
        return new SmsNotification();
    }
}

class PushNotifier extends Notifier {
    @Override
    Notification createNotification() {
        return new PushNotification();
    }
}

// Абстрактный класс уведомления
interface Notification {
    void send(String text);
}

// Конкретные реализации уведомлений
class EmailNotification implements Notification {
    @Override
    public void send(String text) {
        System.out.println("[EMAIL] " + text);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String text) {
        System.out.println("[SMS] " + text);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String text) {
        System.out.println("[PUSH] " + text);
    }
}

// Точка входа
public class NotificationApp {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите тип уведомления: email/sms/push");
            return;
        }

        String type = args[0].toLowerCase();
        Notifier notifier = null;

        switch (type) {
            case "email":
                notifier = new EmailNotifier();
                break;
            case "sms":
                notifier = new SmsNotifier();
                break;
            case "push":
                notifier = new PushNotifier();
                break;
            default:
                System.out.println("Неизвестный тип уведомления");
                return;
        }

        notifier.notifyUser("Hello!");
    }
}
