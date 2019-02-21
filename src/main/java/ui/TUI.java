package ui;

import dal.IUserDAO;
import dal.UserDAOimpl;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class TUI {
    public void run() throws IUserDAO.DALException {
        IUserDAO userDAO = new UserDAOimpl();

        Scanner sc = new Scanner(System.in);
        String choice;


        do {
            System.out.println("Muligheder:\n"
                    + "1. Vis bruger\n"
                    + "2. Vis alle brugere\n"
                    + "3. Opret bruger\n"
                    + "4. Opdater bruger\n"
                    + "5. Slet bruger\n"
                    + "6. Luk program\n");

            choice = sc.next();

            switch (choice){
                case "1": // Vis bruger
                    System.out.println("Write userID:");
                    System.out.println(userDAO.getUser(sc.nextInt()));
                    System.out.println("Done...");
                    break;

                case "2": // Vis alle brugere
                    List<UserDTO> userlist = userDAO.getUserList();
                    for (int n=0 ; n < userlist.size() ; n++)
                        System.out.println(userlist.get(n));
                    System.out.println("Done...");
                    break;

                case "3": // Opret bruger
                    userDAO.createUser(createUserDTO(sc));
                    System.out.println("Done...");
                    break;

                case "4": // Opdater bruger
                    userDAO.updateUser(createUserDTO(sc));
                    System.out.println("Done...");
                    break;

                case "5": // Slet bruger
                    System.out.println("Write userID:");
                    userDAO.deleteUser(sc.nextInt());
                    System.out.println("Done...");
                    break;

                case "6":
                    break;
            }

            sc.nextLine();

        } while (!choice.equals("6"));
        System.out.println("Program closed");
        System.exit(0);
    }

    public static UserDTO createUserDTO(Scanner sc){
        UserDTO newUser = new UserDTO();
        System.out.println("ID:");
        newUser.setUserId(sc.nextInt());

        System.out.println("Name:");
        newUser.setUserName(sc.next());

        System.out.println("Initials:");
        newUser.setIni(sc.next());

        System.out.println("CPR:");
        newUser.setCpr(sc.next());

        System.out.println("Password:");
        newUser.setPassword(sc.next());

        System.out.println("Roles (write STOP to stop):");
        String role;
        do {
            role = sc.next();
            if (!role.equalsIgnoreCase("stop"))
                newUser.addRole(role);
        } while (!role.equalsIgnoreCase("stop"));

        return newUser;
    }
}
