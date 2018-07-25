package com.demoyageek.alzhapp.Encapsulation;

public class Circle {
    private String circleMemberId;

    public Circle(){
    }

    public Circle(String circleMemberId) {
        this.circleMemberId = circleMemberId;
    }

    public String getCircleMemberId() {
        return circleMemberId;
    }

    public void setCircleMemberId(String circleMemberId) {
        this.circleMemberId = circleMemberId;
    }
}
