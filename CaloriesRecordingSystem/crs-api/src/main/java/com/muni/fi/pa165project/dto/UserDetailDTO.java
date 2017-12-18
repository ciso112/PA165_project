package com.muni.fi.pa165project.dto;

import java.util.Objects;

/**
 * @author Lukáš Císar
 */
public class UserDetailDTO {

    private Long id;

    private String name;

    private String username;

    private String email;
    
    private long numberOfRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 7 * hash + Objects.hashCode(this.name);
        hash = 7 * hash + Objects.hashCode(this.email);
        hash = 7 * hash * Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserDetailDTO)) {
            return false;
        }
        final UserDetailDTO other = (UserDetailDTO) obj;
        if (!this.username.equals(other.username)) {
            return false;
        }
        if (!this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

	public long getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
}
