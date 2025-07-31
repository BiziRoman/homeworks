package Architecture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// Класс резюме
class Resume implements Cloneable {
    private String name;
    private String position;
    private List<String> skills;

    // Имитация дорогой операции
    public Resume(String name, String position, List<String> skills) throws InterruptedException {
        this.name = name;
        this.position = position;
        this.skills = new ArrayList<>(skills);

        System.out.println("Инициализация шаблона...");
        TimeUnit.MILLISECONDS.sleep(1000); // Имитация долгой операции
    }

    @Override
    protected Resume clone() {
        try {
            Resume cloned = (Resume) super.clone();
            // Глубокое копирование списка навыков
            cloned.skills = new ArrayList<>(this.skills);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Резюме:\n" +
                "Имя: " + name + "\n" +
                "Позиция: " + position + "\n" +
                "Навыки: " + String.join(", ", skills) + "\n";
    }
}

public class ResumeGenerator {
    public static void main(String[] args) throws InterruptedException {
        // Создаем эталонное резюме
        List<String> skills = List.of(
                "Java",
                "Spring",
                "SQL",
                "Git",
                "REST"
        );

        System.out.println("Создание эталонного шаблона...");
        Resume seniorTemplate = new Resume(
                "Иван Петров",
                "Senior Java Developer",
                skills
        );

        // Клонируем шаблон три раза
        System.out.println("\nКлонирование шаблона...");

        Resume juniorResume = seniorTemplate.clone();
        juniorResume.setName("Петр Иванов");
        juniorResume.setPosition("Junior Java Developer");

        Resume middleResume = seniorTemplate.clone();
        middleResume.setName("Анна Сидорова");
        middleResume.setPosition("Middle Java Developer");

        Resume seniorResume = seniorTemplate.clone();
        seniorResume.setName("Мария Иванова");
        seniorResume.setPosition("Senior Java Developer");

        // Выводим результаты
        System.out.println("\nРезультаты клонирования:");
        System.out.println(juniorResume);
        System.out.println(middleResume);
        System.out.println(seniorResume);
    }
}
