# README #

Bank Account exercise

### What is this repository for? ###

Coding exercises for the Introduction to TDD and BDD.
Part of the A-CSD training course.

### What is this branch for? ###

This branch showcases the use of a manually developed mock email sender component:

* FakeEmailSender

In order to prevent a direct dependency on the mock implementation, the DIP (Dependency Inversion Principle)
is applied by introducing the interface:

* EmailSender

DIP says: high-level modules should not depend on low-level modules (both should depend on abstractions)
DI: Dependency Injection: BankAccount's dependencies are injected.

### Branches ###

* main: empty
* 1-bank-account-java: basic project, no implementation
* 2-bank-account-with-unit-tests: basic functionalities implemented, with unit tests
* 3-bank-account-with-tdd: additional functionalities developed with TDD
* 4-bank-account-manual-mock: showcases how to implement a mock for dependencies
* 5-bank-account-manual-mock-Dependency-Injection: showcases dependency injection
* 6-bank-account-with-mockito: showcases use of Mockito
* 7-bank-account-with-transaction-history: showcase creation of new dependency
* 8-bank-account-with-transaction-history-stub: showcase use of a stub
* 9-bank-account-with-transaction-history-implementation: showcase TDD for InMemoryTransactionHistory




### Who do I talk to? ###

* Axel Wilhelm Berle
* axelberle@gmail.com


