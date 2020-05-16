package com.hospital.pojo;

import javax.persistence.*;

@Table(name = "hospital_user")
public class HospitalUser {
    private Integer id;

    private String name;

    private Integer age;

    private String ceshi;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return ceshi
     */
    public String getCeshi() {
        return ceshi;
    }

    /**
     * @param ceshi
     */
    public void setCeshi(String ceshi) {
        this.ceshi = ceshi;
    }
}