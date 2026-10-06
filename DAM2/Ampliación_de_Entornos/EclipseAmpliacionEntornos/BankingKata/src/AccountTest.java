
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class AccountTest {

    private LocalDate currentDate;

    private Account newAccount() {
        return new Account(() -> currentDate);
    }

    @Test
    void test1() {
        Account account = newAccount();

        assertEquals("Date        Amount  Balance", account.printStatement());
    }

    @Test
    void test2() {
        currentDate = LocalDate.of(2015, 12, 24);
        Account account = newAccount();

        account.deposit(500);

        assertEquals(
            "Date        Amount  Balance\n" +
            "24.12.2015   +500      500",
            account.printStatement());
    }
    
    @Test
    void test3() {
        currentDate = LocalDate.of(2020, 1, 1);
        Account account = newAccount();

        account.deposit(1000);
        account.withdraw(300);
        account.deposit(50);

        assertEquals(
            "Date        Amount  Balance\n" +
            "1.1.2020    +1000     1000\n" +
            "1.1.2020     -300      700\n" +
            "1.1.2020      +50      750",
            account.printStatement());
    }

    @Test
    void test4() {
        currentDate = LocalDate.of(2015, 12, 24);
        Account account = newAccount();
        account.deposit(500);

        currentDate = LocalDate.of(2016, 8, 23);
        account.withdraw(100);

        assertEquals(
            "Date        Amount  Balance\n" +
            "24.12.2015   +500      500\n" +
            "23.8.2016    -100      400",
            account.printStatement());
    }
}