package Architecture;

// Абстрактные сервисы
interface ComputeInstance {
    void start();
}

interface BlobStorage {
    void save(String name);
}

// Абстрактная фабрика
interface CloudFactory {
    ComputeInstance createCompute();
    BlobStorage createStorage();
}

// Конкретные реализации для AWS
class AwsCompute implements ComputeInstance {
    @Override
    public void start() {
        System.out.println("Запущен AWS EC2 инстанс");
    }
}

class AwsBlobStorage implements BlobStorage {
    @Override
    public void save(String name) {
        System.out.println("Файл сохранен в AWS S3: " + name);
    }
}

class AwsFactory implements CloudFactory {
    @Override
    public ComputeInstance createCompute() {
        return new AwsCompute();
    }

    @Override
    public BlobStorage createStorage() {
        return new AwsBlobStorage();
    }
}

// Конкретные реализации для Azure
class AzureCompute implements ComputeInstance {
    @Override
    public void start() {
        System.out.println("Запущен Azure VM инстанс");
    }
}

class AzureBlobStorage implements BlobStorage {
    @Override
    public void save(String name) {
        System.out.println("Файл сохранен в Azure Storage: " + name);
    }
}

class AzureFactory implements CloudFactory {
    @Override
    public ComputeInstance createCompute() {
        return new AzureCompute();
    }

    @Override
    public BlobStorage createStorage() {
        return new AzureBlobStorage();
    }
}

// Класс-клиент
class DeploymentManager {
    private ComputeInstance compute;
    private BlobStorage storage;

    public DeploymentManager(CloudFactory factory) {
        this.compute = factory.createCompute();
        this.storage = factory.createStorage();
    }

    public void deploy() {
        compute.start();
        storage.save("deployment.log");
    }
}

// Точка входа
public class CloudApp {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Укажите провайдера: aws или azure");
            return;
        }

        CloudFactory factory;
        String provider = args[0].toLowerCase();

        switch (provider) {
            case "aws":
                factory = new AwsFactory();
                break;
            case "azure":
                factory = new AzureFactory();
                break;
            default:
                System.out.println("Неизвестный провайдер");
                return;
        }

        DeploymentManager manager = new DeploymentManager(factory);
        manager.deploy();
    }
}
