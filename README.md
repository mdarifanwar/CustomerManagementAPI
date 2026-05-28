# Customer Management API and Display System

A Spring Boot web application for managing customer data with user authentication, data entry forms, and database integration.

## Features

- **User Authentication**: Simple login system with Spring Security
- **Customer Management**: Add, view, edit, and delete customer records
- **Data Validation**: Form validation for customer details
- **Responsive UI**: Modern Bootstrap-based interface
- **Database Integration**: MySQL/H2 database support with JPA
- **Real-time Updates**: DevTools for hot reloading during development

## Technologies Used

- **Backend**: Java 17, Spring Boot 3.1.5
- **Framework**: Spring MVC, Spring Data JPA, Spring Security
- **Database**: MySQL (configurable to H2 for development)
- **Template Engine**: Thymeleaf
- **Frontend**: Bootstrap 5.1.3, Font Awesome 6.0
- **Build Tool**: Maven

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ (or use H2 for development)
- IDE with Spring Boot support (Eclipse, IntelliJ IDEA, VS Code)

## Database Setup

### Option 1: MySQL (Recommended for Production)

1. Install MySQL and create a database:
   ```sql
   CREATE DATABASE customer_db;
   ```

2. Create a local `.env` file in the project root with your MySQL and app login credentials:
  ```properties
  DB_USERNAME=your_mysql_username
  DB_PASSWORD=your_mysql_password
  APP_USERNAME=your_app_username
  APP_PASSWORD=your_app_password
  ```

3. If you prefer environment variables instead of a `.env` file, export the same names before running the app.

### Option 2: H2 Database (Development/Testing)

1. Comment out MySQL configuration in `application.properties`
2. Uncomment H2 configuration lines
3. Access H2 console at: `http://localhost:8080/h2-console`

## Getting Started

### 1. Clone and Setup

```bash
cd your-project-directory
# Project files are already created
```

### 2. Configure Database

Edit `.env` or set environment variables for your database and login setup.

### 3. Build and Run

```bash
# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

### 4. Access the Application

- **URL**: http://localhost:8080
- **Login Credentials**: use the values configured in `APP_USERNAME` and `APP_PASSWORD`

## Application Structure

```
src/
├── main/
│   ├── java/com/example/customerdatasystem/
│   │   ├── CustomerDataSystemApplication.java    # Main application class
│   │   ├── config/
│   │   │   └── SecurityConfig.java               # Spring Security configuration
│   │   ├── controller/
│   │   │   └── CustomerController.java           # Web controllers
│   │   ├── entity/
│   │   │   └── Customer.java                     # JPA entity
│   │   ├── repository/
│   │   │   └── CustomerRepository.java           # Data repository
│   │   └── service/
│   │       └── CustomerService.java              # Business logic
│   └── resources/
│       ├── application.properties                # Configuration
│       └── templates/                           # Thymeleaf templates
│           ├── login.html
│           ├── dashboard.html
│           ├── customer-form.html
│           ├── customer-list.html
│           └── customer-details.html
```

## Usage Guide

### 1. Login
- Navigate to http://localhost:8080
- Use the credentials configured in your local environment
- Redirected to dashboard after successful login

### 2. Dashboard
- View total customer count
- Quick navigation to add customers or view all customers

### 3. Add Customer
- Click "Add New Customer" from dashboard or navigation
- Fill in required fields:
  - Full Name (required)
  - Email Address (required, must be unique)
  - Phone Number (required)
  - Address (required)
- Submit form to save

### 4. View Customers
- Navigate to "View Customers" to see all records
- Table displays all customer information
- Use action buttons to view, edit, or delete customers

### 5. Customer Details
- Click "View" button on any customer row
- See complete customer information
- Quick actions for email, phone, and editing

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Dashboard (redirects to login if not authenticated) |
| GET | `/login` | Login page |
| GET | `/dashboard` | Main dashboard |
| GET | `/customer/new` | Customer entry form |
| POST | `/customer/save` | Save customer data |
| GET | `/customers` | List all customers |
| GET | `/customer/{id}` | View customer details |
| GET | `/customer/edit/{id}` | Edit customer form |
| POST | `/customer/delete/{id}` | Delete customer |

## Validation Rules

- **Name**: Required, not blank
- **Email**: Required, valid email format, unique
- **Phone**: Required, valid phone number format
- **Address**: Required, not blank, max 500 characters

## Development

### Hot Reloading
The application includes Spring Boot DevTools for automatic restart on code changes.

### Adding New Features
1. Create/modify entities in `entity` package
2. Add repository methods in `repository` package
3. Implement business logic in `service` package
4. Create controllers in `controller` package
5. Design templates in `templates` folder

### Customization
- Modify `SecurityConfig.java` for authentication changes
- Update `application.properties` for configuration changes
- Customize templates for UI changes
- Add validation annotations in entity classes

## Troubleshooting

### Database Connection Issues
- Verify MySQL is running
- Check credentials in `application.properties`
- Ensure database `customer_db` exists

### Port Already in Use
- Change port in `application.properties`:
  ```properties
  server.port=8081
  ```

### Build Issues
- Ensure Java 17+ is installed
- Run `mvn clean install` to resolve dependencies

## Security Notes

- In production, use proper user management
- Configure HTTPS for production deployment
- Use environment variables for sensitive configuration

## Contributing

1. Follow Spring Boot best practices
2. Add proper validation and error handling
3. Include unit tests for new features
4. Update documentation for new endpoints

## License

This project is for educational purposes as part of a Spring Boot learning exercise.
