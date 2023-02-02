# BankTransactionDisplayApp

### Technologies: <br/>
    Back-end: Spring
    Front-end: JavaFX
    Database: H2

Project for Object-Oriented Technologies course. Simple Desktop Application allowing the user to display a list of transactions from different banks. App’s server receives .csv files before adding it to the database.

### Description:

Our system monitors a storage, which is a folder where we can upload bank statements in the form of '.csv' files.

Upon adding a file, our system identifies the bank from which the file originates and extracts the most important information for us, which is common across all banks (currently, we support transaction histories from three banks: Millennium, PKOBP, and Citibank, but it is easily expandable).

Data exchange in the API is implemented using Retrofit. The simple UI displays a table of transaction records from all banks, allowing for tagging of individual records and filtering them based on specific tags.