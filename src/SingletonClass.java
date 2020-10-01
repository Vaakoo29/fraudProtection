public class SingletonClass {

    private static SingletonClass singletonClass;

    private static int count = 0;

    SingletonClass() {
        count++;
    };

    public static SingletonClass getInstance() {
        if (singletonClass == null) {
            singletonClass = new SingletonClass();
        }


        return singletonClass;
    }

    public int getCount() {
        return count;
    }
}
