import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

//TODO Rename class
public class UserDAO implements IUserDAO {
    //TODO Make a connection to the database
    private Connection createConnection() throws IUserDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/chbu?"
                    + "user=chbu&password=4thVbCaMOxnXi3aJ4");
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    public UserDTO getUser(int userId) throws DALException {
        //TODO Implement this
        Connection c = createConnection();


        //TODO: Make a user from the resultset
        UserDTO user = new UserDTO();

        try {
            c.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
        return user;
    }

    public List<UserDTO> getUserList() throws DALException {
        //TODO Implement this
        return null;
    }

    public void createUser(UserDTO user) throws DALException {
        //TODO Implement this
    }

    public void updateUser(UserDTO user) throws DALException {
        //TODO Implement this
    }

    public void deleteUser(int userId) throws DALException {
        //TODO Implement this
    }
}