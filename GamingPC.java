package Architecture;

// Основной класс продукта
public class GamingPC {
    private final String cpu;
    private final String gpu;
    private final int ram;
    private final int ssd;
    private final boolean liquidCooling;
    private final boolean rgbLighting;

    private GamingPC(Builder builder) {
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
        this.ssd = builder.ssd;
        this.liquidCooling = builder.liquidCooling;
        this.rgbLighting = builder.rgbLighting;
    }

    @Override
    public String toString() {
        return "GamingPC{" +
                "CPU=" + cpu +
                ", GPU=" + gpu +
                ", RAM=" + ram + " GB" +
                ", SSD=" + ssd + " GB" +
                ", Жидкое охлаждение=" + (liquidCooling ? "Да" : "Нет") +
                ", RGB-подсветка=" + (rgbLighting ? "Да" : "Нет") +
                '}';
    }

    // Вложенный Builder
    public static class Builder {
        private final String cpu;
        private final String gpu;
        private int ram = 16;    // значение по умолчанию
        private int ssd = 500;   // значение по умолчанию
        private boolean liquidCooling = false;
        private boolean rgbLighting = false;

        // Обязательные параметры в конструкторе
        public Builder(String cpu, String gpu) {
            this.cpu = cpu;
            this.gpu = gpu;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setSsd(int ssd) {
            this.ssd = ssd;
            return this;
        }

        public Builder setLiquidCooling(boolean liquidCooling) {
            this.liquidCooling = liquidCooling;
            return this;
        }

        public Builder setRgbLighting(boolean rgbLighting) {
            this.rgbLighting = rgbLighting;
            return this;
        }

        public GamingPC build() {
            return new GamingPC(this);
        }
    }

    public static void main(String[] args) {
        // Сборка бюджетной конфигурации
        GamingPC budgetPC = new GamingPC.Builder("Intel i5", "RTX 4060")
                .setRam(16)
                .build();

        // Сборка топовой конфигурации
        GamingPC topPC = new GamingPC.Builder("Intel i9", "RTX 5090")
                .setRam(64)
                .setSsd(2000)
                .setLiquidCooling(true)
                .setRgbLighting(true)
                .build();

        // Вывод результатов
        System.out.println("Бюджетная конфигурация:");
        System.out.println(budgetPC);
        System.out.println("\nТоповая конфигурация:");
        System.out.println(topPC);
    }
}

