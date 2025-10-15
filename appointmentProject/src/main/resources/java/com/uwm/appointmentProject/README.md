Combinations Between Tables:
 - manufacturers holds both medications and manufacturer files
 - staff holds both provider and nurse files
 - common holds abstract classes, interfaces, helper methods, and the user-type related files (the stuff that limits who can see/use what).

 - In each of these labeled directories and the sub-folders of the common directory, there will be four additional directories:

   1. controller
      - Is the bridge between front-end and back-end
      - receives http requests (example: get, post, put, delete, etc.)
      - calls service layer to process business logic
      - returns a response - rendered template, redirect, JSON file, etc.
     
    2. service
       - contains rules, validations, computations, and coordinating actions between mulitple repositories
       - NEVER directly interacts with front end
       - Talks to the repository layer for database access
       - Can call other services if needed.
      
    3. model
       - represents the data structure (usually mapping database tables)
       - each module usually has one model class per table (we have a few exceptions due to how related some of the tables are)
       - defines fields, relationships, and sometimes validation rules
      
    4. repository
       - provides create, read, update, delete operations
       - can contain custom queries for complex operations
       - services call repositories to persist or fetch data
      
    Typical Flow:
   Controller -> Service -> Repository -> Database

   Model is present throughout all layers.
      
