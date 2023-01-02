package ua.nure.andriimatiashov.kpp.example.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "database")
public class DAOConfig {
    private String url;
    private String user;
    private String password;
    private String typeDAO;

    public DAOConfig(String url, String user, String password, String typeDAO) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.typeDAO = typeDAO;
    }

    public DAOConfig() {
    }

    public String getTypeDAO() {
        return typeDAO;
    }

    public void setTypeDAO(String typeDAO) {
        this.typeDAO = typeDAO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DAOConfig{" +
                "url='" + url + '\'' +
                ", login='" + user + '\'' +
                ", password='" + password + '\'' +
                ", typeDAO=" + typeDAO +
                '}';
    }
}
