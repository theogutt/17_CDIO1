import dal.IUserDAO;
import ui.TUI;

public class Main {
    public static void main(String[] args) throws IUserDAO.DALException {
        TUI tui = new TUI();
        tui.run();
    }
}