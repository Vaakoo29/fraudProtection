import java.io.*;

public class MainClass {

    public static void main(String[] args) throws IOException, DuplicateAccountException {
//        BufferedReader br = new BufferedReader(new FileReader("dataBase.csv"));
//        String line = "";
//
//        while ((line = br.readLine()) != null ) {
//            String[] values = line.split(",");
//            System.out.println("Date of birth: " + values[1]);
//        }

        AccountManagerImpl accountManager = new AccountManagerImpl();
        Person person = new Person("Титова Марина Сергеевна", "19/08/1999");
//        try {
//            accountManager.registerNewAccount("wow@gmail.com", "1293", person);
//        } catch (DuplicateAccountException e) {
//            System.out.println("Данный аккаунт уже существует.");
//        }

        accountManager.removeAccount("vaakoo58@gmail.com","8494");
//        System.out.println(accountManager.hasAccount("vaakoo5@gmail.com"));

//        System.out.println(accountManager.numOfAccounts());

        try {
//            accountManager.getPerson("bagetov@pekarnya.ru","8390");
//            accountManager.getPerson("bagetov@pekarnya.ru","8393");
//            accountManager.getPerson("bagetov@pekarnya.ru","3930");
//            accountManager.getPerson("bagetov1@pekarnya.ru","830");
//            accountManager.getPerson("bagetov@pekarnya.ru","3930");
//            accountManager.getPerson("bagetov@pekarnya.ru","3930");
            System.out.println(accountManager.getPerson("bagetov@pekarnya.ru","83930"));

        } catch (TooManyLoginAttemptsException e) {
            System.out.println("Превышено допустимое количество неудачных попыток.");
        }
    }
}
