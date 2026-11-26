
class BankAccount {

    volatile boolean isActive = true;

    public void checkAccount() {	

        while (isActive) {
            System.out.println("Account is active");
        }

        System.out.println("Account is closed");
    }

    public void closeAccount() {
        isActive = false;
        System.out.println("Account closed by admin");
    }
}

public class VolatileExample {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Thread t1 = new Thread(() -> {
            account.checkAccount();
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.closeAccount();
        });

        t1.start();
        t2.start();
    }
}
