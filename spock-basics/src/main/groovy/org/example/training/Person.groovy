package org.example.training

import groovy.transform.Canonical

@Canonical
class Person {
    String firstName
    String lastName

    String getFullName() { return "$firstName $lastName" }
}
