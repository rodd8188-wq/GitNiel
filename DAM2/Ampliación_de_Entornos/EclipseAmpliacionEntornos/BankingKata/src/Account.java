
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Account {

    private static final String HEADER = "Date        Amount  Balance";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.M.yyyy");

    private final Supplier<LocalDate> today;
    private final List<String> lines = new ArrayList<>();
    private int balance = 0;

    public Account() {
        this(LocalDate::now);
        
    }

    public Account(Supplier<LocalDate> today) {
        this.today = today;
        
    }

    public void deposit(int amount) {
        record(amount);
        
    }

    public void withdraw(int amount) {
        record(-amount);
        
    }

    public String printStatement() {
        StringBuilder sb = new StringBuilder(HEADER);
        
        for (String line : lines) {
            sb.append("\n").append(line);
            
        }
        return sb.toString();
        
    }

    private void record(int signedAmount) {
        balance += signedAmount;
        String amount = (signedAmount > 0 ? "+" : "") + signedAmount;
        lines.add(String.format("%-10s%7s%9d",
                today.get().format(DATE_FORMAT), amount, balance));
        
    }
}