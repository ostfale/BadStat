package de.ostfale.jbad.badstat.player.api;

import de.ostfale.jbad.badstat.player.internal.GenderType;

public record Player(
        String firstName,
        String lastName,
        String userId,
        Integer yearOfBirth,
        GenderType GenderType
) {

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userId='" + userId + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", GenderType=" + GenderType +
                '}';
    }


}
