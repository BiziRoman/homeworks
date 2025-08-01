package Architecture;

// Абстракция цвета
interface Color {
    void apply(String shape);
}

// Конкретные реализации цветов
class Red implements Color {
    @Override
    public void apply(String shape) {
        System.out.println("Рисуем " + shape + " красным цветом");
    }
}

class Blue implements Color {
    @Override
    public void apply(String shape) {
        System.out.println("Рисуем " + shape + " синим цветом");
    }
}

class Green implements Color {
    @Override
    public void apply(String shape) {
        System.out.println("Рисуем " + shape + " зелёным цветом");
    }
}

// Абстракция фигуры
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

// Конкретные реализации фигур
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        color.apply("круг");
    }
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        color.apply("квадрат");
    }
}

public class BridgeLauncher {
    public static void main(String[] args) {
        // Создаём красный круг
        Shape redCircle = new Circle(new Red());
        System.out.println("Создаём красный круг:");
        redCircle.draw();
        System.out.println();

        // Создаём синий квадрат
        Shape blueSquare = new Square(new Blue());
        System.out.println("Создаём синий квадрат:");
        blueSquare.draw();
        System.out.println();

        // Создаём зелёный круг
        Shape greenCircle = new Circle(new Green());
        System.out.println("Создаём зелёный круг:");
        greenCircle.draw();
    }
}

