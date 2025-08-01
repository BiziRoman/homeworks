package Architecture;

import java.util.ArrayList;
import java.util.List;

// Интерфейс компонента меню
interface MenuComponent {
    void print(String indent);
    int countItems();
}

// Лист — пункт меню
class MenuItem implements MenuComponent {
    private final String name;
    private final double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "- " + name + " ($" + price + ")");
    }

    @Override
    public int countItems() {
        return 1;
    }
}

// Композит — подменю
class Menu implements MenuComponent {
    private final String name;
    private final List<MenuComponent> components = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public void add(MenuComponent component) {
        components.add(component);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + name + ":");
        for (MenuComponent component : components) {
            component.print(indent + "  ");
        }
    }

    @Override
    public int countItems() {
        int count = 0;
        for (MenuComponent component : components) {
            count += component.countItems();
        }
        return count;
    }
}

public class CompositeLauncher {
    public static void main(String[] args) {
        // Создаем корневое меню
        Menu mainMenu = new Menu("Main Menu");

        // Создаем подменю File
        Menu fileMenu = new Menu("File");
        fileMenu.add(new MenuItem("New", 0));
        fileMenu.add(new MenuItem("Open", 0));
        fileMenu.add(new MenuItem("Exit", 0));

        // Создаем подменю Edit
        Menu editMenu = new Menu("Edit");
        editMenu.add(new MenuItem("Copy", 0));
        editMenu.add(new MenuItem("Paste", 0));

        // Добавляем подменю в главное меню
        mainMenu.add(fileMenu);
        mainMenu.add(editMenu);

        // Выводим меню
        System.out.println("Меню приложения:");
        mainMenu.print("");

        // Считаем количество пунктов
        int totalItems = mainMenu.countItems();
        System.out.println("\nОбщее количество пунктов меню: " + totalItems);
    }
}

