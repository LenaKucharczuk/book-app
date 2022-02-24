# Book app

Application which allows creating list of books (for example books to read).

1. List page - contains list of book records with fields: author, title, ISBN. When no records are stored then
   information ‘No records’ is be displayed. Contains link to add new record.
2. New record page - displays simple form with fields: author, title, ISBN and button Add. On submitting the form,
   application needs to persist data from the form. Each time, form is submitted, application persists new record.
   Application should have validation rule on author that forename or surname should start from letter 'A'. Application
   must display failure message, mark failed field and holds user on new records form when validation fails. On success
   application redirects user to list of added records.
3. Additionally, it has rest service with two methods. One method adds a book to the storage and second method returns 
   all books’ stored in storage.
