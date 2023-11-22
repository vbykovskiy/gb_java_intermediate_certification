import java.util.*;

public class Laptop {
    // Поля для хранения параметров ноутбука
    private int ram;        // ОЗУ
    private int hdd;        // Жесткий диск
    private String os;      // Операционная система
    private String color;   // Цвет
    // Конструктор с инициализацией полей
    public Laptop(int ram, int hdd, String os, String color) {
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public static void main(String[] args) {
        // Создание HashSet для хранения ноутбуков
        Set<Laptop> laptops = new HashSet<>();
        // Добавление ноутбуков в HashSet
        laptops.add(new Laptop(8, 500, "Windows", "Black"));
        laptops.add(new Laptop(16, 1000, "MacOS", "Silver"));
        laptops.add(new Laptop(4, 250, "Linux", "Red"));
        laptops.add(new Laptop(8, 1000, "Windows", "White"));
        laptops.add(new Laptop(16, 500, "MacOS", "Black"));
        laptops.add(new Laptop(4, 500, "Windows", "Silver"));
        laptops.add(new Laptop(6, 2000, "Windows", "Red"));
        laptops.add(new Laptop(4, 500, "Windows", "Brown"));
        laptops.add(new Laptop(6, 1000, "MacOS", "Gold"));
        laptops.add(new Laptop(32, 8000, "No OS", "Silver"));
        laptops.add(new Laptop(8, 1000, "Linux", "Brown"));
        laptops.add(new Laptop(4, 500, "Windows", "Silver"));
        laptops.add(new Laptop(32, 4000, "NO OS", "Gold"));
        // Создание Map для хранения выбранных критериев фильтрации
        Map<String, Object> filter = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        int choice; // Переменная для выбора пункта меню
        boolean showFilter = false; // Переменная для отображения отфильтрованных ноутбуков
        
        do {
            ConClear();
            //System.out.println(""); // Пустая строка для разделения меню
            
            // Вывод отфильтрованных ноутбуков
            if (showFilter == true) {
                for (Laptop laptop : laptops) {
                    if (checkParam(laptop, filter)) {
                        System.out.println("RAM: "      + laptop.ram + "Gb, " + '\t'
                                         + "HDD: "      + laptop.hdd + "Gb, " + '\t' 
                                         + "OS: "       + laptop.os  + ", "   + '\t'
                                         + "Color: "    + laptop.color);
                    }
                }
            } else {
                System.out.println("Для отображения отфильтрованных ноутбуков нажмите 5");
            }

            // Вывод текущих значений фильтра
            System.out.println('\n' + "Текущие значения фильтра: " + '\n');
            
            if (filter.containsKey("ram")) {
                System.out.println("Объем оперативной памяти от: " + filter.get("ram") + " Gb");
            }
            if (filter.containsKey("hdd")) {
                System.out.println("Объем жесткого диска от: " + filter.get("hdd") + " Gb");
            }
            if (filter.containsKey("os")) {
                System.out.println("Выбранная операционная система: " + filter.get("os"));
            }
            if (filter.containsKey("color")) {
                System.out.println("Выбранный цвет: " + filter.get("color"));
            }

            // Вывод меню
            System.out.println('\n' + "Введите номер пункта меню: " + '\n');
            System.out.println("1 - Объем оперативной памяти");
            System.out.println("2 - Объем жесткого диска");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет ноутбука");
            System.out.println("5 - Показать отфильтрованные ноутбуки");
            System.out.println("6 - Сбросить фильтр");
            System.out.println("7 - Выход из программы" + '\n');

            // Считывание выбранного пункта меню
            choice = scanner.nextInt();

            // Обработка выбранного пункта меню
            switch (choice) {
                case 1:
                    System.out.print('\n' + "Введите минимальное значение объема оперативной памяти: ");
                    int minRam = scanner.nextInt();
                    filter.put("ram", minRam);
                    break;
                case 2:
                    System.out.print('\n' + "Введите минимальный объем жесткого диска: ");
                    int minHdd = scanner.nextInt();
                    filter.put("hdd", minHdd);
                    break;
                case 3:
                    System.out.print('\n' + "Выберите операционную систему: ");
                    String os = scanner.next();
                    filter.put("os", os);
                    break;
                case 4:
                    System.out.print('\n' + "Выберите цвет: ");
                    String color = scanner.next();
                    filter.put("color", color);
                    break;
                case 5:
                    if (showFilter == true) {
                        showFilter = false;
                    } else {
                        showFilter = true;
                    }
                    break;
                case 6:
                    filter.clear();
                case 7:
                    System.out.println('\n' + "Выход");
                    break;
                default:
                    System.out.println('\n' + "Укажите пункт меню из предложенного списка");
                    break;
            }
        } while (choice != 7);
        scanner.close();
    }

    // Метод для сравнения ноутбуков по критериям
    private static boolean checkParam(Laptop laptop, Map<String, Object> filter) {
        if (filter.containsKey("ram")) { 
            int minRam = (int) filter.get("ram");
            if (laptop.ram < minRam) {
                return false;
            }
        }
        if (filter.containsKey("hdd")) {
            int minHdd = (int) filter.get("hdd");
            if (laptop.hdd < minHdd) {
                return false;
            }
        }
        if (filter.containsKey("os")) {
            String os = (String) filter.get("os");
            if (!laptop.os.equals(os)) {
                return false;
            }
        }
        if (filter.containsKey("color")) {
            String color = (String) filter.get("color");
            if (!laptop.color.equals(color)) {
                return false;
            }
        }
        return true;
    }

    // Метод для очистки консоли
    private static void ConClear() {
        System.out.print("\u001B[2J\u001B[0;0H");
    }
}