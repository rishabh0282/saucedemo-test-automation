## Sample JIRA Defect Reports

### DEF-001: Login allows empty username submission (example)

- **Project**: SAUCEDEMO
- **Issue Type**: Bug  
- **Summary**: Login form allows empty username submission and displays generic error  
- **Environment**:  
  - **URL**: `https://www.saucedemo.com/`  
  - **Browser**: Chrome 129, Windows 10  
  - **Build**: v1.0.0  
- **Pre-conditions**: User is on the SauceDemo login page.  
- **Steps to Reproduce**:  
  1. Navigate to the SauceDemo login page.  
  2. Leave the username field empty.  
  3. Enter `secret_sauce` in the password field.  
  4. Click the `Login` button.  
- **Expected Result**:  
  - User should see a clear validation message like: `Username is required`.  
- **Actual Result**:  
  - Generic error message is shown: `Epic sadface: Username and password do not match any user in this service`.  
- **Severity**: Medium  
- **Priority**: High  
- **Attachments**: `login-empty-username.png` (screenshot)  
- **Detected By**: Automation (Cucumber scenario: `Login with invalid password`).  

---

### DEF-002: Checkout allows non-numeric postal code (example)

- **Project**: SAUCEDEMO  
- **Issue Type**: Bug  
- **Summary**: Postal code field accepts alphabetic characters during checkout  
- **Environment**:  
  - **URL**: `https://www.saucedemo.com/`  
  - **Browser**: Chrome 129, Windows 10  
  - **Build**: v1.0.0  
- **Pre-conditions**:  
  - User is logged in as `standard_user`.  
  - User has at least one item in the cart.  
- **Steps to Reproduce**:  
  1. Proceed to checkout information page.  
  2. Enter `John` in First Name, `Doe` in Last Name.  
  3. Enter `ABCDE` in Postal Code field.  
  4. Click `Continue`.  
- **Expected Result**:  
  - Validation should prevent non-numeric values and display an error such as `Please enter a valid postal code`.  
- **Actual Result**:  
  - System accepts `ABCDE` and allows navigation to the overview page without validation error.  
- **Severity**: Low  
- **Priority**: Medium  
- **Attachments**: `checkout-alpha-postal-code.png`.  
- **Detected By**: Manual exploratory test.  

---

### DEF-003: Cart badge does not reset after logout (example)

- **Project**: SAUCEDEMO  
- **Issue Type**: Bug  
- **Summary**: Shopping cart badge shows stale item count after logout and login  
- **Environment**:  
  - **URL**: `https://www.saucedemo.com/`  
  - **Browser**: Chrome 129, Windows 10  
  - **Build**: v1.0.0  
- **Pre-conditions**:  
  - User can successfully login as `standard_user`.  
- **Steps to Reproduce**:  
  1. Log in as `standard_user`.  
  2. Add 2 items to the cart.  
  3. Open the side menu and click `Logout`.  
  4. Log in again as `standard_user`.  
- **Expected Result**:  
  - Cart badge should be cleared and show no count on fresh login.  
- **Actual Result**:  
  - Cart badge still displays `2` even though cart is empty.  
- **Severity**: Medium  
- **Priority**: Medium  
- **Detected By**: Automation (Cucumber scenario: `Add multiple items to cart`).  


