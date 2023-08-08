# Online Store
## Introduction
The "Online Store" project is a Java-based web application that allows for efficient management of products and users within a virtual store. Leveraging MySQL as the database and Hibernate as the ORM framework, this project offers a comprehensive and organized approach to online commerce.

## Functional Overview
The project encompasses the following entities:

### Users:
* First name
* Last name
* Password (encrypted using BCrypt)
* Username
* Role (admin or user)

### Products:
* Product name
* Description
* Price

### Administrator Functionalities
Users with the "admin" role are granted access to the following features:

1. Dedicated page at /admin
1. View, edit, and delete products at /admin/items
1. View user information and delete users at /admin/users


### User Functionalities
Standard users can make use of the following functionalities:

1. Adding products to the shopping cart
1. Purchasing items from the cart
1. Removing products from the cart
1. Modifying personal information
1. Logging out from the account
1. Advanced Validation
1. To ensure data integrity, the application employs advanced dual-layer validation for both user and product data.

## Technologies Utilized
- Java
- Spring Framework
- Maven
- MySQL
- Hibernate (ORM)

## Running the Project
1. Clone the repository: git clone https://github.com/denomelchenko/store.git.
1. Install the required dependencies.
1. Launch the application.
1. Open a web browser and navigate to http://localhost:8080.