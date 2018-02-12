package com.practice.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String userId;
    private String displayName;
    private String password;
    private String emailId;

    public String toString()
    {
        return String.format("UserId: %s, Name: %s, EmailId: %s", userId, displayName, emailId);
    }

    @Override
    public boolean equals(Object object)
    {
        if(this == object)
        {
            return true;
        }
        if(object == null || this.getClass() != object.getClass())
        {
            return false;
        }
        User userDAO = (User) object;
        return compareFields(this.getUserId(), userDAO.getUserId())
                && compareFields(this.getDisplayName(), userDAO.getDisplayName())
                && compareFields(this.getPassword(), userDAO.getPassword())
                && compareFields(this.getEmailId(), userDAO.getEmailId());
    }

    private boolean compareFields(Object field1, Object field2)
    {
        return (field1 == field2) || ((field1 != null) && field1.equals(field2));
    }

}
