package primary;

/**
 * @author lppppp
 * @create 2020-12-25 13:43
 */
public class User {
        private String username;
        private String password;

        public User() {
        }

        public User(String user, String password) {
            super();
            this.username = user;
            this.password = password;
        }

        @Override
        public String toString() {
            return "primary.User [user=" + username + ", password=" + password + "]";
        }

        public String getUser() {
            return username;
        }

        public void setUser(String user) {
            this.username = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
