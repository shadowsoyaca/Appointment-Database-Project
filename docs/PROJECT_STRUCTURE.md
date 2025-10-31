Combinations Between Tables:
-----------------------------
 - abstractmodel holds every abstract model utilized in the models.
 - util holds helper methods that can be utilized across the entire backend directory
 - security involves the management of the users, views, privileges, and other account factors.
-------------------------------------------------------------------------------------------------------------------------------------------------------------

Format Instructions:
-----------------------
 - In each of these labeled directories and the sub-folders of the common directory, there will be four additional directories:

   1. controller
      - Is the bridge between front-end and back-end
      - receives http requests (example: get, post, put, delete, etc.)
      - calls service layer to process business logic
      - returns a response - rendered template, redirect, JSON file, etc.
     
    2. service
       - contains rules, validations, computations, and coordinating actions between multiple repositories
       - NEVER directly interacts with front end
       - Talks to the repository layer for database access
       - Can call other services if needed.
      
    3. model
       - represents the data structure (usually mapping database tables)
       - each module usually has one model class per table (we have a few exceptions due to how related some of the tables are)
       - defines fields and relationships
      
    4. repository
       - provides create, read, update, delete operations
       - can contain custom queries for complex operations
       - services call repositories to persist or fetch data
      
    Typical Flow:
   Controller -> Service -> Repository -> Database

   Model is present throughout all layers.

-----------------------------------------------------------------------------------------------



      
