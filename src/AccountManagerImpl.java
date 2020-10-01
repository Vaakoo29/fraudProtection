import javax.swing.*;
import java.io.*;
import java.util.*;

// использует файловую систему как базу данных

public class AccountManagerImpl implements AccountManager {

    private String path = "dataBase.csv";

    @Override
    public void registerNewAccount(String email, String password, Person person) throws DuplicateAccountException {
        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equalsIgnoreCase(person.toString())) {
                    throw new DuplicateAccountException("Данный аккаунт уже существует.");
                }
            }

            FileWriter fw = new FileWriter(path,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);


            pw.println(person + "," + email + "," + password);
            pw.flush();
            pw.close();
            br.close();
            fw.close();
            bw.close();

            JOptionPane.showMessageDialog(null, "Сохранение сделано.");
        } catch (IOException e) {
            System.out.println("Такой базы данных не существует.");
        }
    }

    @Override
    public void removeAccount(String email, String password) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            String filepath = "dataBase1.csv";
            int counterForOldFile = 0;
            int counterForNewFile = 0;

            File oldFile = new File(path);
            File newFile = new File(filepath);

            FileWriter fw = new FileWriter(filepath,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                counterForOldFile += 1;
                if (!(values[1].equalsIgnoreCase(email)&&values[2].equalsIgnoreCase(password))) {
                    pw.println(line);
                }
            }
            pw.flush();
            pw.close();
            fw.close();
            bw.close();

            BufferedReader br1 = new BufferedReader(new FileReader(filepath));

            while ((line = br1.readLine()) != null) {
                counterForNewFile += 1;
            }

            br1.close();

            if (counterForNewFile == counterForOldFile) {
                oldFile.delete();
                File changed = new File(path);
                newFile.renameTo(changed);
                throw new WrongCredentialsException("Введены неправильные данные.");
            }



            oldFile.delete();
            File changed = new File(path);
            newFile.renameTo(changed);

            JOptionPane.showMessageDialog(null, "Пользователь удален.");

        } catch (FileNotFoundException e) {
            System.out.println("Такой базы данных не существует.");
        } catch (IOException e) {
            System.out.println("Ошибка при прочтении файла.");
        } catch (WrongCredentialsException e) {
            System.out.println("Введены неправильные данные.");
        }

    }

    @Override
    public boolean hasAccount(String email) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            ArrayList<String> emails = new ArrayList<String>();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                emails.add(values[1]);
            }

            br.close();

            return emails.contains(email);

        } catch (FileNotFoundException e) {
            System.out.println("Такой базы данных не существует.");
        } catch (IOException e) {
            System.out.println("Ошибка при прочтении файла.");
        }

        return false; // ругается
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException {


        Person foundPerson = new Person(null,null);

        AttemptCounter attemptCounter = new AttemptCounter();

        ArrayList<String> emails = new ArrayList<String>();


        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                emails.add(values[1]);

                if (values[1].equalsIgnoreCase(email)&&values[2].equalsIgnoreCase(password)) {
                    String[] str = values[0].split(" ");
                    foundPerson.setFullName(str[0] + " " + str[1] + " " + str[2]);
                    foundPerson.setDateOfBirth(str[3]);
                    return foundPerson;
                }
            }

            br.close();

            if (emails.contains(email)) {
                if (attemptCounter.getCounter() == 5) {
                    throw new TooManyLoginAttemptsException("Превышено допустимое количество неудачных попыток.");
                }
            } else {
                throw new WrongCredentialsException("Введены неправильные данные.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такой базы данных не существует.");
        } catch (IOException e) {
            System.out.println("Ошибка при прочтении файла.");
        } catch (WrongCredentialsException e) {
            System.out.println("Введены неправильные данные.");
        }

        return foundPerson;

    }

    @Override
    public int numOfAccounts() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            int num = 0;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                num+=1;
            }

            br.close();

            return (num-1);

        } catch (FileNotFoundException e) {
            System.out.println("Такой базы данных не существует.");
        } catch (IOException e) {
            System.out.println("Ошибка при прочтении файла.");
        }

        return 0;
    }
}
