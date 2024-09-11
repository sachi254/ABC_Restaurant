# **ABC Restaurant Online Reservation System**

## **Table of Contents**
- [Technologies](#technologies)
- [Usage](#usage)
- [Login Credentials](#login-credentials)
- [Functionality](#functionality)
- [Security](#security)
- [Local Setup](#local-setup)

---

### **Technologies**
- **Back End:** Spring Boot 3, Java, MySQL
- **Front End:** Angular 18, TypeScript, HTML, CSS
- **Security:** Spring Security with JWT authentication
- **Database:** MySQL (abcres_db schema)
- **Other:** Node.js, npm

---

### **Usage**
Once the project is running, you can use the system to manage restaurant reservations and explore the menu. Different user roles have various levels of access, such as Admin, Staff, and Customers.

---

### **Login Credentials**
- **Admin**  
  Email: `abcadmin24@gmail.com`  
  Password: `24@Ad#abc`

- **Staff**  
  Email: `abcstaff24@gmail.com`  
  Password: `25#St@abc`

- **Customer 1**  
  Email: `sachintha24@gmail.com`  
  Password: `Sachi@24`

- **Customer 2**  
  Email: `ravihansa26@gmail.com`  
  Password: `Ravi@2024`

---

### **Functionality**

**Admin:**
- Login to the system.
- Create products under specific categories.
- Update product details.
- Delete products.
- Manage categories.
- View categories and products.
- Browse categories by title.
- Browse products by title.
- Approve customer reservation requests.
- Cancel customer reservation requests.
- View managed reservation statuses.
- Logout from the system.

**Customer:**
- Sign up to the system.
- Login to the system.
- Submit reservation requests to the restaurant.
- Contact the restaurant with specific requirements.
- View reservation request status.
- View categories and products.
- Browse categories by title.
- Browse products by title.
- Logout from the system.

**Staff:**
- Login to the system.
- Approve customer reservation requests.
- Cancel customer reservation requests.
- View managed reservation statuses.
- View categories and products.
- Browse categories by title.
- Browse products by title.
- Logout from the system.

---

### **Security**
The system uses JWT-based authentication with role-based access control. Ensure to keep your credentials secure and do not share sensitive information.

---

### **Local Setup**

1. **Clone and Download:**
   - Clone the project from GitHub or download the ZIP file.
   
2. **Back-End Setup:**
   - Open the `abc-restaurant` folder in your IDE.
   - Create a MySQL database named `abcres_db` or modify the schema name in the application properties.

3. **Run the Spring Boot Application:**
   - Ensure the database connection is successful.
   - Run the application to start the server on **port 8080**.

4. **Front-End Setup:**
   - Open the `restaurant_angular` folder in VS Code.
   - If you encounter the error `"Try installing with 'npm install'"`, run the command:  
     ```bash
     npm install
     ```
   - If another error appears like `"To address all issues, run: 'npm audit fix'"`, run:  
     ```bash
     npm audit fix
     ```

5. **Serve the Front End:**
   - Run the following command to serve the application:  
     ```bash
     ng serve
     ```
   - Access the application at `http://localhost:4200`.

---

### **GitHub Repository**
- **Repository Link:** [ABC Restaurant GitHub Repository](https://github.com/sachi254/)

---

**Thank you!**  
For further information or troubleshooting, please refer to the documentation in the project or contact [Sachintha](mailto:76sachi2@gmail.com).

---
