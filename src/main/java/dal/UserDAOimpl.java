package dal;

import dto.UserDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;

public class UserDAOimpl implements IUserDAO {

    public UserDTO getUser(int userId) throws DALException{
        UserDTO newUser = new UserDTO();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185118?"
                + "user=s185118&password=SNX64wUCCqEHKNVwEwumg")){
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE userID = " + userId);
            newUser = createUserDTO(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.println(newUser);
        return newUser;
    }

    public List<UserDTO> getUserList() throws DALException{
        List<UserDTO> userList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185118?"
                + "user=s185118&password=SNX64wUCCqEHKNVwEwumg")){
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ORDER BY userID");

            while(!resultSet.isLast()){
                userList.add(createUserDTO(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //for (int n=0 ; n < userList.size() ; n++)
        //    System.out.println(userList.get(n));
        return userList;
    }

    public void createUser(UserDTO user) throws DALException{
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185118?"
                + "user=s185118&password=SNX64wUCCqEHKNVwEwumg")){
            Statement statement = connection.createStatement();

            String query = String.format("INSERT INTO users VALUES (%d, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                    getNextUserID(statement), user.getUserName(), user.getIni(), user.getCpr(), user.getPassword(), formatRoles(user.getRoles().toString()));

            statement.executeUpdate(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(UserDTO user) throws DALException{
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185118?"
                + "user=s185118&password=SNX64wUCCqEHKNVwEwumg")){
            Statement statement = connection.createStatement();

            String query = String.format("UPDATE users SET userName = \"%s\", ini = \"%s\", cpr = \"%s\", pass = \"%s\", roles = \"%s\" WHERE userID = %d",
                    user.getUserName(), user.getIni(), user.getCpr(), user.getPassword(), formatRoles(user.getRoles().toString()), user.getUserId());

            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteUser(int userId) throws DALException{
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185118?"
                + "user=s185118&password=SNX64wUCCqEHKNVwEwumg")){
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM users WHERE userID = " + userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserDTO createUserDTO(ResultSet resultSet) throws SQLException {
        UserDTO newUser = new UserDTO();

        resultSet.next();

        int newUserID = resultSet.getInt("userID");
        newUser.setUserId(newUserID);

        String userName = resultSet.getString("userName");
        newUser.setUserName(userName);

        String ini = resultSet.getString("ini");
        newUser.setIni(ini);

        String cpr = resultSet.getString("cpr");
        newUser.setCpr(cpr);

        String pass = resultSet.getString("pass");
        newUser.setPassword(pass);

        String roles = resultSet.getString("roles");
        roles = formatRoles(roles);
        List<String> rolesArray = new ArrayList<>(Arrays.asList(roles.split(", ")));
        newUser.setRoles(rolesArray);

        return newUser;
    }

    // Fjerner [ og ] fra den givne String. Brugt på ArrayLister i String form.
    private String formatRoles(String roles){
        String newRoles;
        newRoles = roles.replaceAll("\\[", "");
        newRoles = newRoles.replaceAll("]","");

        return newRoles;
    }

    // Finder det næste unikke userID fra nextUserID tabellen i database
    private int getNextUserID(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT nextUserID FROM static_nextUserID");
        resultSet.next();
        int nextUserID = resultSet.getInt("nextUserID");

        statement.executeUpdate("UPDATE static_nextUserID SET nextUserID = nextUserID+1;");

        return nextUserID;
    }
}
