package cz.muni.fi.pa165.creaturehunting.api.dto;

/**
 * Data Transfer Object for LogInDTO
 * 
 * @author Fita
 */
public class LogInDTO {
    private long id= -1;
    private String name;
    private String password;
    private boolean isRoot;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 29 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( !(o instanceof LogInDTO) ) return false;

        final LogInDTO logInDTO = (LogInDTO) o;

        if (!new Long(id).equals(logInDTO.id)) return false;
        if (!name.equals(logInDTO.getName())) return false;
        if (!password.equals(logInDTO.getPassword())) return false;

        return true;
    }
    
    

    @Override
    public String toString() {
        return "LogInDTO{" + "id=" + id + ", name=" + name + ", password=" + password + ", isRoot=" + isRoot + '}';
    }     
}
