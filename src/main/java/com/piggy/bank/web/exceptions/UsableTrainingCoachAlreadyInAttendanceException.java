package com.piggy.bank.web.exceptions;

import com.piggy.bank.web.entities.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsableTrainingCoachAlreadyInAttendanceException extends Exception {
    private List<Person> listOfPeopleExistingAlreadyInAttendanceAndUsableTrainingCoach;

    public UsableTrainingCoachAlreadyInAttendanceException(List<Person> people) {
        this.listOfPeopleExistingAlreadyInAttendanceAndUsableTrainingCoach = people;
    }
}
