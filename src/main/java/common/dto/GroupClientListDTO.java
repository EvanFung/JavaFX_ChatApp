package common.dto;

import java.util.Set;

public class GroupClientListDTO {
    private Set<String> listUser;
    private Set<String> listGroup;

    public Set<String> getListUser() {
        return listUser;
    }

    public void setListUser(Set<String> listUser) {
        this.listUser = listUser;
    }

    public Set<String> getListGroup() {
        return listGroup;
    }

    public void setListGroup(Set<String> listGroup) {
        this.listGroup = listGroup;
    }
}
