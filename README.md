# IGT-tech-task
Description:


Create a REST services that would support the following functionality: 

Customer CRUD functionality (create, read, update, delete) 
   - user submits: name, surname, country, email, password
   - should be validated on server side for email’s correct format, non-empty values for other fields like name, surname, country, password
   
Customer debt case CRUD functionality (create, read, update, delete) 

   - user submits amount, currency, due date
   - the debt case should be linked to existing customer 
   - should be validated on server side for positive amount, correct date, and existing customer 
   - data should be stored in database
      
Application should be covered by unit and integration tests.

Optional bonus task:
  - to implement basic authentication to use the rest api calls
