package ui;

import dal.IUserDAO;
import dal.UserDAOimpl;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class TUI {
    private IUserDAO userDAO;
    private Scanner sc;
    private Scanner stop;

    public TUI(){
        sc = new Scanner(System.in);
        stop = new Scanner(System.in);
        userDAO = new UserDAOimpl();
    }

    public void run() throws IUserDAO.DALException {
        IUserDAO userDAO = new UserDAOimpl();
        int choice;

        do {
            System.out.println("Menu:\n"
                    + "1. Show user\n"
                    + "2. Show all users\n"
                    + "3. Create user\n"
                    + "4. Edit user\n"
                    + "5. Delete user\n"
                    + "6. Close program\n");

            choice = sc.nextInt();

            switch (choice){
                case 1: // Vis bruger
                    System.out.println("Write userID:");
                    try {
                        System.out.println(userDAO.getUser(sc.nextInt()));
                    } catch(IUserDAO.DALException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: // Vis alle brugere
                    List<UserDTO> userlist = userDAO.getUserList();
                    for (int n=0 ; n < userlist.size() ; n++)
                        System.out.println(userlist.get(n));
                    sc.nextLine();
                    break;

                case 3: // Opret bruger
                    userDAO.createUser(createUserDTO());
                    break;

                case 4: // Opdater bruger
                    try {
                        userDAO.updateUser(updateUser());
                    } catch (IUserDAO.DALException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5: // Slet bruger
                    System.out.println("Write userID:");
                    userDAO.deleteUser(sc.nextInt());
                    break;

                case 6:
                    break;
            }

            System.out.println("Press ENTER to continue");
            stop.nextLine();

        } while (choice != 6);
        System.out.println("...Program closed");
        System.exit(0);
    }

    private UserDTO createUserDTO(){
        UserDTO newUser = new UserDTO();

        System.out.println("Name:");
        newUser.setUserName(sc.next());

        System.out.println("Initials:");
        newUser.setIni(sc.next());

        System.out.println("CPR (form: xxxxxx-xxxx):");
        String cpr;
        do {
            cpr = sc.next();
            newUser.setCpr(cpr);
        } while(!cpr.matches("\\d{6}-\\d{4}"));

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

    private UserDTO updateUser() throws IUserDAO.DALException {
        UserDTO chosenUser;
        int choice;

        System.out.println("Write user ID for user you want to edit");
        chosenUser = userDAO.getUser(sc.nextInt());

        System.out.println("Chosen user: \n" + chosenUser);

        do {
            System.out.println("What do you want to edit?\n"
                    + "1. Name\n"
                    + "2. Initials\n"
                    + "3. CPR\n"
                    + "4. Password\n"
                    + "5. Roles\n"
                    + "6. Exit\n");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("New name:\n");
                    chosenUser.setUserName(sc.next());
                    break;

                case 2:
                    System.out.println("New initials:\n");
                    chosenUser.setIni(sc.next());
                    break;

                case 3:
                    System.out.println("New CPR (form: xxxxxx-xxxx):\n");
                    String cpr;
                    do {
                        cpr = sc.next();
                        chosenUser.setCpr(cpr);
                    } while(!cpr.matches("\\d{6}-\\d{4}"));
                    break;

                case 4:
                    System.out.println("New password:\n");
                    chosenUser.setPassword(sc.next());
                    break;

                case 5:
                    List<String> roles = chosenUser.getRoles();
                    int roleChoice;
                    System.out.println("Add (1) or remove (2) roles? ");
                    System.out.println("Current roles: " + roles);
                    roleChoice = sc.nextInt();
                    switch (roleChoice){
                        case 1:
                            System.out.println("Write STOP to stop adding roles");
                            String addedRole;
                            do {
                                for (String allRoles : roles)
                                    System.out.print(allRoles + ", ");
                                System.out.println();
                                addedRole = sc.next();
                                if (!addedRole.equalsIgnoreCase("stop"))
                                    chosenUser.addRole(addedRole);
                            } while (!addedRole.equalsIgnoreCase("stop"));
                            break;

                        case 2:
                            String removeRole;
                            System.out.println("Which role should be removed (write STOP to stop)?");
                            do {
                                for (String allRoles : roles)
                                    System.out.print(allRoles + ", ");
                                System.out.println();
                                removeRole = sc.next();
                                chosenUser.removeRole(removeRole);
                            } while (!removeRole.equalsIgnoreCase("stop"));

                            break;
                    }

                    break;

                case 6:
                    break;
            }
        } while(choice != 6);


        return chosenUser;
    }

}
