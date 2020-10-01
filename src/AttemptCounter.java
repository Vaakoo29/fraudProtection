// Реализация паттерна Singleton
// для хранения информации только в единственной "точке"

// Класс для считывания количества неудачных попыток зайти в почтовый сервер
// В случае превышения кол-ва попыток возвращать ошибку
public class AttemptCounter {

    SingletonClass singleton = new SingletonClass();

    public int getCounter() {
        return singleton.getCount();
    }

    public String toString() {
        return Integer.toString(singleton.getCount());
    }

}
