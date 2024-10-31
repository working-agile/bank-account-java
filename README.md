# README #

Bank Account exercise

### What is this repository for? ###

Coding exercises for the Introduction to TDD and BDD.
Part of the A-CSD training course.

### What is this branch for? ###

This branch showcases the use of a manually developed mock email sender component:

> `FakeEmailSender`

In order to prevent a direct dependency on the mock implementation, the DIP (Dependency Inversion Principle)
is applied by introducing the interface:

> `EmailSender`

* *Dependency Inversion Principle* (__DIP__): says: high-level modules should not depend on low-level modules (both should depend on abstractions)

* *Dependency Injection* (__DI__): BankAccount's dependencies are injected (alternative: __IoC__: Inversion of Control)

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
* 10-bank-account-with-transaction-history-integration-test

* 11-addition-with-bdd-setup
* 12-addition-with-bdd-solutions
* 13-bank-account-with-bdd-setup
* 14-bank-account-with-bdd-solutions


### Who do I talk to? ###

* Axel Wilhelm Berle
* axelberle@gmail.com


