# grand-exchange-io
Grand-exchange.io will be a full-stack, mock online store experience! As part of my final capstone project, given wireframes and stakeholder requirements, I'll create a responsive and modern-looking front end, while pairing that with a robust back end using Spring Boot. The project will be guided by User stories and will allow anyone to browse the exchange products, register for an account as well as creating personalized wishlists, and even add items to their cart.

## Objective
The objective of this project is to build a robust RESTful API, given a set of user stories, that compliments a front-end built in Vue.js, based on provided wireframes. 

Back-End Will Demonstrate:
- Use of the DAO pattern to access data in a SQL Database
- Written code meant to retrieve data from a RESTful API
- Implementation of server-side code to create a RESTful API
- Use of Auth to control access to API endpoints

Front-End Will Demonstrate:
- TBD

## Project Setup

![ALT](/database/m2_final_project_ERD.drawio.png)

## Back-End

#### Provided Stories & Use Cases

As a user of the system, I need to be able to register myself with a username, address information, and password.
As a user of the system, I need to be able to log in using my registered username and password.

#### API Endpoints:

1. POST `/register` - register a new user (Provided Use Case 1)
2. POST `/login` - existing user login (Provided Use Case 2)

### Required User Stories & Use Cases:

1. As an unauthenticated user, I can see a list of products for sale.
2. As an unauthenticated user, I can search for a list of products by name or SKU.
3. As an unauthenticated user, I can view additional information about a specific product (product detail).
4. As a user, I can view my shopping cart and see the following details:
    - The list of products, quantities, and prices in my cart
    - The subtotal of all products in my cart
    - The tax amount (in U.S. dollars) charged for my state
        - Obtain the tax rate from an external API using the URL: https://teapi.netlify.app/api/statetax?state=[state-code].
        - The state code is part of the user address information.
        - The tax rate returned from the API is a percentage. Convert this to a decimal value to use in calculating the tax amount.
    - The cart total, which is the subtotal plus the amount of tax
5. As a user, I can add a product to my shopping cart.
    - If the product is already in my cart, increase the quantity appropriately.
    - The quantity added must be positive.
6. As a user, I can remove a product from my shopping cart. This removes the item from the cart entirely, regardless of the quantity in the cart.
7. As a user I can clear my cart, removing all the items from the cart.

#### API Endpoints:

1. GET `/products` - get the list of products (Required Use Case 1)
2. GET `/products?sku={product_sku}&name={product_name}` - search for products (Required Use Case 2)
3. GET `/products/{id}` - get a single product (Required Use Case 3)
4. GET `/cart` - get the user's cart (Required Use Case 4)
5. POST `/cart/items` - add item to cart (Required Use Case 5)
6. DELETE `/cart/items/{itemId}` - remove item from cart (Required Use Case 6)
7. DELETE `/cart` - clear cart (Required Use Case 7)

### Additional Use Cases

1. As a user, I can see a list of all of my wishlists.
2. As a user, I can see a single wishlist, including a list of the items on the wishlist.
3. As a user, I can create and name a new wishlist.
4. As a user, I can delete an existing wishlist that I own.
5. As a user, I can add a product to a wishlist that I own. If the item is already in the wishlist, it's not added a second time, but no error is raised.
6. As a user, I can remove a product from a wishlist that I own. If the item isn't in the wishlist, it's not deleted, but no error is raised.

#### API endpoints:

1. GET `/wishlists` - get user wishlists (Bonus Use Case 1)
2. GET `/wishlists/{wishlistId}` - get wishlist (Bonus Use Case 2)
3. POST `/wishlists` - create wishlist (Bonus Use Case 3)
4. DELETE `/wishlists/{wishlistId}` - delete wishlist (Bonus Use Case 4)
5. POST `/wishlists/{wishlistId}/products/{productId}` - add product to wishlist (Bonus Use Case 5)
6. DELETE `/wishlists/{wishlistId}/products/{productId}` - remove product from wishlist (Bonus Use Case 6)

## Front-End

Details to come, check back soon!...
