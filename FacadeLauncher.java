package Architecture;

// Подсистема бронирования авиабилетов
class FlightBooking {
    private boolean isBooked = false;

    public void book() {
        System.out.println("Авиабилеты забронированы");
        isBooked = true;
    }

    public void cancel() {
        if (isBooked) {
            System.out.println("Бронирование авиабилетов отменено");
            isBooked = false;
        }
    }
}

// Подсистема бронирования отеля
class HotelBooking {
    private boolean isBooked = false;

    public void book() {
        System.out.println("Отель забронирован");
        isBooked = true;
    }

    public void cancel() {
        if (isBooked) {
            System.out.println("Бронирование отеля отменено");
            isBooked = false;
        }
    }
}

// Подсистема аренды автомобиля
class CarRental {
    private boolean isBooked = false;

    public void book() {
        System.out.println("Автомобиль арендован");
        isBooked = true;
    }

    public void cancel() {
        if (isBooked) {
            System.out.println("Аренда автомобиля отменена");
            isBooked = false;
        }
    }
}

// Фасад для бронирования путешествия
class TravelFacade {
    private FlightBooking flight = new FlightBooking();
    private HotelBooking hotel = new HotelBooking();
    private CarRental car = new CarRental();

    public void planTrip() {
        System.out.println("Начинаем планирование путешествия...");
        flight.book();
        hotel.book();
        car.book();
        System.out.println("Путешествие успешно забронировано!");
    }

    public void cancelTrip() {
        System.out.println("Отменяем бронирование путешествия...");
        car.cancel();
        hotel.cancel();
        flight.cancel();
        System.out.println("Все бронирования отменены");
    }
}

public class FacadeLauncher {
    public static void main(String[] args) {
        TravelFacade travel = new TravelFacade();

        // Планируем путешествие
        travel.planTrip();

        // Симуляция ситуации с неподтверждённым отпуском
        System.out.println("\n!! Ситуация: отпуск не подтверждён !!");
        travel.cancelTrip();
    }
}

