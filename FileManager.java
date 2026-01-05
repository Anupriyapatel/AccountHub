import java.io.*;
import java.util.*;

public class FileManager {
    private static final String FILE_NAME = "accounts.dat";

    @SuppressWarnings("unchecked")
    public static List<BankAccount> loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<BankAccount>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing account file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void saveAccounts(List<BankAccount> accounts) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }
}