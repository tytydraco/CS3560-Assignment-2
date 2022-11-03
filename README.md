# CS3560-Assignment-2
A Java project that explores object-oriented patterns.

# Patterns

## Overview
* [Visitor](src/visitors): Objects can visit other objects and perform operations.
* [Observer](src/util): Objects can "watch" other objects for changes, and react when they change.
* [Singleton](src/data/local/DataManager.java): An object that can only have one instance of itself ever be created.
* [Composite](src/data/models/identity): An object has a tree of subobjects of the same type.

## Usage
* Visitor: I chose to make the Tweets visitable, and created a goodness visitor and a length-validator visitor.
* Observer: I allowed user feeds to be observed, so that when users posted a new tweet, any open windows would know to fetch the new posts.
* Singleton: I heard that it was bad practice to use Singletons because they are nearly the same as global variables, but I used it to hold the root group since there would only ever be one allowed to exist during the program's lifespan. I also included methods for the root group here as well.
* Composite: The groups and users are compositable, meaning that groups can contain a tree of subgroups and users. The data manager is able to traverse the composite structure. The Identifiable class is the component class, and the composite class is the Group and User.
