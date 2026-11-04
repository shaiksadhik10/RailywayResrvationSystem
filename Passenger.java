
package com.railway.model;

public class Passenger {

    private int passengerId;
    private String passengerName;
    private int age;
    private String gender;

    // Default constructor
    public Passenger() {
    }

    // Parameterized constructor
    public Passenger(int passengerId, String passengerName,
                     int age, String gender) {
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.age = age;
        this.gender = gender;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + passengerId
                + ", Name: " + passengerName
                + ", Age: " + age
                + ", Gender: " + gender;
    }
}
